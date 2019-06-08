package edu.ricm3.game.purgatoire;

import java.awt.Color;

import ricm3.interpreter.IDirection;

public class HellPlayerStunt extends Stunt {

	int m_lastPopPeriod;
	int m_nbrPeriod;
	int m_DMGBuffRatio = Options.BUFF_DMG;
	int m_weaknessBuffRatio = Options.BUFF_WEAKNESS;

	Timer m_buffTimer;

	HellPlayerStunt(Entity entity) {
		super(Singleton.getNewPlayerHellAut(), entity, Color.RED);
		m_maxHP = Options.HELL_PLAYER_HP_MAX;
		setDMG(Options.HELL_PLAYER_DMG);
		m_buffTimer = new Timer(m_durationBuff * 1000);
	}

	@Override
	void pop(IDirection d) {
		m_nbrPeriod = (int) m_entity.m_level.m_model.m_totalTime/Options.TOTAL_PERIOD;
		if (m_nbrPeriod != m_lastPopPeriod) {
			buff(m_DMGBuffRatio, m_weaknessBuffRatio);
			m_lastPopPeriod = m_nbrPeriod;
			m_buffTimer.start(m_durationBuff * 1000);
		}
		System.out.println("pop hell");
	}

	@Override
	void wizz(IDirection d) {
		System.out.println("wizz hell");
	}

	@Override
	void hit(IDirection d) {
		System.out.println("hit hell");
	}

	@Override
	void egg() {
		System.out.println("egg hell");
	}

	@Override
	public void step(long now) {
		super.step(now);
		if (m_buffTimer.m_previousNow == 0)
			m_buffTimer.m_previousNow = now;
		if (m_buffTimer.end()) {
			m_DMGBuff = 1;
			m_weaknessBuff = 1;
		}
		m_buffTimer.step(now);
	}
}
