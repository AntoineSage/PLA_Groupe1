package edu.ricm3.game.purgatoire.stunts;

import java.util.Random;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.Timer;
import edu.ricm3.game.purgatoire.entities.Entity;
import edu.ricm3.game.purgatoire.entities.Missile;
import edu.ricm3.game.purgatoire.entities.Obstacle;
import edu.ricm3.game.purgatoire.entities.Player;
import edu.ricm3.game.purgatoire.entities.Soul;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HeavenNestStunt extends Stunt {

	long m_nestSpawnPeriod = Options.NEST_SPAWN_PERIOD;

	public HeavenNestStunt() {
		super(Singleton.getNewNestHeavenAut(), new AnimationPlayer(Singleton.getNestHeavenAnim(), AnimType.IDLE, 2),
				Options.HEAVEN_NEST_HP_MAX, Options.HEAVEN_NEST_DMG, Options.HEAVEN_NEST_KARMA_TOGIVE);

		m_wizzTimer = new Timer(Options.NEST_WIZZ_DURATION);
		m_popTimer = new Timer(Options.NEST_POP_DURATION);
	}

	@Override
	public void wizz(IDirection direction) {
		if (m_wizzTimer.isFinished()) {
			int width = m_entity.m_bounds.width;
			int height = m_entity.m_bounds.height;
			int x = m_entity.m_bounds.x;
			int y = m_entity.m_bounds.y;
			new Obstacle(m_entity.m_level, x, y, width, height);
			m_entity.m_level.removeEntity(m_entity);
			m_wizzTimer.start();
		}
	}

	@Override
	public void pop(IDirection direction) {
		m_nestSpawnPeriod *= Options.NEST_COEF_CHANGE_SPAWN_DELAY;
		if (Options.ECHO_POP_NEST)
			System.out.println("Nest spawn period: " + m_nestSpawnPeriod);
	}

	private void changeSpawnPeriod() {
		if (m_popTimer.isFinished() && m_nestSpawnPeriod > Options.NEST_MIN_SPAWN_PERIOD) {
			pop(IDirection.FRONT);
			m_popTimer.start();
		}
	}

	@Override
	public void takeDamage(Entity e) {
		m_entity.addHP(-(int) (m_weaknessBuff * e.m_currentStunt.getDMG()));
		if (m_entity.m_HP <= 0) {
			if (e instanceof Missile) {
				Player p = (Player) ((Missile) e).getOwner();
				p.addKarma(m_entity);
			}
			m_entity.die();
		}
	}

	@Override
	public void egg() {
		int x, y, width, height, randX, randY;
		for (int i = 0; i < 10; i++) {
			width = m_entity.m_bounds.width;
			height = m_entity.m_bounds.height;
			x = (2 * m_entity.m_bounds.x + (width)) / 2;
			y = (2 * m_entity.m_bounds.y + (height)) / 2;
			Random r = new Random();
			double tmpX = Math.random();
			double tmpY = Math.random();

			if (tmpX >= 0.5)
				randX = x - r.nextInt(width + 2);

			else
				randX = x + r.nextInt(width + 2);

			if (tmpY >= 0.5)
				randY = y - r.nextInt(height + 2);
			else
				randY = y + r.nextInt(height + 2);

			if (m_entity.m_level.m_collisionGrid.isOk(IEntityType.ADVERSARY, randX - 2, randY - 2, 2, 2)) {
				new Soul(m_entity.m_level, randX - 2, randY - 2, 2, 2);
				break;
			}
		}
	}

	@Override
	public void step(long now) {
		super.step(now);
		changeSpawnPeriod();
	}

}
