package edu.ricm3.game.purgatoire;

import java.awt.Color;

public class LevelMaker {
	public static Level makeTestLevel(Model model, Color c) {
		Level level = new Level(model, c);

		Special special = new Special(level, 0, 0, 5, 5);
		level.addEntity(special);

		Soul soul = new Soul(level, 10, 10, 2, 2);
		level.addEntity(soul);
		soul = new Soul(level, 14, 10, 2, 2);
		level.addEntity(soul);
		soul = new Soul(level, 18, 10, 2, 2);
		level.addEntity(soul);

		Obstacle wall = new Obstacle(level, 10, 30, 20, 2);
		level.addEntity(wall);
		wall = new Obstacle(level, 10, 32, 2, 20);
		level.addEntity(wall);

		return level;
	}

}
