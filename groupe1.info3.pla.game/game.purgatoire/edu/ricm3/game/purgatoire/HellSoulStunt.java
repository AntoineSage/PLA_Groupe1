package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;
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
}
