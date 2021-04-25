package species;

public class Plant extends AutotrophSpecies{
	
	public Plant(int size, int maxAge, int startEnergy, int[] maxNutrientValues) {
		super(size, maxAge, startEnergy, new int[] {50,50,50});
	}
	
	public Plant(int x, int y, int size, int maxAge, int startEnergy,  int[] maxNutrientValues) {
		super(x, y, size, maxAge, startEnergy, new int[] {50,50,50});
	}
	
	public int getEnergy() {
		return 250;
	}
}
