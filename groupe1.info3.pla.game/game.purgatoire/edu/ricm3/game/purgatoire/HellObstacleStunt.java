package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;

public class HellObstacleStunt extends Stunt {

	HellObstacleStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
		m_maxHP = Options.HELL_OBSTACLE_HP_MAX;
		m_DMG = Options.HELL_OBSTACLE_DMG;
	}

	HellObstacleStunt() {
		super(Singleton.getNewObstacleHellAut(), null, Color.pink);
	}

	@Override
	void pop(IDirection d) {
		System.out.println("pop hell obstacle");
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz hell obstacle");
	}

	@Override
	void hit(IDirection d) {
		System.out.println("hit hell obstacle");
	}

	@Override
	void egg() {
		System.out.println("egg hell obstacle");
	}

	@Override
	void getDamage(int DMG) {
		System.out.println("getdamage hell obstacle");
	}

}
