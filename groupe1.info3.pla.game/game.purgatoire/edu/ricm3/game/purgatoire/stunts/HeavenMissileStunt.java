package edu.ricm3.game.purgatoire.stunts;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Singleton;
import ricm3.interpreter.IDirection;

public class HeavenMissileStunt extends Stunt {

//	HeavenMissileStunt(IAutomaton automaton, Color c) {
//		super(automaton, c);
//	}

	HeavenMissileStunt() {
		super(Singleton.getNewMissileHeavenAut(), new AnimationPlayer(Singleton.getMissileHeavenAnim(), AnimType.IDLE, 2));
	}

	void goingOut(IDirection d) {
		m_entity.die();
	}

}
