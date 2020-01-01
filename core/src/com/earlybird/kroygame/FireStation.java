package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class FireStation extends Entity {
	
	public int currentRefilling;
	public int maxCapacity;
	private int refillRate;
	
	public FireStation(Sprite sprite) {
		super();
		this.sprite = sprite;
		this.currentRefilling = 0;
		this.maxCapacity = 1;
		this.refillRate = 10;
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
