package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;

public class HeavenObstacleStunt extends Stunt {

	HeavenObstacleStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
		m_maxHP = Options.HEAVEN_OBSTACLE_HP_MAX;
		m_DMG = Options.HEAVEN_OBSTACLE_DMG;
	}

	HeavenObstacleStunt() {
		super(Singleton.getNewObstacleHeavenAut(), null, Color.red);
	}
	
	@Override
	void pop(IDirection d) {
		System.out.println("pop heaven");
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz heaven");
	}

	@Override
	void hit(IDirection d) {
		System.out.println("hit heaven");
	}

	@Override
	void egg() {
		System.out.println("egg heaven");
	}

	@Override
	void getDamage(int DMG) {
		System.out.println("getDamage heaven");
	}

}
