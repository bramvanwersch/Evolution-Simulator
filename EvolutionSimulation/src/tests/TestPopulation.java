package tests;

import java.awt.Color;
import java.util.Arrays;

import junit.framework.TestCase;
import populations.AutotrophPopulation;
import populations.Population;
import user_input.HetrotrophPopulationSettings;

public class TestPopulation extends TestCase {
	
	public HetrotrophPopulationSettings createOptionSettings() {
		return new HetrotrophPopulationSettings("Plant", "a name", 0, 1, 1, 1, 1, Color.GREEN, 1.0);
	}
	
	public void testCalcAvgAttribute() {
		Population p = new AutotrophPopulation(createOptionSettings());
		double[] attrArray = new double[] {2.0, 5.0, 10.0, 0.0};
 		assertEquals(4.25, p.calcAvgAttribute(attrArray));	
	}
	
	public void testCalcMinMax() {
		Population p = new AutotrophPopulation(createOptionSettings());
		double[] attrArray = new double[] {2.0, 5.0, 10.0, 0.0};
		assertEquals(Arrays.toString(new double[] {0.0, 10.0}), Arrays.toString(p.calcMinMax(attrArray)));
	}
	
	public void testGetStats() {
		assertEquals(true, false);
	}

}
