package gui;



import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import simulation.BlankGameLoop;
import simulation.Environment;
import simulation.Population;

public class TempBlankRun {
	private Timer timer;
	private Environment environment;
	private BlankGameLoop blankLoop;
	public TempBlankRun(OptionData optionData) {
		environment = new Environment(optionData);
		blankLoop = new BlankGameLoop(50, environment, 10);
		timer = new Timer(10, blankLoop);
		
		
		
		while(timer.isRunning()) {
			
		}
		while(!timer.isRunning()) {
			
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
	private void writeData() {
		ArrayList<Population> populations = environment.getPopulations();
		
		for(int i =0; i<populations.size(); i++) {
			if(populations.get(i).getNrSpecies()>0) {
				Population rightPopulation = populations.get(i);
			}
		}
		return rightPopulation;
	}
	
}


