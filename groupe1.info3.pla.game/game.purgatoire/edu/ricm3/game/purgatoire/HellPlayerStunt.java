package edu.ricm3.game.purgatoire;

import java.awt.Color;

public class HellPlayerStunt extends Stunt {

	HellPlayerStunt(Entity entity) {
		super(null, entity, Color.RED);
	}

	@Override
	void pop() {
		System.out.println("pop hell");
	}

	@Override
	void wizz() {
		System.out.println("wizz hell");
	}

	@Override
	void move() {
	}

	@Override
	void hit() {
		System.out.println("hit hell");
	}

	@Override
	void egg() {
		System.out.println("egg hell");
	}

	@Override
	void getDamage() {
		System.out.println("getDamage hell");
	}
}
