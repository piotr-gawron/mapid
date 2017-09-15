package list.mapid;

import java.io.IOException;
import java.io.PrintWriter;

public class Writer {

  public void writeToFile(DataSet dataSet, String filename) throws IOException {
    PrintWriter writer = new PrintWriter(filename, "UTF-8");
    for (Data data : dataSet.getElements()) {
      writer.write(writeElement(data));
    }
    writer.close();
  }

  private String writeElement(Data data) {
    StringBuilder result = new StringBuilder();

    result.append(data.getId());
    for (int i = 0; i < data.getOther().size(); i++) {
      result.append("\t" + data.getOther().get(i));
    }
    return result.toString() + "\n";
  }
}
