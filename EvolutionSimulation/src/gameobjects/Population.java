package gameobjects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import genome.Genome;
import genome.PanGenome;

public abstract class Population {
	private final double MUTATION_CHANCE = 0.01;
	private int diedSpecies;
	private PopulationData popData;
	private Color color;
	private String type;
	private PanGenome panGenome;
	private String speciesData;
	private String name;
	
	public Population(Color color, String type, String name) {
		this.popData = new PopulationData();
		this.popData.setReduce(true);
		this.diedSpecies = 0;
		this.color = color;
		this.type = type;
		this.speciesData = "";
		this.name = name;
		this.panGenome = new PanGenome(this.type +"Data");
	}
	
	public abstract void nextTimePoint();
	
	public void multiplySpecies() {
		for (int i = 0; i < getNrSpecies(); i++) {
			if (getSpecies(i).isCanMultiply()) {
				createOffspring(i, true);
			}
		}
	}
	
	public abstract Species getSpecies(int i);

	public abstract int getNrSpecies();
	
	public abstract void shuffleSpeciesList();

	public abstract void removeSpecies(int index);

	protected abstract void createOffspring(int i, boolean b);

	public void cloneSpecies(int index) {
		createOffspring(index, false);
	}
	
	public int getDiedSpecies() {
		return diedSpecies;
	}
	
	public void addDiedSpiecies() {
		this.diedSpecies += 1;
	}

	public Color getColor() {
		return this.color;
	}

	public String getType() {
		return this.type;
	}
	public abstract void addSpeciesData(Species s, int prevNumber);

	public void checkAliveSpecies() {
		for (int i = 0; i < getNrSpecies(); i++) {
			if (!getSpecies(i).isAlive()) {
				removeSpecies(i);
			}
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSpeciesData() {
		return speciesData;
	}
	
	public void setSpeciesData(String s) {
		speciesData = s;
	}
	
	public double getMutationChance() {
		return MUTATION_CHANCE;
	}
	
	public PanGenome getPanegenome() {
		return panGenome;
	}
	
	public PopulationData getPopData() {
		return popData;
	}
	
	/*
 * Methods for data class. These methods calculate max, min and average values for all species for a 
 * certain statistic.
 */
	public void saveStatsData(int timeElapsed) {
		double[][] stats = getStats();
		popData.setSpeedStats(stats[0]);
		popData.setSizeStats(stats[1]);
		popData.setAgeStats(stats[2]);
		popData.setScentStats(stats[3]);
		popData.setEnergyCostStats(stats[4]);
		popData.setTime(timeElapsed/1000);
		popData.setNrSpecies(getNrSpecies());
	}
	
	public double[][] getStats() {
		double[][] valArray = new double[5][getNrSpecies()];
		for (int i = 0; i < getNrSpecies(); i++) {
			double[] attributes = getSpecies(i).getAttributeData();
			for (int j = 0; j < attributes.length; j++) {
				valArray[j][i] = attributes[j];
			}
		}
		double[][] finalArray = new double[5][];
		for (int k = 0; k < finalArray.length; k++) {
			double[] attribute = valArray[k];	
			int[] minMax = calcMinMax(attribute);
			finalArray[k] = new double[]{calcAvgAttribute(attribute), minMax[0], minMax[1]};
		}
		return finalArray;
	}

	public double calcAvgAttribute(double[] attrArray) {
		double total = 0;
		if (attrArray.length == 0) {
			return 0;
		}
		for(double arr : attrArray){
			total += arr;
		}
		return total/new Double(attrArray.length);
	}
	
	private int[] calcMinMax(double[] attrArray) {
		if (attrArray.length == 0) {
			return new int [] {0,0};
		}
		int[] minMax = {(int) attrArray[0],(int) attrArray[0]};
		for(int i = 0; i < getNrSpecies(); i++){
			if (attrArray[i] < minMax[0]) {
				minMax[0] = (int)attrArray[i];
			}
			else if (attrArray[i] > minMax[1]) {
				minMax[1] = (int)attrArray[i];
			}
		}
		return minMax;
	}
}
