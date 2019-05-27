package simulation;

import java.util.ArrayList;
import java.util.Collections;

import genome.Genome;

public class SpeciePopulation {
	private final double MUTATION_CHANCE = 0.01;
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
		Genome genome = new Genome(s.getGenome().getPerfectGenes(), s.getGenome().getDNACode());
		int energy = s.halfEnergy();
		Species sCopy = null;
		if (this.type.equals("Carnivore")) {
			genome.mutateGenome(MUTATION_CHANCE);
			genome.setGeneValues();
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Carnivore(s.getxLoc(), s.getyLoc(),energy, genome);
			}
		}
		else if (this.type.equals("Herbivore")) {
			genome.mutateGenome(MUTATION_CHANCE);
			genome.setGeneValues();
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Herbivore(s.getxLoc(), s.getyLoc(),energy, genome);
			}
		}
		else if (this.type.equals("Omnivore")) {
			genome.mutateGenome(MUTATION_CHANCE);
			genome.setGeneValues();
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Omnivore(s.getxLoc(), s.getyLoc(),energy, genome);
			}
		}
		if(sCopy != null) {
			speciesList.add(sCopy);
		}
		else{
			diedSpecies += 1;
		}
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
