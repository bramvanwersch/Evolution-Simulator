package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import simulation.BlankGameLoop;
import simulation.Environment;

public class BlankRun extends JFrame {
	private Timer timer;
	private Environment environment;
	private BlankGameLoop blankLoop;
	private JPanel contentPane;
	private JLabel lblCounter;
	private JTextField loopAmount; 
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					BlankRun frame = new BlankRun(10);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	
	public BlankRun( int updateTime) {
		createBlankGui();
//		setVisible(true);

	}

	public void createBlankGui() {
		JFrame f = new JFrame("Blank Run");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		f.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		lblCounter = new JLabel("0");
		panel.add(lblCounter);
		
		JButton btnLoops = new JButton("Start Loops");
		panel.add(btnLoops);
		btnLoops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startLoopsHandler();


			}
		});
		
		loopAmount = new JTextField("");
		loopAmount.setPreferredSize(new Dimension( 200, 24 ));
		panel.add(loopAmount);

	
	//	JButton btn

		
		
		f.add(panel);
		f.pack();
		f.setVisible(true);
		
		
		
		
	}
	
	/**This is the error handler for the Jtextfield with the amount of runs one wants to do.
	 * 	It will use ........... to do all the runs
	 * 
	 * 
	 * 
	 * 
	 */
	private void startLoopsHandler() {
		String str = loopAmount.getText();
		if (str.chars().allMatch( Character::isLetter )) {
			JOptionPane.showMessageDialog(contentPane, "These are charcters", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (Integer.parseInt(str) > 10) {
			JOptionPane.showMessageDialog(contentPane, "Too many runs will take a lot of time", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (str.chars().allMatch( Character::isDigit ) && Integer.parseInt(str) < 10) {
			int runs = Integer.parseInt(str);
			for(int i = 0; i < runs; i++) {
				System.out.println("I get to my runs loop");
				OptionData optionData = makeOptionData();
				environment = new Environment(optionData);
				blankLoop = new BlankGameLoop( 50, environment, 10);
				timer = new Timer(10, blankLoop);
				startTimer();
				while(!blankLoop.isSimulationFinished()) {
					System.out.println("stuck in while loop");
					System.out.println(blankLoop.getDeadPopulation());
					if(blankLoop.isSimulationFinished()) {
						System.out.println("simulation finshed");
					}
				}
			}
		}
	}
	

	
	
	public BlankGameLoop getBlankLoop() {
		return blankLoop;
	}
	
	
	public void startTimer() {
		timer.start();
	}
	
	private void stopTimer() {
		timer.stop();
	}
	
	private OptionData makeOptionData() {
		OptionData optionData = new OptionData();
		optionData.setFoodEnergy(100);
		optionData.setFoodSize(5);
		
		optionData.addColorsList(new Color(66,66,66));
		optionData.addEatSizeFactorsList(1);
		optionData.addMaxAgesList(8);
		optionData.addNamesList("Brams");
		optionData.addNoIndividualsList(1);
		optionData.addScentRangesList(10);
		optionData.addSizesList(50);
		optionData.addSpeedsList(10);
		optionData.addTypeList("Carnivore");
		
		optionData.addColorsList(new Color(66,66,66));
		optionData.addEatSizeFactorsList(0);
		optionData.addMaxAgesList(5);
		optionData.addNamesList("Wytzeus");
		optionData.addNoIndividualsList(1);
		optionData.addScentRangesList(10);
		optionData.addSizesList(50);
		optionData.addSpeedsList(10);
		optionData.addTypeList("Carnivore");
		return optionData;
		
	}
	public void updateCounter(Integer count) {
		String strCount = Integer.toString(count);
		this.lblCounter.setText(strCount);
	}


}
