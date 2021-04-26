package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import user_input.OptionData;
import user_input.PopulationSettings;

import javax.swing.border.LineBorder;

import java.awt.Button;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import utility_functions.Constants;

/**
 * Class that is the start of the application. It shows an option menu in which
 * the user can select the prefered options.
 * @author Bram van Wersch
 */
public class OptionMenu extends JFrame {

	private JPanel contentPane;
	private JSpinner plantEnergySpinner;
	private JSpinner plantSizeSpinner;
	private JScrollPane scrollPane;
	private JPanel scrollPanel;
	private int numberOfSpecies;
	private ArrayList<ArrayList<JSpinner>> spinnerValues;
	private ArrayList<JLabel> speciesColors;
	private ArrayList<JComboBox<String>> speciesTypes;
	private ArrayList<JTextField> speciesNames;
	private ArrayList<JPanel> populationPanels;
	private OptionData data;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new OptionMenu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor that creates the frame and innitialises ArrayLists for 
	 * collecting the neccesairy data.
	 */
	public OptionMenu() {
		numberOfSpecies = 0;
		spinnerValues =  new ArrayList<ArrayList<JSpinner>>();
		speciesColors = new ArrayList<JLabel>();
		speciesTypes = new ArrayList<JComboBox<String>>();
		speciesNames = new ArrayList<JTextField>();
		populationPanels = new ArrayList<JPanel>();
		data = new OptionData();
		initGUI();
		setVisible(true);
	}
	
