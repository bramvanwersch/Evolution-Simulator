package gui;

import javax.swing.JPanel;

import user_input.PopulationSettings;

public abstract class PopulationPanel extends JPanel {
	protected String name;
	
	public PopulationPanel(String baseName) {
		this.name = baseName;
		innitLayout();
	}
	
	public abstract PopulationSettings getSettings();
	public abstract void innitLayout();
}
