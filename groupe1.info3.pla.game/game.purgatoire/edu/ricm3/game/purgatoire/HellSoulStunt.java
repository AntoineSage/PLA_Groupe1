package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;

public class HellSoulStunt extends Stunt{

	HellSoulStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
	}
	
	HellSoulStunt(){
		super(Singleton.getNewSoulHellAut(),null, Color.green);
	}
}
