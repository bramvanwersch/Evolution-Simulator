package simulation;

import java.util.ArrayList;
import java.util.Iterator;

public class Data {
	private ArrayList<Double> avgSpeed;
	private ArrayList<Double> avgSize;
	private ArrayList<Double> avgAge;
	private ArrayList<Double> avgScent;
	private ArrayList<Double> avgEnergyCost;
	private ArrayList<Double> nrHerbivores;
	private ArrayList<Double> nrOmnivores;
	private ArrayList<Double> nrCarnivores;
	private int time;
	
	public Data() {
		this.avgSpeed = new ArrayList<Double>(100);
		this.avgSize = new ArrayList<Double>(100);
		this.avgAge = new ArrayList<Double>(100);
		this.avgScent = new ArrayList<Double>(100);
		this.avgEnergyCost = new ArrayList<Double>(100);
		this.nrHerbivores = new ArrayList<Double>(100);
		this.nrOmnivores = new ArrayList<Double>(100);
		this.nrCarnivores = new ArrayList<Double>(100);
		this.time = 0;
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


	public void addTime() {
		this.time += 1;
	}

	
	


}
