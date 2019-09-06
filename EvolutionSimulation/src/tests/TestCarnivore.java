package tests;

import junit.framework.TestCase;
import species.Carnivore;

public class TestCarnivore extends TestCase {
	
	
	public void testEatSize() {
		Carnivore s =  new Carnivore(12, 10, 10, 10, 1.0);
		s.setXYLoc(100, 100);
		//startSize is half the size
		assertTrue(s.eat(100, 100, 6, 2000));
		assertFalse(s.eat(100, 100, 7, 2000));
	}
	
	public void testEatLocation() {
		Carnivore s =  new Carnivore(12, 10, 10, 10, 1.0);
		s.setXYLoc(100, 100);
		//startSize is half the size
		assertTrue(s.eat(100, 100, 1, 2000));
		assertFalse(s.eat(97, 100, 1, 2000));
		assertFalse(s.eat(100, 97, 1, 2000));
		assertTrue(s.eat(98, 98, 1, 2000));
		assertTrue(s.eat(102, 102, 1, 2000));
	}
	
	public void testEatTimeCheck() {
		Carnivore s =  new Carnivore(12, 10, 10, 10, 1.0);
		s.setXYLoc(100, 100);
		assertFalse(s.getEating());
		s.eat(100, 100, 1, 2000);
		assertTrue(s.getEating());
	}
	
//	public void testEatTimeCheckAfterTime() {
//		Carnivore s =  new Carnivore(12, 10, 10, 10, 1.0);
//		s.setXYLoc(100, 100);
//		s.eat(100, 100, 1, 2000);
//		for (int i = 0; i < 222; i ++) {
//			s.addAge();
//		}
//		s.extendedNextTimePoint();
//		assertFalse(s.getEating());
//	}

}
