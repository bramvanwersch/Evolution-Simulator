package gameobjects;

import genome.Genome;

public class AutotrophSpecies extends Species{
	private final int MAX_ENERGY_GAIN = 15;
	//value at wich the growth efficincy of hte species goes down
	private final int MINIMAL_NUTRIENT_NEEDED = 20;
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

	//returns a value that explains how much of the nutrients was taken
	public double eat(double[] nutrientValues) {
		double additionFactor = 1.0;
		double lowestVal = MINIMAL_NUTRIENT_NEEDED;
		for(double val: nutrientValues) {
			if (val < lowestVal) {
				lowestVal = val;
			}
		}
		//factor between 1 and 0.1 if all obove 20 no addition factor stays 1
		additionFactor *= lowestVal/MINIMAL_NUTRIENT_NEEDED *0.9 + 0.1;
		changeEnergy(additionFactor * MAX_ENERGY_GAIN);
		return additionFactor;
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
