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

public class HellSoulStunt extends Stunt {

	private long lastUpdate;
	private Player isPlayer;

	public HellSoulStunt() {
		super(Singleton.getNewSoulHellAut(), new AnimationPlayer(Singleton.getSoulHellAnim(), AnimType.IDLE, 16),
				Options.HELL_SOUL_HP_MAX, Options.HELL_SOUL_DMG, Options.HELL_SOUL_KARMA_TOGIVE);
	}
	
	public HellSoulStunt(IAutomaton automaton) {
		super(automaton, new AnimationPlayer(Singleton.getSoulHellAnim(), AnimType.IDLE, 16),
				Options.HELL_SOUL_HP_MAX, Options.HELL_SOUL_DMG, Options.HELL_SOUL_KARMA_TOGIVE);
	}

	public HellSoulStunt(boolean unlimitedRange) {
		super(Singleton.getNewUnlimitedRangeFollow(), new AnimationPlayer(Singleton.getSoulHellAnim(), AnimType.IDLE, 16),
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
			System.out.println("Wizz hell (transparency) soul");
	}

	@Override
	public void hit(IDirection d) {
		System.out.println("hit hell soul");
	}

	@Override
	public void takeDamage(Entity e) {
		m_entity.addHP(-(int) (m_weaknessBuff * e.m_currentStunt.getDMG()));
		if (m_entity.m_HP <= 0) {
			if (Options.ECHO_DIE)
				System.out.println("Soul dies");
			if (e instanceof Player) {
				isPlayer = (Player) e;
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
	public boolean isInRange(IEntityType targetType) {
		Iterator<Entity> iter;
		int range = 15;
		int x_rangeMax = m_entity.m_bounds.x + m_entity.m_bounds.width + range;
		int x_rangeMin = m_entity.m_bounds.x - range;
		int y_rangeMax = m_entity.m_bounds.y + m_entity.m_bounds.width + range;
		int y_rangeMin = m_entity.m_bounds.y - range;

		if (x_rangeMin < 0)
			x_rangeMin = 0;
		if (x_rangeMax >= Options.LVL_WIDTH)
			x_rangeMax = Options.LVL_WIDTH ;
		if (y_rangeMin < 0)
			y_rangeMin = 0;
		if (y_rangeMax >= Options.LVL_HEIGHT)
			y_rangeMax = Options.LVL_HEIGHT ;

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
