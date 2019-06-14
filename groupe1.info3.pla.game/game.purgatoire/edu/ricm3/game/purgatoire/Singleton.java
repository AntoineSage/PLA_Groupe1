package edu.ricm3.game.purgatoire;

import java.io.File;
import java.util.List;

import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IEntityType;
import ricm3.parser.Ast;
import ricm3.parser.Ast.AI_Definitions;
import ricm3.parser.AutomataParser;

public class Singleton {

	private static IAutomaton[] m_HellAut;
	private static IAutomaton[] m_HeavenAut;
	private static Animation[] m_HellAnim;
	private static Animation[] m_HeavenAnim;

	private static int[] m_Firsts;
	private static int[] m_Count;

	private static IAutomaton[] m_HellAutFirst;
	private static IAutomaton[] m_HeavenAutFirst;
	private static Animation[] m_HellAnimFirst;
	private static Animation[] m_HeavenAnimFirst;

	private static Controller m_controller;
	private static List<IAutomaton> m_automatons;
	private static Animation[] m_animations;

	private static IAutomaton m_playerHeavenMoveAut;
	private static IAutomaton m_playerHellMoveAut;

	private static BiSound m_backgroundMusic;

	private static IAutomaton m_soulUnlimitedRangeFollow;
	private static IAutomaton m_verticalSoulAut;
	private static IAutomaton m_horizontalSoulAut;

	public static final String[] m_existingEntitiesTypes = { "Players", "Souls", "Obstacles", "Nests", "Missiles",
			"Specials" };

	@SuppressWarnings("unused")
	private static Singleton m_singleton = new Singleton(Options.AUT_FILE);

