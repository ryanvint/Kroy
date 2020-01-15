package com.earlybird.kroygame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Fortresses extends Group{

	public Fortresses() {
		
	}
	
	public Fortress getFortress(Vector2 point) {
		for(int i=0; i<this.getChildren().size; i++) {
			Fortress thisFortress = (Fortress) this.getChild(i);
			if(point != null) {
				Vector2 bottomLeft = new Vector2(thisFortress.getX(),thisFortress.getY());
				Vector2 topRight = new Vector2(thisFortress.getX() + 96, thisFortress.getY() + 96);
				if(Utils.isBetween(bottomLeft,topRight,point)) {
					return thisFortress;
				}
			}
		}
		
		return null;
	}
	
}
