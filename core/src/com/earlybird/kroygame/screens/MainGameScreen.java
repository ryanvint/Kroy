package com.earlybird.kroygame.screens;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.earlybird.kroygame.Engines;
import com.earlybird.kroygame.FireEngine;
import com.earlybird.kroygame.FireStation;
import com.earlybird.kroygame.Fortress;
import com.earlybird.kroygame.Kroy;
import com.earlybird.kroygame.Map;
import com.earlybird.kroygame.Resources;
import com.earlybird.kroygame.SelectedEngines;
import com.earlybird.kroygame.Engine;


public class MainGameScreen extends DefaultScreen {
	
	public static final int scrWidth = 32 * Resources.TILE_SIZE;
	public static final int scrHeight = 24 * Resources.TILE_SIZE;
	
	private Stage gameStage; //Used as a base to add all sprites to the Game using the scene2D library
	private Engines engines; //Used to group all fire engines
	private SelectedEngines selectedEngines; //Allows user to select more than one engine at a time
	
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Map roadmap;
	
	private Vector2 firstTouch;
	private Vector2 lastTouch;
	private boolean clicked;
	
	Texture selectionbox;
	

	public MainGameScreen(Kroy game) {
		super(game);	
		ExtendViewport viewport = new ExtendViewport(scrWidth, scrHeight);
		gameStage = new Stage(viewport);
		
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		selectionbox = new Texture("badlogic.jpg"); //Do we still need this?
		camera = new OrthographicCamera();
		camera.setToOrtho(false, scrWidth, scrHeight);
		camera.update();
		map = new TmxMapLoader().load("testroadmap.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		roadmap = new Map(this.map);
		
		engines = new Engines();
		selectedEngines= new SelectedEngines();
		
		addFireTruck(4,1);
		addFireTruck(4,12);
		addFortress(17,6);
		addFireStation(25,13);
		
		gameStage.addActor(engines);
		gameStage.addActor(selectedEngines);
		
	}

	public void addFortress(int xTilePos, int yTilePos) { //Renders a Fortress at a specified XY location with a Texture allocated with in Resources.jv
		Fortress fortress1 = new Fortress(game.res.fortress1);
		fortress1.setPosition(xTilePos * Resources.TILE_SIZE, yTilePos * Resources.TILE_SIZE);
		gameStage.addActor(fortress1);
	}
	
	public void addFireStation(int xTilePos, int yTilePos) { //Renders a Firestation at a specified XY location with a Texture allocated with in Resources.jv
		FireStation firestation = new FireStation(game.res.firestation);
		firestation.setPosition(xTilePos * Resources.TILE_SIZE, yTilePos * Resources.TILE_SIZE);
		gameStage.addActor(firestation);
	}
	
	//Adds a fireEngine with health/water bar into the game at the X,Y tile position passed in
	//Then adds this fireEngine to the Engines group
	public void addFireTruck(int xTilePos, int yTilePos) {
		Engine engine = new Engine();
		FireEngine fireEngine = new FireEngine(game.res.firetruck);
		engine.addActor(fireEngine);
		engine.addActor(fireEngine.healthBar);
		engine.addActor(fireEngine.waterBar);
		engine.getChild(1).setPosition(0, -5);
		engine.getChild(2).setPosition(0, -10);
		engine.setPosition(xTilePos * Resources.TILE_SIZE, yTilePos * Resources.TILE_SIZE);
		engines.addActor(engine);
	}
	
	public void update(float delta) {
		//for ever engine in engines and selected engines check(looping) if there is an engines in their tileTarget
		//check between enemies and enemies for junction halting
		//check between engines and engines for junction halting
		//check between units so they stop in adjacent tiles
		//if engine is stopped, check if its moveToLocation tile is different to its current tile
		//if so, check next tile to see if engine can move into it (sure if this is best)
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
		
		//change selectedEngines colour
		
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
		gameStage.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode)
	    {
		case Keys.CONTROL_RIGHT:
			info();
			return true;
		case Keys.C:
			deselectEngines();
			write("Deselected");
			return true;
		case Keys.ESCAPE:
			Gdx.app.exit();
	    }
		return super.keyUp(keycode);
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return super.keyTyped(character);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			this.firstTouch = new Vector2(screenX, Gdx.graphics.getHeight()- screenY);
			this.lastTouch = firstTouch;
			this.clicked = true;
			return true;
		}
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			if (this.firstTouch == this.lastTouch) {
				if (this.roadmap.isRoad(this.firstTouch) == true) {
					if (this.selectedEngines.hasChildren()) {
//						---------stuff between '-' can go into its own method
						SnapshotArray<Actor> children = this.selectedEngines.getChildren();
						Actor[] actors = children.begin();
						for (int i = 0, n = children.size; i < n; i++) {
							Actor child = actors[i];
							child.clearActions();
							Vector2 truckcoords = new Vector2(child.getX(), child.getY());
							SequenceAction sequence = new SequenceAction();
//							replace 0.2f with engine's speed
//							setmovelocation of fireengine to lasttouch
//							or set list of actions
							write("gg");
							List<Action> actions = this.roadmap.pathfind(truckcoords, this.lastTouch, 0.2f);
							for (Action a : actions) {
								sequence.addAction(a);
							}
							child.addAction(sequence);
						}
//						deselectEngines();
//						------------
					}
				}
				//move actors in selectedEngines to this.firstTouch
				//set move location for all actors in selectedEngines
				return true;
			}
			else {
				selectEngines();
			}
			return true;
		}
		return super.touchUp(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		lastTouch = new Vector2(screenX, Gdx.graphics.getHeight()- screenY);
		return true;
//		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return super.mouseMoved(screenX, screenY);
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return super.scrolled(amount);
	}
	
//	is point 3 between point 1 and 2?
	private boolean isBetween(Vector2 point1, Vector2 point2, Vector2 point3) {
		Vector2 big = new Vector2(0, 0);
		Vector2 small = new Vector2(0, 0);
		if (point1.x < point2.x) {
			big.x = point2.x;
			small.x = point1.x;
		}
		else {
			big.x = point1.x;
			small.x = point2.x;
		}
		if (point1.y < point2.y) {
			big.y = point2.y;
			small.y = point1.y;
		}
		else {
			big.y = point1.y;
			small.y = point2.y;
		}
		if (point3.x > small.x & point3.x < big.x
				& point3.y > small.y & point3.y < big.y) {
			return true;
			}
		else {
			return false;
		}
	}
	//to save time when debugging
	private void write(String text) {
		System.out.println(text);
	}
	private void info() {
		write("--------------");
		System.out.println(this.lastTouch);
		System.out.println(roadmap.isRoad(lastTouch));
		write(selectedEngines.toString());
		write(engines.toString());
	}
	private void selectEngines() {
		//select engines
		SnapshotArray<Actor> children = this.engines.getChildren();
		Actor[] actors = children.begin();
		for (int i = 0, n = children.size; i < n; i++) {
			Actor child = actors[i];
			Vector2 childPos = new Vector2(child.getX(), child.getY());
			if (isBetween(this.firstTouch, this.lastTouch, childPos) == true) {
				write("Engine selected");
				this.selectedEngines.addActor(child);
			}
		}
	}
	private void deselectEngines() {
		SnapshotArray<Actor> children = this.selectedEngines.getChildren();
		Actor[] actors = children.begin();
		for (int i = 0, n = children.size; i < n; i++) {
			Actor child = actors[i];
			this.engines.addActor(child);
		}
	}
}
