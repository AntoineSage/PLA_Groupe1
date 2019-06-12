package edu.ricm3.game.purgatoire.entities;

import edu.ricm3.game.purgatoire.Level;
import edu.ricm3.game.purgatoire.Model;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.WorldType;
import edu.ricm3.game.purgatoire.stunts.HeavenPlayerStunt;
import edu.ricm3.game.purgatoire.stunts.HellPlayerStunt;
import edu.ricm3.game.purgatoire.stunts.PlayerStunt;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IEntityType;
import ricm3.parser.Ast.Automaton;

public class Player extends Entity {
	private int m_maxTotalHP;
	private int m_karma, m_maxKarma;
	private int m_XP;
	private int m_rank;
	private Model m_model;

	public Player(Model model, Level level, int x, int y) {
		super(level, new HeavenPlayerStunt(), new HellPlayerStunt(), x, y, Options.PLAYER_SIZE);
		m_model = model;
		m_type = IEntityType.PLAYER;
		m_XP = 0;
		m_HP = m_currentStunt.getMaxHP();
		m_maxKarma = Options.PLAYER_KARMA_MAX;
		((PlayerStunt) m_currentStunt).updateRankStats();
	}

	public void addKarma(Entity e) {
		m_karma += e.m_currentStunt.m_karmaToGive;
		Singleton.getController().updateKarmaUI();
	}

	public void addKarma(int karma) {
		m_karma += karma;
		if (m_karma > getMaxKarma())
			m_karma = getMaxKarma();
		else if (m_karma < -getMaxKarma())
			m_karma = -getMaxKarma();
		Singleton.getController().updateKarmaUI();
	}

	public void updateRank() {
		if (m_XP >= getMaxXP() && getRank() < Options.PLAYER_MAX_RANK) {
			m_rank++;
			Singleton.getController().updateRankUI();
		} else if (m_XP < getMinXP()) {
			m_rank--;
			Singleton.getController().updateRankUI();
		}

		int delta = getMaxTotalHP();
		((PlayerStunt) m_currentStunt).updateRankStats();
		delta = getMaxTotalHP() - delta;
		if (delta >= 0) {
			m_currentStunt.m_maxHP += delta;
			addHP(delta);
		} else {
			m_currentStunt.m_maxHP = Math.min(m_currentStunt.m_maxHP, getMaxTotalHP());
			setHP(Math.min(getHP(), m_currentStunt.m_maxHP));
		}

		if (Options.ECHO_PLAYER_UPDATE_STATS)
			System.out.println(
					"Update stats: " + delta + " delta, " + getMaxTotalHP() + " maxTotalHP, " + m_currentStunt.m_maxHP
							+ " maxHP, " + getHP() + " HP, " + m_currentStunt.getBaseDMG() + " baseDMG");
	}

	public void nextLevel(Level newLevel) {
		m_level = newLevel;
		m_bounds.y = Options.LVL_HEIGHT - m_bounds.height;
		m_level.addEntity(this);
	}

	public int getKarma() {
		return m_karma;
	}

	public int getMaxKarma() {
		return m_maxKarma;
	}

	public int getXP() {
		return m_XP;
	}

	public int getMinXP() {
		return Options.PLAYER_RANKS[m_rank];
	}

	public int getMaxXP() {
		return Options.PLAYER_RANKS[m_rank + 1];
	}

	public int getRank() {
		return m_rank;
	}

	public String getRankName() {
		return ((PlayerStunt) m_currentStunt).getRankName();
	}

	public void addXP(double coef) {
		m_XP += Math.abs(m_karma) * coef;
		m_XP = Math.max(m_XP, 0);
		if (getRank() == Options.PLAYER_MAX_RANK)
			m_XP = Math.min(getMaxXP(), m_XP);
		updateRank();
		Singleton.getController().updateXPUI();
	}

	@Override
	public void addHP(int HP) {
		super.addHP(HP);
		if (Options.ECHO_PLAYER_DAMAGE_TAKEN)
			System.out.println("Player damage taken: " + HP);
		Singleton.getController().updateHPUI();
	}

	public int getMaxTotalHP() {
		return m_maxTotalHP;
	}

	public void setMaxTotalHP(int newMax) {
		m_maxTotalHP = newMax;
	}

	public void setHP(int HP) {
		m_HP = HP;
	}

	public void testKarma() {
		if (m_karma >= 0 && m_model.getWorldType() == WorldType.HEAVEN
				|| m_karma <= 0 && m_model.getWorldType() == WorldType.HELL) {
			addXP(Options.COEF_KARMA_POS);
		} else {
			addXP(Options.COEF_KARMA_NEG);
			m_model.transform();
		}
		m_karma = 0;
	}

}
