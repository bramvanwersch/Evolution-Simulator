package tests;

import java.util.Arrays;

import genome.Genome;
import junit.framework.TestCase;

public class GenomeTest extends TestCase {
	
	public void testSequenceAllignment() {
		Genome g = new Genome(new String[] {"gen1"}, new int[] {8});
		//stopcodons are always assumed and removed.
		int score = g.sequenceAlligner("THISLINE#", "ISALIGNED#");
		assertEquals(7, score);
	}
	
	public void testSequenceAllignment2() {
		Genome g = new Genome(new String[] {"gen1"}, new int[] {8});
		//stopcodons are always assumed and removed.
		int score = g.sequenceAlligner("MGLLCSRSRHHTED#", "MGLLCSRSRHHTED#");
		assertEquals(78, score);
	}
	
	public void testSequenceAllignment3() {
		Genome g = new Genome(new String[] {"gen1"}, new int[] {8});
		//stopcodons are always assumed and removed.
		int score = g.sequenceAlligner("MGLLCSSSSSSSRSRHHTED#","MGLLCSRSRHHTED#");
		assertEquals(54, score);
	}
	
	public void testDnaToAA() {
		Genome g = new Genome(new String[] {"gen1"}, new int[] {8});
		String aaSeq = g.DnaToAa("AAATTTGGCGGGGCCTAATT");
		assertEquals("KFGGA#", aaSeq);
	}
	
	public void testGetORFs() {
		Genome g = new Genome(new String[] {"gen1","gen2","gen3"}, new int[] {8,8,8});
		String[] orfs = g.getORFs();
		System.out.println(Arrays.toString(orfs));
		assertEquals(3, orfs.length);
	}


}
