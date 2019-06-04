package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;

public class Stunt {

	IAutomaton m_automaton;
	Color m_c;
	Rectangle m_bounds;
	BufferedImage m_sprite;
	
	public Stunt() {}
	
	Stunt(IAutomaton automaton, Color c, Rectangle bounds ) {
		m_automaton = automaton;
		m_c = c;
		m_bounds = bounds;
	}

	void hit() {

	}

	void move(IDirection d) {
		switch (d) {
		case EAST:
			System.out.println("EAST");
			break;
		case NORTH:
			System.out.println("NORTH");
			break;
		case SOUTH:
			System.out.println("SOUTH");
			break;
		case WEST:
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

	void egg() {
		
	}
	void getDamage() {

	}
}
