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
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Rectangle;
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

public class Controller extends GameController implements ActionListener {

	static class GraphicUI extends Canvas {
		private static final long serialVersionUID = -4828379213574397971L;
		private Color m_color;
		private View m_view;
		private Rectangle m_rect;

		public GraphicUI(Color c, View v, int x, int y, int w, int h) {
			m_color = c;
			m_view = v;
			m_rect = new Rectangle(x, y, w, h);
			m_view.addGraphicUI(this);
			setPreferredSize(new Dimension(w, h));
		}

		@Override
		public void paint(Graphics g) {
			g.setColor(m_color);
			g.fillRect(m_rect.x, m_rect.y, m_rect.width, m_rect.height);
		}

		public void repaint(int val) {
			m_rect.y = val;
			repaint();
		}

	}

	Model m_model;
	View m_view;
	Queue<KeyEvent> m_allKeyPressed;
	public GraphicUI m_karmaBar, m_HPBar, m_XPBar, m_periodCircle;
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

		Container karmaBar = new Container();
		karmaBar.setLayout(new GridBagLayout());

		m_periodLabel = new Label();
		m_periodLabel.setAlignment(Label.CENTER);
		m_karmaBar = new GraphicUI(Color.orange, m_view, 0, 0, 30, 150);
		m_karmaLabel = new Label();
		m_karmaLabel.setAlignment(Label.CENTER);

		westInside.add(m_periodLabel);
		karmaBar.add(m_karmaBar);
		westInside.add(karmaBar);
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

		m_HPBar = new GraphicUI(Color.red, m_view, 100, 200, 30, 150);
		m_HPLabel = new Label();
		m_HPLabel.setAlignment(Label.CENTER);
		m_XPBar = new GraphicUI(Color.blue, m_view, 500, 500, 30, 150);
		m_XPLabel = new Label();
		m_XPLabel.setAlignment(Label.CENTER);
		m_rankLabel = new Label();
		m_rankLabel.setAlignment(Label.CENTER);
		m_totalTimeLabel = new Label();
		m_totalTimeLabel.setAlignment(Label.CENTER);
		m_totalDistanceLabel = new Label();
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
		updateUI();
	}

	public void updateUI() {
		m_periodLabel.setText(String.format("period: %.1f%n", (Options.TOTAL_PERIOD - m_model.m_period) / 1000));
		m_karmaLabel.setText("karma: " + m_model.m_player.getKarma());

		m_HPLabel.setText("HP: " + m_model.m_player.getHP() + "/" + m_model.m_player.getMaxHP());
		m_XPLabel.setText("XP: " + m_model.m_player.getXP() + "/" + m_model.m_player.getMaxXP());
		m_rankLabel.setText("rank: " + m_model.m_player.getRank());
		m_totalTimeLabel.setText("total time: " + m_model.m_totalTime + "s");
		m_totalDistanceLabel.setText("total distance: " + m_model.m_totalDistance + "m");
	}

	@Override
	public void step(long now) {
		m_model.step(now);
		m_view.step(now);
		updateUI();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		m_allKeyPressed.add(e);
		if (e.getKeyCode() == KeyEvent.VK_A) {
			m_model.getPlayer().addKarma(+50);
		} else if (e.getKeyCode() == KeyEvent.VK_E) {
			m_model.getPlayer().addKarma(-50);
		}
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
