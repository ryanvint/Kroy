package com.earlybird.kroygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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


public class MainGameScreen implements Screen {

	private TiledMap map;
	private TiledMapRenderer renderer;
	private OrthographicCamera camera;
	
	Texture fireTruckImg;
	
	//Creates new fireEngine squad
	FireEngineSquad fireSquad = new FireEngineSquad();
	
	Kroy game;

	public MainGameScreen(Kroy game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		fireTruckImg = new Texture("firetruck.png");
		
		//Adds a fireEngine entity to the game inside of the squad
		fireSquad.addEngine();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 768);
		camera.update();
		
		map = new TmxMapLoader().load("MapTest.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1f / 1.5f);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
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
		
		//Renders all fireEngines in game
		for(int i=0; i<fireSquad.getSize(); i++) {
			FireEngine currentEngine = fireSquad.getEngine(i);
			game.batch.draw(fireTruckImg, currentEngine.getCurrentLocationX(), currentEngine.getCurrentLocationY(), 20, 23, 0, 0, 32, 32, false, false);
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
	}

}
