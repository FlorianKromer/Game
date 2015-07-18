package game.controllers;

import game.util.Constants;
import game.util.GamePreferences;
import game.views.CreatePlayerScreen;
import game.views.LoadingScreen;
import game.views.MapScreen;
import game.views.MenuPrincipalScreen;

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


	/**
	 * le personnage du joueur initialise lors de la creation
	 */
	public characters.Character player;
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
		this.player = null;
		manager = new AssetManager();

	}


	@Override
	public void create() {
		screenHashtable = new Hashtable<Integer, Screen>();
		screenHashtable.put(Constants.LOADINGSCREEN, new LoadingScreen(this));
		screenHashtable.put(Constants.MENUSCREEN, new MenuPrincipalScreen(this));
		screenHashtable.put(Constants.CREATEPLAYERSCREEN, new CreatePlayerScreen(this));
		screenHashtable.put(Constants.MAPSCREEN, new MapScreen(this));
		

		changeScreen(Constants.LOADINGSCREEN);
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
