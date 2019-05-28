package simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.Timer;

import gui.Game;
import gui.TerrainPanel;

//TODO make gui nicer and add max age counter.
public class GameLoop implements ActionListener{
	private Environment environment;
	private TerrainPanel panel;
	private int timeElapsed;
	private JTextField foodRegenTxt;
	private Game frame;
	private Data data;

	public GameLoop(TerrainPanel panel, JTextField txtFoodRegen, Data dataObj, Game mainFrame) {
		this.frame = mainFrame;
		this.environment = panel.getEnvironment();
		this.panel = panel;
		this.foodRegenTxt = txtFoodRegen;
		this.data = dataObj;
		this.timeElapsed = 0;
		environment.moveSpecies();
	}
	
	 public void actionPerformed(ActionEvent e) {
		 timeElapsed += 50;
		 environment.moveSpecies();
		 environment.checkAliveSpecies();
		 environment.eatFood();
		 environment.eatSpecies();
		 addFood();
		 if (timeElapsed % 1000 == 0) {
			 environment.multiplySpecies();
			 environment.addCheckAge();
			 addDataValues();
		 }
		 panel.repaint();
		 if (!checkIfAllDead(e)) {
			 frame.updateLabels(getLabelTexts());
		 }
		 this.environment.shuffleLists();
     }

	private void addDataValues() {
		data.setNrHerbivores(environment.getNrHerbivores());
		data.setNrOmnivores(environment.getNrOmnivores());
		data.setNrCarnivores(environment.getNrCarnivores());
		data.setAvgSpeed(environment.getSpeedStats()[0]);
		data.setAvgSize(environment.getSizeStats()[0]);
		data.setAvgAge(environment.getMaxAgeStats()[0]);
		data.setAvgScent(environment.getScentStats()[0] - environment.getSizeStats()[0]);
		data.setAvgEnergyCost(environment.getEnergyConsumptionStats()[0]);
		data.addTime();
	}
	
	private void addFood() {
		Environment t = this.environment;
		t.createFood(Integer.parseInt(foodRegenTxt.getText()));
	}
	
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

	private boolean checkIfAllDead(ActionEvent e) {
		if (this.environment.getNrSpecies() == 0) {
			Timer t  = (Timer) e.getSource();
			t.stop();
			return true;
		}
		return false;
	}
}
