package edu.ricm3.game.purgatoire;

import ricm3.interpreter.IEntityType;

public class Obstacle extends Entity {

	Obstacle(Level level, Stunt heaven, Stunt hell, int x, int y, int width, int height) {
		super(level, heaven, hell, x, y, width, height);
		m_type = IEntityType.OBSTACLE;
	}

}
