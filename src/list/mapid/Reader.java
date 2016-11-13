package list.mapid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

	public DataSet readData(String filename, boolean replaceCommaWithDot) throws FileNotFoundException, IOException {
		DataSet result = new DataSet();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			// skip header
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] tmp = line.split("\t", -1);
				Data element = new Data();
				element.setId(tmp[0]);
				for (int i = 1; i < tmp.length; i++) {
					if (replaceCommaWithDot) {
						tmp[i] = tmp[i].replace(",", ".");
					}
					element.getOther().add(tmp[i]);
				}
				result.addElement(element);
			}
		}

		return result;
	}

	public void processIdMapping(DataSet dataSet, String filename) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String[] tmp = line.split("\t", -1);
					dataSet.addAltMapping(tmp[0], tmp[1]);
				}
			}
		}
	}

	public void processCogIdMapping(DataSet dataSet, String filename, Integer altIdLengthToTrim) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String[] tmp = line.split("\t", -1);
					String cogId = tmp[1];
					String altId = tmp[0];
					if (altIdLengthToTrim != null) {
						altId = altId.substring(0, altIdLengthToTrim);
					}
					dataSet.addCogMapping(altId, cogId);
				}
			}
		}
	}

	public void addCogData(DataSet dataSet, String filename) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String[] tmp = line.split("\t", -1);
					String cogId = tmp[0];
					String cat = tmp[1];
					dataSet.addCogData(cogId, cat);
				}
			}
		}
	}

	public void addPhylogenyData(DataSet dataSet, String filename, int col, Integer altIdLengthToTrim) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String[] tmp = line.split("\t", -1);
					String altGaId = tmp[0];
					String phylogeny = tmp[col].split(";")[0];
					if (altIdLengthToTrim != null) {
						altGaId = altGaId.substring(0, altIdLengthToTrim);
					}
					dataSet.addPhylogeny(altGaId, phylogeny);
				}
			}
		}
	}

	public void addFamily(DataSet dataSet, String filename) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String[] tmp = line.split("\t", -1);
					String id = tmp[1];
					String family = tmp[0];
					dataSet.addFamily(id, family);
				}
			}
		}
		
	}
}
