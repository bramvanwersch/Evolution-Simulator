package genome;

import java.util.HashMap;
import java.util.Map;

public class DNAtoAA {
	private Map<String, String> dnaAA;
	private String[] aaNames = {"A","R","N","D","C","Q","E","G","H","I",
					"L","K","M","F","P","S","T","W","Y","V","#"};
	private String[][] dnaCodes = 
			{{"GCT","GCC","GCA","GCG"},
			{"CGT","CGC","CGA","CGG","AGA","AGG"},
			{"AAT","AAC"},
			{"GAT","GAC"},
			{"TGT","TGC"},
			{"CAA","CAG"},
			{"GAA","GAG"},
			{"GGT","GGC","GGA","GGG"},
			{"CAT","CAC"},
			{"ATT","ATC","ATA"},
			{"TTA","TTG","CTT","CTC","CTA","CTG"},
			{"AAA","AAG"},
			{"ATG"},
			{"TTT","TTC"},
			{"CCT","CCC","CCA","CCG"},
			{"TCT","TCC","TCA","TCG","AGT","AGC"},
			{"ACT","ACC","ACA","ACG"},
			{"TGG"},
			{"TAT","TAC"},
			{"GTT","GTC","GTA","GTG"},
			{"TAA","TGA","TAG"}};
	
	public DNAtoAA() {
		dnaAA = new HashMap<String, String>();
		createMap();
	}

	private void createMap() {
		for (int i = 0; i < dnaCodes.length; i++) {
			for (String dnaCodon : dnaCodes[i]) {
				dnaAA.put(dnaCodon, aaNames[i]);
			}
		}
	}
	
	public Map<String, String> getDNAConversionMap(){
		return dnaAA;
	}

}
