package user_input;

import java.awt.Color;
import java.util.ArrayList;

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
	
	public String getPopulationType() {
		if (type == "Plant") {
			return "Autotroph";
		}
		else {
			return "Hetrotroph";
		}
	}
}
