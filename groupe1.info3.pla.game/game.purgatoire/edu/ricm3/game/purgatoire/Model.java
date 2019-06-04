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

import java.util.List;

import edu.ricm3.game.GameModel;
import ricm3.interpreter.IAutomaton;
import ricm3.parser.Ast;
import ricm3.parser.AutomataParser;
import ricm3.parser.Ast.AI_Definitions;

public class Model extends GameModel {
	WorldType m_wt;
	Level m_currentLevel, m_nextLevel;
	Player m_player;
	IAutomaton m_aut;

	public Model() {
		try {
			Ast ast = AutomataParser.from_file("ProtoPlayer.aut");
			List<IAutomaton> automatons = ((AI_Definitions) ast).make();
			m_aut = automatons.get(0);
			m_player = new Player();
			m_player.m_currentStunt = new Stunt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void transform() {
	}

	public void step(long now, Controller controller) {
		m_aut.step(m_player, controller);
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
	}

	@Override
	public void step(long now) {
		
	}
}
