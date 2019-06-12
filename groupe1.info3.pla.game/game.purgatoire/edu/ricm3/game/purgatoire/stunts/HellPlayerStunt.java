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
	Timer m_karmaTimer;

	int m_lastPopPeriod = -1;
	int m_nbPeriod;
	int m_DMGBuffRatio = Options.BUFF_DMG;
	int m_weaknessBuffRatio = Options.BUFF_WEAKNESS;
	int m_durationBuff = Options.BUFF_DURATION;

	public HellPlayerStunt() {
		super(Singleton.getNewPlayerHellAut(), new AnimationPlayer(Singleton.getPlayerHellAnim(), AnimType.IDLE, 2),
				Options.HELL_PLAYER_HP_MAX, Options.HELL_PLAYER_DMG);

		m_missiles = new LinkedList<Missile>();
		m_missileTimer = new Timer(500);
		m_wizzTimer = new Timer(1000);
		m_popTimer = new Timer(m_durationBuff);
		m_karmaTimer = new Timer(Options.PLAYER_KARMA_TIME_DURATION);
		m_karmaTimer.start();
	}

	@Override
	public void pop(IDirection d) {
		// Peut-être un peu lourd comme calcul ? A voir si on peut pas juste avoir un
		// compteur de période écoulée ?
		m_nbPeriod = (int) m_entity.m_level.m_model.m_totalTime / Options.TOTAL_PERIOD;
		if (m_nbPeriod != m_lastPopPeriod) {
			m_popTimer.start();
			buff(m_DMGBuffRatio, m_weaknessBuffRatio);
			m_lastPopPeriod = m_nbPeriod;
		}
	}

	@Override
	public void wizz(IDirection d) {
		if (m_wizzTimer.isFinished()) {
			m_wizzTimer.start();
			Missile missile;
			// North Line
			for (int x = 0; x <= 3; x++) {
				missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
						m_entity.m_bounds.x + x, m_entity.m_bounds.y - 1, 1, 1, IDirection.NORTH, m_entity);
				m_missiles.add(missile);
			}
			// South Line
			for (int x = -1; x <= 2; x++) {
				missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
						m_entity.m_bounds.x + x, m_entity.m_bounds.y + 3, 1, 1, IDirection.SOUTH, m_entity);
				m_missiles.add(missile);
			}
			// East Line
			for (int y = 0; y <= 3; y++) {
				missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
						m_entity.m_bounds.x + 3, m_entity.m_bounds.y + y, 1, 1, IDirection.EAST, m_entity);
				m_missiles.add(missile);
			}
			// West Line
			for (int y = -1; y <= 2; y++) {
				missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
						m_entity.m_bounds.x - 1, m_entity.m_bounds.y + y, 1, 1, IDirection.WEST, m_entity);
				m_missiles.add(missile);
			}
		}

	}

	@Override
	public void hit(IDirection d) {
		if (m_missileTimer.isFinished()) {
			m_missileTimer.start();
			Missile missile;
			switch (d) {
			case NORTH:
				missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
						m_entity.m_bounds.x + 1, m_entity.m_bounds.y - 1, 1, 1, d, m_entity);
				m_missiles.add(missile);
				break;
			case SOUTH:
				missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
						m_entity.m_bounds.x + 1, m_entity.m_bounds.y + m_entity.m_bounds.height, 1, 1, d, m_entity);
				m_missiles.add(missile);
				break;
			case EAST:
				missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
						m_entity.m_bounds.x + m_entity.m_bounds.width, m_entity.m_bounds.y + 1, 1, 1, d, m_entity);
				m_missiles.add(missile);
				break;
			case WEST:
				missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
						m_entity.m_bounds.x - 1, m_entity.m_bounds.y + 1, 1, 1, d, m_entity);
				m_missiles.add(missile);
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
		if (m_popTimer.isFinished()) {
			m_DMGBuff = 1;
			m_weaknessBuff = 1;
		}
		m_popTimer.step(now);
		m_missileTimer.step(now);
		m_wizzTimer.step(now);
		m_karmaTimer.step(now);
		changeKarmaOverTime();
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

	@Override
	public void changeKarmaOverTime() {
		if (m_karmaTimer.isFinished()) {
			((Player) m_entity).addKarma(+Options.PLAYER_KARMA_TIME_AMOUNT);
			m_karmaTimer.start();
		}
	}

}
