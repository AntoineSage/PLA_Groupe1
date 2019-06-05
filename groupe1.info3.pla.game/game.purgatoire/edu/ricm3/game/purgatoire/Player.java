package edu.ricm3.game.purgatoire;

public class Player extends Entity {
	int m_karma;
	int m_XP;
	private int m_maxXP;
	private int m_rank;
	Model m_model;

	public Player(Model model, Level level, int x, int y, int width, int height) {
		super(level, new HeavenPlayerStunt(null), new HellPlayerStunt(null), x, y, width, height);
		m_model = model;
		m_heavenStunt.setAttachedEntity(this);
		m_hellStunt.setAttachedEntity(this);
	}

	void addKarma(Entity e) {
		m_karma += e.m_karmaToGive;
	}

	@Override
	void step(long now) {
	}

	void step(long now, Controller controller) {
		m_currentStunt.m_automaton.step(this, controller);
	}

	void nextLevel(Level newLevel) {
		m_level = newLevel;
		m_bounds.y = Options.LVL_HEIGHT - 2;
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

}
