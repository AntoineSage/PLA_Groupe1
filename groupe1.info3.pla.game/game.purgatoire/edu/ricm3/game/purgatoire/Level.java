package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.util.LinkedList;

public class Level {

	Model m_model;
	LinkedList<Entity> m_souls;
	LinkedList<Entity> m_obstacles;
	LinkedList<Entity> m_entities;
	Entity m_special;
	Entity collisionGrid[][];
	Color m_c;



	Level(Model model, Color c) {
		m_c = c;
		m_model = model;
		m_souls = new LinkedList<Entity>();
		m_obstacles = new LinkedList<Entity>();
		m_entities = new LinkedList<Entity>();
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
}
