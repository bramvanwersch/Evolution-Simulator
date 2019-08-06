package gui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
		
		JPanel buttonPanel = new JPanel();
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTimer();
			}
		});
		buttonPanel.add(btnStart);
		
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopTimer();
			}
		});
		buttonPanel.add(btnPause, bd.NORTH);
		
		f.add(buttonPanel, bd.NORTH);
			
		f.pack();
		f.setVisible(true);
	}
	
	
	
	public void startTimer() {
		timer.start();
	}
	
	private void stopTimer() {
		timer.stop();
	}
	
	private void newGame() {
		// TODO Auto-generated method stub
		
	}

}
