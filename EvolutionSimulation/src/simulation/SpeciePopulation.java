package simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import genome.Gene;
import genome.Genome;
import genome.PanGenome;

public class SpeciePopulation {
	private final double MUTATION_CHANCE = 0.01;
	private ArrayList<Species> speciesList;
	private int diedSpecies;
	private int[] color;
	private String type;
	private PanGenome panGenome;
	
	public SpeciePopulation(int[] color, String type) {
		this.speciesList = new ArrayList<Species>();
		this.diedSpecies = 0;
		this.color = color;
		this.type = type;
		this.panGenome = new PanGenome(this.type +"Data",this.type);
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
	
	public void multiplySpecies(int index, boolean mutation) {
		Species s = speciesList.get(index);
		Genome genome = new Genome(s.getGenome().getPerfectGenes(), s.getGenome().getDNACode());
		int energy = s.getEnergy();
		if (mutation) {
			genome.mutateGenome(MUTATION_CHANCE);
			energy = s.halfEnergy();
		}
		genome.setGeneValues();
		Species sCopy = null;
		if (this.type.equals("Carnivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Carnivore(s.getxLoc(), s.getyLoc(),energy, genome, speciesList.size() + diedSpecies +1);
			}
		}
		else if (this.type.equals("Herbivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Herbivore(s.getxLoc(), s.getyLoc(),energy, genome, speciesList.size() + diedSpecies +1);
			}
		}
		else if (this.type.equals("Omnivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Omnivore(s.getxLoc(), s.getyLoc(),energy, genome, speciesList.size() + diedSpecies +1);
			}
		}
		if(sCopy != null) {
			speciesList.add(sCopy);
			panGenome.writeSpeciesInfo(sCopy, s.getNumber());
		}
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
