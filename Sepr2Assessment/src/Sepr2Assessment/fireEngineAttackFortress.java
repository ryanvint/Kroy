package Sepr2Assessment;

public class fireEngineAttackFortress {
	//These variables are all used to provide mock values for the
	//function as their associated functions have already been tested
	int targetHealth;
	boolean canAttack;
	boolean damageDealt;
	
	int currentVolume;
	int currentHealth;
	
	public void setInRange(boolean canAttack) {
		this.canAttack = canAttack;
	}
	
	public boolean getInRange() {
		return this.canAttack;
	}
	
	public boolean getDamageDealt() {
		return this.damageDealt;
	}
		
	public void setTargetHealth(int targetHealth) {
		this.targetHealth = targetHealth;
	}
	
	public int getTargetHealth() {
		return this.targetHealth;
	}
	
	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}
	
	public int getCurrentVolume() {
		return this.currentVolume;
	}

	public void setCurrentHealth(int currentHealth){
		this.currentHealth = currentHealth;
	}
	
	public int getCurrentHealth() {
		return this.currentHealth;
	}
		
	
	public boolean isEnoughWater() { //Method to check that a fire engines currentVolume is not empty 
		if(this.getCurrentVolume()>0) return true;
		return false;
	}
	
	public boolean isEnoughHealth() {
		if(this.getCurrentHealth()>0) return true;
		return false;
	}
	public void attackFortress() {
		if(this.getInRange() && this.isEnoughWater() && this.isEnoughHealth() && this.getTargetHealth()>0) {
			//this.dealDamage();
			this.damageDealt = true;
		}
		else {
			//this.setCurrentTarget(null);
			this.damageDealt = false;
		}
	}
}
