package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import gameobjects.Ecosystem;
import populations.PopulationData;

import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Class that acts as the thread for updating the Graph building pannel.
 * It implements a run method that acts as the exit condition for the thread
 * and update method
 * @author Bram vanWersch
 */
public class GraphBuilder extends Thread{
	private final Color[] attributeColors = new Color[] {new Color(0,105,0), new Color(191,61,255), new Color(255,0,0), 
		 	 								new Color(21,235,231), new Color(13,49,208), new Color(11,243,24)};
	private JPanel sidePanel;
	private GraphBuilderPanel graphPanel;
	private Ecosystem ecosystem;
	public JRadioButton[] selectedPopulations;
	public JRadioButton[] selectedAttributes;
	private boolean lookingAtGraph;
	
	/**
	 * Innitialises the graph and saves data needed for building most of the 
	 * graphs.
	 * @param ecosystem instance of the game. It holds all connections between
	 * data flows in the class
	 * @param populationNames of the populations for consistant display of 
	 * names in a fixed order
	 * @param attributeNames of the individual populations
	 * @param width of the graph panel
	 * @param height of the graph panel
	 * @param axisNames of the x and y axis
	 * @param dataPoints is a boolean that tells the graph panel to draw dots
	 * for each data point (true) or not (false).
	 */
	public GraphBuilder(Ecosystem ecosystem, String[] populationNames, String[] attributeNames
			,int width,int height,String[] axisNames, boolean dataPoints) {
		this.ecosystem = ecosystem;
		this.lookingAtGraph = true;
		JFrame f = new JFrame("Graph");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sidePanel = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		sidePanel.setLayout(gbl);
		selectedPopulations = new JRadioButton[populationNames.length];
		selectedAttributes = new JRadioButton[attributeNames.length];
		
		createSidePanelWidgets(populationNames, attributeNames);
		graphPanel = new GraphBuilderPanel(getXData(),getYData(), selectedPopulations, selectedAttributes,
				width,height,axisNames, dataPoints, attributeColors);
		f.add(graphPanel, BorderLayout.WEST);
		f.add(sidePanel, BorderLayout.EAST);
		f.pack();
		f.setVisible(true);
		
		//makes sure the thread is disposed when the window is closed.
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				lookingAtGraph = false;
			}
		});
	}
	
	/**
	 * Function that checks if the JFrame containing the graph is still open
	 * and if this is the case it refreshes the graph every half a second.
	 */
	@Override
	public void run() {
		while(lookingAtGraph) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			graphPanel.setYData(getYData());
			graphPanel.setXData(getXData());
			graphPanel.repaint();
		}
	}
	
	/**
	 * Function that collects all the data arrays from each PopulationData
	 * class. The data pis reduced before it is collected to make the graph 
	 * more smoothed out and the data lines clear to distinguish.
	 * @return an int array of array of arrays that holds data for each
	 * population for each attribute.
	 */
	private int[][][] getYData(){
		int [][][] yDataArray = new int[ecosystem.getNrHetrotrophPopulations()][][];
		ecosystem.getPopulation(0).getPopData();
		for (int i = 0; i < ecosystem.getNrHetrotrophPopulations(); i++) {
			PopulationData pd = ecosystem.getPopulation(i).getPopData();
			pd.setDataDivisionFactor();
			yDataArray[i] = pd.getDataArray();
		}
		return yDataArray;
	}
	
	/**
	 * Takes the time that is saved for the average data of the ecosystem
	 * instance and reduces that to fit the yData.
	 * @return an int array that contains time points for the x-axis.
	 */
	private int[] getXData() {
		ecosystem.getAveragePopData().setDataDivisionFactor();
		return ecosystem.getAveragePopData().getTime();
	}
    
	/**
	 * Creates the information shown on the side of the graph panel.
	 * @param populationNames are the names of the known populations
	 * @param attributeNames are the names of the attributes each species has.
	 */
    private void createSidePanelWidgets(String[] populationNames, String[] attributeNames){
    	//Hardcoded
    	//spacing kind of bad solution.
    	JLabel populationsLbl = new JLabel("Populations:            ");
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		sidePanel.add(populationsLbl, gbc);
    	for (int i = 0; i < populationNames.length; i++) {
    		JRadioButton rd = new JRadioButton(populationNames[i]);
    		rd.setSelected(true);
        	gbc.anchor = GridBagConstraints.CENTER;
    		gbc.gridy += 1;
    		sidePanel.add(rd, gbc);
    		selectedPopulations[i] = rd;
    	}
    	gbc.anchor = GridBagConstraints.WEST;
    	JLabel spaceLbl = new JLabel("  ");
		gbc.gridy += 1;
		sidePanel.add(spaceLbl, gbc);
    	JLabel attributeLbl = new JLabel("Attributes:");
		gbc.gridy += 1;
		sidePanel.add(attributeLbl, gbc);
    	for (int j = 0; j < attributeNames.length; j++) {
        	gbc.anchor = GridBagConstraints.CENTER;
    		JRadioButton rd = new JRadioButton(attributeNames[j]);
    		rd.setSelected(true);
    		rd.setForeground(attributeColors[j]);
    		gbc.gridy += 1;
    		sidePanel.add(rd, gbc);
    		selectedAttributes[j] = rd;
    	}
    	gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy += 1;
    	JButton refreshBtn = new JButton("Refresh");
    	refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphPanel.repaint();
			}
		});
    	sidePanel.add(refreshBtn, gbc);
    }
}

