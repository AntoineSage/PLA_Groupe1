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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameView;
import edu.ricm3.game.purgatoire.entities.Entity;

public class View extends GameView {
	private static final long serialVersionUID = 1L;

	private Model m_model;
	private int m_yG1; // position de g1 par rapport à g
	private int BLOCK_SIZE = (Options.WIN_WIDTH) / Options.LVL_WIDTH;
	private int NB_BLOCKS_WIN = Options.WIN_HEIGHT / BLOCK_SIZE;
	private List<Component> m_graphicUIs;

	long m_last;
	int m_npaints;
	int m_fps;

	private static BufferedImage m_heavenBackground;
	private static BufferedImage m_heavenBackground2;
	private static BufferedImage m_hellBackground;
	private static BufferedImage m_currentBackground;
	private static BufferedImage m_currentBackground2;

	private Color grey;

	public View(Model m) {
		grey = Options.PRIMARY_BACKGROUND;
		m_model = m;

		// ecart entre g1 et g, est négatif quand g1 n'est pas dans g
		m_yG1 = -(Options.LVL_HEIGHT - NB_BLOCKS_WIN);

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent ce) {
				if (Options.ECHO_WINDOW_SIZE_CHANGE)
					System.out.printf("%d %d\n", getWidth(), getHeight());
				Options.WIN_HEIGHT = getHeight();

				int tmp = getWidth();
				Options.WIN_WIDTH = tmp == 0 ? Options.WIN_WIDTH : tmp;

				float borderSize = (float) (65.0f * (float) getWidth() / 765.0f);
				BLOCK_SIZE = (int) (((float) getWidth() - 2.0f * borderSize) / ((float) Options.LVL_WIDTH));
				NB_BLOCKS_WIN = Options.WIN_HEIGHT / BLOCK_SIZE;
			}
		});
		m_graphicUIs = new LinkedList<Component>();

		File imageFile = new File("sprites/hellBG.png");
		try {
			m_hellBackground = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("sprites/heavenBG1.png");
		try {
			m_heavenBackground = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("sprites/heavenBG2.png");
		try {
			m_heavenBackground2 = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		m_yG1 = getHeight() - Options.LVL_HEIGHT * BLOCK_SIZE;
	}

	public void step(long now) {
		// if the camera is blocked
		if (Options.LVL_HEIGHT - m_model.m_player.m_bounds.y <= (NB_BLOCKS_WIN / 2)) {
			m_yG1 = getHeight() - Options.LVL_HEIGHT * BLOCK_SIZE;
			if (Options.ECHO_CAMERA_MODE)
				System.out.println("Camera is blocked");
		}
		// the camera follows the player
		else {
			m_yG1 = -(m_model.m_player.m_bounds.y - (NB_BLOCKS_WIN - m_model.m_player.m_bounds.height) / 2 - 2)
					* BLOCK_SIZE;
			if (Options.ECHO_CAMERA_MODE)
				System.out.println("Camera follows player");
		}

		transform();
	}

	public void transform() {
		if (m_model.getWorldType() == WorldType.HEAVEN) {
			m_currentBackground = m_heavenBackground;
			m_currentBackground2 = m_heavenBackground2;
		} else {
			m_currentBackground = m_hellBackground;
			m_currentBackground2 = m_hellBackground;
		}
	}

	private void computeFPS() {
		long now = System.currentTimeMillis();
		if (now - m_last > 1000L) {
			m_fps = m_npaints;
			m_last = now;
			m_npaints = 0;
		}
		m_game.setFPS(m_fps, null);
		// m_game.setFPS(m_fps, "npaints=" + m_npaints);
		m_npaints++;
	}

	@Override
	protected void _paint(Graphics g) {
		computeFPS();
		g.setColor(grey);
		g.fillRect(0, 0, getWidth(), getHeight());

		paintLevel(g, m_model.m_currentLevel, 0);
		paintLevel(g, m_model.m_nextLevel, 1);

		Iterator<Component> iter = m_graphicUIs.iterator();
		while (iter.hasNext()) {
			Component graphic = iter.next();
			graphic.repaint();
		}
	}

	private void paintLevel(Graphics g, Level lvl, int i) {
		float borderSize = (float) (65.0f * (float) getWidth() / 765.0f);

		int lvlHeight = Options.LVL_HEIGHT * BLOCK_SIZE;
		int lvlWidth = (int) (((float) (Options.LVL_WIDTH * BLOCK_SIZE)) / (1.0f - (2.0f * 65.0f) / 765.0f));
		int lvlY = m_yG1 - lvlHeight * i;
		int lvlX = (getWidth() - lvlWidth) / 2;

		g.drawImage(lvl.getId() % 2 == 0 ? m_currentBackground : m_currentBackground2, lvlX, lvlY, lvlWidth, lvlHeight,
				null);

		g.setColor(Color.white);
		g.drawLine(lvlX, lvlY, lvlX + lvlWidth, lvlY);

		Rectangle viewportBounds = new Rectangle(0, 0, getWidth(), getHeight());
		Iterator<Entity> iter = lvl.m_obstacles.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			Rectangle bound = getRect(e, lvlX + (int) borderSize, lvlY);
			if (viewportBounds.intersects(bound)) {
				e.m_currentStunt.m_animation.step();
				paintSprite(g, e.m_currentStunt.m_animation.getSprite(), bound);
			}
		}

		if (lvl.m_special != null) {
			Rectangle bound = getRect(lvl.m_special, lvlX + (int) borderSize, lvlY);
			if (viewportBounds.intersects(bound)) {
				lvl.m_special.m_currentStunt.m_animation.step();
				paintSpriteWithHPBarTransparency(g, lvl.m_special.m_currentStunt.m_animation.getSprite(), bound,
						lvl.m_special);
			}
		}

		if (lvl.m_player != null) {
			Rectangle bound = getRect(lvl.m_player, lvlX + (int) borderSize, lvlY);
			if (viewportBounds.intersects(bound)) {
				lvl.m_player.m_currentStunt.m_animation.step();
				paintSprite(g, lvl.m_player.m_currentStunt.m_animation.getSprite(), bound);
			}
		}

		iter = lvl.m_souls.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			Rectangle bound = getRect(e, lvlX + (int) borderSize, lvlY);
			if (viewportBounds.intersects(bound)) {
				e.m_currentStunt.m_animation.step();
				paintSpriteWithHPBarTransparency(g, e.m_currentStunt.m_animation.getSprite(), bound, e);
			}
		}

		iter = lvl.m_missiles.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			Rectangle bound = getRect(e, lvlX + (int) borderSize, lvlY);
			if (viewportBounds.intersects(bound)) {
				e.m_currentStunt.m_animation.step();
				paintSprite(g, e.m_currentStunt.m_animation.getSprite(), bound);
			}
		}
		iter = lvl.m_nest.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			Rectangle bound = getRect(e, lvlX + (int) borderSize, lvlY);
			if (viewportBounds.intersects(bound)) {
				e.m_currentStunt.m_animation.step();
				paintSpriteWithHPBar(g, e.m_currentStunt.m_animation.getSprite(), bound, e);
			}
		}
		iter = lvl.m_souls.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			Rectangle bound = getRect(e, lvlX + (int) borderSize, lvlY);
			if (viewportBounds.intersects(bound)) {
				e.m_currentStunt.m_animation.step();
				paintSprite(g, e.m_currentStunt.m_animation.getSprite(), bound);
			}
		}
	}

	private void paintSprite(Graphics g, BufferedImage sprite, Rectangle bound) {
		g.drawImage(sprite, bound.x, bound.y, bound.width, bound.height, null);
	}

	private void paintSpriteWithHPBar(Graphics g, BufferedImage sprite, Rectangle bound, Entity e) {
		g.drawImage(sprite, bound.x, bound.y, bound.width, bound.height, null);
		g.setColor(Color.BLACK);
		g.fillRect(bound.x, bound.y - BLOCK_SIZE / 2, bound.width, BLOCK_SIZE / 3);
		g.setColor(Color.RED);
		g.fillRect(bound.x, bound.y - BLOCK_SIZE / 2,
				(int) ((float) bound.width * ((float) e.m_HP / (float) e.m_currentStunt.getMaxHP())), BLOCK_SIZE / 3);
	}

	private void paintSpriteWithHPBarTransparency(Graphics g, BufferedImage sprite, Rectangle bound, Entity e) {
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, e.m_transparency));
		paintSpriteWithHPBar(g, sprite, bound, e);
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

	}

	private Rectangle getRect(Entity m_player, int x, int y) {
		return new Rectangle(m_player.m_bounds.x * BLOCK_SIZE + x, m_player.m_bounds.y * BLOCK_SIZE + y,
				m_player.m_bounds.width * BLOCK_SIZE, m_player.m_bounds.height * BLOCK_SIZE);
	}

