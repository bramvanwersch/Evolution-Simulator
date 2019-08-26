package gameobjects;

import java.awt.Color;
import java.util.ArrayList;

public class PlantPopulation extends Population{
	private ArrayList<Plant> plantList;

	public PlantPopulation(Color color, String type, String name) {
		super(color, type, name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void nextTimePoint() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void multiplySpecies() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copySpecies(int index, boolean mutation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSpeciesData(Species s, int prevNumber) {
		// TODO Auto-generated method stub
		
	}
	public void addPlants(int nrPlants, int plantEnergy, int plantSize) {
		for (int i =0 ; i < nrPlants; i++) {
			System.out.println("plants added to a single population");
			plantList.add(new Plant(plantEnergy, plantSize));
		}
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
}
