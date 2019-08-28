package gameobjects;

import java.awt.Color;
import java.util.ArrayList;

public class AutotrophPopulation extends Population{
	private ArrayList<Plant> plantList;

	public AutotrophPopulation(Color color, String type, String name) {
		super(color, type, name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void nextTimePoint() {
		checkAliveSpecies();
	}


	@Override
	public void multiplySpecies() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSpeciesData(Species s, int prevNumber) {
		// TODO Auto-generated method stub
		
	}
	
	public int getNrPlant() {
		return plantList.size();
	}
	public Plant getPlant(int index) {
		return plantList.get(index);
	}
	public void removePlant(int index) {
		plantList.remove(index);
	}

	@Override
	public void cloneSpecies(int index) {
		// TODO Auto-generated method stub
		
	}
}
