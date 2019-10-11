package user_input;

import java.awt.Color;

/**
 * Class for saving settings set by the user for an individual population
 * @author Bram van Wersch
 */
public class PopulationSettings {
	private String type;
	private String name;
	private int noIndividuals;
	private int size;
	private int speed;
	private int maxAge;
	private int scentRange;
	private Color color;
	private double eatSizeFactor;
	
	/**
	 * Construtor for the class for setting all of the population values.
	 * @param type of the population.
	 * @param name of the population.
	 * @param noIndividuals number of individuals for the population to start
	 * with
	 * @param size of all the species in the population at creation.
	 * @param speed of all the species in the population at creation.
	 * @param maxAge of all the species in the population at creation.
	 * @param scentRange of all the species in the population at creation.
	 * @param color of the population.
	 * @param eatSizeFactor of all the species in the population at creation.
	 */
	public PopulationSettings(String type, String name, int noIndividuals, int size,
			int speed, int maxAge, int scentRange, Color color, double eatSizeFactor) {
		this.type = type;
		this.name = name;
		this.noIndividuals = noIndividuals;
		this.size = size;
		this.speed = speed;
		this.maxAge = maxAge;
		this.scentRange = scentRange;
		this.color = color;
		this.eatSizeFactor = eatSizeFactor;
	}
	
	/*
	 * Functions for returning the individualy saved settings.
	 */
	public String getName() {
		return this.name;
	}

	public int getNoIndividuals() {
		return this.noIndividuals;
	}

	public int getSize() {
		return this.size;
	}

	public int getSpeed() {
		return this.speed;
	}

	public int getMaxAge() {
		return this.maxAge;
	}

	public int getScentRange() {
		return this.scentRange;
	}

	public Color getColor() {
		return this.color;
	}

	public double getEatSizeFactor() {
		return this.eatSizeFactor;
	}
	
	public String getType() {
		return this.type;
	}
	
	/**
	 * Function that determines if a population is autotroph or hetrotroph
	 * depening on the type of species the population contains.
	 * @return String that tells what type the population is.
	 */
	public String getPopulationType() {
		if (type.equals("Plant")) {
			return "Autotroph";
		}
		else {
			return "Hetrotroph";
		}
	}

	public int[] getMaxNutrientValues() {
		// TODO Auto-generated method stub
		return null;
	}
}
