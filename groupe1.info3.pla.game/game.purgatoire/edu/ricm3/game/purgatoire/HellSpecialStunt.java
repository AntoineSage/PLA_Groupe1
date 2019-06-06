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
		isPlayer.addKarma(m_entity);
		isPlayer.m_HP --;
		System.out.println("pop flaque");
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz flaque");
	}
	void step() {
		isPlayer = (Player)m_entity.superposedWith(IEntityType.PLAYER);
		if(isPlayer != null) {
			this.pop(IDirection.SOUTH);
			this.wizz(IDirection.SOUTH);
		}
			
	}

}
