package edu.ricm3.game.purgatoire;

public class Options {

	static int WIN_HEIGHT = 756;
	static int WIN_WIDTH = 786;
	static final int LVL_HEIGHT = 84;
	static final int LVL_WIDTH = 45;

	static final int UI_PANEL_SIZE = 300;
	static final int UI_BAR_HEIGHT = 150;
	static final int UI_BAR_WIDTH = 30;

	static final int TOTAL_PERIOD = 5000;
	public static final String AUT_FILE = "automatons/automata.aut";

	static final double COEF_KARMA_POS = 0.2;
	static final double COEF_KARMA_NEG = -0.3;

	// PLAYER OPTIONS
	static int HELL_PLAYER_DMG = 1;
	static int HELL_PLAYER_HP_MAX = 1;
	static int HEAVEN_PLAYER_DMG = 1;
	static int HEAVEN_PLAYER_HP_MAX = 10;

	static int PLAYER_MAX_TOTAL_HP = 150;
	static int PLAYER_HP = 50;
	static int PLAYER_XP = 0;
	static int PLAYER_XP_MAX = 150;
	static int PLAYER_KARMA_MAX = 150;

	// NEST OPTIONS
	static int HELL_NEST_DMG = 1;
	static int HELL_NEST_HP_MAX = 1;
	static int HEAVEN_NEST_DMG = 1;
	static int HEAVEN_NEST_HP_MAX = 1;

	static int NEST_HP = 1;

	// SOUL OPTIONS
	static int HELL_SOUL_DMG = 1;
	static int HELL_SOUL_HP_MAX = 10;
	static int HELL_SOUL_KARMA_TOGIVE = 10;
	static int HEAVEN_SOUL_DMG = 1;
	static int HEAVEN_SOUL_HP_MAX = 10;
	static int HEAVEN_SOUL_KARMA_TOGIVE = -10;

	static int SOUL_HP = 1;

	// SPCL OPTIONS
	static int HELL_SPCL_DMG = 1;
	static int HELL_SPCL_HP_MAX = 1;
	static int HELL_SPCL_HP_TOGIVE = 5;
	static int HELL_SPCL_KARMA_TOGIVE = 10;
	static int HEAVEN_SPCL_DMG = 1;
	static int HEAVEN_SPCL_HP_MAX = 1;
	static int HEAVEN_SPCL_KARMA_TOGIVE = 10;

	static int SPCL_HP = 1;

	// OBSTACLE OPTIONS
	static int HELL_OBSTACLE_DMG = 0;
	static int HELL_OBSTACLE_HP_MAX = 1;
	static int HEAVEN_OBSTACLE_DMG = 0;
	static int HEAVEN_OBSTACLE_HP_MAX = 1;

	static int OBSTACLE_HP = 1;

	// MISSILE OPTIONS
	static int HELL_MISSILE_DMG = 1;
	static int HELL_MISSILE_HP_MAX = 1;
	static int HEAVEN_MISSILE_DMG = 1;
	static int HEAVEN_MISSILE_HP_MAX = 1;

	static int MISSILE_HP = 1;

	// DASH SIZE AND COOLDOWN
	static int DASH_SIZE = 10;
	static int DASH_CD = 5;

	// ENTITIES SIZE
	static int SOUL_HEIGHT = 2;
	static int SOUL_WIDTH = 2;
	static int NEST_HEIGHT = 3;
	static int NEST_WIDTH = 3;
	static int SPCL_HEIGHT = 5;
	static int SPCL_WIDTH = 5;

}
