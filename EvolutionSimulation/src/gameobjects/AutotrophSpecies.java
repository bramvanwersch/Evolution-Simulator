package gameobjects;

import genome.Genome;

public class AutotrophSpecies extends Species{

	public AutotrophSpecies(int size, int maxAge, int startEnergy) {
		super(startEnergy);
		setXYLoc();
	}

	@Override
	public boolean eat(int x, int y, int sSize, int sEnergy) {
		// TODO Auto-generated method stub
		return false;
	}

	public void extendedNextTimePoint() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxAge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getAttributeData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double inXBounds(double d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double inYBounds(double d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getEatSizeFactor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Genome getGenome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nextTimePoint() {
		// TODO Auto-generated method stub
		
	}

}
