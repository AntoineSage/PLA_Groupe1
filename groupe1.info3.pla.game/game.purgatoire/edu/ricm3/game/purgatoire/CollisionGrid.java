package edu.ricm3.game.purgatoire;

import java.util.LinkedList;

public class CollisionGrid {
	
	LinkedList<Entity> grid[][];
	
	public CollisionGrid() {
		
	}
	
	void addEntity(Entity entity) {
		int x,y,width,height;
		x=entity.m_bounds.x;
		y=entity.m_bounds.y;
		width=entity.m_bounds.width;
		height=entity.m_bounds.height;
	}

	public void updateEntity(Entity e, int x, int y) {
		for(int i = e.m_bounds.x; i < e.m_bounds.width; i++) {
			for(int j = e.m_bounds.y; j < e.m_bounds.height; j++) {
				if(grid[i][j] != null)
				grid[i][j].remove(e);
			}
		}
	}
}
