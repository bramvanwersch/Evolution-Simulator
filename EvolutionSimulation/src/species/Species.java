package species;

import genome.Genome;

/**
 * Abstract class that represents the basic methods of species available.
 * @param ENERGY_DIVISION int representing the amount of energy lost when a species multiplies
 * @param startingEnergy int that remembers the starting energy of a species so the species knows 
 * when it can multiply.
 * @param WINDOW_SIZE int representing the height and width of the playing field in pixels. The 
 * field is square so the the height and width are the same.
 * @param no int representing the order of creation of a species
 * @param xLoc x location the species is on inbetween 0 and WINDOW_SIZE
 * @param yLoc y location the species is on inbetween 0 and WINDOW_SIZE
 * @param energy int representing the amount of energy a species currently has
 * @param age int representing the age of a species in mili seconds.
 * @author Bram van Wersch
 *
 */
public abstract class Species{
	private final int ENERGY_DIVISION = 2;
	private int startingEnergy;
	public final int WINDOW_SIZE = 950;
	private int no;
	private int xLoc;
	private int yLoc;
	private int energy;
	private int age;

	
	/**
	 * Constructor for cloning species.
	 * @param startEnergy int representing the amount of energy a species starts with
	 */
	public Species(int startEnergy) {
		this.energy = startEnergy;
		this.startingEnergy = startEnergy;
		this.no = 0;
		this.age = 0;
	}
	
	/**
	 * Constructor for multiplying species
	 * @param energy int starting energy of the species is dependant on the parent
	 * @param number int for order of creation of a species
	 * @param startEnergy int representing the amount of energy a species starts with this 
	 * signifies more the amount of energy a species works towards.
	 */
	public Species(int energy, int number, int startEnergy) {
		//make species grow
		this.energy = energy;
		this.startingEnergy = startEnergy;
		this.no = number;
		this.age = 0;
	}
	
	/**
	 *Sets the x and y coordinate values at random in the window size
	 */
	public void setXYLoc() {
		this.xLoc = (int) (Math.random()*(WINDOW_SIZE - getSize()) + 0.5* getSize());
		this.yLoc = (int) (Math.random()*(WINDOW_SIZE - getSize()) + 0.5* getSize());
	}
	
	/**
	 * Sets the x and y coordinate values for the values specified.
	 * @param x the x-coordinate for the species
	 * @param y the y-coordinate for the species
	 */
	public void setXYLoc(int x, int y) {
		this.xLoc = x;
		this.yLoc = y;
	}
	
	/**
	 * Returns a x and y value that are offset 50 to -50 pixels depending on the current location
	 * of this species. If the randomed value is out of bounds the x or y value is not changed.
	 * @return Array of 2 integers x and y coordinate
	 */
	public int[] getOfsetXYLoc() {
		int xDist = (int) (Math.random() * 100 -50);
		int yDist = (int) (Math.random() * 100 -50);
		int adjX = this.xLoc;
		int adjY = this.yLoc;
		if (inXBounds(xDist)) {
			adjX += xDist;
		}
		if (inYBounds(yDist)) {
			adjY += yDist;
		}
		return new int[] {adjX, adjY};
	}
	
	/**
	 * Calls methods that need to be called for every species after every update
	 */
	public abstract void nextTimePoint();
	
	/**
	 * Returns an instance of the genome for the species
	 * @return An instance of the genome for the current species
	 */
	public abstract Genome getGenome();
	
	/**
	 * Returns the of all the attributes of a certain species
	 * @return A double list containing all the values of the attributes of a certain species
	 */
	public abstract double[] getAttributeData();
	
	/**
	 * returns the diameter of a species in pixels
	 * @return Int that represents the diameter of the species in pixels
	 */
	public abstract int getSize();
	
	/**
	 * returns the maximum age in seconds the species is allowed to live. After this time the
	 * species simply dies
	 * @return Int representing the age of the species in seconds
	 */
	public abstract int getMaxAge();
	
