package gui;
import javax.swing.JPanel;
import javax.swing.Timer;

import gui.Game;
import gui.OptionData;
import simulation.Environment;
import simulation.GameLoop;

public class Run {
	private Timer timer;
	private int UPDATE_TIME = 50;
	
	
	public Run(OptionData data, boolean runGUI) {
		Environment environment = new Environment(data);
		TerrainPanel panel = new TerrainPanel(950,950, environment);

		Game game = new Game(environment, panel, timer);
		GameLoop loop = new GameLoop(panel, game.txtNumberFood, game);
		timer = new Timer(UPDATE_TIME, loop);

	}

}
