package edu.ricm3.game.purgatoire.stunts;

import java.awt.Color;
import java.awt.image.BufferedImage;

import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.Timer;
import edu.ricm3.game.purgatoire.entities.Entity;
import edu.ricm3.game.purgatoire.entities.Nest;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;

public class HellObstacleStunt extends Stunt {

	Timer m_obstacleDashTimer;

	HellObstacleStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
		m_maxHP = Options.HELL_OBSTACLE_HP_MAX;
		setDMG(Options.HELL_OBSTACLE_DMG);
		m_obstacleDashTimer = new Timer(10000);
		// m_obstacleDashTimer.m_previousNow = 1000;
	}

	public HellObstacleStunt() {
		super(Singleton.getNewObstacleHellAut(), null, Color.pink);
		m_maxHP = Options.HELL_OBSTACLE_HP_MAX;
		setDMG(Options.HELL_OBSTACLE_DMG);
		m_obstacleDashTimer = new Timer(2000);
		m_obstacleDashTimer.m_previousNow = 1000;
	}

	@Override
	public void pop(IDirection d) {
		if (m_obstacleDashTimer.end()) {
			dash(m_entity.m_direction);
			System.out.println("dash obstacle");
			m_obstacleDashTimer.start(m_cooldownDash * 1000);
		}
	}

	@Override
	public void wizz(IDirection d) {
		int x, y, width, height;
		x = m_entity.m_bounds.x;
		y = m_entity.m_bounds.y;
		width = m_entity.m_bounds.width;
		height = m_entity.m_bounds.height;
		m_entity.m_level.removeEntity(m_entity);
		new Nest(m_entity.m_level, x, y, width, height);
	}

	@Override
	public void hit(IDirection d) {
		System.out.println("hit hell obstacle");
	}

	@Override
	public void egg() {
		System.out.println("egg hell obstacle");
	}

	@Override
	public void takeDamage(int DMG) {
		System.out.println("takedamage hell obstacle");
	}

	@Override
	public void step(long now) {
		super.step(now);
		if (m_obstacleDashTimer.m_previousNow == 0)
			m_obstacleDashTimer.m_previousNow = now;
		m_obstacleDashTimer.step(now);
	}

}
