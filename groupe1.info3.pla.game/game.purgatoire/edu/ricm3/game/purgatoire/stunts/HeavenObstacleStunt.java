package edu.ricm3.game.purgatoire.stunts;

import java.awt.Color;
import java.awt.image.BufferedImage;

import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.entities.Entity;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;

public class HeavenObstacleStunt extends Stunt {

	HeavenObstacleStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
		m_maxHP = Options.HEAVEN_OBSTACLE_HP_MAX;
		setDMG(Options.HEAVEN_OBSTACLE_DMG);
	}

	public HeavenObstacleStunt() {
		super(Singleton.getNewObstacleHeavenAut(), null, Color.red);
	}

	@Override
	public void pop(IDirection d) {
		System.out.println("pop heaven obstacle");
	}

	@Override
	public void wizz(IDirection d) {
		System.out.println("wizz heaven obstacle");
	}

	@Override
	public void hit(IDirection d) {
		System.out.println("hit heaven obstacle");
	}

	@Override
	public void egg() {
		System.out.println("egg heaven obstacle");
	}

	@Override
	public void takeDamage(int DMG) {
		System.out.println("takeDamage heaven obstacle");
	}

}
