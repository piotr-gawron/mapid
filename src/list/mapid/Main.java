package list.mapid;

public class Main {

	private static final String	WORK_DIRECTORY	 = "C:/Users/piotr.gawron/Desktop/tmp/martyna/piotrek_to_merge/";

	private static final String	IDS_MAPPING_FILE = "ids.txt";
	private static final String	COG_MAPPING_FILE = "cog_mapping.txt";
	private static final String	COG_DATA_FILE		 = "cog.txt";
	private static final String	DATA_FILE				 = "data.txt";
	private static final String	PHYLOGENY_FILE	 = "phylogeny.txt";
	private static final String	FAMILY_FILE			 = "ass_fin_dbCAN.txt";

	private static final String	OUTPUT_FILE			 = "out.txt";
	private final Reader				reader					 = new Reader();
	private final Writer				writer					 = new Writer();

	public static void main(String[] args) {
		try {
			new Main().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() throws Exception {
		String directory = WORK_DIRECTORY;
		DataSet dataSet = reader.readData(directory + DATA_FILE, true);

		reader.processIdMapping(dataSet, directory + IDS_MAPPING_FILE);

		int altIdLength = dataSet.getElements().get(0).getAltGaId().length();
		reader.processCogIdMapping(dataSet, directory + COG_MAPPING_FILE, altIdLength);
		reader.addCogData(dataSet, directory + COG_DATA_FILE);

		reader.addPhylogenyData(dataSet, directory + PHYLOGENY_FILE, 4, altIdLength);
		reader.addFamily(dataSet, directory + FAMILY_FILE);

		writer.writeToFile(dataSet, directory + OUTPUT_FILE, new String[] { "Bacteria", "Archaea" });
	}

}
