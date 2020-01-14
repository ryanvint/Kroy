package com.earlybird.kroygame.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.earlybird.kroygame.Engines;
import com.earlybird.kroygame.FireEngine;
import com.earlybird.kroygame.FireStation;
import com.earlybird.kroygame.Fortress;
import com.earlybird.kroygame.Kroy;
import com.earlybird.kroygame.Map;
import com.earlybird.kroygame.Resources;
import com.earlybird.kroygame.Unit;
import com.earlybird.kroygame.Utils;
import com.earlybird.kroygame.Engine;
import com.earlybird.kroygame.Fortresses;


public class MainGameScreen extends DefaultScreen {
	
	public static final int scrWidth = 52 * Resources.TILE_SIZE;
	public static final int scrHeight = 30 * Resources.TILE_SIZE;
	
	private Stage gameStage;//Used as a base to add all sprites to the Game using the scene2D library
	private Stage userInterface;
	private Engines engines; //Used to group all fire engines
	private Engines selectedEngines; //Allows user to select more than one engine at a time
	private Fortresses fortresses;
	private FireStation firestation;
	
	private TextButton pauseButton, quitButton;
	private TextField menuTitle, scoreTitle, engineTitle, shopTitle;
	public Skin skin;
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Map roadmap;
	
	private Vector2 firstTouch;
	private Vector2 lastTouch;
	private boolean clicked;
	boolean click;
	
	
	Texture selectionbox;
	

