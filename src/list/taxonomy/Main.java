package list.taxonomy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

	private static final String		TAXONOMY_FILE		 = "taxonomy.txt";
	private static final String		IDS_MAPPING_FILE = "ids.txt";

	// private static final String WORK_DIRECTORY =
	// "C:/Users/piotr.gawron/Desktop/tmp/martyna/2017-06-01/deplecja/";
	private static final String		WORK_DIRECTORY	 = "C:/Users/piotr.gawron/Desktop/tmp/martyna/2017-06-01/dissectionWGLF/";
	private static final String[]	FILES_TO_ANALYZE = new String[] { "input.txt" };

	public static void main(String[] args) {
		try {
			new Main().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() throws Exception {
		Map<String, Set<String>> gaIdToTaxonomy = getGaIdTaxonomyMapping(WORK_DIRECTORY + TAXONOMY_FILE, 17, true);
		Map<String, String> idMapping = gaIdsToIds(WORK_DIRECTORY + IDS_MAPPING_FILE);
		for (String inputFile : FILES_TO_ANALYZE) {
			merge(gaIdToTaxonomy, idMapping, WORK_DIRECTORY + inputFile, WORK_DIRECTORY + inputFile + ".taxonomy.txt");
		}
	}

	private void merge(Map<String, Set<String>> gaIdToTaxonomy, Map<String, String> idMapping, String filename, String outputFilename) throws IOException {
		PrintWriter writer = new PrintWriter(outputFilename, "UTF-8");

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String[] tmp = line.split("\t", -1);
					Set<String> taxonomy = null;
					String id = idMapping.get(tmp[0]);
					if (id != null) {
						taxonomy = gaIdToTaxonomy.get(id);
					}
					if (taxonomy == null) {
						taxonomy = new HashSet<>();
						taxonomy.add(null);
					}
					for (String taxonomyEntry : taxonomy) {
						writer.write(line + "\t" + taxonomyEntry + "\n");
					}
				}
			}
		}

		writer.close();

	}

	private Map<String, String> gaIdsToIds(String filename) throws IOException {
		Map<String, String> mapping = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String[] tmp = line.split("\t", -1);
					mapping.put(tmp[0], tmp[1]);
				}
			}
		}
		return mapping;
	}

	private Map<String, Set<String>> getGaIdTaxonomyMapping(String filename, Integer altIdLengthToTrim, boolean full) throws IOException {
		Map<String, Set<String>> mapping = new HashMap<>();
		int i = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String[] tmp = line.split("\t", -1);
					String altId = tmp[0];
					if (altIdLengthToTrim != null) {
						altId = altId.substring(0, altIdLengthToTrim);
					}
					String taxonomy;
					taxonomy = tmp[4].split(";", -1)[0].replace("\"", "");
					if (taxonomy.equals("Eukaryota")) {
						if (full) {
							taxonomy = tmp[4];
						} else {

							String other = tmp[4].split(";", -1)[2].replace("\"", "");
							if (!other.equals("Insecta")) {
								other = "other";
							}
							taxonomy += "_" + other;
						}
					}
					if (mapping.get(altId) == null) {
						mapping.put(altId, new HashSet<>());
					}
					mapping.get(altId).add(taxonomy);
				}
			}
		}
		return mapping;
	}

}
