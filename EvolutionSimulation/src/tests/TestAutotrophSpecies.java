package tests;

import junit.framework.TestCase;
import species.AutotrophSpecies;

public class TestAutotrophSpecies extends TestCase {
	
	public void testEat() {
		AutotrophSpecies s = new AutotrophSpecies(0,0,0);
		double eatFraction1 = s.eat(new double[] {19,20,20});
		assertEquals(0.955, eatFraction1);
		double eatFraction2 = s.eat(new double[] {30,20,20});
		assertEquals(1.0, eatFraction2);
		double eatFraction3 = s.eat(new double[] {0,0,0});
		assertEquals(0.1, eatFraction3);

	}

}
