package guilessRuns;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	
	
	public DataSaver(PopulationData soleSurvivor,Environment environment) {
		runCounter = 0;
		this.soleSurvivor = soleSurvivor; 
		this.environment = environment;
		
	}
	
	public void saveDataWrapper() {
		StringBuilder dataStringBuilder = new StringBuilder();
		File file = new File("DataDocument.txt");
		if (file.length() == 0) {
			dataStringBuilder.append(makeHeader());
		}else {
			dataStringBuilder.append("\n");
		}
		dataStringBuilder.append(makeString());
		System.out.println("Inside the wrapper");
		String dataString = dataStringBuilder.toString();
		System.out.println(dataString);
		writeToFile(dataString);
	}
	
	private String makeHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append("AvgEnergyCost");
		sb.append("AvgSize");
		sb.append("AvgSpeed");
		sb.append("AvgScent");
		sb.append("NrSpec");
		sb.append("EatingPref");
		sb.append("DATETAG");
		sb.append("Start/End");
		sb.append("\n");
		return sb.toString();
	}
	
	private ArrayList<String> getEatingPref(PopulationData soleSurvivor){
		System.out.println("Value of environment");
		System.out.println(environment.getNrFood());
		Population pop =  environment.getMaxNrSpeciesPop();
		String eatingPref = "Nan";
		if(pop.getType().equals("Carnivore")){
			eatingPref = "C";
		}else if (pop.getType().equals("Herbivore")) {
			eatingPref = "H";
		}else if (pop.getType().equals("Omnivore")) {
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
		sb.append("\n");
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
		sb.append("\n");
		return sb.toString();
	}
	public void writeToFile(String dataString) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("DataDocument.txt", true);
			fileWriter.write(dataString);

			System.out.println("File was written");
		} catch (IOException e) {
			System.out.println("File could not be found");
			e.printStackTrace();
		}finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("File was not saved");
				e.printStackTrace();
			}
			
		}
		
		if(fileWriter==null) {
			System.out.println("no file");
		}
	}
	}
