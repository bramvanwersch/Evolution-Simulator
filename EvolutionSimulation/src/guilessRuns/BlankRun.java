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
import populations.Population;
import user_input.OptionData;
import user_input.PopulationSettings;

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
			Ecosytem ecosystem = new Ecosytem(optionData);
			BlankGameLoop blankGameLoop = new BlankGameLoop(ecosystem, 10);
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
		
		PopulationSettings p1 = new PopulationSettings("Carnivore", "Brams", 1, 50, 10,3, 10,Color.RED, 1.0);
		PopulationSettings p2 = new PopulationSettings("Carnivore", "Brams", 1, 50, 10,3, 10,Color.RED, 1.0);

		optionData.addPopulationSettings(p1);
		optionData.addPopulationSettings(p2);
		return optionData;
	}
	
	
}


