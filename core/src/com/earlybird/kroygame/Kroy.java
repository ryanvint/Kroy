package com.earlybird.kroygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.earlybird.kroygame.screens.MainGameScreen;
import com.earlybird.kroygame.screens.StartScreen;

public class Kroy extends Game {
	
	public static final int WITDH = 52 * Resources.TILE_SIZE;
	public static final int HEIGHT = 30 * Resources.TILE_SIZE;
	public static final String TITLE = "Kroy";
	private Music musicBackground;
	
	
	public Resources res;
	

	@Override
	public void create () { 
		musicBackground = Gdx.audio.newMusic(Gdx.files.internal("DefinitlyNotHalo.mp3"));
		musicBackground.setLooping(true);
		musicBackground.setVolume((float) 0.5);
		musicBackground.play();
		res = new Resources();
		this.setScreen(new StartScreen(this));
	}
	
	@Override
	public void dispose () {
		res.dispose();
	}
}
