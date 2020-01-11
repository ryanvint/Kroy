package com.earlybird.kroygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Resources {
	
	//Resource Class is used to create Texture Regions which define textures with in Texture atlas's that we've curated.
	
	TextureAtlas gameSprites; 
	TextureAtlas gameLocations;
	
	public TextureRegion ground; //Initialising "Texture Regions" which allow use to pull individual texture from the atlas PNGs and assign them to a variable.
	public TextureRegion road;
	public TextureRegion firetruck;
	public TextureRegion firetruckSelected;
	public TextureRegion fortress1;
	public TextureRegion firestation;
	public static final int TILE_SIZE = 32;
	
	public Resources()
	{
		gameLocations = new TextureAtlas(Gdx.files.internal("packed/gameLocations.txt")); //These texture sheets define each texture within the atlas PNG files
		gameSprites = new TextureAtlas(Gdx.files.internal("packed/game.atlas")); //Locations shows all textures used for FireStations and Fortress's 
		//Sprites is where we get all textures for Fire Engines and Alien Patrols
		
		ground = gameSprites.findRegion("ground"); //Do we need?
		road = gameSprites.findRegion("road"); //Do we need?
		firetruckSelected = gameSprites.findRegion("firetruckSelected");
		firetruck = gameSprites.findRegion("firetruck"); //Defining the texture for the Fire Trucks
		fortress1 = gameLocations.findRegion("Fortress"); //Defining the texture for the Fortress's
		firestation = gameLocations.findRegion("FireStation"); //Defining the texture for the Fire Station
	}
	
	public void dispose()
	{
		gameSprites.dispose();
		gameLocations.dispose();
	}
}
