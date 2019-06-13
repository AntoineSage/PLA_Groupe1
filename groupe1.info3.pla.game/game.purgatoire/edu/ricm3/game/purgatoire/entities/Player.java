package edu.ricm3.game.purgatoire.entities;

import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.ricm3.game.purgatoire.Level;
import edu.ricm3.game.purgatoire.Model;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import edu.ricm3.game.purgatoire.WorldType;
import edu.ricm3.game.purgatoire.stunts.HeavenPlayerStunt;
import edu.ricm3.game.purgatoire.stunts.HellPlayerStunt;
import edu.ricm3.game.purgatoire.stunts.PlayerStunt;
import ricm3.interpreter.IEntityType;

public class Player extends Entity {
	private int m_maxTotalHP;
	private int m_karma, m_maxKarma;
	private int m_XP;
	private int m_rank;
	private Model m_model;

	public Player(Model model, Level level, int x, int y) {
		super(level, new HeavenPlayerStunt(), new HellPlayerStunt(), x, y, Options.PLAYER_SIZE);
		m_model = model;
		m_type = IEntityType.PLAYER;
		m_XP = 0;
		m_maxKarma = Options.PLAYER_KARMA_MAX;
		((PlayerStunt) m_currentStunt).updateRankStats();
	}

	public void addKarma(Entity e) {
		m_karma += e.m_currentStunt.getKarmaToGive();
		Singleton.getController().updateKarmaUI();
	}

	public void addKarma(int karma) {
		m_karma += karma;
		if (m_karma > getMaxKarma())
			m_karma = getMaxKarma();
		else if (m_karma < -getMaxKarma())
			m_karma = -getMaxKarma();
		Singleton.getController().updateKarmaUI();
	}

	public void updateRank() {
		if (m_XP >= getMaxXP() && getRank() < Options.PLAYER_MAX_RANK) {
			m_rank++;
			Singleton.getController().updateRankUI();
			if (m_rank == Options.PLAYER_MAX_RANK) {
				if (getWorldType() == WorldType.HEAVEN)
					endGameMenu("You are now GOD, you won! Do you want to try again?");
				else
					endGameMenu("You are now SATAN, you won! Do you want to try again?");
			}
		} else if (m_XP < getMinXP()) {
			m_rank--;
			Singleton.getController().updateRankUI();
		}

		int delta = getMaxTotalHP();
		((PlayerStunt) m_currentStunt).updateRankStats();
		delta = getMaxTotalHP() - delta;
		if (delta >= 0) {
			setMaxHP(m_currentStunt.getMaxHP() + delta);
			addHP(delta);
		} else {
			setMaxHP(Math.min(m_currentStunt.getMaxHP(), getMaxTotalHP()));
			setHP(Math.min(getHP(), m_currentStunt.getMaxHP()));
		}

		if (Options.ECHO_PLAYER_UPDATE_STATS)
			System.out.println("Update stats: " + delta + " delta, " + getMaxTotalHP() + " maxTotalHP, "
					+ m_currentStunt.getMaxHP() + " maxHP, " + getHP() + " HP, " + m_currentStunt.getBaseDMG()
					+ " baseDMG");
	}

	public void nextLevel(Level newLevel) {
		m_level = newLevel;
		m_bounds.y = Options.LVL_HEIGHT - m_bounds.height;
		m_level.addEntity(this);
	}

	public int getKarma() {
		return m_karma;
	}

	public int getMaxKarma() {
		return m_maxKarma;
	}

	public int getXP() {
		return m_XP;
	}

	public int getMinXP() {
		return Options.PLAYER_RANKS[m_rank];
	}

	public int getMaxXP() {
		return Options.PLAYER_RANKS[m_rank + 1];
	}

	public int getRank() {
		return m_rank;
	}

	public String getRankName() {
		return ((PlayerStunt) m_currentStunt).getRankName();
	}

	public void addXP(double coef) {
		m_XP += Math.abs(m_karma) * coef;
		m_XP = Math.max(m_XP, 0);
		if (getRank() == Options.PLAYER_MAX_RANK)
			m_XP = Math.min(getMaxXP(), m_XP);
		updateRank();
		Singleton.getController().updateXPUI();
	}

	@Override
	public void addHP(int HP) {
		super.addHP(HP);
		Singleton.getController().updateHPUI();
	}

	public int getMaxTotalHP() {
		return m_maxTotalHP;
	}

	public void setMaxTotalHP(int newMax) {
		m_maxTotalHP = newMax;
	}

	public void setHP(int HP) {
		m_HP = HP;
	}

	public void testKarma() {
		if (m_karma >= 0 && m_model.getWorldType() == WorldType.HEAVEN
				|| m_karma <= 0 && m_model.getWorldType() == WorldType.HELL) {
			addXP(Options.COEF_KARMA_POS);
		} else {
			addXP(Options.COEF_KARMA_NEG);
			m_model.transform();
		}
		m_karma = 0;
	}

	@Override
	public void die() {
		super.die();
		endGameMenu("You lost... Do you want to try again?");
	}

	// TODO high scores
	private void endGameMenu(String msg) {
		JFrame endFrame = new JFrame();
		endFrame.getContentPane().setLayout(new GridBagLayout());

		JPanel inside = new JPanel();
		inside.setLayout(new BoxLayout(inside, BoxLayout.Y_AXIS));
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

		inside.add(new Label(msg, Label.CENTER));

		JButton tryAgain = new JButton("Try again");
		tryAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getModel().respawn();
				endFrame.dispatchEvent(new WindowEvent(endFrame, WindowEvent.WINDOW_CLOSING));
			}
		});
		buttons.add(tryAgain);

		JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttons.add(quit);

		inside.add(buttons);

		endFrame.add(inside);
		endFrame.pack();
		endFrame.setLocationRelativeTo(null);
		endFrame.setVisible(true);
	}

}
