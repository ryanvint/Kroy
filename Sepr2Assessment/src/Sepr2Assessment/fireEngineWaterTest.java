package Sepr2Assessment;

public class fireEngineWaterTest {
	
	public int currentVolume;
	public int maxVolume;
	
	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}
	
	public int getMaxVolume() {
		return maxVolume;
	}

	public void setMaxVolume(int maxVolume) {
		this.maxVolume = maxVolume;
	}
	
	public void changeWater(int change) {
		this.setCurrentVolume(this.getCurrentVolume() + change);
		if(this.getCurrentVolume()<0) {
			this.setCurrentVolume(0);
		}
		else if(this.getCurrentVolume()>this.getMaxVolume()) {
			this.setCurrentVolume(this.getMaxVolume());
		}
	}
}
