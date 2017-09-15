package list.mapid;

import org.apache.commons.cli.*;

public class Main {

  private static final String WORK_DIRECTORY = "C:/Users/piotr.gawron/Desktop/tmp/martyna/piotrek_to_merge/";

  private static final String IDS_MAPPING_FILE = "ids.txt";
  private static final String COG_MAPPING_FILE = "cog_mapping.txt";
  private static final String COG_DATA_FILE = "cog.txt";
  private static final String DATA_FILE = "data.txt";
  private static final String PHYLOGENY_FILE = "phylogeny.txt";
  private static final String FAMILY_FILE = "ass_fin_dbCAN.txt";

  private static final String OUTPUT_FILE = "out.txt";
  private final Reader reader = new Reader();
  private final Writer writer = new Writer();

  public static void main(String[] args) {
    try {
      new Main().run(args);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public DataSet run(String[] args) throws Exception {
    try {
      CommandLine commandLine = parse(args);

      String filename1 = commandLine.getOptionValue("file1");
      String filename2 = commandLine.getOptionValue("file2");
      String filename3 = commandLine.getOptionValue("output");

      Integer column1 = Integer.parseInt(commandLine.getOptionValue("column1"));
      Integer column2 = Integer.parseInt(commandLine.getOptionValue("column2"));

      DataSet dataSet1 = reader.readData(filename1, column1, commandLine.getOptionValue("identifier-substring1"));
      DataSet dataSet2 = reader.readData(filename2, column2, commandLine.getOptionValue("identifier-substring2"));

      Merger merger = new Merger();
      DataSet output = merger.merge(dataSet1, dataSet2, commandLine.hasOption("add-missing"), commandLine.hasOption("merge-duplicates"), commandLine.hasOption("add-duplicates-column"), "_");

      writer.writeToFile(output, filename3);
      return output;
    } catch (ParseException e) {
      printHelp();
      return null;
    }
  }

  private void printHelp() {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("program", getOptions());
  }

  private CommandLine parse(String[] args) throws ParseException {
    CommandLineParser result = new DefaultParser();
    Options options = getOptions();


    return result.parse(options, args);
  }

  private Options getOptions() {
    Options options = new Options();
    options.addRequiredOption(null, "file1", true, "first file to merge");
    options.addRequiredOption(null, "file2", true, "second file to merge");
    options.addRequiredOption(null, "output", true, "output file");
    options.addRequiredOption(null, "column1", true, "column with id in the first file (starting from 0)");
    options.addRequiredOption(null, "column2", true, "column with id in the second file (starting from 0)");
    options.addOption(null, "identifier-substring1", true, "definition of identifier substring used to merge first file in the format: begin,end. " +
            "For example if our identifier look like 'abc123.12', " +
            "but we want to use only '123.1' part we should use '3,8'. " +
            "If no value is provided then the whole string will be used as identifier..");
    options.addOption(null, "identifier-substring2", true, "definition of identifier substring used to merge second file in the format: begin,end. " +
            "For example if our identifier look like 'abc123.12', " +
            "but we want to use only '123.1' part we should use '3,8'. " +
            "If no value is provided then the whole string will be used as identifier..");

    options.addOption("a", "add-missing", false, "when rows in second file doesn't match the first add them to the output");
    options.addOption("m", "merge-duplicates", false, "when rows in second file contain duplicates merge data in the rows");
    options.addOption("d", "add-duplicates-column", false, "when rows in second file contain duplicates attach duplicates columns to the row");

    return options;
  }

}
