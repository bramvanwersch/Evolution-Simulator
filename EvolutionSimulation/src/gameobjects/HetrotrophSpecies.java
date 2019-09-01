package gameobjects;

import genome.Genome;

public abstract class HetrotrophSpecies extends Species {
	private Genome genome;
	private double facingDirection;
	private double eatSizeFactor;


	public HetrotrophSpecies(int x, int y, int energy, Genome genome, int number, double eatSizeFactor) {
		super(energy, number, 10000);
		this.genome = genome;
		this.eatSizeFactor = eatSizeFactor;
		this.facingDirection = Math.random() * 2 * Math.PI;
		setXYLoc(x, y);

		}

	public HetrotrophSpecies(int size, int speed, int maxAge, int scentRange, double eatSizeFactor) {
		super(10000);
		this.genome = new Genome(new String[] {"size","speed","maxAge","scentRange"}, new int[] {size, speed, maxAge, scentRange});
		this.genome.setGeneValues();
		this.facingDirection = Math.random() * 2 * Math.PI;
		this.eatSizeFactor = eatSizeFactor;
		setXYLoc();
		}
	
	@Override
	public void nextTimePoint() {
		move();
		addAge();
		extendedNextTimePoint();
	}
	
	/**
	 * Function that will check if a eatable species is completely in the bounding box of the carnivore.
	 * If this is the case true is returned and the energy is added to the energy of the carnivore. This means
	 * that the species got eaten and will be removed from the game.
	 * @param x the x coordinate of the eatable species
	 * @param y the y coordinate of the eatable species
	 * @param size the sSize the size of the eatable species
	 * @param sEnergy the energy of the eatable species
	 * @return boolean that tells if the species is eaten or not.
	 */
	public boolean eat(int x, int y, int sSize, int sEnergy) {
		if (getSize() * getEatSizeFactor() > sSize) {
			if (getxLoc() - 0.5 * getSize() * getEatSizeFactor() <= x - 0.5 * sSize && 
					getxLoc() + 0.5 * getSize() * getEatSizeFactor() >= x + 0.5 * sSize && 
					getyLoc() - 0.5 * getSize() * getEatSizeFactor() <= y - 0.5 * sSize && 
					getyLoc() + 0.5 * getSize() * getEatSizeFactor() >= y + 0.5 * sSize) {
				changeEnergy(sEnergy);
				return true;
			}
		}
		return false;
	}

	public abstract void extendedNextTimePoint();

	private void move() {
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
		return (int) (((getGenome().getGeneValue("size") - 0.5 * getGenome().getGeneValue("size")) * (getAge())) /
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
