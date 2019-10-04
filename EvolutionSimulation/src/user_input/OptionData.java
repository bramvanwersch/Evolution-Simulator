package user_input;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class OptionData {

	private ArrayList<PopulationSettings> populationSettingList;
	private int plantSize;
	private int plantEnergy;

	public OptionData() {
		this.populationSettingList = new ArrayList<PopulationSettings>();
	}
	
	public void addPopulationSettings(PopulationSettings p) {
		populationSettingList.add(p);
	}
	
	public int getPopulationSettingSize() {
		return this.populationSettingList.size();
	}
	
	public PopulationSettings getPopulationSettings(int index) {
		return populationSettingList.get(index);
	}
	
	public int getPlantSize() {
		return plantSize;
	}

	public void setPlantSize(int foodSize) {
		this.plantSize = foodSize;
	}

	public int getPlantEnergy() {
		return plantEnergy;
	}

	public void setPlantEnergy(int foodEnergy) {
		this.plantEnergy = foodEnergy;
	}
	
}
