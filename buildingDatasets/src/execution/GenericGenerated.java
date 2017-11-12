package execution;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import sources.Background;
import sources.DatasetGenerated;
import sources.FileGenerated;
import sources.MotifGenerated;
import sources.PlantMotifs;

public class GenericGenerated {

	public static void main(String[] args) throws Exception {

		// **************************************************
		// attributes
		// **************************************************
		String dir = "SSH";
		int N = 30;
		int w = 10;
		int nMutation = 2;
		int L = 500;
		int numberOfDatasets = 5;
		String base = "a";
		boolean oops = false;

		// **************************************************
		// background probabilities
		// **************************************************
		Map<String, Double> probs = new LinkedHashMap<>();
		probs.put("a", 1 / 10d);
		probs.put("c", 2 / 10d);
		probs.put("g", 3 / 10d);
		probs.put("t", 4 / 10d);
		Background bg = new Background(probs, 1);

		int n = 1;
		while (n <= numberOfDatasets) {

			// **************************************************
			// dataset and motif generated
			// **************************************************
			MotifGenerated mg = new MotifGenerated(base, w, N, nMutation);
			DatasetGenerated dg = new DatasetGenerated(bg, N, L);
			PlantMotifs pm = new PlantMotifs(dg.getDataset(), mg.getMotifs(), oops);

			List<String> dataset = pm.getDataset();
			List<Integer> positions = pm.getPositions();

			// **************************************************
			// generated file
			// **************************************************
			new File(dir).mkdir();

			String sub = dir + "/" + n;
			File f = new File(sub);
			f.mkdir();
			String dirr = f.getPath() + "/";
			String datasetOut = dirr + "dataset.fa";
			String positionsOut = dirr + "positions.conf";
			String paramOut = dirr + "param.conf";
			FileGenerated.generated(dataset, positions, dirr, datasetOut, positionsOut, paramOut, N, L, w);
			n++;
		}
	}
}
