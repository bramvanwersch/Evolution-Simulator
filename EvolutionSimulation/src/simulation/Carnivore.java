package simulation;

public class Carnivore extends Species{
	private int chaseTime;
	private int MAX_CHASE_TIME = 2000;

	public Carnivore(int size, int speed, int maxAge) {
		super(size, speed, maxAge);
		this.chaseTime = 0;
	}
	
	public Carnivore(int size, int speed, int x, int y, int maxAge, int scentRange,int energy) {
		super(size, speed, x, y, maxAge, scentRange, energy);
		this.chaseTime = 0;
	}
	
	public double getEnergyConsumption() {
		int r = getSize() / 2;
		double contentSurface = (1.33* Math.PI * Math.pow(r, 3)) /(4 * Math.PI * Math.pow(r, 2));
		return (Math.pow(1.25, contentSurface) - 1)* 0.3* getSpeed() + 0.25 * getScentRange() + getAge();
	}

	public boolean checkCanEat(int x, int y, int sSize, int sEnergy) {
		if (getxLoc() - 0.5 * getSize() < x && getxLoc() + 0.5 * getSize() - 0.5 * sSize > x 
				&& getyLoc() - 0.5 * getSize()  < y && getyLoc() + 0.5 * getSize() - 0.5 * sSize > y) {
			changeEnergy(sEnergy);
			return true;
		}
		return false;
	}
	
	public void moveAway(int ix, int iy) {
		double y  = (double) iy;
		double x = (double) ix;
		this.chaseTime += 50;
		if (getEnergy() > 0 && this.chaseTime < this.MAX_CHASE_TIME) {
			double slopeLength = Math.sqrt(Math.pow(x - getxLoc(), 2) + Math.pow(y - getyLoc(), 2));
			//direction that is straigh away from the target
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
			chaseTime -= 5;
		}
	}

}
