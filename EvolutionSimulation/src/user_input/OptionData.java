package user_input;

import java.util.ArrayList;

/**
 * Class that holds general settings and lists of settings for individual
 * populations.
 * @author Bram van Wersch
 */
public class OptionData {

	private ArrayList<PopulationSettings> populationSettingList;
	private int plantSize;
	private int plantEnergy;

	/**
	 * Innitialises an empty list for populationSettings Objects.
	 */
	public OptionData() {
		this.populationSettingList = new ArrayList<PopulationSettings>();
	}
	
	/*
	 * Functions for setting the PopulationSetting and some global values
	 */
	public void addPopulationSettings(PopulationSettings p) {
		populationSettingList.add(p);
	}

	public void setPlantSize(int foodSize) {
		this.plantSize = foodSize;
	}
	
	public void setPlantEnergy(int foodEnergy) {
		this.plantEnergy = foodEnergy;
	}
	
	/*
	 * Getters for returning information.
	 */
	public int getPlantSize() {
		return plantSize;
	}

	public int getPlantEnergy() {
		return plantEnergy;
	}
	
	public int getPopulationSettingSize() {
		return this.populationSettingList.size();
	}
	
	public PopulationSettings getPopulationSettings(int index) {
		return populationSettingList.get(index);
	}
}
