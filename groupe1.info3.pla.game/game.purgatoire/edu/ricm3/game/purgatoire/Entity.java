package edu.ricm3.game.purgatoire;

import edu.ricm3.game.purgatoire.Model.WorldType;

public class Entity {

	Stunt m_heaven;
	Stunt m_hell;
	Stunt m_current;
//	int m_dmg;
//	int m_hp;
	Model m_model;

	Entity(Model model) {
		m_model = model;
		m_heaven = new HeavenPlayerStunt(this);
		m_hell = new HellPlayerStunt(this);
		transform();
	}

	void transform() {
		if (getWorld() == WorldType.HEAVEN)
			m_current = m_heaven;
		else
			m_current = m_hell;
	}

	WorldType getWorld() {
		return m_model.getWorld();
	}

//	void step(long now) {
//	}

//	void takeDamage() {
//	}

	public void pop() {
		m_current.pop();
	}

	public void wizz() {
		m_current.wizz();
	}

}
