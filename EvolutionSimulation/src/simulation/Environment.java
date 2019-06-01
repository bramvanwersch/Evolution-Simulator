package simulation;

import java.util.ArrayList;
import java.util.Collections;

//TODO species need to not be pushed outside the map.
public class Environment {
	private int DEFAULT_FOOD_E = 100;
	private int DEFAULT_FOOD_SIZE = 5;
	private double EAT_SIZE_FACTOR = 1;
	private ArrayList<SpeciePopulation> populations;
	private ArrayList<Food> foodList;
	
	public Environment(int[] nrSpecies, int[] size, int[] speed, int[] maxAge, int[][]colors,
			String[] type, int nrFood) {
		this.foodList = new ArrayList<Food>();
		this.populations = new ArrayList<SpeciePopulation>();
		createPopulations(nrSpecies.length, colors, type);
		createSpecies(nrSpecies, size, speed, maxAge);
		createFood(nrFood);
	}

	private void createPopulations(int nrPopulations, int[][] colors, String[] type) {
		for (int i = 0; i < nrPopulations; i++) {
			SpeciePopulation p = new SpeciePopulation(colors[i], type[i]);
			populations.add(p);
		}
	}

	public void createSpecies(int[] nrSpecies, int[] size, int[] speed, int[] maxAge) {
		for (int i = 0; i <populations.size(); i++) {
			SpeciePopulation p = populations.get(i);
			for (int j = 0; j < nrSpecies[i]; j++) {
				Species s = null;
				if (p.getType().equals("Carnivore")) {
					if (p.getNrSpecies() == 0) {
						s = new Carnivore(size[i], speed[i], maxAge[i]);
						p.addSpeciesData(s, -1);
					}
				}
				else if (p.getType().equals("Herbivore")) {
					if (p.getNrSpecies() == 0) {
						s = new Herbivore(size[i], speed[i], maxAge[i]);
						p.addSpeciesData(s, -1);
					}
				}
				else if(p.getType().equals("Omnivore")) {
					if (p.getNrSpecies() == 0) {
						s = new Omnivore(size[i], speed[i], maxAge[i]);
						p.addSpeciesData(s, -1);
					}
				}
				if (s == null){
					p.multiplySpecies(p.getNrSpecies()-1, false);
				}
				else if (!checkSpeciesPlacement(s)) {
					j--;
				}
				else{
					p.addSpecies(s);
				}
			}
		}
	}
	
	private boolean checkSpeciesPlacement(Species spec) {
		for (SpeciePopulation sp: populations ) {
			for (int i = 0; i < sp.getNrSpecies(); i++ ) {
				Species s = sp.getSpecies(i);
				//check if the central point of the species just created is witin another species or not. if so move it.
				if (s.getxLoc() - s.getSize() < spec.getxLoc() && s.getxLoc() +s.getSize() > spec.getxLoc() &&
					s.getyLoc() - s.getSize() < spec.getyLoc() && s.getyLoc() +s.getSize() > spec.getyLoc()) {
					return false;
				}
			}
		}
		return true;
	}

	public void createFood(int nrFood) {
		for (int i = 0; i < nrFood; i++) {
			foodList.add(new Food(DEFAULT_FOOD_E, DEFAULT_FOOD_SIZE));
		}	
	}
	
	public int getNrFood() {
		return foodList.size();
	}
	
	public Food getFood(int index) {
		return foodList.get(index);
	}
	
	public void removeFood(int index) {
		foodList.remove(index);
	}

	public void shuffleLists() {
		for (SpeciePopulation sp: populations ) {
			sp.shuffleSpeciesList();
		}
		Collections.shuffle(foodList);	
	}
	
	public void addCheckAge() {
		for (SpeciePopulation sp: populations ) {
			for (int i = 0; i < sp.getNrSpecies(); i++) {
				Species s = sp.getSpecies(i);
				if (s.getAge() <= s.getMaxAge()) {
					s.addRepTime();
					s.addAge();
				}
				else {
					sp.removeSpecies(i);
				}
			}
		}
	}

	public void moveSpecies() {
		for (SpeciePopulation sp: populations ) {
			for (int i = 0; i < sp.getNrSpecies(); i++) {
				Species s = sp.getSpecies(i);
				Species closestCarnivore = null;
				Species closestHerbivore = null;
				if (sp.getType().equals("Herbivore")){
					closestCarnivore = checkHerbivoreScent(s);
				}
				else if (sp.getType().equals("Carnivore")){
					closestHerbivore = checkCarnivoreScent(s);
				}
				if (closestCarnivore != null) {
					s.moveAway(closestCarnivore.getxLoc(), closestCarnivore.getyLoc());
				}
				else if (closestHerbivore != null) {
					s.moveAway(closestHerbivore.getxLoc(), closestHerbivore.getyLoc());
				}
				else {
					s.move();
				}
			}
		}	
	}
	
