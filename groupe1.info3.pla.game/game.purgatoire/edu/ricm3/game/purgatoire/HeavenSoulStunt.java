package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;

public class HeavenSoulStunt extends Stunt {

	HeavenSoulStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
	}
	
	HeavenSoulStunt(){
		super(null, null, Color.DARK_GRAY);
	}
}
