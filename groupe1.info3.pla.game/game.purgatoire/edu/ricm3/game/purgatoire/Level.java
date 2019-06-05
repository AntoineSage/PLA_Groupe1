package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;

import ricm3.interpreter.IDirection;

public class Level {

	Model m_model;
	LinkedList<Entity> m_souls;
	LinkedList<Entity> m_obstacles;
	LinkedList<Entity> m_entities;
	Entity m_special;
	CollisionGrid collisionGrid;
	Color m_c;

	Level(Model model, Color c) {
		m_c = c;
		m_model = model;
		m_souls = new LinkedList<Entity>();
		m_obstacles = new LinkedList<Entity>();
		m_entities = new LinkedList<Entity>();
	}

	Level(LinkedList<Entity> obstacles) {
		m_obstacles = obstacles;
		Iterator<Entity> iter = m_obstacles.iterator();
		Entity tmp;
		while (iter.hasNext()) {
			tmp = iter.next();
			collisionGrid.addEntity(tmp);
		}
	}

	Level(Model model) {
		this(model, Color.WHITE);
	}

	void step() {
	}

	WorldType getWorldType() {
		return m_model.getWorldType();
	}

	void transform() {
		System.out.println("Level transform");
		// TODO appeler transform sur les listes(souls,obstacles...)
	}

	// Update the collisionGrid with the future new values translated by x and y.
	public void updateEntity(Entity e, int x, int y) {
		collisionGrid.updateEntity(e, x, y);
	}

	public boolean wontCollide(Entity entity, IDirection d) {
		return collisionGrid.wontCollide(entity, d);
	}
}
