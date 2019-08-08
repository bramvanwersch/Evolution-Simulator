package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class OptionData {
	private ArrayList<String> types;
	private ArrayList<String> names;
	private ArrayList<Integer> noIndividuals;
	private ArrayList<Integer> sizes;
	private ArrayList<Integer> speeds;
	private ArrayList<Integer> maxAges;
	private ArrayList<Integer> scentRanges;
	private ArrayList<Color> colors;
	private ArrayList<Double> eatSizeFactors;
	private int foodSize;
	private int foodEnergy;

	public OptionData() {
		this.types = new ArrayList<String>();
		this.names = new ArrayList<String>();
		this.noIndividuals = new ArrayList<Integer>();
		this.sizes = new ArrayList<Integer>();
		this.speeds = new ArrayList<Integer>();
		this.maxAges = new ArrayList<Integer>();
		this.scentRanges = new ArrayList<Integer>();
		this.colors = new ArrayList<Color>();
		this.eatSizeFactors = new ArrayList<Double>();
		
	}
	
	public void addTypeList(String val) {
		types.add(val);
	}
	
	public void addNamesList(String val) {
		names.add(val);
	}
	
	public void addNoIndividualsList(int val) {
		noIndividuals.add(val);
	}
	
	public void addSizesList(int val) {
		sizes.add(val);
	}
	
	public void addSpeedsList(int val) {
		speeds.add(val);
	}
	
	public void addMaxAgesList(int val) {
		maxAges.add(val);
	}
	
	public void addScentRangesList(int val) {
		scentRanges.add(val);
	}
	
	public void addColorsList(Color val) {
		colors.add(val);
	}
	
	public void addEatSizeFactorsList(double val) {
		eatSizeFactors.add(val);
	}
	
	public int getFoodSize() {
		return foodSize;
	}

	public void setFoodSize(int foodSize) {
		this.foodSize = foodSize;
	}

	public int getFoodEnergy() {
		return foodEnergy;
	}

	public void setFoodEnergy(int foodEnergy) {
		this.foodEnergy = foodEnergy;
	}

	public String[] getTypes() {
		return types.toArray(new String[types.size()]);
	}

	public String[] getNames() {
		return names.toArray(new String[names.size()]);
	}

	public int[] getNoIndividuals() {
		return convertIntegers(noIndividuals);
	}

	public int[] getSizes() {
		return convertIntegers(sizes);
	}

	public int[] getSpeeds() {
		return convertIntegers(speeds);
	}

	public int[] getMaxAges() {
		return convertIntegers(maxAges);
	}

	public int[] getScentRanges() {
		return convertIntegers(scentRanges);
	}

	public Color[] getColors() {
		return colors.toArray(new Color[colors.size()]);
	}

	public double[] getEatSizeFactors() {
		return convertDoubles(eatSizeFactors);
	}

	private int[] convertIntegers(ArrayList<Integer> integers){
	    int[] ret = new int[integers.size()];
	    Iterator<Integer> iterator = integers.iterator();
	    for (int i = 0; i < ret.length; i++){
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}
	
	private double[] convertDoubles(ArrayList<Double> doubles) {
	    double[] ret = new double[doubles.size()];
	    Iterator<Double> iterator = doubles.iterator();
	    for (int i = 0; i < ret.length; i++){
	        ret[i] = iterator.next().doubleValue();
	    }
	    return ret;
	}
	
	
}
