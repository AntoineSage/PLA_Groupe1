package edu.ricm3.game.purgatoire.stunts;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.Timer;
import edu.ricm3.game.purgatoire.entities.Entity;
import edu.ricm3.game.purgatoire.entities.Player;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HeavenSpecialStunt extends Stunt {

	private Timer m_heavenSpecialTimer;

	public HeavenSpecialStunt() {
		super(Singleton.getNewSpecialHeavenAut(),
				new AnimationPlayer(Singleton.getSpecialHeavenAnim(), AnimType.IDLE, 2), Options.HEAVEN_SPCL_HP_MAX,
				Options.HEAVEN_SPCL_DMG, Options.HEAVEN_SPCL_KARMA_TOGIVE);
	}

	@Override
	public void pop(IDirection d) {
		Player player = (Player) m_entity.superposedWith(IEntityType.PLAYER);
		if (m_heavenSpecialTimer == null) {
			m_heavenSpecialTimer = new Timer(5000);
			m_heavenSpecialTimer.start();
		}
		if (player != null) {
			if (Options.ECHO_PLAYER_ON_SPCL)
				System.out.println("Player sur chat");
			player.addKarma(m_entity);
		}
		if (Options.ECHO_POP_SPECIAL)
			System.out.println("Chat pop (give karma)");
	}

	@Override
	public void wizz(IDirection d) {
		m_entity.m_transparency = m_heavenSpecialTimer.getRemainingTimePercentage();
		if (Options.ECHO_WIZZ_SPECIAL)
			System.out.println("Wizz heaven (transparency) cat");
	}

	@Override
	public void takeDamage(int DMG) {
	}

	@Override
	public void takeDamage(Entity e) {
	}

	@Override
	public void step(long now) {
		super.step(now);
		if (m_heavenSpecialTimer != null) {
			m_heavenSpecialTimer.step(now);
			if (m_heavenSpecialTimer.isFinished()) {
				m_entity.die();
			} else
				this.wizz(null);
		}
	}

}
