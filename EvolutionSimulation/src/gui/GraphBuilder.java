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

import simulation.Environment;
import simulation.PopulationData;

import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Arrays;

public class GraphBuilder extends Thread{
	private final Color[] attributeColors = new Color[] {new Color(0,105,0), new Color(191,61,255), new Color(255,0,0), 
		 	 								new Color(21,235,231), new Color(13,49,208), new Color(11,243,24)};
	private JPanel sidePanel;
	private GraphBuilder1 graphPanel;
	private Environment environment;
	public JRadioButton[] selectedPopulations;
	public JRadioButton[] selectedAttributes;
	private boolean lookingAtGraph;
	
	public GraphBuilder(Environment environment, String[] populationNames, String[] attributeNames
			,int width,int height,String[] axisNames, boolean dataPoints) {
		this.environment = environment;
		this.lookingAtGraph = true;
		JFrame f = new JFrame("Graph");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sidePanel = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		sidePanel.setLayout(gbl);
		selectedPopulations = new JRadioButton[populationNames.length];
		selectedAttributes = new JRadioButton[attributeNames.length];
		
		//HARDCODED
		createSidePanelWidgets(populationNames, attributeNames);
		graphPanel = new GraphBuilder1(getXData(),getYData(), selectedPopulations, selectedAttributes,
				width,height,axisNames, dataPoints, attributeColors);
		f.add(graphPanel, BorderLayout.WEST);
		f.add(sidePanel, BorderLayout.EAST);
		f.pack();
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				lookingAtGraph = false;
			}
		});
	}
	
	public void run() {
		while(lookingAtGraph) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			graphPanel.setYData(getYData());
			graphPanel.setXData(getXData());
			graphPanel.repaint();
		}
	}
	
	private int[][][] getYData(){
		int [][][] yDataArray = new int[environment.getAllPopData().length][][];
		for (int j = 0; j < environment.getAllPopData().length; j++) {
			PopulationData pd = environment.getAllPopData()[j];
			pd.setDataDivisionFactor();
			yDataArray[j] = pd.getDataArray();
		}
		return yDataArray;
	}
	
	private int[] getXData() {
		environment.getAveragePopData().setDataDivisionFactor();
		return environment.getAveragePopData().getTime();
	}
    
    private void createSidePanelWidgets(String[] populationNames, String[] attributeNames){
    	//Hardcoded
    	//spacing kind of bad solution.
    	JLabel populationsLbl = new JLabel("Populations:            ");
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.anchor = gbc.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		sidePanel.add(populationsLbl, gbc);
    	for (int i = 0; i < populationNames.length; i++) {
    		JRadioButton rd = new JRadioButton(populationNames[i]);
    		rd.setSelected(true);
        	gbc.anchor = gbc.CENTER;
    		gbc.gridy += 1;
    		sidePanel.add(rd, gbc);
    		selectedPopulations[i] = rd;
    	}
    	gbc.anchor = gbc.WEST;
    	JLabel spaceLbl = new JLabel("  ");
		gbc.gridy += 1;
		sidePanel.add(spaceLbl, gbc);
    	JLabel attributeLbl = new JLabel("Attributes:");
		gbc.gridy += 1;
		sidePanel.add(attributeLbl, gbc);
    	for (int j = 0; j < attributeNames.length; j++) {
        	gbc.anchor = gbc.CENTER;
    		JRadioButton rd = new JRadioButton(attributeNames[j]);
    		rd.setSelected(true);
    		rd.setForeground(attributeColors[j]);
    		gbc.gridy += 1;
    		sidePanel.add(rd, gbc);
    		selectedAttributes[j] = rd;
    	}
    	gbc.anchor = gbc.CENTER;
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

class GraphBuilder1 extends JPanel{
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
	
	private JRadioButton[] selectedPopulations;
	private JRadioButton[] selectedAttributes;
	private Color[] colors;
	private Stroke[] strokes;


    public GraphBuilder1(int[] xData, int[][][] yData, JRadioButton[] selectedPopulations, JRadioButton[] selectedAttributes,
    		int width,int height,String[] axisNames, boolean dataPoints, Color[] attributeColors) {
    	this.allXData =xData;
    	this.allYData = yData;
    	this.pHeigth = (int) height;
    	this.pWidth= (int) width;
    	this.axisNames = axisNames;
    	this.dataPoints = dataPoints;
    	this.selectedPopulations = selectedPopulations;
    	this.selectedAttributes = selectedAttributes;
        this.colors = attributeColors;
        strokes = new Stroke[] {new BasicStroke(3), 
        		new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0),
                new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4, 2, 8, 2}, 0),
                new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1,2}, 0)};
    	this.setBackground(Color.WHITE);
    }

	public Dimension getPreferredSize() {
        return new Dimension(pWidth,pHeigth);
    }

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

	public int getNrOfAxisPoints(int axisSize) {
    	double nrOfPoints = 100;
    	while (axisSize / nrOfPoints < MIN_AXIS_NUMBER_DISTANCE) {
    		nrOfPoints -= 1;
    	}
    	return (int) nrOfPoints;
    }
    
    public void drawGraphLine(int[] xData, int[] yData, int[][][] selectedYData, Color col, Stroke stroke){
    	g2d.setStroke(stroke);
        for (int i = 0; i < xData.length-1; i++) {
        	g2d.setColor(col);
        	int x1 = (int) (xData[i]*nrToPixelX() + DISTANCE_BORDER);
        	int x2 = (int) (xData[i+1]*nrToPixelX() + DISTANCE_BORDER);
        	int y1 = (int) ((pHeigth - yData[i]*nrToPixelY(selectedYData)) - DISTANCE_BORDER);
        	int y2 = (int) ((pHeigth -yData[i+1]*nrToPixelY(selectedYData)) - DISTANCE_BORDER);
            g2d.drawLine(x1, y1, x2, y2);
            g2d.setColor(Color.BLACK);
            if (dataPoints) {
              g2d.fillOval(x1 - SIZE_OF_POINTS/2, y1 - SIZE_OF_POINTS/2,SIZE_OF_POINTS, SIZE_OF_POINTS);
              g2d.fillOval(x2 - SIZE_OF_POINTS/2, y2 - SIZE_OF_POINTS/2,SIZE_OF_POINTS, SIZE_OF_POINTS);
            }
        }
    }
    
    public void drawGraphGrid(int[][][] yData) {
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
	
	private void drawCenteredString(int xCoord, int yCoord, String text) {
    	int adjXCoord = xCoord - g2d.getFontMetrics().stringWidth(text)/ 2;
    	int adjYCoord = yCoord  + g2d.getFontMetrics().getHeight()/3;
    	g2d.drawString(text, adjXCoord, adjYCoord);
	}
    
    private void drawCenteredSlopedString(int xCoord, int yCoord, String text) {
    	double adjXCoord = xCoord - g2d.getFontMetrics().stringWidth(text)/ 2;
    	double adjYCoord = yCoord  - 8;
    	AffineTransform old = g2d.getTransform();
        g2d.rotate(Math.toRadians(45),adjXCoord, adjYCoord);
        g2d.translate(adjXCoord, adjYCoord);
    	g2d.drawString(text, 0,0);
    	g2d.setTransform(old);
    }
    
    public void setYData(int[][][] data) {
    	this.allYData = data;
    }
    
    public void setXData(int[] data) {
    	this.allXData = data;
    }
    
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
    
    private int max(int[] data) {
    	int maxVal = 0;
		for (int n: data) {
    		if (n > maxVal) {
    			maxVal = n;
    		}
    	}
    	return maxVal;
    }
    
    
    
    private String getPowerTenNumber(String nr) {
		//return a string that depicts a nr in a ten power format.
    	char[] nrArray = nr.toCharArray();
    	String returnNr = nrArray[0] + "." + nrArray[1] + "E" + (nrArray.length-1);
		return returnNr;
	}
    
    //calculating the pixels per whole number on a scale.
    private double nrToPixelX() {
    	return (SIZE_X_AXIS) / new Double(max(allXData));
    }
    
    private double nrToPixelY(int[][][] yData) {
    	return (SIZE_Y_AXIS) / new Double(graphStepSize(max(yData), NUMBER_OF_AXIS_POINTS_Y)[1]);
    }
    
    private int[] graphStepSize(int val, int axisPoints) {
    	String sVal = val+"";
    	int groundDiv = (int) Math.pow(10, sVal.length() -2);
    	if (groundDiv == 0){ groundDiv = 1;}
    	while (val % groundDiv != 0) {
    		val += 1;
    	}
    	int stepSize = 1;
    	int count = 0;
    	while(val / stepSize > axisPoints) {
    		if (count == 0) { 
    			stepSize *= 5;
    			count ++;
    		}else {
    			stepSize *= 2;
    			count = 0;}
    	}
    	return new int [] {stepSize, val};
    }
}
