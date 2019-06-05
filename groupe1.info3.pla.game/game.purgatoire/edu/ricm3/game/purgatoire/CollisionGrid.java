package edu.ricm3.game.purgatoire;

import java.util.Iterator;
import java.util.LinkedList;

import ricm3.interpreter.IDirection;

public class CollisionGrid {
	
	LinkedList<Entity> grid[][];
	
	public CollisionGrid() {
		for(int i = 0; i < Options.LVL_WIDTH; i++) {
			for(int j = 0; j < Options.LVL_HEIGHT; j++) {
				grid[i][j] = new LinkedList<Entity>();
			}
		}
	}
	
	void addEntity(Entity entity) {
		int x,y,width,height;
		x=entity.m_bounds.x;
		y=entity.m_bounds.y;
		width=entity.m_bounds.width;
		height=entity.m_bounds.height;
	}

	public void updateEntity(Entity e, int x, int y) {
		for(int i = e.m_bounds.x; i < e.m_bounds.x + e.m_bounds.width; i++) {
			for(int j = e.m_bounds.y; j < e.m_bounds.y + e.m_bounds.height; j++) {
				grid[i][j].remove(e);
			}
		}
		
		for(int i = e.m_bounds.x + x; i < e.m_bounds.x + x + e.m_bounds.width; i++) {
			for(int j = e.m_bounds.y; j <  e.m_bounds.y + y + e.m_bounds.height; j++) {
				if(grid[i][j] == null) {
					grid[i][j] = new LinkedList<Entity>();
				}
				grid[i][j].add(e);					
			}
		}
	}

	public boolean wontCollide(Entity entity, IDirection d) {
		switch(d) {
		case EAST:
			for(int i = entity.m_bounds.y; i < entity.m_bounds.y + entity.m_bounds.height; i++) {
				Iterator<Entity> iter = grid[entity.m_bounds.x + entity.m_bounds.width][i].iterator();
				while(iter.hasNext()) {
					Entity e = iter.next();
					if(entity.m_type.isCollidingWith(e.m_type)) {
						return false;
					}
				}
			}			
			break;
		case NORTH:
			break;
		case SOUTH:
			break;
		case WEST:
			break;
		default:
			break;
		
		}
		return true;
	}
}
