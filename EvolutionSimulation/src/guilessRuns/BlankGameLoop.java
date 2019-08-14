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
	private boolean isSimulationFinished;
	private int updateTime;
	private Integer runCount;
	private PopulationData popData[];
	
	public BlankGameLoop(int txtFoodRegen, Environment enviroment, int updateTime) {
		this.environment = enviroment;
		this.foodRegenTxt = txtFoodRegen;
		this.popData = new PopulationData[environment.getPopulations().size()];
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
				System.out.println("Dying is not an option");
				this.isSimulationFinished = true;
				runCount += 1 ;
			
			}
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
	private String makeHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("AvgEnergyCost");
		list.add("AvgSize");
		list.add("AvgSpeed");
		list.add("AvgScent");
		list.add("NrSpec");
		list.add("NrCarn");
		list.add("NrHerb");
		list.add("NrOmni");
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			sb.append(s);
			sb.append("\t");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	private String makeString(PopulationData soleSurvivor, String header) {
		StringBuilder sb = new StringBuilder();
		String string = "";
		sb.append(header);
		int length = soleSurvivor.getAvgAge().length;
		for(int i = 0 ; i < length ; i++) {
			sb.append(Integer.toString(soleSurvivor.getAvgAge()[i]));
			sb.append("\t" + Integer.toString(soleSurvivor.getAvgEnergyCost()[i]));
			sb.append("\t" + Integer.toString(soleSurvivor.getAvgSize()[i]));
			sb.append("\t" + Integer.toString(soleSurvivor.getAvgSpeed()[i]));
			sb.append("\t" + Integer.toString(soleSurvivor.getAvgScent()[i]));
			sb.append("\t" + Integer.toString(soleSurvivor.getNrSpecies()[i]));
			sb.append("\t" + Integer.toString(soleSurvivor.getNrCarnivores()[i]));
			sb.append("\t" + Integer.toString(soleSurvivor.getNrHerbivores()[i]));
			sb.append("\t" + Integer.toString(soleSurvivor.getNrOmnivores()[i]));
			sb.append("\n");
		
		}
		System.out.println("String made");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	private void writeToFile(String string) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("DataDocument.txt",true);
			fileWriter.write(string);
		} catch (IOException e) {
			System.out.println("File could not be found");
			e.printStackTrace();
		}finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("File was not saved");
				e.printStackTrace();
			}
			
		}
		
		

	}
	
	
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
	
	public boolean isSimulationFinished() {
		return isSimulationFinished;
	}


	
	

}
