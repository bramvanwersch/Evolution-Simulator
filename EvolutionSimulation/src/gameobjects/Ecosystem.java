package gameobjects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import environment.Environment;
import populations.AutotrophPopulation;
import populations.HetrotrophPopulation;
import populations.Population;
import populations.PopulationData;
import species.AutotrophSpecies;
import species.HetrotrophSpecies;
import species.Species;
import user_input.OptionData;
import user_input.AutotrophPopulationSettings;
import user_input.HetrotrophPopulationSettings;
import utility_functions.Utility;

/**
 * Class that holds the dependencies between populations and their environment.
 * It facilitates interactions between populations and there respective species.
 * Also facilitates changes in the environment depending on the species.
 * @author Bram van Wersch
 */
public class Ecosystem {
	private ArrayList<HetrotrophPopulation> hetrotrophPopulations;
	private ArrayList<AutotrophPopulation> autotrophPopulations;
	private int[] hetPopOrderSeed;
	private int[] autPopOrderSeed;
	private PopulationData averagePopData;
	private Environment environment;

	/**
	 * Constructor for the environment innitialises all the different populations
	 * @param options class instance of OptionData that holds all the preferences 
	 * concerning the options that where chosen.
	 */
	public Ecosystem(OptionData options) {
		this.environment = new Environment(new int[] {50,5000}, new int[] {50,5000}, new int[] {50,5000});
		this.hetrotrophPopulations = new ArrayList<HetrotrophPopulation>();
		this.autotrophPopulations = new ArrayList<AutotrophPopulation>();
		this.averagePopData = new PopulationData();
		this.averagePopData.setReduce(true);
		createPopulations(options);
		this.hetPopOrderSeed = createPopOrderSeed(hetrotrophPopulations.size());
		this.autPopOrderSeed = createPopOrderSeed(autotrophPopulations.size());
	}

	/**
	 * Container function for invoking methods that need to be updated every
	 * frame for each species in a population
	 */
	public void nextTimeStep() {
		
		for (int loc : hetPopOrderSeed) {
			Population sp =  hetrotrophPopulations.get(loc);
			sp.nextTimePoint();
		}
		for (int loc : autPopOrderSeed) {
			Population sp = autotrophPopulations.get(loc);
			sp.nextTimePoint();
		}
		environment.nextTimePoint();
		hetrotrophEating();
		autotrophEating();
		shuffleLists();
	}
	
	/**
	 * Method that creates an array that is the lenght of the popultion list that helps randomize the selection of
	 * populations by functions This has to be done this way to make sure the list of populations can be navigated
	 * at random but is not actualy shuffled to ensure that data collection is consistent
	 * @return an array of numbers that the length of the number of populations
	 */
	public int[] createPopOrderSeed(int numberOfPopulations) {
		int[] numberArray = new int[numberOfPopulations];
		for (int i = 0; i < numberOfPopulations; i++ ) {
			numberArray[i] = i;
		}
		return numberArray;
	}
	
	/**
	 * Creates a population for every population that was requested to be 
	 * created by the given options
	 * @param options class instance that contains the options of individual
	 * populations aswell as global options.
	 * TODO make sure that the hardcoded part is regulated by options.
	 */
	private void createPopulations(OptionData options) {
		for (int i = 0; i < options.getPopulationSettingSize(); i++) {
			if (options.getPopulationSettings(i).getPopulationType().equals("Hetrotroph")) {
				HetrotrophPopulation p = new HetrotrophPopulation((HetrotrophPopulationSettings) options.getPopulationSettings(i));
				hetrotrophPopulations.add(p);
			}
			else if (options.getPopulationSettings(i).getPopulationType().equals("Autotroph")) {
				AutotrophPopulation p = new AutotrophPopulation((AutotrophPopulationSettings) options.getPopulationSettings(i));
				autotrophPopulations.add(p);
			}
		}
//		// ABSOLUTELY HARDCODED AND NEEEEEDS TO GO BUT IS A TEMPORARY SOLUTION UNTIL SETTINGS ARE FIXED
//		AutotrophPopulation p = new AutotrophPopulation(new HetrotrophPopulationSettings("Plant","",100 ,5 ,1 ,20,1, Color.GREEN, 1.0));
//		autotrophPopulations.add(p);
	}

//	/**
//	 * Function to check for the closest herbivore that is smaller then the carnivore so scent movement can
//	 * be used to move towards it. 
//	 * @param s1: the carnivore.
//	 * @return the closest herbivore or null if no herbivore is in range of the scent.
//	 */
//	public Species checkCarnivoreScent(Species s1) {
//		Species closestHerbivore = null;
//		double lowestC = s1.getScentRange();
//		for (int i = 0; i < getAllHerbivores().size(); i++) {
//			Species s2 = getAllHerbivores().get(i);
//			//getting slope of triangle using pythagoras.
//			if (Math.sqrt(Math.pow(s1.getxLoc() - s2.getxLoc(), 2) + Math.pow(s1.getyLoc() - s2.getyLoc(), 2)) 
//					< lowestC && s2.getSize() < s1.getEatSizeFactor()* s1.getSize()) {
//				closestHerbivore = s2;
//				lowestC = Math.sqrt(Math.pow(s1.getxLoc() - s2.getxLoc(), 2) + Math.pow(s1.getyLoc() - s2.getyLoc(), 2)); 
//			}
//		}
//		return closestHerbivore;
//	}
	
