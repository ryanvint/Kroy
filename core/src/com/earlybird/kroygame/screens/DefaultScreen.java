package com.earlybird.kroygame.screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.earlybird.kroygame.Kroy;
/**
 * Allows checking for inputs across all screens
 */
public abstract class DefaultScreen implements Screen, InputProcessor{

	protected Kroy game;
	
	DefaultScreen(Kroy _game)
	{
		game = _game;
	}
	
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}


	@Override
	public boolean keyDown(int keycode) {
		return false;
	}


	@Override
	public boolean keyUp(int keycode) {
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		return false;
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}


	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
