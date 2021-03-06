package edu.ricm3.game.purgatoire.entities;

import java.util.Iterator;
import java.util.List;

import edu.ricm3.game.purgatoire.Level;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.stunts.Stunt;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class Missile extends Entity {

	Entity m_owner;

	Missile(Level level, Stunt heaven, Stunt hell, int x, int y, int size, Entity p) {
		super(level, heaven, hell, x, y, size);
		Singleton.notify(IEntityType.IType.MISSILE);
		m_type = IEntityType.MISSILE;
		m_owner = p;
	}

	public Missile(Level level, Stunt heaven, Stunt hell, int x, int y, int size, IDirection direction, Entity p) {
		super(level, heaven, hell, x, y, size);
		Singleton.notify(IEntityType.IType.MISSILE);
		m_type = IEntityType.MISSILE;
		m_direction = direction;
		m_owner = p;
	}

	@Override
	public void enterInCollisionWith(List<Entity> entities) {
		Iterator<Entity> iter = entities.iterator();
		while (iter.hasNext()) {
			Entity entity = iter.next();
			entity.m_currentStunt.takeDamage(m_owner);
		}
		die();
	}

	public Entity getOwner() {
		return m_owner;
	}
}
