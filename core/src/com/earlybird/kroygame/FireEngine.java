package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FireEngine extends Unit {
	
	private int currentVolume, maxVolume; //Used for water statbars
	public boolean isRefilling, isSelected; //Status variables
	public StatBar waterBar; //Used to create the stat bar
	
	
	public FireEngine(TextureRegion texture) { //Instantiates a fire engine
		super();
		this.texture = texture;
		currentVolume = 100;
		maxVolume = 100;
		isRefilling = false;
		isSelected = false;
		this.waterBar = new StatBar(40,5, this.getMaxVolume(), Color.BLACK, Color.BLUE, Color.BLUE);
	}
	
	public boolean isEngineinRange(int leftX, int rightX, int topY, int bottomY) {
		if(this.getX()>=leftX && this.getX()<=rightX && this.getY()>=bottomY && this.getY()<=topY) {
			return true;
		}
		return false;
	}

	//Getters and Setters
	
	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}
	
	public StatBar getWaterBar() {
		return this.waterBar;
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

	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.getHealthBar().setValue(this.getCurrentHealth()); // probably should be in a different function
		this.getWaterBar().setValue(this.getCurrentVolume());
		super.draw(batch, parentAlpha);
	}
	
}
