package Sepr2Assessment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class fireEngineTests {

	@Test
	void UT_FIREENGINE_WATERCHANGE() {
		fireEngineWaterTest testEngine = new fireEngineWaterTest();
		testEngine.setCurrentVolume(0);
		testEngine.setMaxVolume(100);
		testEngine.changeWater(10);
		assertEquals(testEngine.getCurrentVolume(), 10);
	}
	
	@Test
	void UT_FIREENGINE_WATERCHANGE_LIMIT() {
		fireEngineWaterTest testEngine = new fireEngineWaterTest();
		//v1
		testEngine.setCurrentVolume(100);
		testEngine.setMaxVolume(100);
		testEngine.changeWater(10);
		assertEquals(testEngine.getCurrentVolume(), 100);
		//v2
		testEngine.setCurrentVolume(94);
		testEngine.setMaxVolume(100);
		testEngine.changeWater(10);
		assertEquals(testEngine.getCurrentVolume(), 100);
		//v3
		testEngine.setCurrentVolume(0);
		testEngine.changeWater(-10);
		assertEquals(testEngine.getCurrentVolume(), 0);
		//v4
		testEngine.setCurrentVolume(7);
		testEngine.changeWater(-10);
		assertEquals(testEngine.getCurrentVolume(), 0);
	}
	
	@Test
	void UT_FIREENGINE_DEALDAMAGE() {
		fireEngineDamageTest testEngine = new fireEngineDamageTest();
		//v1 
		testEngine.setCurrentVolume(10);
		testEngine.setTargetHealth(100);
		testEngine.dealDamage();
		assertTrue(testEngine.getDamageDealt());
		//v2
		testEngine.setCurrentVolume(0);
		testEngine.setTargetHealth(100);
		testEngine.dealDamage();
		assertFalse(testEngine.getDamageDealt());
		//v3
		testEngine.setCurrentVolume(10);
		testEngine.setTargetHealth(0);
		testEngine.dealDamage();
		assertFalse(testEngine.getDamageDealt());
	}
	
	@Test
	void UT_FIREENGINE_ATTACKFORTRESS() {
		fireEngineAttackFortress testEngine = new fireEngineAttackFortress();
		//v1
		testEngine.setCurrentVolume(10);
		testEngine.setCurrentHealth(100);
		testEngine.setTargetHealth(100);
		testEngine.setInRange(true);
		testEngine.attackFortress();
		assertTrue(testEngine.getDamageDealt());
		//v2
		testEngine.setCurrentVolume(0);
		testEngine.setCurrentHealth(100);
		testEngine.setTargetHealth(100);
		testEngine.setInRange(true);
		testEngine.attackFortress();
		assertFalse(testEngine.getDamageDealt());
		//v3
		testEngine.setCurrentVolume(10);
		testEngine.setCurrentHealth(0);
		testEngine.setTargetHealth(100);
		testEngine.setInRange(true);
		testEngine.attackFortress();
		assertFalse(testEngine.getDamageDealt());
		//v4
		testEngine.setCurrentVolume(10);
		testEngine.setCurrentHealth(100);
		testEngine.setTargetHealth(0);
		testEngine.setInRange(true);
		testEngine.attackFortress();
		assertFalse(testEngine.getDamageDealt());
		//v5
		testEngine.setCurrentVolume(0);
		testEngine.setCurrentHealth(100);
		testEngine.setTargetHealth(0);
		testEngine.setInRange(false);
		testEngine.attackFortress();
		assertFalse(testEngine.getDamageDealt());
	}

}