	/**
	 * Regulates the eating behaviour of hetrotroph species. It executes a
	 * check for every possible candidate.
	 */
	private void hetrotrophEating() {
		for (int loc : hetPopOrderSeed) {
			HetrotrophPopulation sp =  hetrotrophPopulations.get(loc);
			String type = sp.getType();
			if (type.equals("Herbivore")) {
				for (int i = 0; i < getNrPopulations(); i++) {
					Population p = getPopulation(i);
					if (p.getType().equals("Plant")) {
						eatSpecies(sp, p);
					}
				}
			}
			else if (type.equals("Carnivore")) {
				for (int i = 0; i < getNrPopulations(); i++) {
					Population p = getPopulation(i);
					if (p.getType().equals("Herbivore")) {
						eatSpecies(sp, p);
					}
				}
			}
			else if (type.equals("Omnivore")) {
				for (int i = 0; i < getNrPopulations(); i++) {
					Population p = getPopulation(i);
					if (p.getType().equals("Herbivore")) {
						eatSpecies(sp, p);
					}
					else if (p.getType().equals("Plant")) {
						eatSpecies(sp, p);
					}
				}
			}
		}
	}
	
	/**
	 * Takes two populations and compares individual species against each other
	 * using the eat methods of individual species.
	 * TODO this would preferable be within a species or population class but is
	 * difficult because of other species need to be known. Also this function 
	 * does 2 things. It does the check AND the removal.
	 * @param predator is an instance of HetrotrophPopulation that holds all 
	 * species
	 * @param prey is an instance of Population that holds all potential prey
	 */
	private void eatSpecies(HetrotrophPopulation predator, Population prey) {
		for(int i = 0; i < predator.getNrSpecies(); i++) {
			for(int j = prey.getNrSpecies() - 1; j >= 0; j--){
				HetrotrophSpecies s1 = predator.getSpecies(i);
				Species s2 = prey.getSpecies(j);
				if (s1.eat(s2.getxLoc(), s2.getyLoc(), s2.getSize(), s2.getEnergyOnDeath())) {
					prey.removeSpecies(j);
				}
			}
		}
	}
	
	/**
	 * Regulates the eating of autotrophSpecies because it is drastically 
	 * different from that of hetrotrophspecies.
	 */
	private void autotrophEating() {
		for (int loc : autPopOrderSeed) {
			AutotrophPopulation p = autotrophPopulations.get(loc);
			for(int j = 0; j < p.getNrSpecies(); j++){
				AutotrophSpecies s = p.getSpecies(j);
				double fractionConsumed = s.eat(environment.getNutrientValues(s.getxLoc(), s.getyLoc()));
				environment.reduceNutrientValues(s.getxLoc(), s.getyLoc(), fractionConsumed);
			}
				
		}
	}
	
