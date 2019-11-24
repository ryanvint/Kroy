package com.earlybird.kroygame;
public class FireEngine extends Unit{

	private int currentVolume, maxVolume;
	public boolean isRefilling, isSelected;
	
	//Constructor
	public FireEngine(int currentHealth, int maxHealth, int range, int damage, int currentLocationX,
			int currentLocationY, int moveLocationX, int moveLocationY, int speed, int currentVolume, int maxVolume, boolean isRefilling, boolean isSelected) {
		super(currentHealth, maxHealth, range, damage, currentLocationX, currentLocationY, moveLocationX, moveLocationY, speed);
		this.currentVolume = currentVolume;
		this.maxVolume = maxVolume;
		this.isRefilling = isRefilling;
		this.isSelected = isSelected;
	}

	//Getters and Setters
	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}

	public int getMaxVolume() {
		return maxVolume;
	}

	public void setMaxVolume(int maxVolume) {
		this.maxVolume = maxVolume;
	}

	public boolean isRefilling() {
		return isRefilling;
	}

	public void setRefilling(boolean isRefilling) {
		this.isRefilling = isRefilling;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	//Methods
	public void increaseCurrentVolume() {
		
	}
	
	public void decreaseCurrentVolume() {
		
	}
	
	public void increaseMaxVolume() {
		
	}
	
	public void move() {
		
	}
}

