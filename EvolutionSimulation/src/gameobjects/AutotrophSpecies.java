package gameobjects;

import genome.Genome;

public class AutotrophSpecies extends Species{
	private int maxAge;
	private int size;

	public AutotrophSpecies(int size, int maxAge, int startEnergy) {
		super(startEnergy);
		setXYLoc();
		this.maxAge = maxAge;
		this.size = size;
	}
	
	@Override
	public void nextTimePoint() {
		addAge();				
	}

	@Override
	public boolean eat(int x, int y, int sSize, int sEnergy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMaxAge() {
		return this.maxAge;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public double[] getAttributeData() {
		// TODO Auto-generated method stub
		return null;
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

}
