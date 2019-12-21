package com.earlybird.kroygame;

import com.badlogic.gdx.Game;

import com.earlybird.kroygame.screens.MainGameScreen;
import com.earlybird.kroygame.screens.StartScreen;

public class Kroy extends Game {
	
	public static final int WITDH = 32 * Resources.TILE_SIZE;
	public static final int HEIGHT = 24 * Resources.TILE_SIZE;
	public static final String TITLE = "Kroy";
	
	public Resources res;
	

	@Override
	public void create () {

		res = new Resources();
		this.setScreen(new StartScreen(this));
	}
	
	@Override
	public void dispose () {
		res.dispose();
	}
}
