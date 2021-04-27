package tests;

import java.awt.Color;

import junit.framework.TestCase;
import populations.HetrotrophPopulation;
import species.Herbivore;
import species.HetrotrophSpecies;
import user_input.HetrotrophPopulationSettings;

public class TestHetrotrophPopulation extends TestCase {
	
	public HetrotrophPopulationSettings createOptionSettings() {
		return new HetrotrophPopulationSettings("Herbivore", "a name", 0, 1, 1, 1, 1, Color.GREEN, 1.0);
	}

	public void testCreateOffspring() {
		HetrotrophPopulation p = new HetrotrophPopulation(createOptionSettings());
		HetrotrophSpecies h = new Herbivore(10, 10, 10, 10, 1.0);
		p.addSpecies(h);
		//If a species with a unsurvivable genome is created try again. This test is kind of bullshigt
		//atm but at least tests the fact only one species is created.
		while (p.getNrSpecies() < 2) {
			p.createOffspring(0);
		}
		assertEquals(2, p.getNrSpecies());
	}
	
	public void testCloneOffspring() {
		HetrotrophPopulation p = new HetrotrophPopulation(createOptionSettings());
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
