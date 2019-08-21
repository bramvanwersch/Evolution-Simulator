package environment;

public class Environment {
	private NutrientDeposit[] nitrogenDeposits;
	private NutrientDeposit[] phosporusDeposits;
	private NutrientDeposit[] potassiumDeposits;
	
	public Environment(int[] nitrogen, int[] phosporus, int[] potassium) {
		nitrogenDeposits = createDeposits("nitrogen", nitrogen);
		phosporusDeposits = createDeposits("phosporus", phosporus);
		potassiumDeposits = createDeposits("potassium",potassium);
	}

	private NutrientDeposit[] createDeposits(String type, int[] element) {
		NutrientDeposit[] deposits = new NutrientDeposit[element[0]];
		for (int i = 0; i < element[0]; i++) {
			deposits[i] = new NutrientDeposit(type, element[1]);
		}
		return deposits;
	}

	public NutrientDeposit[] getNitrogenDeposits() {
		return nitrogenDeposits;
	}

	public NutrientDeposit[] getPhosporusDeposits() {
		return phosporusDeposits;
	}

	public NutrientDeposit[] getPotassiumDeposits() {
		return potassiumDeposits;
	}
	
	

}
