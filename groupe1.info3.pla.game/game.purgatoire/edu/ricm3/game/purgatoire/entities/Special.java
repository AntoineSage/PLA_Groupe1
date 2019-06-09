package edu.ricm3.game.purgatoire.entities;

import edu.ricm3.game.purgatoire.Level;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.stunts.HeavenSpecialStunt;
import edu.ricm3.game.purgatoire.stunts.HellSpecialStunt;
import edu.ricm3.game.purgatoire.stunts.Stunt;
import ricm3.interpreter.IEntityType;

public class Special extends Entity {

	Special(Level level, Stunt heaven, Stunt hell, int x, int y, int width, int height, int karmaToGive) {
		super(level, heaven, hell, x, y, width, height);
	}

	public Special(Level level, int x, int y, int width, int height) {
		super(level, new HeavenSpecialStunt(), new HellSpecialStunt(), x, y, width, height);
		m_type = IEntityType.TEAM;
		m_HP = Options.SPCL_HP;
	}

	public Special(Level level, int x, int y) {
		super(level, new HeavenSpecialStunt(), new HellSpecialStunt(), x, y, Options.SPCL_WIDTH, Options.SPCL_HEIGHT);
		m_type = IEntityType.TEAM;
		m_HP = Options.SPCL_HP;
	}
}
