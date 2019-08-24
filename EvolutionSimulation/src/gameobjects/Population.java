package gameobjects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import genome.Genome;
import genome.PanGenome;

public abstract class Population {
	private final double MUTATION_CHANCE = 0.01;
	private ArrayList<Species> speciesList;
	private int diedSpecies;
	private PopulationData popData;
	private Color color;
	private String type;
	private PanGenome panGenome;
	private String speciesData;
	private String name;
	
	public Population(Color color, String type, String name) {
		this.speciesList = new ArrayList<Species>();
		this.popData = new PopulationData();
		this.popData.setReduce(true);
		this.diedSpecies = 0;
		this.color = color;
		this.type = type;
		this.speciesData = "";
		this.name = name;
		this.panGenome = new PanGenome(this.type +"Data");
	}
	
	public void addSpecies(Species s) {
		this.speciesList.add(s);
	}
	
	public int getNrSpecies() {
		return speciesList.size();
	}
	
	public Species getSpecies(int index) {
		return speciesList.get(index);
	}
	
	public abstract void checkCanMultiply();
	
	public abstract void multiplySpecies(int index, boolean mutation);

	public void removeSpecies(int index) {	
		speciesList.remove(index);
		diedSpecies += 1;
	}
	
	public int getDiedSpecies() {
		return diedSpecies;
	}

	public void shuffleSpeciesList() {
		Collections.shuffle(speciesList);
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
			if (getSpecies(i).getEnergy() <= 0) {
				removeSpecies(i);
			}
		}
	}
	
	public ArrayList<Species> getSpeciesList() {
		return this.speciesList;
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
			double[] attributes = speciesList.get(i).getAttributeData();
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
