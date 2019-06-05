package edu.ricm3.game.purgatoire;

import java.awt.Color;

import ricm3.interpreter.IDirection;

public class HellPlayerStunt extends Stunt {

	HellPlayerStunt(Entity entity) {
		super(null, entity, Color.RED);
	}

	void tryMove(IDirection d) {
		
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
	void hit(IDirection d) {
		System.out.println("hit hell");
	}

	@Override
	void egg() {
		System.out.println("egg hell");
	}

	@Override
	void getDamage(int DMG) {
		System.out.println("getDamage hell");
	}
}
