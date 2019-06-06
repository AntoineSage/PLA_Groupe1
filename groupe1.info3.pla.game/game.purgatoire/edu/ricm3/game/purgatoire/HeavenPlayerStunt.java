package edu.ricm3.game.purgatoire;

import java.awt.Color;

import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HeavenPlayerStunt extends Stunt {
	HeavenPlayerStunt(Entity entity) {
		super(Singleton.getNewPlayerHeavenAut(), entity, Color.BLUE);
	}

	@Override
	void pop(IDirection d) {
		Special special = (Special)m_entity.superposedWith(IEntityType.TEAM);
		if(special != null) {
			special.pop(null);
		}
		System.out.println("pop heaven player");
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
