package gameobjects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import genome.Genome;

public class HetrotrophPopulation extends Population {
	private ArrayList<HetrotrophSpecies> speciesList;


	public HetrotrophPopulation(Color color, String type, String name) {
		super(color, type, name);
		this.speciesList = new ArrayList<HetrotrophSpecies>();
	}
	
	@Override
	public void nextTimePoint() {
		for (int i = 0; i < getNrSpecies(); i++) {
			getSpecies(i).nextTimePoint();
		}
		multiplySpecies();
		checkAliveSpecies();
	}
	
	
	public void addSpecies(HetrotrophSpecies s) {
		this.speciesList.add(s);
	}
	
	@Override
	public int getNrSpecies() {
		return speciesList.size();
	}
	
	@Override
	public Species getSpecies(int index) {
		return speciesList.get(index);
	}
	
	@Override
	public void removeSpecies(int index) {	
		speciesList.remove(index);
		addDiedSpiecies();
	}
	
	@Override
	public void shuffleSpeciesList() {
		Collections.shuffle(speciesList);
	}
	
	@Override
	public void createOffspring(int index, boolean mutation) {
		Species s = speciesList.get(index);
		Genome genome = new Genome(s.getGenome().getPerfectGenes(), s.getGenome().getDNACode());
		int energy = s.getEnergy();
		if (mutation) {
			genome.mutateGenome(getMutationChance());
			energy = s.halfEnergy();
		}
		genome.setGeneValues();
		HetrotrophSpecies sCopy = null;
		if (getType().equals("Carnivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Carnivore(s.getxLoc(), s.getyLoc(),energy, genome, speciesList.size() + getDiedSpecies() +1
						, s.getEatSizeFactor());
			}
		}
		else if (getType().equals("Herbivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Herbivore(s.getxLoc(), s.getyLoc(),energy, genome, speciesList.size() + getDiedSpecies() +1
						, s.getEatSizeFactor());
			}
		}
		else if (getType().equals("Omnivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Omnivore(s.getxLoc(), s.getyLoc(),energy, genome, speciesList.size() + getDiedSpecies() +1
						, s.getEatSizeFactor());
			}
		}
		if(sCopy != null) {
			speciesList.add(sCopy);
			addSpeciesData(sCopy, s.getNumber());
		}
		
	}

	@Override
	public void addSpeciesData(Species s, int prevNumber) {
		String data = getSpeciesData();
		if (getSpeciesData().isEmpty()) {
			for (String key : s.getGenome().getPerfectGenes().keySet()) {
				data += "<"+ key + "\n"+s.getGenome().DnaToAa(s.getGenome().getPerfectGenes().get(key).getSequence())+"\n";
			}
		}
		data += ">" +s.getNumber() +"<--"+ prevNumber + "\n" +s.getGenome().DnaToAa(s.getGenome().getDNACode())+"\n";
		setSpeciesData(data);
		if (getSpeciesData().length() > 100000) {
			getPanegenome().writeSpeciesInfo(getSpeciesData());
			setSpeciesData(" ");
		}
	}
}
