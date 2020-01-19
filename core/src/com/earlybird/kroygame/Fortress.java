package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Fortress is an entity on the screen
 * It attacks fireEngines one at a time when they are
 * in range of it while it and they still have health. 
 */
public class Fortress extends Entity{
	private boolean hasBoss;

	private FireEngine currentTarget;
	
	public Fortress(TextureRegion texture) {
		this.texture = texture;					
		hasBoss = false;
		this.currentTarget = null;
		this.setMaxHealth(500);
		this.setCurrentHealth(500);
		this.setDamage(1);
		this.setHealthBar(new StatBar(40,5, this.getMaxHealth(), Color.RED, Color.GREEN, Color.GREEN));
		this.getHealthBar().setWidth(96);
	}
	
	public Fortress(TextureRegion texture, boolean hasBoss) {
		super();
		this.texture = texture;
		this.hasBoss = hasBoss;
		this.currentTarget = null;
	}
	
	/**
	 * Attacks the engine passed in with the damage amount of that fortress
	 * @param engine Engine to attack
	 */
	public void attackEngine(FireEngine engine) {		
		engine.changeHealth(-this.getDamage());
	}
	
	/**
	 * Adds 96 to the topRight range to account for texture size
	 */
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
	
	/**
	 * Checks if the current target is valid, if not it gets and sets a new target
	 * @param engines fireEngines to check if in range of becoming a target
	 * @param selectedEngines fireEngines to check if in range of becoming a target
	 */
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
	
	/**
	 * Finds any engines in range of the fortress and sets one to become the current target
	 * @param engines fireEngines to check if in range of becoming a target
	 * @param selectedEngines fireEngines to check if in range of becoming a target
	 */
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
	
	/**
	 * Checks if the currentTarget is valid by having more than 0 health and is in range
	 * @param engine The FireEngine to check if valid
	 * @return True if currentTarget has more than 0 health and is in range
	 */
	public boolean isCurrentTargetValid(FireEngine engine) {
		if(!this.canEntityAttackEntity(engine) || engine.getCurrentHealth() == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Checks if the Fortress has a target currently
	 * @return True if currentTarget is not null
	 */
	public boolean isAttacking() {
		if(this.getCurrentTarget() == null) return false;
		else return true;
	}
	
	/**
	 * If the fortress is attacking, this deals damage to the current target
	 */
	public void attackEngine() {
		if(this.isAttacking()) {
			this.getCurrentTarget().changeHealth(-this.getDamage());
		}
	}

	//Spawn patrol method created for development later ons
	public void spawnPatrol() {
		
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
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.attackEngine();
		super.draw(batch, parentAlpha);
	}

}