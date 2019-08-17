package simulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PopulationData {
	private ArrayList<Double> avgSpeed;
	private ArrayList<Double> avgSize;
	private ArrayList<Double> avgAge;
	private ArrayList<Double> avgScent;
	private ArrayList<Double> avgEnergyCost;
	private ArrayList<Double> nrHerbivores;
	private ArrayList<Double> nrOmnivores;
	private ArrayList<Double> nrCarnivores;
	private ArrayList<Double> time;
	private ArrayList<Double> nrSpecies;
	private double dataDivisionFactor;
	private boolean reduce;
	private String type;

	public PopulationData() {
		this.avgSpeed = new ArrayList<Double>(100);
		this.avgSize = new ArrayList<Double>(100);
		this.avgAge = new ArrayList<Double>(100);
		this.avgScent = new ArrayList<Double>(100);
		this.avgEnergyCost = new ArrayList<Double>(100);
		this.nrHerbivores = new ArrayList<Double>(100);
		this.nrOmnivores = new ArrayList<Double>(100);
		this.nrCarnivores = new ArrayList<Double>(100);
		this.time = new ArrayList<Double>(100);
		this.nrSpecies = new ArrayList<Double>(100);
		this.reduce = false;
		dataDivisionFactor = 1;
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
		return convertDoubles(this.avgSpeed);
	}

	public int[] getAvgSize() {
		return convertDoubles(this.avgSize);
	}

	public int[] getAvgAge() {
		return convertDoubles(this.avgAge);
	}

	public int[] getAvgScent() {
		return convertDoubles(this.avgScent);
	}

	public int[] getAvgEnergyCost() {
		return convertDoubles(this.avgEnergyCost);
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

	public void setAvgSpeed(double d) {
		this.avgSpeed.add(d);
	}

	public void setAvgSize(double d) {
		this.avgSize.add(d);
	}

	public void setAvgAge(double d) {
		this.avgAge.add(d);
	}

	public void setAvgScent(double d) {
		this.avgScent.add(d);
	}

	public void setAvgEnergyCost(double d) {
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
	//This method makes a string matrix of all arrays in this list as columns
	//
	//
	public String getMatrixString() {
		StringBuilder storage = new StringBuilder();
		int length = this.getAvgSpeed().length;

		for(int i=0; i < length ; i++) {

			String row = Double.toString(this.getAvgAge()[i])+"\t"+Double.toString(this.getAvgEnergyCost()[i])+"\t"+Double.toString(this.getAvgScent()[i])
				+"\t"+Double.toString(this.getAvgSize()[i])+"\t"+Double.toString(this.getAvgSpeed()[i])
				+"\t"+Double.toString(this.getNrCarnivores()[i])+"\t"+Double.toString(this.getNrHerbivores()[i])
				+"\t"+Double.toString(this.getNrOmnivores()[i])+"\t"+Double.toString(this.getNrSpecies()[i]);
			
			storage.append(row);
			
		}
		storage.append("\n");
		String string = storage.toString();
		return string;
	}

	
	


}
