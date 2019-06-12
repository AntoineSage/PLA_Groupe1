package edu.ricm3.game.purgatoire.stunts;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import ricm3.interpreter.IDirection;

public class HeavenMissileStunt extends Stunt {

//	HeavenMissileStunt(IAutomaton automaton, Color c) {
//		super(automaton, c);
//	}

	HeavenMissileStunt() {
		super(Singleton.getNewMissileHeavenAut(),
				new AnimationPlayer(Singleton.getMissileHeavenAnim(), AnimType.IDLE, 2), Options.HEAVEN_MISSILE_HP_MAX,
				Options.HEAVEN_MISSILE_DMG);
	}
	

	@Override
	public void pop(IDirection d) {
		m_entity.die();
	}

	void goingOut(IDirection d) {
		m_entity.die();
	}

	@Override
	public void step(long now) {
		super.step(now);
	}

}
