package simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.Timer;

import gui.OptionData;
import gui.SidePanelGui;
import gui.TerrainPanel;

public class GameLoop implements ActionListener{
	private Environment environment;
	private TerrainPanel panel;
	private int timeElapsed;
	private int foodRegenTxt;
	private SidePanelGui sidePanel;

	/**
	 * Class for updating the main panel every 50 ms by invoking updating methods of species and saving data
	 * to be able to track progression.
	 * @param panel: the main panel that draws all generated species and food objects
	 * @param txtFoodRegen: number for a text field to set the food regeneration time.
	 * @param dataObj: data class object that stores values every second.
	 * @param mainFrame: Container for panel and place where information is displayed about the stats of species
	 */
	public GameLoop(TerrainPanel panel, Environment environment, int txtFoodRegen, SidePanelGui sidePanel) {
		this.sidePanel = sidePanel;
		this.environment = environment;
		this.panel = panel;
		this.foodRegenTxt = txtFoodRegen;
		this.timeElapsed = 0;
		sidePanel.updateLabels(getLabelTexts());
	}
	
	/**
	 * Function that is invoked every time this class recieves an update. This is the main function to keep 
	 * the game running
	 */
	public void actionPerformed(ActionEvent e) {
		if (timeElapsed % 1000 == 0) {
			addAverageDataValues();
			addPopData();
			if (timeElapsed != 0) {
				environment.addAge();
			}
			if (!checkIfAllDead(e)) {
				sidePanel.updateLabels(getLabelTexts());
			}
		}
		timeElapsed += 50;
		environment.nextTimeStep();
		environment.createFood(foodRegenTxt);
		
		panel.repaint();
	}
	
	/**
	 * Function that is evoked every second to record data points for every stat of the species and time.
	 */
	private void addAverageDataValues() {
		environment.saveAveragePopulationsStatsData(timeElapsed);
	}
	
	private void addPopData() {
		for (int i = 0; i < environment.getPopulations().size(); i ++) {
			environment.getPopulations().get(i).saveStatsData(timeElapsed);
		}
	}
	
	/**
	 * Function for collecting data to be displayed in the labels besides the game to easier track progression
	 * of statics of species. (this function is bad structure getting game instance and refering back to it).
	 * @return Array of statistics.
	 */
	private String[] getLabelTexts() {
		String [] lblTexts = new String [7];
		double[][] averageStats = environment.getAveragePopulationStats();
		lblTexts[0] = String.format("%d|%d|%d (%d)",environment.getNrHerbivores(),environment.getNrOmnivores(), environment.getNrCarnivores(), environment.getAllDeadSpecies());
		lblTexts[1] = String.format("%.2f (%.0f - %.0f)", averageStats[0][0], averageStats[0][1], averageStats[0][2]);
		lblTexts[2] = String.format("%.2f (%.0f - %.0f)", averageStats[1][0], averageStats[1][1], averageStats[1][2]);
		lblTexts[3] = String.format("%.2f (%.0f - %.0f)", averageStats[2][0], averageStats[2][1], averageStats[2][2]);
		lblTexts[4] = String.format("%.2f (%.0f - %.0f)", averageStats[3][0], averageStats[3][1], averageStats[3][2]);
		lblTexts[5] = String.format("%.2f (%.0f - %.0f)", averageStats[4][0], averageStats[4][1], averageStats[4][2]);
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

	
	public int getTimeElapsed() {
		return timeElapsed;
	}
}
