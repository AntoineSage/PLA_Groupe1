package edu.ricm3.game.purgatoire;

public class Timer {

	private long m_previousNow;
	private long m_current;
	private long m_duration;

	public Timer(long duration) {
		m_current = 0;
		m_duration = duration;
	}

	public void start() {
		m_current = m_duration;
	}

	public void start(long duration) {
		m_duration = duration;
		m_current = m_duration;
	}

	public void step(long now) {
		if (m_previousNow == 0)
			m_previousNow = now;
		m_current = Math.max(0, m_current - (now - m_previousNow));
		m_previousNow = now;
	}

	public boolean isFinished() {
		return m_current <= 0;
	}

	public long getCurrent() {
		return m_current;
	}

	public void setDuration(long duration) {
		m_duration = duration;
	}

}
