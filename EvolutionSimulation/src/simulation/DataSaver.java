package simulation;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import gui.SidePanelGui;
import gui.OptionData;
import gui.OptionMenu;
import gui.Run;


public class DataSaver {
	
	public DataSaver() {
		
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataSaver data = new DataSaver(); 
					data.createOptionData();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void createOptionData() throws IOException {
		OptionData tempoptions = new OptionData();
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
		tempoptions.addMaxAgesList(8);
		tempoptions.addNamesList("Wytzeus");
		tempoptions.addNoIndividualsList(1);
		tempoptions.addScentRangesList(10);
		tempoptions.addSizesList(50);
		tempoptions.addSpeedsList(10);
		tempoptions.addTypeList("Carnivore");
		Run run =  new Run(tempoptions, 10);
		
		if(run.getBlankLoop().simulationFinished()) {
			FileWriter fw = new FileWriter("D:\\Scripts\\EvolutionaryGame2\\EvolutionSimulation\\Data\\OptimizingParametersData.txt");
			PopulationData[] data = run.getBlankLoop().getData();
			
			
			for(int i = 0; i < data.length; i++) {
				
				
				
			}
			
		}
		
		
		
	}
	
	
	

}
