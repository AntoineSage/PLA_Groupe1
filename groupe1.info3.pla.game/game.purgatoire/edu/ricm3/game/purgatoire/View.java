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
import java.awt.Graphics;

import edu.ricm3.game.GameView;
import edu.ricm3.game.purgatoire.Model.WorldType;

public class View extends GameView implements Transformable {
	private static final long serialVersionUID = 1L;

	Model m_model;
	private WorldSprites m_heaven;
	private WorldSprites m_hell;
	private WorldSprites m_current;

	public View(Model m) {
		m_model = m;
		m_heaven = new WorldSprites(Color.blue);
		m_hell = new WorldSprites(Color.red);
		transform();
	}
	
//	public void step(long now) {
//	}
	
	public void transform() {
		if (m_model.getWorld() == WorldType.HEAVEN)
			m_current = m_heaven;
		else
			m_current = m_hell;
	}

	@Override
	protected void _paint(Graphics g) {
		g.setColor(m_current.getPlayerColor());
		g.fillRect(0, 0, 50, 50);
	}
}
