package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		OptionData optionData = makeOptionData();
		environment = new Environment(optionData);
		blankLoop = new BlankGameLoop( 50, environment, updateTime, this);
		timer = new Timer(updateTime, blankLoop);
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
		
		JLabel lblCounter = new JLabel("0");
		panel.add(lblCounter);
		
		JButton btnLoops = new JButton("Start Loops");
		panel.add(btnLoops);
		btnLoops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTimer();
				getBlankLoop();
			}
		});
	
	//	JButton btn

		
		
		f.add(panel);
		f.pack();
		f.setVisible(true);
		
		
		
		
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
		optionData.addMaxAgesList(4);
		optionData.addNamesList("Wytzeus");
		optionData.addNoIndividualsList(1);
		optionData.addScentRangesList(10);
		optionData.addSizesList(50);
		optionData.addSpeedsList(10);
		optionData.addTypeList("Omnivore");
		return optionData;
		
	}
	public void updateCounter(Integer count) {
		String strCount = Integer.toString(count);
		System.out.println(count + "has printed");
		this.lblCounter.setText(strCount);
	}


}
