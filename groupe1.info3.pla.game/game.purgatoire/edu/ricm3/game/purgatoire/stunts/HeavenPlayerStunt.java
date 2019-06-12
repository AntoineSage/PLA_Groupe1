package edu.ricm3.game.purgatoire.stunts;

import java.util.LinkedList;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.Timer;
import edu.ricm3.game.purgatoire.entities.Missile;
import edu.ricm3.game.purgatoire.entities.Player;
import edu.ricm3.game.purgatoire.entities.Special;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HeavenPlayerStunt extends Stunt implements PlayerStunt {

	LinkedList<Missile> m_missiles;
	Timer m_hitTimer;
	Timer m_hitCoolDown;
	boolean m_isFiring;
	Timer m_karmaTimer;
	
	IAutomaton m_automatonMove;

	public HeavenPlayerStunt() {
		super(Singleton.getNewPlayerHeavenAut(), new AnimationPlayer(Singleton.getPlayerHeavenAnim(), AnimType.IDLE, 2),
				Options.PLAYER_HP_MAX_TOTAL_HEAVEN[0], Options.PLAYER_DMG_HEAVEN[0]);

		m_popCooldown = Options.DASH_CD;
		m_popTimer = new Timer(m_popCooldown);
		m_missiles = new LinkedList<Missile>();
		m_hitTimer = new Timer(Options.HEAVEN_HIT_TIMER);
		m_hitCoolDown = new Timer(Options.HEAVEN_HIT_CD);
		m_isFiring = false;

		m_karmaTimer = new Timer(Options.PLAYER_KARMA_TIME_DURATION);
		m_karmaTimer.start();
		m_automatonMove = Singleton.getNewPlayerHeavenMoveAut();
	}

	@Override
	public void pop(IDirection d) {
		if (m_popTimer.isFinished()) {
			dash(m_entity.m_direction);
			m_popTimer.start();
		}
		if (Options.ECHO_POP_PLAYER)
			System.out.println("Player pop heaven");
	}

	@Override
	public void wizz(IDirection d) {
		Special special = (Special) m_entity.superposedWith(IEntityType.TEAM);
		if (special != null) {
			special.pop(null);
		}
		if (Options.ECHO_WIZZ_PLAYER)
			System.out.println("Player wizz heaven");
	}

	@Override
	public void hit(IDirection d) {
		if (m_hitCoolDown.isFinished()) {
			if (m_isFiring == false) {
				m_hitTimer.start();
				m_isFiring = true;
			}
			if (!m_hitTimer.isFinished()) {
				Missile missile;
				switch (d) {
				case NORTH:
					for (int x = 0; x <= 2; x++) {
						missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
								m_entity.m_bounds.x + x, m_entity.m_bounds.y - 1, Options.MISSILE_SIZE,
								IDirection.NORTH, m_entity);
						m_missiles.add(missile);
					}
					break;
				case SOUTH:
					for (int x = 0; x <= 2; x++) {
						missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
								m_entity.m_bounds.x + x, m_entity.m_bounds.y + 3, Options.MISSILE_SIZE,
								IDirection.SOUTH, m_entity);
						m_missiles.add(missile);
					}
					break;
				case EAST:
					for (int y = 0; y <= 2; y++) {
						missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
								m_entity.m_bounds.x + 3, m_entity.m_bounds.y + y, Options.MISSILE_SIZE, IDirection.EAST,
								m_entity);
						m_missiles.add(missile);
					}
					break;
				case WEST:
					for (int y = 0; y <= 2; y++) {
						missile = new Missile(m_entity.m_level, new HeavenMissileStunt(), new HellMissileStunt(),
								m_entity.m_bounds.x - 1, m_entity.m_bounds.y + y, Options.MISSILE_SIZE, IDirection.WEST,
								m_entity);
						m_missiles.add(missile);
					}
					break;
				default:
					break;
				}
			} else {
				m_hitCoolDown.start();
				m_isFiring = false;
			}
		}
	}

	@Override
	public void egg() {
		System.out.println("egg heaven player");
	}

	@Override
	void goingOut(IDirection d) {
		if (d == IDirection.NORTH) {
			m_entity.m_level.m_model.nextLevel();
		}
	}

	public String getRankName() {
		return Options.PLAYER_RANKS_HEAVEN[((Player) m_entity).getRank()];
	}

	@Override
	public void step(long now) {
		super.step(now);
		m_hitTimer.step(now);
		m_hitCoolDown.step(now);
		m_karmaTimer.step(now);
		changeKarmaOverTime();
		m_automatonMove.step(m_entity);
	}

	@Override
	public void changeKarmaOverTime() {
		if (m_karmaTimer.isFinished()) {
			((Player) m_entity).addKarma(-Options.PLAYER_KARMA_TIME_AMOUNT);
			m_karmaTimer.start();
		}
	}

	@Override
	public void updateRankStats() {
		((Player) m_entity).setMaxTotalHP(Options.PLAYER_HP_MAX_TOTAL_HEAVEN[((Player) m_entity).getRank()]);
		setDMG(Options.PLAYER_DMG_HEAVEN[((Player) m_entity).getRank()]);
//		m_hitCoolDown.setDuration(Options.HIT_TIMER_HEAVEN[((Player) m_entity).getRank()]);
	}

}
