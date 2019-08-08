package simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import gui.SidePanelGui;
import gui.TerrainPanel;

public class BlankGameLoop implements ActionListener {
	private Environment environment;
	private int timeElapsed;
	private int foodRegenTxt;
	private PopulationData data;
	
	public BlankGameLoop(int txtFoodRegen, Environment enviroment ) {
		this.environment = enviroment;
		this.foodRegenTxt = txtFoodRegen;
		this.data = new PopulationData();
		this.timeElapsed = 0;
		environment.moveSpecies();
	}
	
	/**
	 * Function that is invoked every time this class recieves an update. This is the main function to keep 
	 * the game running
	 */
	public void actionPerformed(ActionEvent e) {
		System.out.println("looove");
		timeElapsed += 50;
		environment.nextTimeStep();
		environment.createFood(foodRegenTxt);
		if (timeElapsed % 1000 == 0 && timeElapsed != 0) {
			environment.addAge();
		}
		if (!checkIfAllDead(e)) {
		System.out.println("looove");
		}
		System.out.println("looove");
	}
	/**
	 * Function that will check if there are species alive. If no species are alive the game is stopped. This
	 * is important because ever increasing food objects flood memory.
	 * @param e: actionevent variable to stop the timer invoked in the Game class
	 * @return boolean telling if the game should be continued or stopped.
	 */
	private boolean checkIfAllDead(ActionEvent e) {
		if (this.environment.getNrSpecies() == 0) {
			Timer t  = (Timer) e.getSource();
			t.stop();
			return true;
		}
		return false;
	}

}
