package edu.ricm3.game.purgatoire;

import java.awt.image.BufferedImage;

import edu.ricm3.game.purgatoire.Animation.AnimType;

public class AnimationPlayer {
	private Animation m_animation;

	private AnimType m_type;
	private int m_position;
	private int m_framesPerStep;

	private int m_stepsSinceLastUpdate;
	private boolean m_playing;

	public AnimationPlayer(Animation animation, AnimType type, int framesPerStep) {
		m_animation = animation;
		m_type = type;
		m_framesPerStep = framesPerStep;
		m_playing = true;

	}

	public void pause() {
		m_playing = false;
		m_position = 0;
	}

	public void resume() {
		m_playing = true;
	}

	public void changeTo(AnimType type) {
		m_playing = true;
		if (m_type.getValue() != type.getValue()) {
			m_type = type;
			m_position = 0;
		}
	}

	public void step() {
		if (m_playing && m_stepsSinceLastUpdate > m_framesPerStep) {
			m_stepsSinceLastUpdate = 0;
			m_position = m_position + 1;
			if (m_position >= m_animation.length(m_type)) {
				m_position = 0;
				m_playing = false;
			}
		} else {
			m_stepsSinceLastUpdate++;
		}
	}

	public BufferedImage getSprite() {
		return m_animation.get(m_type, m_position);
	}
}
