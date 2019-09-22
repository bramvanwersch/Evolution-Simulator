package populations;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import species.AutotrophSpecies;
import species.Plant;
import species.Species;

/**
 * Class that holds specific methods that need to happen on all 
 * autotropspecies or concern the manipulation of the list of species.
 * @author Bram van Wersch
 * @note this class is very similar to the HetrotrophSpecies class
 * this is because both classes do very similar things buyt need to
 * save differnt kinds of species
 */
public class AutotrophPopulation extends Population{
	//List of all species that can grow or shrink depending on survival of 
	//the species
	private ArrayList<AutotrophSpecies> speciesList;

	/**
	 * Constructor that creates the list of species that each population holds.
	 * @param color of the species as can be seen in the terrainPanel
	 * @param type of species for interactions within the ecosystem
	 * @param name as given by the player of the game to the species.
	 */
	public AutotrophPopulation(Color color, String type, String name) {
		super(color, type, name);
		this.speciesList = new ArrayList<AutotrophSpecies>();
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
	public void addSpecies(AutotrophSpecies s) {
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
	public AutotrophSpecies getSpecies(int index) {
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
		Species s = speciesList.get(index);
		s.divideEnergy();
		int[] xyLoc = s.getOfsetXYLoc();
		AutotrophSpecies sCopy = null;
		if (getType().equals("Plant") && !isOverlapping(xyLoc[0], xyLoc[1])) {
			sCopy = new Plant(xyLoc[0], xyLoc[1], s.getSize(), s.getMaxAge(), s.getEnergy());
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
		Species s = speciesList.get(index);
		AutotrophSpecies sCopy = null;
		if (getType().equals("Plant")) {
			sCopy = new Plant(s.getSize(), s.getMaxAge(), s.getEnergy());
		}
		if(sCopy != null) {
			sCopy.setXYLoc();
			speciesList.add(sCopy);
			addSpeciesData(sCopy, s.getNumber());
		}
	}
	
	/**
	 * Function that checks if a AutotrophSpecies is close to annother AutotropSpecies. This 
	 * is important to make sure that the plant population does not blow up to much in size 
	 * and creates to many objects when there is a low amount of HetrotrophSpecies. This is 
	 * also important to control the dynamic of HetrotrophSpecies and AutotrophSpecies, making 
	 * sure not to much of a jojo effect occurs.
	 * @param x location of the species to be created
	 * @param y location of the species to be created
	 * @return boolean depending if the spot is free for a species to grow in.
	 */
	public boolean isOverlapping(int x, int y) {
		for (Species s: speciesList) {
			if ((s.getxLoc() - 2 * s.getSize() < x && s.getxLoc() + 2 * s.getSize() > x) &&
				(s.getyLoc() - 2 * s.getSize() < y && s.getyLoc() + 2 * s.getSize() > y)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * TODO still needs proper implementation. See also HetrotrophSpecies for better explanation
	 * about functionality.
	 */
	@Override
	public void addSpeciesData(Species s, int prevNumber) {
		// TODO Auto-generated method stub
		
	}
}
