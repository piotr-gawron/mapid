package list;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

	public DataSet readData(String filename) throws FileNotFoundException, IOException {
		DataSet result = new DataSet();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			// skip header
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] tmp = line.split("\t", -1);
				Data element = new Data();
				element.setId(tmp[0]);
				for (int i = 1; i < tmp.length; i++) {
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

	public void processCogIdMapping(DataSet dataSet, String filename, boolean trimAltId) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String[] tmp = line.split("\t", -1);
					String cogId = tmp[1];
					String altId = tmp[0];
					if (trimAltId) {
						altId=altId.substring(0, altId.length() - 1);
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
					dataSet.addCogData(cogId,cat);
				}
			}
		}
	}
}
