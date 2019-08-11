package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import simulation.BlankGameLoop;
import simulation.Environment;

public class BlankRun {
	private Timer timer;
	private Environment environment;
	private BlankGameLoop blankLoop;
	
	
	public BlankRun(OptionData data, int updateTime, int runCounter) {
		environment = new Environment(data);
		createBlankGui();
		blankLoop = new BlankGameLoop( 50, environment, updateTime);
		timer = new Timer(updateTime, blankLoop);

	}
	
	public void createBlankGui() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 100, 100);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_lblTbd = new GridBagConstraints();
		JLabel lblTbd = new JLabel("TBD");
		lblTbd.setText("possible");
		contentPane.add(lblTbd, gbc_lblTbd);
		frame.setVisible(true);
		
		
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


}
