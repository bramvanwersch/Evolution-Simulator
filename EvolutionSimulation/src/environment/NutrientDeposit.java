package environment;

public class NutrientDeposit {
	private int WINDOW_SIZE = 950;
	private int radius;
	private int x;
	private int y;
	private double value;
	
	public NutrientDeposit(int maxValue) {
		this.value = Math.random() * (maxValue- 0.75* maxValue) + 0.75* maxValue;
		this.radius = (int) (Math.random() * (75 - 50) + 50);
		x = (int) (Math.random() * (WINDOW_SIZE - 2 * radius));
		y = (int) (Math.random() * (WINDOW_SIZE - 2 * radius));
	}

	public int getXPos() {
		return this.x;
	}

	public int getYPos() {
		return this.y;
	}
	
	public int getSize() {
		return 2* this.radius;
	}

	public boolean checkArea(int xCoord, int yCoord) {
		//absolute value of the c of the pythagoras equation.
		if (Math.abs(Math.sqrt(Math.pow(this.x- xCoord, 2) + Math.pow(this.y - yCoord, 2))) 
				< radius){
			return true;
		}
		return false;
	}
	
	public double getValueAtDistance(int xCoord, int yCoord) {
		//absolute value of the c of the pythagoras equation.
		double radialDistanceFromCentre = Math.abs(Math.sqrt(Math.pow(this.x- xCoord, 2) + Math.pow(this.y - yCoord, 2)));
		if (radialDistanceFromCentre < radius){
			double inverseFractionDistance = 1 - radialDistanceFromCentre/radius;
			return inverseFractionDistance * value;
		}
		return 0.0;
	}
	
	public double removeAmount(double amount) {
		if (this.value == 0) return amount;
		if (this.value - amount < 0) {
			double left =  amount  - this.value;
			this.value = 0;
			return left;
		}
		this.value -= amount;
		return 0.0;
	}
	
}
