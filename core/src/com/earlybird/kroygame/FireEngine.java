package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * FireEngine extends Unit thus it is a movable Entity
 * This contains all variables and methods that FireEngines
 * need to attack and have their values checked
 */
public class FireEngine extends Unit {
	
	private int currentVolume, maxVolume, waterRate, ID;
	private boolean isRefilling, isSelected;
	private StatBar waterBar, userInterfaceWaterBar;
	
	private Fortress currentTarget;

	public FireEngine(TextureRegion texture, int ID) {
		super();
		this.ID = ID;
		this.texture = texture;
		currentVolume = 200;
		maxVolume = 200;
		waterRate = 2;
		isRefilling = false;
		isSelected = false;
		currentTarget = null;
		this.waterBar = new StatBar(40,5, this.getMaxVolume(), Color.BLACK, Color.BLUE, Color.BLUE);
		this.userInterfaceWaterBar = new StatBar(80,10, this.getMaxVolume(), Color.BLACK, Color.BLUE, Color.BLUE);
		this.setMaxHealth(300);
		this.setCurrentHealth(300);
		this.setDamage(1);
		this.setHealthBar(new StatBar(40,5, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN));
		this.setUserInterfaceHealthBar(new StatBar(80,10, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN));
	}
	
	/**
	 * Checks that currentVolume is greater than 0
	 * @return true if greater than 0
	 */
	public boolean isEnoughWater() { //Method to check that a fire engines currentVolume is not empty 
		if(this.getCurrentVolume()>0) return true;
		return false;
	}
	
	/**
	 * checks that currentHealth is greater than 0
	 * @return true if greater than 0
	 */
	public boolean isEnoughHealth() {
		if(this.getCurrentHealth()>0) return true;
		return false;
	}
	
	/**
	 * Changes the currentVolume value and ensures it stays within the bounds of maxVolume and 0
	 * @param change the amount to change by, negative value for decreasing
	 */
	public void changeWater(int change) {
		this.setCurrentVolume(this.getCurrentVolume() + change);
		if(this.getCurrentVolume()<0) {
			this.setCurrentVolume(0);
		}
		else if(this.getCurrentVolume()>this.getMaxVolume()) {
			this.setCurrentVolume(this.getMaxVolume());
		}
	}
	
	/**
	 * Deals damage to current target
	 */
	public void dealDamage() { //Method used to deal Damage to enemies using the fire engines currentVolumen and WaterRate
		if(currentTarget.getCurrentHealth()>0 && isEnoughWater()) {
			this.changeWater(-this.getWaterRate());
			currentTarget.changeHealth(-this.getDamage());
		}
	}
	
	/**
	 * Checks if the FireEngine is able to attack the current target
	 * If so it deals damage to it, if not the target is set to null
	 */
	public void attackFortress() {
		if(this.canEntityAttackEntity(currentTarget) && this.isEnoughWater() && this.isEnoughHealth()) {
			this.dealDamage();
		}
		else {
			this.setCurrentTarget(null);
		}
	}
	
	/**
	 * Checks if the fireEngine is currently attacking anything
	 * @return True if fireEngine has a currentTarget
	 */
	public boolean isAttacking() {
		if(this.getCurrentTarget() == null) return false;
		return true;
	}

	/**
	 * Checks if the Engine is in range of the fireStation
	 * @param station the fire station you want to check
	 * @return true if the fire engine is in range
	 */
	public boolean isInFireStationRange(FireStation station) {
		if(this.isEntityinRange(new Vector2(station.getX(),station.getY()-32), new Vector2(station.getX()+96,station.getY()))) {
			return true;
		}
		return false;
	}
	
	//Getters and Setters
	
	public void setCurrentTarget(Fortress fortress) {
		this.currentTarget = fortress;
	}
	
	public Fortress getCurrentTarget() {
		return this.currentTarget;
	}
	
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
	
	public int getID() {
		return ID;
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

	public StatBar getUserInterfaceWaterBar() {
		return userInterfaceWaterBar;
	}

	public void setUserInterfaceWaterBar(StatBar userInterfaceWaterBar) {
		this.userInterfaceWaterBar = userInterfaceWaterBar;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		//Changes waterbar and UI bar values
		this.getWaterBar().setValue(this.getCurrentVolume());
		this.getUserInterfaceWaterBar().setValue(this.getCurrentVolume());
		this.getUserInterfaceHealthBar().setValue(this.getCurrentHealth());
		if(this.isAttacking()) {
			this.attackFortress();
		}
		super.draw(batch, parentAlpha);
	}
	
}
