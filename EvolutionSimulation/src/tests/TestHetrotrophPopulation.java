package tests;

import java.awt.Color;

import junit.framework.TestCase;
import populations.HetrotrophPopulation;
import species.Herbivore;
import species.HetrotrophSpecies;

public class TestHetrotrophPopulation extends TestCase {
	
	public void testCreateOffspring() {
		HetrotrophPopulation p = new HetrotrophPopulation(Color.GREEN, "Herbivore", "some name");
		HetrotrophSpecies h = new Herbivore(10, 10, 10, 10, 1.0);
		p.addSpecies(h);
		p.createOffspring(0);
		assertEquals(2, p.getNrSpecies());
	}
	
	public void testCloneOffspring() {
		HetrotrophPopulation p = new HetrotrophPopulation(Color.GREEN, "Herbivore", "some name");
		HetrotrophSpecies h = new Herbivore(10, 10, 10, 10, 1.0);
		p.addSpecies(h);
		p.cloneOffspring(0);
		HetrotrophSpecies sp1 = p.getSpecies(0);
		HetrotrophSpecies sp2 = p.getSpecies(1);
		
		assertEquals(sp1.getMaxAge(), sp2.getMaxAge());
		assertEquals(sp1.getMaxSize(), sp2.getMaxSize());
		assertEquals(sp1.getScentRange(), sp2.getScentRange());
		assertEquals(sp1.getSpeed(), sp2.getSpeed());
	}

}
