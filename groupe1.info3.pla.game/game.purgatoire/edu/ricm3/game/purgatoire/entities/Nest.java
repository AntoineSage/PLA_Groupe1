package edu.ricm3.game.purgatoire.entities;

import edu.ricm3.game.purgatoire.Level;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.stunts.HeavenNestStunt;
import edu.ricm3.game.purgatoire.stunts.HellNestStunt;
import edu.ricm3.game.purgatoire.stunts.Stunt;
import ricm3.interpreter.IEntityType;

public class Nest extends Entity {

	Nest(Level level, Stunt heaven, Stunt hell, int x, int y, int size) {
		super(level, heaven, hell, x, y, size);
	}

	public Nest(Level level, int x, int y, int size) {
		super(level, new HeavenNestStunt(), new HellNestStunt(), x, y, size);
		m_type = IEntityType.DANGER;
	}

	public Nest(Level level, int x, int y) {
		super(level, new HeavenNestStunt(), new HellNestStunt(), x, y, Options.NEST_SIZE);
		m_type = IEntityType.DANGER;
	}

}
