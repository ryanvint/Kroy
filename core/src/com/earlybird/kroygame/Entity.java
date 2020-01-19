package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

/**
 * Entity is the main class for all actors on the screen that will have health values
 * Unit and Fortress extend from this
 */
public abstract class Entity extends Actor{
	Texture texture;
	
	private int currentHealth, maxHealth, range, damage;
	private StatBar healthBar, userInterfaceHealthBar;
	private boolean notDestroyed = true;
	
	public Entity(int currentHealth, int maxHealth, int range, int damage) {
		this.setOrigin(this.getWidth()/2, this.getHeight()/2);
		this.currentHealth = currentHealth;
		this.maxHealth = maxHealth;
		this.range = range;
		this.damage = damage;
		//Creates a statbar of with a max value of maxhealth for the fireEngine and the UI
		this.healthBar = new StatBar(40,5, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
		this.userInterfaceHealthBar = new StatBar(80,10, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
	}
	
	public Entity() {
		currentHealth = 100;
		maxHealth = 100;
		range = 32*4;
		damage = 1;
		this.healthBar = new StatBar(40,5, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
		this.userInterfaceHealthBar = new StatBar(80,10, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
	}
	
	/**
	 * Changes the health of the entity but keeps it >0 and <max health
	 * @param change amount to change health by. Negative to subtract health
	 */
	public void changeHealth(int change) {							//Changes the current health of a Entity via a change parameter 
		this.setCurrentHealth(this.getCurrentHealth() + change);
		if(this.getCurrentHealth()<0) {
			this.setCurrentHealth(0);
		}
		else if(this.getCurrentHealth()>this.getMaxHealth()) {
			this.setCurrentHealth(this.getMaxHealth());
		}
	}
	
	/**
	 * Checks if the entity is within the square range of the 2 Vector2 points passed in
	 * @param bottomLeft Bottom left point of square range being checked
	 * @param topRight Top Right point of square range being checked
	 * @return boolean for if the entity is in range
	 */
	public boolean isEntityinRange(Vector2 bottomLeft, Vector2 topRight) {  //Method
		Vector2 currentLocation = new Vector2(this.localToStageCoordinates(new Vector2(0,0)));
		if(currentLocation.x>=bottomLeft.x && currentLocation.x<=topRight.x && currentLocation.y>=bottomLeft.y && currentLocation.y<=topRight.y) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if a given entity is in range of this entity instance
	 * @param entityToAttack
	 * @return boolean of if the entity given is in the range of this entity
	 */
	public boolean canEntityAttackEntity(Entity entityToAttack) {
		if(entityToAttack!=null) {
			Vector2 bottomLeft = new Vector2(this.localToStageCoordinates(new Vector2(0,0)).sub(new Vector2(this.getRange(), this.getRange())));
			Vector2 topRight = new Vector2(this.localToStageCoordinates(new Vector2(0,0)).add(new Vector2(this.getRange(), this.getRange())));
				if(entityToAttack.isEntityinRange(bottomLeft, topRight) && this.getCurrentHealth()!=0) {
					return true;
				}
		}
		return false;
	}
	
	//Getters and Setters
	public Texture getTextureRegion() {
		return this.texture;
	}
	
	public void setTextureRegion(Texture texture) {
		this.texture = texture;
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
		//updates healthbar value
		this.getHealthBar().setValue(this.getCurrentHealth());
		batch.draw(texture, getX(), getY());
	}
}
