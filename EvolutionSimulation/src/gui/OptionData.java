package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class OptionData {
	ArrayList<String> types;
	ArrayList<String> names;
	ArrayList<Integer> noIndividuals;
	ArrayList<Integer> sizes;
	ArrayList<Integer> speeds;
	ArrayList<Integer> maxAges;
	ArrayList<Integer> scentRange;
	ArrayList<Color> colors;
	ArrayList<Integer> eatSizeFactor;
	
	public OptionData() {
		this.types = new ArrayList<String>();
		this.names = new ArrayList<String>();
		this.noIndividuals = new ArrayList<Integer>();
		this.sizes = new ArrayList<Integer>();
		this.speeds = new ArrayList<Integer>();
		this.maxAges = new ArrayList<Integer>();
		this.scentRange = new ArrayList<Integer>();
		this.colors = new ArrayList<Color>();
		this.eatSizeFactor = new ArrayList<Integer>();
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
	
	public void addScentRangeList(int val) {
		scentRange.add(val);
	}
	
	public void addColorsList(Color val) {
		colors.add(val);
	}
	
	public void addEatSizeFactorList(int val) {
		eatSizeFactor.add(val);
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

	public int[] getScentRange() {
		return convertIntegers(scentRange);
	}

	public Color[] getColors() {
		return colors.toArray(new Color[colors.size()]);
	}

	public int[] getEatSizeFactor() {
		return convertIntegers(eatSizeFactor);
	}

	private  int[] convertIntegers(ArrayList<Integer> integers)
	{
	    int[] ret = new int[integers.size()];
	    Iterator<Integer> iterator = integers.iterator();
	    for (int i = 0; i < ret.length; i++)
	    {
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}
	
	
}
