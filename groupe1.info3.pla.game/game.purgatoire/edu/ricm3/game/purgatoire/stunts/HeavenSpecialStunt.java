package edu.ricm3.game.purgatoire.stunts;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.Timer;
import edu.ricm3.game.purgatoire.entities.Player;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HeavenSpecialStunt extends Stunt {

	Timer m_heavenSpecialTimer;

//	HeavenSpecialStunt(Entity entity) {
//		super(Singleton.getNewSpecialHeavenAut(), entity, Color.ORANGE);
//		m_maxHP = Options.HEAVEN_SPCL_HP_MAX;
//		setDMG(Options.HEAVEN_SPCL_DMG);
//		m_karmaToGive = Options.HEAVEN_SPCL_KARMA_TOGIVE;
//	}

	public HeavenSpecialStunt() {
		super(Singleton.getNewSpecialHeavenAut(),
				new AnimationPlayer(Singleton.getSpecialHeavenAnim(), AnimType.IDLE, 2), Options.HEAVEN_SPCL_HP_MAX,
				Options.HEAVEN_SPCL_DMG, Options.HEAVEN_SPCL_KARMA_TOGIVE);
	}

	@Override
	public void pop(IDirection d) {
		Player player = (Player) m_entity.superposedWith(IEntityType.PLAYER);
		if (m_heavenSpecialTimer == null)
			m_heavenSpecialTimer = new Timer(5000);
		m_heavenSpecialTimer.start();
		if (player != null) {
			player.addKarma(m_entity);
			System.out.println("sur chat");

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

	@Override
	public void step(long now) {
		super.step(now);
		if (m_heavenSpecialTimer != null) {
			m_heavenSpecialTimer.step(now);
			if (m_heavenSpecialTimer.isFinished()) {
				m_entity.die();
			}
		}
	}
}
