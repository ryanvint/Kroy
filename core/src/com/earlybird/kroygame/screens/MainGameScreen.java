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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.earlybird.kroygame.FireEngine;
import com.earlybird.kroygame.FireEngineSquad;
import com.earlybird.kroygame.Kroy;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class MainGameScreen implements Screen, InputProcessor {

	private TiledMap map;
	private TiledMapRenderer renderer;
	private OrthographicCamera camera;
	private boolean clicked;
	private int boxstartx;
	private int boxstarty;
	private int mousex;
	private int mousey;
//	private ShapeRenderer shapeRenderer;
	
	Texture fireTruckImg;
	Texture selectionbox;
	//Creates new fireEngine squad
	FireEngineSquad fireSquad = new FireEngineSquad();
	
	Kroy game;

	public MainGameScreen(Kroy game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		fireTruckImg = new Texture("firetruck.png");
		selectionbox = new Texture("badlogic.jpg");
		//Adds a fireEngine entity to the game inside of the squad
		fireSquad.addEngine();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 768);
		camera.update();
		map = new TmxMapLoader().load("MapTest.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1f / 1.5f);
//		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		//Loop listening to FireEngine changes
		//Checks for movement or selected status changes of any spawned fireEngines
		for(int i=0; i<fireSquad.getSize(); i++) {
			FireEngine currentEngine = fireSquad.getEngine(i);
			currentEngine.movement(currentEngine.getSpeed());
			currentEngine.setSelectedUnit();
		}
		
		camera.update();
		renderer.setView(camera);
		renderer.render();
		
		//Rendering sprites
		game.batch.begin();
		Color c = game.batch.getColor();
		game.batch.setColor(c.r, c.g, c.b, 1f);
		//Renders all fireEngines in game
		for(int i=0; i<fireSquad.getSize(); i++) {
			FireEngine currentEngine = fireSquad.getEngine(i);
			game.batch.draw(fireTruckImg, currentEngine.getCurrentLocationX(), currentEngine.getCurrentLocationY(), 20, 23, 0, 0, 32, 32, false, false);
		}
//		if (this.clicked = true) {
//			shapeRenderer.begin(ShapeType.Line);
//			shapeRenderer.rect(this.boxstartx,this.boxstarty,mousex - this.boxstartx,mousey - this.boxstarty);
//			shapeRenderer.end();
//		}
		if (this.clicked == true) {
			game.batch.setColor(c.r, c.g, c.b, .1f);
			game.batch.draw(selectionbox, this.boxstartx, this.boxstarty, mousex - this.boxstartx, mousey - this.boxstarty);
		}
		game.batch.end();
		//System.out.println("Cursor: (" + Gdx.input.getX() +"," + Gdx.input.getY() + "), Truck: (" + FireEngine1.getCurrentLocationX() + "," + FireEngine1.currentLocationY + ")");
	
		
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
		game.batch.dispose();
		fireTruckImg.dispose();
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
