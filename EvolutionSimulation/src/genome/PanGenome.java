package genome;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import simulation.Species;

//function for making a text file for genome and genes.
public class PanGenome {
	
	private String fileName;
	private String type;
	private boolean perfectGenes;
	private BufferedWriter writer;


	public PanGenome(String fileName, String type) {
		this.fileName = fileName;
		this.type = type;
		this.perfectGenes = false;
		try {
			writer = new BufferedWriter(new FileWriter(".//Data//"+ fileName +".fa" , false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void writeSpeciesInfo(Species s, int prevNumber){
		try {
			writer = new BufferedWriter(new FileWriter(".//Data//"+ fileName +".fa" , true));
			//making header contain all perfect genes
			if (!perfectGenes) {
				for (String key : s.getGenome().getPerfectGenes().keySet()) {
					writer.write(">>"+ key + "\n"+s.getGenome().getPerfectGenes().get(key).getSequence()+"\n");
				}
				this.perfectGenes = true;
			}
			writer.write(">" +s.getNumber() +"<--"+ prevNumber + "\n" +s.getGenome().getDNACode()+"\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
