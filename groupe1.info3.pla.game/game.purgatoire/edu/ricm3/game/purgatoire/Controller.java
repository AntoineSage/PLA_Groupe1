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

import java.awt.Canvas;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.Box;
import javax.swing.BoxLayout;

import edu.ricm3.game.GameController;
import ricm3.interpreter.IDirection;

public class Controller extends GameController implements ActionListener {

	Model m_model;
	View m_view;
	private long m_lastTransform;
	Queue<KeyEvent> m_allKeyPressed;
	Canvas m_karmaBar, m_HPBar, m_XPBar, m_periodCircle;
	Label m_totalTimeLabel, m_totalDistanceLabel, m_karmaLabel, m_HPLabel, m_XPLabel, m_rankLabel, m_periodLabel;

	public Controller(Model model, View view) {
		m_model = model;
		m_view = view;
		m_allKeyPressed = new LinkedList<KeyEvent>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void notifyVisible() {
		// West container

		Container west = new Container();
//		west.setLayout(new FlowLayout(FlowLayout.CENTER, 0, Options.UI_MARGIN));
//		west.setLayout(new BoxLayout(west, BoxLayout.X_AXIS));
		west.setLayout(new GridBagLayout());
		west.setPreferredSize(new Dimension(Options.UI_PANEL_SIZE, 0));

		Container westInside = new Container();
		westInside.setLayout(new BoxLayout(westInside, BoxLayout.Y_AXIS));

		m_periodLabel = new Label("period: " + m_model.m_period + "s");
		m_periodLabel.setAlignment(Label.CENTER);
		m_karmaBar = new Canvas();
		m_karmaLabel = new Label("karma: " + m_model.m_player.getKarma());
		m_karmaLabel.setAlignment(Label.CENTER);

		westInside.add(m_periodLabel);
		westInside.add(m_karmaBar);
		westInside.add(m_karmaLabel);

		west.add(westInside);
//		west.add(Box.createHorizontalGlue());
//		west.add(Box.createRigidArea(new Dimension(Options.UI_HORIZONTAL_MARGIN, 0)));

		// East container

		Container east = new Container();
		east.setLayout(new GridBagLayout());
		east.setPreferredSize(new Dimension(Options.UI_PANEL_SIZE, 0));

		Container eastInside = new Container();
		eastInside.setLayout(new BoxLayout(eastInside, BoxLayout.Y_AXIS));

		Container bars = new Container();
		bars.setLayout(new BoxLayout(bars, BoxLayout.X_AXIS));
		Container HP = new Container();
		HP.setLayout(new BoxLayout(HP, BoxLayout.Y_AXIS));
		Container XP = new Container();
		XP.setLayout(new BoxLayout(XP, BoxLayout.Y_AXIS));

		m_HPBar = new Canvas();
		m_HPLabel = new Label("HP: " + m_model.m_player.getHP() + "/" + m_model.m_player.getMaxHP());
		m_HPLabel.setAlignment(Label.CENTER);
		m_XPBar = new Canvas();
		m_XPLabel = new Label("XP: " + m_model.m_player.getXP() + "/" + m_model.m_player.getMaxXP());
		m_XPLabel.setAlignment(Label.CENTER);
		m_rankLabel = new Label("rank: " + m_model.m_player.getRank());
		m_rankLabel.setAlignment(Label.CENTER);
		m_totalTimeLabel = new Label("total time: " + m_model.m_totalTime + "s");
		m_totalTimeLabel.setAlignment(Label.CENTER);
		m_totalDistanceLabel = new Label("total distance: " + m_model.m_totalDistance + "m");
		m_totalDistanceLabel.setAlignment(Label.CENTER);

		HP.add(m_HPBar);
		HP.add(m_HPLabel);
		XP.add(m_XPBar);
		XP.add(m_XPLabel);
		bars.add(HP);
		bars.add(XP);

		eastInside.add(bars);
		eastInside.add(m_rankLabel);
		eastInside.add(m_totalTimeLabel);
		eastInside.add(m_totalDistanceLabel);

		east.add(Box.createRigidArea(new Dimension(Options.UI_MARGIN, 0)));
		east.add(eastInside);
		east.add(Box.createRigidArea(new Dimension(Options.UI_MARGIN, 0)));

		m_game.addWest(west);
		m_game.addEast(east);
	}

	@Override
	public void step(long now) {
		if (m_lastTransform == 0)
			m_lastTransform = now;
		if (now - m_lastTransform > 5000) {
			m_model.transform();
			m_view.transform();
			m_model.printWorld();
			m_model.getPlayer().pop();
			m_model.getPlayer().wizz();
			m_model.getPlayer().hit(m_model.m_player.m_direction);
			m_model.getPlayer().egg();
			m_lastTransform = now;
		}
		m_model.step(now, this);
		m_view.step(now);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		m_allKeyPressed.add(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Iterator<KeyEvent> iter = m_allKeyPressed.iterator();
		while (iter.hasNext()) {
			KeyEvent key = iter.next();
			if (key.getKeyCode() == e.getKeyCode()) {
				iter.remove();
				// m_allKeyPressed.remove(key);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	public boolean isKeyPressed(int e) {
		Iterator<KeyEvent> iter = m_allKeyPressed.iterator();
		while (iter.hasNext()) {
			KeyEvent key = iter.next();
			if (key.getKeyCode() == e) {
				return true;
			}
		}
		return false;
	}
}
