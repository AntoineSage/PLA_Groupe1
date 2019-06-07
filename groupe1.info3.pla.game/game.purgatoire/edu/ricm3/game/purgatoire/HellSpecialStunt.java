package edu.ricm3.game.purgatoire;

import java.awt.Color;

import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HellSpecialStunt extends Stunt {
	
	
	HellSpecialStunt(Entity entity) {
		super(Singleton.getNewSpecialHellAut(), entity, Color.CYAN);
		m_maxHP = Options.HELL_SPCL_HP_MAX;
		m_DMG = Options.HELL_SPCL_DMG;
	}
	
	HellSpecialStunt() {
		super(Singleton.getNewSpecialHellAut(), null, Color.CYAN);
	}

	@Override
	void pop(IDirection d) {
		Player player =(Player)m_entity.superposedWith(IEntityType.PLAYER);
		if(player != null) {
			System.out.println("sur flaque");
			player.addKarma(m_entity);
			player.addHp(Options.HELL_SPCL_HP_TOGIVE);
		}
		System.out.println("pop flaque");
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz flaque");
	}
	@Override
	public void step(Entity e) {
		m_automaton.step(e);
	}

	@Override
	void getDamage(int DMG) {
	}
	
	@Override
	public boolean isEntityAt(IEntityType type, IDirection direction) {
		return m_entity.superposedWith(type) != null;
	}
}
