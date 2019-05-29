package edu.ricm3.game.purgatoire;

import ricm3.interpreter.Aut;

public class Entity {

	public int m_x, m_y, m_w, m_h;
	public Direction m_direction;
	public Aut m_automaton;

	public enum Direction {
		NORD, SUD, EST, WEST;

	}

	public Entity() {
		m_x = 0;
		m_y = 0;
		m_h = 1;
		m_w = 1;
		m_direction = Direction.NORD;
		m_automaton = null;
	}

	public Entity(int x, int y, int w, int h, Direction direction, Aut automaton) {
		m_x = x;
		m_y = y;
		m_h = h;
		m_w = w;
		m_direction = direction;
		m_automaton = automaton;
	}

	public Entity(int x, int y, int w, int h, Direction direction) {
		m_x = x;
		m_y = y;
		m_h = h;
		m_w = w;
		m_direction = direction;
	}

	public void move(Direction direction) {
		switch (direction) {
		case NORD:
			m_y--;
			break;
		case SUD:
			m_y++;
			break;
		case EST:
			m_x++;
			break;
		case WEST:
			m_x--;
			break;
		}
	}

	public void setAutomaton(Aut aut) {
		m_automaton = aut;
	}

}
