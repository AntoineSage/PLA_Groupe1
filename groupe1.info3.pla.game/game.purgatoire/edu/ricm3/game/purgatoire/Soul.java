package edu.ricm3.game.purgatoire;

public class Soul extends Entity {

	Soul(Level level, Stunt heaven, Stunt hell, int x, int y, int width, int height) {
		super(level, heaven, hell, x, y, width, height);
		m_heavenStunt.setAttachedEntity(this);
		m_hellStunt.setAttachedEntity(this);
	}

}
