package simulation;

import genome.Genome;

public class Species{
	private final int ENERGY_DIVISION = 3;
	private final int MINIMUM_REP_TIME = 2;
	private final int DEFAULT_SCENT_RANGE = 40;
	private final int DEFAULT_ENERGY = 4000;
	private int WINDOW_SIZE = 900;
	private Genome genome;
	private int speed;
	private int xLoc;
	private int yLoc;
	private int size;
	private int energy;
	private double facingDirection;
	private int age;
	private int maxAge;
	private int scentRange;
	private int lastRepCycle;
	
	//constructor for innitial species construction
	public Species(int size, int speed, int maxAge) {
		this.genome = new Genome(new String[] {"size","speed","maxAge","scentRange"}, new int[] {size, speed, maxAge, DEFAULT_SCENT_RANGE});
		this.genome.setGeneValues();
		this.speed = genome.getGeneValue("speed");
		this.size = genome.getGeneValue("size");
		this.maxAge = genome.getGeneValue("maxAge");
		this.scentRange = genome.getGeneValue("scentRange");
		this.energy = DEFAULT_ENERGY;
		this.age = 0;
		this.lastRepCycle = 0;
		this.facingDirection = Math.random() * 2 * Math.PI;
		this.xLoc = (int) (Math.random()*(WINDOW_SIZE - this.size) + 0.5* size);
		this.yLoc = (int) (Math.random()*(WINDOW_SIZE - this.size) + 0.5* size);
	}
	
//Constructor for multiplying
	public Species(int x, int y, int energy, Genome genome) {
		this.genome = genome;
		this.speed = genome.getGeneValue("speed");
		this.size = genome.getGeneValue("size");
		this.maxAge = genome.getGeneValue("maxAge");
		this.scentRange = genome.getGeneValue("scentRange");
		this.energy = energy;
		this.age = 0;
		this.facingDirection = Math.random() * 2 * Math.PI;
		this.lastRepCycle = 0;
		this.xLoc = x;
		this.yLoc = y;
	}

	public double getEnergyConsumption() {
		//for underlying classes
		return 10000000;
	}
	
	public void move() {
		if (this.energy > 0) {
			double min = (facingDirection - 0.25 * Math.PI);
			double max = (facingDirection + 0.25 * Math.PI);
			facingDirection = (Math.random() * (max - min)) + min;
			changeXLoc(Math.sin(facingDirection) * this.speed);
			changeYLoc((-1 * Math.cos(facingDirection) * this.speed));
			changeEnergy(-1*getEnergyConsumption());
		}
	}
	
	public void moveAway(int x, int y) {
		//method for inheriting classes
	}
	
	public boolean checkCanEat(int x, int y, int sSize, int sEnergy) {
		return false;
	}
	
	private double inXBounds(double d) {
		if (this.xLoc + d + 0.5 * this.size > WINDOW_SIZE) {
			facingDirection += 0.25 * Math.PI;
			return 0;
		}
		else if (this.xLoc + d - 0.5 * this.size < 0) {
			facingDirection += 0.25 * Math.PI;
			return 0;
		}
		else {
			return d;
		}
	}
	
	private double inYBounds(double d) {
		if (this.yLoc + d + 0.5 * this.size > WINDOW_SIZE) {
			facingDirection += 0.25 * Math.PI;
			return 0;
		}
		else if (this.yLoc + d - 0.5 * this.size < 0) {
			facingDirection += 0.25 * Math.PI;
			return 0;
		}
		else {
			return d;
		}
	}

	public boolean isCanMultiply() {
		if (this.energy/2 > DEFAULT_ENERGY && this.lastRepCycle > MINIMUM_REP_TIME) {
			this.lastRepCycle = 0;
			return true;
		}
		return false;
	}
	
	public void changeXLoc(double d) {
		d = inXBounds(d);
		this.xLoc += Math.round(d);
	}

	public void changeYLoc(double d) {
		d = inYBounds(d);
		this.yLoc += Math.round(d);
	}
	
	protected void setFacingDirection(double fd) {
		facingDirection = fd;
	}
	
	public double getFacingDirection() {
		return this.facingDirection;
	}
	
	public int getScentRange() {
		return this.scentRange;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void addAge() {
		this.age += 1;
	}
	
	public void addRepTime() {
		this.lastRepCycle += 1;
	}
	
	public int getxLoc() {
		return xLoc;
	}
	
	protected void changeEnergy(double energyConsumption) {
		this.energy += energyConsumption;
	}

	public int getyLoc() {
		return yLoc;
	}

	public int getSize() {
		return size;
	}
	
	public int getEnergy() {
		return this.energy;
	}
	
	public int halfEnergy() {
		energy = energy/ENERGY_DIVISION;
		return energy;
	}

	public int getAge() {
		return this.age;
	}
	
	public int getMaxAge() {
		return this.maxAge;
	}

	public Genome getGenome() {
		return genome;
	}
	
	public boolean foodEaten(int getxLoc, int getyLoc, int size2, int energy2) {
		// for inheriting classes
		return false;
	}
}
