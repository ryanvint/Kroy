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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.earlybird.kroygame.FireEngine;
import com.earlybird.kroygame.FireEngineSquad;
import com.earlybird.kroygame.Kroy;


public class MainGameScreen implements Screen {

	private TiledMap map;
	private TiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Stage stage;
	
	Texture fireTruckImg;
	
	//Creates new fireEngine squad
	FireEngineSquad fireSquad = new FireEngineSquad();
	Kroy game;

	public MainGameScreen(Kroy game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		stage = new Stage();
		
		fireTruckImg = new Texture("firetruck.png");
		
		//Adds a fireEngine entity to the game inside of the squad
		fireSquad.addEngine();
		
		//Adds fireEngine healthBar to stage
		stage.addActor(fireSquad.getEngine(0).healthBar);
		stage.addActor(fireSquad.getEngine(0).waterBar);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 768);
		camera.update();
		
		map = new TmxMapLoader().load("MapTest.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1.0f / 1.56f);
		
		System.out.println();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Loop listening to FireEngine changes
		//Checks for movement or selected status changes of any spawned fireEngines
		//Changes healthBar location and value for each fireEngine as they move
		for(int i=0; i<fireSquad.getSize(); i++) {
			FireEngine currentEngine = fireSquad.getEngine(i);
			currentEngine.movement(currentEngine.getSpeed());
			currentEngine.setSelectedUnit();
			currentEngine.healthBar.setPosition(currentEngine.getHealthBarX(), currentEngine.getHealthBarY());
			currentEngine.healthBar.setValue(currentEngine.getCurrentHealth());
			currentEngine.waterBar.setPosition(currentEngine.getWaterBarX(), currentEngine.getWaterBarY());
			currentEngine.waterBar.setValue(currentEngine.getCurrentVolume());
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
		
		//Renders the stage (HealthBar)
		stage.draw();
		stage.act();
		
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
