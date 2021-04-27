package tests;

import java.awt.Color;
import java.util.Arrays;

import junit.framework.TestCase;
import user_input.OptionData;
import user_input.HetrotrophPopulationSettings;
import gameobjects.Ecosystem;

public class TestEcosystem extends TestCase {
	
	public OptionData createOptionData(int aS1, int aS2, int aS3, int aS4) {
		OptionData p = new OptionData();
		p.addPopulationSettings(new HetrotrophPopulationSettings("Plant", "a name", aS1, 4, 1, 1, 0, Color.GREEN, 1.0));
		p.addPopulationSettings(new HetrotrophPopulationSettings("Herbivore", "a name", aS2, 2, 1, 3, 2, Color.GREEN, 1.0));
		p.addPopulationSettings(new HetrotrophPopulationSettings("Carnivore", "a name", aS3, 0, 4, 1, 1, Color.GREEN, 1.0));
		p.addPopulationSettings(new HetrotrophPopulationSettings("Omnivore", "a name", aS4, 10, 1, 2, 15, Color.GREEN, 1.0));
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
	
	public void testGetMaxSpeciesHetPopulation() {
		OptionData d = createOptionData(4,3,5,5);
		Ecosystem t = new Ecosystem(d);
		assertEquals("Carnivore", t.getMaxSpeciesHetPopulations().getType());
	}
	
	public void testGetMinSpeciesHetPopulation() {
		OptionData d = createOptionData(4,3,5,2);
		Ecosystem t = new Ecosystem(d);
		assertEquals("Omnivore", t.getMinSpeciesHetPopulations().getType());
		t.getPopulation(2).cloneOffspring(0);
		assertEquals("Herbivore", t.getMinSpeciesHetPopulations().getType());
	}
	
	public void testGetAveragePopulationStats() {
		OptionData d = createOptionData(4,3,2,5);
		Ecosystem t = new Ecosystem(d);
		double[][] ref = {{2.0, 1.0, 4.0}, {4.0, 0.0, 10.0}, {2.0, 1.0, 3.0}, {6.0, 1.0, 15.0}, {2.187596126979267, 0.9325326533546022, 3.322723074228597}};
		assertEquals(Arrays.deepToString(ref), Arrays.deepToString(t.getAveragePopulationStats()));
	}
}
