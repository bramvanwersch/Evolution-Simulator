package species;

import genome.Genome;

public class Herbivore extends HetrotrophSpecies{
	public double digestion_efficiency = 0.5;

	/**
	 * Constructor for cloning herbivores
	 * @param size the maximum size of the hetrotrophspecies
	 * @param speed the speed of the hetrotrophspecies
	 * @param maxAge the maximum age of hetrotrophspecies
	 * @param scentRange the range at witch the hetrotrophspecies can sense other species
	 * @param eatSizeFactor double that tells how mutch smaller the species can be and still eat
	 * another species.
	 */	
	public Herbivore(int size, int speed, int maxAge, int scentRange, double eatSizeFactor) {
		super(size, speed, maxAge, scentRange, eatSizeFactor);
	}
	 
		/**
		 * Constructor for multyplying herbivores
		 * @param x the coordinate in pixels of the x location of the hetrotrophspecies
		 * @param y the coordinate in pixels of the y location of the hetrotrophspecies
		 * @param energy the amount of energy for the hetrotrophspecies to start with
		 * @param genome the genome of the hetrotrophspecies
		 * @param number representing the order of creation of the species
		 * @param eatSizeFactor double that tells how mutch smaller the species can be and still eat
		 * another species.
		 */	
	public Herbivore(int x, int y, int energy, Genome genome, int number, double eatSizeFactor) {
		super(x, y,energy, genome, number, eatSizeFactor);
	}
	
	/**
	 * Get the efficiency that energy in food is converted to energy for a species.
	 * @return double of the efficiency as a fraction
	 */
	protected double getDigestionEfficiency() {
		return digestion_efficiency;
	}
	
	
	/**
	 * Needs proper implementation
	 * TODO: needs fixing of the chase mechanic
	 * @param ix
	 * @param iy
	 */
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
	
	/**
	 * for inheriting the abstract method
	 */
	public void extendedNextTimePoint() {
	}
}
