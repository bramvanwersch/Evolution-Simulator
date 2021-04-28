package species;

public class Plant extends AutotrophSpecies{
	
	public Plant(int size, int maxAge, int startEnergy, int[] maxNutrientValues, int energyOnConsumption) {
		super(size, maxAge, startEnergy, new int[] {50,50,50}, energyOnConsumption);
	}
	
	public Plant(int x, int y, int size, int maxAge, int startEnergy,  int[] maxNutrientValues, int energyOnConsumption) {
		super(x, y, size, maxAge, startEnergy, new int[] {50,50,50}, energyOnConsumption);
	}
	
}