/**
 * Class that builds the pannel that contains the graph
 * @author Bram van Wersch
 */
class GraphBuilderPanel extends JPanel{
	private int[] allXData;
	private int[][][] allYData;
	private int pHeigth;
	private int pWidth;
	//distance from graph to end of pane
	private int DISTANCE_END = 10;
	//min distance between axis points.
	private final int MIN_AXIS_NUMBER_DISTANCE = 20;
	//distance of numbers from x and y axis
	private int NUMBER_DISTANCE = 20;
	//length of stripe extending trough x or y axis.
	private final int AXIS_STRIPE_LENGHT = 5;
	//size of data orbs
	private final int SIZE_OF_POINTS = 7;
	//maximum number of axis points
	private int NUMBER_OF_AXIS_POINTS_X;
	private int NUMBER_OF_AXIS_POINTS_Y;
	//distance from side to y or x axis
	private int DISTANCE_BORDER;
	//size of x axis.
	private int SIZE_X_AXIS;
	//size of y axis
	private int SIZE_Y_AXIS;
	private String[] axisNames;
	private int AXIS_FONT_SIZE;
	private Graphics2D g2d;
	private boolean dataPoints = true;
	//Lists for tracking what Attributes to show and what populations to show.
	private JRadioButton[] selectedPopulations;
	private JRadioButton[] selectedAttributes;
	private Color[] colors;
	private Stroke[] strokes;

