package edu.ricm3.game.purgatoire;

import java.awt.Color;

import ricm3.interpreter.IDirection;

public class HeavenSpecialStunt extends Stunt {
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
	
	@Override
	void getDamage(int DMG) {
	}

}
