package gui;


import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SidePanelGui extends JPanel{
	public JTextField txtNumberFood;
	private JLabel lblNrSpeciesText;
	private JLabel lblTime;
	private JLabel lblAvgSizeText;
	private JLabel lblAvgSpeedText;
	private JLabel lblAvgScentText;
	private JLabel lblAvgAgeText;
	private JLabel lblEnergyConsumptionText;
	private int heigth;
	private int width;
	private GridBagLayout gbl_gamePanel;
	
	public SidePanelGui(int heigth, int width) {
		gbl_gamePanel = new GridBagLayout();
		this.heigth = heigth;
		this.width = width;
		drawPanel();
		
	}
	public Dimension getPreferredSize() {
        return new Dimension(width, heigth);
    }
		
//menu bar
//		JMenuBar menuBar = new JMenuBar();
//		f.setJMenuBar(menuBar);
//		
//		JMenu evolutionMenu = new JMenu("Evolution game");
//		menuBar.add(evolutionMenu);
//		
//		JMenuItem mntmNew = new JMenuItem("New...");
//		mntmNew.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				newGame();
//			}
//		});
//		evolutionMenu.add(mntmNew);
//		
//		JMenuItem mntmOpen = new JMenuItem("Restart...");
//		mntmOpen.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				restartGame();
//			}
//		});
//		evolutionMenu.add(mntmOpen);
		
		
	private void drawPanel() {
		setLayout(gbl_gamePanel);

		JLabel lblStatistics = new JLabel("Statistics:");
		GridBagConstraints gbc_lblStatistics = new GridBagConstraints();
		gbc_lblStatistics.gridwidth = 2;
		gbc_lblStatistics.insets = new Insets(5, 5, 5, 0);
		gbc_lblStatistics.gridx = 1;
		gbc_lblStatistics.gridy = 0;
		add(lblStatistics, gbc_lblStatistics);

		JLabel lblNrSpecies = new JLabel("Number of individuals:");
		lblNrSpecies.setOpaque(true);
		GridBagConstraints gbc_lblNrSpecies = new GridBagConstraints();
		gbc_lblNrSpecies.anchor = GridBagConstraints.WEST;
		gbc_lblNrSpecies.insets = new Insets(5, 5, 5, 5);
		gbc_lblNrSpecies.gridx = 1;
		gbc_lblNrSpecies.gridy = 1;
		add(lblNrSpecies, gbc_lblNrSpecies);
		
		lblNrSpeciesText = new JLabel("");
		GridBagConstraints gbc_lblNrSpeciesText = new GridBagConstraints();
		gbc_lblNrSpeciesText.anchor = GridBagConstraints.WEST;
		gbc_lblNrSpeciesText.weightx = 1.0;
		gbc_lblNrSpeciesText.insets = new Insets(5, 5, 5, 0);
		gbc_lblNrSpeciesText.gridx = 2;
		gbc_lblNrSpeciesText.gridy = 1;
		add(lblNrSpeciesText, gbc_lblNrSpeciesText);
		
		JLabel lblAvgSpeed = new JLabel("Average speed:");
		lblAvgSpeed.setOpaque(true);
		GridBagConstraints gbc_lblAvgSpeed = new GridBagConstraints();
		gbc_lblAvgSpeed.anchor = GridBagConstraints.WEST;
		gbc_lblAvgSpeed.insets = new Insets(5, 5, 5, 5);
		gbc_lblAvgSpeed.gridx = 1;
		gbc_lblAvgSpeed.gridy = 2;
		add(lblAvgSpeed, gbc_lblAvgSpeed);
		
		lblAvgSpeedText = new JLabel("");
		GridBagConstraints gbc_lblAvgSpeedText = new GridBagConstraints();
		gbc_lblAvgSpeedText.anchor = GridBagConstraints.WEST;
		gbc_lblAvgSpeedText.weightx = 1.0;
		gbc_lblAvgSpeedText.insets = new Insets(5, 5, 5, 0);
		gbc_lblAvgSpeedText.gridx = 2;
		gbc_lblAvgSpeedText.gridy = 2;
		add(lblAvgSpeedText, gbc_lblAvgSpeedText);
		
		JLabel lblAvgSize = new JLabel("Average max size:");
		lblAvgSize.setOpaque(true);
		GridBagConstraints gbc_lblAvgSize = new GridBagConstraints();
		gbc_lblAvgSize.anchor = GridBagConstraints.WEST;
		gbc_lblAvgSize.insets = new Insets(5, 5, 5, 5);
		gbc_lblAvgSize.gridx = 1;
		gbc_lblAvgSize.gridy = 3;
		add(lblAvgSize, gbc_lblAvgSize);
		
		lblAvgSizeText = new JLabel("");
		GridBagConstraints gbc_lblAvgSizeText = new GridBagConstraints();
		gbc_lblAvgSizeText.anchor = GridBagConstraints.WEST;
		gbc_lblAvgSizeText.weightx = 1.0;
		gbc_lblAvgSizeText.insets = new Insets(5, 5, 5, 0);
		gbc_lblAvgSizeText.gridx = 2;
		gbc_lblAvgSizeText.gridy = 3;
		add(lblAvgSizeText, gbc_lblAvgSizeText);
		
		JLabel lblAvgAge = new JLabel("Average max age:");
		lblAvgAge.setOpaque(true);
		GridBagConstraints gbc_lblAvgAge = new GridBagConstraints();
		gbc_lblAvgAge.anchor = GridBagConstraints.WEST;
		gbc_lblAvgAge.insets = new Insets(5, 5, 5, 5);
		gbc_lblAvgAge.gridx = 1;
		gbc_lblAvgAge.gridy = 5;
		add(lblAvgAge, gbc_lblAvgAge);
		
		lblAvgAgeText = new JLabel("");
		GridBagConstraints gbc_lblAvgAgeText = new GridBagConstraints();
		gbc_lblAvgAgeText.anchor = GridBagConstraints.WEST;
		gbc_lblAvgAgeText.insets = new Insets(5, 5, 5, 0);
		gbc_lblAvgAgeText.gridx = 2;
		gbc_lblAvgAgeText.gridy = 5;
		add(lblAvgAgeText, gbc_lblAvgAgeText);
		
		JLabel lblAvgScent = new JLabel("Average scent range:");
		lblAvgScent.setOpaque(true);
		GridBagConstraints gbc_lblAvgScent = new GridBagConstraints();
		gbc_lblAvgScent.anchor = GridBagConstraints.WEST;
		gbc_lblAvgScent.insets = new Insets(5, 5, 5, 5);
		gbc_lblAvgScent.gridx = 1;
		gbc_lblAvgScent.gridy = 6;
		add(lblAvgScent, gbc_lblAvgScent);
		
		lblAvgScentText = new JLabel("");
		GridBagConstraints gbc_lblAvgScentText = new GridBagConstraints();
		gbc_lblAvgScentText.anchor = GridBagConstraints.WEST;
		gbc_lblAvgScentText.insets = new Insets(5, 5, 5, 0);
		gbc_lblAvgScentText.gridx = 2;
		gbc_lblAvgScentText.gridy = 6;
		add(lblAvgScentText, gbc_lblAvgScentText);
		
		JLabel lblEnergyConsumption = new JLabel("Energy consumption:");
		lblEnergyConsumption.setOpaque(true);
		GridBagConstraints gbc_lblEnergyConsumption = new GridBagConstraints();
		gbc_lblEnergyConsumption.anchor = GridBagConstraints.WEST;
		gbc_lblEnergyConsumption.insets = new Insets(5, 5, 5, 5);
		gbc_lblEnergyConsumption.gridx = 1;
		gbc_lblEnergyConsumption.gridy = 7;
		add(lblEnergyConsumption, gbc_lblEnergyConsumption);
		
		lblEnergyConsumptionText = new JLabel("");
		GridBagConstraints gbc_lblEnergyConsumptionText = new GridBagConstraints();
		gbc_lblEnergyConsumptionText.anchor = GridBagConstraints.WEST;
		gbc_lblEnergyConsumptionText.insets = new Insets(5, 5, 5, 0);
		gbc_lblEnergyConsumptionText.gridx = 2;
		gbc_lblEnergyConsumptionText.gridy = 7;
		add(lblEnergyConsumptionText, gbc_lblEnergyConsumptionText);
		
//		JButton btnShowGraph = new JButton("Show Graph");
//		btnShowGraph.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new GraphBuilder(gameloop.getData().getTimeArray(), gameloop.getData().getDataArray()
//						,1000, 800, new String [] {"Time", ""}, false);
//			}
//
//	
//		});
//		GridBagConstraints gbc_btnShowGraph = new GridBagConstraints();
//		gbc_btnShowGraph.fill = GridBagConstraints.HORIZONTAL;
//		gbc_btnShowGraph.insets = new Insets(5, 5, 5, 5);
//		gbc_btnShowGraph.gridx = 2;
//		gbc_btnShowGraph.gridy = 11;
//		gamePannel.add(btnShowGraph, gbc_btnShowGraph);
//	
		
		JLabel lblTimeElapsed = new JLabel("Time elapsed:");
		GridBagConstraints gbc_lblTimeElapsed = new GridBagConstraints();
		gbc_lblTimeElapsed.anchor = GridBagConstraints.WEST;
		gbc_lblTimeElapsed.weightx = 1.0;
		gbc_lblTimeElapsed.insets = new Insets(5, 5, 5, 5);
		gbc_lblTimeElapsed.gridx = 1;
		gbc_lblTimeElapsed.gridy = 8;
		add(lblTimeElapsed, gbc_lblTimeElapsed);
		
		lblTime = new JLabel("");
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.anchor = GridBagConstraints.WEST;
		gbc_lblTime.insets = new Insets(5, 5, 5, 0);
		gbc_lblTime.weightx = 1.0;
		gbc_lblTime.gridx = 2;
		gbc_lblTime.gridy = 8;
		add(lblTime, gbc_lblTime);
		
//		JLabel lblNrFood = new JLabel("Regen of food:");
//		GridBagConstraints gbc_lblNrFood = new GridBagConstraints();
//		gbc_lblNrFood.anchor = GridBagConstraints.WEST;
//		gbc_lblNrFood.insets = new Insets(5, 5, 5, 5);
//		gbc_lblNrFood.gridx = 1;
//		gbc_lblNrFood.gridy = 9;
//		add(lblNrFood, gbc_lblNrFood);
//		
//		txtNumberFood = new JTextField();
//		txtNumberFood.setText(FOOD_REGENERATION_RATE + "");
//		GridBagConstraints gbc_txtNumberFood = new GridBagConstraints();
//		gbc_txtNumberFood.anchor = GridBagConstraints.WEST;
//		gbc_txtNumberFood.weightx = 1.0;
//		gbc_txtNumberFood.insets = new Insets(0, 0, 5, 0);
//		gbc_txtNumberFood.fill = GridBagConstraints.HORIZONTAL;
//		gbc_txtNumberFood.gridx = 2;
//		gbc_txtNumberFood.gridy = 9;
//		add(txtNumberFood, gbc_txtNumberFood);
//		txtNumberFood.setColumns(10);
	}
	

//	
//	//broken needs fixing
//	private void restartGame() {
//		if (timer != null) {
//			timer.stop();
//			initGUI();
//			this.environment = new Environment(this.options);
//			panel.repaint();
//			lblNrSpeciesText.setText(panel.getEnvironment().getNrSpecies() + "");
//			this.gameloop = new GameLoop(panel,txtNumberFood, this);
//			timer.restart();
//			}
//	}
	

	public void updateLabels(String [] textArray){
		this.lblNrSpeciesText.setText(textArray[0]);
		this.lblAvgSpeedText.setText(textArray[1]);
		this.lblAvgSizeText.setText(textArray[2]);
		this.lblAvgAgeText.setText(textArray[3]);
		this.lblAvgScentText.setText(textArray[4]);
		this.lblEnergyConsumptionText.setText(textArray[5]);
		this.lblTime.setText(textArray[6]);
	}

}

