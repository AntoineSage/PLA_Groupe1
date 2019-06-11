package edu.ricm3.game.purgatoire.stunts;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.Timer;
import edu.ricm3.game.purgatoire.entities.Nest;
import ricm3.interpreter.IDirection;

public class HeavenObstacleStunt extends Stunt {

//	HeavenObstacleStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
//		super(automaton, entity, sprite);
//		m_maxHP = Options.HEAVEN_OBSTACLE_HP_MAX;
//		setDMG(Options.HEAVEN_OBSTACLE_DMG);
//		m_obstacleDashTimer = new Timer(10000);
//	}

	public HeavenObstacleStunt() {
		super(Singleton.getNewObstacleHeavenAut(),
				new AnimationPlayer(Singleton.getObstacleHeavenAnim(), AnimType.IDLE, 2));
		m_maxHP = Options.HELL_OBSTACLE_HP_MAX;
		setDMG(Options.HEAVEN_OBSTACLE_DMG);
		m_popTimer = new Timer(m_popCooldown);
		m_popCooldown = Options.DASH_CD;
	}

	@Override
	public void pop(IDirection d) {
		if (m_popTimer.isFinished()) {
			dash(m_entity.m_direction);
			System.out.println("dash obstacle");
			m_popTimer.start();
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
		Nest nest = new Nest(m_entity.m_level, x, y, width, height);

		System.out.println("wizz heaven obstacle");
	}

	@Override
	public void hit(IDirection d) {
		System.out.println("hit heaven");
	}

	@Override
	public void egg() {
		System.out.println("egg heaven");
	}

	@Override
	public void step(long now) {
		super.step(now);
		m_popTimer.step(now);
	}

	@Override
	public void takeDamage(int DMG) {
		System.out.println("takeDamage heaven obstacle");
	}
}
