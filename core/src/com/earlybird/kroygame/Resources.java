package com.earlybird.kroygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Resources is used to create texture regions and build texture atlas's
 * To add textures add the png you want to add into the Kroy assets folder
 * Then define variables with findRegion("fileName")
 */
public class Resources {
	
//	TextureAtlas gameSprites; 
	//TextureAtlas gameLocations;

	public Texture fortress, centralHall, minister, railway, firetruck, firetruckSelected, firestation;
	public static final int TILE_SIZE = 32;
	
	public Resources()
	{
		//These texture sheets define each texture within the atlas PNG files locations shows all textures used for FireStations and Fortress's 
		//gameLocations = new TextureAtlas(Gdx.files.internal("GameLocation.txt"));
		//gameSprites atlas is auto generated from kroy assets folder
//		gameSprites = new TextureAtlas(Gdx.files.internal("packed/game.atlas"));
		
		firetruckSelected = new Texture("firetruckSelected.png");
		firetruck = new Texture("firetruck.png");
		centralHall = new Texture("AlienCentralHall.png");
		minister = new Texture("AlienYorkMinister.png");
		railway = new Texture("RailwayAlien.png");
		firestation = new Texture("FireStation.png");
	}
	
	public void dispose()
	{
//		gameSprites.dispose();
	}
}
