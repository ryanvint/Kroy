package Sepr2Assessment;

public class entityCanAttackTest {
	//As isEntityInRange has already been tested we can just
	//provide mock results for it, using the boolean inRange
	boolean inRange;
	
	int currentHealth;
	
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	public int getCurrentHealth() {
		return this.currentHealth;
	}
	
	public void setInRange(boolean inRange) {
		this.inRange = inRange;
	}
	
	public boolean canEntityAttackEntity(entityCanAttackTest entityToAttack) {
		if(entityToAttack!=null) {
				if(inRange && this.getCurrentHealth()!=0) {
					return true;
				}
		}
		return false;
	}

}
