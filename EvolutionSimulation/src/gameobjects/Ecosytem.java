package gameobjects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import environment.Environment;
import populations.AutotrophPopulation;
import populations.HetrotrophPopulation;
import populations.Population;
import populations.PopulationData;
import species.AutotrophSpecies;
import species.Carnivore;
import species.Herbivore;
import species.HetrotrophSpecies;
import species.Omnivore;
import species.Plant;
import species.Species;
import user_input.OptionData;

/**
 * Class that holds the dependencies between populations and their environment.
 * It facilitates interactions between populations and there respective species.
 * Also facilitates changes in the environment depending on the species.
 * @author Bram van Wersch
 */
public class Ecosytem {
	private ArrayList<HetrotrophPopulation> hetrotrophPopulations;
	private ArrayList<AutotrophPopulation> autotrophPopulations;
	private int[] popOrderSeed;
	private PopulationData averagePopData;
	private Environment environment;

	/**
	 * Constructor for the environment innitialises all the different populations
	 * and creates them aswell as the species in them
	 * @param options
	 */
	public Ecosytem(OptionData options) {
		this.environment = new Environment(new int[] {50,50}, new int[] {50,50}, new int[] {50,50});
		this.hetrotrophPopulations = new ArrayList<HetrotrophPopulation>();
		this.autotrophPopulations = new ArrayList<AutotrophPopulation>(); 
		this.popOrderSeed = createPopOrderSeed(options.getPopulationSettingSize());
		this.averagePopData = new PopulationData();
		this.averagePopData.setReduce(true);
		createHetrotrophPopulations(options);
		//Still hardcoded needs addition of poption panel data.
		createAutotrophPopulations(options);
		createAutotrophSpecies(100, options.getPlantSize(), 50, options.getPlantEnergy());
	}

	/**
	 * Container function for invoking methods that need to be updated every frame for each species in a population
	 */
	public void nextTimeStep() {
		
		for (int loc : popOrderSeed) {
			Population sp =  hetrotrophPopulations.get(loc);
			sp.nextTimePoint();
		}
		for (Population p : autotrophPopulations) {
			p.nextTimePoint();
		}
		hetrotrophEating();
		autotrophEating();
		shuffleLists();
	}
	
	/**
	 * Method that creates an array that is the lenght of the popultion list that helps rendomize the selection of
	 * populations by functions This has to be done this way to make sure populations are chosen at random but are not 
	 * actualy shuffled which leads to problems in the data collection.
	 * @return an array of numbers that the length of the number of populations
	 */
	private int[] createPopOrderSeed(int numberOfPopulations) {
		int[] numberArray = new int[numberOfPopulations];
		for (int i = 0; i < numberOfPopulations; i++ ) {
			numberArray[i] = i;
		}
		return shufflePopOrderSeed(numberArray);
	}

	
//	/**
//	 * Function to check for the closest carnivore that is bigger then the herbivore so scent movement can
//	 * be used to move away from it. 
//	 * @param s1: the herbivore.
//	 * @return the closest carnivore or null if no carnivore is in range of the scent.
//	 */
//	public Species checkHerbivoreScent(Species s1) {
//		Species closestCarnivore = null;
//		double lowestC = s1.getScentRange();
//		for (int i = 0; i < getAllCarnivores().size(); i++) {
//			Species s2 = getAllCarnivores().get(i);
//			//getting slope of triangle using pythagoras.
//			if (Math.sqrt(Math.pow(s1.getxLoc() - s2.getxLoc(), 2) + Math.pow(s1.getyLoc() - s2.getyLoc(), 2)) 
//					< lowestC && s2.getSize() > s1.getEatSizeFactor()* s1.getSize()) {
//				closestCarnivore = s2;
//				lowestC = Math.sqrt(Math.pow(s1.getxLoc() - s2.getxLoc(), 2) + Math.pow(s1.getyLoc() - s2.getyLoc(), 2)); 
//			}
//		}
//		return closestCarnivore;
//	}
//	
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
	
//	/**
//	 * Function for plant eaters to check if there bounding box is on top of a food object. If this is the 
//	 * case the food is consumed and the species gets energy
//	 */
//	public void eatPlants() {
//		for (int i = 0; i < getAllHerbivores().size() + getAllOmnivores().size(); i++) {
//			Species s = getAllPlantEaters().get(i);
//			for(int j = getNrPlant() - 1; j >= 0; j--) {
//				Plant f = getPlant(j);
//				if (s.eat(f.getxLoc(), f.getyLoc(), f.getSize(), f.getEnergy())) {
//					removePlant(j);
//				}
//			}
//		}	
//	}
	
