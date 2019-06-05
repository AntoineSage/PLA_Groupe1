package edu.ricm3.game.purgatoire;

import java.awt.Color;

import ricm3.interpreter.IDirection;

public class HellSpecialStunt extends Stunt {
	HellSpecialStunt(Entity entity) {
		super(Singleton.getNewSpecialHellAut(), entity, Color.CYAN);
	}
	
	HellSpecialStunt() {
		super(Singleton.getNewSpecialHellAut(), null, Color.CYAN);
	}

	@Override
	void pop(IDirection d) {
		System.out.println("pop flaque");
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz flaque");
	}

}
