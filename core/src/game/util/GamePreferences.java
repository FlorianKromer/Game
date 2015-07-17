/*******************************************************************************
 * Copyright 2013 Andreas Oehlke
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


package game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

public class GamePreferences {

	public static final String TAG = GamePreferences.class.getName();

	public static final GamePreferences instance = new GamePreferences();

	public boolean sound;
	public boolean music;
	public float volSound;
	public float volMusic;
	public int charSkin;
	public boolean showFpsCounter;
	public boolean useMonochromeShader;
	//stats
	public long timePlayed;
	public int nbPandaPlayed;
	public int nbPikachuPlayed;
	public int nbMarioPlayed;
	//succes
	public boolean suc_creerUnePartie;
	//sauvegarde
	public int saveNumber;

	private Preferences prefs;

	// singleton: prevent instantiation from other classes
	private GamePreferences () {
		prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
	}

	public void load () {
		sound = prefs.getBoolean("sound", true);
		music = prefs.getBoolean("music", true);
		volSound = MathUtils.clamp(prefs.getFloat("volSound", 0.5f), 0.0f, 1.0f);
		volMusic = MathUtils.clamp(prefs.getFloat("volMusic", 0.5f), 0.0f, 1.0f);
		charSkin = MathUtils.clamp(prefs.getInteger("charSkin", 0), 0, 2);
		showFpsCounter = prefs.getBoolean("showFpsCounter", false);
		useMonochromeShader = prefs.getBoolean("useMonochromeShader", false);
		//stats
		timePlayed = prefs.getLong("timePlayed",0);
		nbPandaPlayed = prefs.getInteger("nbKikouPlayed", 0);
		nbPikachuPlayed = prefs.getInteger("nbPikachuPlayed", 0);
		nbMarioPlayed = prefs.getInteger("nbMarioPlayed", 0);
		//succes
		suc_creerUnePartie = prefs.getBoolean("creerUnePartie",true);
		//sauvegarde
		saveNumber = prefs.getInteger("saveNumber",0);
	}

	public void save () {
		prefs.putBoolean("sound", sound);
		prefs.putBoolean("music", music);
		prefs.putFloat("volSound", volSound);
		prefs.putFloat("volMusic", volMusic);
		prefs.putInteger("charSkin", charSkin);
		prefs.putBoolean("showFpsCounter", showFpsCounter);
		prefs.putBoolean("useMonochromeShader", useMonochromeShader);
		//stats
		prefs.putLong("timePlayed", timePlayed);
		prefs.getInteger("nbKikouPlayed", nbPandaPlayed);
		prefs.getInteger("nbPikachuPlayed", nbPikachuPlayed);
		prefs.getInteger("nbMarioPlayed",nbMarioPlayed);
		//succes
		prefs.putBoolean("creerUnePartie", suc_creerUnePartie);
		//sauvegarde
		prefs.putInteger("saveNumber", saveNumber);
		//on enregistre
		prefs.flush();
	}

}
