package simulation;

import java.awt.Color;

import genome.Genome;

public class AnimalPopulation extends Population {

	public AnimalPopulation(Color color, String type, String name) {
		super(color, type, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkCanMultiply() {
		for (int i = 0; i < getNrSpecies(); i++) {
			if (getSpecies(i).isCanMultiply()) {
				multiplySpecies(i, true);
			}
		}
	}

	@Override
	public void multiplySpecies(int index, boolean mutation) {
		Species s = getSpeciesList().get(index);
		Genome genome = new Genome(s.getGenome().getPerfectGenes(), s.getGenome().getDNACode());
		int energy = s.getEnergy();
		if (mutation) {
			genome.mutateGenome(getMutationChance());
			energy = s.halfEnergy();
		}
		genome.setGeneValues();
		Species sCopy = null;
		if (getType().equals("Carnivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Carnivore(s.getxLoc(), s.getyLoc(),energy, genome, getSpeciesList().size() + getDiedSpecies() +1
						, s.getEatSizeFactor());
			}
		}
		else if (getType().equals("Herbivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Herbivore(s.getxLoc(), s.getyLoc(),energy, genome, getSpeciesList().size() + getDiedSpecies() +1
						, s.getEatSizeFactor());
			}
		}
		else if (getType().equals("Omnivore")) {
			if (genome.isSpeciesSurvivable()) {
				sCopy = new Omnivore(s.getxLoc(), s.getyLoc(),energy, genome, getSpeciesList().size() + getDiedSpecies() +1
						, s.getEatSizeFactor());
			}
		}
		if(sCopy != null) {
			getSpeciesList().add(sCopy);
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
