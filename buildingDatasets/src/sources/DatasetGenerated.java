package sources;

import java.util.ArrayList;
import java.util.List;

import util.Functions;

/**
 * Generate and plants motif into a dataset
 * 
 * @author jadermcg
 *
 */
public class DatasetGenerated {

	// *********************************************************
	// attributes
	// *********************************************************
	private Background bg;
	private int N;
	private int L;
	private List<String> dataset;

	// *********************************************************
	// public constructor
	// *********************************************************
	public DatasetGenerated(Background bg, int N, int L) {
		this.bg = bg;
		this.N = N;
		this.L = L;
		dataset = new ArrayList<>();
		start();
	}

	// *********************************************************
	// start method
	// *********************************************************
	public void start() {
		datasetGenerate();
	}

	// *********************************************************
	// generated dataset with background probabilities
	// *********************************************************
	public void datasetGenerate() {
		for (int i = 0; i < N; i++) {
			String seq = "";
			for (int j = 0; j < L; j++) {
				seq = seq.concat(Functions.generateNucleotideFromBgProbability((bg)));
			}
			dataset.add(seq);
		}
	}

	// *********************************************************
	// returns dataset with plated motifs
	// *********************************************************
	public List<String> getDataset() {
		return dataset;
	}

}
