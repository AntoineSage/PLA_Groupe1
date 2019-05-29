/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.ricm3.game.purgatoire;

import edu.ricm3.game.GameModel;
import ricm3.interpreter.State;

public class Model extends GameModel {
	Entity hero;
	
	long lastTimeSinceAutomatonStep;
	
	public Model() {
		Aut aut = new Aut();
		State state1 = new State();
		State state2 = new State();
		State state3 = new State();
		
		hero = new Entity(30, 30, 3, 3, aut);
		lastTimeSinceAutomatonStep = 0;
	}

	@Override
	public void step(long now) {
		long elapsed = now - lastTimeSinceAutomatonStep;
		if(elapsed > 1000) {
			hero.m_automaton.step();
			lastTimeSinceAutomatonStep = now;
		}
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
	}
}
