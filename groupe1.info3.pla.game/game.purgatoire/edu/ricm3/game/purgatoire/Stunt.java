package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;

abstract public class Stunt {

	IAutomaton m_automaton;
	Color m_c;
	Rectangle m_bounds;
	BufferedImage m_sprite;

	Stunt(IAutomaton automaton, Color c, Rectangle bounds) {
		m_automaton = automaton;
		m_c = c;
		m_bounds = bounds;
	}

	void hit() {

	}

	void move() {

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
