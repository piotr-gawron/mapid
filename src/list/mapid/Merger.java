package list.mapid;

import java.util.List;

public class Merger {
  public DataSet merge(DataSet dataSet1, DataSet dataSet2, boolean addMissing, boolean mergeDuplicates, boolean attachDuplicates, String separator) {
    DataSet result = new DataSet(null);

    int columnsToAdd = dataSet2.getElements().iterator().next().getOther().size();

    for (Data data : dataSet1.getElements()) {
      List<Data> toMerge = dataSet2.getElementsById(dataSet1.getId(data.getId()));
      if (toMerge == null || toMerge.size() == 0) {
        Data row = new Data(data);
        for (int i = 0; i < columnsToAdd; i++) {
          row.addCell("");
        }
        result.addElement(row);
      } else {
        if (mergeDuplicates) {
          Data row = new Data(data);
          for (int i = 0; i < columnsToAdd; i++) {
            String value = "";
            for (Data dataToMerge : toMerge) {
              if (value.equals("")) {
                value = dataToMerge.getOther().get(i);
              } else {
                value += separator + dataToMerge.getOther().get(i);
              }
            }
            row.addCell(value);
          }
          result.addElement(row);
        } else if (attachDuplicates) {
          Data row = new Data(data);
          for (Data dataToMerge : toMerge) {
            for (int i = 0; i < columnsToAdd; i++) {
              row.addCell(dataToMerge.getOther().get(i));
            }
          }
          result.addElement(row);
        } else {
          for (Data dataToMerge : toMerge) {
            Data row = new Data(data);
            for (int i = 0; i < columnsToAdd; i++) {
              row.addCell(dataToMerge.getOther().get(i));
            }
            result.addElement(row);
          }
        }
      }
    }

    return result;
  }

  public DataSet mergeDuplicates(DataSet dataSet, String separator, boolean attachDuplicates) {

    DataSet result = new DataSet(null);

    int columnsToAdd = dataSet.getElements().iterator().next().getOther().size();

    for (String id : dataSet.getIds()) {
      List<Data> toMerge = dataSet.getElementsById(id);
      if (!attachDuplicates) {
        Data row = new Data();
        for (int i = 0; i < columnsToAdd; i++) {
          String value = "";
          for (Data dataToMerge : toMerge) {
            if (value.equals("")) {
              value = dataToMerge.getOther().get(i);
            } else {
              value += separator + dataToMerge.getOther().get(i);
            }
          }
          row.setId(toMerge.get(0).getId());
          row.addCell(value);
        }
        result.addElement(row);
      } else {
        Data row = new Data();
        for (Data dataToMerge : toMerge) {
          for (int i = 0; i < columnsToAdd; i++) {
            row.addCell(dataToMerge.getOther().get(i));
          }
        }
        row.setId(toMerge.get(0).getId());
        result.addElement(row);
      }
    }

    return result;
  }
}
