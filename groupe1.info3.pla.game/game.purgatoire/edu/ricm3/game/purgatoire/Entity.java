package edu.ricm3.game.purgatoire;

import edu.ricm3.game.purgatoire.Model.WorldType;

public class Entity implements Transformable {

	Stunt m_heavenStunt;
	Stunt m_hellStunt;
	Stunt m_currentStunt;
	int m_DMG;
	int m_HP;
	int m_maxHP;
	Model m_model;

	Entity(Model model) {
		m_model = model;
		m_heavenStunt = new HeavenPlayerStunt(this);
		m_hellStunt = new HellPlayerStunt(this);
		transform();
	}

	public void transform() {
		if (getWorld() == WorldType.HEAVEN)
			m_currentStunt = m_heavenStunt;
		else
			m_currentStunt = m_hellStunt;
	}

	WorldType getWorld() {
		return m_model.getWorld();
	}

	void step(long now) {
	}

	void takeDamage() {
		m_currentStunt.getDamage();
	}

	public void pop() {
		m_currentStunt.pop();
	}

	public void wizz() {
		m_currentStunt.wizz();
	}

	public void egg() {
		m_currentStunt.egg();
	}

	public void hit() {
		m_currentStunt.hit();
	}
}