	/**
	 * Innitialises the innitial data aswel as the start for the radio buttons 
	 * that track what attributes and populations are supposed to be shown
	 * @param xData to be displayed on the x-axis
	 * @param yData to be displayed on the y axis
	 * @param selectedPopulations are the populations currently selected
	 * @param selectedAttributes are the attributes that are currently selected
	 * @param width of the graph panel
	 * @param height of the graph panel
	 * @param axisNames for both axi
	 * @param dataPoints is a boolean that tells the graph panel to draw dots
	 * for each data point (true) or not (false).
	 * @param attributeColors are the colors for each attribute.
	 */
    public GraphBuilderPanel(int[] xData, int[][][] yData, JRadioButton[] selectedPopulations, JRadioButton[] selectedAttributes,
    		int width,int height,String[] axisNames, boolean dataPoints, Color[] attributeColors) {
    	this.allXData =xData;
    	this.allYData = yData;
    	this.pHeigth = height;
    	this.pWidth= width;
    	this.axisNames = axisNames;
    	this.dataPoints = dataPoints;
    	this.selectedPopulations = selectedPopulations;
    	this.selectedAttributes = selectedAttributes;
        this.colors = attributeColors;
        //configure what the lines look like.
        //TODO show in the interface what population corresponds to what line type.
        this.strokes = new Stroke[] {new BasicStroke(3), 
        		new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0),
                new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4, 2, 8, 2}, 0),
                new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1,2}, 0)};
    	this.setBackground(Color.WHITE);
    }

    /**
     * Method needed for JPanel extension
     */
    @Override
	public Dimension getPreferredSize() {
        return new Dimension(pWidth,pHeigth);
    }

    /**
     * Method that is invoked every time the paint or repaint method is called
     * on the instance of this JPanel Object. It draws everything that is shown
     * in the graph panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][][] selectedYData = getSelectedYData();
    	this.DISTANCE_BORDER = (int) (pHeigth *0.05 +55);
    	this.SIZE_X_AXIS = pWidth - DISTANCE_BORDER - DISTANCE_END;
    	this.SIZE_Y_AXIS = pHeigth - DISTANCE_BORDER -DISTANCE_END;
    	this.NUMBER_OF_AXIS_POINTS_X = getNrOfAxisPoints(SIZE_X_AXIS);
    	this.NUMBER_OF_AXIS_POINTS_Y = getNrOfAxisPoints(SIZE_Y_AXIS);
    	this.AXIS_FONT_SIZE = getAxisFont();
    	g2d = (Graphics2D) g;
    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawGraphGrid(selectedYData);
        ArrayList<Color> orderColors = new ArrayList<Color>();
        ArrayList<Stroke> orderStrokes = new ArrayList<Stroke>();
        //Getting all correct colors needed instead of the first of the list
    	for (int i = 0; i < this.allYData.length; i++) {
        	if (selectedPopulations[i].isSelected()) {
        		orderStrokes.add(this.strokes[i]);
        	}
    	}
    	//Getting all correct strokes needed instead of the first of the list.
    	for (int j = 0; j < this.allYData[0].length; j++) {
			if (selectedAttributes[j].isSelected()) {
				orderColors.add(this.colors[j]);
			}
    	}
        for (int i = 0; i < selectedYData.length; i++) {
        	for (int j = 0; j < selectedYData[i].length; j++) {
        		drawGraphLine(allXData, selectedYData[i][j],selectedYData, orderColors.get(j), orderStrokes.get(i));
        	}
        }
        drawAxisNames();
    }

    /**
     * Calculates how many numbers can be displayed on the x or y axis 
     * depending on a predefined minimal distance between the data points.
     * @param axisSize The total lenght of the axis
     * @return an int that tells how many numbers can be displayed on an axis
     * and how many grid lines need to be drawn
     */
	private int getNrOfAxisPoints(int axisSize) {
		//Is a double to ensure the division is not rounded
    	double nrOfPoints = 100;
    	while (axisSize / nrOfPoints < MIN_AXIS_NUMBER_DISTANCE) {
    		nrOfPoints -= 1;
    	}
    	return (int) nrOfPoints;
    }
    
	/**
	 * Draws a line that is provided based on the x and y coordinates, color
	 * and type of stroke. It simply draws lines between the data points.
	 * @param xData are all the x coordinates for each data point
	 * @param yData are all the y coordinates for each data point
	 * @param selectedYData is the data that is selected for attribute and 
	 * population. It is used to determine the what lenght a number represents
	 * in pixels.
	 * @param col is the color of the line
	 * @param stroke is the way the line should look like.
	 */
    private void drawGraphLine(int[] xData, int[] yData, int[][][] selectedYData, Color col, Stroke stroke){
    	g2d.setStroke(stroke);
        for (int i = 0; i < xData.length-1; i++) {
        	g2d.setColor(col);
        	int x1 = (int) (xData[i]*nrToPixelX() + DISTANCE_BORDER);
        	int x2 = (int) (xData[i+1]*nrToPixelX() + DISTANCE_BORDER);
        	int y1 = (int) ((pHeigth - yData[i]*nrToPixelY(selectedYData)) - DISTANCE_BORDER);
        	int y2 = (int) ((pHeigth -yData[i+1]*nrToPixelY(selectedYData)) - DISTANCE_BORDER);
            g2d.drawLine(x1, y1, x2, y2);
            g2d.setColor(Color.BLACK);
            //draw small circles at each data point.
            if (dataPoints) {
              g2d.fillOval(x1 - SIZE_OF_POINTS/2, y1 - SIZE_OF_POINTS/2,SIZE_OF_POINTS, SIZE_OF_POINTS);
              g2d.fillOval(x2 - SIZE_OF_POINTS/2, y2 - SIZE_OF_POINTS/2,SIZE_OF_POINTS, SIZE_OF_POINTS);
            }
        }
    }
    
    /**
     * Draws the basic grid and numbers and values on the x and y axis.
     * @param yData is the data that is selected for attribute and 
	 * population. It is used to configure where the grid lines have to be drawn.
     */
    private void drawGraphGrid(int[][][] yData) {
    	//basic x y axis
    	g2d.fillRect(DISTANCE_BORDER, pHeigth - DISTANCE_BORDER + 2, pWidth - DISTANCE_END- DISTANCE_BORDER + 1, 2);
    	g2d.fillRect(DISTANCE_BORDER, DISTANCE_END , 2, pHeigth - DISTANCE_BORDER - DISTANCE_END + 2);
    	//values on axis
    	int xMax = max(allXData);
    	int yMax = max(yData);
    	//draw the 0
    	drawCenteredString(DISTANCE_BORDER, pHeigth - DISTANCE_BORDER + NUMBER_DISTANCE,"0");
    	drawCenteredString(DISTANCE_BORDER - NUMBER_DISTANCE, pHeigth - DISTANCE_BORDER, "0");
    	
    	int stepSizeX = graphStepSize(xMax, NUMBER_OF_AXIS_POINTS_X)[0];
    	int stepSizeY = graphStepSize(yMax, NUMBER_OF_AXIS_POINTS_Y)[0];
    	int count = 1;
    	//x axis numbers
    	for (int i = stepSizeX; i <= xMax; i += stepSizeX) {
    		double xCoord = DISTANCE_BORDER + nrToPixelX()*stepSizeX*count;
    		int yCoord = pHeigth - DISTANCE_BORDER;
    		String nrText = i +"";
    		//make numbers to 10 power above 1000.
    		if (i >= 1000) {
    			nrText = getPowerTenNumber(nrText); 
    		}
    		//slope numbers if they get to long.
    		if (g2d.getFontMetrics().stringWidth(nrText) > MIN_AXIS_NUMBER_DISTANCE) {
    			drawCenteredSlopedString((int) xCoord, yCoord + NUMBER_DISTANCE, nrText);
    		}
    		else {
    			drawCenteredString((int) xCoord, yCoord + NUMBER_DISTANCE, nrText);
    		}
    		g2d.setColor(Color.GRAY);
    		g2d.drawLine((int) xCoord, yCoord - SIZE_Y_AXIS, (int) xCoord, yCoord + AXIS_STRIPE_LENGHT);
    		g2d.setColor(Color.BLACK);
    		count ++;
    	}
    	//y axis numbers
    	count = 1;
    	for (int i= stepSizeY; i <= graphStepSize(yMax, NUMBER_OF_AXIS_POINTS_Y)[1]; i += stepSizeY ) {
    		int xCoord = DISTANCE_BORDER;
    		double yCoord =  pHeigth - DISTANCE_BORDER - nrToPixelY(yData)*stepSizeY*count;
    		String nrText = i +"";
    		if (i >= 1000) {
    			nrText = getPowerTenNumber(nrText); 
    		}
    		drawCenteredString(xCoord- NUMBER_DISTANCE, (int) yCoord, nrText);
    		g2d.setColor(Color.GRAY);
    		g2d.drawLine(xCoord- AXIS_STRIPE_LENGHT, (int) yCoord, xCoord + SIZE_X_AXIS,(int) yCoord);
    		g2d.setColor(Color.BLACK);
    		count++;
    	}
    }

    /**
     * Draws the names of the x and y axis based on pre defined fixed 
     * distances and the size of the panel.
     */
	private void drawAxisNames() {
    	Font axisFont=new Font("Ariel", Font.BOLD, AXIS_FONT_SIZE );
    	g2d.setFont(axisFont);
    	//drawing x axis name
    	double xCoord1 = (SIZE_X_AXIS/ 2  + DISTANCE_BORDER);
    	double yCoord1 = (2*pHeigth - DISTANCE_BORDER + NUMBER_DISTANCE)/2;
    	drawCenteredString((int) xCoord1, (int) yCoord1 ,this.axisNames[0]);
    	// drawing y axis name
    	double xCoord2 = (DISTANCE_BORDER - NUMBER_DISTANCE - 10)/2;
    	double yCoord2 = (SIZE_Y_AXIS/ 2 + DISTANCE_END);
    	g2d.translate(xCoord2, yCoord2);
        g2d.rotate(Math.toRadians(-90));
        drawCenteredString(0,0,this.axisNames[1]);
        g2d.translate(-xCoord2,-yCoord2);
        g2d.rotate(Math.toRadians(90));
    }
	
	/**
	 * Draws a string in such a way around a x and y coordinate that it looks
	 * centered around those coordinates.
	 * @param xCoord is the x location of the string to be centered around.
	 * @param yCoord is the y location of the string to be centered around.
	 * @param text is the text to be drawn
	 */
	private void drawCenteredString(int xCoord, int yCoord, String text) {
    	int adjXCoord = xCoord - g2d.getFontMetrics().stringWidth(text)/ 2;
    	int adjYCoord = yCoord  + g2d.getFontMetrics().getHeight()/3;
    	g2d.drawString(text, adjXCoord, adjYCoord);
	}
    
	/**
	 * Draws a string sloped when this exceeds a certain lenght to make sure 
	 * that numbers do not overlap and everything cn be read properly.
	 * @param xCoord is the x location of the string to be centered around.
	 * @param yCoord is the y location of the string to be centered around.
	 * @param text is the text to be drawn
	 */
    private void drawCenteredSlopedString(int xCoord, int yCoord, String text) {
    	double adjXCoord = xCoord - g2d.getFontMetrics().stringWidth(text)/ 2;
    	double adjYCoord = yCoord  - 8;
    	AffineTransform old = g2d.getTransform();
        g2d.rotate(Math.toRadians(45),adjXCoord, adjYCoord);
        g2d.translate(adjXCoord, adjYCoord);
    	g2d.drawString(text, 0,0);
    	g2d.setTransform(old);
    }
    
    /**
     * Is used to update the y data
     * @param data to update to
     */
    public void setYData(int[][][] data) {
    	this.allYData = data;
    }
    
    /**
     * Is used to update the x data
     * @param data to update to
     */
    public void setXData(int[] data) {
    	this.allXData = data;
    }
    
    /**
     * Calculates the size of the font depending on the dimensions of the graph
     * @return an int that tells a font class how big the font has to be.
     */
    private int getAxisFont() {
    	int size = 1;
        while (true) {
            Font font = new Font("Ariel", Font.BOLD, size);
            int testHeight = getFontMetrics(font).getHeight();
            if (testHeight <= DISTANCE_BORDER*0.35) {
                size++;
            } 
            else {
                return size;
            }
        }
	}
    
    /**
     * Function for reducing the saved yData to only include the selected data.
     * @return an array of arrays of arrays that contains all populations that 
     * are selected with there respective attributes.
     */
    private int[][][] getSelectedYData() {
    	//create boolean array to signify which populations and attributes are selected
    	int selectedPopCount = 0;
    	for (int i = 0; i < this.allYData.length; i++) {
    		if (selectedPopulations[i].isSelected()) {
    			selectedPopCount ++;
    		}
    	}
    	int selectedAttrCount = 0;
    	for (int i = 0; i < this.allYData[0].length; i++) {
    		if (selectedAttributes[i].isSelected()) {
    			selectedAttrCount ++;
    		}
    	}
    	int[][][] yData = new int[selectedPopCount][][];
    	int popArrayCount = 0;
    	for (int i = 0; i < this.allYData.length; i++) {
        	if (selectedPopulations[i].isSelected()) {
        		int [][] populationAttributes = new int[selectedAttrCount][];
        		int attrArrayCount = 0;
        		for (int j = 0; j < this.allYData[i].length; j++) {
        			int [] attributeDataPoints = this.allYData[i][j];
        			if (selectedAttributes[j].isSelected()) {
        				populationAttributes[attrArrayCount] = attributeDataPoints;
            			attrArrayCount ++;
        			}
        		}
        		if (populationAttributes.length != 0) {
        			yData[popArrayCount] = populationAttributes;
            		popArrayCount ++;
        		}
        	}
    	}
		return yData;
	}
    
    /**
     * Calculates the max value present in the yData set.
     * @param data an array of arrays of arrays that contains all populations that 
     * are selected with there respective attributes.
     * @return The maximum value of all the data points.
     */
    private int max(int[][][] data) {
    	int maxVal = 0;
    	for (int[][] population : data) {
    		for (int[] dataRow: population) {
	    		for (int n: dataRow) {
		    		if (n > maxVal) {
		    			maxVal = n;
		    		}
	    		}
    		}
    	}
    	return maxVal;
    }
    
    /**
     * Calculates the max value present in the xData set.
     * @param data an array containing all time points on the xData set.
     * @return The maximum value of all the data points.
     */
    private int max(int[] data) {
    	int maxVal = 0;
		for (int n: data) {
    		if (n > maxVal) {
    			maxVal = n;
    		}
    	}
    	return maxVal;
    }
    
    /**
     * Changes a number bigger then 1000 into a string in a nrEpower format
     * @param nr as a string
     * @return a String that represents the number in a ten to the power x
     * format.
     */
    private String getPowerTenNumber(String nr) {
    	char[] nrArray = nr.toCharArray();
    	String returnNr = nrArray[0] + "." + nrArray[1] + "E" + (nrArray.length-1);
		return returnNr;
	}

    /**
     * Calculates the amount of pixels needed per number for the xAxis. This 
     * tells this class how many pixels it should take to draw a distance of 1
     * on the xAxis.
     * @return A double that tells how many pixels a distance of 1 is.
     */
    private double nrToPixelX() {
    	return (SIZE_X_AXIS) / new Double(max(allXData));
    }
    
    /**
     * Calculates the amount of pixels needed per number for the yAxis. This 
     * tells this class how many pixels it should take to draw a distance of 1
     * on the yAxis.
     * @param yData an array of arrays of arrays that contains all populations that 
     * are selected with there respective attributes.
     * @return A double that tells how many pixels a distance of 1 is.
     */
    private double nrToPixelY(int[][][] yData) {
    	return (SIZE_Y_AXIS) / new Double(graphStepSize(max(yData), NUMBER_OF_AXIS_POINTS_Y)[1]);
    }
    
    /**
     * Calculates the stepSize for the numbers on the x or y axis to take
     * for displaying the next value. This number is a multitude of 5.
     * @param val the maximum value on the respective axis
     * @param axisPoints the amount of axis points the graph wants to display.
     * @return an Array that contains the step size and the maximum value for 
     * the respective axis.
     */
    private int[] graphStepSize(int val, int axisPoints) {
    	String sVal = val + "";
    	int groundDiv = (int) Math.pow(10, sVal.length() -2);
    	//if the lenght of sVal is smaller then 2 the groundDiv is set to 1 to
    	//ensure no division by 0 error.
    	if (groundDiv == 0) groundDiv = 1;
    	while (val % groundDiv != 0) {
    		val += 1;
    	}
    	int stepSize = 1;
    	int count = 0;
    	while(val / stepSize > axisPoints) {
    		if (count == 0) { 
    			stepSize *= 5;
    			count ++;
    		}
    		else {
    			stepSize *= 2;
    			count = 0;
    		}
    	}
    	return new int [] {stepSize, val};
    }
}
