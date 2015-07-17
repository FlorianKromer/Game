
package game.util;

public class Constants {

	// GUI
	public static final float VIEWPORT_GUI_WIDTH = 1024.0f;
	public static final float VIEWPORT_GUI_HEIGHT = 480.0f;
	
	// Location of description file for texture atlas
	public static final String TEXTURE_ATLAS_OBJECTS = "images/canyonbunny.pack";
	public static final String TEXTURE_ATLAS_UI = "images/canyonbunny-ui.pack";
	public static final String TEXTURE_ATLAS_LIBGDX_UI = "images/uiskin.atlas";

	//Screen
	public final static int LOADINGSCREEN = 0;
	public final static int MENUSCREEN = 10;
	public final static int CREATEPLAYERSCREEN = 20;
//	public final static int CHATSCREEN = 30;
	public final static int BATTLESCREEN = 40;
//	public final static int RESULTSCREEN = 50;
//	public final static int DICOSCREEN = 60;
//	public final static int FINALSCREEN = 70;
	public static final int MAPSCREEN = 80;
	
	// Game preferences file
	public static final String PREFERENCES = "kikou.prefs";

	//Game state
	public static final int STARTGAME=1;

	//Action
	public static final int CONNEXION=1;
	public static final int NOUVEAU=2;
	public static final int LANCERSKILL = 3;
	public static final int ATTAQUEMONSTRE = 4;
	public static final int MESSAGE = 5;
	public static final int PRET = 6;
	public static final int TOKEN = 7;
	public static final int TOKENTOUR = 8;
	public static final int LANCERSOIN = 9;
	public static final int DECO = 0;
	
	//Effet
	public static final String blockedKey = "blocked";
	
	//classType
	public static final int KOALA = 0;
	public static final int PIKACHU = 1;
	public static final int MARIO = 2;
	
	
	
	
}