	private Singleton(String file) {
		Ast ast = null;
		try {
			ast = AutomataParser.from_file(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		m_automatons = ((AI_Definitions) ast).make();
		File folder = new File("animations/");
		File animationsFiles[] = folder.listFiles();
		m_animations = new Animation[animationsFiles.length];
		for (int i = 0; i < animationsFiles.length; i++) {
			m_animations[i] = new Animation(animationsFiles[i]);
		}
		m_playerHeavenMoveAut = m_automatons.get(0);
		m_playerHellMoveAut = m_automatons.get(0);
		m_soulUnlimitedRangeFollow = m_automatons.get(4);
		m_verticalSoulAut = m_automatons.get(12);
		m_horizontalSoulAut = m_automatons.get(13);
		try {
			m_backgroundMusic = BiSound.make("sprites/hell.wav", "sprites/heaven.wav", true);
		} catch (Exception ex) {
		}

		m_Count = new int[m_existingEntitiesTypes.length];
	}

//	public Singleton getSingleton() {
//		return m_singleton;
//	}

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

	public static void notify(IEntityType.IType type) {
		switch (type) {
		case PLAYER:
			m_Count[0]++;
			break;
		case ADVERSARY:
			m_Count[1]++;
			break;
		case OBSTACLE:
			m_Count[2]++;
			break;
		case DANGER:
			m_Count[3]++;
			break;
		case MISSILE:
			m_Count[4]++;
			break;
		case TEAM:
			m_Count[5]++;
			break;
		default:
			break;
		}
	}

	public static void set(IAutomaton[] HellAut, IAutomaton[] HeavenAut, Animation[] HellAnim, Animation[] HeavenAnim,
			int[] Firsts, IAutomaton[] HellAutFirst, IAutomaton[] HeavenAutFirst, Animation[] HellAnimFirst,
			Animation[] HeavenAnimFirst) {
		if (m_HellAut == null)
			m_HellAut = HellAut;
		if (m_HeavenAut == null)
			m_HeavenAut = HeavenAut;
		if (m_HellAnim == null)
			m_HellAnim = HellAnim;
		if (m_HeavenAnim == null)
			m_HeavenAnim = HeavenAnim;
		if (m_Firsts == null)
			m_Firsts = Firsts;
		if (m_HellAutFirst == null)
			m_HellAutFirst = HellAutFirst;
		if (m_HeavenAutFirst == null)
			m_HeavenAutFirst = HeavenAutFirst;
		if (m_HellAnimFirst == null)
			m_HellAnimFirst = HellAnimFirst;
		if (m_HeavenAnimFirst == null)
			m_HeavenAnimFirst = HeavenAnimFirst;
	}

	public static List<IAutomaton> getAutomatons() {
		return m_automatons;
	}

	public static IAutomaton getNewPlayerHeavenMoveAut() {
		return m_playerHeavenMoveAut.copy();
	}

	public static IAutomaton getNewPlayerHellMoveAut() {
		return m_playerHellMoveAut.copy();
	}

	public static IAutomaton getNewUnlimitedRangeFollow() {
		return m_soulUnlimitedRangeFollow.copy();
	}

	public static IAutomaton getNewPlayerHellAut() {
		if (m_Count[0] < m_Firsts[0]) {
			return m_HellAutFirst[0].copy();
		} else {
			return m_HellAut[0].copy();
		}
	}

	public static IAutomaton getNewPlayerHeavenAut() {
		if (m_Count[0] < m_Firsts[0]) {
			return m_HeavenAutFirst[0].copy();
		} else {
			return m_HeavenAut[0].copy();
		}
	}

	public static Animation getPlayerHellAnim() {
		if (m_Count[0] < m_Firsts[0]) {
			return m_HellAnimFirst[0];
		} else {
			return m_HellAnim[0];
		}
	}

	public static Animation getPlayerHeavenAnim() {
		if (m_Count[0] < m_Firsts[0]) {
			return m_HeavenAnimFirst[0];
		} else {
			return m_HeavenAnim[0];
		}
	}

	public static IAutomaton getNewSoulHellAut() {
		if (m_Count[1] < m_Firsts[1]) {
			return m_HellAutFirst[1].copy();
		} else {
			return m_HellAut[1].copy();
		}
	}

	public static IAutomaton getNewSoulHeavenAut() {
		if (m_Count[1] < m_Firsts[1]) {
			return m_HeavenAutFirst[1].copy();
		} else {
			return m_HeavenAut[1].copy();
		}
	}

	public static Animation getSoulHellAnim() {
		if (m_Count[1] < m_Firsts[1]) {
			return m_HellAnimFirst[1];
		} else {
			return m_HellAnim[1];
		}
	}

	public static Animation getSoulHeavenAnim() {
		if (m_Count[1] < m_Firsts[1]) {
			return m_HeavenAnimFirst[1];
		} else {
			return m_HeavenAnim[1];
		}
	}

	public static IAutomaton getVerticalSoul() {
		if (m_Count[1] < m_Firsts[1]) {
			return m_HeavenAutFirst[1].copy();
		} else {
			return m_verticalSoulAut.copy();
		}
	}

	public static IAutomaton getHorizontalSoul() {
		if (m_Count[1] < m_Firsts[1]) {
			return m_HeavenAutFirst[1].copy();
		} else {
			return m_verticalSoulAut.copy();
		}
	}

	public static IAutomaton getNewObstacleHellAut() {
		if (m_Count[2] < m_Firsts[2]) {
			return m_HellAutFirst[2].copy();
		} else {
			return m_HellAut[2].copy();
		}
	}

	public static IAutomaton getNewObstacleHeavenAut() {
		if (m_Count[2] < m_Firsts[2]) {
			return m_HeavenAutFirst[2].copy();
		} else {
			return m_HeavenAut[2].copy();
		}
	}

	public static Animation getObstacleHellAnim() {
		if (m_Count[2] < m_Firsts[2]) {
			return m_HellAnimFirst[2];
		} else {
			return m_HellAnim[2];
		}
	}

	public static Animation getObstacleHeavenAnim() {
		if (m_Count[2] < m_Firsts[2]) {
			return m_HeavenAnimFirst[2];
		} else {
			return m_HeavenAnim[2];
		}
	}

	public static IAutomaton getNewNestHeavenAut() {
		if (m_Count[3] < m_Firsts[3]) {
			return m_HellAutFirst[3].copy();
		} else {
			return m_HellAut[3].copy();
		}
	}

	public static IAutomaton getNewNestHellAut() {
		if (m_Count[3] < m_Firsts[3]) {
			return m_HeavenAutFirst[3].copy();
		} else {
			return m_HeavenAut[3].copy();
		}
	}

	public static Animation getNestHellAnim() {
		if (m_Firsts[3] < 0) {
			return m_HellAnimFirst[3];
		} else {
			return m_HellAnim[3];
		}
	}

	public static Animation getNestHeavenAnim() {
		if (m_Count[3] < m_Firsts[3]) {
			return m_HeavenAnimFirst[3];
		} else {
			return m_HeavenAnim[3];
		}
	}

	public static IAutomaton getNewMissileHellAut() {
		if (m_Count[4] < m_Firsts[4]) {
			return m_HellAutFirst[4].copy();
		} else {
			return m_HellAut[4].copy();
		}
	}

	public static IAutomaton getNewMissileHeavenAut() {
		if (m_Count[4] < m_Firsts[4]) {
			return m_HeavenAutFirst[4].copy();
		} else {
			return m_HeavenAut[4].copy();
		}
	}

	public static Animation getMissileHellAnim() {
		if (m_Count[4] < m_Firsts[4]) {
			return m_HellAnimFirst[4];
		} else {
			return m_HellAnim[4];
		}
	}

	public static Animation getMissileHeavenAnim() {
		if (m_Count[4] < m_Firsts[4]) {
			return m_HeavenAnimFirst[4];
		} else {
			return m_HeavenAnim[4];
		}
	}

	public static IAutomaton getNewSpecialHellAut() {
		if (m_Count[5] < m_Firsts[5]) {
			return m_HellAutFirst[5].copy();
		} else {
			return m_HellAut[5].copy();
		}
	}

	public static IAutomaton getNewSpecialHeavenAut() {
		if (m_Count[5] < m_Firsts[5]) {
			return m_HeavenAutFirst[5].copy();
		} else {
			return m_HeavenAut[5].copy();
		}
	}

	public static Animation getSpecialHellAnim() {
		if (m_Count[5] < m_Firsts[5]) {
			return m_HellAnimFirst[5];
		} else {
			return m_HellAnim[5];
		}
	}

	public static Animation getSpecialHeavenAnim() {
		if (m_Count[5] < m_Firsts[5]) {
			return m_HeavenAnimFirst[5];
		} else {
			return m_HeavenAnim[5];
		}
	}

	public static Animation[] getAnimations() {
		return m_animations;
	}

	public static BiSound getBackgroundMusic() {
		return m_backgroundMusic;
	}

	public static void clearCount() {
		m_Count = new int[m_existingEntitiesTypes.length];
	}

}
