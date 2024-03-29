package species;

import genome.Genome;

public class Carnivore extends HetrotrophSpecies{
	public double digestion_efficiency = 0.75;
	private final int FOOD_DIGEST_TIME = 1000;
	boolean eating = false;
	private int timeSinceEating;
	private int prevSpeed;

	/**
	 * Constructor for cloning carnivores.
	 * @param size the maximum size of the hetrotrophspecies
	 * @param speed the speed of the hetrotrophspecies
	 * @param maxAge the maximum age of hetrotrophspecies
	 * @param scentRange the range at witch the hetrotrophspecies can sense other species
	 * @param eatSizeFactor double that tells how mutch smaller the species can be and still eat
	 * another species.
	 * */
	public Carnivore(int size, int speed, int maxAge, int scentRange, double eatSizeFactor) {
		super(size, speed, maxAge, scentRange, eatSizeFactor);
		this.timeSinceEating = 0;
	}
	
	/**
	 * Constructor for multyplying carnivores
	 * @param x the coordinate in pixels of the x location of the hetrotrophspecies
	 * @param y the coordinate in pixels of the y location of the hetrotrophspecies
	 * @param energy the amount of energy for the hetrotrophspecies to start with
	 * @param genome the genome of the hetrotrophspecies
	 * @param number representing the order of creation of the species
	 * @param eatSizeFactor double that tells how mutch smaller the species can be and still eat
	 * another species.
	 */
	public Carnivore(int x, int y,int energy, Genome genome, int number, double eatSizeFactor) {
		super(x, y, energy, genome, number, eatSizeFactor);
		this.timeSinceEating = 0;
	}
	
	/**
	 * Get the efficiency that energy in food is converted to energy for a species.
	 * @return double of the efficiency as a fraction
	 */
	protected double getDigestionEfficiency() {
		return digestion_efficiency;
	}
	
	
	/**
	 * For evoking methods specific for carnivores only that need to be checked every time point.
	 */
	public void extendedNextTimePoint() {
		eatTimeCheck();
	}

	/**
	 * Function that will check if a eatable species is completely in the bounding box of the carnivore.
	 * If this is the case true is returned and the energy is added to the energy of the carnivore. This means
	 * that the species got eaten and will be removed from the game. The eating boolean will be set to true making
	 * carnivores slow so they cant effectively eat.
	 * @param x the x coordinate of the eatable species
	 * @param y the y coordinate of the eatable species
	 * @param size the sSize the size of the eatable species
	 * @param sEnergy the energy of the eatable species
	 * @return boolean that tells if the species is eaten or not.
	 */
	@Override
	public boolean eat(int x, int y, int sSize, int sEnergy) {
		if (getSize() * getEatSizeFactor() >= sSize) {
			if (getxLoc() - 0.5 * getSize() * getEatSizeFactor() <= x - 0.5 * sSize && 
					getxLoc() + Math.round(0.5 * getSize() * getEatSizeFactor()) >= x + Math.round(0.5 * sSize) && 
					getyLoc() - Math.round(0.5 * getSize() * getEatSizeFactor()) <= y - Math.round(0.5 * sSize) && 
					getyLoc() + Math.round(0.5 * getSize() * getEatSizeFactor()) >= y + Math.round(0.5 * sSize)) {
				changeEnergy(sEnergy);
				this.eating = true;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Function that will make the carnivore move towards a herbivore if this one is in the scentrange of the
	 * carnivore. If the species has recently eaten the normal move behaviour will be invoked.
	 * @param ix integer representing the x coordinate of the herbivore
	 * @param iy integer representing the y coordinate of the herbivore
	 * TODO: Unused at the moment and the chase mechanic feels broken.
	 */
	public void scentMovement(int ix, int iy) {
		double y  = (double) iy;
		double x = (double) ix;
		if (getEnergy() > 0 && !this.eating) {
			double slopeLength = Math.sqrt(Math.pow(x - getxLoc(), 2) + Math.pow(y - getyLoc(), 2));
			changeXLoc((x - getxLoc())/ slopeLength *getSpeed());
			changeYLoc((y - getyLoc())/ slopeLength * getSpeed());
			double fd = Math.atan2((y - getyLoc())/ slopeLength, (x - getxLoc())/ slopeLength);
			double min = (fd - 0.25 * Math.PI);
			double max = (fd + 0.25 * Math.PI);
			setFacingDirection((Math.random() * (max - min)) + min);
			changeEnergy(-1*getEnergyConsumption());
		}
		else {
			move();
		}
	}

	/**
	 * Function for checking if the carnivore has recently eaten. Setting the speed lower after eating and 
	 * returning it back if enough time has passed.
	 */
	private void eatTimeCheck() {
		if (this.eating) {
			if (this.timeSinceEating == 0) {
				this.prevSpeed = getSpeed();
				setSpeed(3);
			}
			this.timeSinceEating += 50;
			if (this.timeSinceEating >= this.FOOD_DIGEST_TIME) {
				this.eating = false;
				this.timeSinceEating = 0;
				setSpeed(prevSpeed);
			}
		}
	}
	
	/**
	 * Returns the eating boolean that tells if a species has recently eaten.
	 */
	public boolean getEating() {
		return this.eating;
	}
}
