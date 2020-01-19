package Sepr2Assessment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class fortressTests {

	@Test
	void UT_FORTRESS_CHANGETARGET() {
		fortressChangeTarget testFortress = new fortressChangeTarget();
		testFortress.setCurrentHealth(100);
		//v1
		testFortress.setCurrentEngineHealth(10);
		testFortress.setCurrentTarget("Engine");
		testFortress.setInRange(true);
		testFortress.changeFortressTarget();
		assertFalse(testFortress.getNewTarget());
		//v2
		testFortress.setCurrentEngineHealth(0);
		testFortress.setCurrentTarget("Engine");
		testFortress.setInRange(true);
		testFortress.changeFortressTarget();
		assertTrue(testFortress.getNewTarget());
		//v3
		testFortress.setCurrentEngineHealth(50);
		testFortress.setCurrentTarget("Engine");
		testFortress.setInRange(false);
		testFortress.changeFortressTarget();
		assertTrue(testFortress.getNewTarget());
		//v4
		testFortress.setCurrentTarget(null);
		assertTrue(testFortress.getNewTarget());
	}

}
