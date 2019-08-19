	package gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import gui.SidePanelGui;
import gui.OptionData;
import simulation.PopulationData;
import simulation.Environment;
import simulation.GameLoop;
import simulation.Population;

public class Run {
	private Timer timer;
	private int UPDATE_TIME = 50;
	private JFrame f;
	private OptionData data;
	private SidePanelGui sidePanel;
	private TerrainPanel panel;
	private GameLoop loop;
	private Environment environment;

	public Run(OptionData data) {
		this.data = data;
		environment = new Environment(this.data);
		f = new JFrame("Terrain");
		createGui();
		loop = new GameLoop(panel,environment, 50, sidePanel);
		timer = new Timer(UPDATE_TIME, loop);
	}
	
	private void createGui() {
		//main panel
		panel = new TerrainPanel(950,950, environment);
		
		//panel tot the side
		sidePanel = new SidePanelGui(950, 300);
		
		BorderLayout bd = new BorderLayout();
		f.setLayout(bd);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		buttonPanel.add(btnPause, BorderLayout.NORTH);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restartTimer();
			}
		});
		buttonPanel.add(btnRestart, BorderLayout.NORTH);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});
		buttonPanel.add(btnNew, BorderLayout.NORTH);
		
		JButton btnGraph = new JButton("Graph");
		btnGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawGraph();
			}
		});
		buttonPanel.add(btnGraph, BorderLayout.NORTH);
		
		f.add(buttonPanel, BorderLayout.NORTH);
			
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
		timer.stop();
		f.dispose();
		environment = new Environment(this.data);
		f = new JFrame("Terrain");
		createGui();
		loop = new GameLoop(panel,environment, 50, sidePanel);
		timer = new Timer(UPDATE_TIME, loop);
	}
	
	private void newGame() {
		timer.stop();
		f.dispose();
		new OptionMenu();
	}
	

	private void drawGraph() {
		//LENGHT OF ATTRIBUTES IS STILL HARDCODED
		String[] populationNames = new String[environment.getAllPopData().length];
		for (int i = 0; i < environment.getPopulations().size(); i++) {
			Population sp = environment.getPopulations().get(i);
			populationNames[i] = sp.getName();
		}
		String[] attributeNames = new String[] {"speed", "size", "max age", "scent", "energy", "Nr species"};		
		new GraphBuilder(environment, populationNames, attributeNames,
				1000, 800, new String [] {"Time", ""}, false).start();
	}

}
