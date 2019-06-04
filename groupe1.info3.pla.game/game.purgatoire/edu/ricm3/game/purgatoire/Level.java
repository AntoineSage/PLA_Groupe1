package edu.ricm3.game.purgatoire;

import java.util.LinkedList;
public class Level {

	Model m_model;
	LinkedList<Entity> m_souls;
	LinkedList<Entity> m_obstacles;
	Entity special;

	public Level(Model model) {
		m_model=model;
		
	}
	void transform() {
		System.out.println("Level transform");
		//TODO appeler transform sur les listes(souls,obstacles...)
	}
}
