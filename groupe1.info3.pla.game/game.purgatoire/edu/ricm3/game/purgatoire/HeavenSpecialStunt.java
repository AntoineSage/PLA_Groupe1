package edu.ricm3.game.purgatoire;

import java.awt.Color;

import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HeavenSpecialStunt extends Stunt {

	HeavenSpecialStunt(Entity entity) {
		super(Singleton.getNewSpecialHeavenAut(), entity, Color.ORANGE);
		m_maxHP = Options.HEAVEN_SPCL_HP_MAX;
		setDMG(Options.HEAVEN_SPCL_DMG);
	}

	HeavenSpecialStunt() {
		super(Singleton.getNewSpecialHeavenAut(), null, Color.ORANGE);
	}

	@Override
	void pop(IDirection d) {
		Player player = (Player) m_entity.superposedWith(IEntityType.PLAYER);
		if (player != null) {
			System.out.println("sur chat");
			player.addKarma(m_entity);
		}
		System.out.println("pop cat");
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz cat");
	}

	@Override
	void takeDamage(int DMG) {
		System.out.println("takeDMG cat");
	}
}
