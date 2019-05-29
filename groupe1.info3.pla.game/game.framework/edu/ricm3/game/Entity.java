package edu.ricm3.game;

public class Entity {

	public int m_x, m_y, m_w, m_h;
	public Direction m_direction;
	
	public enum Direction{
		NORD, SUD, EST, WEST;
		
	}
	
	public Entity() {
		m_x = 0;
		m_y = 0;
		m_h = 1;
		m_w = 1;
		m_direction = Direction.NORD;
	}
	
	public Entity(int x, int y, int w, int h, Direction direction) {
		m_x = x;
		m_y = y;
		m_h = h;
		m_w = w;
		m_direction = direction;
	}
	
	public void move(Direction direction) {
		switch(direction) {
		case NORD: m_y--;
		case SUD: m_y++;
		case EST: m_x++;
		case WEST: m_x--;
		}
	}
	
}
