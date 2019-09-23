package tests;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;
import populations.PopulationData;

public class TestPopulationData extends TestCase {
	
	public void testDoubleListToIntArrayOfArrays() {
		PopulationData p = new PopulationData();
		ArrayList<double[]> a = new ArrayList<double[]>();
		a.add(new double[] {1.0, 1.0, 1.0});
		a.add(new double[] {2.0, 1.0, 3.0});
		a.add(new double[] {3.0, 1.0, 5.0});
		int[][] res = new int[][] {{1, 1, 1}, {2, 1, 3}, {3, 1, 5}};
		assertEquals(Arrays.deepToString(res), Arrays.deepToString(p.doubleListToIntArrayOfArrays(a)));
	}
	
	public void testReduceAllDataNonReduced() {
		PopulationData p = new PopulationData();
		ArrayList<double[]> a = new ArrayList<double[]>();
		for (int i = 0; i < 49; i++) {
			a.add(new double[] {2.0, 1.0, 3.0});
		}
		ArrayList<double[]> res = a;
		a = p.reduceAllData(a);
		for (int j = 0; j < res.size(); j++) {
			assertEquals(Arrays.toString(res.get(j)), Arrays.toString(a.get(j)));
		}

	}
	
	public void testReduceAllDataReduced() {
		PopulationData p = new PopulationData();
		ArrayList<double[]> a = new ArrayList<double[]>();
		for (int i = 0; i < 60; i++) {
			a.add(new double[] {2.0, 1.0, 3.0});
		}
	}
}
