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

import edu.ricm3.game.GameModel;

public class Model extends GameModel {
	WorldType m_wt;
	Level m_currentLevel, m_nextLevel;

	Player m_player;
	Soul m_soul;
	Obstacle m_obstacle;
	Nest m_nest;
	Special m_special;

	int m_totalDistance;
	double m_period, m_totalTime;

	long lastUpdatePlayer, lastUpdateSoul, lastPeriodUpdate;

	public Model() {
		m_wt = WorldType.HELL;
		m_currentLevel = LevelMaker.makeTestLevel(this, Color.yellow);
		m_nextLevel = LevelMaker.makeTestLevel(this, Color.pink);

		m_player = new Player(this, m_currentLevel, (Options.LVL_WIDTH) / 2, Options.LVL_HEIGHT - Options.PLAYER_HEIGHT,
				Options.PLAYER_WIDTH, Options.PLAYER_HEIGHT);
	}

	WorldType getWorld() {
		return m_wt;
	}

	public Player getPlayer() {
		return m_player;
	}

	public Special getSpecial() {
		return m_special;
	}

	public Soul getSoul() {
		return m_soul;
	}

	public Nest getNest() {
		return m_nest;
	}

	void printWorld() {
		if (m_wt == WorldType.HEAVEN)
			System.out.println("Heaven");
		else
			System.out.println("Hell");
	}

	public void transform() {
		if (m_wt == WorldType.HEAVEN)
			m_wt = WorldType.HELL;
		else
			m_wt = WorldType.HEAVEN;
		m_player.transform();
		m_currentLevel.transform();
		m_nextLevel.transform();
	}

	public void step(long now) {
		if (lastPeriodUpdate == 0) {
			lastPeriodUpdate = now;
		}
		m_currentLevel.step(now);
		m_nextLevel.step(now);
		if (now - lastPeriodUpdate > 100) {
			m_period += now - lastPeriodUpdate;
			m_totalTime += now - lastPeriodUpdate;
			lastPeriodUpdate = now;
		}
		if (m_period >= Options.TOTAL_PERIOD) {
			m_player.testKarma();
			m_period = 0;
		}
	}

	@Override
	public void shutdown() {
	}

	public WorldType getWorldType() {
		return m_wt;
	}

	void nextLevel() {
		m_currentLevel = m_nextLevel;
		m_nextLevel = LevelMaker.makeTestLevel(this, Color.GREEN);
		m_player.nextLevel(m_currentLevel);
	}

	public Obstacle getObstacle() {
		return m_obstacle;
	}
}
