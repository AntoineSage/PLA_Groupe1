package edu.ricm3.game.purgatoire.stunts;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.entities.Entity;
import edu.ricm3.game.purgatoire.entities.Missile;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class Stunt {

	public AnimationPlayer m_animation;
	IAutomaton m_automaton;
	public Color m_c;
	BufferedImage m_sprite;
	Entity m_entity;
	int m_rangeDash = Options.DASH_SIZE;
	int m_cooldownDash = Options.DASH_CD;
	int m_durationBuff = Options.BUFF_DURATION;
	public int m_maxHP;
	private int m_DMG;
	public int m_karmaToGive;
	float m_DMGBuff = 1;
	public float m_weaknessBuff = 1;

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
		default:
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

	void buff(int buffDMG, int debuffWeakness) {
		m_DMGBuff = (float) (1 + buffDMG / 100.0);
		m_weaknessBuff = (float) (1 + debuffWeakness / 100.0);
	}

	public void pop(IDirection d) {
		System.out.println("pop de base");
	}

	public void wizz(IDirection d) {
		System.out.println("wizz de base");
	}

	public void hit(IDirection d) {
		System.out.println("hit de base");
	}

	private void move(int x, int y) {
		m_entity.m_level.updateEntity(m_entity, x, y);
		m_entity.m_bounds.translate(x, y);
		m_entity.m_level.m_model.m_totalDistance -= y;
		Singleton.getController().updateDistanceUI();
	}

	public void egg() {
		// TODO egg de base
		System.out.println("egg de base : NYI");
	}

	public void takeDamage(int DMG) {
		m_entity.addHP(-(int) (m_weaknessBuff * DMG));
		if (m_entity.m_HP <= 0) {
			m_entity.die();
		}
	}

	public int getDMG() {
		return (int) ((float) (m_DMGBuff * m_DMG));
	}

	void setDMG(int DMG) {
		m_DMG = DMG;
	}

	public void setAttachedEntity(Entity entity) {
		m_entity = entity;
	}

	public boolean isEntityAt(IEntityType type, IDirection direction) {
		return m_entity.superposedWith(type) != null;
	}

	public boolean nobodyCollideWithEntity() {
		if (m_entity instanceof Missile) {
		}
		List<Entity> colliders = m_entity.m_level.m_collisionGrid.testCollisionFutur(m_entity, m_entity.m_direction);
		if (!colliders.isEmpty()) {
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

	public void setKarmaToGive(int karmaToGive) {
		m_karmaToGive = karmaToGive;
	}

}