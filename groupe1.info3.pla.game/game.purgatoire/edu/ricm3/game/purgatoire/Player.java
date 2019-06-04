package edu.ricm3.game.purgatoire;

public class Player extends Entity {
	int m_karma;
	int m_XP;

	Player(int karma, int XP, int HP, int maxHP, int DMG, int karmaToGive, Stunt heavenStunt, Stunt hellStunt,
			Stunt currentStunt, Level level) {
		super(HP, maxHP, DMG, karmaToGive, heavenStunt, hellStunt, currentStunt, level);
		m_karma = karma;
		m_XP = XP;
	}

	void addKarma(Entity e) {
		m_karma += e.m_karmaToGive;
	}
}
