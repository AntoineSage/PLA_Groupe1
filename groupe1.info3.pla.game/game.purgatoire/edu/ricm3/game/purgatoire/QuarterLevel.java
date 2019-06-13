package edu.ricm3.game.purgatoire;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class QuarterLevel {

	protected List<String> levelQuarter;
	private int m_index;
	private int m_positionQuarter;
	protected int x_offset, y_offset;
	QuarterType m_quarterType;

	QuarterLevel(Random randGenerator,int positionQuarter, List<Integer> quarterList, Level level, QuarterType quarterType){
		levelQuarter = new ArrayList<String>();
		Random r = new Random();
		m_index = randGenerator.nextInt(quarterList.size());
		m_positionQuarter = positionQuarter;
		m_quarterType = quarterType;
		quarterOffset();
		try {
			File file = new File("level.paterns/Patern" + quarterList.get(m_index) + ".txt");
			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				levelQuarter.add(line);
			}
			sc.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	void quarterOffset() {
		switch (m_positionQuarter) {
		case 0:
			break;
		case 1:
			x_offset = Options.LVL_WIDTH / 2;
			break;
		case 2:
			y_offset = Options.LVL_HEIGHT / 2;
			break;
		case 3:
			x_offset = Options.LVL_WIDTH / 2;
			y_offset = Options.LVL_HEIGHT / 2;
			break;
		}
	}

	public int getIndex() {
		return m_index;
	}

}
