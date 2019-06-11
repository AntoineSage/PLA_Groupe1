package edu.ricm3.game.purgatoire;

import java.io.FileNotFoundException;
import java.util.List;

import ricm3.interpreter.IAutomaton;
import ricm3.parser.Ast;
import ricm3.parser.Ast.AI_Definitions;
import ricm3.parser.AutomataParser;

public class Singleton {

	private static Singleton m_singleton = new Singleton(Options.AUT_FILE);

	private static IAutomaton m_playerHellAut;
	private static IAutomaton m_playerHeavenAut;

	private static IAutomaton m_soulHellAut;
	private static IAutomaton m_soulHeavenAut;

	private static IAutomaton m_obstacleHellAut;
	private static IAutomaton m_obstacleHeavenAut;

	private static IAutomaton m_specialHellAut;
	private static IAutomaton m_specialHeavenAut;

	private static IAutomaton m_nestHellAut;
	private static IAutomaton m_nestHeavenAut;

	private static IAutomaton m_missileHellAut;
	private static IAutomaton m_missileHeavenAut;

	private static Controller m_controller;

	private static Animation m_playerHellAnim;
	private static Animation m_playerHeavenAnim;

	private static Animation m_soulHellAnim;
	private static Animation m_soulHeavenAnim;

	private static Animation m_obstacleHellAnim;
	private static Animation m_obstacleHeavenAnim;

	private static Animation m_specialHellAnim;
	private static Animation m_specialHeavenAnim;

	private static Animation m_nestHellAnim;
	private static Animation m_nestHeavenAnim;

	private static Animation m_missileHellAnim;
	private static Animation m_missileHeavenAnim;
	
	private Singleton(String file) {
		Ast ast = null;
		try {
			ast = AutomataParser.from_file(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<IAutomaton> automatons = ((AI_Definitions) ast).make();

		m_playerHellAut = automatons.get(0);
		m_playerHeavenAut = automatons.get(0);

		m_soulHellAut = automatons.get(1);
		m_soulHeavenAut = automatons.get(2);

		m_obstacleHellAut = automatons.get(3);
		m_obstacleHeavenAut = automatons.get(3);

		m_specialHellAut = automatons.get(5);
		m_specialHeavenAut = automatons.get(4);

		m_nestHellAut = automatons.get(6);
		m_nestHeavenAut = automatons.get(6);

		m_missileHellAut = automatons.get(7);
		m_missileHeavenAut = automatons.get(7);

		try {
			m_playerHellAnim = new Animation("animations/playerHell.ani");
			m_playerHeavenAnim = new Animation("animations/playerHeaven.ani");

			m_soulHellAnim = new Animation("animations/soulHell.ani");
			m_soulHeavenAnim = new Animation("animations/soulHeaven.ani");

			m_obstacleHellAnim = new Animation("animations/obstacleHell.ani");
			m_obstacleHeavenAnim = new Animation("animations/obstacleHeaven.ani");

			m_specialHellAnim = new Animation("animations/specialHell.ani");
			m_specialHeavenAnim = new Animation("animations/specialHeaven.ani");

			m_nestHellAnim = new Animation("animations/nestHell.ani");
			m_nestHeavenAnim = new Animation("animations/nestHeaven.ani");

			m_missileHellAnim = new Animation("animations/missileHell.ani");
			m_missileHeavenAnim = new Animation("animations/missileHeaven.ani");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	public Singleton getSingleton() {
		return m_singleton;
	}

	public static Controller getController() {
		return m_controller;
	}

	public static void setController(Controller controller) throws IllegalAccessException {
		if (m_controller == null) {
			m_controller = controller;
		} else {
			throw new IllegalAccessException("controller can only be set one time");
		}

	}

	public static IAutomaton getNewPlayerHellAut() {
		return m_playerHellAut.copy();
	}

	public static IAutomaton getNewPlayerHeavenAut() {
		return m_playerHeavenAut.copy();
	}

	public static IAutomaton getNewObstacleHellAut() {
		return m_obstacleHellAut.copy();
	}

	public static IAutomaton getNewObstacleHeavenAut() {
		return m_obstacleHeavenAut.copy();
	}

	public static IAutomaton getNewSoulHellAut() {
		return m_soulHellAut.copy();
	}

	public static IAutomaton getNewSoulHeavenAut() {
		return m_soulHeavenAut.copy();
	}

	public static IAutomaton getNewSpecialHellAut() {
		return m_specialHellAut.copy();
	}

	public static IAutomaton getNewSpecialHeavenAut() {
		return m_specialHeavenAut.copy();
	}

	public static IAutomaton getNewNestHellAut() {
		return m_nestHellAut.copy();
	}

	public static IAutomaton getNewNestHeavenAut() {
		return m_nestHeavenAut.copy();
	}

	public static IAutomaton getNewMissileHellAut() {
		return m_missileHellAut.copy();
	}

	public static IAutomaton getNewMissileHeavenAut() {
		return m_missileHeavenAut.copy();

	}

	public static Animation getPlayerHellAnim() {
		return m_playerHellAnim;
	}

	public static Animation getPlayerHeavenAnim() {
		return m_playerHeavenAnim;
	}

	public static Animation getSoulHellAnim() {
		return m_soulHellAnim;
	}

	public static Animation getSoulHeavenAnim() {
		return m_soulHeavenAnim;
	}

	public static Animation getObstacleHellAnim() {
		return m_obstacleHellAnim;
	}

	public static Animation getObstacleHeavenAnim() {
		return m_obstacleHeavenAnim;
	}

	public static Animation getSpecialHellAnim() {
		return m_specialHellAnim;
	}

	public static Animation getSpecialHeavenAnim() {
		return m_specialHeavenAnim;
	}

	public static Animation getNestHellAnim() {
		return m_nestHellAnim;
	}

	public static Animation getNestHeavenAnim() {
		return m_nestHeavenAnim;
	}

	public static Animation getMissileHellAnim() {
		return m_missileHellAnim;
	}

	public static Animation getMissileHeavenAnim() {
		return m_missileHeavenAnim;
	}
}
