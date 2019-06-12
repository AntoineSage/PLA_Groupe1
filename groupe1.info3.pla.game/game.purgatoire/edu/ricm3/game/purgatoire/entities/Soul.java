package edu.ricm3.game.purgatoire.entities;

import edu.ricm3.game.purgatoire.Level;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.stunts.HeavenSoulStunt;
import edu.ricm3.game.purgatoire.stunts.HellSoulStunt;
import edu.ricm3.game.purgatoire.stunts.Stunt;
import ricm3.interpreter.IEntityType;

public class Soul extends Entity {

	Soul(Level level, Stunt heaven, Stunt hell, int x, int y, int width, int height) {
		super(level, heaven, hell, x, y, width, height);
	}

	public Soul(Level level, int x, int y, int width, int height) {
		super(level, new HeavenSoulStunt(), new HellSoulStunt(), x, y, width, height);
		m_type = IEntityType.ADVERSARY;
	}

	public Soul(Level level, int x, int y) {
		super(level, new HeavenSoulStunt(), new HellSoulStunt(), x, y, Options.SOUL_WIDTH, Options.SOUL_HEIGHT);
		m_type = IEntityType.ADVERSARY;
	}

}
