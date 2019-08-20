package simulation;

public class Plant{
	private int WINDOW_SIZE = 950;
	private int energy;
	private int size;
	private int xLoc;
	private int yLoc;
	
	public Plant(int energy, int size) {
		this.size = size;
		this.energy = energy;
		this.xLoc = (int) (Math.random() * (WINDOW_SIZE - this.size -5) + 0.5 * this.size);
		this.yLoc = (int) (Math.random() * (WINDOW_SIZE - this.size -5) + 0.5 * this.size);
		}
	
	public int getEnergy() {
		return energy;
	}

	public int getSize() {
		return size;
	}

	public int getxLoc() {
		return xLoc;
	}

	public int getyLoc() {
		return yLoc;
	}


}
