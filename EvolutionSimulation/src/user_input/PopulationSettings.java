package user_input;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class PopulationSettings {
	private JComboBox<String> type;
	private JTextField name;
	private JSpinner noIndividuals;
	private JSpinner size;
	private JSpinner maxAge;
	private JLabel color;
	
	public PopulationSettings(JComboBox<String> type, JTextField name, JSpinner noIndividuals,
			JSpinner size, JSpinner maxAge, JLabel color) {
		this.type = type;
		this.name = name;
		this.noIndividuals = noIndividuals;
		this.size = size;
		this.maxAge = maxAge;
		this.color = color;
	}
	
	public String getType() {
		return (String) type.getSelectedItem();
	}
	
	public String getName() {
		return name.getText();
	}
	
	public int getNoIndividuals() {
		return (int) (noIndividuals.getValue());
	}
	
	public String getPopulationType() {
		return null;
	}
	
	public int getSize() {
		return (int) (this.size.getValue());
	}
	
	public Color getColor() {
		return this.color.getBackground();
	}
	
	public int getMaxAge() {
		return (int) (this.maxAge.getValue());
	}

}


