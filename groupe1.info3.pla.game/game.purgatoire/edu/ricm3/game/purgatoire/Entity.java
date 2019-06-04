package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;

public class Entity {
	int m_HP, m_maxHP;
	int m_DMG;
	int m_karmaToGive;
	Stunt m_heavenStunt, m_hellStunt, m_currentStunt;
	IAutomaton m_automaton;
	Level m_level;
	Color m_c;
	Rectangle m_bounds;
	BufferedImage m_sprite;

	Entity() {

	}

	Entity(int HP, int maxHP, int DMG, int karmaToGive, Stunt heavenStunt, Stunt hellStunt, Stunt currentStunt,
			Level level) {
		m_HP = HP;
		m_maxHP = maxHP;
		m_DMG = DMG;
		m_heavenStunt = heavenStunt;
		m_hellStunt = hellStunt;
		m_currentStunt = currentStunt;
		m_level = level;
		m_c = m_currentStunt.m_c;
		m_automaton = m_currentStunt.m_automaton;
		m_bounds = m_currentStunt.m_bounds;
		m_sprite = m_currentStunt.m_sprite;
	}

	void transfrom() {

	}

	WorldType getWorldType() {
		return m_level.getWorldType();
	}

	void hit() {
		m_currentStunt.hit();
	}

	void move() {
		m_currentStunt.move();
	}

	void pop() {
		m_currentStunt.pop();
	}

	void wizz() {
		m_currentStunt.wizz();
	}

	void egg() {
		m_currentStunt.egg();
	}
}
