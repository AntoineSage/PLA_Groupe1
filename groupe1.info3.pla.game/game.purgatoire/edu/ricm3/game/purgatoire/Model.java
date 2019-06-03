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
import edu.ricm3.game.purgatoire.Entity.Direction;
import ricm3.interpreter.IAutomaton;
import ricm3.parser.Ast;
import ricm3.parser.Ast.AI_Definitions;
import ricm3.parser.Ast.Automaton;

public class Model extends GameModel {
	public Entity m_hero;
	
	long lastTimeSinceAutomatonStep; 
	
	public Model() {
		m_hero = new Entity(30, 30, 3, 3, Direction.NORD);
		
		try {
			AI_Definitions ast = (AI_Definitions) ricm3.parser.AutomataParser.from_file("test");
			IAutomaton aut = ast.automata.get(0).make();
			m_hero.setAutomaton(aut);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lastTimeSinceAutomatonStep = 0;
	}

	@Override
	public void step(long now) {
		long elapsed = now - lastTimeSinceAutomatonStep;
		if(elapsed > 1000) {
			m_hero.m_automaton.step(m_hero);
			lastTimeSinceAutomatonStep = now;
		}
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
	}
}