	public void multiplySpecies() {
		for (SpeciePopulation sp: populations ) {
			for (int i = 0; i < sp.getNrSpecies(); i++) {
				if (sp.getSpecies(i).isCanMultiply()) {
					sp.multiplySpecies(i, true);
				}
			}
		}
	}
	
	public Species checkHerbivoreScent(Species s1) {
		Species closestSprite = null;
		double lowestC = s1.getScentRange();
		for (int i = 0; i < getAllCarnivores().size(); i++) {
			Species s2 = getAllCarnivores().get(i);
			//getting slope of triangle using pythagoras.
			if (Math.sqrt(Math.pow(s1.getxLoc() - s2.getxLoc(), 2) + Math.pow(s1.getyLoc() - s2.getyLoc(), 2)) 
					< lowestC && s2.getSize() > EAT_SIZE_FACTOR* s1.getSize()) {
				closestSprite = s2;
				lowestC = Math.sqrt(Math.pow(s1.getxLoc() - s2.getxLoc(), 2) + Math.pow(s1.getyLoc() - s2.getyLoc(), 2)); 
			}
		}
		return closestSprite;
	}
	
	public Species checkCarnivoreScent(Species s1) {
		Species closestSprite = null;
		double lowestC = s1.getScentRange();
		for (int i = 0; i < getAllHerbivores().size(); i++) {
			Species s2 = getAllHerbivores().get(i);
			//getting slope of triangle using pythagoras.
			if (Math.sqrt(Math.pow(s1.getxLoc() - s2.getxLoc(), 2) + Math.pow(s1.getyLoc() - s2.getyLoc(), 2)) 
					< lowestC && s2.getSize() < EAT_SIZE_FACTOR* s1.getSize()) {
				closestSprite = s2;
				lowestC = Math.sqrt(Math.pow(s1.getxLoc() - s2.getxLoc(), 2) + Math.pow(s1.getyLoc() - s2.getyLoc(), 2)); 
			}
		}
		return closestSprite;
	}
	
	public void checkAliveSpecies() {
		for (SpeciePopulation sp: populations ) {
			for (int i = 0; i < sp.getNrSpecies(); i++) {
				if (sp.getSpecies(i).getEnergy() <= 0) {
					sp.removeSpecies(i);
				}
			}
		}
	}
	
	public void eatFood() {
		for (int i = 0; i < getAllHerbivores().size() + getAllOmnivores().size(); i++) {
			Species s = getAllPlantEaters().get(i);
			for(int j = getNrFood() - 1; j >= 0; j--) {
				Food f = getFood(j);
				if (s.foodEaten(f.getxLoc(), f.getyLoc(), f.getSize(), f.getEnergy())) {
					removeFood(j);
				}
			}
		}	
	}
	
	public void eatSpecies() {
		for(int i = 0; i < getAllCarnivores().size() + getAllOmnivores().size(); i++) {
			for (SpeciePopulation sp: populations ) {
				if (sp.getType().equals("Herbivore")) {
					for(int j = sp.getNrSpecies() - 1; j >= 0; j--){
						Species s1 = getAllMeatEaters().get(i);
						Species s2 = sp.getSpecies(j);
						if (s1.getSize() > s2.getSize() * EAT_SIZE_FACTOR) {
							if (s1.checkCanEat(s2.getxLoc(), s2.getyLoc(), s2.getSize(), s2.getEnergy())) {
								sp.removeSpecies(j);
								if (i != 0) {
									i--;
								}
							}
						}
					}
				}
			}
		}
	}
	
	private ArrayList<Species> getAllMeatEaters() {
		ArrayList<Species> meatList = getAllCarnivores();
		meatList.addAll(getAllOmnivores());
		return meatList;
	}
	
	private ArrayList<Species> getAllPlantEaters() {
		ArrayList<Species> greenList = getAllHerbivores();
		greenList.addAll(getAllOmnivores());
		return greenList;
	}


	private ArrayList<Species> getAllSpecies() {
		ArrayList<Species> specList = new ArrayList<Species>();
		for (SpeciePopulation sp: populations ) {
			for (int i = 0; i < sp.getNrSpecies(); i++) {
				specList.add(sp.getSpecies(i));
			}
		}
		return specList;
	}
	
