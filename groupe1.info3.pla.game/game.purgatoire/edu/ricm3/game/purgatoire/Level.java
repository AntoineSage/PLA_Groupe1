package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ricm3.interpreter.IDirection;

public class Level {

	Model m_model;
	List<Entity> m_souls;
	List<Entity> m_obstacles;
	List<Entity> m_nest;
	List<Entity> m_entities;
	Entity m_special;
	Entity m_player;

	CollisionGrid m_collisionGrid;
	Color m_c;
	private long lastUpdatePlayer;
	private long lastUpdateOthers;
	private long lastUpdateNest;

	List<Entity> m_toRemove;

	Level(Model model, Color c, List<Entity> obstacles, List<Entity> souls, Special special) {
		m_model = model;
		m_c = c;

		m_obstacles = new LinkedList<Entity>();
		m_souls = new LinkedList<Entity>();
		m_nest = new LinkedList<Entity>();

		m_collisionGrid = new CollisionGrid();
		m_entities = new LinkedList<Entity>();

		m_toRemove = new LinkedList<Entity>();

		Iterator<Entity> iter = obstacles.iterator();
		while (iter.hasNext()) {
			addEntity(iter.next());
		}
		iter = souls.iterator();
		while (iter.hasNext()) {
			addEntity(iter.next());
		}
		addEntity(special);
	}

	Level(Model model, Color c) {
		m_c = c;
		m_model = model;
		m_souls = new LinkedList<Entity>();
		m_obstacles = new LinkedList<Entity>();
		m_nest = new LinkedList<Entity>();
		m_entities = new LinkedList<Entity>();

		m_collisionGrid = new CollisionGrid();
		m_toRemove = new LinkedList<Entity>();
	}

	public void addEntity(Entity e) {
		if (e instanceof Obstacle) {
			m_obstacles.add(e);
		}

		if (e instanceof Soul) {
			m_souls.add(e);
		}

		if (e instanceof Nest) {
			m_nest.add(e);
		}

		if (e instanceof Special) {
			m_special = e;
		}

		if (e instanceof Player) {
			m_player = e;
		}

		m_entities.add(e);
		m_collisionGrid.addEntity(e);
	}

	public void removeEntity(Entity e) {
		m_toRemove.add(e);
	}

	// Update the collisionGrid with the future new values translated by x and y.
	public void updateEntity(Entity e, int x, int y) {
		m_collisionGrid.updateEntity(e, x, y);
	}

	Level(List<Entity> obstacles) {
		m_obstacles = obstacles;
		Iterator<Entity> iter = m_obstacles.iterator();
		Entity tmp;
		while (iter.hasNext()) {
			tmp = iter.next();
			m_collisionGrid.addEntity(tmp);
		}
	}

	Level(Model model) {
		this(model, Color.WHITE);
	}

	WorldType getWorldType() {
		return m_model.getWorldType();
	}

	void transform() {
		Iterator<Entity> iter = m_entities.iterator();
		while (iter.hasNext())
			iter.next().transform();
	}

	public boolean wontCollide(Entity entity, IDirection d) {
		return m_collisionGrid.wontCollide(entity, d);
	}

	public void step(long now) {
		removeEntities();
		if (now - lastUpdatePlayer > 1000 / 60) {
			lastUpdatePlayer = now;
			if (m_player != null)
				m_player.step(now);
		}
		if (now - lastUpdateOthers > 200) {
			Iterator<Entity> iter = m_souls.iterator();
			while (iter.hasNext()) {
				iter.next().step(now);
			}
			iter = m_obstacles.iterator();
			while (iter.hasNext()) {
				iter.next().step(now);
			}

			if (m_special != null)
				m_special.step(now);
			lastUpdateOthers = now;
		}
		
			Iterator<Entity> iter = m_nest.iterator();
			if(now - lastUpdateNest > Options.NEST_SPAWN_DELAY) {
			while (iter.hasNext()) {
				iter.next().step(now);
			}
			lastUpdateNest = now;
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

			m_entities.remove(e);
			m_collisionGrid.removeEntity(e);
			iter.remove();
		}
	}
}
