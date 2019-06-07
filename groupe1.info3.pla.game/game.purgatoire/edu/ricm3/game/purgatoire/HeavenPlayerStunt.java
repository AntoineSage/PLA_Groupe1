package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.util.Iterator;

import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HeavenPlayerStunt extends Stunt {

	Timer m_dashTimer;

	HeavenPlayerStunt(Entity entity) {
		super(Singleton.getNewPlayerHeavenAut(), entity, Color.BLUE);
		m_dashTimer = new Timer(m_cooldownDash);
		m_entity.m_maxHP = Options.HEAVEN_PLAYER_HP_MAX;
		m_entity.m_DMG = Options.HEAVEN_PLAYER_DMG;

	}

	@Override
	void pop(IDirection d) {
		if (m_dashTimer.end()) {
			dash(m_entity.m_direction);
			System.out.println("dash");
			m_dashTimer.start(m_cooldownDash * 1000);
		}
		System.out.println("pop heaven player");
	}

	@Override
	void wizz(IDirection d) {
		Special special = (Special)m_entity.superposedWith(IEntityType.TEAM);
		if(special != null) {
			special.pop(null);
		}
		System.out.println("wizz heaven player");
	}

	@Override
	void hit(IDirection d) {
		int y, yMin, yMax, x, xMin, xMax;
		switch (d) {
		case NORTH:
			y = m_entity.m_bounds.y;
			yMin = y - 2 * m_entity.m_bounds.height;
			while (y > 1 && y > yMin) {
				for (x = m_entity.m_bounds.x; x < m_entity.m_bounds.x + m_entity.m_bounds.width; x++) {
					Iterator<Entity> iter = m_entity.m_level.m_collisionGrid.m_grid[x][y - 1].iterator();
					while (iter.hasNext()) {
						Entity e = iter.next();
						e.m_currentStunt.getDamage(m_entity.m_DMG);
					}
				}
				y--;
			}
			m_entity.m_direction = IDirection.NORTH;
			break;
		case SOUTH:
			y = m_entity.m_bounds.y + 3;
			yMax = y + 2 * m_entity.m_bounds.height;
			while (y < Options.LVL_HEIGHT && y < yMax) {
				for (x = m_entity.m_bounds.x; x < m_entity.m_bounds.x + m_entity.m_bounds.width; x++) {
					Iterator<Entity> iter = m_entity.m_level.m_collisionGrid.m_grid[x][y].iterator();
					while (iter.hasNext()) {
						Entity e = iter.next();
						e.m_currentStunt.getDamage(m_entity.m_DMG);
					}
				}
				y++;
			}
			m_entity.m_direction = IDirection.SOUTH;
			break;
		case EAST:
			x = m_entity.m_bounds.x + m_entity.m_bounds.width;
			xMax = x + 2 * m_entity.m_bounds.width;
			while (x < Options.LVL_WIDTH && x < xMax) {
				for (y = m_entity.m_bounds.y; y < m_entity.m_bounds.y + m_entity.m_bounds.height; y++) {
					Iterator<Entity> iter = m_entity.m_level.m_collisionGrid.m_grid[x][y].iterator();
					while (iter.hasNext()) {
						Entity e = iter.next();
						e.m_currentStunt.getDamage(m_entity.m_DMG);
					}
				}
				x++;
			}
			m_entity.m_direction = IDirection.EAST;
			break;
		case WEST:
			x = m_entity.m_bounds.x;
			xMin = x - 2 * m_entity.m_bounds.width;
			while (x > 1 && x > xMin) {
				for (y = m_entity.m_bounds.y; y < m_entity.m_bounds.y + m_entity.m_bounds.height; y++) {
					Iterator<Entity> iter = m_entity.m_level.m_collisionGrid.m_grid[x-1][y].iterator();
					while (iter.hasNext()) {
						Entity e = iter.next();
						e.m_currentStunt.getDamage(m_entity.m_DMG);
					}
				}
				x--;
			}
			m_entity.m_direction = IDirection.WEST;
			break;
		}
	}

	@Override
	void egg() {
		System.out.println("egg heaven");
	}

	@Override
	void getDamage(int DMG) {
		System.out.println("getDamage heaven");
	}

	@Override
	public void step(long now) {
		super.step(now);
		if (m_dashTimer.m_previousNow == 0)
			m_dashTimer.m_previousNow = now;
		m_dashTimer.step(now);
	}
}
