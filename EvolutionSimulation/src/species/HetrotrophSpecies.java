package species;
import java.math.BigDecimal;

import genome.Genome;

/**
 * Abstract class for containing methods of all hetrotrophspecies.
 * @author Bram van Wersch
 *
 */
public abstract class HetrotrophSpecies extends Species {
	private Genome genome;
	private double facingDirection;
	private double eatSizeFactor;

	/**
	 * Constructor for cloning hetrotrophspecies.
	 * @param size the maximum size of the hetrotrophspecies
	 * @param speed the speed of the hetrotrophspecies
	 * @param maxAge the maximum age of hetrotrophspecies
	 * @param scentRange the range at witch the hetrotrophspecies can sense other species
	 * @param eatSizeFactor double that tells how mutch smaller the species can be and still eat
	 * another species.
	 */
	public HetrotrophSpecies(int size, int speed, int maxAge, int scentRange, double eatSizeFactor) {
		super(20000);
		this.genome = new Genome(new String[] {"size","speed","maxAge","scentRange"}, new int[] {size, speed, maxAge, scentRange});
		this.genome.setGeneValues();
		this.facingDirection = Math.random() * 2 * Math.PI;
		this.eatSizeFactor = eatSizeFactor;
		setXYLoc();
	}
	
	/**
	 * Constructor for multyplying hetrotroph species
	 * @param x the coordinate in pixels of the x location of the hetrotrophspecies
	 * @param y the coordinate in pixels of the y location of the hetrotrophspecies
	 * @param energy the amount of energy for the hetrotrophspecies to start with
	 * @param genome the genome of the hetrotrophspecies
	 * @param number representing the order of creation of the species
	 * @param eatSizeFactor double that tells how mutch smaller the species can be and still eat
	 * another species.
	 */
	public HetrotrophSpecies(int x, int y, int energy, Genome genome, int number, double eatSizeFactor) {
		super(energy, number, 20000);
		this.genome = genome;
		this.eatSizeFactor = eatSizeFactor;
		this.facingDirection = Math.random() * 2 * Math.PI;
		setXYLoc(x, y);
	}

	/**
	 * For eveoking methods that need an update every game update. 
	 */
	@Override
	public void nextTimePoint() {
		move();
		addAge();
		extendedNextTimePoint();
	}
	
	/**
	 * Function that will check if a eatable species is completely in the bounding box of the carnivore.
	 * If this is the case true is returned and the energy is added to the energy of the carnivore. This means
	 * that the species got eaten and will be removed from the game. The comparissons are always rounded down to 
	 * the nearest number.
	 * @param x the x coordinate of the eatable species
	 * @param y the y coordinate of the eatable species
	 * @param size the sSize the size of the eatable species
	 * @param sEnergy the energy of the eatable species
	 * @return boolean that tells if the species is eaten or not.
	 */
	public boolean eat(int x, int y, int sSize, int sEnergy) {
		if (getSize() * getEatSizeFactor() >= sSize) {
			if (getxLoc() - 0.5 * getSize() * getEatSizeFactor() <= x - 0.5 * sSize && 
					getxLoc() + Math.round(0.5 * getSize() * getEatSizeFactor()) >= x + Math.round(0.5 * sSize) && 
					getyLoc() - Math.round(0.5 * getSize() * getEatSizeFactor()) <= y - Math.round(0.5 * sSize) && 
					getyLoc() + Math.round(0.5 * getSize() * getEatSizeFactor()) >= y + Math.round(0.5 * sSize)) {
				changeEnergy(sEnergy);
				return true;
			}
		}
		return false;
	}

	/**
	 * Extension of nextTimePoint(). This method is a method for specific hetrotrophspecies for
	 * evoking certain methods only they need evoked every time point.
	 */
	public abstract void extendedNextTimePoint();

	/**
	 * Moves the species acoording to theire speed. The speed is the distance moved in pixels. A random 
	 * direction within 0.5 pi radians (quarter of a circel) from the original facingDirection is chosen
	 * for the species to move towards. Also the energy is reduced accordingly.
	 */
	public void move() {
		if (getEnergy() > 0) {
			changeXLoc(Math.sin(getFacingDirection()) * getSpeed());
			changeYLoc((-1 * Math.cos(getFacingDirection()) * getSpeed()));
			changeEnergy(-1 * getEnergyConsumption());
			double min = (getFacingDirection() - 0.25 * Math.PI);
			double max = (getFacingDirection() + 0.25 * Math.PI);
			setFacingDirection((Math.random() * (max - min)) + min);
		}		
	}
	
