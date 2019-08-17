package simulation;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PopulationData {
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
    
    public int[][] convertTripleDoubles(ArrayList<double[]> doubles){
        //first reduce the data before converting to doubles
        if (this.reduce) {
            doubles = reduceTripleData(doubles);
        }
        int[][] ret = new int[doubles.size()][3];
        Iterator<double[]> iterator = doubles.iterator();
        for (int i = 0; i < ret.length; i++){
            
            for(int j= 0 ; j <ret[0].length ; j++) {
            	ret[i][j] = (int) Math.round(doubles.get(i)[j]);
            }
        }
        return ret;
    }
    
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
    
    public void setDataDivisionFactor() {
        int divisionFactor = 1;
        int arrayLength = time.size();
        while (arrayLength/divisionFactor > 50){
            divisionFactor += 1;
        }
        dataDivisionFactor = divisionFactor;
    }
    /*This function reduces the amount of data saved by only saving the averafge data per datadivisionFactor
     * $edit Wytze, I've changed it so that it returns the average average, minimum and maximum over dataDivisionfactor
     * I hope you agree with how I've done it.
     * 
     */
    
    private ArrayList<double[]> reduceTripleData(ArrayList<double[]> dataArray) {
        ArrayList<double[]> averagedData = new ArrayList<double[]>();
        for (int i = 0; i < dataArray.size(); i += dataDivisionFactor) {
            if (i + dataDivisionFactor <= dataArray.size()) {
                List<double[]> values  = dataArray.subList(i, (int) (i + dataDivisionFactor));
                Double avgSum = 0.0;
                Double minSum = 0.0;
                Double maxSum = 0.0;
                for (int j=0; i < values.size(); i++) {
                	avgSum += values.get(j)[0];
                	minSum += values.get(j)[1];
                	maxSum += values.get(j)[2];
                }
                double[] averagedDataUnit = {avgSum/dataDivisionFactor, minSum/dataDivisionFactor, maxSum/dataDivisionFactor};
                averagedData.add(averagedDataUnit);
            }
            else {
                List<double[]> values = new ArrayList<double[]>();
                for (int k = i; k < dataArray.size(); k++) {
                    values.add(dataArray.get(i));
                }
                Double avgSum =  0.0;
                Double minSum = 0.0;
                Double maxSum = 0.0;
                for (int l=0; i < values.size(); i++) {
                	avgSum += values.get(l)[0];
                	minSum += values.get(l)[1];
                	maxSum += values.get(l)[2];
                }
                double[] averagedDataUnit = {avgSum/dataDivisionFactor, minSum/dataDivisionFactor, maxSum/dataDivisionFactor};
                averagedData.add(averagedDataUnit);
            }
        }
        return averagedData;
    }
	public int[] convertDoubles(ArrayList<Double> doubles){
		//first reduce the data before converting to doubles
		if (this.reduce) {
			doubles = reduceData(doubles);
		}
	    int[] ret = new int[doubles.size()];
	    Iterator<Double> iterator = doubles.iterator();
	    for (int i = 0; i < ret.length; i++){
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}
	private ArrayList<Double> getFirstOfTriples(ArrayList<double[]> dataArray){
		ArrayList<Double> firstOfTriple = new ArrayList<Double>(dataArray.size());
		for(int i= 0; i < dataArray.size(); i++) {
			firstOfTriple.add(dataArray.get(i)[0]);
		}
		return firstOfTriple;
	}
    
	private ArrayList<Double> reduceData(ArrayList<Double> dataArray) {
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
				//just to make sure this only happens once. That should be the case anyway but...
			}
		}
		return averagedData;
	}

    
    private double sum(List<Double> values) {
        double sum = 0;
        for (double val : values) {
            sum += val;
        }
        return sum;
    }
    
    public int[] getAvgSpeed() {
        return convertDoubles(getFirstOfTriples(this.speedStats));
    }
    
    public int[][] getSpeedStats(){
    	return convertTripleDoubles(this.speedStats);
    }
    
    public int[] getAvgSize() {
        return convertDoubles(getFirstOfTriples(this.sizeStats));
    }
    
    public int[][] getSizeStats(){
    	return convertTripleDoubles(this.sizeStats);
    }
    
    public int[] getAvgAge() {
        return convertDoubles(getFirstOfTriples(this.ageStats));
    }
    
    public int[][] getAgeStats(){
    	return convertTripleDoubles(this.ageStats);
    }
    
    public int[] getAvgScent() {
        return convertDoubles(getFirstOfTriples(this.scentStats));
    }
    public int[][] getScentStats(){
    	return convertTripleDoubles(this.scentStats);
    }

    public int[] getAvgEnergyCost() {
        return convertDoubles(getFirstOfTriples(this.energyCostStats));
    }
    
    public int[] getTime() {
        return convertDoubles(this.time);
    }
    
    public int[] getNrHerbivores() {
        return convertDoubles(this.nrHerbivores);
    }
    
    public int[] getNrOmnivores() {
        return convertDoubles(this.nrOmnivores);
    }
    
    public int[] getNrCarnivores() {
        return convertDoubles(this.nrCarnivores);
    }
    
    public int[] getNrSpecies() {
        return convertDoubles(this.nrSpecies);
    }


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
    public String getEatingPref() {
        String eatingPref = null;
        if(this.nrCarnivores.toString()!="[]") {
            eatingPref = "Carnivore";
        } else if (this.nrHerbivores.toString()!="[]") {
            eatingPref = "Herbivore";
        } else if (this.nrOmnivores.toString()!="[]") {
            eatingPref = "Omnivore";
        }
        return eatingPref;
    }

}