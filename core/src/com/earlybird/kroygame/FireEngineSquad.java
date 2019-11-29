package com.earlybird.kroygame;

import java.util.ArrayList;
import java.util.List;

//Class which stores all created fireEngine objects
public class FireEngineSquad {
	List<FireEngine> fireEngineSquad;
	
	public FireEngineSquad(){
		this.fireEngineSquad = new ArrayList<FireEngine>();
	}
	
	//Adds a new generic fireEngine to the list
	public void addEngine() {
		this.fireEngineSquad.add(new FireEngine());
	}
	
	//Changes isSelected variable for all fireEngines to false
	public void deselectEngines() {
		for(int i=0; i<fireEngineSquad.size(); i++) {
			fireEngineSquad.get(i).setSelected(false);
		}
	}
	
	//Returns a list of all fireEngines currently selected (isSelected == true)
	public List<FireEngine> getSelectedFireEngines(){
		List<FireEngine> selectedEngines = new ArrayList<FireEngine>();
		for(int i=0; i<fireEngineSquad.size(); i++) {
			if(fireEngineSquad.get(i).isSelected()) {
				selectedEngines.add(fireEngineSquad.get(i));
			}
		}
		return selectedEngines;
	}
	
	//Returns the size of the fireEngineSquad list
	public int getSize() {
		return fireEngineSquad.size();
	}
	
	//Returns the fireEngine at the index i given in the list
	public FireEngine getEngine(int i) {
		return fireEngineSquad.get(i);
	}
	
	//Returns list of fireEngines inside predefined square range
	public List<FireEngine> getEnginesInRange(int leftX, int rightX, int topY, int bottomY){
		List<FireEngine> enginesInRange = new ArrayList<FireEngine>();
		for(int i=0; i<fireEngineSquad.size(); i++) {
			if(fireEngineSquad.get(i).getCurrentLocationX()>=leftX && fireEngineSquad.get(i).getCurrentLocationX()<=rightX && fireEngineSquad.get(i).getCurrentLocationY()>=bottomY && fireEngineSquad.get(i).getCurrentLocationY()<=topY) {
				enginesInRange.add(fireEngineSquad.get(i));
			}
		}
		return enginesInRange;
	}
	
}
