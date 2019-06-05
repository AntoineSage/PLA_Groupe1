package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;

public class Stunt {

	IAutomaton m_automaton;
	Color m_c;
	BufferedImage m_sprite;
	Entity m_entity;

	Stunt(IAutomaton automaton, Entity entity, Color c) {
		m_automaton = automaton;
		m_entity = entity;
		m_c = c;
	}

	Stunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		m_automaton = automaton;
		m_entity = entity;
		m_sprite = sprite;
	}

	void move(IDirection d) {
		switch (d) {
		case EAST:
			m_entity.m_bounds.x++;
			System.out.println("EAST");
			break;
		case NORTH:
			m_entity.m_bounds.y--;
			System.out.println("NORTH");
			break;
		case SOUTH:
			m_entity.m_bounds.y++;
			System.out.println("SOUTH");
			break;
		case WEST:
			m_entity.m_bounds.x--;
			System.out.println("WEST");
			break;
		default:
			break;
		}
	}

	void pop() {
	}

	void wizz() {
	}

	void move() {
	}

	void hit() {
	}

	void egg() {
	}

	void getDamage() {
	}

	public void setAttachedEntity(Entity entity) {
		m_entity = entity;
	}
}
