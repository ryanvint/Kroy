package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Fortress extends Entity{


	private boolean hasBoss;			//Boolean variable to be used in latter stage of developement
									//to implement mini-game

	private FireEngine currentTarget;
	
	public Fortress(TextureRegion texture) {	//Instantiates a Fortress with a texture parameter created in the resource class
		this.texture = texture;					
		hasBoss = false;
		this.getHealthBar().setWidth(96);
		this.currentTarget = null;
	}
	
	public Fortress(TextureRegion texture, boolean hasBoss) {	//Instantiates a Fortress with a texture parameter created in the resource class
		super();												//but with parameter showing whether it has a boss in it or not.
		this.texture = texture;
		this.hasBoss = hasBoss;
		this.currentTarget = null;
	}
	
	public void attackEngine(FireEngine engine) {				//Method used to attack fire engines, and reduce there health determined by damage		
		engine.changeHealth(-this.getDamage());					//that the fortress does
	}
	
	public void changeFortressTarget(Engines engines, Engines selectedEngines) {
		if(this.isAttacking()) {
			if(!this.isCurrentTargetValid(this.getCurrentTarget())) {
				this.setNewTarget(engines, selectedEngines);
			}
		}
		else {
			this.setNewTarget(engines, selectedEngines);
		}
	}
	
	public void setNewTarget(Engines engines, Engines selectedEngines) {
		boolean isTargetSet = false;
		if(!this.isCurrentTargetValid(this.currentTarget)) {
			this.setCurrentTarget(null);
		}
		if(engines.getChildren().size != 0) {
			for(int i = 0; i<engines.getChildren().size; i++) {
				if(isCurrentTargetValid(engines.getFireEngine(i)) && !isTargetSet) {
					this.setCurrentTarget(engines.getFireEngine(i));
					isTargetSet = true;
				}
			}
		}
		if(selectedEngines.getChildren().size != 0) {
			for(int i = 0; i<selectedEngines.getChildren().size; i++) {
				if(isCurrentTargetValid(selectedEngines.getFireEngine(i)) && !isTargetSet) {
					this.setCurrentTarget(selectedEngines.getFireEngine(i));
					isTargetSet = true;
				}
			}
		}
	}
	
	public boolean isCurrentTargetValid(FireEngine engine) {
		if(!this.canEntityAttackEntity(engine) || engine.getCurrentHealth() == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean isAttacking() {
		if(this.getCurrentTarget() == null) return false;
		else return true;
	}
	
	public void attackEngine() {
		if(this.isAttacking()) {
			this.getCurrentTarget().changeHealth(-this.getDamage());
		}
	}

	//Getters and setters
	public FireEngine getCurrentTarget() {
		return this.currentTarget;
	}
	
	public void setCurrentTarget(FireEngine engine) {
		this.currentTarget = engine;
	}
	
	public boolean isHasBoss() {
		return hasBoss;
	}

	public void setHasBoss(boolean hasBoss) {
		this.hasBoss = hasBoss;
	}
	
	//Methods 
	public void spawnPatrol() { //Spawn patrol method created for development later on
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.attackEngine();
		super.draw(batch, parentAlpha);
	}

}