	/**
	 * Function that shuffles the species and food list making sure that checks that are biased by list order
	 * are less biased. Besided that a seed that is a list of integer locations is shuffled making sure the 
	 * populations are looped trough in a random order but dont change order
	 */
	private void shuffleLists() {
		for (int i = 0; i < getNrPopulations(); i++) {
			Population sp = getPopulation(i);
			sp.shuffleSpeciesList();
		}
		this.hetPopOrderSeed = Utility.shuffleArray(hetPopOrderSeed);
		this.autPopOrderSeed = Utility.shuffleArray(autPopOrderSeed);
	}
	
//	private boolean checkSpeciesPlacement(Species spec) {
//		for (Population sp : getPopulations()) {
//			for (int i = 0; i < sp.getNrSpecies(); i++ ) {
//				Species s = sp.getSpecies(i);
//				//check if the central point of the species just created is witin another species or not. if so move it.
//				if (s.getxLoc() - s.getSize() < spec.getxLoc() && s.getxLoc() +s.getSize() > spec.getxLoc() &&
//					s.getyLoc() - s.getSize() < spec.getyLoc() && s.getyLoc() +s.getSize() > spec.getyLoc()) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}
	
/*
 * Functions for returning lists of specific Hetrotroph species. These
 * methods are private to make sure that they are not used to manipulate
 * species
 */
	
	private ArrayList<Species> getAllCarnivores() {
		ArrayList<Species> specList = new ArrayList<Species>();
		for (int loc : hetPopOrderSeed) {
			Population sp =  hetrotrophPopulations.get(loc);
			if (sp.getType().equals("Carnivore")) {
				for (int i = 0; i < sp.getNrSpecies(); i++) {
					specList.add(sp.getSpecies(i));
				}
			}
		}
		return specList;
	}
	
	private ArrayList<Species> getAllOmnivores() {
		ArrayList<Species> specList = new ArrayList<Species>();
		for (int loc : hetPopOrderSeed) {
			Population sp =  hetrotrophPopulations.get(loc);
			if (sp.getType().equals("Omnivore")) {
				for (int i = 0; i < sp.getNrSpecies(); i++) {
					specList.add(sp.getSpecies(i));
				}
			}
		}
		return specList;
	}
	
	private ArrayList<Species> getAllHerbivores() {
		ArrayList<Species> specList = new ArrayList<Species>();
		for (int loc : hetPopOrderSeed) {
			Population sp =  hetrotrophPopulations.get(loc);
			if (sp.getType().equals("Herbivore")) {
				for (int i = 0; i < sp.getNrSpecies(); i++) {
					specList.add(sp.getSpecies(i));
				}
			}
		}
		return specList;
	}

/*
 * Methods for counting differnt hetrotroph species. Dead or alive.
 */
	public int getNrHerbivores() {
		return getAllHerbivores().size();
	}

	public int getNrCarnivores() {
		return getAllCarnivores().size();
	}

	public int getNrOmnivores() {
		return getAllOmnivores().size();
	}

	public int getNrHetrotrophSpecies() {
		int count = 0;
		for (Population sp: hetrotrophPopulations ) {
			count += sp.getNrSpecies();
		}
		return count;
	}
	
	public int getNrDeadHetrotrophSpecies() {
		int count = 0;
		for (Population sp: hetrotrophPopulations ) {
			count += sp.getDiedSpecies();
		}
		return count;
	}
	
	/**
	 * Returns the size of hetro and autotroph populations in total taken
	 * together.
	 * @return an integer that is the size of all populations together.
	 */
	public int getNrPopulations() {
		return hetrotrophPopulations.size() + autotrophPopulations.size();
	}
	
	/**
	 * Method for getting a specific population from a list. This construct
	 * is here to make sure the full lists of populations are not just passed 
	 * around
	 * @param index of the population to return 
	 * @return a population at the given index.
	 */
	public Population getPopulation(int index) {
		ArrayList<Population> allPops = new ArrayList<Population>();
		allPops.addAll(hetrotrophPopulations);
		allPops.addAll(autotrophPopulations);
		return allPops.get(index);
	}
	
	/**
	 * Returns the size of hetrotroph populations.
	 * @return an integer that is the size of all hetrotroph populations
	 */
	public int getNrHetrotrophPopulations() {
		return this.hetrotrophPopulations.size();
	}
	
	/**
	 * Method for getting a specific hetrotrophpopulation from a list. 
	 * This construct is here to make sure the full lists of 
	 * hetrotrophpopulations are not just passed around.
	 * @param index of the population to return 
	 * @return a population at the given index.
	 */
	public HetrotrophPopulation getHetrotrophPopulation(int index) {
		return this.hetrotrophPopulations.get(index);
	}
	
	private ArrayList<Population> getLivingPopulations() {
		ArrayList<Population> livingPopulations = new ArrayList<Population>();
		for (Population sp: hetrotrophPopulations) {
			if (sp.getNrSpecies() > 0) {
				livingPopulations.add(sp);
			}
		}
		return livingPopulations;
	}
	
