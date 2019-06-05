package edu.ricm3.game.purgatoire;

import java.awt.Color;

public class HeavenPlayerStunt extends Stunt {
	HeavenPlayerStunt(Entity entity) {
		super(null, entity, Color.RED);
	}

	@Override
	void pop() {
		System.out.println("pop heaven");
	}

	@Override
	void wizz() {
		System.out.println("wizz heaven");
	}

	@Override
	void move() {
	}

	@Override
	void hit() {
		System.out.println("hit heaven");
	}

	@Override
	void egg() {
		System.out.println("egg heaven");
	}

	@Override
	void getDamage() {
		System.out.println("getDamage heaven");
	}
}
