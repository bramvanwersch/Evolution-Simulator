package guilessRuns;



import java.io.FileWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import gui.OptionData;
import simulation.Environment;
import simulation.Population;

public class BlankRun {
	private Timer timer;
	private Environment environment;
	private BlankGameLoop blankLoop;
	public BlankRun(OptionData optionData) {
		environment = new Environment(optionData);
		blankLoop = new BlankGameLoop(50, environment, 10);
		timer = new Timer(10, blankLoop);
		
		
		
		while(timer.isRunning()) {
			
		}
		
		Population population = getSoleSurvivor();
		double[] stats = population.getMaxAgeStats();
		for (int i =0 ; i < stats.length; i++ ) {
			System.out.println(stats[i]);
		}
		
	}
	
	
	public BlankGameLoop getBlankGameLoop() {
		return blankLoop;
	}
	
	
	
	public void startTimer() {
		timer.start();
	}
	public void stopTimer() {
		timer.stop();
	}
	private Population getSoleSurvivor() {
		ArrayList<Population> populations = environment.getPopulations();
		Population rightPopulation = null;
		for(int i =0; i<populations.size(); i++) {
			if(populations.get(i).getNrSpecies()>0) {
				rightPopulation = populations.get(i);
			}
		}
		return rightPopulation;
	}
	
//	private void writeSoleSurvivor(Population survivor) {
//		FileWriter filewriter = new FileWriter("DataDocument.txt");
//	}
	
}


