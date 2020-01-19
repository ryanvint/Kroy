package Sepr2Assessment;

public class entityHealthTest {
	
	public int currentHealth;
	public int maxHealth;
	
	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public void changeHealth(int change) {							
		this.setCurrentHealth(this.getCurrentHealth() + change);
		if(this.getCurrentHealth()<0) {
			this.setCurrentHealth(0);
		}
		else if(this.getCurrentHealth()>this.getMaxHealth()) {
			this.setCurrentHealth(this.getMaxHealth());
		}
	}
}
