package edu.ricm3.game.purgatoire;

import java.awt.Color;

import ricm3.interpreter.IDirection;

public class HeavenPlayerStunt extends Stunt {
	HeavenPlayerStunt(Entity entity) {
		super(null, entity, Color.BLUE);
	}

	void tryMove(IDirection d) {
		
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
