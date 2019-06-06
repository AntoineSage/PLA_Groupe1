package edu.ricm3.game.purgatoire;

import ricm3.interpreter.IEntityType;

public class Nest extends Entity {

	Nest(Level level, Stunt heaven, Stunt hell, int x, int y, int width, int height) {
		super(level, heaven, hell, x, y, width, height);
	}

	public Nest(Level level, int x, int y, int width, int height) {
		super(level, new HeavenNestStunt(), new HellNestStunt(), x, y, width, height);
		m_type = IEntityType.DANGER;
	}
}
