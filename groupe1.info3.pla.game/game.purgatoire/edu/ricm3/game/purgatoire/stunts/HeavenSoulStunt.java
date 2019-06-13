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

	private Player isPlayer;
	private Long lastUpdate;

	public HeavenSoulStunt() {
		super(Singleton.getNewSoulHeavenAut(), new AnimationPlayer(Singleton.getSoulHeavenAnim(), AnimType.IDLE, 16),
				Options.HEAVEN_SOUL_HP_MAX, Options.HEAVEN_SOUL_DMG, Options.HEAVEN_SOUL_KARMA_TOGIVE);
		lastUpdate = (long) 0;
	}

	public HeavenSoulStunt(boolean unlimitedRange) {
		super(Singleton.getNewUnlimitedRangeFollow(), new AnimationPlayer(Singleton.getSoulHeavenAnim(), AnimType.IDLE, 16),
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
		if (Options.ECHO_POP_SOUL)
			System.out.println("Pop heaven (kamikaze) soul");
	}

	@Override
	public void wizz(IDirection d) {
		if (m_entity.m_transparency == 1.0) {
			m_entity.m_transparency = (float) 0.1F;
		}

		else if (m_entity.m_transparency == 0.1F) {
			m_entity.m_transparency = (float) 1;
		}
		if (Options.ECHO_WIZZ_SOUL)
			System.out.println("Wizz heaven (transparency) soul");
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
