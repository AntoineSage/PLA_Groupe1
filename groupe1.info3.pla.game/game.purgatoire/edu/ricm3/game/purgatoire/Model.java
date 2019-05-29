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

public class Model extends GameModel {

	public static enum WorldType {
		HEAVEN, HELL;
	}

	private WorldType m_wt;
	private Player m_player;
	private long m_lastTransform;
	// TODO lastTransform and transform() in Controller?

	public Model() {
		m_wt = WorldType.HEAVEN;
		m_player = new Player(this);
	}

	@Override
	public void step(long now) {
		if (m_lastTransform == 0)
			m_lastTransform = now;
		if (now - m_lastTransform > 2000) {
			transform();
			printWorld();
			m_player.pop();
			m_player.wizz();
			m_lastTransform = now;
		}
	}

	WorldType getWorld() {
		return m_wt;
	}

	void printWorld() {
		if (m_wt == WorldType.HEAVEN)
			System.out.println("Heaven");
		else
			System.out.println("Hell");
	}

	void transform() {
		if (m_wt == WorldType.HEAVEN)
			m_wt = WorldType.HELL;
		else
			m_wt = WorldType.HEAVEN;
		m_player.transform();
	}

	@Override
	public void shutdown() {
	}
}
