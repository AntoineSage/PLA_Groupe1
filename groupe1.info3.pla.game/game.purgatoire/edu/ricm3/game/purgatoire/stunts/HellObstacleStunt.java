package edu.ricm3.game.purgatoire.stunts;

import java.awt.Color;
import java.awt.image.BufferedImage;

import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.entities.Entity;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;

public class HellObstacleStunt extends Stunt {

	HellObstacleStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
		m_maxHP = Options.HELL_OBSTACLE_HP_MAX;
		setDMG(Options.HELL_OBSTACLE_DMG);
	}

	public HellObstacleStunt() {
		super(Singleton.getNewObstacleHellAut(), null, Color.pink);
	}

	@Override
	public void pop(IDirection d) {
		System.out.println("pop hell obstacle");
	}

	@Override
	public void wizz(IDirection d) {
		System.out.println("wizz hell obstacle");
	}

	@Override
	public void hit(IDirection d) {
		System.out.println("hit hell obstacle");
	}

	@Override
	public void egg() {
		System.out.println("egg hell obstacle");
	}

	@Override
	public void takeDamage(int DMG) {
		System.out.println("takedamage hell obstacle");
	}

}
