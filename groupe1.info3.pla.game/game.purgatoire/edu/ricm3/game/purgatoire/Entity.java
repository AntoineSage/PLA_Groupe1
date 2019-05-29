package edu.ricm3.game.purgatoire;

public class Entity {

	Stunt heaven;
	Stunt hell;
	Stunt current;
	int dmg;
	int hp;
	Model m_entity;

	Entity() {

	}

	enum WorldType {
		HEAVEN, HELL;
	}

	void transform() {
		if (getWorld() == WorldType.HELL)
			current = heaven;
		else
			current = hell;
	}

	WorldType getWorld() {
		return WorldType.HEAVEN;
		// TODO type partagé
	}

	void takeDamage() {

	}
}
