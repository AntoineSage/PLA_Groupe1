package edu.ricm3.game.purgatoire;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ricm3.game.purgatoire.entities.Entity;
import edu.ricm3.game.purgatoire.entities.Missile;
import edu.ricm3.game.purgatoire.entities.Nest;
import edu.ricm3.game.purgatoire.entities.Obstacle;
import edu.ricm3.game.purgatoire.entities.Player;
import edu.ricm3.game.purgatoire.entities.Soul;
import edu.ricm3.game.purgatoire.entities.Special;
import ricm3.interpreter.IDirection;

public class Level {

	private static int id = 0;
	private int m_id;

	public Model m_model;

	protected List<Entity> m_souls;
	protected List<Entity> m_obstacles;
	protected List<Entity> m_nest;
	protected List<Entity> m_missiles;

	protected Entity m_special;
	protected Player m_player;

	protected List<Entity> m_entities;
	protected List<Entity> m_toRemove;

	public CollisionGrid m_collisionGrid;

	private long lastUpdatePlayer;
	private long lastUpdateSouls;
	private long lastUpdateObstacles;
	private long lastUpdateNests;

	public BufferedImage m_background;

	Level(Model model) {
		m_id = id;
		id++;
		m_model = model;

		m_souls = new LinkedList<Entity>();
		m_obstacles = new LinkedList<Entity>();
		m_nest = new LinkedList<Entity>();
		m_entities = new LinkedList<Entity>();
		m_missiles = new LinkedList<Entity>();
		m_toRemove = new LinkedList<Entity>();

		m_collisionGrid = new CollisionGrid();

	}

	public void addEntity(Entity e) {
		if (m_collisionGrid.addEntity(e) == null)
			return;
		m_entities.add(e);

		if (e instanceof Obstacle) {
			if (m_obstacles.contains(e))
				throw new IllegalArgumentException("Cannot have to same entity in the level");
			m_obstacles.add(e);
		}

		if (e instanceof Soul) {
			if (m_souls.contains(e))
				throw new IllegalArgumentException("Cannot have to same entity in the level");
			m_souls.add(e);
		}

		if (e instanceof Nest) {
			if (m_nest.contains(e))
				throw new IllegalArgumentException("Cannot have to same entity in the level");
			m_nest.add(e);
		}

		if (e instanceof Special) {
			m_special = e;
		}

		if (e instanceof Player) {
			m_player = (Player) e;
		}

		if (e instanceof Missile) {
			if (m_missiles.contains(e))
				throw new IllegalArgumentException("Cannot have to same entity in the level");
			m_missiles.add(e);
		}
	}

	public void removeEntity(Entity e) {
		m_toRemove.add(e);
	}

	// Update the collisionGrid with the future new values translated by x and y.
	public void updateEntity(Entity e, int x, int y) {
		m_collisionGrid.updateEntity(e, x, y);
	}

	public void step(long now) {
		removeEntities();
		Iterator<Entity> iter;
		if (now - lastUpdatePlayer > 1000 / 30) {
			lastUpdatePlayer = now;
			if (m_player != null)
				m_player.step(now);

			iter = m_missiles.iterator();
			while (iter.hasNext()) {
				iter.next().step(now);
			}
		}

		if (now - lastUpdateSouls > 1000 / 30) {
			iter = m_souls.iterator();
			while (iter.hasNext()) {
				iter.next().step(now);
			}
			if (m_special != null)
				m_special.step(now);
			lastUpdateSouls = now;
		}
		if (now - lastUpdateObstacles > 500) {
			iter = m_obstacles.iterator();
			while (iter.hasNext()) {
				iter.next().step(now);
			}
			if (m_special != null)
				m_special.step(now);
			lastUpdateObstacles = now;
		}

		iter = m_nest.iterator();
		if (now - lastUpdateNests > Options.NEST_SPAWN_PERIOD && this == m_model.m_currentLevel) {
			while (iter.hasNext()) {
				iter.next().step(now);
			}
			lastUpdateNests = now;
		}
	}

