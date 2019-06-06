package edu.ricm3.game.purgatoire;

import java.awt.Color;

import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HellSpecialStunt extends Stunt {
	
	Player isPlayer;
	
	HellSpecialStunt(Entity entity) {
		super(Singleton.getNewSpecialHellAut(), entity, Color.CYAN);
	}
	
	HellSpecialStunt() {
		super(Singleton.getNewSpecialHellAut(), null, Color.CYAN);
	}

	@Override
	void pop(IDirection d) {
		System.out.println("pop flaque");
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz flaque");
	}
	@Override
	public void step(Entity e) {
		isPlayer = (Player)m_entity.superposedWith(IEntityType.PLAYER);
		if(isPlayer != null) {
			System.out.println("sur flaque");
			isPlayer.addKarma(m_entity);
			m_entity.m_HP ++;
		}
		m_automaton.step(m_entity);	
	}

	@Override
	public boolean isEntityAt(IEntityType type, IDirection direction) {
		return m_entity.superposedWith(type) != null;
	}

}
