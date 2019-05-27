package genome;

public class Gene {
	private String name;
	private int value;
	private String sequence;
	private final int avgAminoAcids = 30;
	private int maxScore;

	public Gene(String name, int value) {
		this.name = name;
		//the perfect gene sequence is twice as effective as the startign value.
		this.value = value *2;
		this.sequence = createGeneSequence();
	}

	private String createGeneSequence() {
		// start with a start codon
		String seq = "ATG";
		double min =  1 * avgAminoAcids;
		double max =  2 * avgAminoAcids;
		int amntAminoAcids = (int) ((Math.random() * (max - min)) + min);
		String [] nucleotides = {"A","T","C","G"};
		for (int i = 0; i < amntAminoAcids; i++) {
			String codon = nucleotides[(int) (Math.random() * 4)] + nucleotides[(int) (Math.random() * 4)] + nucleotides[(int) (Math.random() * 4)];
			if (codon.equals("TAA") || codon.equals("TGA") || codon.equals("TAG")) {
				i--;
				continue;
			}
			seq += codon;
		}
		// end with stop codon. Chose one of 3.
		seq += "TAG";
		return seq;
	}
	
	public String getSequence() {
		return this.sequence;
	}

	public void setMaxScore(int score) {
		this.maxScore = score;
	}
	
	public int getMaxScore() {
		return this.maxScore;
	}
	
	public String getName() {
		return this.name;
	}

}
