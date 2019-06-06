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

	Stunt(IAutomaton automaton, Color c) {
		m_automaton = automaton;
		m_c = c;
	}

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

	public void tryMove(IDirection d) {
		switch (d) {
		case NORTH:
			if (m_entity.m_bounds.y == 1) {
				m_entity.m_level.m_model.nextLevel();
			} else if (m_entity.wontCollide(d)) {
				move(0, -1);
			}
			break;
		case SOUTH:
			if (m_entity.m_bounds.y < Options.LVL_HEIGHT - m_entity.m_bounds.height) {
				if (m_entity.wontCollide(d)) {
					move(0, 1);
				}
			}
			break;
		case EAST:
			if (m_entity.m_bounds.x < Options.LVL_WIDTH - m_entity.m_bounds.height) {
				if (m_entity.wontCollide(d)) {
					move(1, 0);
				}
			}
			break;
		case WEST:
			if (m_entity.m_bounds.x > 0) {
				if (m_entity.wontCollide(d)) {
					move(-1, 0);
				}
			}
			break;
		default:
			break;
		}
	}

	void pop(IDirection d) {
		System.out.println("pop de base");
	}

	void wizz(IDirection d) {
		System.out.println("wizz de base");
	}

	void hit(IDirection d) {
		System.out.println("hit de base");
	}

	private void move(int x, int y) {
		m_entity.m_level.updateEntity(m_entity, x, y);
		m_entity.m_bounds.translate(x, y);
	}

	void egg() {
		System.out.println("egg de base");
	}

	void getDamage(int DMG) {
		m_entity.addHP(-DMG);
	}

	public void setAttachedEntity(Entity entity) {
		m_entity = entity;
	}
}