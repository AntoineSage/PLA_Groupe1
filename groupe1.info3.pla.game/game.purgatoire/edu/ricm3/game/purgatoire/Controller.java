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

import java.awt.Button;
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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.ricm3.game.GameController;
import edu.ricm3.game.purgatoire.Bars.HPBar;
import edu.ricm3.game.purgatoire.Bars.KarmaBar;
import edu.ricm3.game.purgatoire.Bars.TimeCircle;
import edu.ricm3.game.purgatoire.Bars.XPBar;

public class Controller extends GameController implements ActionListener {

	private Model m_model;
	private View m_view;
	private List<Integer> m_allKeyPressed;
	private Stack<Integer> m_directionKey;
	private HPBar m_HPBar;
	private XPBar m_XPBar;
	private KarmaBar m_karmaBar;
	private TimeCircle m_timeCircle;

	private Label m_totalTimeLabel, m_totalDistanceLabel, m_periodLabel;
	private Label m_karmaLabel, m_HPLabel, m_XPLabel, m_rankLabel;
	private Label m_cooldownLabel, m_DMGLabel, m_weaknessLabel;

	Music m_player;
	Button m_music;

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
		// West container

		JPanel west = new JPanel();
//		west.setLayout(new FlowLayout(FlowLayout.CENTER, 0, Options.UI_MARGIN));
		west.setLayout(new GridBagLayout());
		west.setPreferredSize(new Dimension(Options.UI_PANEL_SIZE, 0));

		JPanel westInside = new JPanel();
		westInside.setLayout(new BoxLayout(westInside, BoxLayout.Y_AXIS));
		westInside.setMinimumSize(new Dimension(200, 50));

		JPanel karma = new JPanel();
		karma.setLayout(new BoxLayout(karma, BoxLayout.X_AXIS));
		JPanel timeCircle = new JPanel();
		timeCircle.setLayout(new BoxLayout(timeCircle, BoxLayout.X_AXIS));

		m_periodLabel = new Label("", Label.CENTER);
		m_karmaBar = new KarmaBar(m_view, 0, 0, Options.UI_BAR_WIDTH, 2 * Options.UI_BAR_HEIGHT);
		m_karmaLabel = new Label("", Label.CENTER);
		m_timeCircle = new TimeCircle(m_view, 0, 0, Options.UI_CIRCLE_SIZE, Options.UI_CIRCLE_SIZE);
		m_totalTimeLabel = new Label("", Label.CENTER);
		m_totalDistanceLabel = new Label("", Label.CENTER);

		westInside.add(m_periodLabel);
		timeCircle.add(m_timeCircle);
		westInside.add(timeCircle);
		westInside.add(Box.createRigidArea(new Dimension(0, Options.UI_MARGIN)));
		karma.add(m_karmaBar);
		westInside.add(karma);
		westInside.add(m_karmaLabel);
		westInside.add(Box.createRigidArea(new Dimension(0, Options.UI_MARGIN)));
		westInside.add(m_totalTimeLabel);
		westInside.add(m_totalDistanceLabel);

		west.add(westInside);
//		westInside.add(Box.createHorizontalGlue());

		// East container

		JPanel east = new JPanel();
		east.setLayout(new GridBagLayout());
		east.setPreferredSize(new Dimension(Options.UI_PANEL_SIZE, 0));

		JPanel eastInside = new JPanel();
		eastInside.setLayout(new BoxLayout(eastInside, BoxLayout.Y_AXIS));

		JPanel bars = new JPanel();
		bars.setLayout(new BoxLayout(bars, BoxLayout.X_AXIS));
		JPanel HP = new JPanel();
		HP.setLayout(new BoxLayout(HP, BoxLayout.Y_AXIS));
		JPanel XP = new JPanel();
		XP.setLayout(new BoxLayout(XP, BoxLayout.Y_AXIS));
		JPanel HPBar = new JPanel();
		HPBar.setLayout(new BoxLayout(HPBar, BoxLayout.X_AXIS));
		JPanel XPBar = new JPanel();
		XPBar.setLayout(new BoxLayout(XPBar, BoxLayout.X_AXIS));

		m_HPBar = new HPBar(m_view, 0, 0, Options.UI_BAR_WIDTH, Options.UI_BAR_HEIGHT);
		m_HPLabel = new Label("", Label.CENTER);
		m_XPBar = new XPBar(m_view, 0, 0, Options.UI_BAR_WIDTH, Options.UI_BAR_HEIGHT);
		m_XPLabel = new Label("", Label.CENTER);
		m_cooldownLabel = new Label("", Label.CENTER);
		m_rankLabel = new Label("", Label.CENTER);
		m_DMGLabel = new Label("", Label.CENTER);
		m_weaknessLabel = new Label("", Label.CENTER);

