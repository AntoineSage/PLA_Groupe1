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

	public void wizz2() {

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
		if (m_entity.m_transparency == 1.0) {
			m_entity.m_transparency = (float) 0.1F;
			System.out.println("TANS" + m_entity.m_transparency);

		}

		else if (m_entity.m_transparency == 0.1F) {
			m_entity.m_transparency = (float) 1;
			System.out.println("TANS" + m_entity.m_transparency);
		}
		System.out.println("wizz hell soul");
	}

	@Override
	public void hit(IDirection d) {
		System.out.println("hit hell soul");
	}

	@Override
	public void takeDamage(Entity e) {
		System.out.println("Take DAMAGE" + m_entity.m_HP);
		System.out.println("DAMAGE " + e.m_currentStunt.getDMG());
		System.out.println("BUFF" + -(int) (m_weaknessBuff * e.m_currentStunt.getDMG()));
		m_entity.addHP(-(int) (m_weaknessBuff * e.m_currentStunt.getDMG()));
		if (m_entity.m_HP <= 0) {
			System.out.println("Soul is dying");
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

//		if (soulWizzTimer != null) {
//			soulWizzTimer.step(now);
//		}
		isPlayer = (Player) m_entity.superposedWith(IEntityType.PLAYER);
		if (isPlayer != null) {
			pop(isPlayer);
		}
		if (now - lastUpdate > 1000 / 15) {
			m_automaton.step(m_entity);
			lastUpdate = now;
		}
	}
}
