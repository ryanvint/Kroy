
public class Entity {

	int currentHealth, maxHealth, range, damage, currentLocationX, currentLocationY;
	
	//Constructor
	public Entity(int currentHealth, int maxHealth, int range, int damage, int currentLocationX, int currentLocationY) {
		this.currentHealth = currentHealth;
		this.maxHealth = maxHealth;
		this.range = range;
		this.damage = damage;
		this.currentLocationX = currentLocationX;
		this.currentLocationY = currentLocationY;
	}

	//Getters and Setters
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

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getCurrentLocationX() {
		return currentLocationX;
	}

	public void setCurrentLocationX(int currentLocationX) {
		this.currentLocationX = currentLocationX;
	}

	public int getCurrentLocationY() {
		return currentLocationY;
	}

	public void setCurrentLocationY(int currentLocationY) {
		this.currentLocationY = currentLocationY;
	}

	//Methods
	public void destroy() {
		
	}
	
	public void attack() {
		
	}
	
	public void increaseHealth() {
		
	}
	
	public void decreaseHealth() {
		
	}
	
	public void increaseMaxHealth() {
		
	}
	
	public void increaseRange() {
		
	}
	
	public void increaseDamage() {
		
	}
}