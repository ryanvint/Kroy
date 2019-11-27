package com.earlybird.kroygame;

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
	public static final int SPEED = 70;
	
	private TiledMap map;
	private TiledMapRenderer renderer;
	private OrthographicCamera camera;
	
	SpriteBatch batch;
	Texture img;
	float x;
	float y;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("firetruck.png");
		
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
		
		if (Gdx.input.isKeyPressed(Keys.W)) {
			y += SPEED * Gdx.graphics.getDeltaTime();
		}
		
		if (Gdx.input.isKeyPressed(Keys.S)) {
			y -= SPEED * Gdx.graphics.getDeltaTime();
		}
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			x -= SPEED * Gdx.graphics.getDeltaTime();
		}
		
		if (Gdx.input.isKeyPressed(Keys.D)) {
			x += SPEED * Gdx.graphics.getDeltaTime();
		}
		
		camera.update();
		renderer.setView(camera);
		renderer.render();
		
		batch.begin();
		batch.draw(img, x, y, 21, 21, 0, 0, 32, 32, false, false);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
