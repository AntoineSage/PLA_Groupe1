package edu.ricm3.game.purgatoire.stunts;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import ricm3.interpreter.IDirection;

public class HellMissileStunt extends Stunt {

	HellMissileStunt() {
		super(Singleton.getNewMissileHellAut(), new AnimationPlayer(Singleton.getMissileHellAnim(), AnimType.IDLE, 2),
				Options.HELL_MISSILE_HP_MAX, Options.HELL_MISSILE_DMG);
	}

	@Override
	public void pop(IDirection d) {
		m_entity.die();
		if (Options.ECHO_POP_MISSILE)
			System.out.println("Pop heaven (die) missile");
	}

	@Override
	public void wizz(IDirection d) {
		switch (m_entity.m_direction) {
		case NORTH:
			m_entity.m_direction = IDirection.SOUTH;
			break;
		case SOUTH:
			m_entity.m_direction = IDirection.NORTH;
			break;
		case WEST:
			m_entity.m_direction = IDirection.EAST;
			break;
		case EAST:
			m_entity.m_direction = IDirection.WEST;
			break;
		default:
			break;
		}
		if (Options.ECHO_WIZZ_MISSILE)
			System.out.println("Wizz hell (turn) missile");
	}

	@Override
	public void hit(IDirection d) {
		System.out.println("hit hell soul");
	}

	@Override
	public void egg() {
		System.out.println("egg hell soul");
	}

	void goingOut(IDirection d) {
		m_entity.die();
	}

	@Override
	public void step(long now) {
		super.step(now);
	}

}