	/**
	 * Changes the location of a species by a certain rounded double in the x direction
	 * @param d double representing the amount of pixels the species is suposed to be moved
	 * in the x direction
	 */
	public void changeXLoc(double d) {
		if (inXBounds(d)) {
			this.xLoc += Math.round(d);
		}
	}

	/**
	 * Changes the location of a species by a certain rounded double in the y direction
	 * @param d double representing the amount of pixels the species is suposed to be moved
	 * in the y direction
	 */
	public void changeYLoc(double d) {
		if (inYBounds(d)) {
			this.yLoc += Math.round(d);
		}
	}
	
	/**
	 * Returns a boolean that tells if the species has twice the amount of energy it had when 
	 * it was created. If this is the case the species will multiply.
	 */
	public boolean isCanMultiply() {
		if (this.energy/2 > this.startingEnergy) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns a boolean telling if a certain double makes the xLoc smaller then or equal to
	 * 0 or bigger then or equal to the WINDOW_SIZE
	 * @param d double signifiing the movement in the x direction
	 * @return boolean telling if a species is in or outside the playing window.
	 */
	public boolean inXBounds(double d) {
		if (this.xLoc + d + 0.5 * getSize() >= WINDOW_SIZE) {
			return false;
		}
		else if (this.xLoc + d - 0.5 * getSize() <= 0) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * Returns a boolean telling if a certain double makes the yLoc smaller then or equal to
	 * 0 or bigger then or equal to the WINDOW_SIZE
	 * @param d double signifiing the movement in the y direction
	 * @return boolean telling if a species is in or outside the playing window.
	 */
	public boolean inYBounds(double d) {
		if (this.yLoc + d + 0.5 * getSize() >= WINDOW_SIZE) {
			return false;
		}
		else if (this.yLoc + d - 0.5 * getSize() <= 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Returns the current x location of a species.
	 * @return Int telling the x location of a species in pixels
	 */
	public int getxLoc() {
		return xLoc;
	}
	
	/**
	 * Returns the current y location of a species.
	 * @return Int telling the y location of a species in pixels
	 */
	public int getyLoc() {
		return yLoc;
	}
	
	/**
	 * Returns boolean telling if a species is alive or not. This is false if the energy
	 * of the species is 0 or lower or when the species reaches its max age or older
	 * @return Boolean telling if the species is alive or not.
	 */
	public boolean isAlive() {
		if (this.energy <= 0) {
			return false;
		}
		else if (getAge() >= getMaxAge()) {
			return false;
		}
		return true;
	}
		
//	public abstract void scentMovement(int x, int y);
	
	//energy methods
	
	/**
	 * Returns the amount of energy a species currently has.
	 * @return Int representing the enrgy in an imaginary unit.
	 */
	public int getEnergy() {
		return this.energy;
	}
	
	/**
	 * Changes the energy by a given double. 
	 * @param energyConsumption Double telling how much the energy has to change
	 */
	public void changeEnergy(double energyConsumption) {
		this.energy += energyConsumption;
	}
	
	/**
	 * Changes the energy based on a ENERGY_DIVISION int. This method is called upon reproduction
	 * it signifies the amount of energy that is consumed and passed on to the next generation.
	 */
	public void divideEnergy() {
		this.energy = energy/ENERGY_DIVISION;
	}
	
//Methods for the attributes
	/**
	 * Increases the age of a species by 50ms. This is equal to the update time.
	 */
	public void addAge() {
		this.age += 50;
	}
	
	/**
	 * Returns the age of the organism in seconds. This tims is always rounded down to the 
	 * nearest integer
	 * @return Int that represents the living time of an organism in seconds.
	 */
	public int getAge() {
		return this.age/1000;
	}
	
	/**
	 * Returns an integer that displays the order of creation of species. A number 5 means that is was the 
	 * 5th carnivore or herbivore etc. that was created.
	 * @return Int displaying the order of creation.
	 */
	public int getNumber() {
		return this.no;
	}
}
