package edu.ricm3.game.purgatoire;

import java.awt.Color;

import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;

public class HellMissileStunt extends Stunt {

	HellMissileStunt(IAutomaton automaton, Color c) {
		super(automaton, c);
		m_DMG = Options.HELL_MISSILE_DMG;
	}
	
	HellMissileStunt(){
		super(Singleton.getNewMissileHellAut(), null, Color.DARK_GRAY);
	}
	
	@Override
	void pop(IDirection d) {
		m_entity.die();
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz hell soul");
	}

	@Override
	void hit(IDirection d) {
		System.out.println("hit hell soul");
	}

	@Override
	void egg() {
		System.out.println("egg hell soul");
	}
	
	void goingOut(IDirection d){
		m_entity.die();
	}
	
	@Override
	public void step(long now) {
		m_automaton.step(m_entity);
	}

}
