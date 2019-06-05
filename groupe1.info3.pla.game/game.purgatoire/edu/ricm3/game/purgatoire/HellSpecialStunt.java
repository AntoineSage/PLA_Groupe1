package edu.ricm3.game.purgatoire;

import java.awt.Color;

public class HellSpecialStunt extends Stunt {
	HellSpecialStunt(Entity entity) {
		super(null, entity, Color.CYAN);
	}
	
	HellSpecialStunt() {
		super(null, null, Color.CYAN);
	}

	@Override
	void pop() {
		System.out.println("pop flaque");
	}

	@Override
	void wizz() {
		System.out.println("wizz flaque");
	}

}
