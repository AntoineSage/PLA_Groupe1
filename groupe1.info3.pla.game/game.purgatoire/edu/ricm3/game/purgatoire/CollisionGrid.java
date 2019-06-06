package edu.ricm3.game.purgatoire;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class CollisionGrid {

	List<Entity> m_grid[][];

	public CollisionGrid() {
		m_grid = new List[Options.LVL_WIDTH][Options.LVL_HEIGHT];
		for (int i = 0; i < Options.LVL_WIDTH; i++) {
			for (int j = 0; j < Options.LVL_HEIGHT; j++) {
				m_grid[i][j] = new LinkedList<Entity>();
			}
		}
	}

	void addEntity(Entity entity) {
		if (isOk(entity) == true) {
			for (int i = entity.m_bounds.x; i < entity.m_bounds.x + entity.m_bounds.width; i++) {
				for (int j = entity.m_bounds.y; j < entity.m_bounds.y + entity.m_bounds.height; j++) {
					m_grid[i][j].add(entity);
				}
			}
		}
	}

	boolean isOk(Entity entity) {
		int x, y, width, height;
		x = entity.m_bounds.x;
		y = entity.m_bounds.y;
		width = entity.m_bounds.width;
		height = entity.m_bounds.height;
		if (x >= 0 && (x < Options.LVL_WIDTH) && (x + width - 1 < Options.LVL_WIDTH) && (x + width - 1 >= 0))
			if (y >= 0 && (y < Options.LVL_HEIGHT) && (y + height - 1 < Options.LVL_HEIGHT) && (y + height - 1 >= 0)) {
				return true;
			}
		return false;
	}

	public void updateEntity(Entity e, int x, int y) {
		for (int i = e.m_bounds.x; i < e.m_bounds.x + e.m_bounds.width; i++) {
			for (int j = e.m_bounds.y; j < e.m_bounds.y + e.m_bounds.height; j++) {
				m_grid[i][j].remove(e);
			}
		}

		for (int i = e.m_bounds.x + x; i < e.m_bounds.x + x + e.m_bounds.width; i++) {
			for (int j = e.m_bounds.y + y; j < e.m_bounds.y + y + e.m_bounds.height; j++) {
				if (m_grid[i][j] == null) {
					m_grid[i][j] = new LinkedList<Entity>();
				}
				m_grid[i][j].add(e);
			}
		}
	}

	public boolean wontCollide(Entity entity, IDirection d) {
		switch (d) {
		case EAST:
			for (int i = entity.m_bounds.y; i < entity.m_bounds.y + entity.m_bounds.height; i++) {
				Iterator<Entity> iter = m_grid[entity.m_bounds.x + entity.m_bounds.width][i].iterator();
				while (iter.hasNext()) {
					Entity e = iter.next();
					if (entity.m_type.isCollidingWith(e.m_type)) {
						return false;
					}
				}
			}
			break;
		case NORTH:
			for (int i = entity.m_bounds.x; i < entity.m_bounds.x + entity.m_bounds.width; i++) {
				Iterator<Entity> iter = m_grid[i][entity.m_bounds.y - 1].iterator();
				while (iter.hasNext()) {
					Entity e = iter.next();
					if (entity.m_type.isCollidingWith(e.m_type)) {
						return false;
					}
				}
			}
			break;
		case SOUTH:
			for (int i = entity.m_bounds.x; i < entity.m_bounds.x + entity.m_bounds.width; i++) {
				Iterator<Entity> iter = m_grid[i][entity.m_bounds.y + entity.m_bounds.height].iterator();
				while (iter.hasNext()) {
					Entity e = iter.next();
					if (entity.m_type.isCollidingWith(e.m_type)) {
						return false;
					}
				}
			}
			break;
		case WEST:
			for (int i = entity.m_bounds.y; i < entity.m_bounds.y + entity.m_bounds.height; i++) {
				Iterator<Entity> iter = m_grid[entity.m_bounds.x - 1][i].iterator();
				while (iter.hasNext()) {
					Entity e = iter.next();
					if (entity.m_type.isCollidingWith(e.m_type)) {
						return false;
					}
				}
			}
			break;
		default:
			throw new IllegalStateException();

		}
		return true;
	}

	public void removeEntity(Entity e) {
		for (int i = e.m_bounds.x; i < e.m_bounds.x + e.m_bounds.width; i++) {
			for (int j = e.m_bounds.y; j < e.m_bounds.y + e.m_bounds.height; j++) {
				m_grid[i][j].remove(e);
			}
		}
	}

	public Entity testCollisionWithType(Entity e, IEntityType type) {
		for (int i = e.m_bounds.x; i < e.m_bounds.x + e.m_bounds.width; i++) {
			for (int j = e.m_bounds.y; j < e.m_bounds.y + e.m_bounds.height; j++) {
				Iterator<Entity> iter = m_grid[i][j].iterator();
				while(iter.hasNext()) {
					Entity eInList = iter.next();
					if(eInList.m_type == type) return eInList;
				}
			}
		}
		
		return null;
	}
}
