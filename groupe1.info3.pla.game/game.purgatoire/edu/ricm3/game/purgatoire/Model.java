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

	//int l_x, l_y, l_h, l_w;
	Bounds b1, b2, b3;

	public Model() {
//		l_x = 0;
//		l_y = 398;
//		l_h = 2;
//		l_w = 100;

		b1 = new Bounds(0, 0, Options.WIN_WIDTH, Options.LVL_SIZE);
		b2 = new Bounds(0, 0, Options.WIN_WIDTH, Options.LVL_SIZE);
		b3 = new Bounds(0, 0, Options.WIN_WIDTH, Options.LVL_SIZE);
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
