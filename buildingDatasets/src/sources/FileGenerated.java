package sources;

import java.io.File;
import java.io.PrintStream;
import java.util.List;

public class FileGenerated {

	// ******************************************************
	// generated dataset and positions files
	// ******************************************************
	public static void generated(List<String> dataset, List<Integer> positions, String dir,
			String datasetOut, String positionsOut, String paramOut, int N, int L, int w)
			throws Exception {

		PrintStream ps = new PrintStream(new File(datasetOut));
		// generated dataset file
		int i = 1;
		for (String seq : dataset) {
			ps.println(">seq" + i++);
			int k = 0;
			for (int j = 0; j < seq.length(); j++) {
				ps.print(seq.charAt(j));
				k++;
				if (k == 60) {
					ps.print("\n");
					k = 0;
				}
			}
			ps.print("\n\n");
		}
		ps.close();

		// genarated positions file
		int seqNumber[] = { 1 };
		ps = new PrintStream(positionsOut);

		for (int p : positions) {
			if (p != -1)
				ps.println(seqNumber[0] + "=" + p);
			seqNumber[0]++;
		}

		ps.close();

		// generated param file
		// ps = new PrintStream(paramOut);
		// ps.println(w);
		// ps.println(0.0001);
		// ps.println(N);
		// ps.println(((L - w + 1) * N) - N);
		// ps.println("false");
		// ps.close();
	}
}
