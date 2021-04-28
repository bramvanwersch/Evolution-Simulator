package user_input;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import user_input.PopulationSettings;


public class AutotrophPopulationSettings extends PopulationSettings{
	private JSpinner energyOnEat;

	
	public AutotrophPopulationSettings(JComboBox<String> type, JTextField name, JSpinner noIndividuals, JSpinner size,
			JSpinner maxAge, JLabel color, JSpinner energyOnEat) {
		super(type, name, noIndividuals, size, maxAge, color);
		this.energyOnEat = energyOnEat;
	}

	public String getPopulationType() {
		return "Autotroph";
	}
	
	
	public int[] getMaxNutrientValues() {
		return new int[] {50, 50, 50};
	}
	
	public int getEnergyOnConsumption() {
		return (int) (energyOnEat.getValue());
	}
	
}
