package species;

import genome.Genome;

public abstract class Species{
	private final int ENERGY_DIVISION = 2;
	private int startingEnergy;
	public final int WINDOW_SIZE = 950;
	private int no;
	private int xLoc;
	private int yLoc;
	private int energy;
	private int age;

	
	//constructor for innitial species construction
	public Species(int startEnergy) {
		this.energy = startEnergy;
		this.startingEnergy = startEnergy;
		this.no = 0;
		this.age = 0;
	}
	
//Constructor for multiplying
	public Species(int energy, int number, int startEnergy) {
		//make species grow
		this.energy = energy;
		this.startingEnergy = startEnergy;
		this.no = number;
		this.age = 0;
	}
	
	public void setXYLoc() {
		this.xLoc = (int) (Math.random()*(WINDOW_SIZE - getSize()) + 0.5* getSize());
		this.yLoc = (int) (Math.random()*(WINDOW_SIZE - getSize()) + 0.5* getSize());
	}
	
	public void setXYLoc(int x, int y) {
		this.xLoc = x;
		this.yLoc = y;
	}
	
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
	
	public abstract void nextTimePoint();
	
	public abstract Genome getGenome();
		
	public abstract double[] getAttributeData();
	
	public abstract int getSize();

	public abstract int getMaxAge();
			
	public void changeXLoc(double d) {
		if (inXBounds(d)) {
			this.xLoc += Math.round(d);
		}
	}

	public void changeYLoc(double d) {
		if (inYBounds(d)) {
			this.yLoc += Math.round(d);
		}
	}
	
	public boolean isCanMultiply() {
		if (this.energy/2 > this.startingEnergy) {
			return true;
		}
		return false;
	}
	
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
	
	public int getxLoc() {
		return xLoc;
	}
	
	public int getyLoc() {
		return yLoc;
	}
	
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
	
	public int getEnergy() {
		return this.energy;
	}
	
	protected void changeEnergy(double energyConsumption) {
		this.energy += energyConsumption;
	}
	
	public void halfEnergy() {
		this.energy = energy/ENERGY_DIVISION;
	}
	
//Methods for the attributes
	
	public void addAge() {
		this.age += 50;
	}
	
	public int getAge() {
		return this.age/1000;
	}
		
	public int getNumber() {
		return this.no;
	}
}
