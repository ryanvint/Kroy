package com.earlybird.kroygame;

import java.awt.Dimension;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Kroy extends ApplicationAdapter {
	
	//Used in desktop build to set screen size and title
	public static final int WITDH = 1024;
	public static final int HEIGHT = 768;
	public static final String TITLE = "Kroy";
	
	
	private TiledMap map;
	private TiledMapRenderer renderer;
	private OrthographicCamera camera;
	
	SpriteBatch batch;
	Texture fireTruckImg;
	
	//Creates new fireEngine squad
	FireEngineSquad fireSquad = new FireEngineSquad();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
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
	public void render () {
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
		batch.begin();
		
		//Renders all fireEngines in game
		for(int i=0; i<fireSquad.getSize(); i++) {
			FireEngine currentEngine = fireSquad.getEngine(i);
			batch.draw(fireTruckImg, currentEngine.currentLocationX, currentEngine.currentLocationY, 24, 24, 0, 0, 32, 32, false, false);
		}
		
		batch.end();
		
		//System.out.println("Cursor: (" + Gdx.input.getX() +"," + Gdx.input.getY() + "), Truck: (" + FireEngine1.getCurrentLocationX() + "," + FireEngine1.currentLocationY + ")");
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		fireTruckImg.dispose();
	}
}
