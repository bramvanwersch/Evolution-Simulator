package gui;



import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import simulation.BlankGameLoop;
import simulation.Environment;

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
}
