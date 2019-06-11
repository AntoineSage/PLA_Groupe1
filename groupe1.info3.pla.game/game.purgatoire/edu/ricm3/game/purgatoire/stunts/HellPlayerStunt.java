package edu.ricm3.game.purgatoire.stunts;

import java.util.LinkedList;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.Timer;
import edu.ricm3.game.purgatoire.entities.Missile;
import edu.ricm3.game.purgatoire.entities.Player;
import ricm3.interpreter.IDirection;

public class HellPlayerStunt extends Stunt implements PlayerStunt {

	LinkedList<Missile> m_missiles;
	Timer m_missileTimer;
	Timer m_buffTimer;

	int m_lastPopPeriod = -1;
	int m_nbPeriod;
	int m_DMGBuffRatio = Options.BUFF_DMG;
	int m_weaknessBuffRatio = Options.BUFF_WEAKNESS;
	int m_durationBuff = Options.BUFF_DURATION;

	public HellPlayerStunt() {
		super(Singleton.getNewPlayerHellAut(), new AnimationPlayer(Singleton.getPlayerHellAnim(), AnimType.IDLE, 2),
				Options.HELL_PLAYER_HP_MAX, Options.HELL_PLAYER_DMG);

		m_missiles = new LinkedList<Missile>();
		m_missileTimer = new Timer(1000);
		m_wizzTimer = new Timer(1000);
		m_buffTimer = new Timer(m_durationBuff);
	}

	@Override
	public void pop(IDirection d) {
		// Peut-être un peu lourd comme calcul ? A voir si on peut pas juste avoir un
		// compteur de période écoulée ?
		m_nbPeriod = (int) m_entity.m_level.m_model.m_totalTime / Options.TOTAL_PERIOD;
		if (m_nbPeriod != m_lastPopPeriod && m_popTimer.isFinished()) {
			buff(m_DMGBuffRatio, m_weaknessBuffRatio);
			m_lastPopPeriod = m_nbPeriod;
		}
		System.out.println("pop hell");
	}

	@Override
	public void wizz(IDirection d) {
		if (m_wizzTimer.isFinished()) {
			m_wizzTimer.start();
			Missile missile;
			// North Line
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x, m_entity.m_bounds.y - 1, 1, 1, IDirection.NORTH, m_entity);
			m_missiles.add(missile);
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x + 1, m_entity.m_bounds.y - 1, 1, 1, IDirection.NORTH, m_entity);
			m_missiles.add(missile);
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x + 2, m_entity.m_bounds.y - 1, 1, 1, IDirection.NORTH, m_entity);
			m_missiles.add(missile);
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x + 3, m_entity.m_bounds.y - 1, 1, 1, IDirection.NORTH, m_entity);
			m_missiles.add(missile);
			// South Line
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x - 1, m_entity.m_bounds.y + 3, 1, 1, IDirection.SOUTH, m_entity);
			m_missiles.add(missile);
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x, m_entity.m_bounds.y + 3, 1, 1, IDirection.SOUTH, m_entity);
			m_missiles.add(missile);
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x + 1, m_entity.m_bounds.y + 3, 1, 1, IDirection.SOUTH, m_entity);
			m_missiles.add(missile);
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x + 2, m_entity.m_bounds.y + 3, 1, 1, IDirection.SOUTH, m_entity);
			m_missiles.add(missile);
			// East Line
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x + 3, m_entity.m_bounds.y, 1, 1, IDirection.EAST, m_entity);
			m_missiles.add(missile);
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x + 3, m_entity.m_bounds.y + 1, 1, 1, IDirection.EAST, m_entity);
			m_missiles.add(missile);
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x + 3, m_entity.m_bounds.y + 2, 1, 1, IDirection.EAST, m_entity);
			m_missiles.add(missile);
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x + 3, m_entity.m_bounds.y + 3, 1, 1, IDirection.SOUTH, m_entity);
			m_missiles.add(missile);
			// West Line
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x - 1, m_entity.m_bounds.y - 1, 1, 1, IDirection.WEST, m_entity);
			m_missiles.add(missile);
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x - 1, m_entity.m_bounds.y, 1, 1, IDirection.WEST, m_entity);
			m_missiles.add(missile);
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x - 1, m_entity.m_bounds.y + 1, 1, 1, IDirection.WEST, m_entity);
			m_missiles.add(missile);
			missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
					m_entity.m_bounds.x - 1, m_entity.m_bounds.y + 2, 1, 1, IDirection.WEST, m_entity);
			m_missiles.add(missile);
		}

	}

	@Override
	public void hit(IDirection d) {
		if (m_missileTimer.isFinished()) {
			m_missileTimer.start();
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
			default:
				break;
			}
		}
	}

	@Override
	public void egg() {
		System.out.println("egg hell");
	}

	@Override
	public void step(long now) {
		super.step(now);
		if (m_buffTimer.isFinished()) {
			m_DMGBuff = 1;
			m_weaknessBuff = 1;
		}
		m_popTimer.step(now);
		m_missileTimer.step(now);
		m_wizzTimer.step(now);
	}

	@Override
	void goingOut(IDirection d) {
		if (d == IDirection.NORTH) {
			m_entity.m_level.m_model.nextLevel();
		}
	}

	public String getRankName() {
		return Options.PLAYER_RANKS_HELL[((Player) m_entity).getRank()];
	}

}
