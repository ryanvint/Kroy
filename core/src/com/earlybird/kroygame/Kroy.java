package com.earlybird.kroygame;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.earlybird.kroygame.screens.MainGameScreen;
import com.earlybird.kroygame.screens.StartScreen;

public class Kroy extends Game {
	
	public static final int WITDH = 1024;
	public static final int HEIGHT = 768;
	public static final String TITLE = "Kroy";
	
	public SpriteBatch batch;
	

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainGameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		
	}
}
