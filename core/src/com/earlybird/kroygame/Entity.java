package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;

//Used to import everything needed to port items over
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.earlybird.kroygame.screens.MainGameScreen;

public abstract class Entity extends Actor{
	TextureRegion texture;
	
	private int currentHealth, maxHealth, range, damage;
	public StatBar healthBar, userInterfaceHealthBar;
	public boolean notDestroyed = true;
	
	
	//void _init() {
	//	setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
	//	setTouchable(Touchable.enabled);
	//}

	//Constructor
	public Entity(int currentHealth, int maxHealth, int range, int damage) {	//Constructor to instantiate Entity
		this.setOrigin(this.getWidth()/2, this.getHeight()/2);
		this.currentHealth = currentHealth;
		this.maxHealth = maxHealth;
		this.range = range;
		this.damage = damage;
		this.healthBar = new StatBar(40,5, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
	}
	
	public Entity() {	////Constructor to instantiate Entity
		currentHealth = 100;
		maxHealth = 100;
		range = 32*4;
		damage = 1;
		this.healthBar = new StatBar(40,5, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
	}
	
	public void changeHealth(int change) {
		this.setCurrentHealth(this.getCurrentHealth() + change);
		if(this.getCurrentHealth()<0) {
			this.setCurrentHealth(0);
		}
		else if(this.getCurrentHealth()>this.getMaxHealth()) {
			this.setCurrentHealth(this.getMaxHealth());
		}
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
	public void draw(Batch batch, float parentAlpha) {
		this.getHealthBar().setValue(this.getCurrentHealth());
		//this.getUserInterfaceHealthBar().setValue(this.getCurrentHealth());
		batch.draw(texture, getX(), getY());
	}
}
