package edu.ricm3.game.purgatoire.stunts;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.Timer;
import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.entities.Entity;
import edu.ricm3.game.purgatoire.entities.Missile;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class Stunt {

	public AnimationPlayer m_animation;
	public Color m_c;
	public BufferedImage m_sprite;

	IAutomaton m_automaton;
	Entity m_entity;

	int m_rangeDash = Options.DASH_SIZE;

	Timer m_popTimer;
	int m_popCooldown;
	int m_popDuration; // not used

	Timer m_wizzTimer; // not used
	int m_wizzCooldown; // not used
	int m_wizzDuration;

	public int m_maxHP;
	private int m_DMG;
	public int m_karmaToGive;
	float m_DMGBuff = 1;
	public float m_weaknessBuff = 1;

//	Stunt(IAutomaton automaton, Color c) {
//		m_automaton = automaton;
//		m_c = c;
//	}
//
//	Stunt(IAutomaton automaton, Entity entity, Color c) {
//		m_automaton = automaton;
//		m_entity = entity;
//		m_c = c;
//	}
//
//	public Stunt(IAutomaton automaton, AnimationPlayer animation) {
//		m_automaton = automaton;
//		m_animation = animation;
//		m_popTimer = new Timer(Options.DEFAULT_CD);
//		m_wizzTimer = new Timer(Options.DEFAULT_CD);
//	}

	public Stunt(IAutomaton automaton, AnimationPlayer animation, int maxHP, int DMG) {
		m_automaton = automaton;
		m_animation = animation;
		m_maxHP = maxHP;
		m_DMG = DMG;
		m_popTimer = new Timer(Options.DEFAULT_CD);
		m_wizzTimer = new Timer(Options.DEFAULT_CD);
	}

	public Stunt(IAutomaton automaton, AnimationPlayer animation, int maxHP, int DMG, int karmaToGive) {
		m_automaton = automaton;
		m_animation = animation;
		m_maxHP = maxHP;
		m_DMG = DMG;
		m_karmaToGive = karmaToGive;
		m_popTimer = new Timer(Options.DEFAULT_CD);
		m_wizzTimer = new Timer(Options.DEFAULT_CD);
	}

//	public Stunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
//		m_automaton = automaton;
//		m_entity = entity;
//		m_sprite = sprite;
//	}

	public void tryMove(IDirection d) {
		switch (d) {
		case NORTH:
			m_entity.m_direction = IDirection.NORTH;
			if (m_animation != null)
				m_animation.changeTo(AnimType.NORTH);
			if (m_entity.m_bounds.y <= 0) {
				goingOut(d);
			} else {
				if (nobodyCollideWithEntity()) {
					move(0, -1);
				}
			}
			break;
		case SOUTH:
			m_entity.m_direction = IDirection.SOUTH;
			if (m_animation != null)
				m_animation.changeTo(AnimType.SOUTH);
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
			if (m_animation != null)
				m_animation.changeTo(AnimType.EAST);
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
			if (m_animation != null)
				m_animation.changeTo(AnimType.WEST);
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

	public void takeDamage(Entity e) {
		m_entity.addHP(-(int) (m_weaknessBuff * e.m_currentStunt.m_DMG));
		if (m_entity.m_HP <= 0) {
			m_entity.die();
		}
	}

	public int getDMG() {
		return (int) ((float) (m_DMGBuff * m_DMG));
	}

	public int getBaseDMG() {
		return m_DMG;
	}

	void setDMG(int DMG) {
		m_DMG = DMG;
	}

	public void setAttachedEntity(Entity entity) {
		m_entity = entity;
	}

	public boolean isEntityAt(IEntityType type, IDirection direction) {
		Iterator<Entity> iter;
		switch (direction) {
		case NORTH:
			iter = m_entity.m_level.m_collisionGrid
					.get(m_entity.m_bounds.x, m_entity.m_bounds.y - m_entity.m_bounds.height).iterator();
			while (iter.hasNext()) {
				Entity e = iter.next();
				if (e.m_type == type)
					return true;
				return false;
			}
			break;
		case SOUTH:
			iter = m_entity.m_level.m_collisionGrid
					.get(m_entity.m_bounds.x, m_entity.m_bounds.y + m_entity.m_bounds.height).iterator();
			while (iter.hasNext()) {
				Entity e = iter.next();
				if (e.m_type == type)
					return true;
				return false;
			}
			break;

		case WEST:
			iter = m_entity.m_level.m_collisionGrid
					.get(m_entity.m_bounds.x - m_entity.m_bounds.width, m_entity.m_bounds.y).iterator();
			while (iter.hasNext()) {
				Entity e = iter.next();
				if (e.m_type == type)
					return true;
				return false;
			}
			break;
		case EAST:
			iter = m_entity.m_level.m_collisionGrid
					.get(m_entity.m_bounds.x + m_entity.m_bounds.width, m_entity.m_bounds.y).iterator();
			while (iter.hasNext()) {
				Entity e = iter.next();
				if (e.m_type == type)
					return true;
				return false;
			}
			break;
		default:
			throw new IllegalStateException();
		}
		return false;
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

	public long getTimeLeftPop() {
		return m_popTimer.getCurrent();
	}

	public long getTimeLeftWizz() {
		return m_wizzTimer.getCurrent();
	}

}