		HP.add(new Label("HP", Label.CENTER));
		HPBar.add(m_HPBar);
		HP.add(HPBar);
		HP.add(m_HPLabel);
		XP.add(new Label("XP", Label.CENTER));
		XPBar.add(m_XPBar);
		XP.add(XPBar);
		XP.add(m_XPLabel);
		bars.add(HP);
		bars.add(XP);

		eastInside.add(bars);
		eastInside.add(Box.createRigidArea(new Dimension(0, Options.UI_MARGIN)));
		eastInside.add(m_rankLabel);
		eastInside.add(m_cooldownLabel);
		eastInside.add(m_DMGLabel);
		eastInside.add(m_weaknessLabel);

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
		updateBuffsUI();
	}

	public void updateTimeUI() {
		m_periodLabel.setText(String.format("%.1f%ns", (Options.TOTAL_PERIOD - m_model.getPeriod()) / 1000));
		m_totalTimeLabel.setText(String.format("Total time: %.1f%ns", m_model.getTotalTime() / 1000));
		m_timeCircle.updateArcs(m_model.getPeriod());
	}

	public void updateKarmaUI() {
		m_karmaBar.updateHeights(m_model.getPlayer().getKarma(), m_model.getPlayer().getMaxKarma());
		m_karmaLabel.setText("Karma: " + m_model.getPlayer().getKarma());

		if (Options.ECHO_PLAYER_KARMA_CHANGE)
			System.out.println("Player new karma: " + m_model.getPlayer().getKarma());
	}

	public void updateHPUI() {
		m_HPBar.updateHeights(m_model.getPlayer().getHP(), m_model.getPlayer().getMaxHP(),
				m_model.getPlayer().getMaxTotalHP());
		m_HPLabel.setText(m_model.getPlayer().getHP() + "/" + m_model.getPlayer().getMaxHP());

		if (Options.ECHO_PLAYER_HP_CHANGE)
			System.out.println("Player new HP: " + m_model.getPlayer().getHP());
	}

	public void updateXPUI() {
		m_XPBar.updateHeights(m_model.getPlayer().getXP(), m_model.getPlayer().getMinXP(),
				m_model.getPlayer().getMaxXP());
		m_XPLabel.setText(m_model.getPlayer().getXP() + "/" + m_model.getPlayer().getMaxXP());

		if (Options.ECHO_PLAYER_XP_CHANGE)
			System.out.println("Player new XP: " + m_model.getPlayer().getXP());
	}

	public void updateRankUI() {
		updateHPUI();
		updateDMGUI();
		m_rankLabel.setText("Rank: " + (m_model.getPlayer().getRank() + 1) + " - " + m_model.getPlayer().getRankName());

		if (Options.ECHO_PLAYER_RANK_CHANGE)
			System.out.println("Player new rank: " + (m_model.getPlayer().getRank() + 1));
	}

	// TODO fix distance when changing world
	public void updateDistanceUI() {
		m_totalDistanceLabel.setText("Total distance: " + m_model.m_totalDistance / Options.PLAYER_SIZE + "m");
	}

	public void updateCooldownUI() {
		if (m_model.getWorldType() == WorldType.HEAVEN)
			m_cooldownLabel.setText(String.format("Pop: %.1f%ns", (float) m_model.getPlayer().getTimeLeftPop() / 1000));
		else
			m_cooldownLabel
					.setText(String.format("Wizz: %.1f%ns", (float) m_model.getPlayer().getTimeLeftWizz() / 1000));
	}

	public void updateBuffsUI() {
		updateDMGUI();
		m_weaknessLabel.setText("Damage taken: " + (int) (m_model.getPlayer().getWeakness() * 100) + "%");
	}

	public void updateDMGUI() {
		m_DMGLabel.setText("Damage: " + m_model.getPlayer().getDMG());
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

		if ((e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_NUMPAD1) && Options.CHEAT_MODE) {
			m_model.getPlayer().addKarma(+50);
		} else if ((e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_NUMPAD2) && Options.CHEAT_MODE) {
			m_model.getPlayer().addKarma(-50);
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			m_model.respawn();
		} else if (e.getKeyCode() == KeyEvent.VK_P) {
			m_model.switchPause();
		}

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
