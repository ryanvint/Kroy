package com.earlybird.world;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class TiledGameMap {

	public abstract void render(OrthographicCamera camera);
	public abstract void update(float delta);
	public abstract void dispose();
	
	
}
