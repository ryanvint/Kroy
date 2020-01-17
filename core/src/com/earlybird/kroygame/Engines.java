package com.earlybird.kroygame;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Group;

public class Engines extends Group {

	public Engines() {	//Class 'Engines' which is an extension of 'Group' 
						//Scene2d groups store actors together so that actions
	}					//can be performed on all actors within group
						//The Engines Group stores together all the engines on the map
						//We instantiate the engines group twice within the code;
						//firstly SelectedEngines and also Engines. Engines stores all engines 
						//that are on the map and SelectedEngines contains all Engines that are currently selected.
	
	public FireEngine getFireEngine(int index) {    					//Returns a fire engine actor
		return (FireEngine)((Engine)this.getChild(index)).getChild(0);
	}
	
	public Engine getEngine(int index) {								//Returns a fire engine actor and it accompanying statbars
		return (Engine)this.getChild(index);
	}

}
