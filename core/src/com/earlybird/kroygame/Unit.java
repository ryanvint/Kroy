package com.earlybird.kroygame;

import com.badlogic.gdx.math.Vector2;

public abstract class Unit extends Entity {
	private Vector2 targetTile;
	
	public Vector2 getTargetTile() {
		return targetTile;
	}

	public void setTargetTile(Vector2 targetTile) {
		this.targetTile = targetTile;
	}
}
