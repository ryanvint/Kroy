package com.earlybird.kroygame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
		Engine thisEngine = (Engine) this.getChild(index);
		return thisEngine.getFireEngine();
	}
	
	public FireEngine getFireEngineByID(int ID) {
		if(this.checkInEngines(ID)) {
			for (int i = 0; i<this.getChildren().size; i++) {
	        	int var = this.getFireEngine(i).getID();
	        	if (var == ID) {
	        		return this.getFireEngine(i);
	        	} else {
	        		continue;
	        	}
	        }
			return null;
		}
		return null;
	}
	
	public Engine getEngineByID(int ID) {
		if(this.checkInEngines(ID)) {
			for (int i = 0; i<this.getChildren().size; i++) {
	        	int var = this.getFireEngine(i).getID();
	        	if (var == ID) {
	        		return this.getEngine(i);
	        	} else {
	        		continue;
	        	}
	        }
			return null;
		}
		return null;
	}
	
	public boolean checkInEngines(int ID) {
		for (int i = 0; i<this.getChildren().size; i++) {
        	int var = this.getFireEngine(i).getID();
        	if (var == ID) {
        		return true;
        	} else {
        		continue;
        	}
        }
		return false;
	}
	
	public void setLastPostions() {
		if (this.hasChildren() == true) {
			for (Actor a : this.getChildren()) {
				Engine e = (Engine) a;
				Unit u = (Unit) e.getChild(0);
				u.setLastPos(u.localToStageCoordinates(new Vector2(0,0)));
			}
		}
	}
	
	public void updateDir() {
		if (this.hasChildren() == true) {
			for (Actor a : this.getChildren()) {
				Engine e = (Engine) a;
				Unit u = (Unit) e.getChild(0);
				int d = Utils.findDir(u.getLastPos(), u.localToStageCoordinates(new Vector2(0,0)));
				if (d != -1) {
					u.setDir(d);
				}
			}
		}

	}
	
	public Engine getEngine(int index) {								//Returns a fire engine actor and it accompanying statbars
		return (Engine)this.getChild(index);
	}

}
