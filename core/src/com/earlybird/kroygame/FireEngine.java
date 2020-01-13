package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class FireEngine extends Unit {
	
	private int currentVolume, maxVolume, waterRate; //Used for water statbars
	public boolean isRefilling, isSelected; //Status variables
	public StatBar waterBar; //Used to create the stat bar
	
	
	public FireEngine(TextureRegion texture) { //Instantiates a fire engine
		super();
		this.texture = texture;
		currentVolume = 100;
		maxVolume = 100;
		waterRate = 20;
		isRefilling = false;
		isSelected = false;
		this.waterBar = new StatBar(40,5, this.getMaxVolume(), Color.BLACK, Color.BLUE, Color.BLUE);
	}
	
	public boolean isEngineinRange(Vector2 bottomLeft, Vector2 topRight) {
		if(this.getX()>=bottomLeft.x && this.getX()<=topRight.x && this.getY()>=bottomLeft.y && this.getY()<=topRight.y) {
			return true;
		}
		return false;
	}
	
	public void changeWater(int change) {
		this.setCurrentVolume(this.getCurrentVolume() + change);
		if(this.getCurrentVolume()<0) {
			this.setCurrentVolume(0);
		}
		else if(this.getCurrentVolume()>this.getMaxVolume()) {
			this.setCurrentVolume(this.getMaxVolume());
		}
	}
	
	public void attackFortress(Fortress fortress) {
		if(fortress.getCurrentHealth()>0 && this.getCurrentVolume()>0) {
			this.changeWater(-this.getWaterRate());
			fortress.changeHealth(-this.getDamage());
		}
	}

	//Getters and Setters
	
	public void setWaterRate(int rate) {
		this.waterRate = rate;
	}
	
	public int getWaterRate() {
		return this.waterRate;
	}
	
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
		this.getWaterBar().setValue(this.getCurrentVolume());
		super.draw(batch, parentAlpha);
	}
	
}
