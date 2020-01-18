package com.earlybird.kroygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Resources {
	
	//Resource Class is used to create Texture Regions which define textures with in Texture atlas's that we've curated.
	
	TextureAtlas gameSprites; 
	TextureAtlas gameLocations;
	//Initialising "Texture Regions" which allow use to pull individual texture from the atlas PNGs and assign them to a variable.
	public TextureRegion firetruck;
	public TextureRegion firetruckSelected;
	public TextureRegion fortress, centralHall, building, minister, railway;
	public TextureRegion firestation;
	public static final int TILE_SIZE = 32;
	
	public Resources()
	{
		gameLocations = new TextureAtlas(Gdx.files.internal("packed/GameLocation.txt")); //These texture sheets define each texture within the atlas PNG files
		gameSprites = new TextureAtlas(Gdx.files.internal("packed/game.atlas")); //Locations shows all textures used for FireStations and Fortress's 
		//Sprites is where we get all textures for Fire Engines and Alien Patrols
		
		firetruckSelected = gameSprites.findRegion("firetruckSelected");
		firetruck = gameSprites.findRegion("firetruck"); //Defining the texture for the Fire Trucks
		fortress = gameLocations.findRegion("Fortress"); //Defining the texture for the Fortress's
		centralHall = gameLocations.findRegion("AlienCentralHall");
		building = gameLocations.findRegion("Building2");
		minister = gameSprites.findRegion("AlienYorkMinister");
		railway = gameSprites.findRegion("RailwayAlien");
		firestation = gameLocations.findRegion("FireStation"); //Defining the texture for the Fire Station
	}
	
	public void dispose()
	{
		gameSprites.dispose();
		gameLocations.dispose();
	}
}
