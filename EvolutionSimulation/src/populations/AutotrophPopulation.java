package populations;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import species.AutotrophSpecies;
import species.Plant;
import species.Species;

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
	
	public boolean isOverlapping(int x, int y) {
		for (Species s: speciesList) {
			if ((s.getxLoc() - 2 * s.getSize() < x && s.getxLoc() + 2 * s.getSize() > x) &&
				(s.getyLoc() - 2 * s.getSize() < y && s.getyLoc() + 2 * s.getSize() > y)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void addSpeciesData(Species s, int prevNumber) {
		// TODO Auto-generated method stub
		
	}
}
