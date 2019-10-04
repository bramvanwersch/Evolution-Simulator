package tests;


import java.awt.Color;

import junit.framework.TestCase;
import populations.AutotrophPopulation;
import populations.HetrotrophPopulation;
import species.AutotrophSpecies;
import species.Herbivore;
import species.HetrotrophSpecies;
import species.Plant;
import user_input.PopulationSettings;

public class TestAutotrophPopulation extends TestCase {
	
	public PopulationSettings createOptionSettings() {
		return new PopulationSettings("Plant", "a name", 0, 1, 1, 1, 1, Color.GREEN, 1.0);
	}
	
	public void testIsOverlappingPositive() {
		AutotrophPopulation p = new AutotrophPopulation(createOptionSettings());
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
		AutotrophPopulation p = new AutotrophPopulation(createOptionSettings());
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
	
	public void testCloneOffspring() {
		AutotrophPopulation p = new AutotrophPopulation(createOptionSettings());
		AutotrophSpecies h = new AutotrophSpecies(10, 10, 2000);
		p.addSpecies(h);
		p.cloneOffspring(0);
		AutotrophSpecies sp1 = p.getSpecies(0);
		AutotrophSpecies sp2 = p.getSpecies(1);
		
		assertEquals(sp1.getMaxAge(), sp2.getMaxAge());
		assertEquals(sp1.getSize(), sp2.getSize());
	}

}
