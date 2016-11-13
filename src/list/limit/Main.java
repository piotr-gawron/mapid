package list.limit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import list.mapid.InvalidArgumentException;

public class Main {

	private static final String		GA_IDS_FILE			 = "contigsF.txt";
	private static final String		IDS_MAPPING_FILE = "ids.txt";

	private static final String		WORK_DIRECTORY	 = "C:/Users/piotr.gawron/Desktop/tmp/martyna/stor25/";
	private static final String[]	FILES_TO_ANALYZE = new String[] { "out.txt", "data_contigs.txt", "data_dbcan.txt" };

	public static void main(String[] args) {
		try {
			new Main().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() throws Exception {
		Set<String> gaIds = getGaIds(WORK_DIRECTORY + GA_IDS_FILE, 17);
		System.out.println(gaIds.size());
		Set<String> ids = gaIdsToIds(gaIds, WORK_DIRECTORY + IDS_MAPPING_FILE);
		System.out.println(ids.size());
		for (String inputFile : FILES_TO_ANALYZE) {
			filter(ids, WORK_DIRECTORY + inputFile, WORK_DIRECTORY + inputFile + ".filtered.txt");
		}
	}

	private void filter(Set<String> ids, String filename, String outputFilename) throws IOException {
		PrintWriter writer = new PrintWriter(outputFilename, "UTF-8");

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String[] tmp = line.split("\t", -1);
					if (ids.contains(tmp[0])) {
						writer.write(line + "\n");
					}
				}
			}
		}

		writer.close();

	}

	private Set<String> gaIdsToIds(Set<String> gaIds, String filename) throws IOException {
		Map<String, String> mapping = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String[] tmp = line.split("\t", -1);
					mapping.put(tmp[1], tmp[0]);
				}
			}
		}

		Set<String> result = new HashSet<>();

		for (String string : gaIds) {
			if (mapping.get(string) == null) {
				throw new InvalidArgumentException("Cannot find mapping for id: " + string);
			}
			result.add(mapping.get(string));
		}

		return result;
	}

	private Set<String> getGaIds(String filename, Integer altIdLengthToTrim) throws IOException {
		Set<String> result = new HashSet<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String altId = line.trim();
					if (altIdLengthToTrim != null) {
						altId = altId.substring(0, altIdLengthToTrim);
					}
					result.add(altId);
				}
			}
		}
		return result;
	}

}
