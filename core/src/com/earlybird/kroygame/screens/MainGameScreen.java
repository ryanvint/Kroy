package com.earlybird.kroygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
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
	private Map roadmap;
	
	Texture selectionbox;
	Texture firetruck;

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
	}
}
