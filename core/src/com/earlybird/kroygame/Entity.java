package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

//Used to import everything needed to port items over
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.earlybird.kroygame.screens.MainGameScreen;

public abstract class Entity extends Actor{
	Sprite sprite;
	
	private int currentHealth, maxHealth, range, damage;
	public StatBar healthBar;
	public boolean notDestroyed = true;
	
	
	//void _init() {
	//	setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
	//	setTouchable(Touchable.enabled);
	//}

	//Constructor
	public Entity(int currentHealth, int maxHealth, int range, int damage) {
		this.currentHealth = currentHealth;
		this.maxHealth = maxHealth;
		this.range = range;
		this.damage = damage;
		this.healthBar = new StatBar(40,5, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
	}
	
	public Entity() {
		currentHealth = 100;
		maxHealth = 100;
		range = 0;
		damage = 0;
		this.healthBar = new StatBar(40,5, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN);
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

	public boolean isNotDestroyed() {
		return notDestroyed;
	}

	public void setNotDestroyed(boolean notDestroyed) {
		this.notDestroyed = notDestroyed;
	}


			

	@Override
	public void draw(Batch batch, float parentAlpha) {
		sprite.draw(batch);
	}

	@Override
	protected void positionChanged() {
		sprite.setPosition(getX(), getY());
		super.positionChanged();
		
	}
	
}
