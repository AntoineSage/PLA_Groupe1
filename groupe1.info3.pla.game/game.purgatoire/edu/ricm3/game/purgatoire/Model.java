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

import edu.ricm3.game.GameModel;

public class Model extends GameModel implements Transformable {
	private WorldType m_wt;
	Level m_currentLevel, m_nextLevel;
	private Player m_player;
	// TODO lastTransform and transform() in Controller?

	public Model() {
		m_wt = WorldType.HEAVEN;
		m_currentLevel = new Level(this);
		m_nextLevel = new Level(this);
		m_player = new Player(this, m_currentLevel, 0, 0, 3, 3);
	}

	@Override
	public void step(long now) {
	}

	WorldType getWorld() {
		return m_wt;
	}

	public Player getPlayer() {
		return m_player;
	}

	void printWorld() {
		if (m_wt == WorldType.HEAVEN)
			System.out.println("Heaven");
		else
			System.out.println("Hell");
	}

	public void transform() {
		// TODO put world change in Controller?
//		if (m_wt == WorldType.HEAVEN)
//			if (m_player.m_karma < 0)
//				m_wt = WorldType.HELL;
//			else if (m_player.m_karma > 0)
//				m_wt =WorldType.HEAVEN;
		if(m_wt == WorldType.HEAVEN) m_wt = WorldType.HELL;
		else m_wt = WorldType.HEAVEN;
		m_player.transform();
		m_currentLevel.transform();
		m_nextLevel.transform();
	}

	@Override
	public void shutdown() {
	}

	public WorldType getWorldType() {
		return m_wt;
	}
}
