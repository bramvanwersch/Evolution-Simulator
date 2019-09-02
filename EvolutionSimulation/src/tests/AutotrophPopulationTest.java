package tests;


import java.awt.Color;

import junit.framework.TestCase;
import populations.AutotrophPopulation;
import species.AutotrophSpecies;

public class AutotrophPopulationTest extends TestCase {
	
	public void testIsOverlappingPositive() {
		AutotrophPopulation p = new AutotrophPopulation(Color.GREEN, "Plant", "a nanme");
		AutotrophSpecies s = new AutotrophSpecies(10, 10, 2000);
		p.addSpecies(s);
		p.getSpecies(0).setXYLoc(10, 10);
		assertEquals(true, p.isOverlapping(10, 10));
		//just inside region
		assertEquals(true, p.isOverlapping(10, 29));
		assertEquals(true, p.isOverlapping(29, 10));
		//just outside
		assertEquals(false, p.isOverlapping(30, 10));
		assertEquals(false, p.isOverlapping(10, 30));
	}
	
	public void testIsOverlappingNegative() {
		AutotrophPopulation p = new AutotrophPopulation(Color.GREEN, "Plant", "a nanme");
		AutotrophSpecies s = new AutotrophSpecies(10, 10, 2000);
		p.addSpecies(s);
		p.getSpecies(0).setXYLoc(100, 100);
		assertEquals(true, p.isOverlapping(100, 100));
		//just inside region
		assertEquals(true, p.isOverlapping(100, 81));
		assertEquals(true, p.isOverlapping(81, 100));
		//just outside
		assertEquals(false, p.isOverlapping(80, 100));
		assertEquals(false, p.isOverlapping(100, 80));
	}

}
