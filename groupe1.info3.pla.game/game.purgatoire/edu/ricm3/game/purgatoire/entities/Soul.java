package edu.ricm3.game.purgatoire.entities;

import edu.ricm3.game.purgatoire.Level;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
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
		Singleton.notify(IEntityType.IType.ADVERSARY);
		m_type = IEntityType.ADVERSARY;
	}

	public Soul(Level level, int x, int y) {
		super(level, new HeavenSoulStunt(), new HellSoulStunt(), x, y, Options.SOUL_SIZE);
		Singleton.notify(IEntityType.IType.ADVERSARY);
		m_type = IEntityType.ADVERSARY;
	}

	public Soul(Level level, int x, int y, int soulSize, boolean unlimitedRange) {
		super(level, new HeavenSoulStunt(unlimitedRange), new HellSoulStunt(unlimitedRange), x, y, Options.SOUL_SIZE);
		Singleton.notify(IEntityType.IType.ADVERSARY);
		m_type = IEntityType.ADVERSARY;
	}

	public Soul(Level level, int x, int y, IAutomaton automaton) {
		super(level, new HeavenSoulStunt(automaton), new HellSoulStunt(automaton), x, y, Options.SOUL_SIZE);
		Singleton.notify(IEntityType.IType.ADVERSARY);
		m_type = IEntityType.ADVERSARY;
	}
}
