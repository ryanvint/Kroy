package com.earlybird.kroygame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Engine is a group used to store fireEngine, healthBar and waterBar instantiations together
 * This is used to move the fireEngine and the statBars consistent with one another
 *
 */
public class Engine extends Group{
	private Vector2 targetTile;
	//Speed controls how fast the engines move, the higher it is the slower they will move
	private float speed = 0.4f;
	
	public Engine() {
	}

	/**
	 * Checks if the Engine has more than 0 health
	 * If not the speed is made slower
	 * If so the speed is returned to its normal value
	 */
	public void checkEngineBroken() {
		if(!this.getFireEngine().isEnoughHealth()) {
			this.setSpeed(1.2f);
		}
		else {
			this.setSpeed(.4f);
		}
	}
	
	/**
	 * @return The FireEngine instance within this Engine group
	 */
	public FireEngine getFireEngine() {
		FireEngine thisFireEngine = (FireEngine) this.getChild(0);
		return thisFireEngine;
	}
	
	/**
	 * Sets the position to a tile location
	 */
	@Override
	public void setPosition(float x, float y) {
		this.targetTile = new Vector2((float) Math.floor(x / 32), (float) Math.floor(y / 32));
		super.setPosition(x, y);
	}

	public Vector2 getTargetTile() {
		return targetTile;
	}

	public void setTargetTile(Vector2 targetTile) {
		this.targetTile = targetTile;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}

