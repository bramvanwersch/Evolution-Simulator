package user_input;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import user_input.PopulationSettings;


public class AutotrophPopulationSettings extends PopulationSettings{

	
	public AutotrophPopulationSettings(JComboBox<String> type, JTextField name, JSpinner noIndividuals, JSpinner size,
			JSpinner maxAge, JLabel color) {
		super(type, name, noIndividuals, size, maxAge, color);
	}

	public String getPopulationType() {
		return "Autotroph";
	}
	
	
	public int[] getMaxNutrientValues() {
		return new int[] {50, 50, 50};
	}
	
	public int getEnergyOnConsumption() {
		return 0;
	}
	
}
