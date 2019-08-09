package simulation;

import genome.Genome;

public class Carnivore extends Species{
	private String[] geneNames = {"size","speed","maxAge","scentRange"};
	private final int FOOD_DIGEST_TIME = 1000;
	boolean eating = false;
	private int timeSinceEating;

	//innitial constructor
	public Carnivore(int size, int speed, int maxAge, int scentRange, String name, double eatSizeFactor) {
		super(size, speed, maxAge, scentRange, name, eatSizeFactor);
		this.timeSinceEating = 0;
	}
	
	//inheriting constructor
	public Carnivore(int x, int y,int energy, Genome genome, int number, String name, double eatSizeFactor) {
		super(x, y, energy, genome, number, name, eatSizeFactor);
		this.timeSinceEating = 0;
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
	 * @return boolean that tells if the species can be eaten or not.
	 */
	public boolean checkCanEat(int x, int y, int sSize, int sEnergy) {
		if (getxLoc() - 0.5 * getSize() < x && getxLoc() + 0.5 * getSize() - 0.5 * sSize > x 
				&& getyLoc() - 0.5 * getSize()  < y && getyLoc() + 0.5 * getSize() - 0.5 * sSize > y) {
			changeEnergy(sEnergy);
			this.eating = true;
			return true;
		}
		return false;
	}
	
	/**
	 * Function that will make the carnivore move towards a herbivore if this one is in the scentrange of the
	 * carnivore. If the species has recently eaten the normal move behaviour will be invoked.
	 * @param ix integer representing the x coordinate of the herbivore
	 * @param iy integer representing the y coordinate of the herbivore
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
	 * Function for moving the carnivore. This function overwrites the species function to allow for a check to
	 * see if the carnivore has eaten recently. If this is the case the speed of the carnivore will temporarily
	 * drop to a lower value.
	 */
	public void move() {
		eatTimeCheck();
		if (getEnergy() > 0) {
			double min = (getFacingDirection() - 0.25 * Math.PI);
			double max = (getFacingDirection() + 0.25 * Math.PI);
			setFacingDirection((Math.random() * (max - min)) + min);
			changeXLoc(Math.sin(getFacingDirection()) * getSpeed());
			changeYLoc((-1 * Math.cos(getFacingDirection()) * getSpeed()));
			changeEnergy(-1*getEnergyConsumption());			
		}
	}
	
	/**
	 * Function for checking if the carnivore has recently eaten. Setting the speed lower after eating and 
	 * returning it back if enough time has passed.
	 */
	public void eatTimeCheck() {
		if (this.eating) {
			if (this.timeSinceEating == 0) {
				setSpeed(3);
			}
			this.timeSinceEating += 50;
			if (this.timeSinceEating >= this.FOOD_DIGEST_TIME) {
				this.eating = false;
				this.timeSinceEating = 0;
				setSpeed(getGenome().getGeneValue("speed"));
			}
		}
	}
	
	/**
	 * This has to be implemented yet
	 * @return
	 */
	public String[] getGeneNames() {
		return this.geneNames;
	}
}
