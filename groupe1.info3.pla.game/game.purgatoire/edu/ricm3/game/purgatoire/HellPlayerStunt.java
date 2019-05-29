package edu.ricm3.game.purgatoire;

public class HellPlayerStunt extends Stunt {

	HellPlayerStunt(Entity entity) {
		super(entity);
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
	void hit(Entity entity) {
	}
}
