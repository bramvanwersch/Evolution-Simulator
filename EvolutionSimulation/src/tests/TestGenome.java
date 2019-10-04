package tests;

import java.util.Arrays;

import genome.DNAtoAA;
import genome.Genome;
import junit.framework.TestCase;

public class TestGenome extends TestCase {
	
	public void testSequenceAllignment() {
		Genome g = new Genome(new String[] {"gen1"}, new int[] {8});
		//stopcodons are always assumed and removed.
		int score = g.sequenceAlligner("THISLINE#", "ISALIGNED#");
		assertEquals(16, score);
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
		assertEquals(3, orfs.length);
	}
	
	public void testCreatePerfectGenes() {
		Genome g = new Genome(new String[] {"gen1", "gen2"}, new int[] {10, 5});
		assertEquals(40, g.getPerfectGenes().get("gen1").getValue());
		assertEquals(20, g.getPerfectGenes().get("gen2").getValue());
	}
	
	public void testCreateStarterGenes() {
		Genome g = new Genome(new String[] {"gen1"}, new int[] {10});
		String ORF=  g.createStarterGenes()[0];
		String perfectSequence = g.getPerfectGenes().get("gen1").getSequence();
		double allignmentScore = g.sequenceAlligner(g.DnaToAa(perfectSequence), g.DnaToAa(ORF));
		double perfectScore = g.getPerfectGenes().get("gen1").getMaxScore();
		assertEquals(10, (int) ((allignmentScore/perfectScore) * g.getPerfectGenes().get("gen1").getValue()));
	}
	
	public void testSetGeneValues() {
		//run multiple times to test for consistency because of randoms.
		for (int i = 0; i < 10; i ++) {
			Genome g = new Genome(new String[] {"gen1"}, new int[] {10});
			g.setGeneValues();
			assertEquals(10, g.getAttributeValue("gen1"));
		}
	}
	
	public void testGetGeneNameScore() {
		//run multiple times to test for consistency because of randoms.
		//look into the finding of ORFs it might cut of a codon or two that influence the score
		for (int i = 0; i < 10; i ++) {
			Genome g = new Genome(new String[] {"gen1"}, new int[] {10});
			String ORF=  g.createStarterGenes()[0];
			String[] nameScore = g.getGeneNameScore(ORF);
			double perfectScore = g.getPerfectGenes().get("gen1").getMaxScore();
			assertEquals(10, (int) ((Double.parseDouble(nameScore[1])/perfectScore) * g.getPerfectGenes().get("gen1").getValue()));
		}
	}


}
