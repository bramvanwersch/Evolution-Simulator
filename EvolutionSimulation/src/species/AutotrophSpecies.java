package species;

import genome.Genome;

/**
 * Class that holds general methods for AutotrophSpecies
 * @author Bram van Wersch
 */
public class AutotrophSpecies extends Species{
	private final int MAX_ENERGY_GAIN = 50;
	//value at wich the growth efficincy of the species goes down
	public final static int MAXIMUM_CONSUMPTION_RATE = 20;
	private int maxAge;
	private int size;

	/**
	 * Constructor that randomly innitialises the location of the Autotroph 
	 * species and saves some variables
	 * @param size of the species
	 * @param maxAge of the species
	 * @param startEnergy of the species
	 */
	public AutotrophSpecies(int size, int maxAge, int startEnergy) {
		super(startEnergy);
		setXYLoc();
		this.maxAge = maxAge;
		this.size = size;
	}
	/**
	 * Constructor that innitialises the location of the Autotroph species
	 * based on a x and y coordinate.
	 * @param x coordinate for the species to be placed
	 * @param y coordinate for the species to be placed
	 * @param size of the species
	 * @param maxAge of the species
	 * @param startEnergy of the species
	 */
	public AutotrophSpecies(int x, int y, int size, int maxAge, int startEnergy) {
		super(startEnergy);
		this.maxAge = maxAge;
		this.size = size;
		setXYLoc(x, y);
	}
	
	@Override
	public void nextTimePoint() {
		addAge();
	}

	/**
	 * Takes the values of nutrients available in its current spot and
	 * calculates the amount of energy the plant will get based on these
	 * values.
	 * @param nutrientValues is an array that contains the values for the three
	 * nutrients and determines how much energy the plant can consume.
	 * @return a fraction between 1 and 0.05 that tells how much of the 
	 * nutrients where consumed.
	 */
	public double eat(double[] nutrientValues) {
		double additionFactor = 1.0;
		double lowestVal = MAXIMUM_CONSUMPTION_RATE;
		for (double val: nutrientValues) {
			if (val < lowestVal) {
				lowestVal = val;
			}
		}
		additionFactor *= lowestVal / MAXIMUM_CONSUMPTION_RATE * 0.95 + 0.05;
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
	public Genome getGenome() {
		// TODO Auto-generated method stub
		return null;
	}

}
