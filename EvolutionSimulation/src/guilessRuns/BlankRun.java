package guilessRuns;



import java.awt.Color;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import environment.Environment;
import gameobjects.Ecosytem;
import gameobjects.Population;
import user_input.OptionData;

public class BlankRun extends SwingWorker<Void, Integer> {
	private int runs;
	public JLabel lblCounter;
	
	public BlankRun(int runs, JLabel lblCounter) {
		this.runs = runs;
		this.lblCounter = lblCounter;
	}
	
	public Void doInBackground() {
		for (int i = 0; i < runs; i++) {
			OptionData optionData = makeOptionData();
			Environment environment = new Environment(new int[] {50,50}, new int[] {50,50}, new int[] {50,50});
			Ecosytem ecosystem = new Ecosytem(optionData, environment);
			BlankGameLoop blankGameLoop = new BlankGameLoop(50, ecosystem, 10);
			Timer timer = new Timer(10, blankGameLoop);
			timer.start();
			while (!blankGameLoop.getRunFinished()) {
				
			}
			publish(i);
			System.out.println("One run finished");
		}
		return null;
		
	}
	@Override
	protected void process(List<Integer> chunks) {
		// TODO Auto-generated method stub
		int runsDone = chunks.get(chunks.size()-1);
		runsDone +=1;
		lblCounter.setText(Integer.toString(runsDone));
	}



	
	

	private OptionData makeOptionData() {
		OptionData optionData = new OptionData();
		optionData.setPlantEnergy(100);
		optionData.setPlantSize(5);
		
		optionData.addColorsList(new Color(66,66,66));
		optionData.addEatSizeFactorsList(1);
		optionData.addMaxAgesList(3);
		optionData.addNamesList("Brams");
		optionData.addNoIndividualsList(1);
		optionData.addScentRangesList(10);
		optionData.addSizesList(50);
		optionData.addSpeedsList(10);
		optionData.addTypeList("Carnivore");
		
		optionData.addColorsList(new Color(66,66,66));
		optionData.addEatSizeFactorsList(0);
		optionData.addMaxAgesList(2);
		optionData.addNamesList("Wytzeus");
		optionData.addNoIndividualsList(1);
		optionData.addScentRangesList(10);
		optionData.addSizesList(50);
		optionData.addSpeedsList(10);
		optionData.addTypeList("Carnivore");
		return optionData;
	}
	
	
}


