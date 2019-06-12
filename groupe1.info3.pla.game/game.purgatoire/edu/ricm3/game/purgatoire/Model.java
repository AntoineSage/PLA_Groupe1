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
	LevelMaker m_currentLevelMaker, m_nextLevelMaker;

	public int m_totalDistance;
	double m_period;
	public double m_totalTime;

	long lastPeriodUpdate;

	public Model(){
		m_wt = WorldType.HEAVEN;
		m_currentLevelMaker = new LevelMaker();
		m_nextLevelMaker = new LevelMaker();
		m_currentLevel = m_currentLevelMaker.loadLevel(this, Color.yellow);
		m_nextLevel = m_nextLevelMaker.loadLevel(this, Color.pink);

		m_player = new Player(this, m_currentLevel, (Options.LVL_WIDTH) / 2, Options.LVL_HEIGHT - Options.PLAYER_SIZE);
	}

	public void transform() {
		if (m_wt == WorldType.HEAVEN) {
			m_wt = WorldType.HELL;
			m_player.setHP(m_player.getMaxHP() / 2);
		} else {
			m_wt = WorldType.HEAVEN;
			double p = m_player.getHPPercent();
			m_player.setMaxHP(m_player.getMaxTotalHP());
			m_player.setHPPercent(p);
		}
		m_currentLevel.transform();
		m_nextLevel.transform();
	}

	public void step(long now) {
		m_nextLevel.step(now);
		m_currentLevel.step(now);

		if (now - lastPeriodUpdate > 100) {
			m_period += now - lastPeriodUpdate;
			m_totalTime += now - lastPeriodUpdate; // ? m_totalTime = now ?
			lastPeriodUpdate = now;
		}
		if (m_period >= Options.TOTAL_PERIOD) {
			if ((int) (m_totalTime / Options.TOTAL_PERIOD) % Options.NB_PERIOD_DIFFICULTY == 0) {
				raiseDifficulty();
			}
			// raiseDifficulty();
			// m_currentLevel.updateDifficulty();
			// m_nextLevel.updateDifficulty();
			m_player.testKarma();
			m_period = 0;
		}
	}

	private void raiseDifficulty() {
		Options.HELL_NEST_DMG *= Options.HELL_NEST_DMG_COEF;
		Options.HELL_NEST_HP_MAX *= Options.HELL_NEST_HP_MAX_COEF;
		Options.HEAVEN_NEST_DMG *= Options.HEAVEN_NEST_DMG_COEF;
		Options.HEAVEN_NEST_HP_MAX *= Options.HEAVEN_NEST_HP_MAX_COEF;

		Options.HELL_SOUL_DMG *= Options.HELL_SOUL_DMG_COEF;
		Options.HELL_SOUL_HP_MAX *= Options.HELL_SOUL_HP_MAX_COEF;
		Options.HEAVEN_SOUL_DMG *= Options.HEAVEN_SOUL_DMG_COEF;
		Options.HEAVEN_SOUL_HP_MAX *= Options.HEAVEN_SOUL_HP_MAX_COEF;

		Options.HELL_OBSTACLE_DMG *= Options.HELL_OBSTACLE_DMG_COEF;
		Options.HELL_OBSTACLE_HP_MAX *= Options.HELL_OBSTACLE_HP_MAX_COEF;
		Options.HEAVEN_OBSTACLE_DMG *= Options.HEAVEN_OBSTACLE_DMG_COEF;
		Options.HEAVEN_OBSTACLE_HP_MAX *= Options.HEAVEN_OBSTACLE_HP_MAX_COEF;

		if (Options.ECHO_RAISE_DIFFICULTY) {
			System.out.println(
					"Nest upgraded: " + Options.HELL_NEST_DMG + " hellDMG, " + Options.HELL_NEST_HP_MAX + " hellHPmax, "
							+ Options.HEAVEN_NEST_DMG + " heavenDMG, " + Options.HEAVEN_NEST_HP_MAX + " heavenHPmax");
			System.out.println(
					"Soul upgraded: " + Options.HELL_SOUL_DMG + " hellDMG, " + Options.HELL_SOUL_HP_MAX + " hellHPmax, "
							+ Options.HEAVEN_SOUL_DMG + " heavenDMG, " + Options.HEAVEN_SOUL_HP_MAX + " heavenHPmax");
			System.out.println("Obst upgraded: " + Options.HELL_OBSTACLE_DMG + " hellDMG, "
					+ Options.HELL_OBSTACLE_HP_MAX + " hellHPmax, " + Options.HEAVEN_OBSTACLE_DMG + " heavenDMG, "
					+ Options.HEAVEN_OBSTACLE_HP_MAX + " heavenHPmax");
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
		m_currentLevelMaker = m_nextLevelMaker;
		m_nextLevelMaker = new LevelMaker();
		m_nextLevel = m_nextLevelMaker.loadLevel(this, Color.GREEN);
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

	public void respawn() {
		Options.HELL_NEST_DMG = Options.HELL_NEST_DMG_BASE;
		Options.HELL_NEST_HP_MAX = Options.HELL_NEST_HP_MAX_BASE;
		Options.HEAVEN_NEST_DMG = Options.HEAVEN_NEST_DMG_BASE;
		Options.HEAVEN_NEST_HP_MAX = Options.HEAVEN_NEST_HP_MAX_BASE;
		Options.HELL_SOUL_DMG = Options.HELL_SOUL_DMG_BASE;
		Options.HELL_SOUL_HP_MAX = Options.HELL_SOUL_HP_MAX_BASE;
		Options.HEAVEN_SOUL_DMG = Options.HEAVEN_SOUL_DMG_BASE;
		Options.HEAVEN_SOUL_HP_MAX = Options.HEAVEN_SOUL_HP_MAX_BASE;
		Options.HELL_OBSTACLE_DMG = Options.HELL_OBSTACLE_DMG_BASE;
		Options.HELL_OBSTACLE_HP_MAX = Options.HELL_OBSTACLE_HP_MAX_BASE;
		Options.HEAVEN_OBSTACLE_DMG = Options.HEAVEN_OBSTACLE_DMG_BASE;
		Options.HEAVEN_OBSTACLE_HP_MAX = Options.HEAVEN_OBSTACLE_HP_MAX_BASE;
		m_totalDistance = 0;
		m_period = 0;
		m_totalTime = 0;
		m_wt = WorldType.HEAVEN;
		m_currentLevelMaker = new LevelMaker();
		m_nextLevelMaker = new LevelMaker();
		m_currentLevel = m_currentLevelMaker.loadLevel(this, Color.yellow);
		m_nextLevel = m_nextLevelMaker.loadLevel(this, Color.pink);
		m_player = new Player(this, m_currentLevel, (Options.LVL_WIDTH) / 2, Options.LVL_HEIGHT - Options.PLAYER_SIZE);

	}
}
