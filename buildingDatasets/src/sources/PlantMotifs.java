package sources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlantMotifs {

	// *****************************************************
	// attributes
	// *****************************************************
	private List<String> dataset;
	private List<String> motifs;
	private List<Integer> positions;
	private boolean oops;

	// *****************************************************
	// default constructor
	// *****************************************************
	public PlantMotifs(List<String> dataset, List<String> motifs, boolean oops) {
		this.dataset = dataset;
		this.motifs = motifs;
		this.oops = oops;
		positions = new ArrayList<>();
		start();
	}

	// *****************************************************
	// start method
	// *****************************************************
	private void start() {
		if (oops)
			plantOnePeerSequence();
		else
			plantZeroOnePeerSequence();
	}

	// *****************************************************
	// plant zero or one motif peer sequence
	// *****************************************************
	private void plantZeroOnePeerSequence() {
		int x = 0;
		for (String seq : dataset) {
			StringBuilder temp = new StringBuilder(seq);
			int p = new Random().nextInt(motifs.size());
			String motif = motifs.get(p);
			int w = motif.length();
			int L = temp.length();
			double q = Math.random();
			if (q < 0.9) {
				int pos = new Random().nextInt(L - w + 1);
				temp.replace(pos, pos + w, motif.toUpperCase());
				positions.add(pos);
				motifs.remove(p);
			} else {
				positions.add(-1);
			}
			dataset.set(x++, temp.toString());
		}
	}

	// *****************************************************
	// plant one motif peer sequence
	// *****************************************************
	private void plantOnePeerSequence() {
		int x = 0;
		for (String seq : dataset) {
			StringBuilder temp = new StringBuilder(seq);
			int p = new Random().nextInt(motifs.size());
			String motif = motifs.get(p);
			int w = motif.length();
			int L = temp.length();
			int q = new Random().nextInt(L - w + 1);
			temp.replace(q, q + w, motif.toUpperCase());
			positions.add(q);
			motifs.remove(p);
			dataset.set(x++, temp.toString());
		}
	}

	// *****************************************************
	// return dataset whith planted motifs
	// *****************************************************
	public List<String> getDataset() {
		return dataset;
	}

	// *****************************************************
	// return positions where motifs were planted
	// *****************************************************
	public List<Integer> getPositions() {
		return positions;
	}
}
