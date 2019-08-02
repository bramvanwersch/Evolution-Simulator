package gui;

import java.awt.Color;
import java.util.ArrayList;

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
	
	public void addNoScentRangeList(int val) {
		scentRange.add(val);
	}
	
	public void addColorsList(Color val) {
		colors.add(val);
	}
	
	public void addEatSizeFactorList(int val) {
		eatSizeFactor.add(val);
	}

	public ArrayList<String> getTypes() {
		return types;
	}

	public ArrayList<String> getNames() {
		return names;
	}

	public ArrayList<Integer> getNoIndividuals() {
		return noIndividuals;
	}

	public ArrayList<Integer> getSizes() {
		return sizes;
	}

	public ArrayList<Integer> getSpeeds() {
		return speeds;
	}

	public ArrayList<Integer> getMaxAges() {
		return maxAges;
	}

	public ArrayList<Integer> getScentRange() {
		return scentRange;
	}

	public ArrayList<Color> getColors() {
		return colors;
	}

	public ArrayList<Integer> getEatSizeFactor() {
		return eatSizeFactor;
	}

	
	
}
