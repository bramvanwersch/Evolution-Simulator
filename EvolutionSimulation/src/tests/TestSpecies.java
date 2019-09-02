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
	
	public void tetsChangeXLoc() {
		Species s = new Plant(100, 100, 10, 10, 2000);
		s.changeXLoc(5);
		assertEquals(105, s.getxLoc());
		s.changeXLoc(-10);
		assertEquals(95, s.getxLoc());
	}
	
	public void testChangeYLoc() {
		Species s = new Plant(100, 100, 10, 10, 2000);
		s.changeYLoc(5);
		assertEquals(105, s.getyLoc());
		s.changeYLoc(-10);
		assertEquals(95, s.getyLoc());
	}
	
	public void testCanMultiply() {
		Species s = new Carnivore(10, 10, 10, 10, 1.0);
		s.changeEnergy(20002);
		assertTrue(s.isCanMultiply());
		s.changeEnergy(-2);
		assertFalse(s.isCanMultiply());
	}
	
	public void testInXBounds() {
		Species s = new Plant(1, 1, 10, 10, 2000);
		assertFalse(s.inXBounds(-1));
		assertTrue(s.inXBounds(100));
		assertFalse(s.inXBounds(2000));
		assertFalse(s.inXBounds(-500));
	}
	
	public void testInYBounds() {
		Species s = new Plant(1, 1, 10, 10, 2000);
		assertFalse(s.inYBounds(-1));
		assertTrue(s.inYBounds(100));
		assertFalse(s.inYBounds(2000));
		assertFalse(s.inYBounds(-500));
	}
	
	public void testIsAliveEnergy() {
		Species s = new Plant(1, 1, 10, 1, 2000);
		assertTrue(s.isAlive());
		s.changeEnergy(-2000);
		assertFalse(s.isAlive());
	}
	
	public void testIsAliveAge() {
		Species s = new Plant(1, 1, 10, 1, 2000);
		assertTrue(s.isAlive());
		//increase age by one second
		for (int i = 0; i < 20; i++) {
			s.addAge();
		}
		assertFalse(s.isAlive());
	}
	
	public void tetsAddAge() {
		Species s = new Plant(1, 1, 10, 1, 2000);
		for (int i = 0; i < 20; i++) {
			s.addAge();
		}		
		assertEquals(1, s.getAge());

	}


}
