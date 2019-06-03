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

public class View extends GameView {
	private static final long serialVersionUID = 1L;

	Model m_model;
	int m_y;

	public View(Model m) {
		m_model = m;
		m_y = 0;
	}

	public void up() {
		m_y = m_model.player.m_y - (Options.WIN_HEIGHT) / 2 + Options.PLAYER_SIZE;
		System.out.println(m_y);
	}

	public void down() {
		m_y = m_model.player.m_y - (Options.WIN_HEIGHT) / 2 + Options.PLAYER_SIZE;
		System.out.println(m_y);
	}

	@Override
	protected void _paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		Graphics g1 = g.create(0, -m_y - Options.LVL_SIZE, getWidth(), Options.LVL_SIZE);
		g1.setColor(Color.PINK);
		g1.fillRect(m_model.b1.m_x, m_model.b1.m_y, m_model.b1.m_w, m_model.b1.m_h);
		Graphics g2 = g.create(0, -m_y, getWidth(), Options.LVL_SIZE);
		g2.setColor(Color.BLUE);
		g2.fillRect(m_model.b2.m_x, m_model.b2.m_y, m_model.b2.m_w, m_model.b2.m_h);
		Graphics g3 = g.create(0, -m_y + Options.LVL_SIZE, getWidth(), Options.LVL_SIZE);
		g3.setColor(Color.GREEN);
		g3.fillRect(m_model.b3.m_x, m_model.b3.m_y, m_model.b3.m_w, m_model.b3.m_h);
		g.setColor(Color.RED);
		g.fillRect(m_model.player.m_x, m_model.player.m_y - m_y, m_model.player.m_w, m_model.player.m_h);
		g3.dispose();
		g2.dispose();
		g1.dispose();
	}
}
