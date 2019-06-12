package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LevelMaker {
	
	ArrayList<Integer> quarterList = new ArrayList<Integer>();
	
	LevelMaker(){
		if(Options.LVL_QUARTER_MAX_NBR%4 != 0.0)
			throw new IllegalArgumentException("Incorrect nbr of quarter");
		for(int i =0; i< Options.LVL_QUARTER_MAX_NBR; i++) {
			quarterList.add(i);
		}
	}
	
	public Level loadLevel(Model model, Color c) {		
		Level level = new Level(model, c);
		Random r = new Random();
		int randomIndex = 0;
		
		
		if(quarterList.isEmpty()) {
			for(int i =0; i< Options.LVL_QUARTER_MAX_NBR; i++) {
				quarterList.add(i);
			}
		}
		
		for (int i = 0; i < 4; i++) {
			randomIndex = r.nextInt(quarterList.size());
			try {
			level.levelGenerator(levelFromFile("level.paterns/casual/Patern"+quarterList.get(randomIndex)+".txt", level), i);
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
				System.exit(0);
			}
			quarterList.remove(randomIndex);
		}
		return level;
	}

	private List<String> levelFromFile(String fileName, Level level) throws FileNotFoundException {
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
