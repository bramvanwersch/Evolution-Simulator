package gameobjects;

import java.awt.Color;
import java.util.ArrayList;

public class AutotrophPopulation extends Population{

	public AutotrophPopulation(Color color, String type, String name) {
		super(color, type, name);
	}
	
	@Override
	protected void createOffspring(int index, boolean mutate) {
		Species s = getSpeciesList().get(index);
		Species sCopy = null;
		if (getType().equals("Plant")) {
			sCopy = new Plant(s.getSize(), s.getMaxAge(), s.getEnergy());
		}
		if(sCopy != null) {
			getSpeciesList().add(sCopy);
			addSpeciesData(sCopy, s.getNumber());
		}
	}
	
	@Override
	public void nextTimePoint() {
		checkAliveSpecies();
	}

	@Override
	public void addSpeciesData(Species s, int prevNumber) {
		// TODO Auto-generated method stub
		
	}
}
