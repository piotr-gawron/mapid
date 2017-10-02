package list.merger;

import list.mapid.DataSet;
import list.mapid.Merger;
import list.mapid.Reader;
import list.mapid.Writer;
import org.apache.commons.cli.*;

public class Main {

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

      String input = commandLine.getOptionValue("file");
      Integer column = Integer.parseInt(commandLine.getOptionValue("column"));
      String output = commandLine.getOptionValue("output");

      DataSet dataSet = reader.readData(input, column, commandLine.getOptionValue("identifier-substring"));

      Merger merger = new Merger();
      String separator = commandLine.getOptionValue("separator");
      if (separator == null) {
        separator = "_";
      }
      DataSet afterMerge = merger.mergeDuplicates(dataSet, separator, commandLine.hasOption("add-columns"));

      writer.writeToFile(afterMerge, output);
      return afterMerge;
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

    options.addOption(null, "identifier-substring", true, "definition of identifier substring used to merge second file in the format: begin,end. " +
            "For example if our identifier look like 'abc123.12', " +
            "but we want to use only '123.1' part we should use '3,8'. " +
            "If no value is provided then the whole string will be used as identifier.");
    options.addOption("s", "separator", true, "what kind od separator should be used for merging (default '_')");
    options.addOption("t", "add-columns", false, "instead of putting data into the same columns merging will create additional columns");
    options.addRequiredOption("f", "file", true, "file to be processed");
    options.addRequiredOption("o", "output", true, "output file");
    options.addRequiredOption("c", "column", true, "which column should be used as identifier  (starting from 0)");

    return options;
  }

}
