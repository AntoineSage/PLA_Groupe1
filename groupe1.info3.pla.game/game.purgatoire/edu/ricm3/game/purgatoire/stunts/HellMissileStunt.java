package edu.ricm3.game.purgatoire.stunts;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import ricm3.interpreter.IDirection;

public class HellMissileStunt extends Stunt {

//	HellMissileStunt(IAutomaton automaton, Color c) {
//		super(automaton, c);
//		setDMG(Options.HELL_MISSILE_DMG);
//	}

	HellMissileStunt() {
		super(Singleton.getNewMissileHellAut(), new AnimationPlayer(Singleton.getMissileHellAnim(), AnimType.IDLE, 2),
				Options.HELL_MISSILE_HP_MAX, Options.HELL_MISSILE_DMG);
	}

	@Override
	public void pop(IDirection d) {
		m_entity.die();
		if (Options.ECHO_POP_MISSILE)
			System.out.println("Missile pop (die)");
	}

	@Override
	public void wizz(IDirection d) {
		System.out.println("wizz hell soul");
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
