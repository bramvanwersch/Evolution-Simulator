package populations;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import genome.Genome;
import species.Carnivore;
import species.Herbivore;
import species.HetrotrophSpecies;
import species.Omnivore;
import species.Species;

/**
 * Class that holds specific methods that need to happen on all 
 * hetrotropspecies or concern the manipulation of the list of species.
 * @author Bram van Wersch
 * @note this class is very similar to the HetrotrophSpecies class
 * this is because both classes do very similar things buyt need to
 * save differnt kinds of species
 */
public class HetrotrophPopulation extends Population {
	//List of all species that can grow or shrink depending on survival of 
	//the species
	private ArrayList<HetrotrophSpecies> speciesList;

	/**
	 * Constructor that creates the list of species that each population holds.
	 * @param color of the species as can be seen in the terrainPanel
	 * @param type of species for interactions within the ecosystem
	 * @param name as given by the player of the game to the species.
	 */
	public HetrotrophPopulation(Color color, String type, String name) {
		super(color, type, name);
		this.speciesList = new ArrayList<HetrotrophSpecies>();
	}
	
	/**
	 * Triggers merhods that happen each update of the simulation. It triggers
	 * the nextTimePoint methods for all species aswell as a check for multiplying 
	 * and ability of the species to survive.
	 */
	@Override
	public void nextTimePoint() {
		for (int i = 0; i < getNrSpecies(); i++) {
			getSpecies(i).nextTimePoint();
		}
		multiplySpecies();
		checkAliveSpecies();
	}
	
	/**
	 * Adds a species to the end of the speciesList
	 * @param s is the instance of a hetrotrophspecies to be added to the list.
	 */
	public void addSpecies(HetrotrophSpecies s) {
		this.speciesList.add(s);
	}
	
	/**
	 * Return the size of the species list
	 */
	@Override
	public int getNrSpecies() {
		return speciesList.size();
	}
	
	/**
	 * Returns a species at a certain index of the list. This construct
	 * tries to prevent the user handelig the full list but instead the 
	 * individual species.
	 * @param index of the species to be returned
	 */
	@Override
	public HetrotrophSpecies getSpecies(int index) {
		return speciesList.get(index);
	}
	
	/**
	 * Removes a species from a certain index
	 * @param index of the species to be removed
	 */
	@Override
	public void removeSpecies(int index) {	
		speciesList.remove(index);
		addDiedSpiecies();
	}
	
	/**
	 * Shuffles the list of species making sure that the order in which
	 * the species are processed is different for every update
	 */
	@Override
	public void shuffleSpeciesList() {
		Collections.shuffle(speciesList);
	}
	
	/**
	 * Function that creates offspring by taking a species at a certain index and 
	 * taking its genome and mutate it. After that the values for the attributes are 
	 * slightly changed. Also the location is slightly changed.
	 * @param index of the species to be multiplied.
	 */
	@Override
	public void createOffspring(int index) {
		HetrotrophSpecies s = speciesList.get(index);
		Genome genome = new Genome(s.getGenome().getPerfectGenes(), s.getGenome().getDNACode());
		genome.mutateGenome(getMutationChance());
		s.divideEnergy();
		genome.setGeneValues();
		int[] xyLoc = s.getOfsetXYLoc();
		HetrotrophSpecies sCopy = null;
		if (getType().equals("Carnivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Carnivore(xyLoc[0], xyLoc[1], s.getEnergy(), genome, speciesList.size() + getDiedSpecies() +1
						, s.getEatSizeFactor());
			}
		}
		else if (getType().equals("Herbivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Herbivore(xyLoc[0], xyLoc[1], s.getEnergy(), genome, speciesList.size() + getDiedSpecies() +1
						, s.getEatSizeFactor());
			}
		}
		else if (getType().equals("Omnivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Omnivore(xyLoc[0], xyLoc[1], s.getEnergy(), genome, speciesList.size() + getDiedSpecies() +1
						, s.getEatSizeFactor());
			}
		}
		if(sCopy != null) {
			speciesList.add(sCopy);
			addSpeciesData(sCopy, s.getNumber());
		}
		
	}
	
	/**
	 * Clones a species at a given index by copying the genome. The starting location
	 * of the clones spcies is completely random within the bounds of the playing field.
	 * @param index of the species to be cloned.
	 */
	@Override
	public void cloneOffspring(int index) {
		HetrotrophSpecies s = speciesList.get(index);
		Genome genome = new Genome(s.getGenome().getPerfectGenes(), s.getGenome().getDNACode());
		genome.setGeneValues();
		HetrotrophSpecies sCopy = null;
		if (getType().equals("Carnivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Carnivore(s.getxLoc(), s.getyLoc(), s.getEnergy(), genome, speciesList.size() + getDiedSpecies() +1
						, s.getEatSizeFactor());
			}
		}
		else if (getType().equals("Herbivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Herbivore(s.getxLoc(), s.getyLoc(), s.getEnergy(), genome, speciesList.size() + getDiedSpecies() +1
						, s.getEatSizeFactor());
			}
		}
		else if (getType().equals("Omnivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Omnivore(s.getxLoc(), s.getyLoc(), s.getEnergy(), genome, speciesList.size() + getDiedSpecies() +1
						, s.getEatSizeFactor());
			}
		}
		if(sCopy != null) {
			sCopy.setXYLoc();
			speciesList.add(sCopy);
			addSpeciesData(sCopy, s.getNumber());
		}
		
	}

	/**
	 * Needs some work to work again. Was a function for recording the data of species.
	 */
	@Override
	public void addSpeciesData(Species s, int prevNumber) {
//		String data = getSpeciesData();
//		if (getSpeciesData().isEmpty()) {
//			for (String key : s.getGenome().getPerfectGenes().keySet()) {
//				data += "<"+ key + "\n"+s.getGenome().DnaToAa(s.getGenome().getPerfectGenes().get(key).getSequence())+"\n";
//			}
//		}
//		data += ">" +s.getNumber() +"<--"+ prevNumber + "\n" +s.getGenome().DnaToAa(s.getGenome().getDNACode())+"\n";
//		setSpeciesData(data);
//		if (getSpeciesData().length() > 100000) {
//			getPanegenome().writeSpeciesInfo(getSpeciesData());
//			setSpeciesData(" ");
//		}
	}
}
