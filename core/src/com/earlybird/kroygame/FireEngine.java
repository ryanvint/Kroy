package com.earlybird.kroygame;

import com.earlybird.kroygame.HealthBar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class FireEngine extends Unit{

	private int currentVolume, maxVolume;
	public boolean isRefilling, isSelected;
	public HealthBar healthBar;
	
	//Constructor
	public FireEngine(int currentHealth, int maxHealth, int range, int damage, int currentLocationX,
			int currentLocationY, int moveLocationX, int moveLocationY, int speed, int currentVolume, 
			int maxVolume, boolean isRefilling, boolean isSelected, int spriteSize, HealthBar healthBar) {
		super(currentHealth, maxHealth, range, damage, currentLocationX, currentLocationY, moveLocationX, moveLocationY, speed, spriteSize);
		this.currentVolume = currentVolume;
		this.maxVolume = maxVolume;
		this.isRefilling = isRefilling;
		this.isSelected = isSelected;
		this.healthBar = new HealthBar(40,5, this.getMaxHealth());
	}
	
	public FireEngine() {
		super();
		currentVolume = 100;
		maxVolume = 100;
		isRefilling = false;
		isSelected = false;
		this.healthBar = new HealthBar(40, 5, this.getMaxHealth());
	}

	//Getters and Setters
	public int getCurrentHealth() {
		return (int) this.healthBar.getValue();
	}
	
	public void setCurrentHealth(int health) {
		this.healthBar.setValue(health);
	}
	
	public int getHealthBarX() {
		return this.currentLocationX-10;
	}
	
	public int getHealthBarY() {
		return this.currentLocationY+25;
	}
	
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
	
	//Checks if the left mouse button is clicked and if at that time the cursor is in the units hitbox, if so returns true
	//Note that the cursor input finds the top left to be 0,0 and the rendering/sprite location finds the bottom left to be 0,0 thus the Hight-Y
	public boolean isUnitClicked(){
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && isInHitBox(Gdx.input.getX(), (Kroy.HEIGHT - Gdx.input.getY()))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//If a unit is clicked isSelected is set to true
	//need a way to check all units and de-select them once a new unit is selected
	public void setSelectedUnit() {
		if(isUnitClicked()) {
			isSelected = true;
		}
	}
	
	public void increaseCurrentVolume() {
		
	}
	
	public void decreaseCurrentVolume() {
		
	}
	
	public void increaseMaxVolume() {
		
	}
	
	public void move() {
		
	}
}

