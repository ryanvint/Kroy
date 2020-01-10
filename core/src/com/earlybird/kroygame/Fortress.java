package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Fortress extends Entity{

	public boolean hasBoss;
	
	public Fortress(TextureRegion texture) {
		this.texture = texture;
		hasBoss = false;
	}
	
	public Fortress(TextureRegion texture, boolean hasBoss) {
		super();
		this.texture = texture;
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