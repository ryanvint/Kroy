package com.earlybird.world;

import java.util.HashMap;

public enum TileType {

	//Ex enum
	GRASS(1,true);
	
	public static final int TILE_SIZE = 32;
	
	private int id;
	private boolean collidable;
	
	private TileType(int id, boolean collidable) {
		this.id = id;
		this.collidable = collidable;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isCollidable() {
		return collidable;
	}
	
	private static HashMap<Integer, TileType> tileMap;
	
	static {
		for(TileType tileType : TileType.values()) {
			tileMap.put(tileType.getId(), tileType);
		}
	}
	
	public static TileType getTyleTypeById(int id) {
		return tileMap.get(id);
	}
}
