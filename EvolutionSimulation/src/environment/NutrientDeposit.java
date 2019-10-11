package environment;

public class NutrientDeposit {
	private int WINDOW_SIZE = 950;
	private int x;
	private int y;
	private double totalAvailableNutrient;
	
	public NutrientDeposit(int maxValue) {
		//randoms between 75% and 100% of the max value
		this.totalAvailableNutrient = Math.random() * (maxValue- 0.75* maxValue) + 0.75* maxValue;
		x = (int) (Math.random() * (WINDOW_SIZE - 2 * getRadius()));
		y = (int) (Math.random() * (WINDOW_SIZE - 2 * getRadius()));
	}

	public int getXPos() {
		return this.x;
	}

	public int getYPos() {
		return this.y;
	}
	
	public int getRadius() {
//		System.out.println((int) (totalAvailableNutrient / 10));
		return (int) (totalAvailableNutrient / 100);
	}
	
	public double getTotalAvailableNutrient() {
		return this.totalAvailableNutrient;
	}
	
	public void addNutrient(double val) {
		this.totalAvailableNutrient += val;
	}

	public boolean checkArea(int xCoord, int yCoord) {
		//absolute value of the c of the pythagoras equation.
		if (Math.abs(Math.sqrt(Math.pow(this.x- xCoord, 2) + Math.pow(this.y - yCoord, 2))) 
				< getRadius()){
			return true;
		}
		return false;
	}
	
	/**
	 * Calculates the amount of nutrients available from this deposit depending
	 * on the distance of the autotrophSpecies from the deposit. An x and y 
	 * coordinate tell where the autotrophSpecies is.
	 * @param xCoord of the autotrophSpecies.
	 * @param yCoord of the autotrophSpecies.
	 * @return a double that tells how much nutrients are available in a certain spot.
	 */
	public double getValueAtDistance(int xCoord, int yCoord) {
		//absolute value of the c of the pythagoras equation.
		double radialDistanceFromCentre = Math.abs(Math.sqrt(Math.pow(this.x- xCoord, 2) + Math.pow(this.y - yCoord, 2)));
		if (radialDistanceFromCentre < getRadius()){
			double inverseFractionDistance = 1 - radialDistanceFromCentre / getRadius();
			return inverseFractionDistance * totalAvailableNutrient;
		}
		return 0.0;
	}
	
	public double removeAmount(double amount) {
		if (this.totalAvailableNutrient == 0) return amount;
		if (this.totalAvailableNutrient - amount < 0) {
			double left =  amount  - this.totalAvailableNutrient;
			this.totalAvailableNutrient = 0;
			return left;
		}
		this.totalAvailableNutrient -= amount;
		return 0.0;
	}
	
}
