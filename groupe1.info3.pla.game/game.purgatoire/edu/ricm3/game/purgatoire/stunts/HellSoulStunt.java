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

public class HellSoulStunt extends Stunt {

	long lastUpdate;

	Player isPlayer;

	HellSoulStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
		m_maxHP = Options.HELL_SOUL_HP_MAX;
		setDMG(Options.HELL_SOUL_DMG);
	}

	public HellSoulStunt() {
		super(Singleton.getNewSoulHellAut(), null, Color.green);
		setDMG(Options.HEAVEN_SOUL_DMG);
	}

	@Override
	public void pop(IDirection d) {
		m_entity.die();
	}

	@Override
	public void wizz(IDirection d) {
		System.out.println("wizz hell soul");
	}

	@Override
	public void hit(IDirection d) {
		System.out.println("hit hell soul");
	}

	@Override
	public void egg() {
		System.out.println("egg hell soul");
	}

	@Override
	public void step(long now) {
		isPlayer = (Player) m_entity.superposedWith(IEntityType.PLAYER);
		if (isPlayer != null) {
			System.out.println("GIVE DAMAGE");
			isPlayer.takeDamage(m_entity.m_currentStunt.getDMG());
			m_entity.takeDamage(m_entity.m_HP);
		}
		if (now - lastUpdate > 500) {
			m_automaton.step(m_entity);
			lastUpdate = now;
		}
	}
}
