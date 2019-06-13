package edu.ricm3.game.purgatoire.entities;

import edu.ricm3.game.purgatoire.Level;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.stunts.HeavenSoulStunt;
import edu.ricm3.game.purgatoire.stunts.HellSoulStunt;
import edu.ricm3.game.purgatoire.stunts.Stunt;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IEntityType;

public class Soul extends Entity {

	Soul(Level level, Stunt heaven, Stunt hell, int x, int y, int size) {
		super(level, heaven, hell, x, y, size);
	}

	public Soul(Level level, int x, int y, int size) {
		super(level, new HeavenSoulStunt(), new HellSoulStunt(), x, y, size);
		m_type = IEntityType.ADVERSARY;
	}

	public Soul(Level level, int x, int y) {
		super(level, new HeavenSoulStunt(), new HellSoulStunt(), x, y, Options.SOUL_SIZE);
		m_type = IEntityType.ADVERSARY;
	}
	
	public Soul(Level level, int x, int y, IAutomaton automaton) {
		super(level, new HeavenSoulStunt(), new HellSoulStunt(), x, y, Options.SOUL_SIZE);
		m_type = IEntityType.ADVERSARY;
	}
}
