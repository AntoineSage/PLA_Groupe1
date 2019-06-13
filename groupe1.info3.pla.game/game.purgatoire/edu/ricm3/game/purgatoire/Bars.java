package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Bars {

	static class HPBar extends JComponent {

		private static final long serialVersionUID = -4828379213574397971L;
		private View m_view;
		private int m_x, m_y;
		private int m_maxTotalY, m_maxCurrentY, m_currentY;
		private int m_maxTotalHeight, m_maxCurrentHeight, m_currentHeight;

		public HPBar(View v, int x, int y, int w, int h) {
			m_view = v;
			m_x = x;
			m_y = y;
			m_view.addGraphicUI(this);
			m_maxTotalY = y;
			m_maxCurrentY = y;
			m_currentY = y;
			setForeground(Color.red);
			setLocation(x, y);
			setMinimumSize(new Dimension(w, h));
			setPreferredSize(new Dimension(w, h));
			setMaximumSize(new Dimension(w, h));
//			setVisible(true);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.fillRect(m_x, m_currentY, getWidth(), m_currentHeight);
			g.setColor(Color.white);
			g.fillRect(m_x, m_maxCurrentY, getWidth(), m_maxCurrentHeight);
			g.setColor(Color.gray);
			g.fillRect(m_x, m_maxTotalY, getWidth(), m_maxTotalHeight);
		}

		public void updateHeights(int current, int maxCurrent, int maxTotal) {
			m_maxTotalY = 0;
			m_maxCurrentY = (int) (((float) getHeight() / maxTotal) * (maxTotal - maxCurrent));
			m_currentY = (int) (((float) getHeight() / maxTotal) * (maxTotal - current));

			m_maxTotalHeight = m_maxCurrentY - m_maxTotalY;
			m_maxCurrentHeight = m_currentY - m_maxCurrentY;
			m_currentHeight = getHeight() - m_currentY;
		}

		@Override
		public int getX() {
			return m_x;
		}

		@Override
		public int getY() {
			return m_y;
		}

	}

	static class XPBar extends JComponent {

		private static final long serialVersionUID = -4828379213574397971L;
		private View m_view;
		private int m_x, m_y;
		private int m_maxY, m_currentY;
		private int m_maxHeight, m_currentHeight;

		public XPBar(View v, int x, int y, int w, int h) {
			m_view = v;
			m_x = x;
			m_y = y;
			m_view.addGraphicUI(this);
			m_maxY = y;
			m_currentY = y;
			setForeground(Color.blue);
			setLocation(x, y);
			setMinimumSize(new Dimension(w, h));
			setPreferredSize(new Dimension(w, h));
			setMaximumSize(new Dimension(w, h));
//			setVisible(true);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.fillRect(m_x, m_currentY, getWidth(), m_currentHeight);
			g.setColor(Color.white);
			g.fillRect(m_x, m_maxY, getWidth(), m_maxHeight);
		}

		public void updateHeights(int current, int min, int max) {
			m_maxY = 0;
			m_currentY = (int) (((float) getHeight() / (max - min)) * (max - current));

			m_maxHeight = m_currentY - m_maxY;
			m_currentHeight = getHeight() - m_currentY;
		}

		@Override
		public int getX() {
			return m_x;
		}

		@Override
		public int getY() {
			return m_y;
		}

	}

	static class KarmaBar extends JComponent {

		private static final long serialVersionUID = -4828379213574397971L;
		private View m_view;
		private int m_x, m_y;
		private int m_emptyY, m_karmaY, m_halfY;
		private int m_emptyHeight, m_karmaHeight;

		public KarmaBar(View v, int x, int y, int w, int h) {
			m_view = v;
			m_x = x;
			m_y = y;
			m_view.addGraphicUI(this);
			m_emptyY = y;
			m_karmaY = y;
			setForeground(Color.green);
			setLocation(x, y);
			setMinimumSize(new Dimension(w, h));
			setPreferredSize(new Dimension(w, h));
			setMaximumSize(new Dimension(w, h));
//			setVisible(true);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.fillRect(m_x, m_karmaY, getWidth(), m_karmaHeight);
			g.setColor(Color.white);
			g.fillRect(m_x, m_emptyY, getWidth(), m_emptyHeight);
			g.fillRect(m_x, m_halfY, getWidth(), getHeight() / 2);
			g.setColor(Color.gray);
			g.fillRect(m_x, getHeight() / 2, getWidth(), 1);
		}

		public void updateHeights(int current, int max) {
			if (current >= 0) {
				setForeground(new Color(104, 180, 255));
				m_karmaY = (int) (((float) getHeight() / (2 * max)) * (max - current));
				m_halfY = getHeight() / 2;
				m_emptyY = 0;
				m_emptyHeight = m_karmaY;
			} else {
				setForeground(new Color(255, 138, 138));
				m_karmaY = getHeight() / 2;
				m_halfY = 0;
				m_emptyY = (int) (((float) getHeight() / (2 * max)) * (-current) + (getHeight() / 2));
				m_emptyHeight = getHeight() - m_emptyY;
			}

			m_karmaHeight = (getHeight() / 2) - m_emptyHeight;
		}

		@Override
		public int getX() {
			return m_x;
		}

		@Override
		public int getY() {
			return m_y;
		}

	}

}
