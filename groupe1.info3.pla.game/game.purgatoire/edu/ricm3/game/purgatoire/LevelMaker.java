package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelMaker {

	List<Integer> paternList; // index of paterns
	List<Integer> quarterNest; // contains the list of quarter with a nest
	List<Integer> quarterNonUsed;
	QuarterLevel currentQuarterLevel;

	LevelMaker() {
		paternList = new ArrayList<Integer>();
		paternListTcheck();
		quarterNonUsed = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			quarterNonUsed.add(i);
		}
		quarterNest = new ArrayList<Integer>();
	}

	public Level loadLevel(Model model, Color c) {
		Random r = new Random();
		Level level = new Level(model, c);
		int randomSpecial = r.nextInt(4);
		quarterNonUsed.remove(randomSpecial);
		int randomNest1 = r.nextInt(100);
		int randomNest2 = r.nextInt(100);

		if (randomNest1 < 5) { // Options....
			quarterNest = quarterNonUsed;
		} else if (randomNest2 < 10) {// Options.
			int randomNonUsedQuarter1 = randomizer(quarterNonUsed.size(),r);
			quarterNest.add(randomNonUsedQuarter1);
			quarterNonUsed.remove(randomNonUsedQuarter1);
			int randomNonUsedQuarter2 = randomizer(quarterNonUsed.size(),r);
			quarterNest.add(randomNonUsedQuarter2);
			quarterNonUsed.remove(randomNonUsedQuarter2);
		} else {
				int randomNonUsedQuarter = randomizer(quarterNonUsed.size(),r);
				quarterNest.add(randomizer(randomNonUsedQuarter,r));
		}

		for (int i = 0; i < 4; i++) {
			paternListTcheck();
			if (i == randomSpecial) {
				currentQuarterLevel = new QuarterLevel(i, paternList, level, QuarterType.SPECIAL);
				paternList.remove(currentQuarterLevel.m_index);

			} else {
				if (quarterNest.contains(i)) {
					currentQuarterLevel = new QuarterLevel(i, paternList, level, QuarterType.NEST);
					paternList.remove(currentQuarterLevel.m_index);
				} else {
					currentQuarterLevel = new QuarterLevel(i, paternList, level, QuarterType.NOTHING);
					paternList.remove(currentQuarterLevel.m_index);
				}
			}
			level.quarterLevelPlacement(currentQuarterLevel);
		}
		return level;
	}
	
	int randomizer(int value, Random r) {
		if (value == 0)
			return 0;
		else 
			return r.nextInt(value);
	}

	void paternListTcheck() {
		if (paternList.isEmpty()) {
			for (int j = 0; j < Options.LVL_QUARTER_MAX_NBR; j++) {
				paternList.add(j);
			}
		}
	}
}
