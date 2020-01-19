package com.earlybird.kroygame.screens;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.earlybird.kroygame.*;
import com.earlybird.kroygame.pathfinding.*;


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
	/**
	 * Initializes the Main Game screen
	 * @param game
	 */
	public MainGameScreen(Kroy game) {
		super(game);	
		ExtendViewport viewport = new ExtendViewport(scrWidth, scrHeight);
		gameStage = new Stage(viewport);
		userInterface = new Stage(viewport);
		
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(gameStage);
		multiplexer.addProcessor(this);
	}
	
	/**
	 * Show runs first when the screen is opened
	 * This creates and sets up the map, all UI, fortresses and initial fire engines
	 */
	@Override
	public void show() {
        Gdx.input.setInputProcessor(multiplexer);
        //Camera and map initialized
		camera = new OrthographicCamera();
		camera.setToOrtho(false, scrWidth, scrHeight);
		camera.update();
		map = new TmxMapLoader().load("MapOfYork.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		//Map of roads made as a matrix of 0's and 1's -- Check Map class
		roadmap = new Map(this.map);
		
		skin = new Skin(Gdx.files.internal("uiskin.json"));	
		
		//UI initialized
		quitButton = new TextButton("Quit", skin, "default");
		pauseButton = new TextButton("Pause", skin, "default");
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
		
		//Sets locations and correct scaling for all buttons and labels
		quitButton.setBounds(1550, 900, 100f, 20f);
		//pauseButton.setBounds(1550, 900, 100f, 20f); 
		menuTitle.setBounds(1550, 930, 100f, 20f);
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
		
		//Adds buttons to stage
		//gameStage.addActor(pauseButton);
		gameStage.addActor(quitButton);
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
		
		//Listens for clicks on the quit button and exits the game if pressed
		quitButton.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
               
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	Gdx.app.exit();             
            	return true;
            }
		});
		
		//Listens for clicks on the pause button and pauses the game if pressed. Not implemented yet
		pauseButton.addListener(new InputListener(){ 
            @Override //Currently not implemented correctly
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                
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
		
		//All engine listeners listen for clicks on the engine buttons and selects/deselects that engine in game if clicked on
		engineOne.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { 
            	checkButtonEngineSelect(1);
            	return true;
            }      
                   
		});
				
		engineTwo.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	checkButtonEngineSelect(2);  
            	return true;
            }
		});
		
		engineThree.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	checkButtonEngineSelect(3);       	               	  
            	return true;       
            }
            
		});
				
		//Creates Engine groups for engines and selectedEngines which respectively hold
		//the engines not currently selected and those currently selected
		//Creates a group of Fortresses holding all fortresses in game
		engines = new Engines();
		selectedEngines= new Engines();
		fortresses = new Fortresses();
		
		//Adds initial fire engines to game
		addFireTruck(42,2,2,465,455);
		addFireTruck(46,2,3,335,325);
		addFireTruck(38,2,1,595,585);
				
		//Adds fortresses to game
		addFortress(18,17,game.res.minister);
		addFortress(42,12,game.res.centralHall);
		addFortress(4,12,game.res.railway);
		
		//Adds fire station to game
		addFireStation(42,3);
		
		//Adds all selected and unselected fire engines to the stage as well as all fortresses
		gameStage.addActor(engines);
		gameStage.addActor(selectedEngines);
		gameStage.addActor(fortresses);
		
	}
	
	/**
	 * Checks if any Engine contains fireEngine ID equal to the ID passed in
	 * If so the engine is moved from engines/selectedEngines to the other (selectedEngines/engines)
	 * and the texture is changed accordingly
	 * @param ID a FireEngine ID
	 */
	private void checkButtonEngineSelect(int ID) {
		if (engines.checkInEngines(ID)) {
           		engines.getFireEngineByID(ID).setTexture(game.res.firetruckSelected);
           		selectedEngines.addActor(engines.getEngineByID(ID));
    	} else {
               	if (selectedEngines.checkInEngines(ID)) {
               		selectedEngines.getFireEngineByID(ID).setTexture(game.res.firetruck);
               		engines.addActor(selectedEngines.getEngineByID(ID));
                } 
            }
    	}

	/**
	 * Checks if all fortresses are at 0 health
	 * If so the game is won and the game ends
	 */
	public void checkWinCondition() {
		boolean isAFortressAlive = false;
		for(int i = 0; i<fortresses.getChildren().size; i++) {
			Fortress thisFortress = (Fortress)fortresses.getChild(i);
			if(thisFortress.getCurrentHealth()!=0) {
				isAFortressAlive = true;
			}
		}
		if(!isAFortressAlive) {
			//Do win stuff
		}
	}
	
	/**
	 * Adds a fortress into the fortresses group and adds its health bar to the stage
	 * @param xTilePos the X tile the fortress will be placed on
	 * @param yTilePos the Y tile the fortress will be placed on
	 * @param texture the texture for the fortress being added
	 */
	public void addFortress(int xTilePos, int yTilePos, TextureRegion texture) { //Renders a Fortress at a specified XY location with a Texture allocated with in Resources.jv
		Fortress fortress = new Fortress(texture);
		fortress.setPosition(xTilePos * Resources.TILE_SIZE, yTilePos * Resources.TILE_SIZE);
		gameStage.addActor(fortress.getHealthBar());
		fortress.getHealthBar().setPosition(fortress.getX()+5, fortress.getY()+106);
		fortresses.addActor(fortress);
	}
	
	/**
	 * Adds a FireStation to the game and stage
	 * @param xTilePos
	 * @param yTilePos
	 */
	public void addFireStation(int xTilePos, int yTilePos) { //Renders a Firestation at a specified XY location with a Texture allocated with in Resources.jv
		firestation = new FireStation(game.res.firestation);
		firestation.setPosition(xTilePos * Resources.TILE_SIZE, yTilePos * Resources.TILE_SIZE);
		gameStage.addActor(firestation);
	}
	
	/**
	 * Adds a fireEngine with health/water bar into the game at the X,Y tile position passed in 
	 * Then adds this fireEngine to the Engines group
	 * Adds health and water bars to UI buttons
	 * @param xTilePos
	 * @param yTilePos
	 * @param iD
	 * @param yUIWater
	 * @param yUIHealth
	 */
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
	
	
	/**
	 * This is called every game tick, it updates the stage and actors states
	 * It also checks for fireEngines in range of fire station, the direction of fire engines, 
	 * when engines break, when fortresses need to change targets and if the game has been won
	 * @param delta
	 */
	public void update(float delta) {
		
		this.engines.setLastPostions();
		this.selectedEngines.setLastPostions();

		gameStage.act(delta);
		userInterface.act(delta);

		//Checks if all fireEngines in engines and selectedEngines are in range of fireStation
		//if so it refills them, runs checkEngineBroken on each engine
		for(int i=0; i<engines.getChildren().size; i++) {
			if(engines.getFireEngine(i).isInFireStationRange(firestation)) {
				firestation.refillEngine(engines.getFireEngine(i));
			}
			
			engines.getEngine(i).checkEngineBroken();
		}
		for(int i=0; i<selectedEngines.getChildren().size; i++) {
			if(selectedEngines.getFireEngine(i).isInFireStationRange(firestation)) {
				firestation.refillEngine(selectedEngines.getFireEngine(i));
			}
			selectedEngines.getEngine(i).checkEngineBroken();
		}
		
		//Checks all fortresses for if the target needs to be changed
		for(int i=0; i<fortresses.getChildren().size; i++) {
			fortresses.getFortress(i).changeFortressTarget(engines, selectedEngines);
		}
		
		//Checks if the game has been won
		checkWinCondition();
		
		gameStage.act(delta);
		userInterface.act(Gdx.graphics.getDeltaTime());
		
		//Changes direction of fire engines
		this.engines.updateDir();
		this.selectedEngines.updateDir();
	}

	/**
	 * Is called every frame. Renders everything on screen.
	 */
	@Override
	public void render(float delta) {
		
		update(delta);
		
		Gdx.gl.glClearColor(33/255f, 191/255f, 33/255f, 1);
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
	public void dispose() {
		super.dispose();
		gameStage.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return super.keyDown(keycode);
	}

	/**
	 * When a key is released it checks the value of that key
	 * If C all engines are deselected
	 * If X all currently selected engines stop attacking
	 * If escape the game quits
	 */
	@Override
	public boolean keyUp(int keycode) {
		switch (keycode)
	    {
		case Keys.C:
			deselectEngines();
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

	/**
	 * If the left mouse button is clicked first and last touch are set to the pixel pressed
	 */
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

	/**
	 * On the release of a left mouse click it either selects any engines in the selection box made
	 * or if its released on the same place it was pressed it checks if that location is a road and then
	 * moves all selected engines to that location on the road
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			//If a single click is on a road move the selected engines to that location
			if (this.firstTouch == this.lastTouch) {
				if (this.roadmap.isRoad(this.firstTouch) == true) {
					if (this.selectedEngines.hasChildren()) {
						SnapshotArray<Actor> children = this.selectedEngines.getChildren();
						Actor[] actors = children.begin();
						for (int i = 0, n = children.size; i < n; i++) {
							Engine child = (Engine) actors[i];
							child.clearActions();
							Vector2 truckcoords = new Vector2(child.getX(), child.getY());
							SequenceAction sequence = new SequenceAction();
							child.setTargetTile(checkTargetTile(new Vector2((float) Math.floor(this.lastTouch.x / 32),
									(float) Math.floor(this.lastTouch.y / 32))));
							List<Action> actions = this.roadmap.pathfind(truckcoords, child.getTargetTile(), child.getSpeed());
							for (Action a : actions) {
								sequence.addAction(a);
							}
							child.addAction(sequence);
						}
					}
				}
				//If a single click is made on a fortress make all selected engines attack that fortress if they are in range
				else if(fortresses.getFortressClicked(this.firstTouch)!=null) {
					Fortress thisFortress = fortresses.getFortressClicked(this.firstTouch);
					if (this.selectedEngines.hasChildren()) {
						for(int i=0; i<selectedEngines.getChildren().size; i++) {
						FireEngine thisFireEngine = selectedEngines.getFireEngine(i);
						thisFireEngine.setCurrentTarget(thisFortress);
						}	
					}
				}
				return true;
			}
			else {
				//If mouse was dragged between click and release deselect all engines before selecting new ones in selection range
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
	
	/**
	 * Selects all engines in between the firstTouch and lastTouch position
	 * When a engine is selected it is moved from the Engines group to the selectedEngines group
	 * and switches texture accordingly
	 */
	private void selectEngines() {
		//select engines
		SnapshotArray<Actor> children = this.engines.getChildren();
		Actor[] actors = children.begin();
		for (int i = 0, n = children.size; i < n; i++) {
			Actor child = actors[i];
			Vector2 childPos = new Vector2(child.getX(), child.getY());
			if (Utils.isBetween(this.firstTouch, this.lastTouch, childPos) == true) {
				//switch texture
				Engine thisEngine = (Engine) child;
				FireEngine thisFireEngine = (FireEngine)thisEngine.getChild(0);
				thisFireEngine.setTexture(game.res.firetruckSelected);
				this.selectedEngines.addActor(child);
			}
		}
	}
	
	/**
	 * Deselects all engines
	 * When a engine is deselected it is moved from the selectedEngines group to the Engines group
	 * and switches texture accordingly
	 */
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
	
	/**
	 * Checks the parameter tile to see if it is the target tile of another engine using checkIfTarget. If so, an array
	 *  of nodes is created with a node corresponding to the tile as the first node. The flood method is then called
	 *  passing in the array as well as two vectors for the bottom left and top right search area. If the tile is not
	 *  the target tile of another engine it returns the same tile.
	 * @param tile
	 * @return
	 */
	private Vector2 checkTargetTile(Vector2 tile){
		if (checkIfTarget(tile.x, tile.y) == false) {
			return tile;
		}
		List<Node> queue = new ArrayList<Node>();
		queue.add(new Node((int)tile.x, (int)tile.y));
		return flood(queue, new Vector2(tile.x - 5, tile.y - 5), new Vector2(tile.x + 5, tile.y + 5), 0);
		
	}
	
	/**
	 * recursively iterates through the nodes surrounds the nodes in queue until it finds a node that is not the
	 * TargetTile of another engine.
	 * @param queue	array of nodes
	 * @param a		bottom left of search area
	 * @param b		top right of search area
	 * @param c		count
	 * @return
	 */
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
		return flood(queue, a, b, c);
	}
	
	/**
	 * checks if the tile at x,y is the TargetTile of an Engine in engines or selectedEngines
	 * @param x
	 * @param y
	 * @return
	 */
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
