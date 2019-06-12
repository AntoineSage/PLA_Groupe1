package edu.ricm3.game.purgatoire.entities;

import java.awt.Rectangle;
import java.util.List;

import edu.ricm3.game.purgatoire.Level;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.WorldType;
import edu.ricm3.game.purgatoire.stunts.Stunt;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class Entity {
	public int m_HP;
	public Stunt m_currentStunt;
	public Level m_level;
	public Rectangle m_bounds;
	public IEntityType m_type;
	public IDirection m_direction;
	public float m_transparency;

	private Stunt m_heavenStunt, m_hellStunt;

	Entity(Level level, Stunt heaven, Stunt hell, int x, int y, int size) {
		m_level = level;
		m_heavenStunt = heaven;
		m_heavenStunt.setAttachedEntity(this);
		m_hellStunt = hell;
		m_hellStunt.setAttachedEntity(this);
		m_bounds = new Rectangle(x, y, size, size);
		m_direction = IDirection.NORTH;
		m_level.addEntity(this);
		m_transparency = 1;
		transform();
		m_HP = this.m_currentStunt.getMaxHP();
	}

	public void transform() {
		if (getWorldType() == WorldType.HEAVEN) {
			m_currentStunt = m_heavenStunt;
		} else {
			m_currentStunt = m_hellStunt;
		}
	}

	public void step(long now) {
		m_currentStunt.step(now);
	}

	WorldType getWorldType() {
		return m_level.getWorldType();
	}

	public int getHP() {
		return m_HP;
	}

	public double getHPPercent() {
		return (double) m_HP / m_currentStunt.getMaxHP();
	}

	public void setHPPercent(double p) {
		m_HP = (int) (m_currentStunt.getMaxHP() * p);
	}

	public void addHP(int HP) {
		m_HP = Math.min(m_currentStunt.getMaxHP(), m_HP + HP);
		m_HP = Math.max(m_HP, 0);
		if (Options.ECHO_HP_CHANGE)
			System.out.println("Entity HP change: " + HP + " HP, " + getMaxHP() + " maxHP");
	}

	public int getMaxHP() {
		return m_currentStunt.getMaxHP();
	}

	/*
	 * public int getDMG() { return (m_DMG * m_currentStunt.m_buffedDMG); }
	 */
	public void addMaxHP(int maxHP) {
		m_currentStunt.setMaxHP(m_currentStunt.getMaxHP() + maxHP);
	}

	public void setMaxHP(int maxHP) {
		m_currentStunt.setMaxHP(maxHP);
	}

	public void takeDamage(int DMG) {
		m_currentStunt.takeDamage(DMG);
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

	public void die() {
		m_level.removeEntity(this);
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
		if (m_type2 == IEntityType.PLAYER && m_level.getPlayer() != null) {
			return isGoodDirection(m_direction2, this, m_level.getPlayer());
		}
		return false;
	}

	public boolean isGoodDirection(IDirection d, Entity hostEntity, Entity researchedEntity) {
		switch (d) {
		case NORTH:
			if (hostEntity.m_bounds.y > researchedEntity.m_bounds.y)
				return true;
			break;
		case SOUTH:
			if (hostEntity.m_bounds.y < researchedEntity.m_bounds.y)
				return true;
			break;
		case EAST:
			if (hostEntity.m_bounds.x < researchedEntity.m_bounds.x)
				return true;
			break;
		case WEST:
			if (hostEntity.m_bounds.x > researchedEntity.m_bounds.x)
				return true;
			break;
		default:
			break;
		}
		return false;
	}

	public void enterInCollisionWith(List<Entity> entities) {

	}

	// TODO to improve
	public Entity superposedWith(IEntityType type) {
		return m_level.m_collisionGrid.testCollisionWithType(this, type);
	}

	public boolean superposedWith(IEntityType type, IDirection direction) {
		return m_level.m_collisionGrid.wontCollide(this, direction);
	}

	public long getTimeLeftPop() {
		return m_currentStunt.getTimeLeftPop();
	}

	public long getTimeLeftWizz() {
		return m_currentStunt.getTimeLeftWizz();
	}

}