	public void hetrotrophEating() {
		for (int loc : popOrderSeed) {
			HetrotrophPopulation sp =  hetrotrophPopulations.get(loc);
			String type = sp.getType();
			if (type.equals("Herbivore")) {
				for (Population p : getPopulations()) {
					if (p.getType().equals("Plant")) {
						eatSpecies(sp, p);
					}
				}
			}
			else if (type.equals("Carnivore")) {
				for (Population p : getPopulations()) {
					if (p.getType().equals("Herbivore")) {
						eatSpecies(sp, p);
					}
				}
			}
			else if (type.equals("Omnivore")) {
				for (Population p : getPopulations()) {
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
	 * Function for meat eaters to figure out if theire bounding box is on top of a herbivore. If this is
	 * the case the herbivore will be removed.
	 */
	public void eatSpecies(HetrotrophPopulation predator, Population prey) {
		for(int i = 0; i < predator.getNrSpecies(); i++) {
			for(int j = prey.getNrSpecies() - 1; j >= 0; j--){
				HetrotrophSpecies s1 = predator.getSpecies(i);
				Species s2 = prey.getSpecies(j);
				if (s1.eat(s2.getxLoc(), s2.getyLoc(), s2.getSize(), s2.getEnergy())) {
					prey.removeSpecies(j);
				}
			}
		}
	}
	
	public void autotrophEating() {
		for (AutotrophPopulation p : autotrophPopulations) {
			for(int j = 0; j < p.getNrSpecies(); j++){
				AutotrophSpecies s = p.getSpecies(j);
				s.eat(environment.getNutrientValues(s.getxLoc(), s.getyLoc()));
			}
				
		}
	}
	
	/**
	 * Function that shuffles the species and food list making sure that checks that are biased by list order
	 * are less biased. Besided that a seed that is a list of integer locations is shuffled making sure the popultions
	 * are looped trough in a random order but dont change order
	 */
	public void shuffleLists() {
		for (Population sp: getPopulations()) {
			sp.shuffleSpeciesList();
		}
		this.popOrderSeed = shufflePopOrderSeed(popOrderSeed);
	}
	
	private int[] shufflePopOrderSeed(int[] ar) {
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--){
			int index = rnd.nextInt(i + 1);
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
		return ar;
	}

	private void createHetrotrophPopulations(OptionData options) {
		for (int i = 0; i < options.getPopulationSettingSize(); i++) {
			if (options.getPopulationSettings(i).getPopulationType().equals("Hetrotroph")) {
				HetrotrophPopulation p = new HetrotrophPopulation(options.getPopulationSettings(i));
				hetrotrophPopulations.add(p);
			}
		}
	}

	private void createAutotrophPopulations(OptionData options) {
		for (int i = 0; i < options.getPopulationSettingSize(); i++) {
			if (options.getPopulationSettings(i).getPopulationType().equals("Autotroph")) {
				AutotrophPopulation p = new AutotrophPopulation(options.getPopulationSettings(i));
				autotrophPopulations.add(p);
			}
		}
	}

	private void createAutotrophSpecies(int nrSpecies, int size, int maxAge, int energy) {
		for (int i = 0; i < autotrophPopulations.size(); i++) {
			AutotrophPopulation p = autotrophPopulations.get(i);
			for (int j = 0; j < nrSpecies; j++) {
				AutotrophSpecies s = null;
				if (p.getType().equals("Plant")) {
					if (p.getNrSpecies() == 0) {
						s = new Plant(size, maxAge, energy);
					}
				}
				if (s == null){
					p.cloneOffspring(p.getNrSpecies()-1);
				}
				else{
					p.addSpecies(s);
				}
			}
		}
	}
	
	private boolean checkSpeciesPlacement(Species spec) {
		for (Population sp : getPopulations()) {
			for (int i = 0; i < sp.getNrSpecies(); i++ ) {
				Species s = sp.getSpecies(i);
				//check if the central point of the species just created is witin another species or not. if so move it.
				if (s.getxLoc() - s.getSize() < spec.getxLoc() && s.getxLoc() +s.getSize() > spec.getxLoc() &&
					s.getyLoc() - s.getSize() < spec.getyLoc() && s.getyLoc() +s.getSize() > spec.getyLoc()) {
					return false;
				}
			}
		}
		return true;
	}
	
	private ArrayList<Species> getAllCarnivores() {
		ArrayList<Species> specList = new ArrayList<Species>();
		for (int loc : popOrderSeed) {
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
		for (int loc : popOrderSeed) {
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
		for (int loc : popOrderSeed) {
			Population sp =  hetrotrophPopulations.get(loc);
			if (sp.getType().equals("Herbivore")) {
				for (int i = 0; i < sp.getNrSpecies(); i++) {
					specList.add(sp.getSpecies(i));
				}
			}
		}
		return specList;
	}

// metthods for getting any amount of species
	public int getNrHerbivores() {
		return getAllHerbivores().size();
	}

	public int getNrCarnivores() {
		return getAllCarnivores().size();
	}

	public int getNrOmnivores() {
		return getAllOmnivores().size();
	}

	public int getNrSpecies() {
		int count = 0;
		for (Population sp: hetrotrophPopulations ) {
			count += sp.getNrSpecies();
		}
		return count;
	}
	
	public int getAllDeadSpecies() {
		int count = 0;
		for (Population sp: hetrotrophPopulations ) {
			count += sp.getDiedSpecies();
		}
		return count;
	}
	
	public ArrayList<Population> getPopulations() {
		ArrayList<Population> allPops = new ArrayList<Population>();
		allPops.addAll(hetrotrophPopulations);
		allPops.addAll(autotrophPopulations);
		return allPops;
	}
	
	public ArrayList<Population> getHetrotrophPopulations() {
		ArrayList<Population> hetroPops = new ArrayList<Population>();
		hetroPops.addAll(hetrotrophPopulations);
		return hetroPops;
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
	
	public Population getMaxNrSpeciesPop() {
		Population maxPopulation = hetrotrophPopulations.get(0);
		for (int i = 1; i < hetrotrophPopulations.size(); i++) {
			if (hetrotrophPopulations.get(i).getNrSpecies() > maxPopulation.getNrSpecies()) {
				maxPopulation = hetrotrophPopulations.get(i);
			}
		}
		return maxPopulation;
	}
	public Population getMinNrSpeciesPop() {
		Population minPopulation = hetrotrophPopulations.get(0);
		for (int i = 1; i < hetrotrophPopulations.size(); i++) {
			if (hetrotrophPopulations.get(i).getNrSpecies() < minPopulation.getNrSpecies()) {
				minPopulation = hetrotrophPopulations.get(i);
			}
		}
		return minPopulation;
	}
	
	public PopulationData[] getAllPopData() {
		PopulationData[] popDataArray = new PopulationData[hetrotrophPopulations.size()];
		for (int i = 0; i < hetrotrophPopulations.size(); i++) {
			popDataArray[i] = hetrotrophPopulations.get(i).getPopData();
		}
		return popDataArray;
	}
	
	public PopulationData getAveragePopData() {
		return this.averagePopData;
	}
	
	public Environment getEnvironment() {
		return this.environment;
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
			int[] minMax = calcMinMax(attribute);
			finalArray[k] = new double[]{calcAvgAttribute(attribute), minMax[0], minMax[1]};
		}
		return finalArray;
	}
	

	public double calcAvgAttribute(double[] attrArray) {
		double total = 0;
		for(double arr : attrArray){
			total += arr;
		}
		return total/new Double(attrArray.length);
	}
	
	private int[] calcMinMax(double[] attrArray) {
		int[] minMax = {(int) attrArray[0],(int) attrArray[0]};
		for(int i = 0; i < attrArray.length; i++){
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
