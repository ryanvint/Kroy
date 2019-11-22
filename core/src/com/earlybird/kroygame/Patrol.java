package com.earlybird.kroygame;

public class Patrol extends Unit{

	//patrolPath attribute needs to be added here and then put into the constructor
	
	//Constructor
	public Patrol(int currentHealth, int maxHealth, int range, int damage, int currentLocationX, int currentLocationY,
			int moveLocationX, int moveLocationY, int speed) {
		super(currentHealth, maxHealth, range, damage, currentLocationX, currentLocationY, moveLocationX, moveLocationY, speed);
		
	}

	//patrolPath Getter and Setter here
	
	//Methods
	public void patrol() {
		
	}
}
