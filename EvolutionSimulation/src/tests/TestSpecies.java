package tests;

import junit.framework.TestCase;
import species.Carnivore;
import species.Plant;
import species.Species;

public class TestSpecies extends TestCase {
	
	public void testGetOfsetXYLoc() {
		Species s = new Plant(10, 10, 2000);
		s.setXYLoc(100, 100);
		int[] test = s.getOfsetXYLoc();
		//test 100 times
		for (int i =0; i < 100; i++) {
			assertTrue(test[0] >= 50 && test[0] <= 150 && test[1] >= 50 && test[1] <= 150);
		}
	}
	public void testGetOfsetXYLoc2() {
		Species s = new Plant(10, 10, 2000);
		s.setXYLoc(900, 900);
		int[] test = s.getOfsetXYLoc();
		//test 100 times
		for (int i =0; i < 100; i++) {
			assertTrue(test[0] >= 850 && test[0] <= 950 && test[1] >= 850 && test[1] <= 950);
		}
	}
	
	public void testCanMultiply() {
		Species s = new Carnivore(10, 10, 10, 10, 1.0);
		
	}

}
