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
	private int iD;
	private Fortress currentTarget;

	
	
	public FireEngine(TextureRegion texture, int iD) { //Instantiates a fire engine
		super();
		this.iD = iD;
		this.texture = texture;
		currentVolume = 200;
		maxVolume = 200;
		waterRate = 2;
		isRefilling = false;
		isSelected = false;
		currentTarget = null;
		this.waterBar = new StatBar(40,5, this.getMaxVolume(), Color.BLACK, Color.BLUE, Color.BLUE);
	}
	
	public boolean isEngineinRange(Vector2 bottomLeft, Vector2 topRight) {
		Vector2 currentLocation = new Vector2(this.localToStageCoordinates(new Vector2(0,0)));
		if(currentLocation.x>=bottomLeft.x && currentLocation.x<=topRight.x && currentLocation.y>=bottomLeft.y && currentLocation.y<=topRight.y) {
			return true;
		}
		return false;
	}
	
	public boolean canEngineAttackFortress(Fortress fortress) {
		if(fortress!=null) {
			Vector2 bottomLeft = new Vector2(this.localToStageCoordinates(new Vector2(0,0)).sub(new Vector2(this.getRange(), this.getRange())));
			Vector2 topRight = new Vector2(this.localToStageCoordinates(new Vector2(0,0)).add(new Vector2(this.getRange(), this.getRange())));
				if(fortress.isFortressinRange(bottomLeft, topRight)) {
					return true;
				}
		}
		return false;
		}	
	
	public boolean isEnoughWater() {
		if(this.getCurrentVolume()>0) return true;
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
	
	public void dealDamage() {
		if(currentTarget.getCurrentHealth()>0 && this.getCurrentVolume()>0) {
			this.changeWater(-this.getWaterRate());
			currentTarget.changeHealth(-this.getDamage());
		}
	}
	
	public void attackFortress() {
		if(this.canEngineAttackFortress(currentTarget) && this.isEnoughWater()) {
			this.dealDamage();
		}
		else {
			this.setCurrentTarget(null);
		}
	}
	
	public boolean isAttacking() {
		if(this.getCurrentTarget() == null) return false;
		return true;
	}

	public boolean isInFireStationRange(FireStation station) {
		if(this.isEngineinRange(new Vector2(station.getX(),station.getY()-32), new Vector2(station.getX()+96,station.getY()))) {
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
	
	public int getiD() {
		return iD;
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
		if(this.isAttacking()) {
			this.attackFortress();
		}
		super.draw(batch, parentAlpha);
	}
	
}
