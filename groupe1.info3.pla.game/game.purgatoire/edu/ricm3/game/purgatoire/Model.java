/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.io.FileNotFoundException;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.purgatoire.entities.Player;

public class Model extends GameModel {

	private WorldType m_wt;
	Player m_player;
	Level m_currentLevel, m_nextLevel;

	public int m_totalDistance;
	double m_period;
	public double m_totalTime;

	long lastPeriodUpdate;

	public Model(){
		m_wt = WorldType.HEAVEN;
		m_currentLevel = LevelMaker.loadLevel(this, Color.yellow);
		m_nextLevel = LevelMaker.loadLevel(this, Color.pink);

		m_player = new Player(this, m_currentLevel, (Options.LVL_WIDTH) / 2,
				Options.LVL_HEIGHT - Options.PLAYER_HEIGHT);
	}

	public void transform() {
		if (m_wt == WorldType.HEAVEN)
			m_wt = WorldType.HELL;
		else
			m_wt = WorldType.HEAVEN;
		m_currentLevel.transform();
		m_nextLevel.transform();
	}

	public void step(long now) {
//		if (lastPeriodUpdate == 0) {
//			lastPeriodUpdate = now;
//		}

		m_nextLevel.step(now);
		m_currentLevel.step(now);

		if (now - lastPeriodUpdate > 100) {
			m_period += now - lastPeriodUpdate;
			m_totalTime += now - lastPeriodUpdate; // ? m_totalTime = now ?
			lastPeriodUpdate = now;
		}
		if (m_period >= Options.TOTAL_PERIOD) {
			m_player.testKarma();
			m_period = 0;
		}
	}

	public WorldType getWorldType() {
		return m_wt;
	}

	public Player getPlayer() {
		return m_player;
	}

	public void nextLevel(){
		m_currentLevel = m_nextLevel;
		m_nextLevel = LevelMaker.loadLevel(this, Color.GREEN);
		m_player.nextLevel(m_currentLevel);
	}

	void printWorld() {
		if (m_wt == WorldType.HEAVEN)
			System.out.println("Heaven");
		else
			System.out.println("Hell");
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}
}
