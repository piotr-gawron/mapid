package list.merger;

import list.mapid.Data;
import list.mapid.DataSet;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {
  @Test
  public void testRunHelp() throws Exception {
    Main main = new Main();
    main.run(new String[]{});
  }

  @Test
  public void testMergeCog() throws Exception {
    Main main = new Main();
    DataSet output = main.run(new String[]{
            "--file", "testFiles/merge/cog.txt",
            "--output", "out.txt",
            "--column", "0"});
    assertNotNull(output);
    assertEquals(1, output.getElementsById("Ga0213501_10000661").size());

    Data element = output.getElementsById("Ga0213501_10000661").get(0);
    assertEquals(9, element.getOther().size());
  }

  @Test
  public void testMergeCogWithAddingColumns() throws Exception {
    Main main = new Main();
    DataSet output = main.run(new String[]{
            "--file", "testFiles/merge/cog.txt",
            "--output", "out.txt",
            "--column", "0",
            "-t"});
    assertNotNull(output);
    assertEquals(1, output.getElementsById("Ga0213501_10000661").size());

    Data element = output.getElementsById("Ga0213501_10000661").get(0);
    assertEquals(36, element.getOther().size());
  }
}