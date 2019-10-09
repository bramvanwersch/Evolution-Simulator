package tests;

import junit.framework.TestCase;
import species.Herbivore;
import species.HetrotrophSpecies;

public class TestHetrotrophSpecies extends TestCase {

	
	public void testEatSize() {
		HetrotrophSpecies s =  new Herbivore(12, 10, 10, 10, 1.0);
		s.setXYLoc(100, 100);
		//startSize is half the size
		assertTrue(s.eat(100, 100, 6, 2000));
		assertFalse(s.eat(100, 100, 7, 2000));
	}
	
	public void testEatLocation() {
		HetrotrophSpecies s =  new Herbivore(12, 10, 10, 10, 1.0);
		s.setXYLoc(100, 100);
		//startSize is half the size
		assertTrue(s.eat(100, 100, 1, 2000));
		assertFalse(s.eat(97, 100, 1, 2000));
		assertFalse(s.eat(100, 97, 1, 2000));
		assertTrue(s.eat(98, 98, 1, 2000));
		assertTrue(s.eat(102, 102, 1, 2000));

	}
	
	public void testXMove() {
		HetrotrophSpecies s =  new Herbivore(12, 10, 10, 10, 1.0);
		s.setFacingDirection(Math.PI);
		s.setXYLoc(100, 100);
		s.move();
		assertEquals(110, s.getyLoc());
}
	
	public void testYMove() {
		HetrotrophSpecies s =  new Herbivore(12, 10, 10, 10, 1.0);
		s.setFacingDirection(0.5 * Math.PI);
		s.setXYLoc(100, 100);
		s.move();
		assertEquals(110, s.getxLoc());
	}
	
	public void testMoveDiagonal1() {
		HetrotrophSpecies s =  new Herbivore(12, 10, 10, 10, 1.0);
		s.setFacingDirection(0.35);
		s.setXYLoc(100, 100);
		s.move();
		assertEquals(103,s.getxLoc());
		assertEquals(91, s.getyLoc());
	}
	
	public void testMoveDiagonal2() {
		HetrotrophSpecies s =  new Herbivore(12, 10, 10, 10, 1.0);
		s.setFacingDirection(120);
		s.setXYLoc(100, 100);
		s.move();
		assertEquals(106,s.getxLoc());
		assertEquals(92, s.getyLoc());
	}
	
	public void testGetSize() {
		HetrotrophSpecies s =  new Herbivore(12, 10, 10, 10, 1.0);
		assertEquals(6, s.getSize());
	}
	
	public void testGetSizeAfterTime() {
		HetrotrophSpecies s =  new Herbivore(12, 10, 20, 10, 1.0);
		//increase age by 5 sec
		for (int i = 0; i < 100; i++) {
			s.addAge();
		}
		assertEquals(9, s.getSize());
	}
		
	public void testGetSizeAfterMoreThenHalfTime() {
		HetrotrophSpecies s =  new Herbivore(12, 10, 20, 10, 1.0);
		for (int i = 0; i < 220; i++) {
			s.addAge();
		}
		assertEquals(12, s.getSize());
	}
	
	public void testGetAttributeData() {
		assertEquals(true, false);
	}

	public void testInXBounds() {
		HetrotrophSpecies s =  new Herbivore(12, 10, 20, 10, 1.0);
		s.setXYLoc(940, 940);
		assertTrue(s.inXBounds(6));
		assertFalse(s.inXBounds(7));
	}
	
	public void testInYBounds() {
		HetrotrophSpecies s =  new Herbivore(12, 10, 20, 10, 1.0);
		s.setXYLoc(940, 940);
		assertTrue(s.inYBounds(6));
		assertFalse(s.inYBounds(7));
	}
}
