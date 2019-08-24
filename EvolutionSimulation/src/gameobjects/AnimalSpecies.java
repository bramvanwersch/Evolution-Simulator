package gameobjects;

import genome.Genome;

public class AnimalSpecies extends Species {

	public AnimalSpecies(int x, int y, int energy, Genome genome, int number, double eatSizeFactor) {
		super(x, y, energy, genome, number, eatSizeFactor);
		// TODO Auto-generated constructor stub
	}

	public AnimalSpecies(int size, int speed, int maxAge, int scentRange, double eatSizeFactor) {
		super(size, speed, maxAge, scentRange, eatSizeFactor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scentMovement(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean foodEaten(int x, int y, int sSize, int sEnergy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void eatTimeCheck() {
		// TODO Auto-generated method stub
		
	}

}
