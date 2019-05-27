package simulation;

public class Omnivore extends Species{

	public Omnivore(int size, int speed, int maxAge) {
		super(size, speed, maxAge);
	}
	
	public Omnivore(int size, int speed, int x, int y, int maxAge, int scentRange,int energy) {
		super(size, speed, x, y, maxAge, scentRange, energy);
	}
	
	public double getEnergyConsumption() {
		int r = getSize() / 2;
		double contentSurface = (1.33* Math.PI * Math.pow(r, 3)) /(4 * Math.PI * Math.pow(r, 2));
		return (Math.pow(1.25, contentSurface) - 1)* 0.3* getSpeed() + 0.25 * getScentRange() + getAge();
	}

	public boolean checkCanEat(int x, int y, int sSize, int sEnergy) {
		if (getxLoc() - 0.5 * getSize() < x && getxLoc() + 0.5 * getSize() - 0.5 * sSize > x 
				&& getyLoc() - 0.5 * getSize()  < y && getyLoc() + 0.5 * getSize() - 0.5 * sSize > y) {
			changeEnergy(sEnergy*0.3);
			return true;
		}
		return false;
	}
	
	public boolean foodEaten(int x, int y, int fSize, int fEnergy) {
		if (getxLoc() - 0.5 * getSize() < x && getxLoc() + 0.5 * getSize() - 0.5 * fSize > x
				&& getyLoc() - 0.5 * getSize()  < y && getyLoc() + 0.5 * getSize() - 0.5 * fSize > y) {
			changeEnergy(fEnergy * 0.5);
			return true;
		}
		return false;
	}
	
}
