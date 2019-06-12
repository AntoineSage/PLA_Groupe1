package edu.ricm3.game.purgatoire.entities;

import edu.ricm3.game.purgatoire.Level;
import edu.ricm3.game.purgatoire.stunts.HeavenObstacleStunt;
import edu.ricm3.game.purgatoire.stunts.HellObstacleStunt;
import edu.ricm3.game.purgatoire.stunts.Stunt;
import ricm3.interpreter.IEntityType;

public class Obstacle extends Entity {

	Obstacle(Level level, Stunt heaven, Stunt hell, int x, int y, int size) {
		super(level, heaven, hell, x, y, size);
		m_type = IEntityType.OBSTACLE;
		m_HP = m_currentStunt.getMaxHP();
	}

	public Obstacle(Level level, int x, int y, int size) {
		super(level, new HeavenObstacleStunt(), new HellObstacleStunt(), x, y, size);
		m_type = IEntityType.OBSTACLE;
		m_HP = m_currentStunt.getMaxHP();
	}
}
