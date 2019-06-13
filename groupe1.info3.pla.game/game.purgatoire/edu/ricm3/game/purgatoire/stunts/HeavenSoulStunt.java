package edu.ricm3.game.purgatoire.stunts;

import java.util.Iterator;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.entities.Entity;
import edu.ricm3.game.purgatoire.entities.Player;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class HeavenSoulStunt extends Stunt {

	private Player isPlayer;
	private long lastUpdate;

	public HeavenSoulStunt() {
		super(Singleton.getNewSoulHeavenAut(), new AnimationPlayer(Singleton.getSoulHeavenAnim(), AnimType.IDLE, 16),
				Options.HEAVEN_SOUL_HP_MAX, Options.HEAVEN_SOUL_DMG, Options.HEAVEN_SOUL_KARMA_TOGIVE);
		lastUpdate = (long) 0;
	}

	public HeavenSoulStunt(boolean unlimitedRange) {
		super(Singleton.getNewUnlimitedRangeFollow(),
				new AnimationPlayer(Singleton.getSoulHeavenAnim(), AnimType.IDLE, 16), Options.HEAVEN_SOUL_HP_MAX,
				Options.HEAVEN_SOUL_DMG, Options.HEAVEN_SOUL_KARMA_TOGIVE);
	}

	public HeavenSoulStunt(IAutomaton automaton) {
		super(automaton, new AnimationPlayer(Singleton.getSoulHeavenAnim(), AnimType.IDLE, 16),
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

	@Override
	public boolean isInRange(IEntityType targetType) {
		Iterator<Entity> iter;
		int range = 9;
		int x_rangeMax = m_entity.m_bounds.x + m_entity.m_bounds.width + range;
		int x_rangeMin = m_entity.m_bounds.x - range;
		int y_rangeMax = m_entity.m_bounds.y + m_entity.m_bounds.width + range;
		int y_rangeMin = m_entity.m_bounds.y - range;

		if (x_rangeMin < 0)
			x_rangeMin = 0;
		if (x_rangeMax >= Options.LVL_WIDTH)
			x_rangeMax = Options.LVL_WIDTH;
		if (y_rangeMin < 0)
			y_rangeMin = 0;
		if (y_rangeMax >= Options.LVL_HEIGHT)
			y_rangeMax = Options.LVL_HEIGHT;

		for (int i = x_rangeMin; i < x_rangeMax; i++) {
			for (int j = y_rangeMin; j < y_rangeMax; j++) {
				iter = m_entity.m_level.m_collisionGrid.get(i, j).iterator();
				while (iter.hasNext()) {
					Entity e = iter.next();
					if (e.m_type == targetType)
						return true;
				}
			}
		}
		return false;
	}

}
