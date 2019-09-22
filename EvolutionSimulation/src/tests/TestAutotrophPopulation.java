package tests;


import java.awt.Color;

import junit.framework.TestCase;
import populations.AutotrophPopulation;
import populations.HetrotrophPopulation;
import species.AutotrophSpecies;
import species.Herbivore;
import species.HetrotrophSpecies;
import species.Plant;

public class TestAutotrophPopulation extends TestCase {
	
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
	//function cannot be tested because there is a chance the species is not created when it is created
	//inside the original species.
//	public void testCreateOffspring() {
//		AutotrophPopulation p = new AutotrophPopulation(Color.GREEN, "Plant", "some name");
//		AutotrophSpecies h = new AutotrophSpecies(10, 10, 2000);
//		p.addSpecies(h);
//		p.createOffspring(0);
//		assertEquals(2, p.getNrSpecies());
//	}
	
	public void testCloneOffspring() {
		AutotrophPopulation p = new AutotrophPopulation(Color.GREEN, "Plant", "some name");
		AutotrophSpecies h = new AutotrophSpecies(10, 10, 2000);
		p.addSpecies(h);
		p.cloneOffspring(0);
		AutotrophSpecies sp1 = p.getSpecies(0);
		AutotrophSpecies sp2 = p.getSpecies(1);
		
		assertEquals(sp1.getMaxAge(), sp2.getMaxAge());
		assertEquals(sp1.getSize(), sp2.getSize());
	}

}
