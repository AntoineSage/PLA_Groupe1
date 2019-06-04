package edu.ricm3.game.purgatoire;

public class Entity {
	int m_HP, m_maxHP;
	int m_DMG;
	int m_karmaToGive;
	Stunt m_heavenStunt, m_hellStunt, m_currentStunt;
	Level m_level;

	Entity() {

	}

	Entity(int HP, int maxHP, int DMG, int karmaToGive, Stunt heavenStunt, Stunt hellStunt, Stunt currentStunt,
			Level level) {
		m_HP = HP;
		m_maxHP = maxHP;
		m_DMG = DMG;
		m_heavenStunt = heavenStunt;
		m_hellStunt = hellStunt;
		m_currentStunt = currentStunt;
		m_level = level;
	}

	void transfrom() {

	}

	WorldType getWorldType() {
		return m_level.getWorldType();
	}

	void hit() {
		m_currentStunt.hit();
	}

	void move() {
		m_currentStunt.move();
	}

	void pop() {
		m_currentStunt.pop();
	}

	void wizz() {
		m_currentStunt.wizz();
	}

	void egg() {
		m_currentStunt.egg();
	}
}
