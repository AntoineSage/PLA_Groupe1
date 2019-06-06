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

	}

	void step() {
		isPlayer = (Player) m_entity.superposedWith(IEntityType.PLAYER);
		if (isPlayer.m_type != null) {
			isPlayer.addKarma(m_entity);
			m_entity.die();
		}
	}
}
