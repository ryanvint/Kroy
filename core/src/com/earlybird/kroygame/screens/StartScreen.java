package com.earlybird.kroygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.SnapshotArray;
import com.earlybird.kroygame.Engine;
import com.earlybird.kroygame.FireEngine;
import com.earlybird.kroygame.Kroy;

/**
 * Screen shown at start of game
 * Allows starting and quitting the game
 * Starting the game switches the screen to MainGameScreen
 */
public class StartScreen implements Screen {

	private Stage stage;
	private TextButton startButton, quitButton;
	public Skin skin;
	public Kroy game;
	
	public StartScreen(Kroy game) {
		this.game = game;
	}
	
	/**
	 * On start of screen the buttons are displayed
	 */
	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);						
		skin = new Skin(Gdx.files.internal("uiskin.json"));
															
		startButton = new TextButton("Start", skin, "default");
		quitButton = new TextButton("Quit", skin, "default");
		
		TextButton[] buttonArray = {startButton, quitButton};
		
		Float spacing = 25f;
		for (TextButton button: buttonArray) {
			button.setWidth(200f);
			button.setHeight(20f);
			button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - spacing );
			stage.addActor(button);
			spacing += 25f;
		}
	
		
	}

	/*
	 * Draws the buttons and checks if they have been pressed
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		if (startButton.isPressed() == true){
			game.setScreen(new MainGameScreen(game));
			}
		
		if (quitButton.isPressed() == true) {
			Gdx.app.exit();
		}
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	

}