package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

//Used to import everything needed to port items over
import com.badlogic.gdx.graphics.Color;

public abstract class Entity extends Actor{
	TextureRegion texture;
	
	private int currentHealth, maxHealth, range, damage;
	private StatBar healthBar, userInterfaceHealthBar;
	private boolean notDestroyed = true;
	
	public Entity(int currentHealth, int maxHealth, int range, int damage) {	//Constructor to instantiate Entity
		this.setOrigin(this.getWidth()/2, this.getHeight()/2);
		this.currentHealth = currentHealth;
		this.maxHealth = maxHealth;
		this.range = range;
		this.damage = damage;
		this.healthBar = new StatBar(40,5, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
		this.userInterfaceHealthBar = new StatBar(80,10, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
	}
	
	public Entity() {	//Constructor to instantiate Entity
		currentHealth = 100;
		maxHealth = 100;
		range = 32*4;
		damage = 1;
		this.healthBar = new StatBar(40,5, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
		this.userInterfaceHealthBar = new StatBar(80,10, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
	}
	
	public void changeHealth(int change) {							//Changes the current health of a Entity via a change parameter 
		this.setCurrentHealth(this.getCurrentHealth() + change);
		if(this.getCurrentHealth()<0) {
			this.setCurrentHealth(0);
		}
		else if(this.getCurrentHealth()>this.getMaxHealth()) {
			this.setCurrentHealth(this.getMaxHealth());
		}
	}
	
	public boolean isEntityinRange(Vector2 bottomLeft, Vector2 topRight) {  //Method
		Vector2 currentLocation = new Vector2(this.localToStageCoordinates(new Vector2(0,0)));
		if(currentLocation.x>=bottomLeft.x && currentLocation.x<=topRight.x && currentLocation.y>=bottomLeft.y && currentLocation.y<=topRight.y) {
			return true;
		}
		return false;
	}
	
	public boolean canEntityAttackEntity(Entity entityToAttack) {
		if(entityToAttack!=null) {
			Vector2 bottomLeft = new Vector2(this.localToStageCoordinates(new Vector2(0,0)).sub(new Vector2(this.getRange(), this.getRange())));
			Vector2 topRight = new Vector2(this.localToStageCoordinates(new Vector2(0,0)).add(new Vector2(this.getRange()+96, this.getRange()+96)));
				if(entityToAttack.isEntityinRange(bottomLeft, topRight) && this.getCurrentHealth()!=0) {
					return true;
				}
		}
		return false;
	}
	
	//Getters and Setters
	
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

	public StatBar getHealthBar() {
		return healthBar;
	}

	public void setHealthBar(StatBar healthBar) {
		this.healthBar = healthBar;
	}

	public StatBar getUserInterfaceHealthBar() {
		return userInterfaceHealthBar;
	}

	public void setUserInterfaceHealthBar(StatBar userInterfaceHealthBar) {
		this.userInterfaceHealthBar = userInterfaceHealthBar;
	}

	public boolean isNotDestroyed() {
		return notDestroyed;
	}

	public void setNotDestroyed(boolean notDestroyed) {
		this.notDestroyed = notDestroyed;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) { //Draws entity 
		this.getHealthBar().setValue(this.getCurrentHealth());
		batch.draw(texture, getX(), getY());
	}
}
