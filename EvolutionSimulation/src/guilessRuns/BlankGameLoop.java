package guilessRuns;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import gameobjects.Ecosystem;
import populations.Population;
import populations.PopulationData;

public class BlankGameLoop implements ActionListener {
	private Ecosystem ecosystem;
	private int timeElapsed;
	private int updateTime;
	private Integer runCount;
	private boolean runFinished;
	
	public BlankGameLoop(Ecosystem ecosystem, int updateTime) {
		this.ecosystem = ecosystem;
		this.timeElapsed = 0;
		this.updateTime = updateTime;
	}
	
	/**
	 * Function that is invoked every time this class recieves an update. This is the main function to keep 
	 * the game running
	 */
	public void actionPerformed(ActionEvent e) {
		timeElapsed += updateTime;
		ecosystem.nextTimeStep();
		addPopData();
		if (timeElapsed % 1000 == 0) {
			addPopData();
			if (checkIfSoleSurvivor(e)) {
				
				// The Thread goes so fast that if I don't wait for a second, the last value of populationData doesn't exist yet.

				PopulationData winnerData = getSoleSurvivor();
				Population winnerPop = ecosystem.getMaxSpeciesHetPopulations();
				DataSaver dataSaverWinner = new DataSaver(winnerData, winnerPop);
				System.out.println("length of all popData");
				System.out.println(ecosystem.getNrHetrotrophPopulations());
				if(winnerData==null || winnerPop==null) {
					System.out.println("non existing winner");
				}
				dataSaverWinner.saveWinner();

				PopulationData loserData = getSoleSurvivor();
				Population loserPop = ecosystem.getMinSpeciesHetPopulations();
				DataSaver dataSaverLoser = new DataSaver(loserData, loserPop);
				dataSaverLoser.saveLoser();

				
			}
		}
	}

	
	/**
	 * Function that is evoked every second to record data points for every stat of the species and time.
	 */
	private void addPopData() {
		for (int i = 0; i < ecosystem.getNrPopulations(); i ++) {
			ecosystem.getPopulation(i).saveStatsData(timeElapsed);
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
		for(int i = 0; i < ecosystem.getNrHetrotrophPopulations(); i++ ) {
			PopulationData pd = ecosystem.getPopulation(i).getPopData();
			length = pd.getNrSpecies().length - 1;
			if(pd.getNrSpecies()[length] != 0) {
				soleSurvivor = pd;
			}
			}
		return soleSurvivor;
	}
	private PopulationData getSoleLoser() {
		int length = 0;
		PopulationData soleLoser = null;
		for(int i = 0; i < ecosystem.getNrHetrotrophPopulations() ; i++ ) {
			PopulationData pd = ecosystem.getPopulation(i).getPopData();
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
		for (int i = 0; i < ecosystem.getNrPopulations(); i++) {
			Population pops = ecosystem.getPopulation(i);
			if(pops.getNrSpecies()==0) {
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
