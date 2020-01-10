package com.earlybird.kroygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Resources {
	
	TextureAtlas gameSprites;
	TextureAtlas gameLocations;
	
	
	public TextureRegion ground;
	public TextureRegion road;
	public TextureRegion firetruck;
	public TextureRegion fortress1;
	public TextureRegion fortress2;
	public TextureRegion firestation;
	public static final int TILE_SIZE = 32;
	
	public Resources()
	{
		gameLocations = new TextureAtlas(Gdx.files.internal("packed/gameLocations.txt"));
		gameSprites = new TextureAtlas(Gdx.files.internal("packed/game.atlas"));
		
		ground = gameSprites.findRegion("ground");
		road = gameSprites.findRegion("road");
		firetruck = gameSprites.findRegion("firetruck");
		fortress1 = gameLocations.findRegion("Fortress");
		firestation = gameLocations.findRegion("FireStation");
	}
	
	public void dispose()
	{
		gameSprites.dispose();
	}
}
