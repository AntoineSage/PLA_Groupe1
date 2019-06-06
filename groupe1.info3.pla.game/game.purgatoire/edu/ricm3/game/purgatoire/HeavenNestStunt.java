package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;

public class HeavenNestStunt extends Stunt{

	HeavenNestStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
	}
	
	HeavenNestStunt(){
		super(Singleton.getNewNestHellAut(),null, Color.GRAY);
	}
	
	@Override
	void egg() {
		System.out.println("egg de Nid (Heaven) ");
	}
}
