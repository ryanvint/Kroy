package com.earlybird.kroygame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Engines is a group holding all instances of Engine
 * This in the main is used as engines and selectedEngines to group together
 * the engines that are selected and those that aren't
 */
public class Engines extends Group {

	public Engines() {
						
	}
	
	/**
	 * Gets the FireEngine instance from the index given in the group
	 * @param index the index in the group of the FireEngine you want returned
	 * @return FireEngine
	 */
	public FireEngine getFireEngine(int index) {
		Engine thisEngine = (Engine) this.getChild(index);
		return thisEngine.getFireEngine();
	}
	
	/**
	 * Gets the FireEngine instance with a given ID from within the group. 
	 * @param ID of the FireEngine you want returned
	 * @return FireEngine if one exists with the ID given, if not returns null
	 */
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
	
	/**
	 * Gets the Engine instance with the given ID from within the group
	 * @param ID of the Engine you want returned
	 * @return Engine if one exists with the ID given, if not returns null
	 */
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
	
	/**
	 * Checks if a fireEngine with the given ID exists within the group
	 * @param ID to check if its in the group
	 * @return true if the ID is in the group, false if not
	 */
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
	
	/**
	 * Sets the last position of the engines
	 */
	public void setLastPostions() {
		if (this.hasChildren() == true) {
			for (Actor a : this.getChildren()) {
				Engine e = (Engine) a;
				Unit u = (Unit) e.getChild(0);
				u.setLastPos(u.localToStageCoordinates(new Vector2(0,0)));
			}
		}
	}
	
	/**
	 * Updates the direction of the engines in the group
	 */
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
	
	/**
	 * Gives the Engine in the group at a given index
	 * @param index
	 * @return Engine at index
	 */
	public Engine getEngine(int index) {
		return (Engine)this.getChild(index);
	}

}
