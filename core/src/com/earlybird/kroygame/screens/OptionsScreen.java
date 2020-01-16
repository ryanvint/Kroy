																																					package com.earlybird.kroygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.earlybird.kroygame.Engine;
import com.earlybird.kroygame.FireEngine;

public class OptionsScreen implements Screen {
	
	private TextButton backButton, musicOnButton, musicOffButton;
	
	
	
	public OptionsScreen() {
		super();
	}

	@Override
	public void show() {
		Stage stage = new Stage();												
		Gdx.input.setInputProcessor(stage);						
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));		
		
		backButton = new TextButton("Back", skin, "default");
		musicOnButton = new TextButton("Music On", skin, "default");
		musicOffButton = new TextButton("Music Off", skin, "default");
		
		TextButton[] buttonArray = {backButton, musicOnButton, musicOffButton};//Array of all buttons on the menu allows for easier addition of the wanted buttons
		//to the current stage and set there position and shape.
		Float spacing = 25f;												//Spacing starting value
		for (TextButton button: buttonArray) {								//For loop iteration through buttonArray
			button.setWidth(200f);											//How the button is shaped X
			button.setHeight(20f);											//How the button is shaped Y
			button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - spacing ); //Position of button on stage
			stage.addActor(button);											//Adding the current button to the stage
			spacing += 25f;		
		}
			
		backButton.addListener(new InputListener() { //Listens for any input on the engine no.2 button and executes accordingly
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Press Up");
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	System.out.println("Press Down");     
            	return true;
            }
           
		});
		
		}
		

	@Override
	public void render(float delta) {
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

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
