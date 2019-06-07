package edu.ricm3.game.purgatoire;

import java.awt.image.BufferedImage;
import java.util.List;

public class Animation {
	private List<BufferedImage> m_sprites[];
	
	public enum AnimType {
		NORTH(1), SOUTH(2), EAST(3), IDLE(4);
		
        private final int value;

        AnimType(final int newValue) {
            value = newValue;
        }

        public int getValue() { return value; }
	}
    
	public Animation(String fileName) {
		m_sprites = spritesFromFile(fileName);
	}

	private static List<BufferedImage> spritesFromFile(String fileName) {
		List<BufferedImage> sprites = new BufferedImage[4];
	}

	public int length(AnimType type) {
		return m_sprites[type.getValue()].size();
	}

	public BufferedImage get(AnimType type, int position) {
		return m_sprites[type.getValue()].get(position);
	}
}
