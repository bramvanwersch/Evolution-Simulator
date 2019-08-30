package gameobjects;

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
	
	public void setXYLoc() {
		this.xLoc = (int) (Math.random()*(WINDOW_SIZE - getSize()) + 0.5* getSize());
		this.yLoc = (int) (Math.random()*(WINDOW_SIZE - getSize()) + 0.5* getSize());
	}
	
//Constructor for multiplying
	public Species(int x, int y, int energy, int number, int startEnergy) {
		//make species grow
		this.energy = energy;
		this.startingEnergy = startEnergy;
		this.no = number;
		this.age = 0;
		this.xLoc = x;
		this.yLoc = y;
	}
	
	public abstract void nextTimePoint();
	
	public abstract Genome getGenome();
	
	public abstract double getEatSizeFactor();
	
	public abstract double[] getAttributeData();
	
	public abstract int getSize();

	public abstract int getMaxAge();
			
	public void changeXLoc(double d) {
		d = inXBounds(d);
		this.xLoc += Math.round(d);
	}

	public void changeYLoc(double d) {
		d = inYBounds(d);
		this.yLoc += Math.round(d);
	}
	
	public boolean isCanMultiply() {
		if (this.energy/2 > this.startingEnergy) {
			return true;
		}
		return false;
	}
	
	public double inXBounds(double d) {
		if (getxLoc() + d + 0.5 * getSize() > WINDOW_SIZE) {
			return 0;
		}
		else if (getxLoc() + d - 0.5 * getSize() < 0) {
			return 0;
		}
		else {
			return d;
		}
	}

	public double inYBounds(double d) {
		if (getyLoc() + d + 0.5 * getSize() > WINDOW_SIZE) {
			return 0;
		}
		else if (getyLoc() + d - 0.5 * getSize() < 0) {
			return 0;
		}
		else {
			return d;
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
