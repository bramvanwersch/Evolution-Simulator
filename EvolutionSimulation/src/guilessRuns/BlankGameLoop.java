package guilessRuns;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;

import gui.SidePanelGui;
import gui.TerrainPanel;
import simulation.Environment;
import simulation.Population;
import simulation.PopulationData;

public class BlankGameLoop implements ActionListener {
	private Environment environment;
	private int timeElapsed;
	private int foodRegenTxt;
	private int updateTime;
	private Integer runCount;
	private PopulationData popData[];
	private boolean runFinished;
	
	public BlankGameLoop(int txtFoodRegen, Environment enviroment, int updateTime) {
		this.environment = enviroment;
		this.foodRegenTxt = txtFoodRegen;
		this.popData = new PopulationData[environment.getPopulations().size()];
		this.timeElapsed = 0;
		this.updateTime = updateTime;
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
		timeElapsed += updateTime;
		environment.nextTimeStep();
		environment.createFood(foodRegenTxt);
		addPopData();
		if (timeElapsed % 1000 == 0) {
			System.out.println("Age step taken");
			System.out.println(this.environment.getPopulations().size());
			if (timeElapsed != 0) {
				environment.addAge();
			}
			addPopData();
			if (checkIfSoleSurvivor(e)) {
				PopulationData soleSurvivor = getSoleSurvivor();
				DataSaver dataSaver = new DataSaver(soleSurvivor, environment);
				dataSaver.saveDataWrapper();
				
			}
		}
	}

	
	/**
	 * Function that is evoked every second to record data points for every stat of the species and time.
	 */
	private void addPopData() {
		for (int i = 0; i < environment.getPopulations().size(); i ++) {
			double[][] stats = environment.getPopulations().get(i).getStats();
			popData[i].setAvgSpeed(stats[0][0]);
			popData[i].setAvgSize(stats[1][0]);
			popData[i].setAvgAge(stats[2][0]);
			popData[i].setAvgScent(stats[3][0]);
			popData[i].setAvgEnergyCost(stats[4][0]);
			popData[i].setTime(timeElapsed/1000);
			popData[i].setNrSpecies(environment.getPopulations().get(i).getNrSpecies());
		}
	}
	
	/**
	 * Function that will check if there are species alive. If there is one species alive the timer stops.
	 * Because these GUIless games are used to make a inference about good starting values.
	 * @param e: actionevent variable to stop the timer invoked in the Game class
	 * @return boolean telling if the game should be continued or stopped.
	 */
	private boolean checkIfSoleSurvivor(ActionEvent e) {
		Integer countDeadPopulation = getDeadPopulation();

		if (countDeadPopulation>= 1) {
			System.out.println("Timer stopped");
			Timer t  = (Timer) e.getSource();
			t.stop();
			runFinished = true;
			return true;
		}
		return false;
	}
	
	private PopulationData getSoleSurvivor() {
		int length = 0;
		PopulationData soleSurvivor = null;
		for(int i = 0; i < popData.length ; i++ ) {
			length = popData[i].getNrSpecies().length;
			if(popData[i].getNrSpecies()[length-1]!=0) {
				System.out.println(popData[i].getNrSpecies()[length-1]);
				soleSurvivor = popData[i];
			}
			}
		System.out.println("SoleSurvivor found");
		System.out.println(Integer.toString(soleSurvivor.getAvgSpeed()[0]));
		return soleSurvivor;
	}

	/* This obtains the "type" of the population(omnivore="O", herbivore="H" or carnivore="C") and returns a List of characters
	 * of the length of the data
	 * @ param PopulationData solesurvivor ; the sole survivor of the run.
	 */

	/* This formats the string to write into the file, it puts together: the type obtained from getEatingpref,
	 * the header made with makeHeader and the data of the lone survivor obtained from populationData.
	 * 
	 */


	
	
	public Integer getDeadPopulation() {
		Integer countDeadPopulation = 0;
		ArrayList<Population> pops = this.environment.getPopulations();
		for(int i = 0; i < pops.size() ; i++ ) {
			if(pops.get(i).getNrSpecies()==0) {
				countDeadPopulation += 1;
			}
		}
		
		return countDeadPopulation;
	}

	public PopulationData[] getData() {
		return popData;
	}
	public String getRunCountString() {
		return runCount.toString();
	}
	public boolean getRunFinished() {
		return runFinished;
	}
	

}
