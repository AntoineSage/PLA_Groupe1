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

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.ricm3.game.GameController;
import edu.ricm3.game.purgatoire.Bars.HPBar;
import edu.ricm3.game.purgatoire.Bars.KarmaBar;
import edu.ricm3.game.purgatoire.Bars.XPBar;

public class Controller extends GameController implements ActionListener {

	Model m_model;
	View m_view;
	// private long m_lastTransform;
	List<Integer> m_allKeyPressed;
	Stack<Integer> m_directionKey;
	HPBar m_HPBar, m_periodCircle;
	XPBar m_XPBar;
	KarmaBar m_karmaBar;

	Label m_totalTimeLabel, m_totalDistanceLabel, m_karmaLabel, m_HPLabel, m_XPLabel, m_rankLabel, m_periodLabel;
	Label m_cooldownLabel, m_cooldownWizzLabel;

	public Controller(Model model, View view) {
		m_model = model;
		m_view = view;
		m_allKeyPressed = new LinkedList<Integer>();
		m_directionKey = new Stack<Integer>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void notifyVisible() {
		// TODO center bars
		// TODO add Hell or Heaven Label

		// West container

		JPanel west = new JPanel();
//		west.setLayout(new FlowLayout(FlowLayout.CENTER, 0, Options.UI_MARGIN));
//		west.setLayout(new BoxLayout(west, BoxLayout.X_AXIS));
		west.setLayout(new GridBagLayout());
		west.setPreferredSize(new Dimension(Options.UI_PANEL_SIZE, 0));

		JPanel westInside = new JPanel();
		westInside.setLayout(new BoxLayout(westInside, BoxLayout.Y_AXIS));

		JPanel karmaBar = new JPanel();
		karmaBar.setLayout(new GridBagLayout());
		JPanel cooldown = new JPanel();
		cooldown.setLayout(new GridBagLayout());

		m_periodLabel = new Label("", Label.CENTER);
		m_karmaBar = new KarmaBar(m_view, 0, 0, Options.UI_BAR_WIDTH, 2 * Options.UI_BAR_HEIGHT);
		m_karmaLabel = new Label("", Label.CENTER);
		m_cooldownLabel = new Label("", Label.CENTER);
		m_cooldownWizzLabel = new Label("", Label.CENTER);

		westInside.add(m_periodLabel);
		karmaBar.add(m_karmaBar);
		westInside.add(karmaBar);
		westInside.add(m_karmaLabel);
		cooldown.add(m_cooldownLabel);
		westInside.add(cooldown);

		west.add(westInside);
//		west.add(Box.createHorizontalGlue());
//		west.add(Box.createRigidArea(new Dimension(Options.UI_HORIZONTAL_MARGIN, 0)));

		// East container

		JPanel east = new JPanel();
		east.setLayout(new GridBagLayout());
		east.setPreferredSize(new Dimension(Options.UI_PANEL_SIZE, 0));

		JPanel eastInside = new JPanel();
		eastInside.setLayout(new BoxLayout(eastInside, BoxLayout.Y_AXIS));

		JPanel eastBars = new JPanel();
		eastBars.setLayout(new BoxLayout(eastBars, BoxLayout.X_AXIS));
		JPanel HP = new JPanel();
		HP.setLayout(new BoxLayout(HP, BoxLayout.Y_AXIS));
		JPanel XP = new JPanel();
		XP.setLayout(new BoxLayout(XP, BoxLayout.Y_AXIS));
		JPanel HPBarContainer = new JPanel();
		HPBarContainer.setLayout(new GridBagLayout());
		JPanel XPBarContainer = new JPanel();
		XPBarContainer.setLayout(new GridBagLayout());

		m_HPBar = new HPBar(m_view, 0, 0, Options.UI_BAR_WIDTH, Options.UI_BAR_HEIGHT);
		m_HPLabel = new Label("", Label.CENTER);
		m_XPBar = new XPBar(m_view, 0, 0, Options.UI_BAR_WIDTH, Options.UI_BAR_HEIGHT);
		m_XPLabel = new Label("", Label.CENTER);
		m_rankLabel = new Label("", Label.CENTER);
		m_totalTimeLabel = new Label("", Label.CENTER);
		m_totalDistanceLabel = new Label("", Label.CENTER);

		HPBarContainer.add(m_HPBar);
		HP.add(HPBarContainer);
		HP.add(m_HPLabel);
		XPBarContainer.add(m_XPBar);
		XP.add(XPBarContainer);
		XP.add(m_XPLabel);
		eastBars.add(HP);
		eastBars.add(XP);

		eastInside.add(eastBars);
		eastInside.add(m_rankLabel);
		eastInside.add(m_totalTimeLabel);
		eastInside.add(m_totalDistanceLabel);

		east.add(eastInside);

		m_game.addWest(west);
		m_game.addEast(east);
		updateUI();
	}

	public void updateUI() {
		updateTimeUI();
		updateKarmaUI();
		updateHPUI();
		updateXPUI();
		updateRankUI();
		updateDistanceUI();
		updateCooldownUI();
	}

	public void updateTimeUI() {
		m_periodLabel.setText(String.format("period: %.1f%ns", (Options.TOTAL_PERIOD - m_model.m_period) / 1000));
		m_totalTimeLabel.setText(String.format("total time: %.1f%ns", m_model.m_totalTime / 1000));
	}

	public void updateKarmaUI() {
		m_karmaBar.updateHeights(m_model.getPlayer().getKarma(), m_model.getPlayer().getMaxKarma());
		m_karmaLabel.setText("karma: " + m_model.getPlayer().getKarma());

		if (Options.ECHO_PLAYER_KARMA_CHANGE)
			System.out.println("Player new karma: " + m_model.getPlayer().getKarma());
	}

	public void updateHPUI() {
		m_HPBar.updateHeights(m_model.getPlayer().getHP(), m_model.getPlayer().getMaxHP(),
				m_model.getPlayer().getMaxTotalHP());
		m_HPLabel.setText("HP: " + m_model.getPlayer().getHP() + "/" + m_model.getPlayer().getMaxHP());

		if (Options.ECHO_PLAYER_HP_CHANGE)
			System.out.println("Player new HP: " + m_model.getPlayer().getHP());
	}

	public void updateXPUI() {
		m_XPBar.updateHeights(m_model.getPlayer().getXP(), m_model.getPlayer().getMinXP(),
				m_model.getPlayer().getMaxXP());
		m_XPLabel.setText("XP: " + m_model.getPlayer().getXP() + "/" + m_model.getPlayer().getMaxXP());

		if (Options.ECHO_PLAYER_XP_CHANGE)
			System.out.println("Player new XP: " + m_model.getPlayer().getXP());
	}

	public void updateRankUI() {
		m_rankLabel.setText("rank: " + (m_model.getPlayer().getRank() + 1) + " - " + m_model.getPlayer().getRankName());

		if (Options.ECHO_PLAYER_RANK_CHANGE)
			System.out.println("Player new rank: " + (m_model.getPlayer().getRank() + 1));
	}

	// TODO fix distance when changing world
	public void updateDistanceUI() {
		m_totalDistanceLabel.setText("total distance: " + m_model.m_totalDistance / Options.PLAYER_SIZE + "m");
	}

	public void updateCooldownUI() {
		if (m_model.getWorldType() == WorldType.HEAVEN)
			m_cooldownLabel.setText(String.format("pop: %.1f%ns", (float) m_model.getPlayer().getTimeLeftPop() / 1000));
		else
			m_cooldownLabel
					.setText(String.format("wizz: %.1f%ns", (float) m_model.getPlayer().getTimeLeftWizz() / 1000));
	}

	@Override
	public void step(long now) {
		m_model.step(now);
		m_view.step(now);
		updateUI(); // TODO remove this call to updateUI
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (isDirectionKey(code)) {
			m_directionKey.push(code);
		} else {
			m_allKeyPressed.add(code);
		}

		if ((e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_NUMPAD1) && Options.CHEAT_MODE == true) {
			m_model.getPlayer().addKarma(+50);
		} else if ((e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_NUMPAD2) && Options.CHEAT_MODE == true) {
			m_model.getPlayer().addKarma(-50);
<<<<<<< Updated upstream
=======
		}else if((e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_NUMPAD1)&& Options.CHEAT_MODE == true) {
			//m_model.getPlayer()
>>>>>>> Stashed changes
		} else if (e.getKeyCode() == KeyEvent.VK_R)
			m_model.respawn();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		Iterator<Integer> iter = null;
		if (isDirectionKey(code)) {
			iter = m_directionKey.iterator();
		} else {
			iter = m_allKeyPressed.iterator();
		}

		while (iter.hasNext()) {
			int key = iter.next();
			if (key == code) {
				iter.remove();
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

	public boolean isKeyPressed(int code) {
		if (isDirectionKey(code)) {
			if (m_directionKey.size() > 0) {
				return code == m_directionKey.peek();
			}
		} else {
			Iterator<Integer> iter = m_allKeyPressed.iterator();
			while (iter.hasNext()) {
				int key = iter.next();
				if (key == code) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isDirectionKey(int e) {
		return e == KeyEvent.VK_Z | e == KeyEvent.VK_Q | e == KeyEvent.VK_S | e == KeyEvent.VK_D;
	}
}
