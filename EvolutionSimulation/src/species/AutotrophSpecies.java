package species;

import java.util.HashMap;
import java.util.Map;

import genome.Genome;

/**
 * Class that holds general methods for AutotrophSpecies
 * @author Bram van Wersch
 */
public class AutotrophSpecies extends Species{
	private final int MAX_ENERGY_GAIN = 50;
	//value at wich the growth efficincy of the species goes down
	public final static int MAXIMUM_CONSUMPTION_RATE = 20;
	public final double MINIMAL_GROWTH_FACTOR = 0.05;
	private int maxAge;
	private int size;
	private Map<String, double[]> nutrientValues;

	/**
	 * Constructor that randomly innitialises the location of the Autotroph 
	 * species and saves some variables
	 * @param size of the species
	 * @param maxAge of the species
	 * @param startEnergy of the species
	 */
	public AutotrophSpecies(int size, int maxAge, int startEnergy, int[] maxNutrientValues) {
		super(startEnergy);
		setXYLoc();
		this.maxAge = maxAge;
		this.size = size;
		this.nutrientValues = addNutrientValues(maxNutrientValues);
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
	public AutotrophSpecies(int x, int y, int size, int maxAge, int startEnergy, int[] maxNutrientValues) {
		super(startEnergy);
		this.maxAge = maxAge;
		this.size = size;
		setXYLoc(x, y);
		this.nutrientValues = addNutrientValues(maxNutrientValues);
	}
	
	@Override
	public void nextTimePoint() {
		addAge();
	}
	
	/** 
	 * Creates a map that contains the name of the nutrient and the max value
	 * specified together with the current value that is innitialised as half
	 * of the max value
	 * @param maxNutrientValues is an integer array with max values for each of
	 * the nutrients
	 * @return a map with the max and current values saved by the name of the
	 * nutrient.
	 */
	private Map<String, double[]> addNutrientValues(int[] maxNutrientValues) {
		Map<String, double[]> m = new HashMap<String, double[]>();
		m.put("nitrogen", new double[] {0.5 * maxNutrientValues[0], maxNutrientValues[0]});
		m.put("phosporus", new double[] {0.5 * maxNutrientValues[1], maxNutrientValues[1]});
		m.put("potassium", new double[] {0.5 * maxNutrientValues[2], maxNutrientValues[2]});
		return m;
	}
	
	/**
	 * Returns the max values that are saved for the intake of nutrients by
	 * this plant.
	 * @return an array of integers that are the max values for the three
	 * nutrients.
	 */
	public int[] getMaxNutrientValues() {
		int[] maxValues = new int[nutrientValues.size()];
		int count = 0;
		for (String key : nutrientValues.keySet()) {
			maxValues[count] = (int) nutrientValues.get(key)[1];
		}
		return maxValues;
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
		if (lowestVal < MAXIMUM_CONSUMPTION_RATE) {
			additionFactor = lowestVal / MAXIMUM_CONSUMPTION_RATE * (1 - MINIMAL_GROWTH_FACTOR) 
					+ MINIMAL_GROWTH_FACTOR;
		}

		//the two nutrients that might be more abundant can have a certain additional impact
		//this makes is so they have a function if not all 3 are present and the balance
		//of nutrients is better controlled. They have the same impact as the lowest val
		for (double val: nutrientValues) {
			if (val != lowestVal) {
				additionFactor += val / MAXIMUM_CONSUMPTION_RATE * (0.1 - MINIMAL_GROWTH_FACTOR) 
						+ MINIMAL_GROWTH_FACTOR;
			}
		}
//		System.out.println(additionFactor);
//		System.out.println();
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
