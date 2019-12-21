package com.earlybird.kroygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.earlybird.kroygame.Kroy;

//implements the Screen class
//Screen to be shown at the start of the game

public class StartScreen implements Screen {

	private Stage stage; 										//creates a new instantiation of the Stage class
	private TextButton startButton, optionsButton, quitButton;  //creates instantiations of Button class for each button needed on screen. 
	public Skin skin; 											//creates a new instantiation of Skin class to allow for menu aesthetics 
	public Kroy game; 											// creates a new instantiation of Kroy which allows us to run this screen through the base file.
				
	
	
	public StartScreen(Kroy game) { 							//StartScreen Constructor which needs Kroy to be passed into it
		this.game = game;
	}
	
	@Override
	public void show() {													//Called when this screen becomes the current game screen
		stage = new Stage();												//Creates a new instantiation of the Stage class
		Gdx.input.setInputProcessor(stage);						
		skin = new Skin(Gdx.files.internal("uiskin.json"));					//This creates a need Skin with contains all the assets needed to render the menu
																			//allows for fonts and pictures
		startButton = new TextButton("Start", skin, "default");				//These full instantiate the buttons on the menu with needed Text, Skin file and
		optionsButton = new TextButton("Options", skin, "default");			//font type
		quitButton = new TextButton("Quit", skin, "default");
		
		TextButton[] buttonArray = {startButton, optionsButton, quitButton};//Array of all buttons on the menu allows for easier addition of the wanted buttons
																			//to the current stage and set there position and shape.
		Float spacing = 25f;												//Spacing starting value
		for (TextButton button: buttonArray) {								//For loop iteration through buttonArray
			button.setWidth(200f);											//How the button is shaped X
			button.setHeight(20f);											//How the button is shaped Y
			button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - spacing ); //Position of button on stage
			stage.addActor(button);											//Adding the current button to the stage
			spacing += 25f;													//Spacing incremeneted value
		}
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());								//
		stage.draw();														//Draws the stage in the current render() call
		
		if (startButton.isPressed() == true){								//Action to take when startButton is pressed
			game.setScreen(new MainGameScreen(game));						//Takes the current screen and closes it then sets the current screen to MainGame
		}
		
		if (optionsButton.isPressed() == true) {							//Action to take when optionsButton is pressed
			System.out.print("Options Pressed"+"\n");							
		}
		
		if (quitButton.isPressed() == true) {								//Action to take when quitButton is pressed
			System.out.print("Quit Pressed"+"\n");
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