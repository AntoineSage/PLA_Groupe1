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

public class View extends GameView implements Transformable {
	private static final long serialVersionUID = 1L;

	Model m_model;
	int m_yG1; // position de g1 par rapport à g
	private WorldSprites m_heaven;
	private WorldSprites m_hell;
	private WorldSprites m_current;

	public View(Model m) {
		m_model = m;
		m_yG1 = Options.LVL_HEIGHT + 1 - Options.NB_BLOCKS_WIN;
		transform();
	}

	public void step(long now) {
		if (m_model.m_player.m_bounds.y < (Options.LVL_HEIGHT - 1) - (Options.NB_BLOCKS_WIN - 1) / 2) {
			m_yG1 = m_model.m_player.m_bounds.y - (Options.NB_BLOCKS_WIN - m_model.m_player.m_bounds.height) / 2;
		} else {
			m_yG1 = Options.LVL_HEIGHT + 1 - Options.NB_BLOCKS_WIN;
		}
	}

	public void transform() {
		if (m_model.getWorld() == WorldType.HEAVEN)
			m_current = m_heaven;
		else
			m_current = m_hell;
	}

	@Override
	protected void _paint(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, getWidth(), getHeight());

		Graphics g1 = g.create(0, -m_yG1 * (Options.BLOCK_SIZE + 1), Options.LVL_WIDTH * Options.BLOCK_SIZE,
				Options.LVL_HEIGHT * Options.BLOCK_SIZE);
		g1.setColor(m_model.m_currentLevel.m_c);
		g1.fillRect(0, 0, Options.LVL_WIDTH * Options.BLOCK_SIZE, Options.LVL_HEIGHT * Options.BLOCK_SIZE);

		Graphics g2 = g.create(0, ((-m_yG1) - Options.LVL_HEIGHT) * (Options.BLOCK_SIZE - 1),
				Options.LVL_WIDTH * Options.BLOCK_SIZE, Options.LVL_HEIGHT * Options.BLOCK_SIZE);
		g2.setColor(m_model.m_nextLevel.m_c);
		g2.fillRect(0, 0, Options.LVL_WIDTH * Options.BLOCK_SIZE, Options.LVL_HEIGHT * Options.BLOCK_SIZE);
		
		paint(g1, m_model.getPlayer());
		paint(g1, m_model.getSpecial());

		paint(g1, m_model.getObstacle());
		paint(g1, m_model.getPlayer());
		paint(g1, m_model.getSoul());

		g1.dispose();
		g2.dispose();
	}

	private void paint(Graphics g, Entity e) {
		g.setColor(e.m_currentStunt.m_c);
		g.fillRect(e.m_bounds.x * Options.BLOCK_SIZE, e.m_bounds.y * Options.BLOCK_SIZE,
				e.m_bounds.width * Options.BLOCK_SIZE, e.m_bounds.height * Options.BLOCK_SIZE);

	}

}
