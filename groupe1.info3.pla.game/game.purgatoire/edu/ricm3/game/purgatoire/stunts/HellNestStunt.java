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
	long m_NestSpawnPeriod = Options.NEST_SPAWN_DELAY;
	Timer m_timerWizz;
	Timer m_timerPop;

	public HellNestStunt() {
		super(Singleton.getNewNestHeavenAut(), new AnimationPlayer(Singleton.getNestHellAnim(), AnimType.IDLE, 2),
				Options.HELL_NEST_HP_MAX, Options.HELL_NEST_DMG, Options.HELL_NEST_KARMA_TOGIVE);
		m_timerWizz = new Timer(3000);
		m_timerPop = new Timer(5000);
	}

	@Override
	public void wizz(IDirection direction) {
		if (m_timerWizz.end()) {
			int width = m_entity.m_bounds.width;
			int height = m_entity.m_bounds.height;
			int x = m_entity.m_bounds.x;
			int y = m_entity.m_bounds.y;
			new Obstacle(m_entity.m_level, x, y, width, height);
			m_entity.m_level.removeEntity(m_entity);
			m_timerWizz.start(3000);
		}
	}

	@Override
	public void pop(IDirection direction) {
		if (m_timerPop.end() && m_NestSpawnPeriod > 500) {
			m_NestSpawnPeriod /= 2;
			m_timerPop.start(5000);
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
		m_timerWizz.step(now);
		m_timerPop.step(now);

	}
}
