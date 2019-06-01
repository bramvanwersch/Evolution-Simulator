package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import simulation.Data;
import simulation.GameLoop;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Game {
	private final int[] START_SPECIES_COUNT = {2,0,10};
	private final int[] START_SIZE_COUNT = {35,35,25};
	private final int[] START_SPEED_COUNT = {15,20,15};
	private final int[] START_MAX_AGES = {90,90,90};
	private final int[][] START_COLORS = {{255,0,0},{255,0,255},{0,0,255}};
	private final String[] SPECIES_TYPES = {"Carnivore","Omnivore","Herbivore"};
	private final int START_FOOD_COUNT = 500;
	private final int UPDATE_TIME = 50;
	private final int FOOD_REGENERATION_RATE = 50;
	private TerrainPanel panel;
	private JTextField txtNumberFood;
	private JLabel lblNrSpeciesText;
	private Timer timer;
	private JLabel lblTime;
	private JLabel lblAvgSizeText;
	private JLabel lblAvgSpeedText;
	private JLabel lblAvgScentText;
	private JLabel lblAvgAgeText;
	private JLabel lblEnergyConsumptionText;
	private Data dataObj;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Game().createGui();
			}
		});
	}
	
	private void createGui() {
		this.dataObj = new Data();
		panel = new TerrainPanel(950,950, START_SPECIES_COUNT,START_SIZE_COUNT, START_SPEED_COUNT,START_MAX_AGES,
				START_COLORS,SPECIES_TYPES, START_FOOD_COUNT);
		SwingUtilities.isEventDispatchThread();
		JFrame f = new JFrame("Terrain");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel gamePannel = new JPanel();
		f.getContentPane().add(gamePannel, BorderLayout.EAST);
		GridBagLayout gbl_gamePannel = new GridBagLayout();
		gamePannel.setLayout(gbl_gamePannel);
		
		JLabel lblStatistics = new JLabel("Statistics:");
		GridBagConstraints gbc_lblStatistics = new GridBagConstraints();
		gbc_lblStatistics.gridwidth = 2;
		gbc_lblStatistics.insets = new Insets(5, 5, 5, 0);
		gbc_lblStatistics.gridx = 1;
		gbc_lblStatistics.gridy = 0;
		gamePannel.add(lblStatistics, gbc_lblStatistics);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridheight = 14;
		gbc_panel.gridy = 0;
		gbc_panel.gridx = 0;
		
		// the panel containing the good stuff
		gamePannel.add(panel, gbc_panel);

		JLabel lblNrSpecies = new JLabel("Number of individuals:");
		lblNrSpecies.setOpaque(true);
		lblNrSpecies.setBackground(Color.RED);
		GridBagConstraints gbc_lblNrSpecies = new GridBagConstraints();
		gbc_lblNrSpecies.anchor = GridBagConstraints.WEST;
		gbc_lblNrSpecies.insets = new Insets(5, 5, 5, 5);
		gbc_lblNrSpecies.gridx = 1;
		gbc_lblNrSpecies.gridy = 1;
		gamePannel.add(lblNrSpecies, gbc_lblNrSpecies);
		
		lblNrSpeciesText = new JLabel(panel.getEnvironment().getNrSpecies() + "");
		GridBagConstraints gbc_lblNrSpeciesText = new GridBagConstraints();
		gbc_lblNrSpeciesText.anchor = GridBagConstraints.WEST;
		gbc_lblNrSpeciesText.weightx = 1.0;
		gbc_lblNrSpeciesText.insets = new Insets(5, 5, 5, 0);
		gbc_lblNrSpeciesText.gridx = 2;
		gbc_lblNrSpeciesText.gridy = 1;
		gamePannel.add(lblNrSpeciesText, gbc_lblNrSpeciesText);
		
		JLabel lblAvgSpeed = new JLabel("Average speed:");
		lblAvgSpeed.setOpaque(true);
		lblAvgSpeed.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblAvgSpeed = new GridBagConstraints();
		gbc_lblAvgSpeed.anchor = GridBagConstraints.WEST;
		gbc_lblAvgSpeed.insets = new Insets(5, 5, 5, 5);
		gbc_lblAvgSpeed.gridx = 1;
		gbc_lblAvgSpeed.gridy = 2;
		gamePannel.add(lblAvgSpeed, gbc_lblAvgSpeed);
		
		lblAvgSpeedText = new JLabel("");
		GridBagConstraints gbc_lblAvgSpeedText = new GridBagConstraints();
		gbc_lblAvgSpeedText.anchor = GridBagConstraints.WEST;
		gbc_lblAvgSpeedText.weightx = 1.0;
		gbc_lblAvgSpeedText.insets = new Insets(5, 5, 5, 0);
		gbc_lblAvgSpeedText.gridx = 2;
		gbc_lblAvgSpeedText.gridy = 2;
		gamePannel.add(lblAvgSpeedText, gbc_lblAvgSpeedText);
		
		JLabel lblAvgSize = new JLabel("Average size:");
		lblAvgSize.setOpaque(true);
		lblAvgSize.setBackground(Color.BLUE);
		GridBagConstraints gbc_lblAvgSize = new GridBagConstraints();
		gbc_lblAvgSize.anchor = GridBagConstraints.WEST;
		gbc_lblAvgSize.insets = new Insets(5, 5, 5, 5);
		gbc_lblAvgSize.gridx = 1;
		gbc_lblAvgSize.gridy = 3;
		gamePannel.add(lblAvgSize, gbc_lblAvgSize);
		
		lblAvgSizeText = new JLabel("");
		GridBagConstraints gbc_lblAvgSizeText = new GridBagConstraints();
		gbc_lblAvgSizeText.anchor = GridBagConstraints.WEST;
		gbc_lblAvgSizeText.weightx = 1.0;
		gbc_lblAvgSizeText.insets = new Insets(5, 5, 5, 0);
		gbc_lblAvgSizeText.gridx = 2;
		gbc_lblAvgSizeText.gridy = 3;
		gamePannel.add(lblAvgSizeText, gbc_lblAvgSizeText);
		
		JLabel lblAvgAge = new JLabel("Average max age:");
		lblAvgAge.setOpaque(true);
		lblAvgAge.setBackground(Color.YELLOW);
		GridBagConstraints gbc_lblAvgAge = new GridBagConstraints();
		gbc_lblAvgAge.anchor = GridBagConstraints.WEST;
		gbc_lblAvgAge.insets = new Insets(5, 5, 5, 5);
		gbc_lblAvgAge.gridx = 1;
		gbc_lblAvgAge.gridy = 5;
		gamePannel.add(lblAvgAge, gbc_lblAvgAge);
		
		lblAvgAgeText = new JLabel("");
		GridBagConstraints gbc_lblAvgAgeText = new GridBagConstraints();
		gbc_lblAvgAgeText.anchor = GridBagConstraints.WEST;
		gbc_lblAvgAgeText.insets = new Insets(5, 5, 5, 0);
		gbc_lblAvgAgeText.gridx = 2;
		gbc_lblAvgAgeText.gridy = 5;
		gamePannel.add(lblAvgAgeText, gbc_lblAvgAgeText);
		
		JLabel lblAvgScent = new JLabel("Average scent range:");
		lblAvgScent.setOpaque(true);
		lblAvgScent.setBackground(Color.GRAY);
		GridBagConstraints gbc_lblAvgScent = new GridBagConstraints();
		gbc_lblAvgScent.anchor = GridBagConstraints.WEST;
		gbc_lblAvgScent.insets = new Insets(5, 5, 5, 5);
		gbc_lblAvgScent.gridx = 1;
		gbc_lblAvgScent.gridy = 6;
		gamePannel.add(lblAvgScent, gbc_lblAvgScent);
		
		lblAvgScentText = new JLabel("");
		GridBagConstraints gbc_lblAvgScentText = new GridBagConstraints();
		gbc_lblAvgScentText.anchor = GridBagConstraints.WEST;
		gbc_lblAvgScentText.insets = new Insets(5, 5, 5, 0);
		gbc_lblAvgScentText.gridx = 2;
		gbc_lblAvgScentText.gridy = 6;
		gamePannel.add(lblAvgScentText, gbc_lblAvgScentText);
		
		JLabel lblEnergyConsumption = new JLabel("Energy consumption:");
		lblEnergyConsumption.setOpaque(true);
		lblEnergyConsumption.setBackground(Color.CYAN);
		GridBagConstraints gbc_lblEnergyConsumption = new GridBagConstraints();
		gbc_lblEnergyConsumption.anchor = GridBagConstraints.WEST;
		gbc_lblEnergyConsumption.insets = new Insets(5, 5, 5, 5);
		gbc_lblEnergyConsumption.gridx = 1;
		gbc_lblEnergyConsumption.gridy = 7;
		gamePannel.add(lblEnergyConsumption, gbc_lblEnergyConsumption);
		
		lblEnergyConsumptionText = new JLabel("");
		GridBagConstraints gbc_lblEnergyConsumptionText = new GridBagConstraints();
		gbc_lblEnergyConsumptionText.anchor = GridBagConstraints.WEST;
		gbc_lblEnergyConsumptionText.insets = new Insets(5, 5, 5, 0);
		gbc_lblEnergyConsumptionText.gridx = 2;
		gbc_lblEnergyConsumptionText.gridy = 7;
		gamePannel.add(lblEnergyConsumptionText, gbc_lblEnergyConsumptionText);
		
		JButton btnShowGraph = new JButton("Show Graph");
		btnShowGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GraphBuilder(dataObj.getTimeArray(), dataObj.getDataArray()
						,1000, 800, new String [] {"Time", ""}, false);
			}

	
		});
		GridBagConstraints gbc_btnShowGraph = new GridBagConstraints();
		gbc_btnShowGraph.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnShowGraph.insets = new Insets(5, 5, 5, 5);
		gbc_btnShowGraph.gridx = 2;
		gbc_btnShowGraph.gridy = 11;
		gamePannel.add(btnShowGraph, gbc_btnShowGraph);
		
		JLabel lblNrFood = new JLabel("Regen of food:");
		GridBagConstraints gbc_lblNrFood = new GridBagConstraints();
		gbc_lblNrFood.anchor = GridBagConstraints.WEST;
		gbc_lblNrFood.insets = new Insets(5, 5, 5, 5);
		gbc_lblNrFood.gridx = 1;
		gbc_lblNrFood.gridy = 12;
		gamePannel.add(lblNrFood, gbc_lblNrFood);
		
		txtNumberFood = new JTextField();
		txtNumberFood.setText(FOOD_REGENERATION_RATE + "");
		GridBagConstraints gbc_txtNumberFood = new GridBagConstraints();
		gbc_txtNumberFood.anchor = GridBagConstraints.WEST;
		gbc_txtNumberFood.weightx = 1.0;
		gbc_txtNumberFood.insets = new Insets(0, 0, 5, 0);
		gbc_txtNumberFood.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNumberFood.gridx = 2;
		gbc_txtNumberFood.gridy = 12;
		gamePannel.add(txtNumberFood, gbc_txtNumberFood);
		txtNumberFood.setColumns(10);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTimer();
			}
		});
		
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStart.insets = new Insets(5, 5, 5, 5);
		gbc_btnStart.gridx = 1;
		gbc_btnStart.gridy = 10;
		gamePannel.add(btnStart, gbc_btnStart);
		
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopTimer();
			}
		});
		GridBagConstraints gbc_btnPause = new GridBagConstraints();
		gbc_btnPause.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPause.insets = new Insets(5, 5, 5, 0);
		gbc_btnPause.gridx = 2;
		gbc_btnPause.gridy = 10;
		gamePannel.add(btnPause, gbc_btnPause);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restartTimer();
			}
		});
		GridBagConstraints gbc_btnRestart = new GridBagConstraints();
		gbc_btnRestart.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRestart.insets = new Insets(5, 5, 5, 5);
		gbc_btnRestart.gridx = 1;
		gbc_btnRestart.gridy = 11;
		gamePannel.add(btnRestart, gbc_btnRestart);
		
		JLabel lblTimeElapsed = new JLabel("Time elapsed:");
		GridBagConstraints gbc_lblTimeElapsed = new GridBagConstraints();
		gbc_lblTimeElapsed.anchor = GridBagConstraints.WEST;
		gbc_lblTimeElapsed.weightx = 1.0;
		gbc_lblTimeElapsed.insets = new Insets(5, 5, 5, 5);
		gbc_lblTimeElapsed.gridx = 1;
		gbc_lblTimeElapsed.gridy = 8;
		gamePannel.add(lblTimeElapsed, gbc_lblTimeElapsed);
		
		lblTime = new JLabel("");
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.anchor = GridBagConstraints.WEST;
		gbc_lblTime.insets = new Insets(5, 5, 5, 0);
		gbc_lblTime.weightx = 1.0;
		gbc_lblTime.gridx = 2;
		gbc_lblTime.gridy = 8;
		gamePannel.add(lblTime, gbc_lblTime);
		
		f.pack();
		f.setVisible(true);
	}
	
	private void startTimer() {
		if (timer == null) {
			lblNrSpeciesText.setText(panel.getEnvironment().getNrSpecies() + "");
			ActionListener al = new GameLoop(panel,txtNumberFood, dataObj, this);
			timer = new Timer(UPDATE_TIME, al);
			timer.start();
		}
		else {
			timer.start();
		}
	}
	
	private void stopTimer() {
		timer.stop();
	}
	
	private void restartTimer() {
		if (timer != null) {
			timer.stop();
		}
		timer.restart();
		createGui();
	}
	
	public void updateLabels(String [] textArray) {
		this.lblNrSpeciesText.setText(textArray[0]);
		this.lblAvgSpeedText.setText(textArray[1]);
		this.lblAvgSizeText.setText(textArray[2]);
		this.lblAvgAgeText.setText(textArray[3]);
		this.lblAvgScentText.setText(textArray[4]);
		this.lblEnergyConsumptionText.setText(textArray[5]);
		this.lblTime.setText(textArray[6]);
	}
}

