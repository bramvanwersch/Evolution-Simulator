package tests;


import java.awt.Color;

import junit.framework.TestCase;
import populations.AutotrophPopulation;
import species.AutotrophSpecies;

public class AutotrophPopulationTest extends TestCase {
	
	public void testIsOverlapping() {
		AutotrophPopulation p = new AutotrophPopulation(Color.GREEN, "Plant", "a nanme");
		AutotrophSpecies s = new AutotrophSpecies(10, 10, 2000);
		p.addSpecies(s);
		p.getSpecies(0).setXYLoc(10, 10);
		assertEquals(p.isOverlapping(10, 10), true);
		assertEquals(p.isOverlapping(0, 29), true);
		assertEquals(p.isOverlapping(29, 0), true);
		assertEquals(p.isOverlapping(30, 0), false);
		assertEquals(p.isOverlapping(0, 30), false);
	}

}
