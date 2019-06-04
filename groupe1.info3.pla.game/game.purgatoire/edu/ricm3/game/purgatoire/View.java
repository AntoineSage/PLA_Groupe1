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
	boolean hasChange;
	Bounds current;
	int x;
	int firstPos;

	public View(Model m) {
		m_model = m;
		m_y = m_model.player.m_y - (Options.WIN_HEIGHT) / 2 + Options.PLAYER_SIZE;
		firstPos = m_y;
		hasChange = false;
		current = m_model.current;
		x = 0;
	}

	public void up() {

		if (m_model.player.m_y > 0) {
			m_y = m_model.player.m_y - (Options.WIN_HEIGHT) / 2 + Options.PLAYER_SIZE;
		} else {
			if (current != m_model.current) {
				hasChange = true;
				current = m_model.current;
				m_y = -((x) * Options.LVL_SIZE) - (Options.WIN_HEIGHT ) + Options.PLAYER_SIZE - 4;
				System.out.println("yooooo" + m_y);
				x++;
			}

			if (hasChange) {
				System.out.println("here: " + m_model.player.m_y + "tot"
						+ (Options.LVL_SIZE - Options.WIN_HEIGHT - (Options.WIN_HEIGHT / 2)));
				if (((Math.abs(m_model.player.m_y) % Options.LVL_SIZE) < Math
						.abs(Options.LVL_SIZE - Options.WIN_HEIGHT - (Options.WIN_HEIGHT / 2)))) {
					System.out.println("here: " + m_model.player.m_y);
				} else {
					hasChange = false;
				}
			} else if (((Math.abs(m_model.player.m_y) % Options.LVL_SIZE) < Options.LVL_SIZE - Options.WIN_HEIGHT
					- (Options.WIN_HEIGHT / 2))) {
				System.out.println("here: " + m_model.player.m_y);
			}

			else {
				System.out.println("else: " + m_model.player.m_y);
				m_y = m_model.player.m_y - (Options.WIN_HEIGHT) / 2 + Options.PLAYER_SIZE;
			}
		}
	}

	public void down() {
	 if ((Math.abs(m_model.player.m_y % Options.LVL_SIZE)) < Options.LVL_SIZE - Options.WIN_HEIGHT
				+ (Options.WIN_HEIGHT / 2)){
			
		} 
		else {
			System.out.println("else: " + m_model.player.m_y);
			m_y = m_model.player.m_y - (Options.WIN_HEIGHT) / 2 + Options.PLAYER_SIZE;
		}

	}

	@Override
	protected void _paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		Graphics g2 = g.create(0, -m_y - (x + 1) * Options.LVL_SIZE, getWidth(), Options.LVL_SIZE);
		g2.setColor(m_model.next.m_c);
		g2.fillRect(m_model.next.m_x, m_model.next.m_y, m_model.next.m_w, m_model.next.m_h);
		Graphics g1 = g.create(0, -m_y - x * Options.LVL_SIZE, getWidth(), Options.LVL_SIZE);
		g1.setColor(m_model.current.m_c);
		g1.fillRect(m_model.current.m_x, m_model.current.m_y, m_model.current.m_w, m_model.current.m_h);
		g.setColor(m_model.player.m_c);
		g.fillRect(m_model.player.m_x, m_model.player.m_y - m_y, m_model.player.m_w, m_model.player.m_h);
		g2.dispose();
		g1.dispose();
	}
}
