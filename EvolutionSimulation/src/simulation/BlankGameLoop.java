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
	private boolean isSimulationFinished;
	private PopulationData[] popData;
	
	public BlankGameLoop(int txtFoodRegen, Environment enviroment ) {
		this.environment = enviroment;
		this.foodRegenTxt = txtFoodRegen;
		this.timeElapsed = 0;
		this.isSimulationFinished = false;
		this.popData = new PopulationData[environment.getPopulations().size()];
		for (int i = 0; i < environment.getPopulations().size(); i ++) {
			this.popData[i] = new PopulationData();
		}
		environment.moveSpecies();
		
		
	}
	
	/**
	 * Function that is invoked every time this class recieves an update. This is the main function to keep 
	 * the game running
	 */
	public void actionPerformed(ActionEvent e) {
		timeElapsed += 23;
		environment.nextTimeStep();
		environment.createFood(foodRegenTxt);
		if (timeElapsed % 1000 == 0 && timeElapsed != 0) {
			environment.addAge();
		}
		if (checkIfSoleSurvivor(e)) {
			addPopData();
			this.isSimulationFinished = true;
		
		}
		
	}
	
	/**
	 * Function that is evoked every second to record data points for every stat of the species and time.
	 */
	private void addPopData() {
		for (int i = 0; i < environment.getPopulations().size(); i ++) {
			Population sp = environment.getPopulations().get(i);
			popData[i].setAvgSpeed(sp.getSpeedStats()[0]);
			popData[i].setAvgSize(sp.getSizeStats()[0]);
			popData[i].setAvgAge(sp.getMaxAgeStats()[0]);
			popData[i].setAvgScent(sp.getScentStats()[0]);
			popData[i].setAvgEnergyCost(sp.getEnergyConsumptionStats()[0]);
			popData[i].addTime();	
			popData[i].setNrSpecies(sp.getNrSpecies());
		}
	}
	
	/**
	 * Function that will check if there are species alive. If no species are alive the game is stopped. This
	 * is important because ever increasing food objects flood memory.
	 * @param e: actionevent variable to stop the timer invoked in the Game class
	 * @return boolean telling if the game should be continued or stopped.
	 */
	private boolean checkIfSoleSurvivor(ActionEvent e) {
		if (this.environment.getNrSpecies() == 1) {
			Timer t  = (Timer) e.getSource();
			t.stop();
			return true;
		}
		return false;
	}

	public PopulationData[] getData() {
		return popData;
	}
	
	
	public boolean isSimulationFinished() {
		return isSimulationFinished;
	}
	
	

}
