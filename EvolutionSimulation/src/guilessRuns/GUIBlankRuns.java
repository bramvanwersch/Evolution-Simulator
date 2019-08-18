package guilessRuns;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import gui.OptionData;
import simulation.Environment;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class GUIBlankRuns {
	private Timer timer;
	private Environment environment;
	private BlankGameLoop blankLoop;
	private JLabel lblCounter;
	private JTextField loopAmount; 
	private JPanel panel;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
			
					GUIBlankRuns frame = new GUIBlankRuns(10);

			}
		});
	}	
	
	private GUIBlankRuns( int updateTime) {
		createBlankGui();
	}

	private void createBlankGui() {
		JFrame f = new JFrame("Blank Run");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(0, 0, 450, 300);

		
		
		panel = new JPanel();
		f.add(panel, BorderLayout.CENTER);
		
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
		
		f.add(panel);
		f.pack();
		f.setVisible(true);
	}
	/**This is the error handler for the Jtextfield with the amount of runs one wants to do.
	 * 	It then uses the tempBlankRun class to create the small runs
	 * @throws  
	 */
	
	private void startLoopsHandler() {
		String str = loopAmount.getText();
		if (str.chars().allMatch(Character::isLetter)) {
			JOptionPane.showMessageDialog(panel, "These are charcters", "Error", JOptionPane.ERROR_MESSAGE);
		}  else if (str.chars().allMatch(Character::isDigit) ) {
			int runs = Integer.parseInt(str);
			System.out.println("Blank Run made");
			BlankRun blankRun = new BlankRun(runs, lblCounter);
			blankRun.execute();
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
	
	/* This creates an instance of optionData with all the values necessary to complete a run. Normally one would select these values in OptionMenu.java.
	 * 
	 */

	public void updateCounter(Integer count) {
		String strCount = Integer.toString(count);
		this.lblCounter.setText(strCount);
	}


}
