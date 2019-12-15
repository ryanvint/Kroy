package com.earlybird.kroygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.earlybird.kroygame.screens.MainGameScreen;

public class Entity {

	private int currentHealth, maxHealth, range, damage, spriteSize;
	protected int currentLocationX;
	protected int currentLocationY;
	public StatBar healthBar;
	
	//Constructor
	public Entity(int currentHealth, int maxHealth, int range, int damage, int currentLocationX, int currentLocationY, int spriteSize) {
		this.currentHealth = currentHealth;
		this.maxHealth = maxHealth;
		this.range = range;
		this.damage = damage;
		this.currentLocationX = currentLocationX;
		this.currentLocationY = currentLocationY;
		this.spriteSize = spriteSize;
		this.healthBar = new StatBar(40,5, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
	}
	
	public Entity(int currentLocationX, int currentLocationY) {
		this.currentLocationX = currentLocationX;
		this.currentLocationY = currentLocationY;
		currentHealth = 100;
		maxHealth = 100;
		range = 0;
		damage = 0;
		spriteSize = 32;
		this.healthBar = new StatBar(40,5, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
	}
	
	public Entity() {
		currentHealth = 100;
		maxHealth = 100;
		range = 0;
		damage = 0;
		currentLocationX = 30;
		currentLocationY = 30;
		spriteSize = 32;
		this.healthBar = new StatBar(40, 5, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
	}

	//Getters and Setters
	public int getHealthBarX() {
		return this.currentLocationX-10;
	}
	
	public int getHealthBarY() {
		return this.currentLocationY-8;
	}
	
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
	
	//Checks if there are any of the units that can be attacked in range (0 is for attacking fire engines
	// 1 is for attacking fortresses)
	public void attack(int unitType) {
		boolean canAttack = false;
		Entity attackThisUnit = null;
		if(unitType == 0) {
			if(MainGameScreen.fireSquad.getEnginesInRange((this.currentLocationX - this.range),(this.currentLocationX + this.range),(this.currentLocationY + this.range),(this.currentLocationY - this.range)).size() > 0) {
				canAttack = true;
				attackThisUnit = MainGameScreen.fireSquad.getEnginesInRange((this.currentLocationX - this.range),(this.currentLocationX + this.range),(this.currentLocationY + this.range),(this.currentLocationY - this.range)).get(0);
			}
		}else if(unitType == 1) {
			for(int i = 0; i < MainGameScreen.fortressList.size(); i++) {
				Fortress currentFortress = MainGameScreen.fortressList.get(i);
				if((currentFortress.getCurrentLocationX() >= (this.currentLocationX-this.range)) && (currentFortress.getCurrentLocationX()<=(this.currentLocationX+this.range)) && (currentFortress.getCurrentLocationY()>=(this.currentLocationY-this.range)) && (currentFortress.getCurrentLocationY()<=(this.currentLocationY+this.range))) {
					canAttack = true;
					attackThisUnit = currentFortress;
				}
			}
		}
		if(canAttack) {
			
			if(this instanceof FireEngine) {
				if(this.getCurrentVolume() != 0) {
					this.changeCurrentVolume(this.getDamage());
					attackThisUnit.changeHealth(this.getDamage());
				}

			}else {
				attackThisUnit.changeHealth(this.getDamage());
			}
		}
		
	}

	public void changeHealth(int damageValue) {
		this.setCurrentHealth(this.getCurrentHealth() - damageValue);
	}
	
	public void increaseMaxHealth() {
		
	}
	
	public void increaseRange() {
		
	}
	
	public void increaseDamage() {
		
	}
	
	//This is the empty version as the empty version to be changed in the fireEngine class
	public void changeCurrentVolume(int damage) {
	}
	
	public int getCurrentVolume() {
		return 0;
	}
}
