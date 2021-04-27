package user_input;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import user_input.PopulationSettings;

/**
 * Class for saving settings set by the user for an individual population
 * @author Bram van Wersch
 */
public class HetrotrophPopulationSettings extends PopulationSettings{
	
	private JSpinner speed;
	
	private JSpinner scentRange;
	private JSpinner eatSizeFactor;
	
	/**
	 * Construtor for the class for setting all of the population values.
	 * @param type of the population.
	 * @param name of the population.
	 * @param noIndividuals number of individuals for the population to start
	 * with
	 * @param size of all the species in the population at creation.
	 * @param speed of all the species in the population at creation.
	 * @param maxAge of all the species in the population at creation.
	 * @param scentRange of all the species in the population at creation.
	 * @param color of the population.
	 * @param eatSizeFactor of all the species in the population at creation.
	 */
	public HetrotrophPopulationSettings(JComboBox<String> type, JTextField name, JSpinner noIndividuals, JSpinner size,
			JSpinner speed, JSpinner maxAge, JSpinner scentRange, JLabel color, JSpinner eatSizeFactor) {
		super(type, name, noIndividuals, size, maxAge, color);
		this.speed = speed;
		this.scentRange = scentRange;

		this.eatSizeFactor = eatSizeFactor;
	}

	public int getSpeed() {
		return (int) (this.speed.getValue());
	}

	public int getScentRange() {
		return (int) (this.scentRange.getValue());
	}


	public double getEatSizeFactor() {
		return (double) (this.eatSizeFactor.getValue());
	}

	public String getPopulationType() {
		return "Hetrotroph";
	}
}
