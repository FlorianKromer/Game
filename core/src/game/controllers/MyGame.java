package game.controllers;

import game.models.world.Joueur;
import game.util.GamePreferences;
import game.views.LoadingScreen;
import game.views.MapScreen;
import game.views.MenuPrincipalScreen;
import game.views.TiledTest;

import java.util.ArrayList;
import java.util.Hashtable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Ici on initialise tout les screen
 * Depart du jeu appelle dans les main
 * @author Florian
 *
 */
public class MyGame extends Game{


	private Hashtable<Integer,Screen> screenHashtable;

	public final static int LOADINGSCREEN = 0;
	public final static int MENUSCREEN = 10;
	public final static int NEWCHARACTERSCREEN = 20;
	public final static int CHATSCREEN = 30;
	public final static int BATTLESCREEN = 40;
	public final static int RESULTSCREEN = 50;
	public final static int DICOSCREEN = 60;
	public final static int FINALSCREEN = 70;
	public static final int MAPSCREEN = 80;


	/**
	 * le personnage du joueur initialise lors de la creation
	 */
	public Joueur player;
	/**
	 * interface qui permet l'appel a des methodes propres a android
	 */
	public UITrick androidUI;

	public ArrayList<String> listHost ;


	public int currentVagueIndex;

	public static AssetManager manager;

	public int currentScreen;

	public MyGame(UITrick actionResolver) {
		super();
		this.androidUI = actionResolver;

		manager = new AssetManager();

	}


	@Override
	public void create() {
		screenHashtable = new Hashtable<Integer, Screen>();
		screenHashtable.put(LOADINGSCREEN, new LoadingScreen(this));
		screenHashtable.put(MENUSCREEN, new MenuPrincipalScreen(this));
		screenHashtable.put(MAPSCREEN, new TiledTest(this));

		changeScreen(0);
	}

	/**
	 * methode permettant de changer d'ecran
	 * @param nextScreen la variable int public correspondant 
	 */
	public void changeScreen(int nextScreen){
		if(screenHashtable.containsKey(nextScreen)){
			setScreen(screenHashtable.get(nextScreen));
			currentScreen =(nextScreen);
		}
	}



	@Override
	public void dispose(){

		GamePreferences.instance.load();
		GamePreferences prefs = GamePreferences.instance;
		prefs.timePlayed += TimeUtils.millis() - AbstractScreen.getTimePlayed();
		prefs.save();
		//System.err.println("fini:"+prefs.timePlayed/1000+"sec");

	}



}
