package edu.ricm3.game.purgatoire;

import java.util.LinkedList;

public class CollisionGrid {

	LinkedList<Entity> grid[][];

	public CollisionGrid() {
	}

	void addEntity(Entity entity) {
		if (isOk(entity) == true) {
			int x, y, width, height;
			x = entity.m_bounds.x;
			y = entity.m_bounds.y;
			width = entity.m_bounds.width;
			height = entity.m_bounds.height;
			for(int i = 0; i < x+width; i ++)
				for(int j = 0; j< y+height; j++) {
					grid[i][j].add(entity);
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
		for (int i = e.m_bounds.x; i < e.m_bounds.width; i++) {
			for (int j = e.m_bounds.y; j < e.m_bounds.height; j++) {
				if (grid[i][j] != null)
					grid[i][j].remove(e);
			}
		}
	}
}
