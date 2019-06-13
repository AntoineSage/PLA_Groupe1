package edu.ricm3.game.purgatoire.entities;

import edu.ricm3.game.purgatoire.Level;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.stunts.HeavenSpecialStunt;
import edu.ricm3.game.purgatoire.stunts.HellSpecialStunt;
import edu.ricm3.game.purgatoire.stunts.Stunt;
import ricm3.interpreter.IEntityType;

public class Special extends Entity {

	Special(Level level, Stunt heaven, Stunt hell, int x, int y, int size, int karmaToGive) {
		super(level, heaven, hell, x, y, size);
	}

	public Special(Level level, int x, int y, int size) {
		super(level, new HeavenSpecialStunt(), new HellSpecialStunt(), x, y, size);
		m_type = IEntityType.TEAM;
	}

	public Special(Level level, int x, int y) {
		super(level, new HeavenSpecialStunt(), new HellSpecialStunt(), x, y, Options.SPCL_SIZE);
		m_type = IEntityType.TEAM;
	}

	public Special(Level level) {
		super(level, new HeavenSpecialStunt(), new HellSpecialStunt(), 0, 0, Options.SPCL_SIZE);
		m_type = IEntityType.TEAM;
		m_HP = m_currentStunt.getMaxHP();
	}
}