//	private void paint(Graphics g, Level lvl) {
//		Rectangle bounds = g.getClipBounds();
//		g.drawImage(lvl.m_id % 2 == 0 ? m_currentBackground : m_currentBackground2, 0, 0, bounds.width, bounds.height,
//				null);
//		g.setColor(Color.white);
//		g.drawLine(0, 0, bounds.width, 0);
//
//		Iterator<Entity> iter = lvl.m_obstacles.iterator();
//		while (iter.hasNext()) {
//			paintAnimation(g, iter.next());
//		}
//
//		if (lvl.m_special != null)
//			paintAnimation(g, lvl.m_special);
//
//		if (lvl.m_player != null)
//			paintAnimation(g, lvl.m_player);
//
//		iter = lvl.m_souls.iterator();
//		while (iter.hasNext()) {
//			paintAnimation(g, iter.next());
//		}
//
//		iter = lvl.m_nest.iterator();
//		while (iter.hasNext()) {
//			paintAnimation(g, iter.next());
//		}
//
//		iter = lvl.m_missiles.iterator();
//		while (iter.hasNext()) {
//			paintAnimation(g, iter.next());
//		}
//	}

	public void addGraphicUI(Component g) {
		m_graphicUIs.add(g);
	}
//
//	private void paintAnimation(Graphics g, Entity e) {
//		e.m_currentStunt.m_animation.step();
//		g.drawImage(e.m_currentStunt.m_animation.getSprite(), e.m_bounds.x * BLOCK_SIZE, e.m_bounds.y * BLOCK_SIZE,
//				e.m_bounds.width * BLOCK_SIZE, e.m_bounds.height * BLOCK_SIZE, null);
//	}

}
