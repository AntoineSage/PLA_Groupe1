package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.Graphics;

public class HeroView implements IViewable {
	Entity m_hero;

	HeroView(Entity hero) { 
		m_hero = hero;
	} 

	@Override
	public void paint(Graphics g, int gridSize) {
		g.setColor(Color.red);
		g.fillRect(m_hero.m_x * gridSize, m_hero.m_y * gridSize, m_hero.m_w * gridSize, m_hero.m_h * gridSize);
	}
}
