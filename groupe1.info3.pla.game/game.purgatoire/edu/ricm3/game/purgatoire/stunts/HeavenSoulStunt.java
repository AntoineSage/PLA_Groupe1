package edu.ricm3.game.purgatoire.stunts;

import java.awt.Color;
import java.awt.image.BufferedImage;

import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.entities.Entity;
import edu.ricm3.game.purgatoire.entities.Player;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HeavenSoulStunt extends Stunt {

	Player isPlayer;

	Long lastUpdate;

	HeavenSoulStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
		m_maxHP = Options.HEAVEN_SOUL_HP_MAX;
		setDMG(Options.HEAVEN_SOUL_DMG);
		m_karmaToGive = Options.HEAVEN_SOUL_KARMA_TOGIVE;
	}

	public HeavenSoulStunt() {
		super(Singleton.getNewSoulHeavenAut(), null, Color.DARK_GRAY);
		lastUpdate = (long) 0;
		setDMG(Options.HEAVEN_SOUL_DMG);
		m_karmaToGive = Options.HEAVEN_SOUL_KARMA_TOGIVE;
	}

	@Override
	public void pop(IDirection d) {
		m_entity.die();
		System.out.println("pop heaven soul");
	}

	@Override
	public void wizz(IDirection d) {
		System.out.println("wizz heaven soul");
	}

	@Override
	public void hit(IDirection d) {
		System.out.println("hit heaven soul");
	}

	@Override
	public void egg() {
		System.out.println("egg heaven soul");
	}

	@Override
	public void step(long now) {
//		if (lastUpdate == null)
//			lastUpdate = now;
		isPlayer = (Player) m_entity.superposedWith(IEntityType.PLAYER);
		if (isPlayer != null) {
			isPlayer.addKarma(m_entity);
			System.out.println("GIVE DAMAGE");
			isPlayer.takeDamage(m_entity.m_currentStunt.getDMG());
			pop(m_entity.m_direction);
		}
		if (lastUpdate - now > 500) {
			m_automaton.step(m_entity);
			lastUpdate = now;
		}
	}

}
