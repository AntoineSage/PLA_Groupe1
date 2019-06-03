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

	private Model m_model;

	private HeroView m_heroView;

	private int gridSize;
	private int numberOfCellHorizontally;
	
	public View(Model m) {
		m_model = m;
		m_heroView = new HeroView(m_model.m_hero);
		numberOfCellHorizontally = 80;
	}

	@Override
	protected void _paint(Graphics g) {
		gridSize = (getWidth())/ numberOfCellHorizontally;
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		m_heroView.paint(g, gridSize);
		paintGrid(g);
	}
	
	private void paintRainbowGrid(Graphics g) {
		float hsb[] = {0, 0.8f, 0.8f};
		int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
		Color col = new Color(rgb);
		
		for(int i = 0; i < getWidth(); i+= gridSize) {
			g.setColor(col);
			g.drawLine(i, 0, i, getHeight());
			hsb[0] = (hsb[0] + (1.0f / (float)(getWidth() / gridSize)));
			rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
			col = new Color(rgb);
		}
		
		hsb[0] = 0;
		
		for(int i = 0; i < getHeight(); i+= gridSize) {
			g.setColor(col);
			g.drawLine(0, i, getWidth(), i);
			hsb[0] = (hsb[0] + (1.0f / (float)(getHeight() / gridSize)));
			rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
			col = new Color(rgb);
		}
	}	
	
	private void paintGrid(Graphics g) {
		g.setColor(new Color(0, 0, 0, 100));
		for(int i = 0; i < getWidth(); i+= gridSize) {
			g.drawLine(i, 0, i, getHeight());
		}
		
		for(int i = 0; i < getHeight(); i+= gridSize) {
			g.drawLine(0, i, getWidth(), i);
		}
	}
	
}
