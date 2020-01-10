package com.earlybird.kroygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Resources {
	
	//Class works by assigning variables shown below with texture found within Texture Atlas's found within Desktop/packed .findregion finds information under name within "" 
	//this then shows what texture to assign from the PNG file linked to the info
	
	TextureAtlas gameSprites;
	TextureAtlas gameLocations;
	
	public TextureRegion ground; //Initiates "Texture Regions" which are objects that are assigned textures later on within MainGameScreen
	public TextureRegion road;	
	public TextureRegion firetruck;
	public TextureRegion fortress1;
	public TextureRegion fortress2;
	public TextureRegion firestation;
	public static final int TILE_SIZE = 32;
	
	public Resources()
	{
		gameLocations = new TextureAtlas(Gdx.files.internal("packed/gameLocations.txt"));
		gameSprites = new TextureAtlas(Gdx.files.internal("packed/game.atlas")); //Shows what texture atlas to use for the gameSprite atlas and
		//links to the PNG file, where textures are stored
		
		ground = gameSprites.findRegion("ground"); //Assigns ground variable with a Texture
		road = gameSprites.findRegion("road"); //Assigns road variable with a Texture
		firetruck = gameSprites.findRegion("firetruck"); //Assigns firetruck variable with a Texture from the Atlas. Item in "" links to sheet which then pulls item from PNG
		//fortress1 = gameLocations.findRegion("Fortress"); //Assigns fortress1 variable with a Texture
		//firestation = gameLocations.findRegion("Firestation"); //Assigns firestation variable with a Texture
	}
	
	public void dispose()
	{
		gameSprites.dispose();
	}
}
 