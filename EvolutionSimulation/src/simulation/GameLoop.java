package simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.Timer;

import gui.Game;
import gui.TerrainPanel;

public class GameLoop implements ActionListener{
	private Environment environment;
	private TerrainPanel panel;
	private int timeElapsed;
	private JTextField foodRegenTxt;
	private Game frame;
	private Data data;

	/**
	 * Class for updating the main panel every 50 ms by invoking updating methods of species and saving data
	 * to be able to track progression.
	 * @param panel: the main panel that draws all generated species and food objects
	 * @param txtFoodRegen: number for a text field to set the food regeneration time.
	 * @param dataObj: data class object that stores values every second.
	 * @param mainFrame: Container for panel and place where information is displayed about the stats of species
	 */
	public GameLoop(TerrainPanel panel, JTextField txtFoodRegen, Data dataObj, Game mainFrame) {
		this.frame = mainFrame;
		this.environment = panel.getEnvironment();
		this.panel = panel;
		this.foodRegenTxt = txtFoodRegen;
		this.data = dataObj;
		this.timeElapsed = 0;
		environment.moveSpecies();
	}
	
	/**
	 * Function that is invoked every time this class recieves an update. This is the main function to keep 
	 * the game running
	 */
	public void actionPerformed(ActionEvent e) {
		timeElapsed += 50;
		environment.nextTimeStep();
		environment.createFood(Integer.parseInt(foodRegenTxt.getText()));
		if (timeElapsed % 1000 == 0 && timeElapsed != 0) {
			addDataValues();
			environment.addCheckAge();
		}
		panel.repaint();
		if (!checkIfAllDead(e)) {
			frame.updateLabels(getLabelTexts());
		}
	}
 
	/**
	 * Function that is evoked every second to record data points for every stat of the species and time.
	 */
	private void addDataValues() {
		data.setNrHerbivores(environment.getNrHerbivores());
		data.setNrOmnivores(environment.getNrOmnivores());
		data.setNrCarnivores(environment.getNrCarnivores());
		data.setAvgSpeed(environment.getSpeedStats()[0]);
		data.setAvgSize(environment.getSizeStats()[0]);
		data.setAvgAge(environment.getMaxAgeStats()[0]);
		data.setAvgScent(environment.getScentStats()[0]);
		data.setAvgEnergyCost(environment.getEnergyConsumptionStats()[0]);
		data.addTime();
	}
	
	/**
	 * Function for collecting data to be displayed in the labels besides the game to easier track progression
	 * of statics of species. (this function is bad structure getting game instance and refering back to it).
	 * @return Array of statistics.
	 */
	private String[] getLabelTexts() {
		String [] lblTexts = new String [7];
		Environment t = this.environment;
		lblTexts[0] = String.format("%d|%d|%d (%d)",environment.getNrHerbivores(),environment.getNrOmnivores(), environment.getNrCarnivores(), environment.getAllDeadSpecies());
		lblTexts[1] = String.format("%.2f (%.0f - %.0f)", environment.getSpeedStats()[0],environment.getSpeedStats()[1],environment.getSpeedStats()[2]);
		lblTexts[2] = String.format("%.2f (%.0f - %.0f)", environment.getSizeStats()[0],environment.getSizeStats()[1],environment.getSizeStats()[2]);
		lblTexts[3] = String.format("%.2f (%.0f - %.0f)", environment.getMaxAgeStats()[0],environment.getMaxAgeStats()[1],environment.getMaxAgeStats()[2]);
		lblTexts[4] = String.format("%.2f (%.0f - %.0f)", environment.getScentStats()[0],environment.getScentStats()[1],environment.getScentStats()[2]);
		lblTexts[5] = String.format("%.2f (%.0f - %.0f)", environment.getEnergyConsumptionStats()[0],environment.getEnergyConsumptionStats()[1],environment.getEnergyConsumptionStats()[2]);
		lblTexts[6] = String.format("%d Seconds", timeElapsed/1000);
		return lblTexts;
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
