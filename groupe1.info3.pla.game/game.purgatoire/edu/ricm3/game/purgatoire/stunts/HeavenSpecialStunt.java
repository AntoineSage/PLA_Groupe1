package edu.ricm3.game.purgatoire.stunts;

import java.awt.Color;

import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.entities.Entity;
import edu.ricm3.game.purgatoire.entities.Player;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HeavenSpecialStunt extends Stunt {

	HeavenSpecialStunt(Entity entity) {
		super(Singleton.getNewSpecialHeavenAut(), entity, Color.ORANGE);
		m_maxHP = Options.HEAVEN_SPCL_HP_MAX;
		setDMG(Options.HEAVEN_SPCL_DMG);
		m_karmaToGive = Options.HEAVEN_SPCL_KARMA_TOGIVE;
	}

	public HeavenSpecialStunt() {
		super(Singleton.getNewSpecialHeavenAut(), null, Color.ORANGE);
		m_karmaToGive = Options.HEAVEN_SPCL_KARMA_TOGIVE;
	}

	@Override
	public void pop(IDirection d) {
		Player player = (Player) m_entity.superposedWith(IEntityType.PLAYER);
		if (player != null) {
			System.out.println("sur chat");
			player.addKarma(m_entity);
		}
		System.out.println("pop cat");
	}

	@Override
	public void wizz(IDirection d) {
		System.out.println("wizz cat");
	}

	@Override
	public void takeDamage(int DMG) {
		System.out.println("takeDMG cat");
	}
}
