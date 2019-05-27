package simulation;

import java.util.ArrayList;
import java.util.Collections;

public class SpeciePopulation {
	private final double MAX_MUTATION_FRACTION = 0.2;
	private ArrayList<Species> speciesList;
	private int diedSpecies;
	private int[] color;
	private String type;
	
	public SpeciePopulation(int[] color, String type) {
		this.speciesList = new ArrayList<Species>();
		this.diedSpecies = 0;
		this.color = color;
		this.type = type;
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
	
	public void multiplySpecies(int index) {
		Species s = speciesList.get(index);
		int energy = s.halfEnergy();
		Species sCopy = null;
		if (this.type.equals("Carnivore")) {
			sCopy = new Carnivore(mutateStat(s.getSize(), MAX_MUTATION_FRACTION),
					mutateStat(s.getSpeed(), MAX_MUTATION_FRACTION), s.getxLoc(), s.getyLoc(),
					mutateStat(s.getMaxAge(), MAX_MUTATION_FRACTION), mutateStat(s.getScentRange(), MAX_MUTATION_FRACTION),
					energy);
		}
		else if (this.type.equals("Herbivore")) {
			sCopy = new Herbivore(mutateStat(s.getSize(), MAX_MUTATION_FRACTION),
					mutateStat(s.getSpeed(), MAX_MUTATION_FRACTION), s.getxLoc(), s.getyLoc(),
					mutateStat(s.getMaxAge(), MAX_MUTATION_FRACTION), mutateStat(s.getScentRange(), MAX_MUTATION_FRACTION),
					energy);
		}
		else if (this.type.equals("Omnivore")) {
			sCopy = new Omnivore(mutateStat(s.getSize(), MAX_MUTATION_FRACTION),
					mutateStat(s.getSpeed(), MAX_MUTATION_FRACTION), s.getxLoc(), s.getyLoc(),
					mutateStat(s.getMaxAge(), MAX_MUTATION_FRACTION), mutateStat(s.getScentRange(), MAX_MUTATION_FRACTION),
					energy);
		}
		speciesList.add(sCopy);
	}
	
	private int mutateStat(int stat, double mutatorFraction) {
		double min = stat - stat * mutatorFraction;
		double max = stat + stat * mutatorFraction;
		return (int) Math.round(Math.random() * (max - min) + min);
	}
	
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

	public int[] getColor() {
		return this.color;
	}

	public String getType() {
		return this.type;
	}

}
