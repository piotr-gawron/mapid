package list.mapid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

  public DataSet readData(String filename, Integer idColumn, String idString) throws FileNotFoundException, IOException {
    DataSet result = new DataSet(idString);

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] tmp = line.split("\t", -1);
        Data element = new Data();
        element.setId(tmp[idColumn]);
        for (int i = 0; i < tmp.length; i++) {
          if (i != idColumn) {
            element.getOther().add(tmp[i]);
          }
        }
        result.addElement(element);
      }
    }

    return result;
  }

}