	private void removeEntities() {
		Iterator<Entity> iter = m_toRemove.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			if (e instanceof Obstacle) {
				m_obstacles.remove(e);
			}

			if (e instanceof Soul) {
				m_souls.remove(e);
			}

			if (e instanceof Nest) {
				m_nest.remove(e);
			}

			if (e instanceof Special) {
				m_special = null;
			}

			if (e instanceof Player) {
				m_player = null;
			}

			if (e instanceof Missile) {
				m_missiles.remove(e);
			}

			m_entities.remove(e);
			m_collisionGrid.removeEntity(e);
			iter.remove();
		}
	}

	public boolean wontCollide(Entity entity, IDirection d) {
		return m_collisionGrid.wontCollide(entity, d);
	}

	void transform() {

		Iterator<Entity> iter = m_entities.iterator();
		while (iter.hasNext())
			iter.next().transform();
	}

	public WorldType getWorldType() {
		return m_model.getWorldType();
	}

//<<<<<<< HEAD
//	/*
//	 * position: 1 = NW, 2 = NE, 3 = SW, 4 = SE
//	 * 
//	 */
//
//	void levelGenerator(List<String> level, int position) {
//		int x_offset = 0;
//		int y_offset = 0;
//		if (level.size() != Options.LVL_HEIGHT / 2)
//			throw new IllegalArgumentException("Wrong lvl Height in your file");
//		for (int k = 0; k < Options.LVL_HEIGHT / 2; k++) {
//			if (level.get(k).length() != Options.LVL_WIDTH / 2)
//				throw new IllegalArgumentException("Wrong lvl Width in your file");
//		}
//		switch (position) {
//		case 0:
//			break;
//		case 1:
//			x_offset = Options.LVL_WIDTH / 2;
//			break;
//		case 2:
//			y_offset = Options.LVL_HEIGHT / 2;
//			break;
//		case 3:
//			x_offset = Options.LVL_WIDTH / 2;
//			y_offset = Options.LVL_HEIGHT / 2;
//			break;
//		}
//		for (int i = 0; i < Options.LVL_HEIGHT / 2; i++) {
//			for (int j = 0; j < Options.LVL_WIDTH / 2; j++) {
//				if (m_collisionGrid.get(j + x_offset, i + y_offset).isEmpty())
//					entityInterpret(level.get(i).charAt(j), x_offset + j, y_offset + i);
//			}
//		}
//	}
//
//	public void entityInterpret(char c, int x, int y) {
//=======
	public void entityInterpret(char c, int x, int y, QuarterType qt) {
		switch (c) {
		case 'O':
			new Obstacle(this, x, y, Options.OBSTACLE_SIZE);
			break;
		case 'S':
			new Soul(this, x, y, Options.SOUL_SIZE);
			break;
		case 'N':
			new Nest(this, x, y, Options.NEST_SIZE);
			break;
		case '*':
			new Special(this, x, y, Options.SPCL_SIZE);
			break;
		case '/':
			if (qt == QuarterType.SPECIAL)
				new Special(this, x, y, Options.SPCL_SIZE);
			if (qt == QuarterType.NEST)
				new Nest(this, x, y, Options.NEST_SIZE);
			break;
		case '_':
			break;
		default:
			throw new IllegalArgumentException("Wrong letter in the level file");
		}
	}

	void quarterLevelPlacement(QuarterLevel quarterLevel) {
		if (quarterLevel.levelQuarter.size() != Options.LVL_HEIGHT / 2)
			throw new IllegalArgumentException("Wrong lvl Height in your file");
		for (int k = 0; k < Options.LVL_HEIGHT / 2; k++) {
			if (quarterLevel.levelQuarter.get(k).length() != Options.LVL_WIDTH / 2)
				throw new IllegalArgumentException("Wrong lvl Width in your file");
		}
		for (int i = 0; i < Options.LVL_HEIGHT / 2; i++) {
			for (int j = 0; j < Options.LVL_WIDTH / 2; j++) {
				if (m_collisionGrid.get(j + quarterLevel.x_offset, i + quarterLevel.y_offset).isEmpty())
					entityInterpret(quarterLevel.levelQuarter.get(i).charAt(j), quarterLevel.x_offset + j,
							quarterLevel.y_offset + i, quarterLevel.m_quarterType);
			}
		}
	}

	public int getId() {
		return m_id;
	}

	public Player getPlayer() {
		return m_player;
	}

}
