package com.earlybird.kroygame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Fortresses is group of all fortress's in the game 
 * This allows easy looping through all fortresses in game
 */
public class Fortresses extends Group{

	public Fortresses() {
							
	}
				
	/**
	 * Gets the Fortress instance at a given index from the group
	 * @param index
	 * @return Fortress of fortress at index in group
	 */
	public Fortress getFortress(int index) {
		return (Fortress) this.getChild(index);
	}
	
	/**
	 * Returns the fortress at a given point
	 * Used to check which fortress is clicked on
	 * @param point
	 * @return Fortress at point given
	 */
	public Fortress getFortressClicked(Vector2 point) { 
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
