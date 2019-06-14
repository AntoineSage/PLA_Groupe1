package edu.ricm3.game.purgatoire;

import java.awt.Color;

public class Options {

	public static final int WIN_MIN_WIDTH = 600;
	public static final int WIN_MIN_HEIGHT = 800;
	public static int WIN_WIDTH = 786;
	public static int WIN_HEIGHT = 756;
	public static final int LVL_HEIGHT = 90;
	public static final int LVL_WIDTH = 54;

	public static final Color PRIMARY_BACKGROUND = new Color(60, 60, 60);
	public static final Color PRIMARY_FOREGROUND = new Color(200, 200, 200);
	public static final Color SECONDARY_FOREGROUND = new Color(220, 220, 220);
	public static final Color CIRCLE_FOREGROUND = new Color(230, 180, 0);
	public static final Color KARMA_POS_FOREGROUND = new Color(80, 80, 220);
	public static final Color KARMA_NEG_FOREGROUND = new Color(220, 70, 120);

	public static final int UI_PANEL_SIZE = 200;
	public static final int UI_BAR_HEIGHT = 150;
	public static final int UI_BAR_WIDTH = 35;
	public static final int UI_CIRCLE_SIZE = 70;
	public static final int UI_MARGIN = 30;

	public static final boolean FPS_DISPLAYED = false;

	public static final int TOTAL_PERIOD = 25000;
	public static final String AUT_FILE = "automatons/automata.aut";

	public static final double COEF_KARMA_POS = 0.6;
	public static final double COEF_KARMA_NEG = -0.6;

	public static final long DEFAULT_CD = 1000;

	public static final int NB_PERIOD_DIFFICULTY = 2;

	public static boolean PAUSE = true;

	// PLAYER OPTIONS

	public static final int[] PLAYER_RANKS = { 0, 100, 200, 300, 400 }; // length <= stats arrays length - 1 !
	// public static final int HELL_PLAYER_DMG = 10;
	// public static final int HELL_PLAYER_HP_MAX = 1000;
	public static final int HELL_DIVIDAND_HP_MAX_TOLOSE = 50;
	// public static final int HEAVEN_PLAYER_DMG = 10;
	// public static final int HEAVEN_PLAYER_HP_MAX = 1000;

	public static final long HELL_PLAYER_WIZZ_TIMER = 1000;
	public static final int PLAYER_KARMA_MAX = 100;
	public static final int PLAYER_MAX_RANK = PLAYER_RANKS.length - 2;
	public static boolean CHEAT_MODE = false;
	public static boolean INVULNERABILITY = false;

	// Player stats arrays
	public static final String[] PLAYER_RANKS_HELL = { "Heretic", "Necromancer", "Demon", "SATAN" };
	public static final String[] PLAYER_RANKS_HEAVEN = { "Priest", "Saint", "Angel", "GOD" };
	public static final int[] PLAYER_DMG_HEAVEN = { 10, 15, 20, 30 };
	public static final int[] PLAYER_DMG_HELL = { 10, 15, 20, 30 };

	public static final int[] PLAYER_HP_MAX_TOTAL_HEAVEN = { 1000, 1200, 1500, 2000 };
	public static final int[] PLAYER_HP_MAX_TOTAL_HELL = { 1000, 1200, 1500, 2000 };
	public static final long[] HIT_TIMER_HELL = { 200, 150, 100, 50 };
//	public static final long[] HIT_TIMER_HEAVEN = { 200, 200, 200, 200 };

	public static final int PLAYER_KARMA_TIME_AMOUNT = 3;
	public static final int PLAYER_KARMA_TIME_DURATION = 1000;

