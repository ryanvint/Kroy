package com.earlybird.kroygame;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Group;

public class Engines extends Group {

	public Engines() {
		// TODO Auto-generated constructor stub
	}
	
	public FireEngine getFireEngine(int index) {
		return (FireEngine)((Engine)this.getChild(index)).getChild(0);
	}
	
	public Engine getEngine(int index) {
		return (Engine)this.getChild(index);
	}

}
