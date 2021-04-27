package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import user_input.HetrotrophPopulationSettings;
import utility_functions.Constants;


public class HetrotrophPopulationPanel extends PopulationPanel {
	private HetrotrophPopulationSettings settings;
	
	public HetrotrophPopulationSettings getSettings() {
		return settings;
	}

	
	public void innitLayout() {
		ArrayList<JSpinner> textList = new ArrayList<JSpinner>();
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagLayout gbl_this = new GridBagLayout();
		this.setLayout(gbl_this);
		
		JLabel lblType = new JLabel("Type:");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.WEST;
		gbc_lblType.insets = new Insets(10, 10, 5, 5);
		gbc_lblType.fill = GridBagConstraints.BOTH;
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 0;
		this.add(lblType, gbc_lblType);
		
		GridBagConstraints gbc_typeComboBox = new GridBagConstraints();
		gbc_typeComboBox.anchor = GridBagConstraints.WEST;
		gbc_typeComboBox.insets = new Insets(10, 0, 5, 5);
		gbc_typeComboBox.fill = GridBagConstraints.BOTH;
		gbc_typeComboBox.gridx = 1;
		gbc_typeComboBox.gridy = 0;
		JComboBox<String> typeComboBox = new JComboBox<String>();
		typeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Herbivore", "Omnivore", "Carnivore"}));
		this.add(typeComboBox, gbc_typeComboBox);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(5, 10, 5, 5);
		gbc_lblName.fill = GridBagConstraints.BOTH;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		this.add(lblName, gbc_lblName);
		
		JTextField nameTxt = new JTextField("Placeholder");
		GridBagConstraints gbc_nameTxt = new GridBagConstraints();
		gbc_nameTxt.anchor = GridBagConstraints.WEST;
		gbc_nameTxt.insets = new Insets(0, 0, 5, 5);
		gbc_nameTxt.fill = GridBagConstraints.BOTH;
		gbc_nameTxt.gridx = 1;
		gbc_nameTxt.gridy = 1;
		this.add(nameTxt, gbc_nameTxt);
		nameTxt.setColumns(10);
		
		JLabel noIndLabel = new JLabel(String.format("No ind.(1, %d):", Constants.MAX_INDIVIDUALS));
		noIndLabel.setToolTipText("Number of individuals.\r\n");
		GridBagConstraints gbc_noIndLabel = new GridBagConstraints();
		gbc_noIndLabel.anchor = GridBagConstraints.WEST;
		gbc_noIndLabel.insets = new Insets(5, 10, 5, 5);
		gbc_noIndLabel.fill = GridBagConstraints.BOTH;
		gbc_noIndLabel.gridx = 0;
		gbc_noIndLabel.gridy = 2;
		this.add(noIndLabel, gbc_noIndLabel);
		
		SpinnerNumberModel indModel = new SpinnerNumberModel(Constants.DEFAULT_START_INDIVIDUALS, 1, Constants.MAX_INDIVIDUALS, 1);
		JSpinner noIndField = new JSpinner(indModel);
		GridBagConstraints gbc_noIndField = new GridBagConstraints();
		gbc_noIndField.anchor = GridBagConstraints.WEST;
		gbc_noIndField.insets = new Insets(0, 0, 5, 5);
		gbc_noIndField.fill = GridBagConstraints.BOTH;
		gbc_noIndField.gridx = 1;
		gbc_noIndField.gridy = 2;
		this.add(noIndField, gbc_noIndField);
		textList.add(noIndField);
		
		JLabel sizeLabel = new JLabel(String.format("Size (1, %d):", Constants.MAX_SIZE));
		sizeLabel.setToolTipText("Starting diameter of the species in pixels.");
		GridBagConstraints gbc_sizeLabel = new GridBagConstraints();
		gbc_sizeLabel.anchor = GridBagConstraints.WEST;
		gbc_sizeLabel.insets = new Insets(5, 10, 5, 5);
		gbc_sizeLabel.fill = GridBagConstraints.BOTH;
		gbc_sizeLabel.gridx = 0;
		gbc_sizeLabel.gridy = 3;
		this.add(sizeLabel, gbc_sizeLabel);
		
		SpinnerNumberModel sizeModel = new SpinnerNumberModel(Constants.DEFAULT_SIZE, 1, Constants.MAX_SIZE, 1);
		JSpinner sizeSpinner = new JSpinner(sizeModel);
		GridBagConstraints gbc_sizeSpinner = new GridBagConstraints();
		gbc_sizeSpinner.anchor = GridBagConstraints.WEST;
		gbc_sizeSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_sizeSpinner.fill = GridBagConstraints.BOTH;
		gbc_sizeSpinner.gridx = 1;
		gbc_sizeSpinner.gridy = 3;
		this.add(sizeSpinner, gbc_sizeSpinner);
		textList.add(sizeSpinner);
		
		JLabel speedLabel = new JLabel(String.format("Speed (1, %d):", Constants.MAX_SPEED));
		speedLabel.setToolTipText("The starting speed of the species in pixels moved per frame.");
		GridBagConstraints gbc_speedLabel = new GridBagConstraints();
		gbc_speedLabel.anchor = GridBagConstraints.WEST;
		gbc_speedLabel.insets = new Insets(5, 10, 5, 5);
		gbc_speedLabel.fill = GridBagConstraints.BOTH;
		gbc_speedLabel.gridx = 0;
		gbc_speedLabel.gridy = 4;
		this.add(speedLabel, gbc_speedLabel);
		
		SpinnerNumberModel speedModel = new SpinnerNumberModel(Constants.DEFAULT_SPEED, 1, Constants.MAX_SPEED, 1);
		JSpinner speedSpinner = new JSpinner(speedModel);
		GridBagConstraints gbc_speedSpinner = new GridBagConstraints();
		gbc_speedSpinner.anchor = GridBagConstraints.WEST;
		gbc_speedSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_speedSpinner.fill = GridBagConstraints.BOTH;
		gbc_speedSpinner.gridx = 1;
		gbc_speedSpinner.gridy = 4;
		this.add(speedSpinner, gbc_speedSpinner);
		textList.add(speedSpinner);
		
		JLabel maxAgeLabel = new JLabel(String.format("Max age (1, %d):", Constants.MAX_MAX_AGE));
		maxAgeLabel.setToolTipText("Age at which the species dies if that did not happen before due to other circumstances.");
		GridBagConstraints gbc_maxAgeLabel = new GridBagConstraints();
		gbc_maxAgeLabel.anchor = GridBagConstraints.WEST;
		gbc_maxAgeLabel.insets = new Insets(5, 10, 5, 5);
		gbc_maxAgeLabel.fill = GridBagConstraints.BOTH;
		gbc_maxAgeLabel.gridx = 2;
		gbc_maxAgeLabel.gridy = 0;
		this.add(maxAgeLabel, gbc_maxAgeLabel);
		
		SpinnerNumberModel maxAgeModel = new SpinnerNumberModel(Constants.DEFAULT_MAX_AGE, 1, Constants.MAX_MAX_AGE, 1);
		JSpinner maxAgeSpinner = new JSpinner(maxAgeModel);
		GridBagConstraints gbc_maxAgeSpinner = new GridBagConstraints();
		gbc_maxAgeSpinner.anchor = GridBagConstraints.WEST;
		gbc_maxAgeSpinner.insets = new Insets(10, 0, 5, 5);
		gbc_maxAgeSpinner.fill = GridBagConstraints.BOTH;
		gbc_maxAgeSpinner.gridx = 3;
		gbc_maxAgeSpinner.gridy = 0;
		this.add(maxAgeSpinner, gbc_maxAgeSpinner);
		textList.add(maxAgeSpinner);
		
		JLabel scentRangeLabel = new JLabel(String.format("Scent range (0, %d):", Constants.MAX_SCENT_RANGE));
		scentRangeLabel.setToolTipText("Range at which a species can smell another species.");
		GridBagConstraints gbc_scentRangeLabel = new GridBagConstraints();
		gbc_scentRangeLabel.anchor = GridBagConstraints.WEST;
		gbc_scentRangeLabel.insets = new Insets(5, 10, 5, 5);
		gbc_scentRangeLabel.fill = GridBagConstraints.BOTH;
		gbc_scentRangeLabel.gridx = 2;
		gbc_scentRangeLabel.gridy = 1;
		this.add(scentRangeLabel, gbc_scentRangeLabel);
		
		SpinnerNumberModel scentRangeModel = new SpinnerNumberModel(Constants.DEFAULT_SCENT_RANGE, 0, Constants.MAX_SCENT_RANGE, 1);
		JSpinner scentRangeSpinner = new JSpinner(scentRangeModel);
		GridBagConstraints gbc_scentRangeSpinner = new GridBagConstraints();
		gbc_scentRangeSpinner.anchor = GridBagConstraints.WEST;
		gbc_scentRangeSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_scentRangeSpinner.fill = GridBagConstraints.BOTH;
		gbc_scentRangeSpinner.gridx = 3;
		gbc_scentRangeSpinner.gridy = 1;
		this.add(scentRangeSpinner, gbc_scentRangeSpinner);
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
		this.add(colorLabel, gbc_colorLabel);
		
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
		this.add(newColorBtn, gbc_newColorBtn);
		
		JLabel eatSizeFactorLabel = new JLabel(String.format("Eat size factor (0, %d):", Constants.MAX_EAT_SIZE_FACTOR));
		eatSizeFactorLabel.setToolTipText("<html>\r\nFactor that tells how mutch bigger a species has to be to be<br>\r\nable to eat its respective plant. A value of 1 means that the<br>\r\nspecies has to be as big or bigger to be able to eat its plant.<br>\r\n</html>\r\n");
		GridBagConstraints gbc_eatSizeFactorLbl = new GridBagConstraints();
		gbc_eatSizeFactorLbl.anchor = GridBagConstraints.WEST;
		gbc_eatSizeFactorLbl.insets = new Insets(5, 10, 5, 5);
		gbc_eatSizeFactorLbl.fill = GridBagConstraints.BOTH;
		gbc_eatSizeFactorLbl.gridx = 2;
		gbc_eatSizeFactorLbl.gridy = 3;
		this.add(eatSizeFactorLabel, gbc_eatSizeFactorLbl);
		
		SpinnerNumberModel eatSizeFactorModel = new SpinnerNumberModel(Constants.DEFAULT_EAT_SIZE_FACTOR, 0, Constants.MAX_SCENT_RANGE, 0.1);
		JSpinner eatSizeFactorSpinner = new JSpinner(eatSizeFactorModel);
		GridBagConstraints gbc_eatSizeFactorSpinner = new GridBagConstraints();
		gbc_eatSizeFactorSpinner.anchor = GridBagConstraints.WEST;
		gbc_eatSizeFactorSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_eatSizeFactorSpinner.fill = GridBagConstraints.BOTH;
		gbc_eatSizeFactorSpinner.gridx = 3;
		gbc_eatSizeFactorSpinner.gridy = 3;
		this.add(eatSizeFactorSpinner, gbc_eatSizeFactorSpinner);
		textList.add(eatSizeFactorSpinner);
				
		
//		JButton btnMoreSpecies = new JButton("X");
//		btnMoreSpecies.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				removeSpeciesPanel(this);
//			}});
//		GridBagConstraints gbc_button = new GridBagConstraints();
//		gbc_button.gridy = 0;
//		gbc_button.gridx = 4;
//		this.add(btnMoreSpecies, gbc_button);
		
		settings = new HetrotrophPopulationSettings(typeComboBox, nameTxt, noIndField, sizeSpinner,
				speedSpinner, maxAgeSpinner, scentRangeSpinner, colorLabel, eatSizeFactorSpinner);
		
	}
}

