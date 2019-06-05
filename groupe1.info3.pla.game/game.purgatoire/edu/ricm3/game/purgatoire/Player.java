package edu.ricm3.game.purgatoire;

public class Player extends Entity {
	int m_karma;
	int m_XP;
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
}
