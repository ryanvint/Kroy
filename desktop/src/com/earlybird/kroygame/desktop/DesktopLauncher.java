package com.earlybird.kroygame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.earlybird.kroygame.Kroy;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		//Sets window size and title
		config.width = Kroy.WITDH;
		config.height = Kroy.HEIGHT;
		config.title = Kroy.TITLE;
		config.resizable = false;
		config.foregroundFPS = 60;
		config.backgroundFPS = 20;
		
		
		new LwjglApplication(new Kroy(), config);
	}
}
