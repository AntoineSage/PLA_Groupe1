package edu.ricm3.game.purgatoire;

public class Special extends Entity {

	Special(Level level,int x , int y, int width, int height) {
		super(level, new HeavenSpecialStunt(null), new HellSpecialStunt(null), x, y, width, height);
		m_heavenStunt.setAttachedEntity(this);
		m_hellStunt.setAttachedEntity(this);
	}
	
}
