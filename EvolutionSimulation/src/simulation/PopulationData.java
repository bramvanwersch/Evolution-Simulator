package simulation;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PopulationData {
    private ArrayList<double[]> avgSpeed;
    private ArrayList<double[]> avgSize;
    private ArrayList<double[]> avgAge;
    private ArrayList<double[]> avgScent;
    private ArrayList<double[]> avgEnergyCost;
    private ArrayList<Double> nrHerbivores;
    private ArrayList<Double> nrOmnivores;
    private ArrayList<Double> nrCarnivores;
    private ArrayList<Double> time;
    private ArrayList<Double> nrSpecies;
    private double dataDivisionFactor;
    private boolean reduce;
    private String type;


    public PopulationData() {
        this.avgSpeed = new ArrayList<double[]>(100);
        this.avgSize = new ArrayList<double[]>(100);
        this.avgAge = new ArrayList<double[]>(100);
        this.avgScent = new ArrayList<double[]>(100);
        this.avgEnergyCost = new ArrayList<double[]>(100);
        this.nrHerbivores = new ArrayList<Double>(100);
        this.nrOmnivores = new ArrayList<Double>(100);
        this.nrCarnivores = new ArrayList<Double>(100);
        this.time = new ArrayList<Double>(100);
        this.nrSpecies = new ArrayList<Double>(100);
        this.reduce = false;
        dataDivisionFactor = 1;
    }
    
    public int[][] convertTripleDoubles(ArrayList<Double[]> doubles){
        //first reduce the data before converting to doubles
        if (this.reduce) {
            doubles = reduceTripleData(doubles);
        }
        int[][] ret = new int[doubles.size()][3];
        Iterator<Double[]> iterator = doubles.iterator();
        for (int i = 0; i < ret.length; i++){
            
            for(int i2= 0 ; i2 <ret[0].length ; i2++) {
            	ret[i][i2] = (int) Math.round(doubles.get(i)[i2]);
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
    
    private ArrayList<Double[]> reduceTripleData(ArrayList<Double[]> dataArray) {
        ArrayList<Double[]> averagedData = new ArrayList<Double[]>();
        for (int i = 0; i < dataArray.size(); i += dataDivisionFactor) {
            if (i + dataDivisionFactor <= dataArray.size()) {
                List<Double[]> values  = dataArray.subList(i, (int) (i + dataDivisionFactor));
                Double avgSum =  (double) 0;
                Double minSum = (double) 0;
                Double maxSum = (double) 0;
                for (int i2=0; i < values.size(); i++) {
                	avgSum += values.get(i2)[0];
                	minSum += values.get(i2)[1];
                	maxSum += values.get(i2)[2];
                }
                Double[] averagedDataUnit = {avgSum/dataDivisionFactor, minSum/dataDivisionFactor, maxSum/dataDivisionFactor};
                averagedData.add(averagedDataUnit);
            }
            else {
                List<Double[]> values = new ArrayList<Double[]>();
                for (int j = i; j < dataArray.size(); j++) {
                    values.add(dataArray.get(i));
                }
                Double avgSum =  (double) 0;
                Double minSum = (double) 0;
                Double maxSum = (double) 0;
                for (int i2=0; i < values.size(); i++) {
                	avgSum += values.get(i2)[0];
                	minSum += values.get(i2)[1];
                	maxSum += values.get(i2)[2];
                }
                Double[] averagedDataUnit = {avgSum/dataDivisionFactor, minSum/dataDivisionFactor, maxSum/dataDivisionFactor};
                averagedData.add(averagedDataUnit);
                //just to make sure this only happens once. That should be the case anyway but...
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
		System.out.println(firstOfTriple.size());
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
        return convertDoubles(getFirstOfTriples(this.avgSpeed));
    }


    public int[] getAvgSize() {
        return convertDoubles(getFirstOfTriples(this.avgSize));
    }


    public int[] getAvgAge() {
    	System.out.println("AvgAge");
    	System.out.println(this.avgSpeed.size());
        return convertDoubles(getFirstOfTriples(this.avgAge));
    }


    public int[] getAvgScent() {
        return convertDoubles(getFirstOfTriples(this.avgScent));
    }


    public int[] getAvgEnergyCost() {
        return convertDoubles(getFirstOfTriples(this.avgEnergyCost));
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
        this.avgSpeed.add(d);
    }


    public void setSizeStats(double[] d) {
        this.avgSize.add(d);
    }


    public void setAgeStats(double[] d) {
        this.avgAge.add(d);
    }


    public void setScentStats(double[] d) {
        this.avgScent.add(d);
    }


    public void setEnergyCostStats(double[] d) {
        this.avgEnergyCost.add(d);
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