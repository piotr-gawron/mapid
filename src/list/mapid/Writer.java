package list.mapid;

import java.io.IOException;
import java.io.PrintWriter;

public class Writer {

	public void writeToFile(DataSet dataSet, String filename, String[] phylogeny) throws IOException {
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		for (Data data : dataSet.getElements()) {
			boolean toBePrinted = false;
			if (phylogeny != null) {
				for (String string : phylogeny) {
					if (data.getPhylogeny() == null || data.getPhylogeny().startsWith(string)) {
						toBePrinted = true;
					}
				}
			} else {
				toBePrinted = true;
			}
			if (toBePrinted) {
				if (data.getCog().size() == 0) {
					writer.write(writeElement(data));
				} else {
					for (CogData cog : data.getCog()) {
						String letters = cog.getCogLetter();
						if (letters.length() == 0) {
							writer.write(writeElement(data, cog.getCogId(), ""));
						} else {
							for (int i = 0; i < letters.length(); i++) {
								writer.write(writeElement(data, cog.getCogId(), letters.charAt(i) + ""));
							}
						}
					}
				}
			}
		}
		writer.close();
	}

	private String writeElement(Data data) {
		return writeElement(data, "", "");
	}

	private String writeElement(Data data, String cogId, String cogCat) {
		StringBuilder result = new StringBuilder();

		result.append(data.getId() + "\t");
		result.append(data.getAltGaId() + "\t");
		result.append(data.getPhylogeny() + "\t");
		result.append(data.getFamily() + "\t");
		for (int i = 0; i < data.getOther().size(); i++) {
			result.append(data.getOther().get(i) + "\t");
		}
		result.append(cogId + "\t");
		result.append(cogCat + "\t");
		return result.toString() + "\n";
	}
}
