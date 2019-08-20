	package gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import environment.Environment;
import gui.SidePanelGui;
import gui.OptionData;
import simulation.PopulationData;
import simulation.Ecosytem;
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
	private Ecosytem ecosystem;
	private Environment environment;

	public Run(OptionData data) {
		this.data = data;
		//HARDCODED FOR NOW NEEDS FEEDBACK FROM OPTIONS
		environment = new Environment(30, 50, 50, 50);
		ecosystem = new Ecosytem(this.data, environment);
		f = new JFrame("Terrain");
		createGui();
		loop = new GameLoop(panel,ecosystem, 50, sidePanel);
		timer = new Timer(UPDATE_TIME, loop);
	}
	
	private void createGui() {
		//main panel
		panel = new TerrainPanel(950,950, ecosystem);
		
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
		ecosystem = new Ecosytem(this.data, environment);
		f = new JFrame("Terrain");
		createGui();
		loop = new GameLoop(panel,ecosystem, 50, sidePanel);
		timer = new Timer(UPDATE_TIME, loop);
	}
	
	private void newGame() {
		timer.stop();
		f.dispose();
		new OptionMenu();
	}
	

	private void drawGraph() {
		//LENGHT OF ATTRIBUTES IS STILL HARDCODED
		String[] populationNames = new String[ecosystem.getAllPopData().length];
		for (int i = 0; i < ecosystem.getPopulations().size(); i++) {
			Population sp = ecosystem.getPopulations().get(i);
			populationNames[i] = sp.getName();
		}
		String[] attributeNames = new String[] {"speed", "size", "max age", "scent", "energy", "Nr species"};		
		new GraphBuilder(ecosystem, populationNames, attributeNames,
				1000, 800, new String [] {"Time", ""}, false).start();
	}

}
