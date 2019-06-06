package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HeavenSoulStunt extends Stunt {

	Player isPlayer;

	HeavenSoulStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
	}

	HeavenSoulStunt() {
		super(Singleton.getNewSoulHeavenAut(), null, Color.DARK_GRAY);
	}

	@Override
	void pop(IDirection d) {
		m_entity.die();
		System.out.println("pop heaven soul");
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz heaven soul");
	}

	@Override
	void hit(IDirection d) {
		System.out.println("hit heaven soul");
	}

	@Override
	void egg() {
		System.out.println("egg heaven soul");
	}

	@Override
	void getDamage(int DMG) {
		System.out.println("getDamage heaven soul");
	}

	@Override
	public void step(Entity e) {
		isPlayer = (Player) m_entity.superposedWith(IEntityType.PLAYER);
		if (isPlayer != null) {
			isPlayer.addKarma(m_entity);
			isPlayer.takeDamage(m_entity.m_DMG);
			m_entity.die();
		}
		m_automaton.step(m_entity);
	}
}
