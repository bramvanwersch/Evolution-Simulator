package guilessRuns;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import gameobjects.Ecosystem;
import populations.Population;
import populations.PopulationData;

public class DataSaver {
	private int runCounter;
	private PopulationData populationData;
	private Ecosystem environment;
	private Population population;
	private String lineSeparator = System.getProperty("line.separator");
	private boolean winner;
	
	
	public DataSaver(PopulationData populationData,Population population) {
		runCounter = 0;
		this.populationData = populationData; 
		this.population = population;
	}
	public void saveLoser()  {
		StringBuilder dataStringBuilder = new StringBuilder();
		String filename = "DataDocumentLoser.txt";
		File file = new File (filename);
		boolean append = false;
		if (file.length()==0) {
			dataStringBuilder.append(makeHeader());
		}else {
			append =true;
		}
		// give false to makeString so that not the last but the second last values are taken.... 
		// Because every stat gets to zero if that species died
		dataStringBuilder.append(makeString(false));
		String dataString = dataStringBuilder.toString();
		try {
			writeToFile(dataString, filename, append);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void saveWinner()  {
		StringBuilder dataStringBuilder = new StringBuilder();
		String filename = "DataDocumentWinner.txt";
		File file = new File (filename);
		boolean append = false;
		if (file.length()==0) {
			dataStringBuilder.append(makeHeader());
		}else {
			dataStringBuilder.append(lineSeparator);
			append =true;
		}
		dataStringBuilder.append(makeString(true));
		String dataString = dataStringBuilder.toString();
		try {
			writeToFile(dataString, filename, append);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	private String makeHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append("AvgAge" +"\t");
		sb.append("MinAge" +"\t");
		sb.append("MaxAge"+"\t");
		sb.append("AvgEnergyCost"+"\t");
		sb.append("MinEnergyCost" +"\t");
		sb.append("MaxEnergyCost" +"\t");
		sb.append("AvgSize"+"\t");
		sb.append("MinSize" +"\t");
		sb.append("MaxSize" +"\t");
		sb.append("AvgSpeed"+"\t");
		sb.append("MinSpeed" +"\t");
		sb.append("MaxSpeed" +"\t");
		sb.append("AvgScent"+"\t");
		sb.append("MinScent" +"\t");
		sb.append("MaxScent" +"\t");
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
	
	private String  makeString(boolean winner) {
		ArrayList<String> eatingPrefList = getEatingPref(populationData);
		StringBuilder sb = new StringBuilder();
		int length = 5;
		if (winner==true) {
			length = populationData.getAgeStats().length - 2;
		}else  {
			length = populationData.getAgeStats().length -2 ;
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now(); 
		// initial values
		sb.append(Integer.toString(populationData.getAgeStats()[0][0]));
		sb.append("\t"+Integer.toString(populationData.getAgeStats()[0][1]));
		sb.append("\t"+Integer.toString(populationData.getAgeStats()[0][2]));
		sb.append("\t" + Integer.toString(populationData.getEnergyCostStats()[0][0]));
		sb.append("\t" + Integer.toString(populationData.getEnergyCostStats()[0][1]));
		sb.append("\t" + Integer.toString(populationData.getEnergyCostStats()[0][2]));
		sb.append("\t" + Integer.toString(populationData.getSizeStats()[0][0]));
		sb.append("\t" + Integer.toString(populationData.getSizeStats()[0][1]));
		sb.append("\t" + Integer.toString(populationData.getSizeStats()[0][2]));
		sb.append("\t" + Integer.toString(populationData.getSpeedStats()[0][0]));
		sb.append("\t" + Integer.toString(populationData.getSpeedStats()[0][1]));
		sb.append("\t" + Integer.toString(populationData.getSpeedStats()[0][2]));
		sb.append("\t" + Integer.toString(populationData.getScentStats()[0][0]));
		sb.append("\t" + Integer.toString(populationData.getScentStats()[0][1]));
		sb.append("\t" + Integer.toString(populationData.getScentStats()[0][2]));
		sb.append("\t" + Integer.toString(populationData.getNrSpecies()[0]));
		sb.append("\t" + eatingPrefList.get(0));
		sb.append("\t" + now);
		sb.append("\t"+ "START");
		sb.append(lineSeparator);
		// last values
		sb.append(Integer.toString(populationData.getAgeStats()[length][0]));
		sb.append("\t"+Integer.toString(populationData.getAgeStats()[length][1]));
		sb.append("\t"+Integer.toString(populationData.getAgeStats()[length][2]));
		sb.append("\t" + Integer.toString(populationData.getEnergyCostStats()[length][0]));
		sb.append("\t" + Integer.toString(populationData.getEnergyCostStats()[length][1]));
		sb.append("\t" + Integer.toString(populationData.getEnergyCostStats()[length][2]));
		sb.append("\t" + Integer.toString(populationData.getSizeStats()[length][0]));
		sb.append("\t" + Integer.toString(populationData.getSizeStats()[length][1]));
		sb.append("\t" + Integer.toString(populationData.getSizeStats()[length][2]));
		sb.append("\t" + Integer.toString(populationData.getSpeedStats()[length][0]));
		sb.append("\t" + Integer.toString(populationData.getSpeedStats()[length][1]));
		sb.append("\t" + Integer.toString(populationData.getSpeedStats()[length][2]));
		sb.append("\t" + Integer.toString(populationData.getScentStats()[length][0]));
		sb.append("\t" + Integer.toString(populationData.getScentStats()[length][1]));
		sb.append("\t" + Integer.toString(populationData.getScentStats()[length][2]));
		sb.append("\t" + Integer.toString(populationData.getNrSpecies()[length]));
		sb.append("\t" + eatingPrefList.get(0));
		sb.append("\t" + now);
		sb.append("\t"+ "END");
		sb.append(lineSeparator);
		return sb.toString();
	}
	/*Writer function which takes the created string and writes it to file. It uses PrintWriter because that is suited for formatted files
	 * 
	 */
	public void writeToFile(String dataString, String filename, boolean append) throws IOException {
		FileWriter FileWriter = null;
		try {
			FileWriter = new FileWriter(filename, append);
			FileWriter.write(dataString);
		} catch (IOException e) {
			e.fillInStackTrace();
		}finally {
			FileWriter.flush();
			FileWriter.close();
			
		}
	}
	}
