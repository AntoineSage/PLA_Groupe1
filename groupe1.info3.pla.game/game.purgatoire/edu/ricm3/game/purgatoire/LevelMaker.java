package edu.ricm3.game.purgatoire;

import java.awt.Color;

public class LevelMaker {
	public static Level makeTestLevel(Model model, Color c) {
		Level level = new Level(model, c);

		new Special(level, 0, 0, 5, 5);

		new Soul(level, 10, 10, 2, 2);
		new Soul(level, 14, 10, 2, 2);
		new Soul(level, 18, 10, 2, 2);

		new Obstacle(level, 10, 30, 20, 2);
		new Obstacle(level, 10, 32, 2, 20);

		return level;
	}

}
