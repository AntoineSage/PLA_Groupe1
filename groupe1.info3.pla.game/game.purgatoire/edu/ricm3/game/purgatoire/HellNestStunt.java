package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HellNestStunt extends Stunt {
	
Timer m_timer;
	
	HellNestStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
		m_timer = new Timer(5000);
	}

	HellNestStunt() {
		super(Singleton.getNewNestHellAut(), null, Color.GRAY);
		m_timer = new Timer(5000);
	}
	
	@Override
	void wizz( IDirection direction) {
		if(m_timer.end()) {
		int width = m_entity.m_bounds.width;
		int height = m_entity.m_bounds.height;
		int x = m_entity.m_bounds.x;
		int y = m_entity.m_bounds.y;
		m_entity.m_level.addEntity(new Obstacle(m_entity.m_level,x,y,width,height));
		m_entity.m_level.removeEntity(m_entity);
		System.out.println("wizz Nest");
		m_timer.start(5000);
		}
	}

	@Override
	void egg() {
		int x, y, width, height, randX, randY;
		for (int i = 0; i < 10; i++) {
			width = m_entity.m_bounds.width;
			height = m_entity.m_bounds.height;
			x = (2 * m_entity.m_bounds.x + (width)) / 2;
			y = (2 * m_entity.m_bounds.y + (height)) / 2;
			Random r = new Random();
			double tmpX = Math.random();
			double tmpY = Math.random();

			if (tmpX >= 0.5)
				randX = x - r.nextInt(width + 2);

			else
				randX = x + r.nextInt(width + 2);
			
			if (tmpY >= 0.5)
				randY = y - r.nextInt(height + 2);
			else
				randY = y + r.nextInt(height + 2);
			
			if (m_entity.m_level.m_collisionGrid.isOk(IEntityType.ADVERSARY,randX - 2, randY - 2, 2, 2))
				m_entity.m_level.addEntity(new Soul(m_entity.m_level, randX - 2, randY - 2, 2, 2));
			break;
		}
	}
	@Override
	public void step(long now) {
		super.step(now);
		m_timer.step(now);
		
	}
}
