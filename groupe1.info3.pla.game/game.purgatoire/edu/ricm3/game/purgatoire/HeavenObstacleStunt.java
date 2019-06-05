package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;

public class HeavenObstacleStunt extends Stunt {

	HeavenObstacleStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
	}

	HeavenObstacleStunt() {
		super(null, null, Color.red);
	}

}
