package com.mygdx.game.desktop;

import game.controllers.MyGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "GAME";
		cfg.width = 1024;
		cfg.height = 480;
		new LwjglApplication(new MyGame(new JavaHelp(cfg)), cfg);
	}
}
