package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.Texture;

/**
 * Resources is used to create textures and assign files to them
 */
public class Resources {

	public Texture fortress, centralHall, minister, railway, firetruck, firetruckSelected, firestation;
	public static final int TILE_SIZE = 32;
	
	public Resources()
	{	
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
