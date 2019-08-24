package simulation;

import genome.Genome;

public class Herbivore extends AnimalSpecies{
	private String[] geneNames = {"size","speed","maxAge","scentRange"};//needs work
	private final int MINIMUM_REP_TIME = 0;
	
	//innitial constructor
	public Herbivore(int size, int speed, int maxAge, int scentRange, double eatSizeFactor) {
		super(size, speed, maxAge, scentRange, eatSizeFactor);
	}
	
	//inheriting constructor
	public Herbivore(int x, int y, int energy, Genome genome, int number, double eatSizeFactor) {
		super(x, y,energy, genome, number, eatSizeFactor);
	}
	
	public boolean foodEaten(int x, int y, int fSize, int fEnergy) {
		if (getxLoc() - 0.5 * getSize() < x && getxLoc() + 0.5 * getSize() - 0.5 * fSize > x
				&& getyLoc() - 0.5 * getSize()  < y && getyLoc() + 0.5 * getSize() - 0.5 * fSize > y) {
			changeEnergy(fEnergy);
			return true;
		}
		return false;
	}
	
	public void scentMovement(int ix, int iy) {
		double y  = (double) iy;
		double x = (double) ix;
		if (getEnergy() > 0) {
			double slopeLength = Math.sqrt(Math.pow(x - getxLoc(), 2) + Math.pow(y - getyLoc(), 2));
			//direction that is straigh away from the target
			double fd = Math.atan2((y - getyLoc())/ slopeLength, (x - getxLoc())/slopeLength);
//			double min = (fd - 0.25 * Math.PI);
//			double max = (fd + 0.25 * Math.PI);
			setFacingDirection(fd);//(Math.random() * (max - min)) + min);
			changeXLoc(Math.sin(getFacingDirection()) * getSpeed());
			changeYLoc((-1 * Math.cos(getFacingDirection()) * getSpeed()));
			changeEnergy(-1*getEnergyConsumption());
		}	
	}
	
	public String[] getGeneNames() {
		return this.geneNames;
	}
	
	public int getRepTime() { 
		return this.MINIMUM_REP_TIME;
	}
	
	public void eatTimeCheck() {
	}
}
