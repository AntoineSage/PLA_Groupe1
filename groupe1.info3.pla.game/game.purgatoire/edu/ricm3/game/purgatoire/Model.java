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

	public Model() {
		m_currentLevel = new Level(this, null, Color.BLUE);
		m_nextLevel = new Level(this, null, Color.pink);
		m_player = new Player(24, Options.LVL_HEIGHT - 1 - 3, 3, 3, m_currentLevel);
	}

	void transform() {
	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
	}

	void nextLevel() {
		m_currentLevel = m_nextLevel;
		m_nextLevel = new Level(this, null, Color.GREEN);
		m_player.nextLevel(m_currentLevel);
	}
}
