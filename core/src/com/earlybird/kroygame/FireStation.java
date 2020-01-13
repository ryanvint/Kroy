package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FireStation extends Entity {
	
	public int currentRefilling;
	public int maxCapacity;
	private int refillRate;
	
	public FireStation(TextureRegion texture) {
		super();
		this.texture = texture;
		this.currentRefilling = 0;
		this.maxCapacity = 1;
		this.refillRate = 1;
	}
	
	public void refillEngine(FireEngine engine) {
		engine.changeHealth(refillRate);
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
