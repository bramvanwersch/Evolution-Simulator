package simulation;

import genome.Genome;

public class Herbivore extends Species{
	private String[] geneNames = {"size","speed","maxAge","scentRange"};//needs work
	
	//innitial constructor
	public Herbivore(int size, int speed, int maxAge) {
		super(size, speed, maxAge);
	}
	
	//inheriting constructor
	public Herbivore(int x, int y, int energy, Genome genome) {
		super(x, y,energy, genome);
	}
	
	public double getEnergyConsumption() {
		int r = getSize() / 2;
		double contentSurface = (1.33* Math.PI * Math.pow(r, 3)) /(4 * Math.PI * Math.pow(r, 2));
		return (Math.pow(1.3, contentSurface) - 1)* 0.3* getSpeed() + 0.25 * getScentRange() + getAge();
	}

	
	public boolean foodEaten(int x, int y, int fSize, int fEnergy) {
		if (getxLoc() - 0.5 * getSize() < x && getxLoc() + 0.5 * getSize() - 0.5 * fSize > x
				&& getyLoc() - 0.5 * getSize()  < y && getyLoc() + 0.5 * getSize() - 0.5 * fSize > y) {
			changeEnergy(fEnergy);
			return true;
		}
		return false;
	}
	
	public void moveAway(int ix, int iy) {
		double y  = (double) iy;
		double x = (double) ix;
		if (getEnergy() > 0) {
			double slopeLength = Math.sqrt(Math.pow(x - getxLoc(), 2) + Math.pow(y - getyLoc(), 2));
			//direction that is straigh away from the target
			double fd = Math.atan2((y - getyLoc())/ slopeLength, (x - getxLoc())/ slopeLength);
			double min = (fd - 0.1 * Math.PI);
			double max = (fd + 0.1 * Math.PI);
			setFacingDirection((Math.random() * (max - min)) + min);
			changeXLoc(Math.sin(getFacingDirection()) * getSpeed());
			changeYLoc((-1 * Math.cos(getFacingDirection()) * getSpeed()));
			changeEnergy(-1*getEnergyConsumption());
		}	
	}
	
	public String[] getGeneNames() {
		return this.geneNames;
	}
}
