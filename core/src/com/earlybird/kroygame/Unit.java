package com.earlybird.kroygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

public class Unit extends Entity{

	public int moveLocationX, moveLocationY;
	private int speed;
	
	//Constructor
	public Unit(int currentHealth, int maxHealth, int range, int damage, int currentLocationX, int currentLocationY, int moveLocationX, int moveLocationY, int speed, int spriteSize) {
		super(currentHealth, maxHealth, range, damage, currentLocationX, currentLocationY, spriteSize);
		this.moveLocationX = moveLocationX;
		this.moveLocationY = moveLocationY;
		this.speed = speed;
	}
	
	public Unit() {
		super();
		moveLocationX = 0;
		moveLocationY = 0;
		speed = 80;
	}

	public void movement(int SPEED) {
		if (Gdx.input.isKeyPressed(Keys.W)) {
			this.currentLocationY += SPEED * Gdx.graphics.getDeltaTime();
		}
		
		if (Gdx.input.isKeyPressed(Keys.S)) {
			this.currentLocationY -= (SPEED * Gdx.graphics.getDeltaTime())/2;
		}
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			this.currentLocationX -= (SPEED * Gdx.graphics.getDeltaTime())/2;
		}
		
		if (Gdx.input.isKeyPressed(Keys.D)) {
			this.currentLocationX += SPEED * Gdx.graphics.getDeltaTime();
		}
	}
	
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
