package tests;

import junit.framework.TestCase;
import species.Herbivore;
import species.HetrotrophSpecies;

public class TestHetrotrophSepcies extends TestCase {

	
	public void testEatSize() {
		HetrotrophSpecies s =  new Herbivore(12, 10, 10, 10, 1.0);
		s.setXYLoc(100, 100);
		//startSize is half the size
		assertTrue(s.eat(100, 100, 5, 2000));
		assertFalse(s.eat(100, 100, 6, 2000));
	}
	
	public void testEatLocation() {
		HetrotrophSpecies s =  new Herbivore(12, 10, 10, 10, 1.0);
		s.setXYLoc(100, 100);
		//startSize is half the size
		assertTrue(s.eat(100, 100, 1, 2000));
		assertFalse(s.eat(90, 100, 1, 2000));
		assertFalse(s.eat(100, 90, 1, 2000));
		assertTrue(s.eat(98, 98, 1, 2000));
		assertTrue(s.eat(102, 102, 1, 2000));

	}
	
	public void testMove() {
		//becuase of the calculation the value can be smaller by 1 then expected
		for (int i = 0; i < 100; i++) {
			HetrotrophSpecies s =  new Herbivore(12, 10, 10, 10, 1.0);
			s.setFacingDirection(Math.PI);
			s.setXYLoc(100, 100);
			s.move();
			System.out.println(s.getxLoc() + "  " + s.getyLoc() + "  " + s.getSpeed());
			assertTrue(s.getxLoc() >= 100);
			assertTrue(s.getyLoc() >= 109 && s.getyLoc() <= 111);
			System.out.println("round" + i);
		}
	}
}