	/**
	 * Creates the gui 
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[]{1.0, 3.0};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel environmentPanel = new JPanel();
		contentPane.add(environmentPanel);
		GridBagLayout gbl_environmentPanel = new GridBagLayout();
		gbl_environmentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_environmentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_environmentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_environmentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		environmentPanel.setLayout(gbl_environmentPanel);
		
		GridBagConstraints gbc_plantPanel = new GridBagConstraints();
		gbc_plantPanel.gridheight = 2;
		gbc_plantPanel.gridwidth = 2;
		gbc_plantPanel.insets = new Insets(20, 20, 5, 20);
		gbc_plantPanel.fill = GridBagConstraints.BOTH;
		gbc_plantPanel.gridx = 0;
		gbc_plantPanel.gridy = 0;
		JPanel plantPanel = new JPanel();
		plantPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "plant properties", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		environmentPanel.add(plantPanel, gbc_plantPanel);
		GridBagLayout gbl_plantPanel = new GridBagLayout();
		plantPanel.setLayout(gbl_plantPanel);
		
		GridBagConstraints gbc_lblPlantEnergy = new GridBagConstraints();
		gbc_lblPlantEnergy.anchor = GridBagConstraints.WEST;
		gbc_lblPlantEnergy.insets = new Insets(10, 5, 5, 5);
		gbc_lblPlantEnergy.gridx = 0;
		gbc_lblPlantEnergy.gridy = 0;
		JLabel lblPlantEnergy = new JLabel("plant Energy(50-500):");
		plantPanel.add(lblPlantEnergy, gbc_lblPlantEnergy);
		lblPlantEnergy.setToolTipText("Energy each plant item gives herbivores and omnivores.");
		lblPlantEnergy.setHorizontalAlignment(SwingConstants.LEFT);
		
		SpinnerNumberModel plantEnergyModel = new SpinnerNumberModel(100, 50, 500, 10);
		plantEnergySpinner = new JSpinner(plantEnergyModel);
		GridBagConstraints gbc_plantEnergyTxt = new GridBagConstraints();
		gbc_plantEnergyTxt.insets = new Insets(10, 10, 5, 5);
		gbc_plantEnergyTxt.gridx = 1;
		gbc_plantEnergyTxt.gridy = 0;
		plantPanel.add(plantEnergySpinner, gbc_plantEnergyTxt);
		
		GridBagConstraints gbc_lblPlantSize = new GridBagConstraints();
		gbc_lblPlantSize.anchor = GridBagConstraints.WEST;
		gbc_lblPlantSize.insets = new Insets(0, 5, 5, 5);
		gbc_lblPlantSize.gridx = 0;
		gbc_lblPlantSize.gridy = 1;
		JLabel lblPlantSize = new JLabel("Plant Size(1-100):");
		plantPanel.add(lblPlantSize, gbc_lblPlantSize);
		lblPlantSize.setToolTipText("Size of the Plant for herbivores and omnivores.");
		lblPlantSize.setHorizontalAlignment(SwingConstants.LEFT);
		
		SpinnerNumberModel plantSizeModel = new SpinnerNumberModel(5, 1, 100, 1);
		plantSizeSpinner = new JSpinner(plantSizeModel);
		GridBagConstraints gbc_plantSizeTxt = new GridBagConstraints();
		gbc_plantSizeTxt.insets = new Insets(0, 10, 5, 5);
		gbc_plantSizeTxt.gridx = 1;
		gbc_plantSizeTxt.gridy = 1;
		plantPanel.add(plantSizeSpinner, gbc_plantSizeTxt);
	
		GridBagConstraints gbc_terrainPanel = new GridBagConstraints();
		gbc_terrainPanel.gridwidth = 2;
		gbc_terrainPanel.insets = new Insets(20, 20, 5, 20);
		gbc_terrainPanel.fill = GridBagConstraints.BOTH;
		gbc_terrainPanel.gridx = 0;
		gbc_terrainPanel.gridy = 2;
		JPanel terrainPanel = new JPanel();
		terrainPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Terrain properties", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		environmentPanel.add(terrainPanel, gbc_terrainPanel);
		GridBagLayout gbl_terrainPanel = new GridBagLayout();
		terrainPanel.setLayout(gbl_terrainPanel);
		
		GridBagConstraints gbc_lblTbd = new GridBagConstraints();
		gbc_lblTbd.insets = new Insets(1, 5, 5, 5);
		gbc_lblTbd.gridx = 0;
		gbc_lblTbd.gridy = 0;
		JLabel lblTbd = new JLabel("TBD");
		terrainPanel.add(lblTbd, gbc_lblTbd);
		
		JPanel populationPanel = new JPanel();
		contentPane.add(populationPanel);
		GridBagLayout gbl_populationPanel = new GridBagLayout();
		gbl_populationPanel.rowHeights = new int[] {50, Constants.WINDOW_HEIGTH - 150};
		int column_width = (int) ((Constants.WINDOW_WIDTH * (2.3 / 3.0) / 2));
		gbl_populationPanel.columnWidths = new int[] {column_width, column_width};
		gbl_populationPanel.columnWeights = new double[]{1.0, 1.0};
		gbl_populationPanel.rowWeights = new double[]{1.0, 1.0};
		populationPanel.setLayout(gbl_populationPanel);
		
		JButton btnMoreSpecies = new JButton("New Hetrotroph Species");
		btnMoreSpecies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSpeciesPanel(numberOfSpecies);
			}});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridy = 0;
		gbc_button.gridx = 0;
		populationPanel.add(btnMoreSpecies, gbc_button);
		
		
		JButton btnMorePlantSpecies = new JButton("New Autotroph Species");
		btnMorePlantSpecies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSpeciesPanel(numberOfSpecies);
			}});
		GridBagConstraints gbc_plant_button = new GridBagConstraints();
		gbc_plant_button.gridy = 0;
		gbc_plant_button.gridx = 1;
		populationPanel.add(btnMorePlantSpecies, gbc_plant_button);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		gbc_scrollPane.gridwidth = 2;
		populationPanel.add(scrollPane, gbc_scrollPane);
		
		scrollPanel = new JPanel();
		GridBagLayout gbl_scrollPanel = new GridBagLayout();
		scrollPanel.setLayout(gbl_scrollPanel);
		
		JButton startGameButton = new JButton("Start game");
		startGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveData();
				setVisible(false);
				dispose();
				new Run(data);
			}
		});
		GridBagConstraints gbc_startGameButton = new GridBagConstraints();
		gbc_startGameButton = new GridBagConstraints();
		gbc_startGameButton.fill = GridBagConstraints.VERTICAL;
		gbc_startGameButton.gridx = 0;
		gbc_startGameButton.gridy = 3;
		gbc_startGameButton.gridwidth = 3;
		contentPane.add(startGameButton, gbc_startGameButton);
		
		//Add a species at the start to not create an empty playing field.
		for (int i = 0; i < Constants.DEFAULT_START_POPULATIONS; i++) {
			addSpeciesPanel(numberOfSpecies);
		}	
	}

	/**
	 * Creates a JPannel that contains a default population. This function is
	 * called when the user requests a new population.
	 * @return a JPannel that contains default values for a population.
	 */
	private JPanel addPopulationLabel() {
		ArrayList<JSpinner> textList = new ArrayList<JSpinner>();
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblType = new JLabel("Type:");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.WEST;
		gbc_lblType.insets = new Insets(10, 10, 5, 5);
		gbc_lblType.fill = GridBagConstraints.BOTH;
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 0;
		panel_1.add(lblType, gbc_lblType);
		
		GridBagConstraints gbc_typeComboBox = new GridBagConstraints();
		gbc_typeComboBox.anchor = GridBagConstraints.WEST;
		gbc_typeComboBox.insets = new Insets(10, 0, 5, 5);
		gbc_typeComboBox.fill = GridBagConstraints.BOTH;
		gbc_typeComboBox.gridx = 1;
		gbc_typeComboBox.gridy = 0;
		JComboBox<String> typeComboBox = new JComboBox<String>();
		typeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Herbivore", "Omnivore", "Carnivore"}));
		panel_1.add(typeComboBox, gbc_typeComboBox);
		speciesTypes.add(typeComboBox);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(5, 10, 5, 5);
		gbc_lblName.fill = GridBagConstraints.BOTH;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		panel_1.add(lblName, gbc_lblName);
		
		JTextField nameTxt = new JTextField(getDefaultName());
		GridBagConstraints gbc_nameTxt = new GridBagConstraints();
		gbc_nameTxt.anchor = GridBagConstraints.WEST;
		gbc_nameTxt.insets = new Insets(0, 0, 5, 5);
		gbc_nameTxt.fill = GridBagConstraints.BOTH;
		gbc_nameTxt.gridx = 1;
		gbc_nameTxt.gridy = 1;
		panel_1.add(nameTxt, gbc_nameTxt);
		nameTxt.setColumns(10);
		speciesNames.add(nameTxt);
		
		JLabel noIndLabel = new JLabel(String.format("No ind.(1, %d):", Constants.MAX_INDIVIDUALS));
		noIndLabel.setToolTipText("Number of individuals.\r\n");
		GridBagConstraints gbc_noIndLabel = new GridBagConstraints();
		gbc_noIndLabel.anchor = GridBagConstraints.WEST;
		gbc_noIndLabel.insets = new Insets(5, 10, 5, 5);
		gbc_noIndLabel.fill = GridBagConstraints.BOTH;
		gbc_noIndLabel.gridx = 0;
		gbc_noIndLabel.gridy = 2;
		panel_1.add(noIndLabel, gbc_noIndLabel);
		
		SpinnerNumberModel indModel = new SpinnerNumberModel(Constants.DEFAULT_START_INDIVIDUALS, 1, Constants.MAX_INDIVIDUALS, 1);
		JSpinner noIndField = new JSpinner(indModel);
		GridBagConstraints gbc_noIndField = new GridBagConstraints();
		gbc_noIndField.anchor = GridBagConstraints.WEST;
		gbc_noIndField.insets = new Insets(0, 0, 5, 5);
		gbc_noIndField.fill = GridBagConstraints.BOTH;
		gbc_noIndField.gridx = 1;
		gbc_noIndField.gridy = 2;
		panel_1.add(noIndField, gbc_noIndField);
		textList.add(noIndField);
		
		JLabel sizeLabel = new JLabel(String.format("Size (1, %d):", Constants.MAX_SIZE));
		sizeLabel.setToolTipText("Starting diameter of the species in pixels.");
		GridBagConstraints gbc_sizeLabel = new GridBagConstraints();
		gbc_sizeLabel.anchor = GridBagConstraints.WEST;
		gbc_sizeLabel.insets = new Insets(5, 10, 5, 5);
		gbc_sizeLabel.fill = GridBagConstraints.BOTH;
		gbc_sizeLabel.gridx = 0;
		gbc_sizeLabel.gridy = 3;
		panel_1.add(sizeLabel, gbc_sizeLabel);
		
		SpinnerNumberModel sizeModel = new SpinnerNumberModel(Constants.DEFAULT_SIZE, 1, Constants.MAX_SIZE, 1);
		JSpinner sizeSpinner = new JSpinner(sizeModel);
		GridBagConstraints gbc_sizeSpinner = new GridBagConstraints();
		gbc_sizeSpinner.anchor = GridBagConstraints.WEST;
		gbc_sizeSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_sizeSpinner.fill = GridBagConstraints.BOTH;
		gbc_sizeSpinner.gridx = 1;
		gbc_sizeSpinner.gridy = 3;
		panel_1.add(sizeSpinner, gbc_sizeSpinner);
		textList.add(sizeSpinner);
		
		JLabel speedLabel = new JLabel(String.format("Speed (1, %d):", Constants.MAX_SPEED));
		speedLabel.setToolTipText("The starting speed of the species in pixels moved per frame.");
		GridBagConstraints gbc_speedLabel = new GridBagConstraints();
		gbc_speedLabel.anchor = GridBagConstraints.WEST;
		gbc_speedLabel.insets = new Insets(5, 10, 5, 5);
		gbc_speedLabel.fill = GridBagConstraints.BOTH;
		gbc_speedLabel.gridx = 0;
		gbc_speedLabel.gridy = 4;
		panel_1.add(speedLabel, gbc_speedLabel);
		
		SpinnerNumberModel speedModel = new SpinnerNumberModel(Constants.DEFAULT_SPEED, 1, Constants.MAX_SPEED, 1);
		JSpinner speedSpinner = new JSpinner(speedModel);
		GridBagConstraints gbc_speedSpinner = new GridBagConstraints();
		gbc_speedSpinner.anchor = GridBagConstraints.WEST;
		gbc_speedSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_speedSpinner.fill = GridBagConstraints.BOTH;
		gbc_speedSpinner.gridx = 1;
		gbc_speedSpinner.gridy = 4;
		panel_1.add(speedSpinner, gbc_speedSpinner);
		textList.add(speedSpinner);
		
		JLabel maxAgeLabel = new JLabel(String.format("Max age (1, %d):", Constants.MAX_MAX_AGE));
		maxAgeLabel.setToolTipText("Age at which the species dies if that did not happen before due to other circumstances.");
		GridBagConstraints gbc_maxAgeLabel = new GridBagConstraints();
		gbc_maxAgeLabel.anchor = GridBagConstraints.WEST;
		gbc_maxAgeLabel.insets = new Insets(5, 10, 5, 5);
		gbc_maxAgeLabel.fill = GridBagConstraints.BOTH;
		gbc_maxAgeLabel.gridx = 2;
		gbc_maxAgeLabel.gridy = 0;
		panel_1.add(maxAgeLabel, gbc_maxAgeLabel);
		
		SpinnerNumberModel maxAgeModel = new SpinnerNumberModel(Constants.DEFAULT_MAX_AGE, 1, Constants.MAX_MAX_AGE, 1);
		JSpinner maxAgeSpinner = new JSpinner(maxAgeModel);
		GridBagConstraints gbc_maxAgeSpinner = new GridBagConstraints();
		gbc_maxAgeSpinner.anchor = GridBagConstraints.WEST;
		gbc_maxAgeSpinner.insets = new Insets(10, 0, 5, 5);
		gbc_maxAgeSpinner.fill = GridBagConstraints.BOTH;
		gbc_maxAgeSpinner.gridx = 3;
		gbc_maxAgeSpinner.gridy = 0;
		panel_1.add(maxAgeSpinner, gbc_maxAgeSpinner);
		textList.add(maxAgeSpinner);
		
		JLabel scentRangeLabel = new JLabel(String.format("Scent range (0, %d):", Constants.MAX_SCENT_RANGE));
		scentRangeLabel.setToolTipText("Range at which a species can smell another species.");
		GridBagConstraints gbc_scentRangeLabel = new GridBagConstraints();
		gbc_scentRangeLabel.anchor = GridBagConstraints.WEST;
		gbc_scentRangeLabel.insets = new Insets(5, 10, 5, 5);
		gbc_scentRangeLabel.fill = GridBagConstraints.BOTH;
		gbc_scentRangeLabel.gridx = 2;
		gbc_scentRangeLabel.gridy = 1;
		panel_1.add(scentRangeLabel, gbc_scentRangeLabel);
		
		SpinnerNumberModel scentRangeModel = new SpinnerNumberModel(Constants.DEFAULT_SCENT_RANGE, 0, Constants.MAX_SCENT_RANGE, 1);
		JSpinner scentRangeSpinner = new JSpinner(scentRangeModel);
		GridBagConstraints gbc_scentRangeSpinner = new GridBagConstraints();
		gbc_scentRangeSpinner.anchor = GridBagConstraints.WEST;
		gbc_scentRangeSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_scentRangeSpinner.fill = GridBagConstraints.BOTH;
		gbc_scentRangeSpinner.gridx = 3;
		gbc_scentRangeSpinner.gridy = 1;
		panel_1.add(scentRangeSpinner, gbc_scentRangeSpinner);
		textList.add(scentRangeSpinner);
		
		JLabel colorLabel = new JLabel();
		GridBagConstraints gbc_colorLabel = new GridBagConstraints();
		colorLabel.setOpaque(true);
		colorLabel.setBackground(Color.CYAN);
		gbc_colorLabel.anchor = GridBagConstraints.WEST;
		gbc_colorLabel.insets = new Insets(5, 10, 5, 5);
		gbc_colorLabel.fill = GridBagConstraints.BOTH;
		gbc_colorLabel.gridx = 2;
		gbc_colorLabel.gridy = 2;
		panel_1.add(colorLabel, gbc_colorLabel);
		speciesColors.add(colorLabel);
		
		JButton newColorBtn = new JButton("Choose color");
		newColorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color speciesColor = JColorChooser.showDialog(null, "Choose a color", Color.GREEN);
				if (speciesColor != null) {
					colorLabel.setBackground(speciesColor);
				}
			}
		});
		GridBagConstraints gbc_newColorBtn = new GridBagConstraints();
		gbc_newColorBtn.anchor = GridBagConstraints.WEST;
		gbc_newColorBtn.insets = new Insets(0, 0, 5, 5);
		gbc_newColorBtn.fill = GridBagConstraints.BOTH;
		gbc_newColorBtn.gridx = 3;
		gbc_newColorBtn.gridy = 2;
		panel_1.add(newColorBtn, gbc_newColorBtn);
		
		JLabel eatSizeFactorLabel = new JLabel(String.format("Eat size factor (0, %d):", Constants.MAX_EAT_SIZE_FACTOR));
		eatSizeFactorLabel.setToolTipText("<html>\r\nFactor that tells how mutch bigger a species has to be to be<br>\r\nable to eat its respective plant. A value of 1 means that the<br>\r\nspecies has to be as big or bigger to be able to eat its plant.<br>\r\n</html>\r\n");
		GridBagConstraints gbc_eatSizeFactorLbl = new GridBagConstraints();
		gbc_eatSizeFactorLbl.anchor = GridBagConstraints.WEST;
		gbc_eatSizeFactorLbl.insets = new Insets(5, 10, 5, 5);
		gbc_eatSizeFactorLbl.fill = GridBagConstraints.BOTH;
		gbc_eatSizeFactorLbl.gridx = 2;
		gbc_eatSizeFactorLbl.gridy = 3;
		panel_1.add(eatSizeFactorLabel, gbc_eatSizeFactorLbl);
		
		SpinnerNumberModel eatSizeFactorModel = new SpinnerNumberModel(Constants.DEFAULT_EAT_SIZE_FACTOR, 0, Constants.MAX_SCENT_RANGE, 0.1);
		JSpinner eatSizeFactorSpinner = new JSpinner(eatSizeFactorModel);
		GridBagConstraints gbc_eatSizeFactorSpinner = new GridBagConstraints();
		gbc_eatSizeFactorSpinner.anchor = GridBagConstraints.WEST;
		gbc_eatSizeFactorSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_eatSizeFactorSpinner.fill = GridBagConstraints.BOTH;
		gbc_eatSizeFactorSpinner.gridx = 3;
		gbc_eatSizeFactorSpinner.gridy = 3;
		panel_1.add(eatSizeFactorSpinner, gbc_eatSizeFactorSpinner);
		textList.add(eatSizeFactorSpinner);
		
		spinnerValues.add(textList);
		
		
		JButton btnMoreSpecies = new JButton("X");
		btnMoreSpecies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSpeciesPanel(panel_1);
			}});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridy = 0;
		gbc_button.gridx = 4;
		panel_1.add(btnMoreSpecies, gbc_button);
		
		return panel_1;
	}

	/**
	 * Saves the data collected by this class into OptionDatat and 
	 * PopulationSettings Objects for the global and individual population
	 * setting respectively.
	 */
	private void saveData() {
		data.setPlantEnergy((int) plantEnergySpinner.getValue());
		data.setPlantSize((int) plantSizeSpinner.getValue());
		for(int i = 0; i < speciesTypes.size(); i++) {
			PopulationSettings p = new PopulationSettings((String) speciesTypes.get(i).getSelectedItem(),
					speciesNames.get(i).getText(), (int) spinnerValues.get(i).get(0).getValue(), 
					(int) spinnerValues.get(i).get(1).getValue(), (int) spinnerValues.get(i).get(2).getValue(),
					(int) spinnerValues.get(i).get(3).getValue(), (int) spinnerValues.get(i).get(4).getValue(),
					speciesColors.get(i).getBackground(), (double) spinnerValues.get(i).get(5).getValue());
			data.addPopulationSettings(p);
		}
	}
	
	private void addSpeciesPanel(int panel_no) {
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridy = (int) (panel_no / 2);
		gbc_panel.gridx = panel_no % 2;
		gbc_panel.insets = new Insets(25, 25, 25, 25);
		numberOfSpecies += 1;
		JPanel panel = addPopulationLabel();
		populationPanels.add(panel);
		scrollPanel.add(panel, gbc_panel);
		scrollPane.setViewportView(scrollPanel);
	}
	
	private void removeSpeciesPanel(JPanel remove_panel) {
		int remove_index = populationPanels.indexOf(remove_panel);
		scrollPanel.remove(populationPanels.get(remove_index));
		populationPanels.remove(remove_index);
		numberOfSpecies -= 1;
		int count = 0;
		// make sure to re-add all panels to reconfigure correct placement
		for (JPanel panel: populationPanels) {
			int panel_index = populationPanels.indexOf(panel);
			scrollPanel.remove(populationPanels.get(panel_index));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridy = (int) (count / 2);
			gbc_panel.gridx = count % 2;
			gbc_panel.insets = new Insets(25, 25, 25, 25);
			scrollPanel.add(panel, gbc_panel);
			count++;
		}
		scrollPane.setViewportView(scrollPanel);
	}
	
	/**
	 * Creates a default name to make sure that populations can be 
	 * distinguished based on name when the simulation is running
	 * @return a String that is the name of a population.
	 */
	private String getDefaultName() {
		return "Pop. " + (speciesNames.size() +1);
	}
	
}
