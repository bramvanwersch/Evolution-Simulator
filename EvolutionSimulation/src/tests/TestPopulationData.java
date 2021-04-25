package tests;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;
import populations.PopulationData;

public class TestPopulationData extends TestCase {
	
	public void testSetDataDivisionFactor() {
		PopulationData p = new PopulationData();
		for (int i = 0; i < 60; i++) {
			p.setTime(i);
		}
		p.setDataDivisionFactor();
		assertEquals(2, p.getDataDivisionFactor());
	}
	
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
		for (int i = 0; i < 120; i++) {
			a.add(new double[] {2.0, 1.0, 3.0});
		}
		ArrayList<double[]> res = new ArrayList<double[]>();
		for (int i = 0; i < 40; i++) {
			res.add(new double[] {2.0, 1.0, 3.0});
		}
		a = p.reduceAllData(a);
		for (int j = 0; j < res.size(); j++) {
			assertEquals(Arrays.toString(res.get(j)), Arrays.toString(a.get(j)));
		}
	}
	
	public void testDoubleListToIntArray() {
		PopulationData p = new PopulationData();
		ArrayList<Double> a = new ArrayList<Double>();
		a.add(1.0);
		a.add(2.0);
		a.add(5.0);
		int[] res = new int[] {1, 2, 5};
		assertEquals(Arrays.toString(res), Arrays.toString(p.doubleListToIntArray(a)));
	}
	
	public void testGetAverageValues() {
		PopulationData p = new PopulationData();
		ArrayList<double[]> a = new ArrayList<double[]>();
		for (int i = 0; i < 20; i++) {
			a.add(new double[] {2.0, 1.0, 3.0});
		}
		ArrayList<Double> res = new ArrayList<Double>();
		for (int i = 0; i < 20; i++) {
			res.add(2.0);
		}
		assertEquals(res, p.getAverageValues(a));
	}
	
	public void testReduceAvgDataArray() {
		PopulationData p = new PopulationData();
		for (int i = 0; i < 60; i++) {
			p.setTime(i);
		}
		ArrayList<Double> a = new ArrayList<Double>();
		for (int i = 0; i < 60; i++) {
			a.add(2.0);
		}
		ArrayList<Double> res = new ArrayList<Double>();
		for (int i = 0; i < 30; i++) {
			res.add(2.0);
		}
		p.setDataDivisionFactor();
		assertEquals(res, p.reduceAvgData(a));
	}
	
	public void testAllSum() {
		PopulationData p = new PopulationData();
		ArrayList<double[]> a = new ArrayList<double[]>();
		a.add(new double[] {1.0, 1.0, 1.0});
		a.add(new double[] {2.0, 1.0, 3.0});
		a.add(new double[] {3.0, 1.0, 5.0});
		assertEquals(Arrays.toString(new double[] {6.0, 3.0, 9.0}), Arrays.toString(p.allSum(a)));
	}
}
