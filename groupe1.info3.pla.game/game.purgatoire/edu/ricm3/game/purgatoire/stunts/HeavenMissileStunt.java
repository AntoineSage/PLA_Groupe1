package edu.ricm3.game.purgatoire.stunts;

import java.awt.Color;

import edu.ricm3.game.purgatoire.Singleton;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;

public class HeavenMissileStunt extends Stunt {

	HeavenMissileStunt(IAutomaton automaton, Color c) {
		super(automaton, c);
		// TODO Auto-generated constructor stub
	}
	
	HeavenMissileStunt(){
		super(Singleton.getNewMissileHeavenAut(), null, Color.DARK_GRAY);
	}

	void goingOut(IDirection d){
		m_entity.die();
	}
	
}
