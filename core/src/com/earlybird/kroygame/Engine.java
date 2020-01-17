package com.earlybird.kroygame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

<<<<<<< HEAD
public class Engine extends Group{ 	//Class 'engine' which is an extension of 'Group' 
									//Scene2d groups store actors together so that actions
}									//can be performed on all actors within group
									//The Engine Group stores a FireEngine sprite and it stat
									//bars all under one name

=======
public class Engine extends Group{
	private Vector2 targetTile;
	
	public void Engine() {
	}

	@Override
	public void setPosition(float x, float y) {
		this.targetTile = new Vector2((float) Math.floor(x / 32), (float) Math.floor(y / 32));
		super.setPosition(x, y);
	}

	public Vector2 getTargetTile() {
		return targetTile;
	}

	public void setTargetTile(Vector2 targetTile) {
		this.targetTile = targetTile;
	}
}
>>>>>>> fc01962a9c0ea1ffbbb60125c9a507d560aedd40
