package utility_functions;

import java.util.List;
import java.util.Random;

public class Utility {
	
	/**
	 * Creates a randomly shuffled array. By swapping each position in the array
	 * with a random position of the array (can be the same position).
	 * @ar the array that needs to be shuffled
	 * @return an integer array that contains as much numbers as populations. 
	 * This is to ensure that populations are looped trough at random but the 
	 * data collection stays logical.
	 */
	public static int[] shuffleArray(int[] ar) {
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--){
			int index = rnd.nextInt(i + 1);
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
		return ar;
	}
	
	/**
	 * Calculates a sum of a list of doubles. 
	 * @param values is a list of doubles 
	 * @return a double that represents the sum of the list of doubles.
	 */
	public static double sum(List<Double> values) {
        double sum = 0;
        for (double val : values) {
            sum += val;
        }
        return sum;
	}
	
	/**
	 * Calculates a sum of an array of doubles. 
	 * @param values is a array of doubles 
	 * @return a double that represents the sum of the array of doubles.
	 */
    public static double sum(double[] values) {
        double sum = 0;
        for (double val : values) {
            sum += val;
        }
        return sum;
    }
}
