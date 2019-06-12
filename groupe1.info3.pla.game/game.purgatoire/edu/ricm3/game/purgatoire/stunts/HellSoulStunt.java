package edu.ricm3.game.purgatoire.stunts;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.entities.Entity;
import edu.ricm3.game.purgatoire.entities.Missile;
import edu.ricm3.game.purgatoire.entities.Player;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HellSoulStunt extends Stunt {

	long lastUpdate;

	Player isPlayer;

//	HellSoulStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
//		super(automaton, entity, sprite);
//		m_maxHP = Options.HELL_SOUL_HP_MAX;
//		setDMG(Options.HELL_SOUL_DMG);
//		m_karmaToGive = Options.HELL_SOUL_KARMA_TOGIVE;
//	}

	public HellSoulStunt() {
		super(Singleton.getNewSoulHellAut(), new AnimationPlayer(Singleton.getSoulHellAnim(), AnimType.IDLE, 2),
				Options.HELL_SOUL_HP_MAX, Options.HELL_SOUL_DMG, Options.HELL_SOUL_KARMA_TOGIVE);

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
		if (Options.ECHO_POP_SOUL)
			System.out.println("Soul hell pop (kamikaze)");
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
	public void takeDamage(Entity e) {
		m_entity.addHP(-(int) (m_weaknessBuff * e.m_currentStunt.getDMG()));
		if (m_entity.m_HP <= 0) {
			if (e instanceof Missile) {
				System.out.println("");
				isPlayer = (Player) ((Missile) e).getOwner();
				isPlayer.addKarma(m_entity);
			}
			m_entity.die();
		}
	}

	@Override
	public void egg() {
		System.out.println("egg hell soul");
	}

	@Override
	public void step(long now) {
		isPlayer = (Player) m_entity.superposedWith(IEntityType.PLAYER);
		if (isPlayer != null) {
			pop(isPlayer);
		}
		if (now - lastUpdate > Options.SOUL_STEP_DELAY) {
			super.step(now);
			lastUpdate = now;
		}
	}

}
