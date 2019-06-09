package edu.ricm3.game.purgatoire.entities;

import edu.ricm3.game.purgatoire.Level;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.stunts.HeavenNestStunt;
import edu.ricm3.game.purgatoire.stunts.HellNestStunt;
import edu.ricm3.game.purgatoire.stunts.Stunt;
import ricm3.interpreter.IEntityType;

public class Nest extends Entity {

	Nest(Level level, Stunt heaven, Stunt hell, int x, int y, int width, int height) {
		super(level, heaven, hell, x, y, width, height);
	}

	public Nest(Level level, int x, int y, int width, int height) {
		super(level, new HeavenNestStunt(), new HellNestStunt(), x, y, width, height);
		m_type = IEntityType.DANGER;
		m_HP = Options.NEST_HP;
	}

	public Nest(Level level, int x, int y) {
		super(level, new HeavenNestStunt(), new HellNestStunt(), x, y, Options.NEST_WIDTH, Options.NEST_HEIGHT);
		m_type = IEntityType.DANGER;
		m_HP = Options.NEST_HP;
	}
}