	// NEST OPTIONS
	public static final int HELL_NEST_DMG_BASE = 2;
	public static int HELL_NEST_DMG = HELL_NEST_DMG_BASE;
	public static final int HELL_NEST_HP_MAX_BASE = 50;
	public static int HELL_NEST_HP_MAX = HELL_NEST_HP_MAX_BASE;
	public static final int HELL_NEST_KARMA_TOGIVE = -30;
	public static final int HEAVEN_NEST_DMG_BASE = 2;
	public static int HEAVEN_NEST_DMG = HEAVEN_NEST_DMG_BASE;
	public static final int HEAVEN_NEST_HP_MAX_BASE = 50;
	public static int HEAVEN_NEST_HP_MAX = HEAVEN_NEST_HP_MAX_BASE;
	public static final int HEAVEN_NEST_KARMA_TOGIVE = -10;

	public static final int NEST_POP_DURATION = 6000;
	public static final int NEST_WIZZ_DURATION = 3000;
	public static final long NEST_SPAWN_PERIOD = 3000;
	public static final long NEST_MIN_SPAWN_PERIOD = 200;

	public static final double NEST_COEF_CHANGE_SPAWN_DELAY = 0.7;
	public static final double HELL_NEST_DMG_COEF = 1.5;
	public static final double HELL_NEST_HP_MAX_COEF = 1.2;
	public static final double HEAVEN_NEST_DMG_COEF = 1.5;
	public static final double HEAVEN_NEST_HP_MAX_COEF = 1.2;

	public static final int NEST_EGG_RANGE = 2 * Options.NEST_SIZE + 4; // = 10

	// SOUL OPTIONS
	public static final int HELL_SOUL_DMG_BASE = 30;
	public static int HELL_SOUL_DMG = HELL_SOUL_DMG_BASE;
	public static final int HELL_SOUL_HP_MAX_BASE = 40;
	public static int HELL_SOUL_HP_MAX = HELL_SOUL_HP_MAX_BASE;
	public static final int HELL_SOUL_KARMA_TOGIVE = -10;
	public static final int HEAVEN_SOUL_DMG_BASE = 30;
	public static int HEAVEN_SOUL_DMG = HEAVEN_SOUL_DMG_BASE;
	public static final int HEAVEN_SOUL_HP_MAX_BASE = 40;
	public static int HEAVEN_SOUL_HP_MAX = HEAVEN_SOUL_HP_MAX_BASE;
	public static final int HEAVEN_SOUL_KARMA_TOGIVE = -1;

	public static final double HELL_SOUL_DMG_COEF = 1.5;
	public static final double HELL_SOUL_HP_MAX_COEF = 1.4;
	public static final double HEAVEN_SOUL_DMG_COEF = 1.5;
	public static final double HEAVEN_SOUL_HP_MAX_COEF = 1.4;

	public static final int SOUL_STEP_DELAY = 1000 / 10;

	// SPCL OPTIONS
	public static final int HELL_SPCL_DMG = 1;
	public static final int HELL_SPCL_HP_MAX = 50;
	public static final int HELL_SPCL_HP_TOGIVE = 5;
	public static final int HELL_SPCL_KARMA_TOGIVE = 2;
	public static final int HEAVEN_SPCL_DMG = 1;
	public static final int HEAVEN_SPCL_HP_MAX = 50;
	public static final int HEAVEN_SPCL_KARMA_TOGIVE = 2;
	public static final long HEAVEN_SPECIAL_TIMER = 5000;

	public static final long HELL_SPECIAL_TIMER = 2000;

	// OBSTACLE OPTIONS
	public static final int HELL_OBSTACLE_DMG_BASE = 0;
	public static int HELL_OBSTACLE_DMG = HELL_OBSTACLE_DMG_BASE;
	public static final int HELL_OBSTACLE_HP_MAX_BASE = 1;
	public static int HELL_OBSTACLE_HP_MAX = HELL_OBSTACLE_HP_MAX_BASE;
	public static final int HEAVEN_OBSTACLE_DMG_BASE = 0;
	public static int HEAVEN_OBSTACLE_DMG = HEAVEN_OBSTACLE_DMG_BASE;
	public static final int HEAVEN_OBSTACLE_HP_MAX_BASE = 1;
	public static int HEAVEN_OBSTACLE_HP_MAX = HEAVEN_OBSTACLE_HP_MAX_BASE;

