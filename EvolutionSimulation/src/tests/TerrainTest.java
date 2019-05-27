package tests;

import junit.framework.TestCase;
import simulation.Species;
import simulation.Environment;

public class TerrainTest extends TestCase {
	
	public void testMoveSpecies() {
		Environment m = new Environment(new int []{5},new int []{5},new int []{5},new int []{5},
				new int[][]{{0,0,255}},new String[] {"Herbivore"},0);
		Species s1 = new Species(0, 20, 100, 100, 60,25,4000);
		Species s2 = new Species(0, 20, 109, 109, 60,25,4000);
		m.moveSpecies(s2, s1, 0);
		assertEquals(80, s2.getxLoc());
		assertEquals(80, s2.getyLoc());
	}
	
	public void testMoveSpecies2() {
		Environment m = new Environment(new int []{5},new int []{5},new int []{5},new int []{5},
				new int[][]{{0,0,255}},new String[] {"Herbivore"},0);
		Species s1 = new Species(0, 20, 100, 100, 60,25, 4000);
		Species s2 = new Species(0, 20, 111, 111, 60,25, 4000);
		m.moveSpecies(s2, s1, 0);
		assertEquals(120, s2.getxLoc());
		assertEquals(120, s2.getyLoc());
	}
	
	public void testCreateSpecies() {
		Environment m = new Environment(new int []{5},new int []{5},new int []{5},new int []{5},
				new int[][]{{0,0,255}},new String[] {"Herbivore"},0);
		assertEquals(m.getNrSpecies(), 5);
	}

}
