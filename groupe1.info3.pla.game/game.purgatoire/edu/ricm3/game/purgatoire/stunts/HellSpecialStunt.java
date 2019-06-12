package edu.ricm3.game.purgatoire.stunts;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.entities.Entity;
import edu.ricm3.game.purgatoire.Timer;
import edu.ricm3.game.purgatoire.entities.Player;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HellSpecialStunt extends Stunt {

	Timer m_hellSpecialTimer;

//	HellSpecialStunt(Entity entity) {
//		super(Singleton.getNewSpecialHellAut(), entity, Color.CYAN);
//		m_maxHP = Options.HELL_SPCL_HP_MAX;
//		setDMG(Options.HELL_SPCL_DMG);
//		m_karmaToGive = Options.HELL_SPCL_KARMA_TOGIVE;
//	}

	int m_hpToGive;

	public HellSpecialStunt() {
		super(Singleton.getNewSpecialHellAut(), new AnimationPlayer(Singleton.getSpecialHellAnim(), AnimType.IDLE, 2),
				Options.HELL_SPCL_HP_MAX, Options.HELL_SPCL_DMG, Options.HELL_SPCL_KARMA_TOGIVE);
		m_hpToGive = Options.HELL_SPCL_HP_TOGIVE;
	}

	@Override
	public void pop(IDirection d) {
		Player player = (Player) m_entity.superposedWith(IEntityType.PLAYER);
//		if (m_hellSpecialTimer == null) {
//			m_hellSpecialTimer = new Timer(Options.HELL_SPECIAL_TIMER);
//			m_hellSpecialTimer.start();
//		}
		if (player != null) {
			System.out.println("sur flaque");
			player.addKarma(m_entity);
			player.addHP(Options.HELL_SPCL_HP_TOGIVE);
			m_entity.takeDamage(1);
			this.wizz(d);
		}
		if (Options.ECHO_POP_SPECIAL)
			System.out.println("Flaque pop (give karma & HP)");
	}

	@Override
	public void wizz(IDirection d) {
		m_entity.m_transparency = ((float)m_entity.getHP()) / ((float)m_entity.getMaxHP()); 
		System.out.println("wizz flaque");
	}

//	@Override
//	public void takeDamage(int DMG) {
//		System.out.println("takeDMG flaque");
//	}
//
	@Override
	public void takeDamage(Entity e) {
		System.out.println("takeDMG flaque");
	}

	@Override
	public boolean isEntityAt(IEntityType type, IDirection direction) {
		return m_entity.superposedWith(type) != null;
	}

	@Override
	public void step(long now) {
		super.step(now);
//		if (m_hellSpecialTimer != null) {
//			m_hellSpecialTimer.step(now);
//			if (m_hellSpecialTimer.isFinished()) {
//				m_entity.die();
//			}
//		}

	}
}
