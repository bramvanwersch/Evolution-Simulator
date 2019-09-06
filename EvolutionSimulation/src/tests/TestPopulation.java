package tests;

import java.awt.Color;
import java.util.Arrays;

import junit.framework.TestCase;
import populations.AutotrophPopulation;
import populations.Population;

public class TestPopulation extends TestCase {
	
	public void testCalcAvgAttribute() {
		Population p = new AutotrophPopulation(Color.BLUE, "Plant", "Some name");
		double[] attrArray = new double[] {2.0, 5.0, 10.0, 0.0};
 		assertEquals(4.25, p.calcAvgAttribute(attrArray));	
	}
	
	public void testCalcMinMax() {
		Population p = new AutotrophPopulation(Color.BLUE, "Plant", "Some name");
		double[] attrArray = new double[] {2.0, 5.0, 10.0, 0.0};
		assertEquals(Arrays.toString(new double[] {0.0, 10.0}), Arrays.toString(p.calcMinMax(attrArray)));
	}

}
