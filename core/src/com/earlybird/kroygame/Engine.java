package com.earlybird.kroygame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Engine extends Group{
	private Vector2 targetTile;
	
	public void Engine() {
	}

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
}
