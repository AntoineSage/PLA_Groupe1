package edu.ricm3.game.purgatoire;

import java.awt.Color;

import ricm3.interpreter.IDirection;

public class HellPlayerStunt extends Stunt {

	HellPlayerStunt(Entity entity) {
		super(Singleton.getNewPlayerHellAut(), entity, Color.RED);
		m_maxHP = Options.HELL_PLAYER_HP_MAX;
		m_DMG = Options.HELL_PLAYER_DMG;
	}

	@Override
	void pop(IDirection d) {
		System.out.println("pop hell");
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz hell");
	}

	@Override
	void hit(IDirection d) {
		System.out.println("hit hell");
	}

	@Override
	void egg() {
		System.out.println("egg hell");
	}

	@Override
	void getDamage(int DMG) {
		System.out.println("getDamage hell");
	}
}
