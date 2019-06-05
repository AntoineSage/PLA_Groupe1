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
	void step(long now) {}

	void step(long now, Controller controller) {m_currentStunt.m_automaton.step(this, controller);}

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
