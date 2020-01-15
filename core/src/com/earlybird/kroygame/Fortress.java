package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Fortress extends Entity{

	public boolean hasBoss;
	
	public Fortress(TextureRegion texture) {
		this.texture = texture;
		hasBoss = false;
		this.healthBar.setWidth(96);
	}
	
	public Fortress(TextureRegion texture, boolean hasBoss) {
		super();
		this.texture = texture;
		this.hasBoss = hasBoss;
	}
	
	public void attackEngine(FireEngine engine) {
		engine.changeHealth(-this.getDamage());
	}
	
	public boolean isFortressinRange(Vector2 bottomLeft, Vector2 topRight) {
		if(this.getX()>=bottomLeft.x && this.getX()<=topRight.x && this.getY()>=bottomLeft.y && this.getY()<=topRight.y) {
			return true;
		}
		return false;
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