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
import simulation.BlankGameLoop;
import simulation.PopulationData;
import simulation.Environment;
import simulation.GameLoop;

public class Run {
	private Timer timer;
	private int UPDATE_TIME = 50;
	private SidePanelGui sidePanel;
	private TerrainPanel panel;
	private GameLoop loop;
	private BlankGameLoop blankLoop;
	private Environment environment;
	
	
	public Run(OptionData data, boolean runGui) {
		environment = new Environment(data);
		if (runGui) {
			createGui();
			loop = new GameLoop(panel,environment, 50, sidePanel);
			timer = new Timer(UPDATE_TIME, loop);
		}else {
			blankLoop = new BlankGameLoop( 50, environment);
			timer = new Timer(UPDATE_TIME, loop);
		}

	}
	
	private void createGui() {
		panel = new TerrainPanel(950,950, environment);
		
		sidePanel = new SidePanelGui(950, 300);
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
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restartTimer();
			}
		});
		buttonPanel.add(btnRestart, bd.NORTH);
		
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
	
 
	private void restartTimer() {
		// TODO Auto-generated method stub
		
	}
	
	private void newGame() {
		// TODO Auto-generated method stub
		
	}

}
