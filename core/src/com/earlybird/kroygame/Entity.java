package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public abstract class Entity extends Actor{
	Sprite sprite;
	
	private int currentHealth, maxHealth, range, damage;
	public boolean notDestroyed = true;
	
	
	void _init() {
		setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		setTouchable(Touchable.enabled);
	}

	public Entity(int currentHealth, int maxHealth, int range, int damage) {
		this.currentHealth = currentHealth;
		this.maxHealth = maxHealth;
		this.range = range;
		this.damage = damage;
	}
	
	public Entity() {
		super();
		currentHealth = 100;
		maxHealth = 100;
		range = 0;
		damage = 0;
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
