package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;

public class HellObstacleStunt extends Stunt {

	HellObstacleStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
	}

	HellObstacleStunt() {
		super(null, null, Color.pink);
	}
}
