package edu.ricm3.game.purgatoire;

import java.awt.Rectangle;

public class Player extends Entity {
	int m_karma;
	int m_XP;

	Player(int karma, int XP, int HP, int maxHP, int DMG, int karmaToGive, Stunt heavenStunt, Stunt hellStunt,
			Stunt currentStunt, Level level, Rectangle bounds) {
		super(HP, maxHP, DMG, karmaToGive, heavenStunt, hellStunt, currentStunt, level, bounds);
		m_karma = karma;
		m_XP = XP;
	}

	Player(int x, int y, int w, int h, Level level) {
		Rectangle bounds = new Rectangle(x, y, w, h);
		m_bounds = bounds;
		m_level = level;
	}

	void addKarma(Entity e) {
		m_karma += e.m_karmaToGive;
	}

	void moveUP() {
		System.out.println(m_bounds.y);
		if (m_bounds.y == 0) {
			m_level.m_model.nextLevel();
		}
		m_bounds.y--;
	}

	void moveDown() {
		if (m_bounds.y < Options.LVL_HEIGHT - 1 - m_bounds.height) {
			m_bounds.y++;
		}
	}

	void moveR() {
		if (m_bounds.x < Options.LVL_WIDTH - m_bounds.height) {
			m_bounds.x++;
		}
	}

	void moveL() {
		if (m_bounds.x > 0) {
			m_bounds.x--;
		}
	}

	void nextLevel(Level newLevel) {
		m_level = newLevel;
		m_bounds.y = Options.LVL_HEIGHT - 1 - 3;
	}
}
