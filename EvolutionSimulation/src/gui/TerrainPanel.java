package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import environment.NutrientDeposit;
import simulation.Plant;
import simulation.Population;
import simulation.Species;
import simulation.Ecosytem;

public class TerrainPanel extends JPanel{
	private int heigth;
	private int width;
	private Graphics2D g2d;
	private Ecosytem ecosystem;
			
	
	public TerrainPanel(int heigth, int width, Ecosytem ecosystem) {
		this.heigth = heigth;
		this.width = width;
		this.ecosystem = ecosystem;
		this.setBackground(Color.WHITE);
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(width, heigth);
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		drawNutrientCircles();
		drawFood();
		drawSpecies();
	}

	private void drawNutrientCircles() {
		for (NutrientDeposit nd : ecosystem.getEnvironment().getNitrogenDeposits()){
			g2d.setColor(new Color(255, 0, 0, 50));
			g2d.fillOval(nd.getXPos(), nd.getYPos(), nd.getSize(), nd.getSize());
		}
		for (NutrientDeposit nd : ecosystem.getEnvironment().getPhosporusDeposits()){
			g2d.setColor(new Color(0, 0, 255, 50));
			g2d.fillOval(nd.getXPos(), nd.getYPos(), nd.getSize(), nd.getSize());
		}
		for (NutrientDeposit nd : ecosystem.getEnvironment().getPotassiumDeposits()){
			g2d.setColor(new Color(255, 255, 0, 50));
			g2d.fillOval(nd.getXPos(), nd.getYPos(), nd.getSize(), nd.getSize());
		}
	}

	private void drawSpecies() {
		for (Population sp : ecosystem.getPopulations()) {
			for (int i = 0; i < sp.getNrSpecies(); i++) {
				Species s = sp.getSpecies(i);
				int xCoord = s.getxLoc() - s.getSize()/2;
				int yCoord = s.getyLoc() - s.getSize()/2;
				g2d.setColor(sp.getColor());
				g2d.fillOval(xCoord, yCoord, s.getSize(), s.getSize());
				//for drawing the enrgy
				g2d.setColor(Color.BLACK);
				drawCenteredString(s.getxLoc(), yCoord -5,String .format("%d(-%.0f)",s.getEnergy(), s.getEnergyConsumption()));
				drawCenteredString(s.getxLoc(), s.getyLoc(),String.format("%s",sp.getName()));
			}
		}
	}
	
	private void drawCenteredString(int xCoord, int yCoord, String text) {
    	int adjXCoord = xCoord - g2d.getFontMetrics().stringWidth(text)/ 2;
    	int adjYCoord = yCoord + g2d.getFontMetrics().getHeight()/3;
    	g2d.drawString(text, adjXCoord, adjYCoord);
	}
	
	private void drawFood() {
		g2d.setColor(Color.GREEN);
		for (int i = 0; i < ecosystem.getNrPlant(); i++) {
			Plant f = ecosystem.getPlant(i);
			int xCoord = f.getxLoc() - f.getSize()/2;
			int yCoord = f.getyLoc() - f.getSize()/2;
			g2d.fillRect(xCoord, yCoord, f.getSize(), f.getSize());
		}
		g2d.setColor(Color.BLACK);
	}
	
	public Ecosytem getEnvironment() {
		return ecosystem;
	}
}

