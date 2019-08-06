package simulation;

import java.awt.Color;
import java.awt.EventQueue;
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
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void createOptionData() {
		OptionData tempoptions = new OptionData();
		tempoptions.addColorsList(new Color(66,66,66));
		tempoptions.addEatSizeFactorsList(0);
		tempoptions.addMaxAgesList(1);
		tempoptions.addNamesList("Brams");
		tempoptions.addNoIndividualsList(1);
		tempoptions.addScentRangesList(1);
		tempoptions.addSizesList(1);
		tempoptions.addSpeedsList(1);
		tempoptions.addTypeList("Herbivore");
		
		tempoptions.addColorsList(new Color(66,66,66));
		tempoptions.addEatSizeFactorsList(0);
		tempoptions.addMaxAgesList(1);
		tempoptions.addNamesList("Wytzeus");
		tempoptions.addNoIndividualsList(1);
		tempoptions.addScentRangesList(1);
		tempoptions.addSizesList(1);
		tempoptions.addSpeedsList(1);
		tempoptions.addTypeList("Herbivore");
		
		Run game =  new Run(tempoptions, false);
		
		
		
	}
	
	
	

}
