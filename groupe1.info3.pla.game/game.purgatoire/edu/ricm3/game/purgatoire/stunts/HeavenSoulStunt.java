package edu.ricm3.game.purgatoire.stunts;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.entities.Entity;
import edu.ricm3.game.purgatoire.entities.Player;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HeavenSoulStunt extends Stunt {

	Player isPlayer;

	Long lastUpdate;

//	HeavenSoulStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
//		super(automaton, entity, sprite);
//		m_maxHP = Options.HEAVEN_SOUL_HP_MAX;
//		setDMG(Options.HEAVEN_SOUL_DMG);
//		m_karmaToGive = Options.HEAVEN_SOUL_KARMA_TOGIVE;
//	}

	public HeavenSoulStunt() {
		super(Singleton.getNewSoulHeavenAut(), new AnimationPlayer(Singleton.getSoulHeavenAnim(), AnimType.IDLE, 2),
				Options.HEAVEN_SOUL_HP_MAX, Options.HEAVEN_SOUL_DMG, Options.HEAVEN_SOUL_KARMA_TOGIVE);
		lastUpdate = (long) 0;
	}

	public void pop(Player p) {
		p.addKarma(m_entity);
		p.takeDamage(m_entity.m_currentStunt.getDMG());
		m_entity.die();
	}

	@Override
	public void pop(IDirection d) {
		isPlayer = (Player) m_entity.superposedWith(IEntityType.PLAYER);
		if (isPlayer != null) {
			pop(isPlayer);
		}
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
	public void takeDamage(Entity e) {
		m_entity.addHP(-(int) (m_weaknessBuff * e.m_currentStunt.getDMG()));
		if (e instanceof Player) {
			isPlayer = (Player) e;
			isPlayer.addKarma(m_entity);
		}
		if (m_entity.m_HP <= 0) {
			m_entity.die();
		}
	}

	@Override
	public void step(long now) {
//		if (lastUpdate == null)
//			lastUpdate = now;
		isPlayer = (Player) m_entity.superposedWith(IEntityType.PLAYER);
		if (isPlayer != null) {
			pop(isPlayer);
		}

		// TODO value in Options
		if (now - lastUpdate > 1000 / 15) {
			m_automaton.step(m_entity);
			lastUpdate = now;
		}
	}

}
