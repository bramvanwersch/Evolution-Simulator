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
import user_input.HetrotrophPopulationSettings;

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
	private ArrayList<PopulationPanel> populationPanels;
	private  ArrayList<JButton> populationRemoveButtons;
	private OptionData data;
	private int counter;
	
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
		counter = 1;
		populationPanels = new ArrayList<PopulationPanel>();
		populationRemoveButtons = new ArrayList<JButton>();
		data = new OptionData();
		initGUI();
		createStartingSpecies();
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
				addHetrotrophSpeciesPanel(numberOfSpecies);
			}});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridy = 0;
		gbc_button.gridx = 0;
		populationPanel.add(btnMoreSpecies, gbc_button);
		
		
		JButton btnMorePlantSpecies = new JButton("New Autotroph Species");
		btnMorePlantSpecies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAutotrophSpeciesPanel(numberOfSpecies);
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
	}
	
	private void createStartingSpecies() {
		for (int i = 0; i < Constants.DEFAULT_HETROTROPH_START_POPULATIONS; i++) {
			addHetrotrophSpeciesPanel(numberOfSpecies);
		}
		for (int i = 0; i < Constants.DEFAULT_AUTOTROPH_START_POPULATIONS; i++) {
			addAutotrophSpeciesPanel(numberOfSpecies);
		}
	}

	/**
	 * Saves the data collected by this class into OptionData and 
	 * PopulationSettings Objects for the global and individual population
	 * setting respectively.
	 */
	private void saveData() {
		data.setPlantEnergy((int) plantEnergySpinner.getValue());
		data.setPlantSize((int) plantSizeSpinner.getValue());
		for (PopulationPanel panel : populationPanels) {
			data.addPopulationSettings(panel.getSettings());
		}
	}
	
	private void addPanel(int panel_no, PopulationPanel panel) {
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridy = (int) (panel_no / 2);
		gbc_panel.gridx = panel_no % 2;
		gbc_panel.insets = new Insets(25, 25, 25, 25);
		numberOfSpecies += 1;
		populationPanels.add(panel);
		scrollPanel.add(panel, gbc_panel);
		scrollPane.setViewportView(scrollPanel);
		counter++;
		
		// add the top bar
		JButton removeButton = new JButton("X");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSpeciesPanel(panel);
				scrollPanel.remove(removeButton);
			}});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridy = (int) (panel_no / 2);
		gbc_button.gridx = panel_no % 2;
		gbc_button.anchor = GridBagConstraints.NORTHEAST;
		gbc_button.insets = new Insets(0, 0, 0, 25);
		populationRemoveButtons.add(removeButton);
		
		scrollPanel.add(removeButton, gbc_button);
		scrollPane.setViewportView(scrollPanel);
	}
	
	private void addHetrotrophSpeciesPanel(int panel_no) {
		PopulationPanel panel = new HetrotrophPopulationPanel(getDefaultName());
		addPanel(panel_no, panel);
	}
	
	private void addAutotrophSpeciesPanel(int panel_no) {
		PopulationPanel panel = new AutotrophPopulationPanel(getDefaultName());
		addPanel(panel_no, panel);
	}
	
	private void removeSpeciesPanel(JPanel remove_panel) {
		int remove_index = populationPanels.indexOf(remove_panel);
		scrollPanel.remove(populationPanels.get(remove_index));
		populationPanels.remove(remove_index);
		populationRemoveButtons.remove(remove_index);
		numberOfSpecies -= 1;
		int count1 = 0;
		// make sure to re-add all panels to reconfigure correct placement
		for (JPanel panel: populationPanels) {
			int panel_index = populationPanels.indexOf(panel);
			scrollPanel.remove(populationPanels.get(panel_index));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridy = (int) (count1 / 2);
			gbc_panel.gridx = count1 % 2;
			gbc_panel.insets = new Insets(25, 25, 25, 25);
			scrollPanel.add(panel, gbc_panel);
			count1++;
		}
		
		int count2 = 0;
		for (JButton removeButton : populationRemoveButtons) {
			int button_index = populationRemoveButtons.indexOf(removeButton);
			scrollPanel.remove(populationRemoveButtons.get(button_index));
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.gridy = (int) (count2 / 2);
			gbc_button.gridx = count2 % 2;
			gbc_button.anchor = GridBagConstraints.NORTHEAST;
			gbc_button.insets = new Insets(0, 0, 0, 25);
			scrollPanel.add(removeButton, gbc_button);
			count2++;
		}
		scrollPane.setViewportView(scrollPanel);
	}
	
	/**
	 * Creates a default name to make sure that populations can be 
	 * distinguished based on name when the simulation is running
	 * @return a String that is the name of a population.
	 */
	private String getDefaultName() {
		return "Pop. " + (counter);
	}
	
}
