package environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import species.AutotrophSpecies;

/**
 * Class that holds the environment related values.
 * @author Bram
 *
 */
public class Environment {
	private NutrientDeposit[][] allDeposits;
	private final double REGENERATION_RATE = 50.0;
	
	/**
	 * Constructor that creates deposits for the 3 main nutrients. 
	 * @param nitrogen an int array of lenght 2 that contains the number of 
	 * deposits and the maximum amount of nitrogen in the deposit.
	 * @param phosporus an int array of lenght 2 that contains the number of 
	 * deposits and the maximum amount of phosporous in the deposit.
	 * @param potassium an int array of lenght 2 that contains the number of 
	 * deposits and the maximum amount of potassium in the deposit.
	 */
	public Environment(int[] nitrogen, int[] phosporus, int[] potassium) {
		allDeposits = new NutrientDeposit[][] {createDeposits(nitrogen), createDeposits(phosporus), createDeposits(potassium)};
	}
	
	public void nextTimePoint() {
		for (int i = 0; i < allDeposits.length; i++) {
			allDeposits[i] = shuffleArray(allDeposits[i]);
		}
		removeEmptyDeposits();
		regenerateNutrients();
	}

	private void regenerateNutrients() {
		for (int i = 0; i < allDeposits.length; i++) {
			for (int j = 0; j < allDeposits[i].length; j++) {
				allDeposits[i][j].addNutrient(REGENERATION_RATE);
			}
		}
	}

	private void removeEmptyDeposits() {
		for (int i = 0; i < allDeposits.length; i++) {
			ArrayList<NutrientDeposit> existingDeposits = new ArrayList<NutrientDeposit>();
			for (int j = 0; j < allDeposits[i].length; j++) {
				if (allDeposits[i][j].getTotalAvailableNutrient() > 0.0) {
					existingDeposits.add(allDeposits[i][j]);
				}
			}
			allDeposits[i] = existingDeposits.toArray(new NutrientDeposit[existingDeposits.size()]);
		}
	}

	/**
	 * Creates deposits for each amount requested
	 * @param element is an array with the number of deposits and the maximum
	 * value for a deposit.
	 * @return an Array that contains the amopunt of NutrientDeposit objects
	 * that was requested. 
	 */
	private NutrientDeposit[] createDeposits(int[] element) {
		int numberOfDeposits = element[0];
		int maxValue = element[1];
		NutrientDeposit[] deposits = new NutrientDeposit[numberOfDeposits];
		for (int i = 0; i < numberOfDeposits; i++) {
			deposits[i] = new NutrientDeposit(maxValue);
		}
		return deposits;
	}

	//TODO these functions need to return individual Objects not lists like this.
	public NutrientDeposit[] getNitrogenDeposits() {
		return allDeposits[0];
	}

	public NutrientDeposit[] getPhosporusDeposits() {
		return allDeposits[1];
	}

	public NutrientDeposit[] getPotassiumDeposits() {
		return allDeposits[2];
	}
	
	/**
	 * Function that returns the sum of the amount of nutrients available for 
	 * a certain location.
	 * @param xCoord of the location the information is requested for
	 * @param yCoord of the location the information is requested for
	 * @return an Array of lenght 3 that holds the total of each nutrient 
	 * available.
	 */
	public double[] getNutrientValues(int xCoord, int yCoord) {
		double[] nutrientValues = new double[] {0,0,0};
		double[][] depositsList = getNutrientDepositValueList(xCoord, yCoord);
		for (int i = 0; i < depositsList.length; i++) {
			nutrientValues[i] = sum(depositsList[i]);
		}
		return nutrientValues;
	}
	
	/**
	 * Function that returns an array of arrays that holds all nutrient values 
	 * of deposits in the order that they are saved in.
	 * @param xCoord of the location the information is requested for
	 * @param yCoord of the location the information is requested for
	 * @return an array pf arrays that holds all values for each deposit based
	 * on a certain location.
	 */
	private double[][] getNutrientDepositValueList(int xCoord, int yCoord){
		double[][] depositsList = new double[3][];
		for (int i = 0; i < allDeposits.length; i++) {
			double [] deposits = new double[allDeposits[i].length];
			for (int j = 0; j < allDeposits[i].length; j++) {
				//if outside radius returns 0
				deposits[j] = allDeposits[i][j].getValueAtDistance(xCoord, yCoord);
			}
			depositsList[i] = deposits;
		}
		return depositsList;
	}
	
	/**
	 * Calculates a sum of an array of doubles. 
	 * @param values is a list of doubles 
	 * @return a double that represents the sum of the list of doubles.
	 */
    private double sum(double[] values) {
        double sum = 0;
        for (double val : values) {
            sum += val;
        }
        return sum;
    }
    
    /**
     * Counts all values in a list that are bigger then 0
     * @param array of doubles
     * @return the number of values in the array that are bigger then 0.
     */
    private boolean anyAboveZero(double[] array) {
    	for (double val : array) {
    		if (val > 0.0) {
    			return true;
    		}
    	}
    	return false;
    }
    
	/**
	 * Calculates the amount of nutrients that need to be substracted from 
	 * each individual nutrient source and substracts them by trying to 
	 * substract all from the first deposit in the list. The remainder is 
	 * returned and substracted from the next deposit etc. There are several
	 * checks for zero values to speed up the process.
	 * @param xCoord of the location the information is requested for
	 * @param yCoord of the location the information is requested for
	 * @param fractionConsumed is a fraction of the MAXIMUM_NUTRIENT_NEEDED
	 * based on the nutrient that is the least available.
	 */
	public void reduceNutrientValues(int xCoord, int yCoord, double fractionConsumed) {
		double[][] depositsList = getNutrientDepositValueList(xCoord, yCoord);
		for (int i = 0; i < depositsList.length; i++) {
			if (anyAboveZero(depositsList[i])) {
				double reduceAmount = (AutotrophSpecies.MAXIMUM_CONSUMPTION_RATE * fractionConsumed);
				for (int j = 0; j < depositsList[i].length; j++) {
					if (reduceAmount == 0.0) break;
					reduceAmount = allDeposits[i][j].removeAmount(reduceAmount);
				}
			}
		}
	}
	
	/**
	 * Creates a randomly shuffled array. By swapping each position in the array
	 * with a random position of the array (can be the same position).
	 * @ar the array that needs to be shuffled
	 * @return an integer array that contains as much numbers as populations. 
	 * This is to ensure that populations are looped trough at random but the 
	 * data collection stays logical.
	 */
	private NutrientDeposit[] shuffleArray(NutrientDeposit[] ar) {
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--){
			int index = rnd.nextInt(i + 1);
			NutrientDeposit a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
		return ar;
	}
}
