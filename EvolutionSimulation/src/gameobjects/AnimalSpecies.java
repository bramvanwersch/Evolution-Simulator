package gameobjects;

import genome.Genome;

public abstract class AnimalSpecies extends Species {
	private Genome genome;
	private double facingDirection;
	private double eatSizeFactor;


	public AnimalSpecies(int x, int y, int energy, Genome genome, int number, double eatSizeFactor) {
		super(x, y, energy, number);
		this.genome = genome;
		this.eatSizeFactor = eatSizeFactor;
		this.facingDirection = Math.random() * 2 * Math.PI;

		}

	public AnimalSpecies(int size, int speed, int maxAge, int scentRange, double eatSizeFactor) {
		super(size);
		this.genome = new Genome(new String[] {"size","speed","maxAge","scentRange"}, new int[] {size, speed, maxAge, scentRange});
		this.genome.setGeneValues();
		this.facingDirection = Math.random() * 2 * Math.PI;
		this.eatSizeFactor = eatSizeFactor;
		setXYLoc();
		}

	@Override
	public abstract boolean foodEaten(int x, int y, int sSize, int sEnergy);

	@Override
	public abstract void eatTimeCheck();

	@Override
	public boolean checkAge() {
		if (getAge() >= getMaxAge()) {
			return true;
		}
		return false;
	}

	@Override
	public void move() {
		if (getEnergy() > 0) {
			double min = (getFacingDirection() - 0.25 * Math.PI);
			double max = (getFacingDirection() + 0.25 * Math.PI);
			setFacingDirection((Math.random() * (max - min)) + min);
			changeXLoc(Math.sin(getFacingDirection()) * getSpeed());
			changeYLoc((-1 * Math.cos(getFacingDirection()) * getSpeed()));
			changeEnergy(-1*getEnergyConsumption());
		}		
	}
	
	protected void setFacingDirection(double fd) {
		facingDirection = fd;
	}
	
	public double getEnergyConsumption() {
		int r = getSize() / 2;
		double contentSurface = (1.33* Math.PI * Math.pow(r, 3)) /(4 * Math.PI * Math.pow(r, 2));
		return (Math.pow(1.4, contentSurface) - 1) + 0.5 * getSpeed() + 0.125 * (getScentRange() - getSize()) + getAge();
	}
	
	public double getFacingDirection() {
		return this.facingDirection;
	}

	@Override
	public int getMaxAge() {
		return this.genome.getGeneValue("maxAge");
	}
	
	public int getMaxSize() {
		return this.genome.getGeneValue("size");
	}
	
	public double getEatSizeFactor() {
		return this.eatSizeFactor;
	}
	
	public int getScentRange() {
		return this.genome.getGeneValue("scentRange") + getSize();
	}
	
	public int getSpeed() {
		return this.genome.getGeneValue("speed");
	}
	
	public void setSpeed(int i) {
		this.genome.setGeneValue("speed", i);
	}

	public Genome getGenome() {
		return genome;
	}

	@Override
	public int getSize() {
		return (int) (((getGenome().getGeneValue("size") - 0.5 * getGenome().getGeneValue("size")) * getAge()) /
				(getAge() + 5) + 0.5 * getGenome().getGeneValue("size"));
	}

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

	@Override
	public double inXBounds(double d) {
		if (getxLoc() + d + 0.5 * getSize() > WINDOW_SIZE) {
			facingDirection += 0.25 * Math.PI;
			return 0;
		}
		else if (getxLoc() + d - 0.5 * getSize() < 0) {
			facingDirection += 0.25 * Math.PI;
			return 0;
		}
		else {
			return d;
		}
	}

	@Override
	public double inYBounds(double d) {
		if (getyLoc() + d + 0.5 * getSize() > WINDOW_SIZE) {
			facingDirection += 0.25 * Math.PI;
			return 0;
		}
		else if (getyLoc() + d - 0.5 * getSize() < 0) {
			facingDirection += 0.25 * Math.PI;
			return 0;
		}
		else {
			return d;
		}
	}

}
