package edu.ricm3.game.purgatoire.stunts;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.Iterator;

import edu.ricm3.game.purgatoire.Animation;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.Timer;
import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.entities.Entity;
import edu.ricm3.game.purgatoire.entities.Player;
import edu.ricm3.game.purgatoire.entities.Special;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HeavenPlayerStunt extends Stunt implements PlayerStunt {

	Timer m_dashTimer;

	public HeavenPlayerStunt() {
		super(Singleton.getNewPlayerHeavenAut(), null, Color.BLUE);
		
		try {
			m_animation = new AnimationPlayer(new Animation("animations/proto.ani"),
					AnimType.IDLE, 3);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_animation.resume();
		
		m_dashTimer = new Timer(m_cooldownDash);
		m_maxHP = Options.HEAVEN_PLAYER_HP_MAX;
		setDMG(Options.HEAVEN_PLAYER_DMG);
	}

	@Override
	public void pop(IDirection d) {
		if (m_dashTimer.end()) {
			dash(m_entity.m_direction);
			System.out.println("dash");
			m_dashTimer.start(m_cooldownDash * 1000);
		}
		System.out.println("pop heaven player");
	}

	@Override
	public void wizz(IDirection d) {
		Special special = (Special) m_entity.superposedWith(IEntityType.TEAM);
		if (special != null) {
			special.pop(null);
		}
		System.out.println("wizz heaven player");
	}

	@Override
	public void hit(IDirection d) {
		int y, yMin, yMax, x, xMin, xMax;
		switch (d) {
		case NORTH:
			y = m_entity.m_bounds.y;
			yMin = y - 2 * m_entity.m_bounds.height;
			while (y > 1 && y > yMin) {
				for (x = m_entity.m_bounds.x; x < m_entity.m_bounds.x + m_entity.m_bounds.width; x++) {
					Iterator<Entity> iter = m_entity.m_level.m_collisionGrid.get(x, y - 1).iterator();
					while (iter.hasNext()) {
						Entity e = iter.next();
						e.m_currentStunt.takeDamage(getDMG());
					}
				}
				y--;
			}
			m_entity.m_direction = IDirection.NORTH;
			break;
		case SOUTH:
			y = m_entity.m_bounds.y + 3;
			yMax = y + 2 * m_entity.m_bounds.height;
			while (y < Options.LVL_HEIGHT && y < yMax) {
				for (x = m_entity.m_bounds.x; x < m_entity.m_bounds.x + m_entity.m_bounds.width; x++) {
					Iterator<Entity> iter = m_entity.m_level.m_collisionGrid.get(x, y).iterator();
					while (iter.hasNext()) {
						Entity e = iter.next();
						e.m_currentStunt.takeDamage(getDMG());
					}
				}
				y++;
			}
			m_entity.m_direction = IDirection.SOUTH;
			break;
		case EAST:
			x = m_entity.m_bounds.x + m_entity.m_bounds.width;
			xMax = x + 2 * m_entity.m_bounds.width;
			while (x < Options.LVL_WIDTH && x < xMax) {
				for (y = m_entity.m_bounds.y; y < m_entity.m_bounds.y + m_entity.m_bounds.height; y++) {
					Iterator<Entity> iter = m_entity.m_level.m_collisionGrid.get(x, y).iterator();
					while (iter.hasNext()) {
						Entity e = iter.next();
						e.m_currentStunt.takeDamage(getDMG());
					}
				}
				x++;
			}
			m_entity.m_direction = IDirection.EAST;
			break;
		case WEST:
			x = m_entity.m_bounds.x;
			xMin = x - 2 * m_entity.m_bounds.width;
			while (x > 1 && x > xMin) {
				for (y = m_entity.m_bounds.y; y < m_entity.m_bounds.y + m_entity.m_bounds.height; y++) {
					Iterator<Entity> iter = m_entity.m_level.m_collisionGrid.get(x - 1, y).iterator();
					while (iter.hasNext()) {
						Entity e = iter.next();
						e.m_currentStunt.takeDamage(getDMG());
					}
				}
				x--;
			}
			m_entity.m_direction = IDirection.WEST;
			break;
		default:
			break;
		}
	}

	@Override
	public void egg() {
		System.out.println("egg heaven player");
	}

	@Override
	void goingOut(IDirection d) {
		if (d == IDirection.NORTH) {
			m_entity.m_level.m_model.nextLevel();
		}
	}

	public String getRankName() {
		return Options.PLAYER_RANKS_HEAVEN[((Player) m_entity).getRank()];
	}

	@Override
	public void step(long now) {
		super.step(now);
		if (m_dashTimer.m_previousNow == 0)
			m_dashTimer.m_previousNow = now;
		m_dashTimer.step(now);
	}

}
