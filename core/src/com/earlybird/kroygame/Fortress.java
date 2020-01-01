package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Fortress extends Entity{

	public boolean hasBoss;
	
	public Fortress(Sprite sprite) {
		this.sprite = sprite;
		hasBoss = false;
	}
	
	public Fortress(Sprite sprite, boolean hasBoss) {
		super();
		this.sprite = sprite;
		this.hasBoss = hasBoss;
	}

	//Getters and setters
	public boolean isHasBoss() {
		return hasBoss;
	}

	public void setHasBoss(boolean hasBoss) {
		this.hasBoss = hasBoss;
	}
	
	//Methods
	public void spawnPatrol() {
		
	}

}