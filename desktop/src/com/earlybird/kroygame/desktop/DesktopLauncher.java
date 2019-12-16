package com.earlybird.kroygame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.earlybird.kroygame.Kroy;

public class DesktopLauncher {
	
	static void pack() {
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.maxWidth = 2048;
		settings.maxHeight = 2048;
		settings.pot = true;
		
		TexturePacker.process("C:\\Users\\tanay\\Documents\\GitHub\\Kroy\\assets", "packed", "game");
	}
	
	public static void main (String[] arg) {
		pack();
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
