package com.earlybird.kroygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Resources {
	
	TextureAtlas gameSprites;
	
	public TextureRegion ground;
	public TextureRegion road;
	public Sprite firetruck;
	public Sprite fortress1;
	public Sprite fortress2;
	public static final int TILE_SIZE = 32;
	
	public Resources()
	{
		gameSprites = new TextureAtlas(Gdx.files.internal("packed/game.atlas"));
		ground = gameSprites.findRegion("ground");
		road = gameSprites.findRegion("road");
		System.out.println("hi");
		firetruck = new Sprite(gameSprites.findRegion("firetruck"));
		fortress1 = new Sprite(gameSprites.findRegion("firetruck"));
		fortress2 = new Sprite(gameSprites.findRegion("firetruck"));
		System.out.println("hii");
	}
	
	public void dispose()
	{
		gameSprites.dispose();
	}
}
