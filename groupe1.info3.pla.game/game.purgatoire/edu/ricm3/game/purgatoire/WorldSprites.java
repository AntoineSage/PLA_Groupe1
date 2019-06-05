package edu.ricm3.game.purgatoire;

import java.awt.Color;

//import java.awt.image.BufferedImage;

public class WorldSprites {

//	private BufferedImage[] m_player;
	private Color m_playerColor;	
	
	public WorldSprites(Color color) {
		m_playerColor = color;
	}
	
	public Color getPlayerColor() {
		return m_playerColor;
	}
	
//	public WorldSprites(BufferedImage[] player) {
//		m_player = player;
//	}
	
//	BufferedImage[] getPlayerSprite() {
//		return m_player;
//	}
	
}