	/**
	 * Function that gives the population data class that saves the average
	 * data for all populations
	 * @return Instance of PopulationData
	 */
	public PopulationData getAveragePopData() {
		return this.averagePopData;
	}
	
	/**
	 * Function for returning the environemnt
	 * @return Instance of environment class.
	 */
	public Environment getEnvironment() {
		return this.environment;
	}
	
	/**
	 * Returns the instance of the population with the most species. It 
	 * prioritises the population that comes first in the list and takes that
	 * over populations with the same amount of species later in the list with 
	 * populations.
	 * @return instance of Population.
	 */
	public Population getMaxSpeciesHetPopulations() {
		Population maxPopulation = hetrotrophPopulations.get(0);
		for (int i = 1; i < hetrotrophPopulations.size(); i++) {
			if (hetrotrophPopulations.get(i).getNrSpecies() > maxPopulation.getNrSpecies()) {
				maxPopulation = hetrotrophPopulations.get(i);
			}
		}
		return maxPopulation;
	}
	
	/**
	 * Returns the population with the minimum number of species. It 
	 * prioritises the population that comes first in the list and takes that
	 * over populations with the same amount of species later in the list with 
	 * populations.
	 * @return an instance of Population.
	 */
	public Population getMinSpeciesHetPopulations() {
		Population minPopulation = hetrotrophPopulations.get(0);
		for (int i = 1; i < hetrotrophPopulations.size(); i++) {
			if (hetrotrophPopulations.get(i).getNrSpecies() < minPopulation.getNrSpecies()) {
				minPopulation = hetrotrophPopulations.get(i);
			}
		}
		return minPopulation;
	}

/*
 * Data saving functions	
 */
	public void saveAveragePopulationsStatsData(int timeElapsed) {
		double[][] averageStats = getAveragePopulationStats();
		averagePopData.setNrHerbivores(getNrHerbivores());
		averagePopData.setNrOmnivores(getNrOmnivores());
		averagePopData.setNrCarnivores(getNrCarnivores());
		averagePopData.setSpeedStats(averageStats[0]);
		averagePopData.setSizeStats(averageStats[1]);
		averagePopData.setAgeStats(averageStats[2]);
		averagePopData.setScentStats(averageStats[3]);
		averagePopData.setEnergyCostStats(averageStats[4]);
		averagePopData.setTime(timeElapsed/1000);
	}
	
	/**
	 * Function that returns the average values for each population based on
	 * the values saved for individual species for each attribute.
	 * TODO min and max should not be calculated over the average but be the
	 * min and max of a certain species specifically.
	 * @return Array of Arrays of doubles containing the average min and max 
	 * for each attribute.
	 */
	public double[][] getAveragePopulationStats() {
		double[][] valArray = new double[5][getLivingPopulations().size()];
		
		for (int i = 0; i < getLivingPopulations().size(); i++) {
			double[][] attributes = getLivingPopulations().get(i).getStats();
			for (int j = 0; j < attributes.length; j++) {
				valArray[j][i] = attributes[j][0];
			}
		}
		double[][] finalArray = new double[5][];
		for (int k = 0; k < finalArray.length; k++) {
			double[] attribute = valArray[k];	
			double[] minMax = calcMinMax(attribute);
			finalArray[k] = new double[]{calcAvgAttribute(attribute), minMax[0], minMax[1]};
		}
		return finalArray;
	}
	
	/**
	 * Calculates the average of a certain array of doubles
	 * @param attrArray is the array of doubles that has to be averaged
	 * @return a number presenting the average of the data array.
	 */
	private double calcAvgAttribute(double[] attrArray) {
		double total = 0;
		for(double arr : attrArray){
			total += arr;
		}
		return total/new Double(attrArray.length);
	}

	/**
	 * Filter out the minimum and maximum value out of an array of doubles
	 * @param attrArray is the array of douvles that has to be averaged
	 * @return an array of 2 doubles first min then maximum
	 */
	private double[] calcMinMax(double[] attrArray) {
		double[] minMax = {attrArray[0], attrArray[0]};
		for(int i = 0; i < attrArray.length; i++){
			if (attrArray[i] < minMax[0]) {
				minMax[0] = attrArray[i];
			}
			else if (attrArray[i] > minMax[1]) {
				minMax[1] = attrArray[i];
			}
		}
		return minMax;
	}
}
