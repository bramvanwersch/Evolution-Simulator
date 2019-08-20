package environment;

public class NutrientDeposit {
	private int WINDOW_SIZE = 950;
	private double Nitrogen;
	private double Phosporus;
	private double Potassium;
	private int x;
	private int y;
	
	public NutrientDeposit(double nitrogen, double phosporus, double potassium) {
		Nitrogen = nitrogen;
		Phosporus = phosporus;
		Potassium = potassium;
		x = (int) (Math.random() * (WINDOW_SIZE - 50) + 50);
		y = (int) (Math.random() * (WINDOW_SIZE - 50) + 50);
	}

	public double getNitrogen() {
		return Nitrogen;
	}

	public double getPhosporus() {
		return Phosporus;
	}

	public double getPotassium() {
		return Potassium;
	}

	public int getXPos() {
		return x;
	}

	public int getYPos() {
		return y;
	}
	
}
