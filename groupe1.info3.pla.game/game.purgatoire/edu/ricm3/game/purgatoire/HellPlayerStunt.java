package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.util.LinkedList;

import ricm3.interpreter.IDirection;

public class HellPlayerStunt extends Stunt {

	LinkedList<Missile> m_missiles;
	Timer m_missileTimer;

	HellPlayerStunt(Entity entity) {
		super(Singleton.getNewPlayerHellAut(), entity, Color.RED);
		m_missiles = new LinkedList<Missile>();
		m_missileTimer = new Timer(0);
		m_maxHP = Options.HELL_PLAYER_HP_MAX;
		m_DMG = Options.HELL_PLAYER_DMG;
	}

	@Override
	void pop(IDirection d) {
		System.out.println("pop hell");
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz hell");
	}

	@Override
	void hit(IDirection d) {
		if (m_missileTimer.end()) {
			m_missileTimer.start(1000 / 2);
			Missile missile;
			switch (d) {
			case NORTH:
				if (m_entity.m_bounds.y > 0) {
					missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
							m_entity.m_bounds.x + 1, m_entity.m_bounds.y - 1, 1, 1, d, m_entity);
					m_missiles.add(missile);
				}
				break;
			case SOUTH:
				if (m_entity.m_bounds.y != Options.LVL_HEIGHT - m_entity.m_bounds.height) {
					missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
							m_entity.m_bounds.x + 1, m_entity.m_bounds.y + m_entity.m_bounds.height, 1, 1, d, m_entity);
					m_missiles.add(missile);
				}
				break;
			case EAST:
				if (m_entity.m_bounds.x != Options.LVL_WIDTH - m_entity.m_bounds.width) {

					missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
							m_entity.m_bounds.x + m_entity.m_bounds.width, m_entity.m_bounds.y + 1, 1, 1, d, m_entity);
					m_missiles.add(missile);
				}
				break;
			case WEST:
				if (m_entity.m_bounds.x != 0) {
					missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
							m_entity.m_bounds.x - 1, m_entity.m_bounds.y + 1, 1, 1, d, m_entity);
					m_missiles.add(missile);
				}
				break;
			}
		}
	}

	@Override
	void egg() {
		System.out.println("egg hell");
	}

	@Override
	void getDamage(int DMG) {
		System.out.println("getDamage hell");
	}

	@Override
	void goingOut(IDirection d) {
		if (d == IDirection.NORTH) {
			m_entity.m_level.m_model.nextLevel();
		}
	}

	@Override
	public void step(long now) {
		super.step(now);
		if (m_missileTimer.m_previousNow == 0) {
			m_missileTimer.m_previousNow = now;
		}
		m_missileTimer.step(now);
	}
}
