package list.mapid;

import java.util.ArrayList;
import java.util.List;

public class Data {
  private String id;
  private List<String> other = new ArrayList<>();

  public Data() {

  }

  public Data(Data data) {
    setId(data.getId());
    for (String s : data.getOther()) {
      addCell(s);
    }
  }

  public void addCell(String s) {
    other.add(s);
  }

  /**
   * @return the id
   * @see #id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   * @see #id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * /**
   *
   * @return the other
   * @see #other
   */
  public List<String> getOther() {
    return other;
  }

  /**
   * @param other the other to set
   * @see #other
   */
  public void setOther(List<String> other) {
    this.other = other;
  }
}
