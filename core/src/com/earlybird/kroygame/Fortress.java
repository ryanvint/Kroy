package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Fortress extends Entity{

	public boolean hasBoss;			//Boolean variable to be used in latter stage of developement
									//to implement mini-game
	
	public Fortress(TextureRegion texture) {	//Instantiates a Fortress with a texture parameter created in the resource class
		this.texture = texture;					
		hasBoss = false;
		this.healthBar.setWidth(96);
	}
	
	public Fortress(TextureRegion texture, boolean hasBoss) {	//Instantiates a Fortress with a texture parameter created in the resource class
		super();												//but with parameter showing whether it has a boss in it or not.
		this.texture = texture;
		this.hasBoss = hasBoss;
	}
	
	public void attackEngine(FireEngine engine) {				//Method used to attack fire engines, and reduce there health determined by damage		
		engine.changeHealth(-this.getDamage());					//that the fortress does
	}
	
	public boolean isFortressinRange(Vector2 bottomLeft, Vector2 topRight) {  //Used to determine if a fortress is in range of a fire engine 
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
	public void spawnPatrol() { //Spawn patrol method created for developement later on
		
	}

}