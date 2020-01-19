package com.earlybird.kroygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.earlybird.kroygame.screens.StartScreen;

/**
 * Kroy is the Game class which instantiates the game
 * and its resources
 */
public class Kroy extends Game {
	
	public static final int WITDH = 52 * Resources.TILE_SIZE;
	public static final int HEIGHT = 30 * Resources.TILE_SIZE;
	public static final String TITLE = "Kroy";
	private Music musicBackground;
	
	public Resources res;

	/**
	 * Sets and starts game music, loads resources, sets screen to start screen
	 */
	@Override
	public void create () { 
		//If code errors on this it's an error with the gradle build
		//Delete everything from the current workspace and re-import with gradle
		musicBackground = Gdx.audio.newMusic(Gdx.files.internal("bensound-scifi.mp3"));
		musicBackground.setLooping(true);
		musicBackground.setVolume((float) 0.10);
		musicBackground.play();
		res = new Resources();
		this.setScreen(new StartScreen(this));
	}
	
	@Override
	public void dispose () {
		res.dispose();
	}
}