	public MainGameScreen(Kroy game) {
		super(game);	
		ExtendViewport viewport = new ExtendViewport(scrWidth, scrHeight);
		gameStage = new Stage(viewport);
		userInterface = new Stage(viewport);
		
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		selectionbox = new Texture("badlogic.jpg"); //Do we still need this?
		camera = new OrthographicCamera();
		camera.setToOrtho(false, scrWidth, scrHeight);
		camera.update();
		//map = new TmxMapLoader().load("testroadmap.tmx");
		map = new TmxMapLoader().load("MapOfYork.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		roadmap = new Map(this.map);
		
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		quitButton = new TextButton("Quit", skin, "default");
		pauseButton = new TextButton("Pause", skin, "default");
		menuTitle = new TextField("Menu", skin, "default");
		scoreTitle = new TextField("Score", skin, "default");
		engineTitle = new TextField("Engines", skin, "default");
		shopTitle = new TextField("Shop", skin, "default");
		quitButton.setBounds(1550, 870, 100f, 20f);
		pauseButton.setBounds(1550, 900, 100f, 20f);
		menuTitle.setBounds(1550, 930, 100f, 20f);
		scoreTitle.setBounds(1550, 810, 100f, 20f);
		shopTitle.setBounds(1550, 250, 100f, 20f);
		engineTitle.setBounds(1550, 720, 100f, 20f);
		
		userInterface.addActor(pauseButton);
		userInterface.addActor(quitButton);
		userInterface.addActor(menuTitle);
		userInterface.addActor(scoreTitle);
		userInterface.addActor(engineTitle);
		userInterface.addActor(shopTitle);
		
		engines = new Engines();
		selectedEngines= new Engines();
		fortresses = new Fortresses();
		
		addFireTruck(42,2);
		addFireTruck(46,2);
		
		addFortress(18,17,game.res.fortress1);
		addFireStation(42,3);

		
		gameStage.addActor(engines);
		gameStage.addActor(selectedEngines);
		gameStage.addActor(fortresses);
		
	}

	public void addFortress(int xTilePos, int yTilePos, TextureRegion texture) { //Renders a Fortress at a specified XY location with a Texture allocated with in Resources.jv
		Fortress fortress = new Fortress(texture);
		fortress.setPosition(xTilePos * Resources.TILE_SIZE, yTilePos * Resources.TILE_SIZE);
		gameStage.addActor(fortress.getHealthBar());
		fortress.getHealthBar().setPosition(fortress.getX()+5, fortress.getY()+106);
		fortresses.addActor(fortress);
	}
	
	public void addFireStation(int xTilePos, int yTilePos) { //Renders a Firestation at a specified XY location with a Texture allocated with in Resources.jv
		firestation = new FireStation(game.res.firestation);
		firestation.setPosition(xTilePos * Resources.TILE_SIZE, yTilePos * Resources.TILE_SIZE);
		gameStage.addActor(firestation);
	}
	
	//Adds a fireEngine with health/water bar into the game at the X,Y tile position passed in
	//Then adds this fireEngine to the Engines group
	public void addFireTruck(int xTilePos, int yTilePos) {
		Engine engine = new Engine();
		FireEngine fireEngine = new FireEngine(game.res.firetruck);
		engine.addActor(fireEngine);
		engine.addActor(fireEngine.getHealthBar());
		engine.addActor(fireEngine.getWaterBar());
		engine.getChild(1).setPosition(-4, -7);
		engine.getChild(2).setPosition(-4, -14);
		engine.setPosition(xTilePos * Resources.TILE_SIZE, yTilePos * Resources.TILE_SIZE);
		engines.addActor(engine);
	}
	
	public void update(float delta) {
		
		setLastPostions(this.engines);
		setLastPostions(this.selectedEngines);
		
		//for ever engine in engines and selected engines check(looping) if there is an engines in their tileTarget
		//check between enemies and enemies for junction halting
		//check between engines and engines for junction halting
		//check between units so they stop in adjacent tiles
		//if engine is stopped, check if its moveToLocation tile is different to its current tile
		//if so, check next tile to see if engine can move into it (sure if this is best)
		
		//Checks if all fireEngines in engines and selectedEngines are in range of fireStation
		//if so it refills them
		for(int i=0; i<engines.getChildren().size; i++) {
			if(engines.getFireEngine(i).isInFireStationRange(firestation)) {
				firestation.refillEngine(engines.getFireEngine(i));
			}
		}
		for(int i=0; i<selectedEngines.getChildren().size; i++) {
			if(selectedEngines.getFireEngine(i).isInFireStationRange(firestation)) {
				firestation.refillEngine(selectedEngines.getFireEngine(i));
			}
		}
		
		gameStage.act(delta);
		userInterface.act(Gdx.graphics.getDeltaTime());
		
		updateDir(this.engines);
		updateDir(this.selectedEngines);
	}
	
	private void setLastPostions(Engines engines) {
		if (engines.hasChildren() == true) {
			for (Actor a : engines.getChildren()) {
				Engine e = (Engine) a;
				Unit u = (Unit) e.getChild(0);
				u.setLastPos(u.localToStageCoordinates(new Vector2(0,0)));
			}
		}
	}
	private void updateDir(Engines engines) {
		if (engines.hasChildren() == true) {
			for (Actor a : engines.getChildren()) {
				Engine e = (Engine) a;
				Unit u = (Unit) e.getChild(0);
				int d = findDir(u.getLastPos(), u.localToStageCoordinates(new Vector2(0,0)));
				if (d != -1) {
					u.setDir(d);
				}
			}
		}
	}
	
	private int findDir(Vector2 a, Vector2 b) {
		if (b.y > a.y) {
			return 0;
		}
		if (b.x < a.x) {
			return 1;
		}
		if (b.y < a.y) {
			return 2;
		}
		if (b.x > a.x) {
			return 3;
		}
		return -1;
	}

	@Override
	public void render(float delta)
	{
		if (click == true) {
			delta = 0;
		}
		update(delta);
		
		Gdx.gl.glClearColor(33/255f, 191/255f, 33/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		renderer.setView(camera);
		renderer.render();
		
		//change selectedEngines colour
		
		gameStage.getBatch().begin();
		gameStage.getBatch().end();
		gameStage.draw();
		userInterface.draw();
		
		if (pauseButton.isPressed() == true) {
			click = true;
		}  
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
		case Keys.X:
			for(int i=0; i<selectedEngines.getChildren().size;i++) {
				selectedEngines.getFireEngine(i).setCurrentTarget(null);
			}
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
							List<Action> actions = this.roadmap.pathfind(truckcoords, this.lastTouch, 0.2f);
							for (Action a : actions) {
								sequence.addAction(a);
							}
							child.addAction(sequence);
						}
						//deselectEngines();
//						------------
					}
				}
				else if(fortresses.getFortress(this.firstTouch)!=null) {
					//Check if selected firetrucks are in range of attacking 
					Fortress thisFortress = fortresses.getFortress(this.firstTouch);
					if (this.selectedEngines.hasChildren()) {
						for(int i=0; i<selectedEngines.getChildren().size; i++) {
						FireEngine thisFireEngine = selectedEngines.getFireEngine(i);
						thisFireEngine.setCurrentTarget(thisFortress);
						}	
					}
				}
				//move actors in selectedEngines to this.firstTouch
				//set move location for all actors in selectedEngines
				return true;
			}
			else {
				deselectEngines();
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
			if (Utils.isBetween(this.firstTouch, this.lastTouch, childPos) == true) {
				write("Engine selected");
				//switch texture
				Engine thisEngine = (Engine) child;
				FireEngine thisFireEngine = (FireEngine)thisEngine.getChild(0);
				thisFireEngine.setTexture(game.res.firetruckSelected);
				this.selectedEngines.addActor(child);
			}
		}
	}
	private void deselectEngines() {
		SnapshotArray<Actor> children = this.selectedEngines.getChildren();
		Actor[] actors = children.begin();
		for (int i = 0, n = children.size; i < n; i++) {
			Actor child = actors[i];
			//switch texture
			Engine thisEngine = (Engine) child;
			FireEngine thisFireEngine = (FireEngine)thisEngine.getChild(0);
			thisFireEngine.setTexture(game.res.firetruck);
			this.engines.addActor(child);
		}
	}
}
