package edu.ricm3.game.purgatoire;

import ricm3.interpreter.IEntityType;

public class Special extends Entity {

	Special(Level level, Stunt heaven, Stunt hell, int x, int y, int width, int height) {
		super(level, heaven, hell, x, y, width, height);
	}

	public Special(Level level, int x, int y, int width, int height) {
		super(level, new HeavenSpecialStunt(), new HellSpecialStunt(), x, y, width, height);
		m_type = IEntityType.TEAM;
		this.setKarmaToGive(1);
	}
}
