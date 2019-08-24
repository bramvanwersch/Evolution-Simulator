package genome;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import gameobjects.Species;

//function for making a text file for genome and genes.
public class PanGenome {
	
	private String fileName;
	private BufferedWriter writer;


	public PanGenome(String fileName) {
		this.fileName = fileName;
		try {
			writer = new BufferedWriter(new FileWriter(".//Data//"+ fileName +".fa" , false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void writeSpeciesInfo(String s){
		try {
			writer = new BufferedWriter(new FileWriter(".//Data//"+ fileName +".fa" , true));
			writer.write(s);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