	public static final double HELL_OBSTACLE_DMG_COEF = 1.5;
	public static final double HELL_OBSTACLE_HP_MAX_COEF = 1.5;
	public static final double HEAVEN_OBSTACLE_DMG_COEF = 1.5;
	public static final double HEAVEN_OBSTACLE_HP_MAX_COEF = 1.5;

	// MISSILE OPTIONS
	public static final int HELL_MISSILE_DMG = 1;
	public static final int HELL_MISSILE_HP_MAX = 1;
	public static final int HEAVEN_MISSILE_DMG = 1;
	public static final int HEAVEN_MISSILE_HP_MAX = 1;

	public static final long MISSILE_TIMER = 50;

	// DASH SIZE AND COOLDOWN
	public static final int DASH_SIZE = 15;
	public static final int DASH_CD = 5000;
	public static final int HEAVEN_HIT_CD = 300;

	// TIMER OPTIONS
	public static final int WIZZ_TIMER = 1000;
	public static final int HEAVEN_HIT_TIMER = 100 * 2;

	// BUFF OPTIONS
	public static final int BUFF_DURATION = 5000;
	public static final int BUFF_DMG = 200;
	public static final int BUFF_WEAKNESS = 100;

	// ENTITIES SIZE
	public static final int PLAYER_SIZE = 3;
	public static final int SOUL_SIZE = 2;
	public static final int NEST_SIZE = 3;
	public static final int SPCL_SIZE = 5;
	public static final int OBSTACLE_SIZE = 3;
	public static final int MISSILE_SIZE = 1;

	// LVL OPTIONS
	public static int LVL_QUARTER_MAX_NBR = 9; // nbr of paterns
	public static int LVL_3_NEST_PROBABILITY = 10;
	public static int LVL_2_NEST_PROBABILITY = 40;
	public static double LVL_3_NEST_PROBABILITY_COEF = 1.1;
	public static double LVL_2_NEST_PROBABILITY_COEF = 1.2;

	// Echo options
	public static final boolean ECHO_PLAYER_HP_CHANGE = false;
	public static final boolean ECHO_PLAYER_XP_CHANGE = false;
	public static final boolean ECHO_PLAYER_KARMA_CHANGE = false;
	public static final boolean ECHO_PLAYER_RANK_CHANGE = false;
	public static final boolean ECHO_HP_CHANGE = false;
	public static final boolean ECHO_DIE = false;
	public static final boolean ECHO_PLAYER_ON_SPCL = false;

	public static final boolean ECHO_WINDOW_SIZE_CHANGE = false;
	public static final boolean ECHO_CAMERA_MODE = false;

	public static final boolean ECHO_POP_NEST = false;
	public static final boolean ECHO_WIZZ_NEST = false;
	public static final boolean ECHO_POP_OBSTACLE = false;
	public static final boolean ECHO_WIZZ_OBSTACLE = false;
	public static final boolean ECHO_POP_PLAYER = false;
	public static final boolean ECHO_WIZZ_PLAYER = false;
	public static final boolean ECHO_POP_MISSILE = false;
	public static final boolean ECHO_WIZZ_MISSILE = false;
	public static final boolean ECHO_POP_SPECIAL = false;
	public static final boolean ECHO_WIZZ_SPECIAL = false;
	public static final boolean ECHO_POP_SOUL = false;
	public static final boolean ECHO_WIZZ_SOUL = false;

	public static final boolean ECHO_DASH = false;
	public static final boolean ECHO_CIRCLE_ATTACK = false;
	public static final boolean ECHO_HIT_TIMER_CHANGE = false;

	public static final boolean ECHO_RAISE_DIFFICULTY = false;
	public static final boolean ECHO_PLAYER_UPDATE_STATS = false;

}
