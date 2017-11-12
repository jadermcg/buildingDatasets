package execution;

import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.Map;

import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.io.FastaReaderHelper;

public class JasparAdjust {

	// ***************************************************************************
	// executa metodos
	// ***************************************************************************
	public static void main(String[] args) throws Exception {

		// datasetname
		String datasetName = "MA0150.2";

		// caminho
		String path = System.getProperty("user.home") + "/datasets/final/jaspar/" + datasetName + "/";

		// metodos de ajuste - rodar nesta sequencia
		adjustDataset(path);
		buildMsaFile(path);
		buildPositionFile(path);
		buildParamFile(path, false, 0.0001);
	}

	// ***************************************************************************
	// ajusta o dataset jaspar
	// ***************************************************************************
	public static void adjustDataset(String path) throws Exception {

		// dataset file
		String datasetFile = path + "dataset.fa";

		// carrega dataset
		Map<String, DNASequence> data = FastaReaderHelper.readFastaDNASequence(Paths.get(
				datasetFile).toFile());

		// print stream
		PrintStream ps = new PrintStream(datasetFile);

		// ajusta dataset com nome correto de sequencia. ex. seq1, seq2, etc...
		int seqNumber = 1;
		for (String k : data.keySet()) {
			String seq = data.get(k).getSequenceAsString();
			ps.print(">seq" + seqNumber++ + "\n");
			int j = 0;
			for (int i = 0; i < seq.length(); i++) {
				if (j != 60) {
					ps.print(seq.charAt(i));
					j++;
				} else {
					ps.print("\n" + seq.charAt(i));
					j = 0;
				}
			}
			ps.println();
		}

		ps.close();
	}

	// ***************************************************************************
	// cria arquivo de motifs
	// ***************************************************************************
	public static void buildMsaFile(String path) throws Exception {
		// msa file
		String msaFile = path + "msa.out";

		// dataset file
		String datasetFile = path + "dataset.fa";

		// carrega dataset
		Map<String, DNASequence> data = FastaReaderHelper.readFastaDNASequence(Paths.get(
				datasetFile).toFile());

		// print stream
		PrintStream ps = new PrintStream(msaFile);

		// extrai os motifs
		int seqNumber = 1;
		for (String k : data.keySet()) {
			String seq = data.get(k).getSequenceAsString();
			ps.println(">seq" + seqNumber++);
			for (int i = 0; i < seq.length(); i++) {
				char c = seq.charAt(i);
				if (Character.isUpperCase(c))
					ps.print(c);
			}
			ps.print("\n");
		}

		ps.close();
	}

	// ***************************************************************************
	// cria arquivo de posicoes
	// ***************************************************************************
	public static void buildPositionFile(String path) throws Exception {
		// positions file
		String positionsFile = path + "positions.conf";

		// dataset file
		String datasetFile = path + "dataset.fa";

		// carrega dataset
		Map<String, DNASequence> data = FastaReaderHelper.readFastaDNASequence(Paths.get(
				datasetFile).toFile());

		// print stream
		PrintStream ps = new PrintStream(positionsFile);

		// extrai as posicoes
		int seqNumber = 1;
		for (String k : data.keySet()) {
			String seq = data.get(k).getSequenceAsString();
			ps.print(seqNumber++ + "=");
			for (int i = 0; i < seq.length(); i++) {
				char c = seq.charAt(i);
				if (Character.isUpperCase(c)) {
					ps.print(i + "\n");
					break;
				}
			}
		}

		ps.close();
	}

	// ***************************************************************************
	// cria arquivo de parametros
	// ***************************************************************************
	public static void buildParamFile(String path, boolean dimmer, double significancia)
			throws Exception {
		// parametros
		int w = 0, total = 0, positivos = 0, negativos = 0;

		// msa file
		String msaFile = path + "msa.out";

		// dataset file
		String datasetFile = path + "dataset.fa";

		// carrega msa
		Map<String, DNASequence> msa = FastaReaderHelper.readFastaDNASequence(Paths.get(msaFile)
				.toFile());

		// carrega dataset
		Map<String, DNASequence> data = FastaReaderHelper.readFastaDNASequence(Paths.get(
				datasetFile).toFile());

		// le tamanho do motif
		w = msa.get("seq1").getLength();
		total = (data.get("seq1").getLength() - w + 1) * data.size();
		positivos = msa.size();
		negativos = total - positivos;

		PrintStream ps = new PrintStream(path + "param.conf");
		ps.println(w);
		ps.println(significancia);
		ps.println(positivos);
		ps.println(negativos);
		ps.print(dimmer);
		ps.close();

	}

}
