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
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class OptionMenu extends JFrame {

	private JPanel contentPane;
	private final JTextField foodEnergyTxt = new JTextField();
	private final JTextField foodSizeTxt = new JTextField();
	private final JTextField eatSizeFactorTxt = new JTextField();
	private JTextField nameTxt;
	private JTextField noIndField;
	private JTextField sizeTxt;
	private JTextField speedTxt;
	private JTextField maxAgeTxt;
	private JScrollPane scrollPane;
	private JPanel scrollPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionMenu frame = new OptionMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OptionMenu() {
		eatSizeFactorTxt.setText("1");
		eatSizeFactorTxt.setColumns(10);
		foodSizeTxt.setText("5");
		foodSizeTxt.setColumns(10);
		foodEnergyTxt.setText("100");
		foodEnergyTxt.setColumns(10);
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 2;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, gbc_tabbedPane);
		
		JPanel environmentPanel = new JPanel();
		tabbedPane.addTab("Environment", null, environmentPanel, null);
		GridBagLayout gbl_environmentPanel = new GridBagLayout();
		gbl_environmentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_environmentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_environmentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_environmentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		environmentPanel.setLayout(gbl_environmentPanel);
		
		GridBagConstraints gbc_foodPanel = new GridBagConstraints();
		gbc_foodPanel.gridheight = 2;
		gbc_foodPanel.gridwidth = 2;
		gbc_foodPanel.insets = new Insets(20, 20, 5, 20);
		gbc_foodPanel.fill = GridBagConstraints.BOTH;
		gbc_foodPanel.gridx = 0;
		gbc_foodPanel.gridy = 0;
		JPanel foodPanel = new JPanel();
		foodPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Food properties", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		environmentPanel.add(foodPanel, gbc_foodPanel);
		GridBagLayout gbl_foodPanel = new GridBagLayout();
		foodPanel.setLayout(gbl_foodPanel);
		
		GridBagConstraints gbc_lblFoodEnergy = new GridBagConstraints();
		gbc_lblFoodEnergy.anchor = GridBagConstraints.WEST;
		gbc_lblFoodEnergy.insets = new Insets(10, 5, 5, 5);
		gbc_lblFoodEnergy.gridx = 0;
		gbc_lblFoodEnergy.gridy = 0;
		JLabel lblFoodEnergy = new JLabel("Food Energy:");
		foodPanel.add(lblFoodEnergy, gbc_lblFoodEnergy);
		lblFoodEnergy.setToolTipText("Energy each food item gives herbivores and omnivores.");
		lblFoodEnergy.setHorizontalAlignment(SwingConstants.LEFT);
		
		GridBagConstraints gbc_foodEnergyTxt = new GridBagConstraints();
		gbc_foodEnergyTxt.insets = new Insets(10, 10, 5, 5);
		gbc_foodEnergyTxt.gridx = 1;
		gbc_foodEnergyTxt.gridy = 0;
		foodPanel.add(foodEnergyTxt, gbc_foodEnergyTxt);
		
		GridBagConstraints gbc_lblFoodSize = new GridBagConstraints();
		gbc_lblFoodSize.anchor = GridBagConstraints.WEST;
		gbc_lblFoodSize.insets = new Insets(0, 5, 5, 5);
		gbc_lblFoodSize.gridx = 0;
		gbc_lblFoodSize.gridy = 1;
		JLabel lblFoodSize = new JLabel("Food Size:");
		foodPanel.add(lblFoodSize, gbc_lblFoodSize);
		lblFoodSize.setToolTipText("Size of the food for herbivores and omnivores.");
		lblFoodSize.setHorizontalAlignment(SwingConstants.LEFT);
		
		GridBagConstraints gbc_foodSizeTxt = new GridBagConstraints();
		gbc_foodSizeTxt.insets = new Insets(0, 10, 5, 5);
		gbc_foodSizeTxt.gridx = 1;
		gbc_foodSizeTxt.gridy = 1;
		foodPanel.add(foodSizeTxt, gbc_foodSizeTxt);
		
		GridBagConstraints gbc_lblEatSizeFactor = new GridBagConstraints();
		gbc_lblEatSizeFactor.anchor = GridBagConstraints.WEST;
		gbc_lblEatSizeFactor.insets = new Insets(0, 5, 5, 5);
		gbc_lblEatSizeFactor.gridx = 0;
		gbc_lblEatSizeFactor.gridy = 2;
		JLabel lblEatSizeFactor = new JLabel("Eat Size Factor:");
		lblEatSizeFactor.setToolTipText("<html>\r\nFactor that tells how mutch bigger a species has to be to be<br>\r\nable to eat its respective food. A value of 1 means that the<br>\r\nspecies has to be as big or bigger to be able to eat its food.<br>\r\n</html>\r\n");
		foodPanel.add(lblEatSizeFactor, gbc_lblEatSizeFactor);
		lblEatSizeFactor.setHorizontalAlignment(SwingConstants.LEFT);
		
		GridBagConstraints gbc_eatSizeFactorTxt = new GridBagConstraints();
		gbc_eatSizeFactorTxt.insets = new Insets(0, 10, 5, 5);
		gbc_eatSizeFactorTxt.gridx = 1;
		gbc_eatSizeFactorTxt.gridy = 2;
		foodPanel.add(eatSizeFactorTxt, gbc_eatSizeFactorTxt);
		
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
		tabbedPane.addTab("Populations", null, populationPanel, null);
		tabbedPane.setEnabledAt(1, true);
		GridBagLayout gbl_populationPanel = new GridBagLayout();
		gbl_populationPanel.rowHeights = new int[] {0};
		gbl_populationPanel.columnWidths = new int[] {0};
		gbl_populationPanel.columnWeights = new double[]{1.0};
		gbl_populationPanel.rowWeights = new double[]{1.0};
		populationPanel.setLayout(gbl_populationPanel);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		populationPanel.add(scrollPane, gbc_scrollPane);
		
		scrollPanel = new JPanel();
		GridBagLayout gbl_scrollPanel = new GridBagLayout();
		gbl_scrollPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_scrollPanel.rowHeights = new int[] {0};
		gbl_scrollPanel.columnWidths = new int[] {0};
		scrollPanel.setLayout(gbl_scrollPanel);
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 8; j++) {
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.gridx = i;
				gbc_panel.gridy = j;
				scrollPanel.add(addPopulationLabel(), gbc_panel);
			}
		}
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		JButton btnMoreSpecies = new JButton("New Species");
		scrollPanel.add(btnMoreSpecies, gbc_panel);
		scrollPane.setViewportView(scrollPanel);		
	}

	private JPanel addPopulationLabel() {
		JPanel panel_1 = new JPanel();
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		panel_1.setLayout(gbl_panel_1);
		
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.WEST;
		gbc_lblType.insets = new Insets(10, 10, 5, 5);
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 0;
		JLabel lblType = new JLabel("Type:");
		panel_1.add(lblType, gbc_lblType);
		
		GridBagConstraints gbc_typeComboBox = new GridBagConstraints();
		gbc_typeComboBox.anchor = GridBagConstraints.WEST;
		gbc_typeComboBox.insets = new Insets(10, 0, 5, 5);
		gbc_typeComboBox.gridx = 1;
		gbc_typeComboBox.gridy = 0;
		JComboBox<String> typeComboBox = new JComboBox();
		typeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Herbivore", "Omnivore", "Carnivore"}));
		panel_1.add(typeComboBox, gbc_typeComboBox);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(5, 10, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		panel_1.add(lblName, gbc_lblName);
		
		nameTxt = new JTextField();
		GridBagConstraints gbc_nameTxt = new GridBagConstraints();
		gbc_nameTxt.anchor = GridBagConstraints.WEST;
		gbc_nameTxt.insets = new Insets(0, 0, 5, 5);
		gbc_nameTxt.gridx = 1;
		gbc_nameTxt.gridy = 1;
		panel_1.add(nameTxt, gbc_nameTxt);
		nameTxt.setColumns(10);
		
		JLabel noIndLabel = new JLabel("No ind.:");
		noIndLabel.setToolTipText("Number of individuals.\r\n");
		GridBagConstraints gbc_noIndLabel = new GridBagConstraints();
		gbc_noIndLabel.anchor = GridBagConstraints.WEST;
		gbc_noIndLabel.insets = new Insets(5, 10, 5, 5);
		gbc_noIndLabel.gridx = 0;
		gbc_noIndLabel.gridy = 2;
		panel_1.add(noIndLabel, gbc_noIndLabel);
		
		noIndField = new JTextField();
		GridBagConstraints gbc_noIndField = new GridBagConstraints();
		gbc_noIndField.anchor = GridBagConstraints.WEST;
		gbc_noIndField.insets = new Insets(0, 0, 5, 5);
		gbc_noIndField.gridx = 1;
		gbc_noIndField.gridy = 2;
		panel_1.add(noIndField, gbc_noIndField);
		noIndField.setColumns(10);
		
		JLabel sizeLabel = new JLabel("Size:");
		sizeLabel.setToolTipText("Starting size of the species in pixels.");
		GridBagConstraints gbc_sizeLabel = new GridBagConstraints();
		gbc_sizeLabel.anchor = GridBagConstraints.WEST;
		gbc_sizeLabel.insets = new Insets(5, 10, 5, 5);
		gbc_sizeLabel.gridx = 0;
		gbc_sizeLabel.gridy = 3;
		panel_1.add(sizeLabel, gbc_sizeLabel);
		
		sizeTxt = new JTextField();
		GridBagConstraints gbc_sizeTxt = new GridBagConstraints();
		gbc_sizeTxt.anchor = GridBagConstraints.WEST;
		gbc_sizeTxt.insets = new Insets(0, 0, 5, 5);
		gbc_sizeTxt.gridx = 1;
		gbc_sizeTxt.gridy = 3;
		panel_1.add(sizeTxt, gbc_sizeTxt);
		sizeTxt.setColumns(10);
		
		JLabel speedLabel = new JLabel("Speed:");
		speedLabel.setToolTipText("The starting speed of the species in pixels moved per frame.");
		GridBagConstraints gbc_speedLabel = new GridBagConstraints();
		gbc_speedLabel.anchor = GridBagConstraints.WEST;
		gbc_speedLabel.insets = new Insets(5, 10, 5, 5);
		gbc_speedLabel.gridx = 0;
		gbc_speedLabel.gridy = 4;
		panel_1.add(speedLabel, gbc_speedLabel);
		
		speedTxt = new JTextField();
		GridBagConstraints gbc_speedTxt = new GridBagConstraints();
		gbc_speedTxt.anchor = GridBagConstraints.WEST;
		gbc_speedTxt.insets = new Insets(0, 0, 5, 5);
		gbc_speedTxt.gridx = 1;
		gbc_speedTxt.gridy = 4;
		panel_1.add(speedTxt, gbc_speedTxt);
		speedTxt.setColumns(10);
		
		JLabel maxAgeLabel = new JLabel("Max age:");
		maxAgeLabel.setToolTipText("Age at which the species dies if that did not happen before due to other circumstances.");
		GridBagConstraints gbc_maxAgeLabel = new GridBagConstraints();
		gbc_maxAgeLabel.anchor = GridBagConstraints.WEST;
		gbc_maxAgeLabel.insets = new Insets(5, 10, 0, 5);
		gbc_maxAgeLabel.gridx = 0;
		gbc_maxAgeLabel.gridy = 5;
		panel_1.add(maxAgeLabel, gbc_maxAgeLabel);
		
		maxAgeTxt = new JTextField();
		GridBagConstraints gbc_maxAgeTxt = new GridBagConstraints();
		gbc_maxAgeTxt.anchor = GridBagConstraints.WEST;
		gbc_maxAgeTxt.insets = new Insets(0, 0, 0, 5);
		gbc_maxAgeTxt.gridx = 1;
		gbc_maxAgeTxt.gridy = 5;
		panel_1.add(maxAgeTxt, gbc_maxAgeTxt);
		maxAgeTxt.setColumns(10);
		return panel_1;
		
	}

}
