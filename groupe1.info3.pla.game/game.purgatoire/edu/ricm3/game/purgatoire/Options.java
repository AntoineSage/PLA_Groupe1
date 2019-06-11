package edu.ricm3.game.purgatoire;

public class Options {

	public static int WIN_HEIGHT = 756;
	public static int WIN_WIDTH = 786;
	public static final int LVL_HEIGHT = 84;
	public static final int LVL_WIDTH = 45;

	public static final int UI_PANEL_SIZE = 200;
	public static final int UI_BAR_HEIGHT = 150;
	public static final int UI_BAR_WIDTH = 30;

	public static final int TOTAL_PERIOD = 5000;
	public static final String AUT_FILE = "automatons/automata.aut";

	public static final double COEF_KARMA_POS = 0.2;
	public static final double COEF_KARMA_NEG = -0.3;

	public static final long DEFAULT_CD = 1000;

	// NEST OPTIONS
	public static final long NEST_SPAWN_DELAY = 3000;

	// PLAYER OPTIONS
	public static int HELL_PLAYER_DMG = 100;
	public static int HELL_PLAYER_HP_MAX = 1000;
	public static int HEAVEN_PLAYER_DMG = 100;
	public static int HEAVEN_PLAYER_HP_MAX = 1000;

	public static int PLAYER_MAX_TOTAL_HP = 1000;
	public static int PLAYER_HP = 1000;
	public static int PLAYER_XP = 0;
	public static int PLAYER_XP_MAX = 150;
	public static int PLAYER_KARMA_MAX = 150;

	public static long HELL_PLAYER_WIZZ_TIMER = 1000;
	public static final int[] PLAYER_RANKS = { 0, 100, 200, 300 };
	public static final int PLAYER_MAX_RANK = PLAYER_RANKS.length - 2;
	public static final String[] PLAYER_RANKS_HEAVEN = { "Rank heaven 1", "Rank heaven 2", "Rank heaven 3" };
	public static final String[] PLAYER_RANKS_HELL = { "Rank hell 1", "Rank hell 2", "Rank hell 3" };

	public static final int PLAYER_KARMA_TIME_AMOUNT = 5;
	public static final int PLAYER_KARMA_TIME_DURATION = 1000;

	// NEST OPTIONS
	public static int HELL_NEST_DMG = 1;
	public static int HELL_NEST_HP_MAX = 1;
	public static int HELL_NEST_KARMA_TOGIVE = -10;
	public static int HEAVEN_NEST_DMG = 1;
	public static int HEAVEN_NEST_HP_MAX = 1;
	public static int HEAVEN_NEST_KARMA_TOGIVE = -10;
	public static long NEST_TIMER_WIZZ = 3000;
	public static long NEST_TIMER_POP =5000;
	public static int NEST_HP = 1;

	// SOUL OPTIONS
	public static int HELL_SOUL_DMG = 10;
	public static int HELL_SOUL_HP_MAX = 3;
	public static int HELL_SOUL_KARMA_TOGIVE = -10;
	public static int HEAVEN_SOUL_DMG = 10;
	public static int HEAVEN_SOUL_HP_MAX = 1;
	public static int HEAVEN_SOUL_KARMA_TOGIVE = -10;

	public static int SOUL_HP = 3;

	// SPCL OPTIONS
	public static int HELL_SPCL_DMG = 1;
	public static int HELL_SPCL_HP_MAX = 1;
	public static int HELL_SPCL_HP_TOGIVE = 5;
	public static int HELL_SPCL_KARMA_TOGIVE = 10;
	public static int HEAVEN_SPCL_DMG = 1;
	public static int HEAVEN_SPCL_HP_MAX = 1;
	public static int HEAVEN_SPCL_KARMA_TOGIVE = 10;
	public static long HEAVEN_SPECIAL_TIMER = 5000;
	public static long HELL_SPECIAL_TIMER = 2000;
	public static int SPCL_HP = 1;

	// OBSTACLE OPTIONS
	public static int HELL_OBSTACLE_DMG = 0;
	public static int HELL_OBSTACLE_HP_MAX = 1;
	public static int HEAVEN_OBSTACLE_DMG = 0;
	public static int HEAVEN_OBSTACLE_HP_MAX = 1;

	public static int OBSTACLE_HP = 1;

	// MISSILE OPTIONS
	public static int HELL_MISSILE_DMG = 1;
	public static int HELL_MISSILE_HP_MAX = 1;
	public static int HEAVEN_MISSILE_DMG = 1;
	public static int HEAVEN_MISSILE_HP_MAX = 1;
	public static long MISSILE_TIMER = 1000;
	public static int MISSILE_HP = 1;

	// DASH SIZE AND COOLDOWN
	public static int DASH_SIZE = 10;
	public static int DASH_CD = 5000;

	// TIMER OPTIONS
	public static int WIZZ_TIMER = 1000;

	// BUFF OPTIONS
	public static int BUFF_DURATION = 5000;
	public static int BUFF_DMG = 100; // %
	public static int BUFF_WEAKNESS = 100; // %

	// ENTITIES SIZE
	public static int PLAYER_HEIGHT = 3; // TODO fix ArrayIndexOutOfBoundsException when size is changed
	public static int PLAYER_WIDTH = 3;
	public static int SOUL_HEIGHT = 2;
	public static int SOUL_WIDTH = 2;
	public static int NEST_HEIGHT = 3;
	public static int NEST_WIDTH = 3;
	public static int SPCL_HEIGHT = 5;
	public static int SPCL_WIDTH = 5;

	// TODO echo options
	public static final boolean ECHO_PLAYER_DAMAGE_TAKEN = true;
	public static final boolean ECHO_PLAYER_HP_CHANGE = false;
	public static final boolean ECHO_PLAYER_XP_CHANGE = false;
	public static final boolean ECHO_PLAYER_KARMA_CHANGE = false;
	public static final boolean ECHO_PLAYER_RANK_CHANGE = false;
	public static final boolean ECHO_WINDOW_SIZE_CHANGE = false;

}
