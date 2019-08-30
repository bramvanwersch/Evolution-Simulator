package gameobjects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class AutotrophPopulation extends Population{
	private ArrayList<AutotrophSpecies> speciesList;


	public AutotrophPopulation(Color color, String type, String name) {
		super(color, type, name);
		this.speciesList = new ArrayList<AutotrophSpecies>();
	}
	
	@Override
	public void nextTimePoint() {
		for (int i = 0; i < getNrSpecies(); i++) {
			getSpecies(i).nextTimePoint();
		}
		multiplySpecies();
		checkAliveSpecies();
	}

	
	public void addSpecies(AutotrophSpecies s) {
		this.speciesList.add(s);
	}
	
	@Override
	public int getNrSpecies() {
		return speciesList.size();
	}
	
	@Override
	public AutotrophSpecies getSpecies(int index) {
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
	protected void createOffspring(int index, boolean mutate) {
		Species s = speciesList.get(index);
		if (mutate) {
			s.halfEnergy();
		}
		AutotrophSpecies sCopy = null;
		if (getType().equals("Plant")) {
			sCopy = new Plant(s.getSize(), s.getMaxAge(), s.getEnergy());
		}
		if(sCopy != null) {
			speciesList.add(sCopy);
			addSpeciesData(sCopy, s.getNumber());
		}
	}

	@Override
	public void addSpeciesData(Species s, int prevNumber) {
		// TODO Auto-generated method stub
		
	}
}
