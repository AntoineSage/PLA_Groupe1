package edu.ricm3.game.purgatoire;

public class Timer {
	public long m_previousNow;
	long m_coolDown;

	public Timer(long coolDown) {
		m_coolDown = coolDown;
	}

	public void start(long coolDown) {
		m_coolDown = coolDown;
	}

	public void step(long now) {
		m_coolDown -= now - m_previousNow;
		m_previousNow = now;
	}

	public boolean end() {
		if (m_coolDown <= 0)
			return true;
		return false;
	}
}
