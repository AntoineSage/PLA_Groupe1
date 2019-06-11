package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelMaker {

	public Level loadLevel(Model model, Color c) {
		Level level = new Level(model, c);
		for (int i = 0; i < 4; i++) {
			level.levelgenerator(levelFromFile(fileName, level), i);
		}
		return level;
	}

	private List<String> levelFromFile(String fileName, Level level) throws FileNotFoundException {
		List<String> levelQuarter = new ArrayList<String>();

		File file = new File(fileName);
		Scanner sc = new Scanner(file);

		if (sc.hasNextLine()) {
			String line = sc.nextLine();
			levelQuarter.add(line);
		}
		return levelQuarter;
	}

}
