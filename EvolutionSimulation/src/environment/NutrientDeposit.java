package environment;

public class NutrientDeposit {
	private int WINDOW_SIZE = 950;
	private double diameter;
	private String type;
	private int x;
	private int y;
	private double value;
	
	public NutrientDeposit(String type, int maxValue) {
		this.type = type;
		this.value = Math.random() * (maxValue- 0.75* maxValue) + 0.75* maxValue;
		diameter = Math.random() * (150 - 100) + 100;
		x = (int) (Math.random() * (WINDOW_SIZE - diameter));
		y = (int) (Math.random() * (WINDOW_SIZE - diameter));
	}

	public double getValue() {
		return this.value;
	}

	public int getXPos() {
		return this.x;
	}

	public int getYPos() {
		return this.y;
	}
	
	public int getSize() {
		return (int) diameter;
	}
	
}
