package edu.ricm3.game.purgatoire;

import java.awt.Rectangle;

import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class Entity {
	int m_HP, m_maxHP;
	int m_DMG;
	int m_karmaToGive;
	Stunt m_heavenStunt, m_hellStunt, m_currentStunt;
	Level m_level;
	Rectangle m_bounds;
	IEntityType m_type;
	IDirection m_direction;

	Entity(Level level, Stunt heaven, Stunt hell, int x, int y, int width, int height) {
		m_level = level;
		m_heavenStunt = heaven;
		m_heavenStunt.setAttachedEntity(this);
		m_hellStunt = hell;
		m_hellStunt .setAttachedEntity(this);
		m_bounds = new Rectangle(x, y, width, height);
		m_direction = IDirection.NORTH;
		m_level.addEntity(this);
		transform();
	}

	public void transform() {
		if (getWorldType() == WorldType.HEAVEN)
			m_currentStunt = m_heavenStunt;
		else
			m_currentStunt = m_hellStunt;
	}

	void step(long now) {
		m_currentStunt.step(this);
	}

	WorldType getWorldType() {
		return m_level.getWorldType();
	}

	public int getHP() {
		return m_HP;
	}

	public int getMaxHP() {
		return m_maxHP;
	}
	
	public void setKarmaToGive(int karmaToGive) {
		m_karmaToGive = karmaToGive;
	}
	
	void die() {
		m_level.removeEntity(this);
	}
	
	void takeDamage(int DMG) {
		m_currentStunt.getDamage(DMG);
	}

	public void tryMove(IDirection d) {
		m_currentStunt.tryMove(d);
	}

	public void pop(IDirection d) {
		m_currentStunt.pop(d);
	}

	public void wizz(IDirection d) {
		m_currentStunt.wizz(d);
	}

	public void egg() {
		m_currentStunt.egg();
	}

	public void hit(IDirection d) {
		m_currentStunt.hit(d);
	}

	public boolean wontCollide(IDirection d) {
		return m_level.wontCollide(this, d);
	}

	public boolean isDir(IDirection m_dir) {
		return m_direction == m_dir;
	}

	public boolean isEntityAt(IEntityType type, IDirection direction) {
		return m_currentStunt.isEntityAt(type, direction);
	}

	public boolean isClosestEntityAt(IEntityType m_type2, IDirection m_direction2) {
		throw new IllegalStateException("Not yet implemented");
	}
	
	// To improve
	public Entity superposedWith(IEntityType type) {
		 return m_level.m_collisionGrid.testCollisionWithType(this, type);
	}
}
