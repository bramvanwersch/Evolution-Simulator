package tests;

import java.util.ArrayList;

import junit.framework.TestCase;
import utility_functions.Utility;

public class TestUtility extends TestCase {
	
	public void testSum() {
		ArrayList<Double> a = new ArrayList<Double>();
		a.add(1.0);
		a.add(2.0);
		a.add(5.0);
		assertEquals(8.0, Utility.sum(a));
	}
	
	public void testSum2() {
		double[] a = new double[] {1.0, 2.0, 5.0}; 
		assertEquals(8.0, Utility.sum(a));
	}

}
