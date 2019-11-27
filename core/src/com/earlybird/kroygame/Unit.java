package com.earlybird.kroygame;

public class Unit extends Entity{

	public int moveLocationX, moveLocationY;
	private int speed;
	
	//Constructor
	public Unit(int currentHealth, int maxHealth, int range, int damage, int currentLocationX, int currentLocationY, int moveLocationX, int moveLocationY, int speed) {
		super(currentHealth, maxHealth, range, damage, currentLocationX, currentLocationY);
		this.moveLocationX = moveLocationX;
		this.moveLocationY = moveLocationY;
		this.speed = speed;
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
