package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FireStation extends Entity {
	
	private int currentRefilling;				//Boolean variable to signify if an individual fire engine is refilling
	private int maxCapacity;						
	private int refillRate;						//Shows how fast the engine can refill
	
	public FireStation(TextureRegion texture) {	//Instantiates a fire engine
		super();
		this.texture = texture;
		this.currentRefilling = 0;		
		this.maxCapacity = 1;
		this.refillRate = 1;
	}
	
	public void refillEngine(FireEngine engine) {	//Method  to refill fire engine
		engine.changeHealth(refillRate);			//Fire engine that is refilling needs to be passed through
		engine.changeWater(refillRate);
	}
	
	//getters and setters
	
	public int getCurrentRefilling() {
		return this.currentRefilling;
	}
	
	public void setCurrentRefilling(int numberRefilling) {
		this.currentRefilling = numberRefilling;
	}
	
	public int getMaxCapacity() {
		return this.maxCapacity;
	}
	
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	
	public int getRefillRate() {
		return this.refillRate;
	}
	
	public void setRefillRate(int refillRate) {
		this.refillRate = refillRate;
	}
	
}
