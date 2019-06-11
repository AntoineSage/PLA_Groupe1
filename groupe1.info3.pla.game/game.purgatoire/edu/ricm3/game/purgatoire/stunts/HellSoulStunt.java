package edu.ricm3.game.purgatoire.stunts;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
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
		super(Singleton.getNewSoulHellAut(), new AnimationPlayer(Singleton.getSoulHellAnim(), AnimType.IDLE, 2));
		setDMG(Options.HEAVEN_SOUL_DMG);
		m_karmaToGive = Options.HELL_SOUL_KARMA_TOGIVE;
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
			m_entity.pop(m_entity.m_direction);
		}
		if (now - lastUpdate > 1000 / 15) {
			m_automaton.step(m_entity);
			lastUpdate = now;
		}
	}
}
