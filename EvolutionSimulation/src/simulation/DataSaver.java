package simulation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.SidePanelGui;
import gui.Trial_And_Error;
import gui.BlankRun;
import gui.OptionData;
import gui.OptionMenu;
import gui.Run;


public class DataSaver {
	private int runCounter;
	
	public DataSaver() {
		runCounter = 0;
		try {
			createOptionData();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataSaver data = new DataSaver(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void createOptionData() throws IOException, InterruptedException {
		OptionData tempoptions = new OptionData();
		tempoptions.setFoodEnergy(100);
		tempoptions.setFoodSize(5);
		
		tempoptions.addColorsList(new Color(66,66,66));
		tempoptions.addEatSizeFactorsList(1);
		tempoptions.addMaxAgesList(8);
		tempoptions.addNamesList("Brams");
		tempoptions.addNoIndividualsList(1);
		tempoptions.addScentRangesList(10);
		tempoptions.addSizesList(50);
		tempoptions.addSpeedsList(10);
		tempoptions.addTypeList("Carnivore");
		
		tempoptions.addColorsList(new Color(66,66,66));
		tempoptions.addEatSizeFactorsList(0);
		tempoptions.addMaxAgesList(4);
		tempoptions.addNamesList("Wytzeus");
		tempoptions.addNoIndividualsList(1);
		tempoptions.addScentRangesList(10);
		tempoptions.addSizesList(50);
		tempoptions.addSpeedsList(10);
		tempoptions.addTypeList("Omnivore");
		
		
//		JOptionPane pane = new JOptionPane();
		Trial_And_Error frame = new Trial_And_Error();
		frame.setVisible(true);
		
		
		BlankRun run =  new BlankRun(tempoptions, 10);
		
		
//		pane.showConfirmDialog(null, "heeyy");
		
		
		System.out.println(run.getBlankLoop().isSimulationFinished());
		boolean checker = false;
		while(!checker) {
			System.out.println(run.getBlankLoop().isSimulationFinished());
			checker = run.getBlankLoop().isSimulationFinished();
		}
				
		FileWriter fw = new FileWriter("D:\\Scripts\\EvolutionaryGame2\\EvolutionSimulation\\Data\\OptimizingParametersData.txt");
		PopulationData[] data = run.getBlankLoop().getData();
	
	
		for(int i = 0; i < data.length; i++) {
			System.out.println("Matrixprinter");
			System.out.println(data[i].getMatrixString());
		}
		
		
}

}
