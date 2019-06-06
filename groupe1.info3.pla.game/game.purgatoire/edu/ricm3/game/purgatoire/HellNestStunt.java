package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;

public class HellNestStunt extends Stunt{

	HellNestStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
	}
	
	HellNestStunt(){
		super(Singleton.getNewNestHellAut(),null, Color.DARK_GRAY);
	}
	
	@Override
	void egg() {
		System.out.println("egg de Nid ");
	}
}
