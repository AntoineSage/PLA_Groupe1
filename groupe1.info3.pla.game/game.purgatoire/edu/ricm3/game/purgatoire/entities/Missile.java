package edu.ricm3.game.purgatoire.entities;

import java.util.Iterator;
import java.util.List;

import edu.ricm3.game.purgatoire.Level;
import edu.ricm3.game.purgatoire.stunts.Stunt;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class Missile extends Entity {

	Entity m_player;

	Missile(Level level, Stunt heaven, Stunt hell, int x, int y, int width, int height, Entity p) {
		super(level, heaven, hell, x, y, width, height);
		m_type = IEntityType.MISSILE;
		m_player = p;
	}

	public Missile(Level level, Stunt heaven, Stunt hell, int x, int y, int width, int height, IDirection direction,
			Entity p) {
		super(level, heaven, hell, x, y, width, height);
		m_type = IEntityType.MISSILE;
		m_direction = direction;
		m_player = p;
	}

	@Override
	public void enterInCollisionWith(List<Entity> entities) {
		Iterator<Entity> iter = entities.iterator();
		while (iter.hasNext()) {
			Entity entity = iter.next();
			entity.m_currentStunt.takeDamage(m_currentStunt.getDMG());
		}
		die();
	}
}
