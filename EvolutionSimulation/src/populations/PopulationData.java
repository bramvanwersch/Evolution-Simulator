package populations;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class that records a data point for every second the a population is alive. It holds
 * the data for the graph to display. It saves the average, min, max value for each time
 * point.
 * @author Bram van wersch
 */
public class PopulationData {
    private final int AMOUNT_OF_DATA_POINTS = 50;
	private ArrayList<double[]> speedStats;
    private ArrayList<double[]> sizeStats;
    private ArrayList<double[]> ageStats;
    private ArrayList<double[]> scentStats;
    private ArrayList<double[]> energyCostStats;
    private ArrayList<Double> nrHerbivores;
    private ArrayList<Double> nrOmnivores;
    private ArrayList<Double> nrCarnivores;
    private ArrayList<Double> time;
    private ArrayList<Double> nrSpecies;
    private double dataDivisionFactor;
    private boolean reduce;
    private String type;

    /**
     * For innitialising all the arrayLists that hold all the data .
     * @param reduce is a boolean that tells the class to reduce the data when returning it or not.
     * @param dataDivisionFactor tells how many data points need to be collected and divided by
     * this number to reduce it to a point that there are AMOUNT_OF_DATA_POINTS data points at all
     * times returned if the
     * reduce boolean is true.
     */
    public PopulationData() {
        this.speedStats = new ArrayList<double[]>(100);
        this.sizeStats = new ArrayList<double[]>(100);
        this.ageStats = new ArrayList<double[]>(100);
        this.scentStats = new ArrayList<double[]>(100);
        this.energyCostStats = new ArrayList<double[]>(100);
        this.nrHerbivores = new ArrayList<Double>(100);
        this.nrOmnivores = new ArrayList<Double>(100);
        this.nrCarnivores = new ArrayList<Double>(100);
        this.time = new ArrayList<Double>(100);
        this.nrSpecies = new ArrayList<Double>(100);
        this.reduce = false;
        this.dataDivisionFactor = 1;
    }
    
    /**
     * Returns the average data of all populations. This is a function that returns this data 
     * if this kind of data is saved
     * TODO change this or make an option to return a certain set of data.
     * @return an Integer array of arrays
     */
    public int[][] getAverageDataArray(){
        int[][] dataArray = new int[8][];
        dataArray[0] = getNrHerbivores();
        dataArray[1] = getNrOmnivores();
        dataArray[2] = getNrCarnivores();
        dataArray[3] = getAvgSpeed();
        dataArray[4] = getAvgSize();
        dataArray[5] = getAvgAge();
        dataArray[6] = getAvgScent();
        dataArray[7] = getAvgEnergyCost();
        return dataArray;
    }
    
    /**
     * Returns all the data points that are recorded by the during the running of the simulation
     * for each individual attribute.
     * @return Array of arrays that contains all the data points of all the attributes.
     */
    public int[][] getDataArray(){
        int[][] dataArray = new int[6][];
        dataArray[0] = getAvgSpeed();
        dataArray[1] = getAvgSize();
        dataArray[2] = getAvgAge();
        dataArray[3] = getAvgScent();
        dataArray[4] = getAvgEnergyCost();
        dataArray[5] = getNrSpecies();
        return dataArray;
    }
    
    /**
     * Changes the data division factor for reducing the data points to be smaller
     * then AMOUNT_OF_DATA_POINTS value
     */
    public void setDataDivisionFactor() {
        int divisionFactor = 1;
        int arrayLength = time.size();
        while (arrayLength/divisionFactor > AMOUNT_OF_DATA_POINTS){
            divisionFactor += 1;
        }
        dataDivisionFactor = divisionFactor;
    }
    
    /**
     * Changes an arrayList of double arrays into an Integer arrays of arrays
     * @param doubles is an arrayList of double arrays
     * @return an Integer array of ararys.
     */
    public int[][] doubleListToIntArrayOfArrays(ArrayList<double[]> doubles){
        //first reduce the data before converting to doubles
        if (this.reduce) {
            doubles = reduceAllData(doubles);
        }
        int[][] returnArray = new int[doubles.size()][3];
        for (int i = 0; i < doubles.size(); i++){
            for(int j = 0 ; j < returnArray[i].length; j++) {
            	returnArray[i][j] = (int) Math.round(doubles.get(i)[j]);
            }
        }
        return returnArray;
    }
    
    /**
     * Function that reduces the amount of data points to AMOUNT_OF_DATA_POINTS. This is done for the 
     * average min and max data.
     * @param dataArray is an arrayList of double arrays that contain the average, min, max data for each
     * data point
     * @return AMOUNT_OF_DATA_POINTS or less data points for the average maximum and minimum data of each data point.
     */
    public ArrayList<double[]> reduceAllData(ArrayList<double[]> dataArray) {
        ArrayList<double[]> averagedData = new ArrayList<double[]>();
        List<double[]> values;
        for (int i = 0; i < dataArray.size(); i += dataDivisionFactor) {
            if (i + dataDivisionFactor <= dataArray.size()) {
                values  = dataArray.subList(i, (int) (i + dataDivisionFactor));
            }
            else {
                values = new ArrayList<double[]>();
                for (int k = i; k < dataArray.size(); k++) {
                    values.add(dataArray.get(i));
                }
            }
            double[] avgValues = allSum(values);
            double[] averagedDataUnit = {avgValues[0]/dataDivisionFactor, avgValues[1]/dataDivisionFactor,
            		avgValues[2]/dataDivisionFactor};
            averagedData.add(averagedDataUnit); 
        }
        return averagedData;
    }
    
