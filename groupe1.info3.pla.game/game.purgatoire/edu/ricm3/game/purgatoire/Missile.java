package edu.ricm3.game.purgatoire;

import java.util.Iterator;
import java.util.List;

import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class Missile extends Entity {

	Player m_player;

	Missile(Level level, Stunt heaven, Stunt hell, int x, int y, int width, int height) {
		super(level, heaven, hell, x, y, width, height);
		m_type = IEntityType.MISSILE;
	}

	Missile(Level level, Stunt heaven, Stunt hell, int x, int y, int width, int height, IDirection direction) {
		super(level, heaven, hell, x, y, width, height);
		m_type = IEntityType.MISSILE;
		m_direction = direction;
	}

	@Override
	public void enterInCollisionWith(List<Entity> entities) {
		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext()) {
			Entity entity = iter.next();
			entity.m_currentStunt.getDamage(m_DMG);
		}
	}
}
