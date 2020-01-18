package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Fortress extends Entity{
	private boolean hasBoss;

	private FireEngine currentTarget;
	
	public Fortress(TextureRegion texture) {
		this.texture = texture;					
		hasBoss = false;
		this.getHealthBar().setWidth(96);
		this.currentTarget = null;
	}
	
	public Fortress(TextureRegion texture, boolean hasBoss) {
		super();
		this.texture = texture;
		this.hasBoss = hasBoss;
		this.currentTarget = null;
	}
	
	public void attackEngine(FireEngine engine) {		
		engine.changeHealth(-this.getDamage());
	}
	
	@Override
	public boolean canEntityAttackEntity(Entity entityToAttack) {
		if(entityToAttack!=null) {
			Vector2 bottomLeft = new Vector2(this.localToStageCoordinates(new Vector2(0,0)).sub(new Vector2(this.getRange(), this.getRange())));
			Vector2 topRight = new Vector2(this.localToStageCoordinates(new Vector2(0,0)).add(new Vector2(this.getRange()+96, this.getRange()+96)));
				if(entityToAttack.isEntityinRange(bottomLeft, topRight) && this.getCurrentHealth()!=0) {
					return true;
				}
		}
		return false;
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