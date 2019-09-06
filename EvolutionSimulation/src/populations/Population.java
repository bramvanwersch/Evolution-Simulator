package populations;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import genome.Genome;
import genome.PanGenome;
import species.Species;

/**
 * Class that holds the basic methods for each population. These methods
 * can be called on all populations.
 * @author Bram van Wersch
 */
public abstract class Population {
	//chance for base to mutate when multiplying
	private final double MUTATION_CHANCE = 0.01;
	//amount of species that have died
	private int diedSpecies;
	//instance of PopulationData for saving information over time 
	private PopulationData popData;
	private Color color;
	private String type;
	//Instance of PanGenome for saving genetic code of species for potential analasys
	private PanGenome panGenome;
	//String of information that is only written into a file every so often to make things
	//more lag friendly
	private String speciesData;
	//name as specified by user.
	private String name;
	
	/**
	 * Constructor that innitialises some data structures and sets some values
	 * @param color of the species as can be seen in the terrainPanel
	 * @param type of species for interactions within the ecosystem
	 * @param name as given by the player of the game to the species.
	 */
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
	
	/**
	 * Triggers methods that happen each update for each population
	 */
	public abstract void nextTimePoint();
	
	/**
	 * Calls the isCanMultiply function of each species of a population and creates
	 * offspring if true is returned
	 */
	public void multiplySpecies() {
		for (int i = 0; i < getNrSpecies(); i++) {
			if (getSpecies(i).isCanMultiply()) {
				createOffspring(i);
			}
		}
	}
	
	/**
	 * Return a species from the species list at a given index
	 * @param i is the index of the species
	 * @return the Species at the index
	 */
	public abstract Species getSpecies(int i);
	
	/**
	 * @return the number of species in the speciesList of each population
	 */
	public abstract int getNrSpecies();
	
	/**
	 * Shuffles the species in the speciesList to make sure that there is always a 
	 * different order for calling the update methods of each species
	 */
	public abstract void shuffleSpeciesList();

	/**
	 * Remove a species at a given index from the speciesList
	 * @param index of the species to be removed
	 */
	public abstract void removeSpecies(int index);
	
	/**
	 * Method for multiplying species meaning copying there genome but modifiyng 
	 * there genetic code based on a mutation chance.
	 * @param index of the species that has to be multiplied.
	 */
	public abstract void createOffspring(int index);
	
	/**
	 * Method for cloning species meaning creating exact copies of the attributes 
	 * and genome. This method is only used when innitialy creating multiple species.
	 * @param index of the species to be cloned
	 */
	public abstract void cloneOffspring(int index); 
	
	/**
	 * Returns the amount of species that died in this population
	 * @return number of species that died
	 */
	public int getDiedSpecies() {
		return diedSpecies;
	}
	
	/**
	 * Increase the amount of dead species by 1.
	 */
	public void addDiedSpiecies() {
		this.diedSpecies += 1;
	}
	
	/**
	 *Give the color of the species as an instance of the Color class
	 * @return a Color instance
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Gives a string of the type of species. So far the options are: Herbivore, Carnivore,
	 * Omnivore, Plant
	 * @return one of these 4 strings.
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Adds species data of species that was just born into a file. The data consists of
	 * the sequence of the genome and the connection to the ancestor. This is written
	 * in Fasta format. 
	 * TODO: this functionality is turned of to reduce lag.
	 * @param s Species that has died recently
	 * @param prevNumber The number of the species that created it
	 */
	public abstract void addSpeciesData(Species s, int prevNumber);

	/**
	 * Method for calling the isAlive method of each species of a population. If the energy
	 * of the species is 0 or below or his age is the max age or higher the species has died
	 * and is consequently removed.
	 */
	public void checkAliveSpecies() {
		for (int i = 0; i < getNrSpecies(); i++) {
			if (!getSpecies(i).isAlive()) {
				removeSpecies(i);
			}
		}
	}
	
	/**
	 * Give the name of the population as determend by the player.
	 * @return String of the name of the species
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * returns a string that contains information about newborn species up to a certain amount
	 * of characters until it is written into a string.
	 * @return a generaly very large string up to 100000 characters.
	 */
	public String getSpeciesData() {
		return speciesData;
	}
	
	/**
	 * Set the speciesData 
	 * @param s is the string of data for the species data to be set to.
	 */
	public void setSpeciesData(String s) {
		speciesData = s;
	}
	
	/**
	 * returns the final value MUTATION_CHANCE as is pre-determined
	 * TODO: make this a variable that the user can change but give proper warnings.
	 * @return small double that tells the chance of a base to change into one of the four 
	 * bases
	 */
	public double getMutationChance() {
		return MUTATION_CHANCE;
	}
	
	/**
	 * TODO: make this class relevant again and work on saving the data.
	 * @return
	 */
	public PanGenome getPanegenome() {
		return panGenome;
	}
	
	/**
	 * Instance of PopulationData that holds for every a second a time point of all attributes
	 * and some other values.
	 * @return
	 */
	public PopulationData getPopData() {
		return popData;
	}
	
/*
 * Methods for data class. These methods calculate max, min and average values for all species for a 
 * certain statistic.
 */
	/**
	 * Saves the average of the attributes of each species for this population. This
	 * allows a proper graph to be drawn. Everything is saved in the popData object.
	 * @param timeElapsed
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
	
	/**
	 * Returns an array containing the {average, min, max} for each attribute
	 * TODO: make this a bit more flexible and use an attribute name instead of an
	 * order of attributes
	 * @return array of arrays that contains this information for each attribute
	 */
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

	/**
	 * Calculates the average of a certain array of doubles
	 * @param attrArray is the array of doubles that has to be averaged
	 * @return a number presenting the average of the data array.
	 */
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
	
	/**
	 * Filter out the minimum and maximum value out of an array of doubles
	 * @param attrArray is the array of douvles that has to be averaged
	 * @return
	 */
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
