package gui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.Timer;

import gui.SidePanelGui;
import gui.OptionData;
import simulation.Data;
import simulation.Environment;
import simulation.GameLoop;

public class Run {
	private Timer timer;
	private int UPDATE_TIME = 50;
	
	
	public Run(OptionData data, boolean runGUI) {
		Environment environment = new Environment(data);
		TerrainPanel panel = new TerrainPanel(950,950, environment);
		
		SidePanelGui sidePanel = new SidePanelGui(950, 300);
		GameLoop loop = new GameLoop(panel, 50, sidePanel);
		timer = new Timer(UPDATE_TIME, loop);

		JFrame f =  new JFrame();
		BorderLayout bd = new BorderLayout();
		f.setLayout(bd);
		f = new JFrame("Terrain");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.add(panel, bd.CENTER);
		f.add(sidePanel, bd.EAST);
			
		f.pack();
		f.setVisible(true);
	}

}
