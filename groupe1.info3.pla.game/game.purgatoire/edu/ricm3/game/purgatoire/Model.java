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

	Bounds current, next, player;
	int pt = Options.LVL_SIZE;
	Color colors[];
	int indice;

	public Model() {
		current = new Bounds(0, 0, Options.WIN_WIDTH, Options.LVL_SIZE, Color.BLUE);
		next = new Bounds(0, 0, Options.WIN_WIDTH, Options.LVL_SIZE, Color.PINK);
		player = new Bounds((Options.WIN_WIDTH) / 2 - Options.PLAYER_SIZE,
				((Options.LVL_SIZE) / 4) , Options.PLAYER_SIZE, Options.PLAYER_SIZE, Color.red);
		colors = new Color[3];
		colors[0] = Color.GREEN;
		colors[1] = Color.BLUE;
		colors[2] = Color.PINK;
	}

	public void up() {
		player.m_y--;
		if (player.m_y + player.m_h < pt - Options.LVL_SIZE) {
			pt -= Options.LVL_SIZE;
			System.out.println(current.m_c);
			current = next;
			System.out.println(current.m_c);
			next = new Bounds(0, 0, Options.WIN_WIDTH, Options.LVL_SIZE, colors[indice % 3]);
			indice++;

		}
	}

	public void down() {
		if (player.m_y + player.m_h != pt) {
			player.m_y++;
		}
	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
	}
}
