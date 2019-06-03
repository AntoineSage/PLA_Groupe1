package edu.ricm3.game.purgatoire;

import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntity;
import ricm3.interpreter.IKeyEnum;

public class Entity {

	public int m_x, m_y, m_w, m_h;
	public Direction m_direction;
	public IAutomaton m_automaton;

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

	public Entity(int x, int y, int w, int h, Direction direction, IAutomaton automaton) {
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

	public void move(IDirection direction) {
		switch (direction) {
		case NORTH:
			m_y--;
			break;
		case SOUTH:
			m_y++;
			break;
		case EAST:
			m_x++;
			break;
		case WEST:
			m_x--;
			break;
		default:
			break;
		}
	}

	public void setAutomaton(IAutomaton aut) {
		m_automaton = aut;
	}

	public boolean gotPower() {
		return false;
	}

	public boolean gotStuff() {
		return false;
	}

	public boolean directionIs(IDirection direction) {
		return false;
	}

	public boolean cellAtIs(IDirection direction, IEntity entity) {
		return false;
	}

	public boolean closestEntityAt(IEntity entity, IDirection direction) {
		return false;
	}

	public boolean isKeyPressed(IKeyEnum key) {
		return false;
	}

}
