package com.earlybird.kroygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.earlybird.kroygame.FireEngine;
import com.earlybird.kroygame.FireEngineSquad;
import com.earlybird.kroygame.Kroy;
import com.earlybird.kroygame.Map;
import com.earlybird.kroygame.Resources;


public class MainGameScreen extends DefaultScreen {
	
	public static final int scrWidth = 32 * Resources.TILE_SIZE;
	public static final int scrHeight = 24 * Resources.TILE_SIZE;
	
	
//	public SpriteBatch batch;
	private Stage gameStage;
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private boolean clicked;
	private int boxstartx, boxstarty, mousex, mousey;
	private Map roadmap;
	
	Texture selectionbox;
	Texture firetruck;
	FireEngineSquad fireSquad = new FireEngineSquad();

	public MainGameScreen(Kroy game) {
		super(game);
//		batch = new SpriteBatch();
		
		ExtendViewport viewport = new ExtendViewport(scrWidth, scrHeight);
		gameStage = new Stage(viewport);
		
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		selectionbox = new Texture("badlogic.jpg");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, scrWidth, scrHeight);
		camera.update();
		map = new TmxMapLoader().load("testroadmap.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		roadmap = new Map(this.map);
		firetruck = new Texture("firetruck.png");
	}
	public void update(float delta) {
		gameStage.act(delta);
	}
	@Override
	public void render(float delta)
	{
		update(delta);
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		renderer.setView(camera);
		renderer.render();	
		
		gameStage.getBatch().begin();
		Color c = gameStage.getBatch().getColor();
		gameStage.getBatch().draw(firetruck, 32 * 4, 32 * 3);
		gameStage.getBatch().setColor(c.r, c.g, c.b, 1f);
		if (this.clicked == true) {
			gameStage.getBatch().setColor(c.r, c.g, c.b, .1f);
			gameStage.getBatch().draw(selectionbox, this.boxstartx, this.boxstarty, mousex - this.boxstartx, mousey - this.boxstarty);
		}
		gameStage.getBatch().end();
		gameStage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		gameStage.getViewport().update(width, height, true);
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
		super.dispose();
//		batch.dispose();
		gameStage.dispose();
		selectionbox.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)) {
			System.out.println(this.boxstartx);
			System.out.println(this.boxstarty);
			System.out.println(this.mousex);
			System.out.println(this.mousey);
			System.out.println(this.clicked);
		}
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			this.boxstartx = screenX;
			this.boxstarty = Gdx.graphics.getHeight()- screenY;
			this.mousex = this.boxstartx;
			this.mousey = this.boxstarty;
			this.clicked = true;
			return true;   
		}
		else {
			return false;
		}
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		//clear selected stuff e.g. selected engines
		if (button == Input.Buttons.LEFT) {
			System.out.println("Hello");
			int bigx;
			int smallx;
			int bigy;
			int smally;
			this.clicked = false;
			if (this.boxstartx < this.mousex) {
				bigx = this.mousex;
				smallx = this.boxstartx;
			}
			else {
				bigx = this.boxstartx;
				smallx = this.mousex;
			}
			if (this.boxstarty < this.mousey) {
				bigy = this.mousey;
				smally = this.boxstarty;
			}
			else {
				bigy = this.boxstarty;
				smally = this.mousey;
			}
			for(int i=0; i<fireSquad.getSize(); i++) {
				FireEngine currentEngine = fireSquad.getEngine(i);
				if (currentEngine.getCurrentLocationX() > smallx & currentEngine.getCurrentLocationX() < bigx
					& currentEngine.getCurrentLocationY() > smally & currentEngine.getCurrentLocationY() < bigy) {
					currentEngine.isSelected();
				}
			}
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
			this.mousex = screenX;
			this.mousey = Gdx.graphics.getHeight() - screenY;
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
