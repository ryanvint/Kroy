package com.earlybird.kroygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

public abstract class Unit extends Entity{

	public int moveLocationX, moveLocationY;
	private int speed;
	
	//Getters and Setters
	public int getMoveLocationX() {
		return moveLocationX;
	}

	public void setMoveLocationX(int moveLocationX) {
		this.moveLocationX = moveLocationX;
	}

	public int getMoveLocationY() {
		return moveLocationY;
	}

	public void setMoveLocationY(int moveLocationY) {
		this.moveLocationY = moveLocationY;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	//Methods
	public void move() {
		
	}
	
	public void increaseSpeed() {
		
	}
	
	protected void changeMoveLocation() {
		
	}
	
}
