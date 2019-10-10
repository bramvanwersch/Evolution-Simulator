package genome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Genome implements SubstitutionMatrix {
	private Map<String, Gene> perfectGenes;
	private Map<String, Integer> geneValues;
	private Map<String, String> dnaCon;
	private String DNACode = "";
	private final int gapP = -4;
	private final int endGapP = -1;
	private final String[] nucleotides = {"A", "T", "G", "C"};
	private double STARTING_CODON_COUNT = 300;
	
	//initial constructor
	public Genome(String[] geneNames, int[] startingValues) {
		perfectGenes = new HashMap<String, Gene>();
		geneValues = new HashMap<String, Integer>();
		dnaCon = new DNAtoAA().getDNAConversionMap();
		createPerfectGenes(geneNames,startingValues);
		createDNACode();
	}
	
	//inheriting constructor
	public Genome(Map<String,Gene> perfGenes, String DNAc) {
		this.perfectGenes = perfGenes;
		this.DNACode = DNAc;
		geneValues = new HashMap<String, Integer>();
		dnaCon = new DNAtoAA().getDNAConversionMap();
	}

	public boolean isSpeciesSurvivable() {
		for (String pKey: perfectGenes.keySet()) {
			if (!geneValues.containsKey(pKey)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Sets the gene values when called by finding all ORFs and scanning what perfect gene they have the best score
	 * for that is above 0. Then accumulating the value for one attribute and saving that for that attribute.
	 */
	public void setGeneValues() {
		String[] ORFs = getORFs();
		String[] nameScore;
		for (String ORF : ORFs) {
			nameScore = getGeneNameScore(ORF);
			String name = nameScore[0];
			double score = Double.parseDouble(nameScore[1]);
			if (name != null) {
				int value = (int) ((score / (double) (perfectGenes.get(name).getMaxScore())) *
							perfectGenes.get(name).getValue());
				if (geneValues.containsKey(name) && value >= 0) {
					value += geneValues.get(name);
				}
				geneValues.put(name, (int) value);
			}
		}
	}
	
	/**
	 * Function to get open reading frames based on finding a start codon and then searching for the next stop codon.
	 * @return: an array containing the ORFs for all genes found in the first reading frame.
	 */
	public String[] getORFs() {
		String gene = "";
		ArrayList<String> ORFs = new ArrayList<String>();
		boolean inGene = false;
		for (int i = 0; i < DNACode.length(); i+=3) {
			String codon = DNACode.substring(i, i +3);
			if (inGene) {
				gene += codon;
				if (codon.equals("TAA") || codon.equals("TGA") || codon.equals("TAG")) {
					ORFs.add(gene);
					gene = "";
					inGene = false;
				}
			}
			else if (codon.equals("ATG")) {
				gene += codon;
				inGene = true;
			}
		}
		return ORFs.toArray(new String [ORFs.size()]);
	}
	
	/**
	 * Expensive function that allignes a dnaCode with each perfect gene to determine the score
	 * between them and to determine the highest scoring match above 0 to asses the amount the gene 
	 * influences the attribute.
	 * @param dnaSeq of the ORF that was found
	 * @return the name of the attribute and the score of that attribute. If no allignment was found that was
	 * good enough returns {null,0}
	 */
	public String[] getGeneNameScore(String dnaSeq) {
		String[] geneNameScore = new String[] {null, "0"};
		int highestScore = 0;
		String aaSeq = DnaToAa(dnaSeq);
		for (String key : perfectGenes.keySet()) {
			Gene perfectGene = perfectGenes.get(key);
			int score =  sequenceAlligner(aaSeq, DnaToAa(perfectGene.getSequence()));
			if (score > highestScore) {
				geneNameScore[0] = perfectGene.getName();
				geneNameScore[1] = score + "";
				highestScore = score;
			}
		}
		return geneNameScore;
	}

	/**
	 * Function that alligns 2 amino acid sequences using a BLOSSUM62 matrix and returns a score. It removes the stop codons
	 * because the matrix has no value for them. This is an expensive function when run multiple times in succesion.
	 * @param seq1; first amino acid sequence
	 * @param seq2; second amino acid sequence
	 * @return; a score for the allignment. The maximum score is obtained by alligning an amino acid sequence with itself.
	 */
	public int sequenceAlligner(String seq1, String seq2) {
		//stopcodons are always assumed and removed.
		seq1 = "0" + seq1.substring(0, seq1.length()-1);
		seq2 = "0" + seq2.substring(0, seq2.length()-1);
		int [][] valueMatrix = new int[seq1.length()][seq2.length()];
		for (int y = 0; y < seq1.length(); y++) {
			for (int x = 0; x < seq2.length(); x++) {
				if (x == 0 && y == 0) {
					valueMatrix[y][x] = 0;
				}
				else if (y -1 < 0) {
					valueMatrix[y][x] = valueMatrix[y][x-1] + endGapP;
				}
				else if (x - 1 < 0) {
					valueMatrix[y][x] = valueMatrix[y-1][x] + endGapP;
				}
				else {
					int dScore = SubstitutionMatrix.getScore(String.valueOf(seq2.charAt(x)), String.valueOf(seq1.charAt(y)));
					int diaValue = valueMatrix[y-1][x-1] + dScore;
					int rightValue = valueMatrix[y][x-1] + gapP;
					if (seq1.length()-1 == y) {
						rightValue = valueMatrix[y][x-1] + endGapP;
					}
					int downValue = valueMatrix[y-1][x] + gapP;
					if(seq2.length()-1 == x) {
						downValue = valueMatrix[y-1][x] + endGapP;
					}
					valueMatrix[y][x] = Math.max(diaValue, Math.max(downValue, rightValue));
				}
			}
		}
		return valueMatrix[seq1.length()-1][seq2.length()-1];
	}
	
	/**
	 * Function that converts a given dna string into a corresponding amino acid sequence. If an amount of nucleotides is given
	 * that is not divisible by 3 the extra 1 or 2 nucleotides are ignored.
	 * @param dna; the dna string to be converted
	 * @return; a string representing the amino acid sequence of a given dna strand.
	 */
	public String DnaToAa(String dna) {
		String aaStrand = "";
		for (int i = 0; i < dna.length()-2; i += 3) {
			aaStrand += dnaCon.get(dna.substring(i, i + 3));
		}
		return aaStrand;
	}
	
	/**
	 * Function that mutates a certain piece of DNA based on a mutation rate. 
	 * @param genSeq; the sequence that has to be mutated
	 * @param mutateChance; the chance for a mutation to occur. This is a nucleotide mutation so it does not neccesairily alter the protein.
	 * Also the nucleotide can mutate back into the same nucleotide.
	 * @param isGene: set this to true if you want to mutate a gene sequence and make sure that the start and stop codons are not affected by
	 * the mutation.
	 * @return: the mutated code.
	 */
	public String mutate(String genSeq, double mutateChance, boolean isGene) {
		String newSeq = "";
		if (isGene) {
			genSeq = genSeq.substring(3, genSeq.length()-3);
			newSeq += "ATG";
		}
		for (char nucleotide : genSeq.toCharArray()) {
			double chance = Math.random();
			if (chance <= mutateChance) {
				newSeq += nucleotides[(int) (Math.random() *4)];
			}
			else {
				newSeq += String.valueOf(nucleotide);
			}
		}
		if (isGene) {
			newSeq = removeStopCodons(newSeq);
			newSeq += "TAG";
		}
//		System.out.println(dnaToAA("ATG" + genSeq + "TAG")+ "  "+ dnaToAA(newSeq));
		return newSeq;
	}
	
	/**
	 * Returns the score for a certain attribute as saved by the genome
	 * @param attributeName is the name of the attribute for which the value was requested
	 * @return value of the attribute
	 */
	public int getAttributeValue(String attributeName) {
		return geneValues.get(attributeName);
	}
	
	/**
	 * Allows to change a value in the genome 
	 * TODO: is a bad type of function should not be allowed and a different strategy has to be employed
	 * @param attribute
	 * @param val
	 */
	public void setGeneValue(String attribute, int val) {
		geneValues.put(attribute, val);
	}
	
	/**
	 * Returns the map of perfect genes. This is here for inheriting the genome class to make sure that
	 * these genes do not have toe be recalculated.
	 * @return map containing Gene instances of perfect genes.
	 */
	public Map<String,Gene> getPerfectGenes(){
		return this.perfectGenes;
	}
	
	/**
	 * Returns the DNA code of the genome for inheriting the genome.
	 * @return A long string of nucleotides
	 */
	public String getDNACode() {
		return this.DNACode;
	}
	
	/**
	 * Mutates the current genome of the species by a certain chance. This function is manly here to protect
	 * the mutate function and to make the proces more straight forward
	 * @param mutateChance a fraction that tells how likely a mutation is to occur. A fraction of 0.1 means that 
	 * 10% of the bases will be mutated.
	 */
	public void mutateGenome(double mutateChance) {
		DNACode = mutate(DNACode, mutateChance, false);
	}
	
// functions for creating the innital DNA code and genes. Will not be used after innitial creation of species.
	
	/**
	 * Function for creating so called perfect genes. These are genes that will tell what the best amino acid sequence is for a certain gene
	 * and also to tell if what gene a newly created gene is. It also checks if newly created 
	 * @param geneNames; the names of the genes that an organism can have
	 * @param startingValues; the starting values that the genes of the organism will approximately have. This value *2 is used to determine
	 * with what the effect of a gene is when being exactly the same as a perfect gene.
	 */
	private void createPerfectGenes(String[] geneNames, int[] startingValues) {
		boolean hasHighSimilar = false;
		for (int i = 0; i < startingValues.length; i++) {
			Gene g =  new Gene(geneNames[i], startingValues[i]);
			String aaSeq = DnaToAa(g.getSequence());
//			System.out.println(aaSeq);
			g.setMaxScore(sequenceAlligner(aaSeq, aaSeq));
			// check if gene is not to similar to other genes
			for (String key : perfectGenes.keySet()) {
				String perfAaSeq = DnaToAa(perfectGenes.get(key).getSequence());
				//meaning to similar
				if (sequenceAlligner(aaSeq, perfAaSeq) > 0) {
					hasHighSimilar = true;
				}
			}
			if (hasHighSimilar) {
				i--;
				hasHighSimilar = false;
			}
			else {
				perfectGenes.put(geneNames[i], g);
			}
		}
	}	
	
//Functions for innitial DNA creation

	
	/**
	 * Creates a DNA code that is the specified amount of codons (STARTING_CODON_COUNT) long plus the generated starter genes.
	 * These are placed at random after a certain codon to assure that they are in the first reading frame.
	 */
	private void createDNACode() {
		String[] starterGenes = createStarterGenes();
		String [] nucleotides = {"A","T","C","G"};
		double[] geneLocations = calcGeneLocations();
		int count = 0;
		for (int i = 0; i < this.STARTING_CODON_COUNT; i++) {
			String codon = nucleotides[(int) (Math.random() * 4)] + nucleotides[(int) (Math.random() * 4)] + nucleotides[(int) (Math.random() * 4)];
			if (codon.equals("ATG") || codon.equals("TAG") || codon.equals("TAA") || codon.equals("TGA")){
				i--;
				continue;
			}
			if (count < geneLocations.length && i == (int) geneLocations[count]) {
				this.DNACode += starterGenes[count];
				count ++;
			}
			this.DNACode += codon;
		}
//		System.out.println(DNACode);	
	}
	
	/**
	 * Function for creating starting genes based on the perfectGenes generated at creation of the genome.
	 * Starter genes have an alignment score that is about 24 -26% of the max score of the corresponding 
	 * perfect genes.
	 * @return an array of starter genes.
	 */
	public String[] createStarterGenes() {
		String[] starterGenes = new String[perfectGenes.keySet().size()];
		int count = 0;
		for (String key : perfectGenes.keySet()) {
			Gene optimalGene = perfectGenes.get(key);
			String optimalGenSeq  = optimalGene.getSequence();
			String newGenSeq = mutate(optimalGenSeq, 0.5, true);
			int value  = optimalGene.getValue();
			double newGenScore = sequenceAlligner(DnaToAa(optimalGenSeq), DnaToAa(newGenSeq));
			double optimalGenScore = optimalGene.getMaxScore();
			//randomly generetate a gene that has the score that is equal to 1/4th of the max score
			while ((int) ((newGenScore / optimalGenScore) * value) != (int) (0.25 * value)) {
				optimalGenSeq  = optimalGene.getSequence();
				newGenSeq = mutate(optimalGenSeq, 0.5, true);
				newGenScore = sequenceAlligner(DnaToAa(optimalGenSeq), DnaToAa(newGenSeq));
			}
			starterGenes[count] = newGenSeq;
			count ++;
		}
		return starterGenes;
	}
	
	/**
	 * Function for randomly chosing a codon within the STARTING_CODON_COUNT amount for genes to be inserted into while setting up the innitial DNA.
	 * @return: return a sorted list from low to high that does not include any duplicates.
	 */
	private double[] calcGeneLocations() {
		double[] locList = new double[perfectGenes.keySet().size()];
		boolean inLocList = false;
		for(int i = 0; i < perfectGenes.keySet().size(); i++) {
			double d = Math.random() * this.STARTING_CODON_COUNT ;
			for (double num : locList) {
				if((int) d == (int) num){
					inLocList = true;
				}
			}
			if (inLocList) {
				i--;
				inLocList = false;
			}
			else {
				locList[i] = d;
			}
		}
		Arrays.sort(locList);
		return locList;
	}
	
	/**
	 * Function that removes any stop codons that where generated by mutating a sequence only used for innitial starter gene creation.
	 * @param newSeq; the dna sequence that contains stop codons.
	 * @return; a dna sequence that does not contain stop codons in the first reading frame.
	 */
	private String removeStopCodons(String newSeq) {
		String returnSeq = "";
		for (int i = 0; i < newSeq.length(); i+=3) {
			String codon = newSeq.substring(i, i +3);
			if (codon.equals("TAA") || codon.equals("TGA") || codon.equals("TAG")) {
				continue;
			}
			returnSeq += codon;
		}
		return returnSeq;
	}

}
