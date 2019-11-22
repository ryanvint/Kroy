package com.earlybird.kroygame;

public class Fortress extends Entity{

	public boolean hasBoss;
	
	public Fortress(int currentHealth, int maxHealth, int range, int damage, int currentLocationX,
			int currentLocationY, boolean hasBoss) {
		super(currentHealth, maxHealth, range, damage, currentLocationX, currentLocationY);
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
