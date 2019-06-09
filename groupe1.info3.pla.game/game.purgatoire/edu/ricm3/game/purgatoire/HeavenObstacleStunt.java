package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;

public class HeavenObstacleStunt extends Stunt {

	Timer m_obstacleDashTimer;
	
	HeavenObstacleStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
		m_maxHP = Options.HEAVEN_OBSTACLE_HP_MAX;
		m_DMG = Options.HEAVEN_OBSTACLE_DMG;
		m_obstacleDashTimer = new Timer(10000);
	}

	HeavenObstacleStunt() {
		super(Singleton.getNewObstacleHeavenAut(), null, Color.red);
		m_maxHP = Options.HELL_OBSTACLE_HP_MAX;
		m_DMG = Options.HELL_OBSTACLE_DMG;
		m_obstacleDashTimer = new Timer(2000);
		m_obstacleDashTimer.m_previousNow = 1000;
	}
	
	@Override
	void pop(IDirection d) {
		if (m_obstacleDashTimer.end()) {
			dash(m_entity.m_direction);
			System.out.println("dash obstacle");
			m_obstacleDashTimer.start(m_cooldownDash * 1000);
		}
	}

	@Override
	void wizz(IDirection d) {
		int x, y, width, height;
		x = m_entity.m_bounds.x;
		y = m_entity.m_bounds.y;
		width = m_entity.m_bounds.width;
		height = m_entity.m_bounds.height;
		m_entity.m_level.removeEntity(m_entity);
		Nest nest = new Nest(m_entity.m_level, x, y, width, height);
		
		System.out.println("wizz heaven obstacle");
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
		if (m_obstacleDashTimer.m_previousNow == 0)
			m_obstacleDashTimer.m_previousNow = now;
		m_obstacleDashTimer.step(now);
	}

}