	private ArrayList<Species> getAllCarnivores() {
		ArrayList<Species> specList = new ArrayList<Species>();
		for (SpeciePopulation sp: populations ) {
			if (sp.getType().equals("Carnivore")) {
				for (int i = 0; i < sp.getNrSpecies(); i++) {
					specList.add(sp.getSpecies(i));
				}
			}
		}
		return specList;
	}
	
	private ArrayList<Species> getAllOmnivores() {
		ArrayList<Species> specList = new ArrayList<Species>();
		for (SpeciePopulation sp: populations ) {
			if (sp.getType().equals("Omnivore")) {
				for (int i = 0; i < sp.getNrSpecies(); i++) {
					specList.add(sp.getSpecies(i));
				}
			}
		}
		return specList;
	}
	
	private ArrayList<Species> getAllHerbivores() {
		ArrayList<Species> specList = new ArrayList<Species>();
		for (SpeciePopulation sp: populations ) {
			if (sp.getType().equals("Herbivore")) {
				for (int i = 0; i < sp.getNrSpecies(); i++) {
					specList.add(sp.getSpecies(i));
				}
			}
		}
		return specList;
	}
	
	public int getNrHerbivores() {
		return getAllHerbivores().size();
	}

	public int getNrCarnivores() {
		return getAllCarnivores().size();
	}

	public int getNrOmnivores() {
		return getAllOmnivores().size();
	}

	public int getNrSpecies() {
		int count = 0;
		for (SpeciePopulation sp: populations ) {
			count += sp.getNrSpecies();
		}
		return count;
	}
	
	public int getAllDeadSpecies() {
		int count = 0;
		for (SpeciePopulation sp: populations ) {
			count += sp.getDiedSpecies();
		}
		return count;
	}
	
	public ArrayList<SpeciePopulation> getPopulations() {
		return populations;
	}
	
	//data stats
	public double[] getSpeedStats() {
		double[] valArray = new double[getNrSpecies()];
		for (int i = 0; i < getNrSpecies(); i++) {
			valArray[i] = getAllSpecies().get(i).getSpeed();
		}
		int[] minMax = calcMinMax(valArray);
		return new double[]{calcAvgAttribute(valArray), minMax[0], minMax[1]};
	}
	
	public double[] getSizeStats() {
		double[] valArray = new double[getNrSpecies()];
		for (int i = 0; i < getNrSpecies(); i++) {
			valArray[i] = getAllSpecies().get(i).getSize();
		}
		int[] minMax = calcMinMax(valArray);
		return new double[]{calcAvgAttribute(valArray), minMax[0], minMax[1]};
	}
	
	public double[] getMaxAgeStats() {
		double[] valArray = new double[getNrSpecies()];
		for (int i = 0; i < getNrSpecies(); i++) {
			valArray[i] = getAllSpecies().get(i).getMaxAge();
		}
		int[] minMax = calcMinMax(valArray);
		return new double[]{calcAvgAttribute(valArray), minMax[0], minMax[1]};
	}
	
	public double[] getScentStats() {
		double[] valArray = new double [getNrSpecies()];
		for (int i = 0; i < getNrSpecies(); i++) {
			valArray[i] = getAllSpecies().get(i).getScentRange() - getAllSpecies().get(i).getSize();
		}
		int[] minMax = calcMinMax(valArray);
		return new double[]{calcAvgAttribute(valArray), minMax[0], minMax[1]};
	}
	
	public double[] getEnergyConsumptionStats() {
		double[] valArray = new double[getNrSpecies()];
		for (int i = 0; i < getNrSpecies(); i++) {
			valArray[i] = getAllSpecies().get(i).getEnergyConsumption();
		}
		int[] minMax = calcMinMax(valArray);
		return new double[]{calcAvgAttribute(valArray), minMax[0], minMax[1]};
	}
	
	public double calcAvgAttribute(double[] attrArray) {
		double total = 0;
		for(double arr : attrArray){
			total += arr;
		}
		return total/new Double(attrArray.length);
	}
	
	private int[] calcMinMax(double[] attrArray) {
		int[] minMax = {(int) attrArray[0],(int) attrArray[0]};
		for(int i = 0; i < getNrSpecies(); i++){
			if (attrArray[i] < minMax[0]) {
				minMax[0] = (int)attrArray[i];
			}
			else if (attrArray[i] > minMax[1]) {
				minMax[1] = (int)attrArray[i];
			}
		}
		return minMax;
	}
}
