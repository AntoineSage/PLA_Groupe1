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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import edu.ricm3.game.GameView;

public class View extends GameView implements Transformable {
	private static final long serialVersionUID = 1L;

	Model m_model;
	int m_yG1; // position de g1 par rapport à g
	private WorldSprites m_heaven;
	private WorldSprites m_hell;
	private WorldSprites m_current;
	int BLOCK_SIZE = (Options.WIN_WIDTH - 2*Options.UI_PANEL_SIZE)/Options.LVL_WIDTH;
	int NB_BLOCKS_WIN = Options.WIN_HEIGHT/BLOCK_SIZE;

	public View(Model m) {
		m_model = m;
		m_heaven = new WorldSprites(Color.blue);
		m_hell = new WorldSprites(Color.red);
		//ecart entre g1 et g, est négatif quand g1 n'est pas dans g 
		m_yG1 = -(Options.LVL_HEIGHT - NB_BLOCKS_WIN);

		transform();

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent ce) {
				System.out.println("WIIIIIIIIIIIIN " + NB_BLOCKS_WIN );
				System.out.printf("%d %d\n", getWidth(), getHeight());
				System.out.println("heyyyyyyyyyy");
				Options.WIN_HEIGHT = getHeight();
				Options.WIN_WIDTH = getWidth();
				System.out.println("WIIIIIIIIIIIIN " + NB_BLOCKS_WIN );
				BLOCK_SIZE = (Options.WIN_WIDTH - 2*Options.UI_PANEL_SIZE)/Options.LVL_WIDTH;
				NB_BLOCKS_WIN = Options.WIN_HEIGHT/BLOCK_SIZE;

			}
		});
	}

	public void step(long now) {
		
		// if the camera need to move
		if (Options.LVL_HEIGHT - m_model.m_player.m_bounds.y <= (NB_BLOCKS_WIN / 2)) {
			// else the camera don't move
			
			m_yG1 = -(Options.LVL_HEIGHT - NB_BLOCKS_WIN);
		} else {
			//System.out.println("YOOOOOOOOOOOOO " + m_model.m_player.m_bounds.y);
			m_yG1 = -(m_model.m_player.m_bounds.y - (NB_BLOCKS_WIN - m_model.m_player.m_bounds.height) / 2 );
			//System.out.println("HERRRRRE");
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
		System.out.println("GGGGGg:" + m_yG1);
		Graphics g1 = g.create((Options.WIN_WIDTH - Options.LVL_WIDTH * BLOCK_SIZE) / 2,
				m_yG1 * BLOCK_SIZE, Options.LVL_WIDTH * BLOCK_SIZE,
				(Options.LVL_HEIGHT + 1) * BLOCK_SIZE);
		System.out.println("GGGGGg:" + m_yG1);
		g1.setColor(m_model.m_currentLevel.m_c);
		g1.fillRect(0, 0, Options.LVL_WIDTH * BLOCK_SIZE, (Options.LVL_HEIGHT + 1) * BLOCK_SIZE);

		Graphics g2 = g.create((Options.WIN_WIDTH - Options.LVL_WIDTH * BLOCK_SIZE) / 2,
				(m_yG1 - Options.LVL_HEIGHT) *BLOCK_SIZE, Options.LVL_WIDTH * BLOCK_SIZE,
				(Options.LVL_HEIGHT + 1) * BLOCK_SIZE);

		g2.setColor(m_model.m_nextLevel.m_c);
		g2.fillRect(0, 0, Options.LVL_WIDTH * BLOCK_SIZE, (Options.LVL_HEIGHT + 1) * BLOCK_SIZE);

		g.setColor(m_current.getPlayerColor());
		g.fillRect(50, 50, 50, 50);

		paint(g1, m_model.getPlayer());
	}

	private void paint(Graphics g, Entity e) {
		g.setColor(e.m_currentStunt.m_c);
		g.fillRect(e.m_bounds.x * BLOCK_SIZE, e.m_bounds.y * BLOCK_SIZE,
				e.m_bounds.width * BLOCK_SIZE, e.m_bounds.height * BLOCK_SIZE);

	}

}
