package simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import gui.BlankRun;
import gui.SidePanelGui;
import gui.TerrainPanel;

public class BlankGameLoop implements ActionListener {
	private Environment environment;
	private int timeElapsed;
	private int foodRegenTxt;
	private boolean isSimulationFinished;
	private PopulationData[] popData;
	private int updateTime;
	private BlankRun blankRun;
	private Integer runCount;
	
	public BlankGameLoop(int txtFoodRegen, Environment enviroment, int updateTime, BlankRun blankRun ) {
		this.environment = enviroment;
		this.foodRegenTxt = txtFoodRegen;
		this.blankRun = blankRun;
		this.timeElapsed = 0;
		this.isSimulationFinished = false;
		this.updateTime = updateTime;
		this.popData = new PopulationData[environment.getPopulations().size()];
		for (int i = 0; i < environment.getPopulations().size(); i ++) {
			this.popData[i] = new PopulationData();
		}
		environment.moveSpecies();
		runCount = 0;
		
		
	}
	
	/**
	 * Function that is invoked every time this class recieves an update. This is the main function to keep 
	 * the game running
	 */
	public void actionPerformed(ActionEvent e) {
		System.out.println("running");
		timeElapsed += updateTime;
		environment.nextTimeStep();
		environment.createFood(foodRegenTxt);
		System.out.println(this.environment.getPopulations().size());
		if (timeElapsed % 1000 == 0 && timeElapsed != 0) {
			environment.addAge();
		}
		if (checkIfSoleSurvivor(e)) {
			System.out.println("Dying is not an option");
			this.isSimulationFinished = true;
			runCount += 1 ;
			System.out.println(runCount);
			blankRun.updateCounter(runCount);
			addPopData();
			
		
		}
		
	}
	
	/**
	 * Function that is evoked every second to record data points for every stat of the species and time.
	 */
	private void addPopData() {
		for (int i = 0; i < environment.getPopulations().size(); i ++) {
			Population sp = environment.getPopulations().get(i);
			popData[i].setAvgSpeed(sp.getSpeedStats()[0]);
			popData[i].setAvgSize(sp.getMaxSizeStats()[0]);
			popData[i].setAvgAge(sp.getMaxAgeStats()[0]);
			popData[i].setAvgScent(sp.getScentStats()[0]);
			popData[i].setAvgEnergyCost(sp.getEnergyConsumptionStats()[0]);
			popData[i].setTime(timeElapsed/1000);
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
		if (this.environment.getPopulations().size()>= 1) {
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
