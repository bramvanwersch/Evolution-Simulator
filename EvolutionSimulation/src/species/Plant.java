package species;

public class Plant extends AutotrophSpecies{
	
	public Plant(int size, int maxAge, int startEnergy) {
		super(size, maxAge, startEnergy);
	}
	
	public Plant(int x, int y, int size, int maxAge, int startEnergy) {
		super(x, y, size, maxAge, startEnergy);
	}
}
