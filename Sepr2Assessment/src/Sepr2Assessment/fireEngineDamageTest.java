package Sepr2Assessment;

public class fireEngineDamageTest {
	//As getters and setters function we can provide a mock
	//for target health within the test function
	int targetHealth;
	//As the two functions within the conditions of damageDealt
	//have already been tested we can assume they function and use
	//this variable to just show if the correct conditional path
	//was taken for the correct inputs
	boolean damageDealt;
	
	int currentVolume;
	
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
	
	//This also tests the isEnoughWater function
	public boolean isEnoughWater() { 
		if(this.getCurrentVolume()>0) return true;
		return false;
	}
	
	public void dealDamage() {
		if(this.getTargetHealth()>0 && isEnoughWater()) {
			//this.changeWater(-this.getWaterRate());
			//currentTarget.changeHealth(-this.getDamage());
			damageDealt = true;
		}else {
			damageDealt = false;
		}
	}
}
