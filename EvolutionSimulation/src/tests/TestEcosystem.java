package tests;

import java.awt.Color;

import junit.framework.TestCase;
import user_input.OptionData;
import user_input.PopulationSettings;
import gameobjects.Ecosystem;

public class TestEcosystem extends TestCase {
	
	public OptionData createOptionData() {
		OptionData p = new OptionData();
		p.addPopulationSettings(new PopulationSettings("Plant", "a name", 0, 1, 1, 1, 1, Color.GREEN, 1.0));
		p.addPopulationSettings(new PopulationSettings("Carnivore", "a name", 0, 1, 1, 1, 1, Color.GREEN, 1.0));
		p.setPlantEnergy(100);
		p.setPlantSize(5);
		return p;
	}
	
	public void testCreatePopOrderSeed() {
		Ecosystem t = new Ecosystem(createOptionData());
	}
}
