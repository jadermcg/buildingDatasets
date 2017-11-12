package execution;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import sources.Background;
import sources.DatasetGenerated;
import sources.FileGenerated;
import sources.JaspaReadFile;
import sources.PlantMotifs;

public class JasparGenerated {

	public static void main(String[] args) throws Exception {

		// **********************************************************
		// background probability
		// **********************************************************
		Map<String, Double> probs = new LinkedHashMap<>();
		probs.put("a", 0.25);
		probs.put("c", 0.25);
		probs.put("g", 0.25);
		probs.put("t", 0.25);
		Background bg = new Background(probs, 1);

		// **********************************************************
		// dataset and jaspar file name
		// **********************************************************
		String datasetName = "dataset_usp";
		String jasparFile = "MA0016.1.sites";
		String dir = System.getProperty("user.home") + "/datasets/final/syntetic/" + datasetName
				+ "/";

		// **********************************************************
		// get upper case motifs
		// **********************************************************
		List<String> motifs = new JaspaReadFile(dir + jasparFile).getMotifs();
		int w = motifs.get(0).length();

		// **********************************************************
		// get upper case motifs
		// **********************************************************
		int L = 500;
		DatasetGenerated dg = new DatasetGenerated(bg, motifs.size(), L);
		List<String> dataset = dg.getDataset();
		int N = dataset.size();

		// **********************************************************
		// plant motifs
		// **********************************************************
		boolean oops = true;
		PlantMotifs pm = new PlantMotifs(dataset, motifs, oops);
		dataset = pm.getDataset();
		List<Integer> positions = pm.getPositions();

		// **********************************************************
		// generated files
		// **********************************************************
		String datasetOut = dir + datasetName + ".fa";
		String positionsOut = dir + "positions.conf";
		String paramOut = dir + "param.conf";

		// generated dataset file
		FileGenerated.generated(dataset, positions, dir, datasetOut, positionsOut, paramOut, N, L,
				w);

	}
}
