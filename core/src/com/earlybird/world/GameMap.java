package com.earlybird.world;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class GameMap {

	public abstract void render(OrthographicCamera camera);
	public abstract void update(float delta);
	public abstract void dispose();
	
	/**
	 * Gets a tile by pixel position on a layer by the x and y pixel positon
	 * @param layer
	 * @param x
	 * @param y
	 * @return
	 */
	public TileType getTileTypeByLocation(int layer, float x, float y) {
		return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE), (int) (y/TileType.TILE_SIZE));
	}
	
	/**
	 * Gets a tile at a coordinate on the map
	 * @param layer
	 * @param col
	 * @param row
	 * @return
	 */
	public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);
	
	
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getLayers();
}
