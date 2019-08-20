package environment;

public class Environment {
	private NutrientDeposit[] deposits;
	
	public Environment(int nrDeposits, int maxNitrogen, int maxPhosporus, int maxPotassium) {
		deposits = new NutrientDeposit[nrDeposits];
		createDeposits(nrDeposits, maxNitrogen, maxPhosporus, maxPotassium);
	}

	private void createDeposits(int nrDeposits, int maxNitrogen, int maxPhosporus, int maxPotassium) {
		for (int i = 0; i < nrDeposits; i++) {
			double NiLevel = Math.random() * (maxNitrogen - 0.75* maxNitrogen) + 0.75* maxNitrogen;
			double PhLevel = Math.random() * (maxPhosporus - 0.75* maxPhosporus) + 0.75* maxPhosporus;
			double PoLevel = Math.random() * (maxPotassium - 0.75* maxPotassium) + 0.75* maxPotassium;
			deposits[i] = new NutrientDeposit(NiLevel, PhLevel, PoLevel);
		}
	}
	

	
	public NutrientDeposit[] getDeposits() {
		return deposits;
	}
	

}
