package com.earlybird.kroygame;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Map {
	
	private int[][] grid;
	
	public Map(TiledMap map) {
		
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
		this.grid = new int[layer.getWidth()][layer.getHeight()]; 
		
        for(int x = 0; x < layer.getWidth();x++){
        	for(int y = 0; y < layer.getHeight();y++){
                TiledMapTileLayer.Cell cell = layer.getCell(x,y);
                this.grid[x][y] = cell.getTile().getId();
            }
        }
        for (int x = 0; x < this.grid.length; x++) {
        	for (int y = 0; y < this.grid[x].length; y++) {
        		System.out.print(grid[x][y]);
        	}
        	System.out.println();
        }
	}
	//public method to query grid
	public boolean isRoad(Vector2 point) {
		int x = (int) Math.floor(point.x / 32);
		int y = (int) Math.floor(point.y / 32);
		
		if (grid[x][y] == 3) {
			return true;
		}
		
		return false;
	}
}
