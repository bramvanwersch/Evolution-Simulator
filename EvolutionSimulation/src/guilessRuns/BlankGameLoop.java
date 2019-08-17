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
	private boolean runFinished;
	
	public BlankGameLoop(int txtFoodRegen, Environment enviroment, int updateTime) {
		this.environment = enviroment;
		this.foodRegenTxt = txtFoodRegen;
		this.timeElapsed = 0;
		this.updateTime = updateTime;
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
			if (timeElapsed != 0) {
				environment.addAge();
			}
			addPopData();
			if (checkIfSoleSurvivor(e)) {
				System.out.println("SoleSurvivor found");
				PopulationData winnerData = getSoleSurvivor();
				Population winnerPop = environment.getMaxNrSpeciesPop();
				System.out.println(winnerData.toString());
				DataSaver dataSaverWinner = new DataSaver(winnerData, winnerPop);
				dataSaverWinner.saveWinner();
				System.out.println("Winner data saved");
				
				PopulationData loserData = getSoleSurvivor();
				Population loserPop = environment.getMinNrSpeciesPop();
				System.out.println(loserData.toString());
				System.out.println(loserPop.toString());
				DataSaver dataSaverLoser = new DataSaver(loserData, loserPop);
				dataSaverLoser.saveLoser();
				System.out.println("loserData saved");
				
			}
		}
	}

	
	/**
	 * Function that is evoked every second to record data points for every stat of the species and time.
	 */
	private void addPopData() {
		for (int i = 0; i < environment.getPopulations().size(); i ++) {
			environment.getPopulations().get(i).saveStatsData(timeElapsed);
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
		System.out.println(countDeadPopulation);
		if (countDeadPopulation>= 1) {
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
		for(int i = 0; i < environment.getAllPopData().length ; i++ ) {
			PopulationData pd = environment.getAllPopData()[i];
			length = pd.getNrSpecies().length-1;
			if(pd.getNrSpecies()[length]!=0) {
				soleSurvivor = pd;
			}
			}
		return soleSurvivor;
	}
	private PopulationData getSoleLoser() {
		int length = 0;
		PopulationData soleLoser = null;
		for(int i = 0; i < environment.getAllPopData().length ; i++ ) {
			PopulationData pd = environment.getAllPopData()[i];
			System.out.println(pd.getNrSpecies());
			length = pd.getNrSpecies().length;
			if(pd.getNrSpecies()[length-1]==0) {
				soleLoser = pd;
			}
			}
		return soleLoser;
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

	public String getRunCountString() {
		return runCount.toString();
	}
	public boolean getRunFinished() {
		return runFinished;
	}
	

}
