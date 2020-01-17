package com.earlybird.kroygame.screens;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.earlybird.kroygame.Engines;
import com.earlybird.kroygame.FireEngine;
import com.earlybird.kroygame.FireStation;
import com.earlybird.kroygame.Fortress;
import com.earlybird.kroygame.Kroy;
import com.earlybird.kroygame.Map;
import com.earlybird.kroygame.Resources;
import com.earlybird.kroygame.StatBar;
import com.earlybird.kroygame.Unit;
import com.earlybird.kroygame.Utils;
import com.earlybird.kroygame.pathfinding.Node;
import com.earlybird.kroygame.Engine;
import com.earlybird.kroygame.Fortresses;


public class MainGameScreen extends DefaultScreen implements InputProcessor {
	
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
	private Button engineOne, engineTwo, engineThree;
	private Label score, nOne, nTwo, nThree;
	private Boolean paused;
	
	public Skin skin, engineSkin;
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Map roadmap;
	private InputMultiplexer multiplexer;
	
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
		
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(gameStage);
		multiplexer.addProcessor(this);
		
		
	}
	
	@Override
	public void show() {
        Gdx.input.setInputProcessor(multiplexer);
		selectionbox = new Texture("badlogic.jpg"); //Do we still need this?
		camera = new OrthographicCamera();
		camera.setToOrtho(false, scrWidth, scrHeight);
		camera.update();
		//map = new TmxMapLoader().load("testroadmap.tmx");
		map = new TmxMapLoader().load("MapOfYork.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		roadmap = new Map(this.map);
		
		skin = new Skin(Gdx.files.internal("uiskin.json"));	
		
		quitButton = new TextButton("Quit", skin, "default"); //Initiates all buttons and labels
		pauseButton = new TextButton("Pause", skin, "default"); //applys all styles and strings
		menuTitle = new TextField("Menu", skin, "default");
		scoreTitle = new TextField("Score", skin, "default");
		engineTitle = new TextField("Engines", skin, "default");
		shopTitle = new TextField("Shop", skin, "default");
		engineOne = new Button(skin, "default");
		engineTwo = new Button(skin, "default");
		engineThree = new Button(skin, "default");
		score = new Label("00000", skin, "default");
		nOne = new Label("No.1", skin, "default");
		nTwo = new Label("No.2", skin, "default");
		nThree = new Label("No.3", skin, "default");
		
		quitButton.setBounds(1550, 900, 100f, 20f); //Set location for all buttons and label and well
		//pauseButton.setBounds(1550, 900, 100f, 20f); //as putting buttons to correct size ie scaling
		menuTitle.setBounds(1550, 930, 100f, 20f); //them
		scoreTitle.setBounds(1550, 810, 100f, 20f);
		score.setBounds(1550, 780, 100f, 20f);
		shopTitle.setBounds(1550, 280, 100f, 20f);
		engineTitle.setBounds(1550, 720, 100f, 20f);
		nOne.setBounds(1550, 690, 100f, 20f);
		engineOne.setBounds(1550, 610, 80f, 80f);
		nTwo.setBounds(1550, 560, 100f, 20f);
		engineTwo.setBounds(1550, 480, 80f, 80f);
		nThree.setBounds(1550, 430, 100f, 20f);
		engineThree.setBounds(1550, 350, 80f, 80f);
		
		//gameStage.addActor(pauseButton);
		gameStage.addActor(quitButton); //Adds the buttons and labels to the gamestage
		gameStage.addActor(menuTitle);
		gameStage.addActor(scoreTitle);
		gameStage.addActor(score);
		gameStage.addActor(engineTitle);
		gameStage.addActor(shopTitle);
		gameStage.addActor(nOne);
		gameStage.addActor(engineOne);
		gameStage.addActor(nTwo);
		gameStage.addActor(engineTwo);		
		gameStage.addActor(nThree);
		gameStage.addActor(engineThree);
		
		quitButton.addListener(new InputListener(){ //Listens for any input on the quit Button and executes accordingly
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Press Up");
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	Gdx.app.exit();             
            	return true;
            }
		});
		
		pauseButton.addListener(new InputListener(){ //Listens for any input on the pause Button and executes accordingly
            @Override //Currently not implemented correctly
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Press Up");
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	if (paused == true) {
                	game.resume();
                	pauseButton.setName("Pause");
                	paused = false;
                } else {
                	game.pause();
                	pauseButton.setName("Continue");
                	paused = true;
                }               
            	return true;
            }
		});
		
		engineOne.addListener(new InputListener() { //Listens for any input on the engine no.1 button and executes accordingly
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            	System.out.println("Press Up");
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { 
            	if (checkInEngines(1)) {
            		for (int i = 0; i<engines.getChildren().size; i++) {
            			Engine thisEngine = (Engine)engines.getChild(i);
            			FireEngine thisFireEngine = (FireEngine)thisEngine.getChild(0);
            			int var = thisFireEngine.getiD();
            			if (var == 1) {
            				thisFireEngine.setTexture(game.res.firetruckSelected);
            				selectedEngines.addActor(engines.getChild(i));
            				break;
            			} else {
            				continue;
            			}	
            		}
            	} else {
            		for (int i = 0; i<selectedEngines.getChildren().size; i++) {
                   		Engine thisEngine = (Engine)selectedEngines.getChild(i);
                        FireEngine thisFireEngine = (FireEngine)thisEngine.getChild(0);
                        int var = thisFireEngine.getiD();
                       	if (var == 1) {
                       		thisFireEngine.setTexture(game.res.firetruck);
                       		engines.addActor(selectedEngines.getChild(i));
                       		break;
                        } else {
                       		continue;
                       	}
                    }
            	}
            return true;
            }      
                   
		});
				
		engineTwo.addListener(new InputListener() { //Listens for any input on the engine no.2 button and executes accordingly
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Press Up");
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	if (checkInEngines(2)) {
            		for (int i = 0; i<engines.getChildren().size; i++) {
            			Engine thisEngine = (Engine)engines.getChild(i);
            			FireEngine thisFireEngine = (FireEngine)thisEngine.getChild(0);
            			int var = thisFireEngine.getiD();
            			if (var == 2) {
            				thisFireEngine.setTexture(game.res.firetruckSelected);
            				selectedEngines.addActor(engines.getChild(i));
            				break;
            			} else {
            				continue;
            			}	
            		}
            	} else {
            		for (int i = 0; i<selectedEngines.getChildren().size; i++) {
                   		Engine thisEngine = (Engine)selectedEngines.getChild(i);
                        FireEngine thisFireEngine = (FireEngine)thisEngine.getChild(0);
                        int var = thisFireEngine.getiD();
                       	if (var == 2) {
                       		thisFireEngine.setTexture(game.res.firetruck);
                       		engines.addActor(selectedEngines.getChild(i));
                       		break;
                        } else {
                       		continue;
                       	}
                    }
            	}
            		return true;       
            }
		});
		
		engineThree.addListener(new InputListener(){ //Listens for any input on the engine no.3 button and executes accordingly
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Press Up");
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	if (checkInEngines(3)) {
            		for (int i = 0; i<engines.getChildren().size; i++) {
            			Engine thisEngine = (Engine)engines.getChild(i);
            			FireEngine thisFireEngine = (FireEngine)thisEngine.getChild(0);
            			int var = thisFireEngine.getiD();
            			if (var == 3) {
            				thisFireEngine.setTexture(game.res.firetruckSelected);
            				selectedEngines.addActor(engines.getChild(i));
            				break;
            			} else {
            				continue;
            			}	
            		}
            	} else {
            		for (int i = 0; i<selectedEngines.getChildren().size; i++) {
                   		Engine thisEngine = (Engine)selectedEngines.getChild(i);
                        FireEngine thisFireEngine = (FireEngine)thisEngine.getChild(0);
                        int var = thisFireEngine.getiD();
                       	if (var == 3) {
                       		thisFireEngine.setTexture(game.res.firetruck);
                       		engines.addActor(selectedEngines.getChild(i));
                       		break;
                        } else {
                       		continue;
                       	}
                    }
            	}            	               	  
            return true;       
            }
            
		});
				
		engines = new Engines();
		selectedEngines= new Engines();
		fortresses = new Fortresses();
		
		addFireTruck(42,2,2,465,455);
		addFireTruck(46,2,3,335,325);
		addFireTruck(38,2,1,595,585);
				
		addFortress(18,17,game.res.minister);
		addFortress(42,12,game.res.centralHall);
		addFortress(4,12,game.res.railway);
		addFireStation(42,3);
		
		gameStage.addActor(engines);
		gameStage.addActor(selectedEngines);
		gameStage.addActor(fortresses);
		
	}

	public boolean checkInEngines(int x) {
		for (int i = 0; i<engines.getChildren().size; i++) {
        	Engine thisEngine = (Engine)engines.getChild(i);
        	FireEngine thisFireEngine = (FireEngine)thisEngine.getChild(0);
        	int var = thisFireEngine.getiD();
        	if (var == x) {
        		return true;
        	} else {
        		continue;
        	}
        }
		return false;
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
	public void addFireTruck(int xTilePos, int yTilePos, int iD, int yUIWater, int yUIHealth) {
		Engine engine = new Engine();
		FireEngine fireEngine = new FireEngine(game.res.firetruck, iD);
		engine.addActor(fireEngine);
		engine.addActor(fireEngine.getHealthBar());
		engine.addActor(fireEngine.getWaterBar());
		engine.getChild(1).setPosition(-4, -7);
		engine.getChild(2).setPosition(-4, -14);
		engine.setPosition(xTilePos * Resources.TILE_SIZE, yTilePos * Resources.TILE_SIZE);
		engines.addActor(engine);
		gameStage.addActor(fireEngine.getUserInterfaceHealthBar());
		gameStage.addActor(fireEngine.getUserInterfaceWaterBar());
		fireEngine.getUserInterfaceHealthBar().setPosition(1550, yUIWater);
		fireEngine.getUserInterfaceWaterBar().setPosition(1550, yUIHealth);
	}
	
	
	
	public void update(float delta) {
		
		setLastPostions(this.engines);
		setLastPostions(this.selectedEngines);
		//loop through engines for target locations, if the same as another change it
		
		//for ever engine in engines and selected engines check(looping) if there is an engines in their tileTarget
		//check between enemies and enemies for junction halting
		//check between engines and engines for junction halting
		//check between units so they stop in adjacent tiles
		//if engine is stopped, check if its moveToLocation tile is different to its current tile
		//if so, check next tile to see if engine can move into it (sure if this is best)
		

		gameStage.act(delta);
		userInterface.act(delta);

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
		
		for(int i=0; i<fortresses.getChildren().size; i++) {
			Fortress thisFortress = (Fortress) fortresses.getChild(i);
			changeFortressTarget(thisFortress, engines, selectedEngines);
		}
		
		gameStage.act(delta);
		userInterface.act(Gdx.graphics.getDeltaTime());
		
		updateDir(this.engines);
		updateDir(this.selectedEngines);
	}

	public void changeFortressTarget(Fortress fortress, Engines engines, Engines selectedEngines) {
		if(fortress.isAttacking()) {
			if(!fortress.isCurrentTargetValid(fortress.getCurrentTarget())) {
				fortress.setNewTarget(engines, selectedEngines);
			}
		}
		else {
			fortress.setNewTarget(engines, selectedEngines);
		}
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
	public void render(float delta) {
		
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
		//userInterface.draw();
		
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
							Engine child = (Engine) actors[i];
							child.clearActions();
							Vector2 truckcoords = new Vector2(child.getX(), child.getY());
							SequenceAction sequence = new SequenceAction();
//							replace 0.2f with engine's speed
							child.setTargetTile(checkTargetTile(new Vector2((float) Math.floor(this.lastTouch.x / 32),
									(float) Math.floor(this.lastTouch.y / 32))));
//							or set list of actions
							List<Action> actions = this.roadmap.pathfind(truckcoords, child.getTargetTile(), 0.2f);
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
	private Vector2 checkTargetTile(Vector2 tile){
		if (checkIfTarget(tile.x, tile.y) == false) {
			return tile;
		}
		List<Node> queue = new ArrayList<Node>();
		queue.add(new Node((int)tile.x, (int)tile.y));
		return flood(queue, new Vector2(tile.x - 5, tile.y - 5), new Vector2(tile.x + 5, tile.y + 5), 0);
		
	}
	private Vector2 flood(List<Node> queue, Vector2 a, Vector2 b , int c) {
		List<Node> list = new ArrayList<Node>();
		for (int i = c, n = queue.size(); i < n; i++) {
			List<Node> testNodes = new ArrayList<Node>();
			testNodes.add(new Node(queue.get(i).getX() + 1, queue.get(i).getY()));
			testNodes.add(new Node(queue.get(i).getX() - 1, queue.get(i).getY()));
			testNodes.add(new Node(queue.get(i).getX(), queue.get(i).getY() + 1));
			testNodes.add(new Node(queue.get(i).getX(), queue.get(i).getY() - 1));
			for (Node t : testNodes) {
				if (queue.contains(t) == false && list.contains(t) == false && Utils.isBetween(a, b, new Vector2(t.getX(), t.getY())) == true
						&& this.roadmap.isRoad(new Vector2(t.getX() * 32, t.getY() * 32))) {
					t.setF(c + 1);
					list.add(t);
				}
			}
		}
		for (Node n : list) {
			if (checkIfTarget(n.getX(), n.getY()) == false) {
				return (new Vector2(n.getX(), n.getY()));
			}
		}
		c = queue.size();
		for (Node n : list) {
			queue.add(n);
		}
		System.out.println("ERROR");
		return flood(queue, a, b, c);
	}
	private boolean checkIfTarget(float x, float y) {
		for (Actor actor : this.engines.getChildren()) {
			Engine e = (Engine) actor;
			if (e.getTargetTile().x == x && e.getTargetTile().y == y) {
				return true;
			}
		}
		for (Actor actor : this.selectedEngines.getChildren()) {
			Engine e = (Engine) actor;
			if (e.getTargetTile().x == x && e.getTargetTile().y == y) {
				return true;
			}
		}
		return false;
	}
}
