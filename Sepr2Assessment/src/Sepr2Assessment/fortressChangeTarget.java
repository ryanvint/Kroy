package Sepr2Assessment;

public class fortressChangeTarget {
	int currentEngineHealth;
	String currentTarget;
	boolean inRange;
	boolean newTarget;
	
	int currentHealth;
	
	public boolean getNewTarget() {
		return this.newTarget;
	}
	
	public void setCurrentEngineHealth(int currentEngineHealth) {
		this.currentEngineHealth = currentEngineHealth;
	}
	
	public int getCurrentEngineHealth() {
		return this.currentEngineHealth;
	}
	
	public void setCurrentTarget(String currentTarget) {
		this.currentTarget = currentTarget;
	}
	
	public String getCurrentTarget() {
		return this.currentTarget;
	}
	
	public void setInRange(boolean inRange) {
		this.inRange = inRange;
	}
	
	public boolean getInRange() {
		return this.inRange;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	
	//These are the methods being tested
	
	public boolean isAttacking() {
		if(this.getCurrentTarget() == null) return false;
		else return true;
	}
	
	public boolean isCurrentTargetValid() {
		if(!(this.getInRange() && this.getCurrentHealth() > 0)  || this.getCurrentEngineHealth() == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void changeFortressTarget() {
		if(this.isAttacking()) {
			if(!this.isCurrentTargetValid()) {
				newTarget = true;
				//this.setNewTarget(engines, selectedEngines);
			}
		}
		else {
			newTarget = false;
			//this.setNewTarget(engines, selectedEngines);
		}
	}
	
}
