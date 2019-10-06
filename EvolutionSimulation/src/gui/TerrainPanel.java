package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import environment.NutrientDeposit;
import gameobjects.Ecosystem;
import populations.Population;
import species.Plant;
import species.Species;

public class TerrainPanel extends JPanel{
	private int heigth;
	private int width;
	private Graphics2D g2d;
	private Ecosystem ecosystem;
			
	
	public TerrainPanel(int heigth, int width, Ecosystem ecosystem) {
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
		for (int i = 0; i < ecosystem.getNrPopulations(); i++) {
			Population sp = ecosystem.getPopulation(i);
			for (int j = 0; j < sp.getNrSpecies(); j++) {
				Species s = sp.getSpecies(j);
				int xCoord = s.getxLoc() - s.getSize()/2;
				int yCoord = s.getyLoc() - s.getSize()/2;
				g2d.setColor(sp.getColor());
				g2d.fillOval(xCoord, yCoord, s.getSize(), s.getSize());
				//for drawing the enrgy
				g2d.setColor(Color.BLACK);
//				drawCenteredString(s.getxLoc(), yCoord -5,String .format("%d(-%.0f)",s.getEnergy(), s.getEnergyConsumption()));
				drawCenteredString(s.getxLoc(), s.getyLoc(),String.format("%s",sp.getName()));
			}
		}
	}
	
	private void drawCenteredString(int xCoord, int yCoord, String text) {
    	int adjXCoord = xCoord - g2d.getFontMetrics().stringWidth(text)/ 2;
    	int adjYCoord = yCoord + g2d.getFontMetrics().getHeight()/3;
    	g2d.drawString(text, adjXCoord, adjYCoord);
	}
	
	public Ecosystem getEnvironment() {
		return ecosystem;
	}
}

