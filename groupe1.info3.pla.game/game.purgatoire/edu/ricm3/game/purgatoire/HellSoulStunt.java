package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HellSoulStunt extends Stunt {

	Player isPlayer;

	HellSoulStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
	}

	HellSoulStunt() {
		super(Singleton.getNewSoulHellAut(), null, Color.green);
	}

	@Override
	public void step(Entity e) {
		isPlayer = (Player) m_entity.superposedWith(IEntityType.PLAYER);
		if (isPlayer != null) {
			m_entity.die();
		}
		m_automaton.step(m_entity);
	}
	
	@Override
	void pop(IDirection d) {
		System.out.println("pop hell soul");
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz hell soul");
	}

	@Override
	void hit(IDirection d) {
		System.out.println("hit hell soul");
	}

	@Override
	void egg() {
		System.out.println("egg hell soul");
	}

	@Override
	void getDamage(int DMG) {
		System.out.println("getdamage hell soul");
	}
}
