package edu.ricm3.game.purgatoire;

import java.util.List;

import ricm3.interpreter.IAutomaton;
import ricm3.parser.Ast;
import ricm3.parser.AutomataParser;
import ricm3.parser.Ast.AI_Definitions;

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

	private static Controller m_controller;
	
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

		m_specialHellAut = automatons.get(3);
		m_specialHeavenAut = automatons.get(3);
	}

	public Singleton getSingleton() {
		return m_singleton;
	}
	
	public static Controller getController() {
		return m_controller;
	}
	
	public static void setController(Controller controller) throws IllegalAccessException {
		if(m_controller == null ) {
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
}