    /**
     * Changes an array of doubles into an array of integers
     * @param doubles arrayList of doubles
     * @return an integer array
     */
	public int[] doubleListToIntArray(ArrayList<Double> doubles){
		//first reduce the data before converting to doubles
		if (this.reduce) {
			doubles = reduceAvgData(doubles);
		}
	    int[] ret = new int[doubles.size()];
	    for (int i = 0; i < doubles.size(); i++){
	        ret[i] = (int) Math.round(doubles.get(i));
	    }
	    return ret;
	}
	
	/**
	 * Returns the first value of the triplet of data that is saved for each data point.
	 * Each data point is saved as a average, min, max.
	 * @param dataArray
	 * @return an array of the first values of each double list.
	 */
	public ArrayList<Double> getAverageValues(ArrayList<double[]> dataArray){
		ArrayList<Double> avgValue = new ArrayList<Double>(dataArray.size());
		for(int i= 0; i < dataArray.size(); i++) {
			avgValue.add(dataArray.get(i)[0]);
		}
		return avgValue;
	}
    
	/**
	 * Reduces the average data array to include AMOUNT_OF_DATA_POINTS or less 
	 * @param dataArray an array of doubles
	 * @return an array of doubles that is not longer then 50.
	 */
	private ArrayList<Double> reduceAvgData(ArrayList<Double> dataArray) {
		ArrayList<Double> averagedData = new ArrayList<Double>();
		for (int i = 0; i < dataArray.size(); i += dataDivisionFactor) {
			if (i + dataDivisionFactor <= dataArray.size()) {
				List<Double> values  = dataArray.subList(i, (int) (i + dataDivisionFactor));
				averagedData.add(sum(values)/dataDivisionFactor);
			}
			else {
				List<Double> values = new ArrayList<Double>();
				for (int j = i; j < dataArray.size(); j++) {
					values.add(dataArray.get(i));
				}
				averagedData.add(sum(values)/(double) values.size());
			}
		}
		return averagedData;
	}
	
	/**
	 * Calculates a sum of a list of doubles. 
	 * @param values is a list of doubles 
	 * @return a double that represents the sum of the list of doubles.
	 */
    private double sum(List<Double> values) {
        double sum = 0;
        for (double val : values) {
            sum += val;
        }
        return sum;
    }
    
    /**
     * Returns the sum of all values in a list of double arrays. This is used
     * to calculate the average of all the numbers in the array and then average 
     * based on location in the array itself. So all numbers in the first place in
     * the array will be averaged and all numbers in the second etc.
     * @param values arraylist of arrays of doubles
     * @return double array that contains the average of each place in the array.
     */
    private double[] allSum(List<double[]> values) {
    	double[] avgReturn = new double[3];
        for (int i = 0; i < values.size(); i++) {
        	for (int j = 0; j < values.get(i).length; j++) {
        		avgReturn[j] += values.get(i)[j];
        	}
        }
        return avgReturn;
    }
    
    /*
     * Functions for adding the average values and the maximum minimum and average 
     * values.
     */
    
    public int[] getAvgSpeed() {
        return doubleListToIntArray(getAverageValues(this.speedStats));
    }
    
    public int[][] getSpeedStats(){
    	return doubleListToIntArrayOfArrays(this.speedStats);
    }
    
    public int[] getAvgSize() {
        return doubleListToIntArray(getAverageValues(this.sizeStats));
    }
    
    public int[][] getSizeStats(){
    	return doubleListToIntArrayOfArrays(this.sizeStats);
    }
    
    public int[] getAvgAge() {
        return doubleListToIntArray(getAverageValues(this.ageStats));
    }
    
    public int[][] getAgeStats(){
    	return doubleListToIntArrayOfArrays(this.ageStats);
    }
    
    public int[] getAvgScent() {
        return doubleListToIntArray(getAverageValues(this.scentStats));
    }
    
    public int[][] getScentStats(){
    	return doubleListToIntArrayOfArrays(this.scentStats);
    }

    public int[] getAvgEnergyCost() {
        return doubleListToIntArray(getAverageValues(this.energyCostStats));
    }
    
    public int[][] getEnergyCostStats() {
        return doubleListToIntArrayOfArrays(this.energyCostStats);
    }
    
    public int[] getTime() {
        return doubleListToIntArray(this.time);
    }
    
    public int[] getNrHerbivores() {
        return doubleListToIntArray(this.nrHerbivores);
    }
    
    public int[] getNrOmnivores() {
        return doubleListToIntArray(this.nrOmnivores);
    }
    
    public int[] getNrCarnivores() {
        return doubleListToIntArray(this.nrCarnivores);
    }
    
    public int[] getNrSpecies() {
        return doubleListToIntArray(this.nrSpecies);
    }
    
    /*
     * Functions for adding a value to the end of the list of a certain stat
     * or for adding an array of doubles that includes minimum maximum and average
     * values. 
     */

    public void setSpeedStats(double[] d) {
        this.speedStats.add(d);
    }

    public void setSizeStats(double[] d) {
        this.sizeStats.add(d);
    }

    public void setAgeStats(double[] d) {
        this.ageStats.add(d);
    }

    public void setScentStats(double[] d) {
        this.scentStats.add(d);
    }

    public void setEnergyCostStats(double[] d) {
        this.energyCostStats.add(d);
    }

    public void setNrOmnivores(double d) {
        this.nrOmnivores.add(d);
    }
    
    public void setNrCarnivores(double d) {
        this.nrCarnivores.add(d);
    }

    public void setNrHerbivores(double d) {
        this.nrHerbivores.add(d);
    }

    public void setNrSpecies(double i) {
        this.nrSpecies.add(i);
    }

    public void setTime(double i) {
        this.time.add(i);
    }
    
    public void setReduce(boolean b) {
        this.reduce = b;
    }
}