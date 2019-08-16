package guilessRuns;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import simulation.Environment;
import simulation.Population;
import simulation.PopulationData;

public class DataSaver {
	private int runCounter;
	private PopulationData soleSurvivor;
	private Environment environment;
	private Population population;
	private String lineSeparator = System.getProperty("line.separator");
	
	
	
	public DataSaver(PopulationData soleSurvivor,Population population) {
		runCounter = 0;
		this.soleSurvivor = soleSurvivor; 
		this.population = population;
		
	}
	
	public void saveDataWrapper() {
		StringBuilder dataStringBuilder = new StringBuilder();
		File file = new File("DataDocument.txt");
		if (file.length() == 0) {
			dataStringBuilder.append(makeHeader());
		}else {
			dataStringBuilder.append(lineSeparator);
		}
		dataStringBuilder.append(makeString());
		String dataString = dataStringBuilder.toString();
		writeToFile(dataString);
	}
	
	private String makeHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append("AvgAge" +"\t");
		sb.append("AvgEnergyCost"+"\t");
		sb.append("AvgSize"+"\t");
		sb.append("AvgSpeed"+"\t");
		sb.append("AvgScent"+"\t");
		sb.append("NrSpec"+"\t");
		sb.append("EatingPref"+"\t");
		sb.append("DATETAG"+"\t");
		sb.append("Start/End"+"\t");
		sb.append(lineSeparator);
		return sb.toString();
	}
	
	private ArrayList<String> getEatingPref(PopulationData soleSurvivor){

		
		String eatingPref = "Nan";
		if(population.getType().equals("Carnivore")){
			eatingPref = "C";
		}else if (population.getType().equals("Herbivore")) {
			eatingPref = "H";
		}else if (population.getType().equals("Omnivore")) {
			eatingPref = "O";
		}
		ArrayList<String> eatingPrefList = new ArrayList<String>(2);
		for (int i = 0 ; i < 2 ; i++) {
			eatingPrefList.add(eatingPref);
		}
		return eatingPrefList;
	}
	
	private String  makeString() {
		ArrayList<String> eatingPrefList = getEatingPref(soleSurvivor);
		StringBuilder sb = new StringBuilder();
		int length = soleSurvivor.getAvgAge().length-1;	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now(); 
		// initial values
		sb.append(Integer.toString(soleSurvivor.getAvgAge()[0]));
		sb.append("\t" + Integer.toString(soleSurvivor.getAvgEnergyCost()[0]));
		sb.append("\t" + Integer.toString(soleSurvivor.getAvgSize()[0]));
		sb.append("\t" + Integer.toString(soleSurvivor.getAvgSpeed()[0]));
		sb.append("\t" + Integer.toString(soleSurvivor.getAvgScent()[0]));
		sb.append("\t" + Integer.toString(soleSurvivor.getNrSpecies()[0]));
		sb.append("\t" + eatingPrefList.get(0));
		sb.append("\t" + now);
		sb.append("\t"+ "START");
		sb.append(lineSeparator);
		// last values
		sb.append(Integer.toString(soleSurvivor.getAvgAge()[length]));
		sb.append("\t" + Integer.toString(soleSurvivor.getAvgEnergyCost()[length]));
		sb.append("\t" + Integer.toString(soleSurvivor.getAvgSize()[length]));
		sb.append("\t" + Integer.toString(soleSurvivor.getAvgSpeed()[length]));
		sb.append("\t" + Integer.toString(soleSurvivor.getAvgScent()[length]));
		sb.append("\t" + Integer.toString(soleSurvivor.getNrSpecies()[length]));
		sb.append("\t" + eatingPrefList.get(0));
		sb.append("\t" + now);
		sb.append("\t"+ "END");
		sb.append(lineSeparator);
		return sb.toString();
	}
	/*Writer function which takes the created string and writes it to file. It uses PrintWriter because that is suited for formatted files
	 * 
	 */
	public void writeToFile(String dataString) {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter("DataDocument.txt");
			printWriter.write(dataString);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			printWriter.flush();
			printWriter.close();
			
		}
	}
	}
