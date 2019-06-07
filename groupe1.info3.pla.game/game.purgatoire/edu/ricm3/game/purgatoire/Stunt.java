package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class Stunt {

	IAutomaton m_automaton;
	Color m_c;
	BufferedImage m_sprite;
	Entity m_entity;
	int m_rangeDash = 10;
	int m_cooldownDash = 5;

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
			System.out.println("HERE");
			m_entity.m_direction = IDirection.NORTH;
			if (m_entity.m_bounds.y <= 1) {
				goingOut(d);
			} else {
				if (nobodyCollideWithEntity()) {
					move(0, -1);
				}
			}
			break;
		case SOUTH:
			m_entity.m_direction = IDirection.SOUTH;
			if (m_entity.m_bounds.y < Options.LVL_HEIGHT - m_entity.m_bounds.height) {
				if (nobodyCollideWithEntity()) {
					move(0, 1);
				}
			} else {
				goingOut(d);
			}
			break;
		case EAST:
			m_entity.m_direction = IDirection.EAST;
			if (m_entity.m_bounds.x < Options.LVL_WIDTH - m_entity.m_bounds.height) {
				if (nobodyCollideWithEntity()) {
					move(1, 0);
				}
			} else {
				goingOut(d);
			}
			break;
		case WEST:
			m_entity.m_direction = IDirection.WEST;
			if (m_entity.m_bounds.x > 0) {
				if (nobodyCollideWithEntity()) {
					move(-1, 0);
				}
			} else {
				goingOut(d);
			}
			break;
		case FRONT:
			switch (m_entity.m_direction) {
			case NORTH:
				tryMove(IDirection.NORTH);
				break;
			case SOUTH:
				tryMove(IDirection.SOUTH);
				break;
			case EAST:
				tryMove(IDirection.EAST);
				break;
			case WEST:
				tryMove(IDirection.WEST);
				break;
			default:
				break;
			}
			break;
		case BACK:
			switch (m_entity.m_direction) {
			case NORTH:
				tryMove(IDirection.NORTH);
				break;
			case SOUTH:
				tryMove(IDirection.SOUTH);
				break;
			case EAST:
				tryMove(IDirection.EAST);
				break;
			case WEST:
				tryMove(IDirection.WEST);
				break;
			default:
				break;
			}
			break;
		case LEFT:
			switch (m_entity.m_direction) {
			case NORTH:
				tryMove(IDirection.NORTH);
				break;
			case SOUTH:
				tryMove(IDirection.SOUTH);
				break;
			case EAST:
				tryMove(IDirection.EAST);
				break;
			case WEST:
				tryMove(IDirection.WEST);
				break;
			default:
				break;
			}
			break;
		case RIGHT:
			switch (m_entity.m_direction) {
			case NORTH:
				tryMove(IDirection.NORTH);
				break;
			case SOUTH:
				tryMove(IDirection.SOUTH);
				break;
			case EAST:
				tryMove(IDirection.EAST);
				break;
			case WEST:
				tryMove(IDirection.WEST);
				break;
			default:
				break;
			}
			break;
		}

	}

	public void step(Entity e) {
		m_automaton.step(m_entity);
	}

	void dash(IDirection d) {
		for (int i = 0; i < m_rangeDash; i++) {
			tryMove(d);
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
		m_entity.m_HP -= DMG;
		if (m_entity.m_HP <= 0) {
			m_entity.die();
		}
	}

	public void setAttachedEntity(Entity entity) {
		m_entity = entity;
	}

	public boolean isEntityAt(IEntityType type, IDirection direction) {
		return m_entity.superposedWith(type) != null;
	}

	public boolean nobodyCollideWithEntity() {
		if (m_entity instanceof Missile) {
			System.out.println("x: " + m_entity.m_bounds.x + "y: " + m_entity.m_bounds.y);
		}
		List<Entity> colliders = m_entity.m_level.m_collisionGrid.testCollisionFutur(m_entity, m_entity.m_direction);
		if (!colliders.isEmpty()) {
			System.out.println("isNotEmpry");
			m_entity.enterInCollisionWith(colliders);
			return false;

		}
		return true;
	}

	void goingOut(IDirection d) {

	}

	public void step(long now) {
		m_automaton.step(m_entity);
	}
}