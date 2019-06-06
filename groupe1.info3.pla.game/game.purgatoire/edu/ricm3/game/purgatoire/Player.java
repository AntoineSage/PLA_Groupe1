package edu.ricm3.game.purgatoire;

import ricm3.interpreter.IEntityType;

public class Player extends Entity {
	private int m_karma;
	private int m_XP;
	private int m_maxXP;
	private int m_rank;
	private Model m_model;

	public Player(Model model, Level level, int x, int y, int width, int height) {
		super(level, new HeavenPlayerStunt(null), new HellPlayerStunt(null), x, y, width, height);
		m_model = model;
		m_type = IEntityType.PLAYER;
	}

	public void addKarma(Entity e) {
		m_karma += e.m_karmaToGive;
		Singleton.getController().updateUI();
	}

	public void addKarma(int karma) {
		m_karma += karma;
		Singleton.getController().updateUI();
	}

	@Override
	public void step(long now) {
		m_currentStunt.m_automaton.step(this);
	}

	public void nextLevel(Level newLevel) {
		m_level = newLevel;
		m_bounds.y = Options.LVL_HEIGHT - 3;
		m_level.addEntity(this);
	}

	public int getKarma() {
		return m_karma;
	}

	public int getXP() {
		return m_XP;
	}

	public void setMaxXP(int maxXP) {
		m_maxXP = maxXP;
	}

	public int getMaxXP() {
		return m_maxXP;
	}

	public int getRank() {
		return m_rank;
	}

	public void addXP(double coef) {
		m_XP += m_karma * coef;
		m_XP = Math.max(m_XP, 0);
		Singleton.getController().updateUI();
	}

	@Override
	public void addHP(int HP) {
		Singleton.getController().updateUI();
		super.addHP(HP);
	}

	public void testKarma() {
		if (m_karma >= 0 && m_model.m_wt == WorldType.HEAVEN || m_karma <= 0 && m_model.m_wt == WorldType.HELL) {
			addXP(Options.COEF_KARMA_POS);
		} else {
			addXP(Options.COEF_KARMA_NEG);
			m_model.transform();
		}
		m_karma = 0;
	}
}
