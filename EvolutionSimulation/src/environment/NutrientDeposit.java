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
		x = (int) (Math.random() * WINDOW_SIZE);
		y = (int) (Math.random() * WINDOW_SIZE);
	}
}
