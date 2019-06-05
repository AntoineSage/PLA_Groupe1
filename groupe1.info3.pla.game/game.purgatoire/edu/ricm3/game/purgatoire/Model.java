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

import java.awt.Color;
import java.util.List;
import edu.ricm3.game.GameModel;
import ricm3.interpreter.IAutomaton;
import ricm3.parser.Ast;
import ricm3.parser.AutomataParser;
import ricm3.parser.Ast.AI_Definitions;

public class Model extends GameModel implements Transformable {
	WorldType m_wt;
	Level m_currentLevel, m_nextLevel;
	Player m_player;
	IAutomaton m_aut;
	// TODO lastTransform and transform() in Controller?

	long lastUpdate;
	
	public Model() {
		m_wt = WorldType.HEAVEN;
		m_currentLevel = new Level(this, Color.yellow);
		m_nextLevel = new Level(this, Color.pink);
		m_player = new Player(this, m_currentLevel, 24, Options.LVL_HEIGHT - 3, 3, 3);

		try {
			Ast ast = AutomataParser.from_file("ProtoPlayer.aut");
			List<IAutomaton> automatons = ((AI_Definitions) ast).make();
			m_aut = automatons.get(0);
			m_player.m_heavenStunt.m_automaton = m_aut;
			m_player.m_hellStunt.m_automaton = m_aut;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void step(long now) {
	}

	WorldType getWorld() {
		return m_wt;
	}

	public Player getPlayer() {
		return m_player;
	}

	void printWorld() {
		if (m_wt == WorldType.HEAVEN)
			System.out.println("Heaven");
		else
			System.out.println("Hell");
	}

	public void transform() {
		// TODO put world change in Controller?
//		if (m_wt == WorldType.HEAVEN)
//			if (m_player.m_karma < 0)
//				m_wt = WorldType.HELL;
//			else if (m_player.m_karma > 0)
//				m_wt =WorldType.HEAVEN;
		if (m_wt == WorldType.HEAVEN)
			m_wt = WorldType.HELL;
		else
			m_wt = WorldType.HEAVEN;
		m_player.transform();
		m_currentLevel.transform();
		m_nextLevel.transform();
	}

	public void step(long now, Controller controller) {
		if(now - lastUpdate > 1000 / 30) {
			lastUpdate = now;
			//m_player.step(now, controller);
		}
	}

	@Override
	public void shutdown() {
	}

	public WorldType getWorldType() {
		return m_wt;
	}

	void nextLevel() {
		m_currentLevel = m_nextLevel;
		m_nextLevel = new Level(this, Color.GREEN);
		m_player.nextLevel(m_currentLevel);
	}
}
