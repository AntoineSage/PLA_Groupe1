package edu.ricm3.game.purgatoire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelMaker {

	private List<Integer> paternList; // index of paterns
	private List<Integer> quarterNest; // contains the list of quarter with a nest
	private List<Integer> quarterNonUsed;
	private QuarterLevel currentQuarterLevel;

	LevelMaker() {
		paternList = new ArrayList<Integer>();
		paternListTcheck();
		quarterNonUsed = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			quarterNonUsed.add(i);
		}
		quarterNest = new ArrayList<Integer>();
	}

	public Level loadLevel(Model model) {
		Random r = new Random();
		Level level = new Level(model);
		int randomSpecial = r.nextInt(4);
		quarterNonUsed.remove(randomSpecial);
		int randomNest = r.nextInt(100);

		if (randomNest < Options.LVL_3_NEST_PROBABILITY) {
			quarterNest = quarterNonUsed;
		} else if (randomNest < Options.LVL_3_NEST_PROBABILITY + Options.LVL_2_NEST_PROBABILITY) {
			int randomNonUsedQuarter1 = randomizer(quarterNonUsed.size(), r);
			quarterNest.add(randomNonUsedQuarter1);
			quarterNonUsed.remove(randomNonUsedQuarter1);
			int randomNonUsedQuarter2 = randomizer(quarterNonUsed.size(), r);
			quarterNest.add(randomNonUsedQuarter2);
			quarterNonUsed.remove(randomNonUsedQuarter2);
		} else {
			int randomNonUsedQuarter = randomizer(quarterNonUsed.size(), r);
			quarterNest.add(randomizer(randomNonUsedQuarter, r));
			quarterNonUsed.remove(randomNonUsedQuarter);
		}

		for (int i = 0; i < 4; i++) {
			paternListTcheck();
			if (i == randomSpecial)
				currentQuarterLevel = new QuarterLevel(model.getRandomGenerator(),i, paternList, level, QuarterType.SPECIAL);
			else if (quarterNest.contains(i))
				currentQuarterLevel = new QuarterLevel(model.getRandomGenerator(),i, paternList, level, QuarterType.NEST);
			else
				currentQuarterLevel = new QuarterLevel(model.getRandomGenerator(),i, paternList, level, QuarterType.NOTHING);
			paternList.remove(currentQuarterLevel.getIndex());
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
