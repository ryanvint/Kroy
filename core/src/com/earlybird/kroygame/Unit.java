package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public abstract class Unit extends Entity {

	private int dir = 0;
	private Vector2 lastPos = this.localToStageCoordinates(this.localToStageCoordinates(new Vector2(0,0)));
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public Vector2 getLastPos() {
		return lastPos;
	}

	public void setLastPos(Vector2 lastPos) {
		this.lastPos = lastPos;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.getHealthBar().setValue(this.getCurrentHealth());
		if (dir == 0) {
			batch.draw(this.texture, this.getX(), this.getY(), this.texture.getRegionWidth() / 2, this.texture.getRegionHeight() / 2,
					this.texture.getRegionWidth(), this.texture.getRegionHeight(), 1, 1, 0);
		}
		if (dir == 1) {
			batch.draw(this.texture, this.getX(), this.getY(), this.texture.getRegionWidth() / 2, this.texture.getRegionHeight() / 2,
					this.texture.getRegionWidth(), this.texture.getRegionHeight(), 1, 1, 90);
		}
		if (dir == 2) {
			batch.draw(this.texture, this.getX(), this.getY(), this.texture.getRegionWidth() / 2, this.texture.getRegionHeight() / 2,
					this.texture.getRegionWidth(), this.texture.getRegionHeight(), 1, 1, 180);
		}
		if (dir == 3) {
			batch.draw(this.texture, this.getX(), this.getY(), this.texture.getRegionWidth() / 2, this.texture.getRegionHeight() / 2,
					this.texture.getRegionWidth(), this.texture.getRegionHeight(), 1, 1, 270);
		}
	}
}
