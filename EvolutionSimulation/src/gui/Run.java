	package gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import environment.Environment;
import gameobjects.Ecosystem;
import gameobjects.GameLoop;
import gui.SidePanelGui;
import populations.Population;
import populations.PopulationData;
import user_input.OptionData;

/**
 * Class that creates the ecosystem and places it in the GUI together with some
 * extra controll buttons that allow the user to restart or redo parts of the
 * configuration 
 * @author Bram van Wersch.
 */
public class Run {
	private Timer timer;
	private int UPDATE_TIME = 50;
	private JFrame f;
	private OptionData data;
	private SidePanelGui sidePanel;
	private TerrainPanel panel;
	private GameLoop loop;
	private Ecosystem ecosystem;

	/**
	 * Construtor for innitialising the GameLoop that is a timer the pannel for
	 * display of the game and surrounding widgets.
	 * @param data is a class that saves all the settings so they can be
	 * accesed by the relevant classes. In this case the information is just
	 * passed on.
	 */
	public Run(OptionData data) {
		this.data = data;
		ecosystem = new Ecosystem(this.data);
		f = new JFrame("Terrain");
		createGui();
		loop = new GameLoop(panel,ecosystem, sidePanel);
		timer = new Timer(UPDATE_TIME, loop);
	}
	
	/**
	 * Function that creates the GUI and places all JPannels in the correct 
	 * places.
	 */
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
	
	/**
	 * Starts the game timer making the game run and update.
	 */
	public void startTimer() {
		timer.start();
	}
	
	/**
	 * Stops the game timer and all update related processes.
	 */
	private void stopTimer() {
		timer.stop();
	}	
 
	/**
	 * Restarts the game timer and recreates all relevant classes that are
	 * needed for a restart.
	 */
	private void restartTimer() {
		timer.stop();
		f.dispose();
		ecosystem = new Ecosystem(this.data);
		f = new JFrame("Terrain");
		createGui();
		loop = new GameLoop(panel,ecosystem, sidePanel);
		timer = new Timer(UPDATE_TIME, loop);
	}
	
	/**
	 * Completelely restarts the simulation by disposing of the current window
	 * and making a new OptionMenu object.
	 */
	private void newGame() {
		timer.stop();
		f.dispose();
		new OptionMenu();
	}
	
	/**
	 * Method that is invoked for drawing the graph
	 */
	private void drawGraph() {
		String[] populationNames = new String[ecosystem.getNrHetrotrophPopulations()];
		for (int i = 0; i < ecosystem.getNrHetrotrophPopulations(); i++) {
			Population sp = ecosystem.getHetrotrophPopulation(i);
			populationNames[i] = sp.getName();
		}
		String[] attributeNames = new String[] {"speed", "size", "max age", "scent", "energy", "Nr species"};		
		new GraphBuilder(ecosystem, populationNames, attributeNames,
				1000, 800, new String [] {"Time", ""}, false).start();
	}
}
