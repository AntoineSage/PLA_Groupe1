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

public class HellNestStunt extends Stunt {

	private long m_nestSpawnPeriod = Options.NEST_SPAWN_PERIOD;

	public HellNestStunt() {
		super(Singleton.getNewNestHeavenAut(), new AnimationPlayer(Singleton.getNestHellAnim(), AnimType.IDLE, 2),
				Options.HELL_NEST_HP_MAX, Options.HELL_NEST_DMG, Options.HELL_NEST_KARMA_TOGIVE);

		m_wizzTimer = new Timer(Options.NEST_WIZZ_DURATION);
		m_popTimer = new Timer(Options.NEST_POP_DURATION);
	}

	@Override
	public void wizz(IDirection direction) {
		if (m_wizzTimer.isFinished()) {
			int width = m_entity.m_bounds.width;
			int x = m_entity.m_bounds.x;
			int y = m_entity.m_bounds.y;
			new Obstacle(m_entity.m_level, x, y, width);
			m_entity.m_level.removeEntity(m_entity);
			m_wizzTimer.start();
		}
		if (Options.ECHO_WIZZ_NEST)
			System.out.println("Wizz hell (obstacle) nest");
	}

	@Override
	public void pop(IDirection direction) {
		m_nestSpawnPeriod *= Options.NEST_COEF_CHANGE_SPAWN_DELAY;
		if (Options.ECHO_POP_NEST)
			System.out.println("Pop hell nest (spawn period: " + m_nestSpawnPeriod + ")");
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

			randX = x + r.nextInt(Options.NEST_EGG_RANGE + 1) - (Options.NEST_EGG_RANGE / 2);
			randY = y + r.nextInt(Options.NEST_EGG_RANGE + 1) - (Options.NEST_EGG_RANGE / 2);

			if (m_entity.m_level.m_collisionGrid.isOk(IEntityType.ADVERSARY, randX - Options.SOUL_SIZE,
					randY - Options.SOUL_SIZE, Options.SOUL_SIZE, Options.SOUL_SIZE)) {
				new Soul(m_entity.m_level, randX - Options.SOUL_SIZE, randY - Options.SOUL_SIZE, Options.SOUL_SIZE, true);
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
