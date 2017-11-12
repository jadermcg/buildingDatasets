package sources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.io.FastaReaderHelper;

public class JaspaReadFile {

	private String jasparFile;

	// *************************************************************
	// public constructor
	// *************************************************************
	public JaspaReadFile(String jasparFile) throws Exception {
		this.jasparFile = jasparFile;
	}

	// *************************************************************
	// get upper case nucleotides of jaspar configuration file
	// *************************************************************
	public List<String> getMotifs() throws Exception {
		Map<String, DNASequence> dataset = FastaReaderHelper.readFastaDNASequence(new File(
				jasparFile));
		List<String> motifs = new ArrayList<>();

		dataset.forEach((k, v) -> {
			String s = v.getSequenceAsString();
			String temp = "";
			for (int i = 0; i < s.length(); i++) {
				Character c = s.charAt(i);
				if (Character.isUpperCase(c)) {
					temp += c;
				}
			}
			motifs.add(temp);
		});

		return motifs;

	}

}
