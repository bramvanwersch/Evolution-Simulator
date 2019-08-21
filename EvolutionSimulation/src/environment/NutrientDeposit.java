package environment;

public class NutrientDeposit {
	private int WINDOW_SIZE = 950;
	private int radius;
	private String type;
	private int x;
	private int y;
	private double value;
	
	public NutrientDeposit(String type, int maxValue) {
		this.type = type;
		this.value = Math.random() * (maxValue- 0.75* maxValue) + 0.75* maxValue;
		this.radius = (int) (Math.random() * (75 - 50) + 50);
		x = (int) (Math.random() * (WINDOW_SIZE - radius));
		y = (int) (Math.random() * (WINDOW_SIZE - radius));
	}

	public int getXPos() {
		return this.x;
	}

	public int getYPos() {
		return this.y;
	}
	
	public int getSize() {
		return this.radius;
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
	
}
