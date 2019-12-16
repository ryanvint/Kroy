package com.earlybird.kroygame;

import com.earlybird.kroygame.StatBar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

public class FireEngine extends Unit{

	private int currentVolume, maxVolume;
	public boolean isRefilling, isSelected;
	public StatBar waterBar;
	
	//Constructor
	public FireEngine(int currentHealth, int maxHealth, int range, int damage, int currentLocationX,
			int currentLocationY, int moveLocationX, int moveLocationY, int speed, int currentVolume, 
			int maxVolume, boolean isRefilling, boolean isSelected, int spriteSize) {
		super(currentHealth, maxHealth, range, damage, currentLocationX, currentLocationY, moveLocationX, moveLocationY, speed, spriteSize);
		this.currentVolume = currentVolume;
		this.maxVolume = maxVolume;
		this.isRefilling = isRefilling;
		this.isSelected = isSelected;
		this.waterBar = new StatBar(40,5, this.getMaxVolume(), Color.BLACK, Color.BLUE, Color.BLUE);
	}
	
	public FireEngine() {
		super();
		currentVolume = 110;
		maxVolume = 110;
		isRefilling = false;
		isSelected = false;
		this.waterBar = new StatBar(40,5, this.getMaxVolume(), Color.BLACK, Color.BLUE, Color.BLUE);
	}

	//Getters and Setters
	public int getWaterBarX() {
		return this.currentLocationX-10;
	}
	
	public int getWaterBarY() {
		return this.currentLocationY-13;
	}
	
	public int getCurrentVolume() {
		super.getCurrentVolume();
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
	
	public void changeCurrentVolume(int damageOutput) {
		if(this.getCurrentVolume() - damageOutput <= this.getMaxVolume()) {
			this.setCurrentVolume(this.getCurrentVolume() - damageOutput);
		}else {
			this.setCurrentVolume(this.getMaxVolume());
		}
		
	}
	
	public void destroy(){
		super.destroy();
		if(this.getCurrentHealth()<=0) {
			this.setDamage(0);
			this.setMaxHealth(0);
			this.setRange(0);
			this.setSpeed(0);
			this.healthBar.setVisible(false);
			this.waterBar.setVisible(false);
		}
		
	}
	
	public void increaseMaxVolume() {
		
	}
	
	public void move() {
		
	}
}

