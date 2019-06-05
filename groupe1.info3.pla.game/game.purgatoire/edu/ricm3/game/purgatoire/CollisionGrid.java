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
}