	/**
	 * Changes the facing direction to a given value. This can be any double
	 * @param fd double of the facing direction
	 */
	public void setFacingDirection(double fd) {
		facingDirection = fd;
	}
	
	/**
	 * Formula for calculating the energy consumption of the hetrotrophspecies. The area of the sphere of
	 * the species occupies to the power of 1.4 creates an exponential increase in energy consumption
	 * limiting the size of the species. The rest of the factors are linear in theire effect and they depend 
	 * linearly on the value of the given statistic.
	 * @return
	 */
	public double getEnergyConsumption() {
		int r = getSize() / 2;
		double contentSurface = (1.33* Math.PI * Math.pow(r, 3)) /(4 * Math.PI * Math.pow(r, 2));
		return (Math.pow(1.4, contentSurface) - 1) + 0.5 * getSpeed() + 0.125 * getScentRange() + getAge();
	}
	
	/**
	 * Returns the current facing direction of a species. The sin() and cos() of the facing direction 
	 * determine the movement on the x and y axis respectively.
	 * @return
	 */
	public double getFacingDirection() {
		return this.facingDirection;
	}
	
	/**
	 * Returns the max age as determined by the genome of the hetrotrophspecies
	 */
	@Override
	public int getMaxAge() {
		return this.genome.getAttributeValue("maxAge");
	}
	
	/**
	 * Returns the max size as determined by the genome of the hetrotrophspecies
	 */
	public int getMaxSize() {
		return this.genome.getAttributeValue("size");
	}
	
	/**
	 * Returns the saved eatSizeFactor for a hetrotropspecies
	 * @return
	 */
	public double getEatSizeFactor() {
		return this.eatSizeFactor;
	}
	
	/**
	 * Returns the scent range as determined by the genome of the hetrotrophspecies
	 */
	public int getScentRange() {
		return this.genome.getAttributeValue("scentRange") + getSize();
	}
	
	/**
	 * Returns the speed as determined by the genome of the hetrotrophspecies
	 */
	public int getSpeed() {
		return this.genome.getAttributeValue("speed");
	}
	
	/**
	 * Allows for changing the speed of a species by changing its genome value.
	 * TODO: this function is dangerous and not desireable
	 * @param i speed to be set.
	 */
	public void setSpeed(int i) {
		this.genome.setGeneValue("speed", i);
	}

	/**
	 * Returns the genome of the species
	 * @return a instance of the genome class as saved by a hetrotrophspecies.
	 */
	public Genome getGenome() {
		return genome;
	}

	/**
	 * Returns the current size of the species. This depends on its age. The species grows from half its size
	 * to its max size over the half of its lifetime. If that point is reached it keeps its max size
	 */
	@Override
	public int getSize() {
		double smallerFactor = ((double) getAge() ) / getMaxAge();
		if (smallerFactor <= 0.5) {
			return (int) ((0.5 + smallerFactor) * getMaxSize());
		}
		return getMaxSize();
	}

	/**
	 * Returns data for the current value of each attribute
	 * TODO: needs to be dynamic and simply loop trough the genes in the genome
	 */
	@Override
	public double[] getAttributeData() {
		double[] attributeArray = new double[5];
		attributeArray[0] = getSpeed();
		attributeArray[1] = getMaxSize();
		attributeArray[2] = getMaxAge();
		attributeArray[3] = getScentRange() - getSize();
		attributeArray[4] = getEnergyConsumption();
		return attributeArray;
	}

	/**
	 * @see Species.inXBounds() This function adds a change in facing direction to make sure hetrotrophspecies 
	 * dont get stuk at one place.
	 */
	@Override
	public boolean inXBounds(double d) {
		if (getxLoc() + d + 0.5 * getSize() >= WINDOW_SIZE) {
			facingDirection += 0.25 * Math.PI;
			return false;
		}
		else if (getxLoc() + d - 0.5 * getSize() <= 0) {
			facingDirection += 0.25 * Math.PI;
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * @see Species.inYBounds() This function adds a change in facing direction to make sure hetrotrophspecies 
	 * dont get stuk at one place.
	 */
	@Override
	public boolean inYBounds(double d) {
		if (getyLoc() + d + 0.5 * getSize() >= WINDOW_SIZE) {
			facingDirection += 0.25 * Math.PI;
			return false;
		}
		else if (getyLoc() + d - 0.5 * getSize() <= 0) {
			facingDirection += 0.25 * Math.PI;
			return false;
		}
		else {
			return true;
		}
	}
}
