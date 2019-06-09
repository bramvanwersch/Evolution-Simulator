package genome;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class OptionMenu extends JFrame {

	private JPanel contentPane;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private final JPanel environmentPanel = new JPanel();
	private final JPanel populationPanel = new JPanel();
	private final JPanel speciesPanel = new JPanel();
	private final JLabel lblFoodEnergy = new JLabel("Food Energy:");
	private final JTextField foodEnergyTxt = new JTextField();
	private final JLabel lblFoodSize = new JLabel("Food Size:");
	private final JTextField foodSizeTxt = new JTextField();
	private final JLabel lblEatSizeFactor = new JLabel("Eat Size Factor:");
	private final JTextField eatSizeFactorTxt = new JTextField();
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JLabel lblTbd = new JLabel("TBD");

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
		setBounds(100, 100, 664, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		contentPane.add(tabbedPane, gbc_tabbedPane);
		
		tabbedPane.addTab("Environment", null, environmentPanel, null);
		GridBagLayout gbl_environmentPanel = new GridBagLayout();
		gbl_environmentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_environmentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_environmentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_environmentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		environmentPanel.setLayout(gbl_environmentPanel);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(20, 20, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Food properties", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		environmentPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		GridBagConstraints gbc_lblFoodEnergy = new GridBagConstraints();
		gbc_lblFoodEnergy.anchor = GridBagConstraints.WEST;
		gbc_lblFoodEnergy.insets = new Insets(10, 5, 5, 5);
		gbc_lblFoodEnergy.gridx = 0;
		gbc_lblFoodEnergy.gridy = 0;
		panel.add(lblFoodEnergy, gbc_lblFoodEnergy);
		lblFoodEnergy.setToolTipText("Energy each food item gives herbivores and omnivores.");
		lblFoodEnergy.setHorizontalAlignment(SwingConstants.LEFT);
		
		GridBagConstraints gbc_foodEnergyTxt = new GridBagConstraints();
		gbc_foodEnergyTxt.insets = new Insets(10, 10, 5, 5);
		gbc_foodEnergyTxt.gridx = 1;
		gbc_foodEnergyTxt.gridy = 0;
		panel.add(foodEnergyTxt, gbc_foodEnergyTxt);
		
		GridBagConstraints gbc_lblFoodSize = new GridBagConstraints();
		gbc_lblFoodSize.anchor = GridBagConstraints.WEST;
		gbc_lblFoodSize.insets = new Insets(0, 5, 5, 5);
		gbc_lblFoodSize.gridx = 0;
		gbc_lblFoodSize.gridy = 1;
		panel.add(lblFoodSize, gbc_lblFoodSize);
		lblFoodSize.setToolTipText("Size of the food for herbivores and omnivores.");
		lblFoodSize.setHorizontalAlignment(SwingConstants.LEFT);
		
		GridBagConstraints gbc_foodSizeTxt = new GridBagConstraints();
		gbc_foodSizeTxt.insets = new Insets(0, 10, 5, 5);
		gbc_foodSizeTxt.gridx = 1;
		gbc_foodSizeTxt.gridy = 1;
		panel.add(foodSizeTxt, gbc_foodSizeTxt);
		
		GridBagConstraints gbc_lblEatSizeFactor = new GridBagConstraints();
		gbc_lblEatSizeFactor.anchor = GridBagConstraints.WEST;
		gbc_lblEatSizeFactor.insets = new Insets(0, 5, 5, 5);
		gbc_lblEatSizeFactor.gridx = 0;
		gbc_lblEatSizeFactor.gridy = 2;
		lblEatSizeFactor.setToolTipText("<html>\r\nFactor that tells how mutch bigger a species has to be to be<br>\r\nable to eat its respective food. A value of 1 means that the<br>\r\nspecies has to be as big or bigger to be able to eat its food.<br>\r\n</html>\r\n");
		panel.add(lblEatSizeFactor, gbc_lblEatSizeFactor);
		lblEatSizeFactor.setHorizontalAlignment(SwingConstants.LEFT);
		
		GridBagConstraints gbc_eatSizeFactorTxt = new GridBagConstraints();
		gbc_eatSizeFactorTxt.insets = new Insets(0, 10, 5, 5);
		gbc_eatSizeFactorTxt.gridx = 1;
		gbc_eatSizeFactorTxt.gridy = 2;
		panel.add(eatSizeFactorTxt, gbc_eatSizeFactorTxt);
		
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Terrain properties", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		environmentPanel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		GridBagConstraints gbc_lblTbd = new GridBagConstraints();
		gbc_lblTbd.gridx = 0;
		gbc_lblTbd.gridy = 0;
		panel_1.add(lblTbd, gbc_lblTbd);
		
		tabbedPane.addTab("Environment", null, populationPanel, null);
		
		tabbedPane.addTab("Species", null, speciesPanel, null);
	}

}
