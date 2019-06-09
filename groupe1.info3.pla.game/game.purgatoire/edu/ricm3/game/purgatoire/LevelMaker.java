package edu.ricm3.game.purgatoire;

import java.awt.Color;

import edu.ricm3.game.purgatoire.entities.Nest;
import edu.ricm3.game.purgatoire.entities.Obstacle;
import edu.ricm3.game.purgatoire.entities.Soul;
import edu.ricm3.game.purgatoire.entities.Special;

public class LevelMaker {
	public static Level makeTestLevel(Model model, Color c) {
		Level level = new Level(model, c);

		new Special(level, 0, 0, Options.SPCL_WIDTH, Options.SPCL_HEIGHT);

		new Soul(level, 10, 10, Options.SOUL_WIDTH, Options.SOUL_HEIGHT);
		new Soul(level, 14, 10, Options.SOUL_WIDTH, Options.SOUL_HEIGHT);
		new Soul(level, 18, 10, Options.SOUL_WIDTH, Options.SOUL_HEIGHT);

		new Obstacle(level, 10, 30, 20, 2);
		new Obstacle(level, 10, 32, 2, 20);

		new Nest(level, 30, 30, Options.NEST_WIDTH, Options.NEST_HEIGHT);
		new Nest(level, 30, 10, Options.NEST_WIDTH, Options.NEST_HEIGHT);

		return level;
	}

}
