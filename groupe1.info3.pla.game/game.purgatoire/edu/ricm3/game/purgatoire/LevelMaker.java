package edu.ricm3.game.purgatoire;

import java.awt.Color;

import edu.ricm3.game.purgatoire.entities.Nest;
import edu.ricm3.game.purgatoire.entities.Obstacle;
import edu.ricm3.game.purgatoire.entities.Soul;
import edu.ricm3.game.purgatoire.entities.Special;

public class LevelMaker {
	public static Level makeTestLevel(Model model, Color c) {
		Level level = new Level(model, c);

		new Special(level, 0, 0);

		new Soul(level, 10, 10);
		new Soul(level, 14, 10);
		new Soul(level, 18, 10);

		new Obstacle(level, 10, 30, Options.OBSTACLE_SIZE);
		new Obstacle(level, 13, 30, Options.OBSTACLE_SIZE);
//		new Obstacle(level, 16, 30, Options.OBSTACLE_SIZE);
		new Obstacle(level, 19, 30, Options.OBSTACLE_SIZE);

		new Obstacle(level, 10, 33, Options.OBSTACLE_SIZE);
		new Obstacle(level, 10, 36, Options.OBSTACLE_SIZE);
		new Obstacle(level, 10, 39, Options.OBSTACLE_SIZE);
		new Obstacle(level, 10, 42, Options.OBSTACLE_SIZE);

		new Nest(level, 30, 30);
		new Nest(level, 30, 10);

		return level;
	}

}
