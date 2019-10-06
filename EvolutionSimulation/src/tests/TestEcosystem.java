package tests;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;
import populations.Population;
import user_input.OptionData;
import user_input.PopulationSettings;
import gameobjects.Ecosystem;

public class TestEcosystem extends TestCase {
	
	public OptionData createOptionData(int aS1, int aS2, int aS3, int aS4) {
		OptionData p = new OptionData();
		p.addPopulationSettings(new PopulationSettings("Plant", "a name", aS1, 1, 1, 1, 1, Color.GREEN, 1.0));
		p.addPopulationSettings(new PopulationSettings("Herbivore", "a name", aS2, 1, 1, 1, 1, Color.GREEN, 1.0));
		p.addPopulationSettings(new PopulationSettings("Carnivore", "a name", aS3, 1, 1, 1, 1, Color.GREEN, 1.0));
		p.addPopulationSettings(new PopulationSettings("Omnivore", "a name", aS4, 1, 1, 1, 1, Color.GREEN, 1.0));
		p.setPlantEnergy(100);
		p.setPlantSize(5);
		return p;
	}
	
	public void testCreatePopOrderSeed() {
		OptionData d = createOptionData(1,1,1,1);
		Ecosystem t = new Ecosystem(d);
		assertEquals(Arrays.toString(new int[] {0,1,2,3}), Arrays.toString(t.createPopOrderSeed(d.getPopulationSettingSize())));
	}
	
	public void testGetNrHerbivores() {
		OptionData d = createOptionData(4,3,2,5);
		Ecosystem t = new Ecosystem(d);
		assertEquals(3, t.getNrHerbivores());
	}
	
	public void testGetNrCarnivores() {
		OptionData d = createOptionData(4,3,2,5);
		Ecosystem t = new Ecosystem(d);
		assertEquals(2, t.getNrCarnivores());
	}
	
	public void testGetNrOmnivores() {
		OptionData d = createOptionData(4,3,2,5);
		Ecosystem t = new Ecosystem(d);
		assertEquals(5, t.getNrOmnivores());
	}
	
	public void testGetNrHetrotrophSpecies() {
		OptionData d = createOptionData(4,3,2,5);
		Ecosystem t = new Ecosystem(d);
		assertEquals(10, t.getNrHetrotrophSpecies());
	}
	
	public void testGetNrDeadHetrotrophSpecies() {
		OptionData d = createOptionData(4,3,2,5);
		Ecosystem t = new Ecosystem(d);
		assertEquals(0, t.getNrDeadHetrotrophSpecies());
	}

	//TODO needs to be activated when the plant populations are correctly created
//	public void testGetNrPopulations() {
//		OptionData d = createOptionData(0,0,0,0);
//		Ecosystem t = new Ecosystem(d);
//		assertEquals(4, t.getNrPopulations());
//	}
	
	public void testGetPopulation() {
		OptionData d = createOptionData(4,3,2,5);
		Ecosystem t = new Ecosystem(d);
		assertEquals("Herbivore", t.getPopulation(0).getType());
		assertEquals("Carnivore", t.getPopulation(1).getType());
		assertEquals("Omnivore", t.getPopulation(2).getType());
		assertEquals("Plant", t.getPopulation(3).getType());
	}
	
	public void testGetNrHetrotrophPopulations() {
		OptionData d = createOptionData(0,0,0,0);
		Ecosystem t = new Ecosystem(d);
		assertEquals(3, t.getNrHetrotrophPopulations());
	}
	
	public void testGetHetrotrophPopulation() {
		OptionData d = createOptionData(4,3,2,5);
		Ecosystem t = new Ecosystem(d);
		assertEquals("Herbivore", t.getHetrotrophPopulation(0).getType());
		assertEquals("Carnivore", t.getHetrotrophPopulation(1).getType());
		assertEquals("Omnivore", t.getHetrotrophPopulation(2).getType());


	}
}
