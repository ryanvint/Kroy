package com.earlybird.kroygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor{

	private int currentHealth, maxHealth, range, damage, spriteSize;
	protected int currentLocationX;
	protected int currentLocationY;

	//Getters and Setters
	public int getSpriteSize() {
		return spriteSize;
	}
	
	public void setSpriteSize(int spriteSize) {
		this.spriteSize = spriteSize;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getCurrentLocationX() {
		return currentLocationX;
	}

	public void setCurrentLocationX(int currentLocationX) {
		this.currentLocationX = currentLocationX;
	}

	public int getCurrentLocationY() {
		return currentLocationY;
	}

	public void setCurrentLocationY(int currentLocationY) {
		this.currentLocationY = currentLocationY;
	}

	//Methods
	//Checks if given x,y pos is inside of the hitbox of the entity.
	//Hitbox is defined as spriteSize+x and y current location to the normal x and y current locations
	public boolean isInHitBox(int xpos, int ypos) {
		if(xpos>=currentLocationX && xpos<=currentLocationX+spriteSize && ypos>=currentLocationY && ypos<=currentLocationY+spriteSize) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void destroy() {
		
	}
	
	public void attack() {
		
	}
	
	public void increaseHealth() {
		
	}
	
	public void decreaseHealth() {
		
	}
	
	public void increaseMaxHealth() {
		
	}
	
	public void increaseRange() {
		
	}
	
	public void increaseDamage() {
		
	}
}
