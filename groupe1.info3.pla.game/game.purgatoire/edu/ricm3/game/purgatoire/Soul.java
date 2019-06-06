package edu.ricm3.game.purgatoire;

import ricm3.interpreter.IEntityType;

public class Soul extends Entity {

	Soul(Level level, Stunt heaven, Stunt hell, int x, int y, int width, int height) {
		super(level, heaven, hell, x, y, width, height);
	}

	public Soul(Level level, int x, int y, int width, int height) {
		super(level, new HeavenSoulStunt(), new HellSoulStunt(), x, y, width, height);
		m_type = IEntityType.ADVERSARY;
		
	}
}
