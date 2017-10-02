package list.mapid;

import java.util.*;

public class DataSet {
  private List<Data> elements = new ArrayList<>();
  private Map<String, List<Data>> elementById = new HashMap<>();

  private String idString = null;

  public DataSet(String idString) {
    this.idString = idString;
  }

  public void addElement(Data element) {
    if (getId(element.getId()) == null) {
      throw new InvalidArgumentException("Unknown id of element");
    }
    List<Data> list = elementById.get(getId(element.getId()));
    if (list == null) {
      list = new ArrayList<>();
      elementById.put(getId(element.getId()), list);
    }
    elements.add(element);
    list.add(element);
  }

  public String getId(String id) {
    if (idString == null) {
      return id;
    } else {
      int start = 0;
      int end = id.length();
      String tmp[] = idString.split(",");
      if (!tmp[0].isEmpty()) {
        start = Integer.valueOf(tmp[0]);
      }
      if (!tmp[1].isEmpty()) {
        end = Integer.valueOf(tmp[1]);
      }
      return id.substring(start, end);
    }
  }

  /**
   * @return the elements
   * @see #elements
   */
  public List<Data> getElements() {
    return elements;
  }


  public List<Data> getElementsById(String id) {
    return elementById.get(id);
  }

  public Collection<String> getIds() {
    return elementById.keySet();
  }
}
