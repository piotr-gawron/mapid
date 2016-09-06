package list;

import java.io.IOException;
import java.io.PrintWriter;

public class Writer {

	public void writeToFile(DataSet dataSet, String filename) throws IOException {
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		for (Data data : dataSet.getElements()) {
			if (data.getCog().size() == 0) {
				writer.write(writeElement(data));
			} else {
				for (CogData cog: data.getCog()) {
					writer.write(writeElement(data, cog.getCogId(), cog.getCogLetter()));
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
		for (int i = 0; i < data.getOther().size(); i++) {
			result.append(data.getOther().get(i) + "\t");
		}
		result.append(cogId + "\t");
		result.append(cogCat + "\t");
		return result.toString() + "\n";
	}
}
