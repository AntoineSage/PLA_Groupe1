package edu.ricm3.game.purgatoire;

import ricm3.interpreter.IEntityType;

public class Player extends Entity {
	int m_karma;
	int m_XP;
	private int m_maxXP;
	private int m_rank;
	Model m_model;

	public Player(Model model, Level level, int x, int y, int width, int height) {
		super(level, new HeavenPlayerStunt(null), new HellPlayerStunt(null), x, y, width, height);
		m_model = model;
		m_type = IEntityType.PLAYER;
		setDMG(100);
	}

	void addKarma(Entity e) {
		m_karma += e.m_karmaToGive;
	}
	
	@Override
	void step(long now) {
		m_currentStunt.step(now);;
	}

	void nextLevel(Level newLevel) {
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

	public int getMaxXP() {
		return m_maxXP;
	}

	public int getRank() {
		return m_rank;
	}

	void addXP(double coef) {
		m_XP += m_karma * coef;
	}

	void testKarma() {
		if (m_karma >= 0 && m_model.m_wt == WorldType.HEAVEN || m_karma <= 0 && m_model.m_wt == WorldType.HELL) {
			addXP(Options.COEF_KARMA_POS);
		} else {
			addXP(Options.COEF_KARMA_NEG);
			m_model.transform();
		}
		m_karma = 0;
	}

	public void addHp(int heal) {
		m_HP = Math.min(m_maxHP, m_HP + heal);
	}
}
