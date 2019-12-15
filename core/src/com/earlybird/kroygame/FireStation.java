package com.earlybird.kroygame;

public class FireStation {
	protected int x,y;
	protected boolean destroyed;
	
	public FireStation() {
		x =  1090;
		y = 30;
		destroyed = false;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean isDestroyed() {
		return destroyed;
	}
	
	//Might be ok just to set destroyed to true if this is called
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
}
