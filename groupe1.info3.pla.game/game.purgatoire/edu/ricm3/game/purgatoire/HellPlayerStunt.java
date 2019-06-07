package edu.ricm3.game.purgatoire;

import java.awt.Color;

import ricm3.interpreter.IDirection;

public class HellPlayerStunt extends Stunt {

	int m_period;

	Timer m_buffTimer;

	HellPlayerStunt(Entity entity) {
		super(Singleton.getNewPlayerHellAut(), entity, Color.RED);
		m_buffTimer = new Timer(m_durationBuff * 1000);
	}

	@Override
	void pop(IDirection d) {
		if (m_period != m_entity.m_level.m_model.m_period) {
			buff(300, 100);
			m_period = m_entity.m_level.m_model.m_period;
			m_buffTimer.start(m_durationBuff * 1000);
		}
		System.out.println("pop hell");
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz hell");
	}

	@Override
	void hit(IDirection d) {
		System.out.println("hit hell");
	}

	@Override
	void egg() {
		System.out.println("egg hell");
	}

	@Override
	void takeDamage(int DMG) {
		System.out.println("getDamage hell");
	}

	@Override
	public void step(long now) {
		super.step(now);
		if (m_buffTimer.m_previousNow == 0)
			m_buffTimer.m_previousNow = now;
		if (m_buffTimer.end()) {
			m_buffedDMG = 1;
			m_weaknessBuff = 1;
		}
		m_buffTimer.step(now);
		System.out.println(m_entity.getDMG());
	}
}
