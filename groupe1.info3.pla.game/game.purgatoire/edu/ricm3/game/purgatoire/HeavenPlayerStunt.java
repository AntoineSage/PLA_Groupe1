package edu.ricm3.game.purgatoire;

import java.awt.Color;

import ricm3.interpreter.IDirection;

public class HeavenPlayerStunt extends Stunt {

	Timer m_dashTimer;

	HeavenPlayerStunt(Entity entity) {
		super(Singleton.getNewPlayerHeavenAut(), entity, Color.BLUE);
		m_dashTimer = new Timer(m_cooldownDash);
	}

	@Override
	void pop(IDirection d) {
		if (m_dashTimer.end()) {
			dash(m_entity.m_direction);
			System.out.println("dash");
			m_dashTimer.start(m_cooldownDash * 1000);
		}
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz heaven");
	}

	@Override
	void hit(IDirection d) {
		System.out.println("hit heaven");
	}

	@Override
	void egg() {
		System.out.println("egg heaven");
	}

	@Override
	void getDamage(int DMG) {
		System.out.println("getDamage heaven");
	}

	@Override
	public void step(long now) {
		super.step(now);
		if (m_dashTimer.m_previousNow == 0)
			m_dashTimer.m_previousNow = now;
		m_dashTimer.step(now);
	}
}
