package edu.ricm3.game.purgatoire.stunts;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.Timer;
import edu.ricm3.game.purgatoire.entities.Entity;
import edu.ricm3.game.purgatoire.entities.Nest;
import ricm3.interpreter.IDirection;

public class HellObstacleStunt extends Stunt {

	public HellObstacleStunt() {
		super(Singleton.getNewObstacleHellAut(), new AnimationPlayer(Singleton.getObstacleHellAnim(), AnimType.IDLE, 2),
				Options.HELL_OBSTACLE_HP_MAX, Options.HELL_OBSTACLE_DMG);

		m_popCooldown = Options.DASH_CD;
		m_popTimer = new Timer(m_popCooldown);
	}

	@Override
	public void pop(IDirection d) {
		if (m_popTimer.isFinished()) {
			dash(m_entity.m_direction);
			m_popTimer.start();
		}
		if (Options.ECHO_POP_OBSTACLE)
			System.out.println("Pop hell (dash) obstacle");
	}

	@Override
	public void wizz(IDirection d) {
		int x, y, width;
		x = m_entity.m_bounds.x;
		y = m_entity.m_bounds.y;
		width = m_entity.m_bounds.width;
		m_entity.m_level.removeEntity(m_entity);
		new Nest(m_entity.m_level, x, y, width);
		if (Options.ECHO_WIZZ_OBSTACLE)
			System.out.println("Wizz hell (nest) obstacle");
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
	}

	@Override
	public void takeDamage(Entity e) {
	}

	@Override
	public void step(long now) {
		super.step(now);
	}

}
