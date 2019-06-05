package edu.ricm3.game.purgatoire;

import java.awt.Color;

public class HellSpecialStunt extends Stunt {
	HellSpecialStunt(Entity entity) {
		super(Singleton.getNewSpecialHellAut(), entity, Color.CYAN);
	}
	
	HellSpecialStunt() {
		super(Singleton.getNewSpecialHellAut(), null, Color.CYAN);
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
