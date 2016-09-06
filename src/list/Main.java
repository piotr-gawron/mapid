package list;

public class Main {

	private static final String	IDS_MAPPING_FILE = "C:/Users/piotr.gawron/Desktop/tmp/martyna/all/ids.txt";
	private static final String	COG_MAPPING_FILE = "C:/Users/piotr.gawron/Desktop/tmp/martyna/all/cog_mapping.txt";
	private static final String	COG_DATA_FILE		 = "C:/Users/piotr.gawron/Desktop/tmp/martyna/all/cog.txt";
	private static final String	DATA_FILE				 = "C:/Users/piotr.gawron/Desktop/tmp/martyna/all/data.txt";

	private static final String	OUTPUT_FILE			 = "C:/Users/piotr.gawron/Desktop/tmp/martyna/all/out.txt";
	Reader											reader					 = new Reader();
	Writer											writer					 = new Writer();

	public static void main(String[] args) {
		try {
			new Main().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() throws Exception {
		DataSet dataSet = reader.readData(DATA_FILE);

		reader.processIdMapping(dataSet, IDS_MAPPING_FILE);

		reader.processCogIdMapping(dataSet, COG_MAPPING_FILE, true);
		reader.addCogData(dataSet, COG_DATA_FILE);

		System.out.println(dataSet.getElements().size());

		writer.writeToFile(dataSet, OUTPUT_FILE);
	}

}
