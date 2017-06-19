package list.fastq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FastqNameChange {

	public static void main(String[] args) {
		try {
			new FastqNameChange().run(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void run(String[] args) throws IOException {
		if (args.length < 3) {
			System.out.println("Usage: program inputDirectory outputDirectory partsForId");
			return;
		}
		String prefix = "";
		if (args.length >= 4) {
			prefix = args[3];
		}
		File input = new File(args[0]);
		String outputDir = args[1];
		new File(outputDir).mkdirs();
		int partsForId = Integer.valueOf(args[2]);
		for (final File fileEntry : input.listFiles()) {
			String filename = fileEntry.getName();
			if (filename.endsWith("fastq")) {
				System.out.println("Processing file: " + filename);
				processFile(fileEntry, outputDir, partsForId, prefix);
			} else {
				System.out.println("Skipping file: " + filename);
			}
		}
	}

	private void processFile(File fileEntry, String outputDir, int partsForId, String prefix) throws IOException {
		if (prefix == null) {
			prefix = "";
		}
		String id = prefix + getId(fileEntry.getName(), partsForId);
		BufferedReader br = new BufferedReader(new FileReader(fileEntry));
		PrintWriter writer = new PrintWriter(outputDir + "/" + fileEntry.getName(), "UTF-8");
		try {
			String line = br.readLine();
			int lineId = 0;

			while (line != null) {
				String result = line;
				if (lineId % 4 == 0) {
					String[] tmp = line.split(" ");
					String[] tmp1 = tmp[1].split(":");
					result = tmp[0] + " " + tmp1[0] + ":" + tmp1[1] + ":" + tmp1[2] + ":" + id;
				}
				writer.println(result);
				line = br.readLine();
				lineId++;
			}
		} finally {
			br.close();
			writer.close();
		}
	}

	private String getId(String name, int partsForId) {
		String tmp[] = name.split("_");

		String result = "";
		for (int i = 0; i < Math.min(tmp.length, partsForId); i++) {
			if (i == 0) {
				result = tmp[i];
			} else {
				result += "_" + tmp[i];
			}
		}
		return result;
	}

}
