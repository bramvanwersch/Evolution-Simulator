package simulation;

public class Food{
	private int WINDOW_SIZE = 950;
	private int energy;
	private int size = 10;
	private int xLoc;
	private int yLoc;
	
	public Food(int energy, int size) {
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
