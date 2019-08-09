package simulation;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * PopulationData class records one instance of population
 * @author wytze
 *
 */


public class PopulationData {
	private ArrayList<Double> avgSpeed;
	private ArrayList<Double> avgSize;
	private ArrayList<Double> avgAge;
	private ArrayList<Double> avgScent;
	private ArrayList<Double> avgEnergyCost;
	private ArrayList<Double> nrHerbivores;
	private ArrayList<Double> nrOmnivores;
	private ArrayList<Double> nrCarnivores;
	private int time;
	private ArrayList<Integer> nrSpecies;
	


	public PopulationData() {
		this.avgSpeed = new ArrayList<Double>(100);
		this.avgSize = new ArrayList<Double>(100);
		this.avgAge = new ArrayList<Double>(100);
		this.avgScent = new ArrayList<Double>(100);
		this.avgEnergyCost = new ArrayList<Double>(100);
		this.nrHerbivores = new ArrayList<Double>(100);
		this.nrOmnivores = new ArrayList<Double>(100);
		this.nrCarnivores = new ArrayList<Double>(100);
		this.time = 0;
		this.nrSpecies = new ArrayList<Integer>(100);
	}
	
	public int[] convertDoubles(ArrayList<Double> doubles){
	    int[] ret = new int[doubles.size()];
	    Iterator<Double> iterator = doubles.iterator();
	    for (int i = 0; i < ret.length; i++){
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}
	
	public int[] getTimeArray() {
		int [] timeArray= new int[this.time];
		for (int i=0; i < this.time; i++) {
			timeArray[i] = i;
		}
		return timeArray;
	}
	
	public int[][] getDataArray(){
		int[][] dataArray = new int[8][this.time];
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
	
	public int getTime() {
		return time;
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
	
	public ArrayList<Integer> getNrSpecies() {
		return nrSpecies;
	}

	public void setNrSpecies(int i) {
		this.nrSpecies.add(i);
	}


	public void addTime() {
		this.time += 1;
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
				+"\t"+Double.toString(this.getNrOmnivores()[i])+"\t"+Integer.toString(this.getNrSpecies().get(i));
			
			storage.append(row);
			
		}
		storage.append("\n");
		String string = storage.toString();
		System.out.println(string);
		return string;
	}

	
	


}
