package sources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MotifGenerated {

	// **********************************************
	// attributes
	// **********************************************
	private int w;
	private int N;
	private int nMutation;
	private String base;
	private List<String> motifs;

	// **********************************************
	// default constructor
	// **********************************************
	public MotifGenerated(String base, int w, int N, int nMutation) {
		this.base = base;
		this.w = w;
		this.N = N;
		this.nMutation = nMutation;
		motifs = new ArrayList<>();
		start();
	}

	// **********************************************
	// start method
	// **********************************************
	private void start() {
		generatedMotifs();
		mutation();
	}

	// **********************************************
	// do mutation
	// **********************************************
	private void mutation() {
		int k = 0;
		for (String seq : motifs) {
			StringBuilder sb = new StringBuilder(seq);
			for (int i = 0; i < nMutation; i++) {
				int r = new Random().nextInt(seq.length());
				double t = new Random().nextDouble();
				String c = "";
				if (t < 0.25) {
					c = "a";
				} else if (t > 0.25 && t < 0.25 * 2) {
					c = "c";
				} else if (t > 0.25 * 2 && t < 0.25 * 3) {
					c = "g";
				} else {
					c = "t";
				}

				sb.replace(r, r + 1, c.toUpperCase());
			}
			motifs.set(k++, sb.toString());
		}

	}

	// **********************************************
	// generated motifs
	// **********************************************
	private void generatedMotifs() {
		for (int i = 0; i < N; i++) {
			String seq = "";
			for (int j = 0; j < w; j++) {
				seq += base;
			}
			motifs.add(seq.toUpperCase());
		}
	}

	// **********************************************
	// get motifs
	// **********************************************
	public List<String> getMotifs() {
		return motifs;
	}

}
