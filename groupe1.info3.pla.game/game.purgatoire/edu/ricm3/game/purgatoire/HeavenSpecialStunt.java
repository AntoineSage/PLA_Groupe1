package edu.ricm3.game.purgatoire;

import java.awt.Color;

import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HeavenSpecialStunt extends Stunt {

	Player isPlayer;

	HeavenSpecialStunt(Entity entity) {
		super(Singleton.getNewSpecialHeavenAut(), entity, Color.ORANGE);
	}

	HeavenSpecialStunt() {
		super(Singleton.getNewSpecialHeavenAut(), null, Color.ORANGE);
	}

	@Override
	void pop(IDirection d) {
		System.out.println("pop cat");
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz cat");
	}

	void step() {
		isPlayer = (Player) m_entity.superposedWith(IEntityType.PLAYER);
		if (isPlayer != null) {
			this.pop(IDirection.EAST);
			this.wizz(IDirection.EAST);
		}
	}
}
