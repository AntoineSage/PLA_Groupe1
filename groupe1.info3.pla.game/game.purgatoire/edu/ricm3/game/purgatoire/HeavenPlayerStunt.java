package edu.ricm3.game.purgatoire;

public class HeavenPlayerStunt extends Stunt {
	HeavenPlayerStunt(Entity entity) {
		super(entity);
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
	void hit(Entity entity) {
	}
}
