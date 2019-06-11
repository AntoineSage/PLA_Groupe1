package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelMaker {

	public static Level loadLevel(Model model, Color c) {
		Level level = new Level(model, c);
		for (int i = 0; i < 4; i++) {
			try {
			level.levelGenerator(levelFromFile("level.paterns/casual/Patern1.txt", level), i);
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		return level;
	}

	private static List<String> levelFromFile(String fileName, Level level) throws FileNotFoundException {
		List<String> levelQuarter = new ArrayList<String>();

		File file = new File(fileName);
		Scanner sc = new Scanner(file);

		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			levelQuarter.add(line);
		}
		sc.close();
		return levelQuarter;
	}

}
