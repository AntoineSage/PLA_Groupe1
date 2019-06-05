package edu.ricm3.game.purgatoire;

import java.awt.Color;

public class HeavenSpecialStunt extends Stunt {
	HeavenSpecialStunt(Entity entity) {
		super(null, entity, Color.ORANGE);
	}

	@Override
	void pop() {
		System.out.println("pop cat");
	}

	@Override
	void wizz() {
		System.out.println("wizz cat");
	}

}
