package gui;

import javax.swing.JPanel;

import user_input.PopulationSettings;

public abstract class PopulationPanel extends JPanel {
	
	public PopulationPanel() {
		innitLayout();
	}
	
	public abstract PopulationSettings getSettings();
	public abstract void innitLayout();
}
