package list.mapid;

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
            "--file1", "testFiles/merge/cog.txt",
            "--file2", "testFiles/merge/cog_names.txt",
            "--output", "out.txt",
            "--column1", "1",
            "--column2", "0"});
    assertNotNull(output);
    Data element = output.getElements().get(0);
    assertTrue(element.getOther().size() > 9);
    assertFalse(element.getOther().get(9).isEmpty());
  }

  @Test
  public void testMergeDbCan() throws Exception {
    Main main = new Main();
    DataSet output = main.run(new String[]{
            "--file1", "testFiles/merge/cog.txt",
            "--file2", "testFiles/merge/dbCAN.txt",
            "--output", "out.txt",
            "--column1", "0",
            "--column2", "1"});
    assertNotNull(output);
    Data element = output.getElements().get(14);
    assertTrue(element.getOther().size() > 9);
    assertFalse(element.getOther().get(9).isEmpty());
  }

  @Test
  public void testMergeEc() throws Exception {
    Main main = new Main();
    DataSet output = main.run(new String[]{
            "--file1", "testFiles/merge/cog.txt",
            "--file2", "testFiles/merge/ec.txt",
            "--output", "out.txt",
            "--column1", "0",
            "--column2", "0"});
    assertNotNull(output);
    Data element = output.getElements().get(11);
    assertTrue(element.getOther().size() > 9);
    assertFalse(element.getOther().get(9).isEmpty());
  }

  @Test
  public void testMergeGeneProduct() throws Exception {
    Main main = new Main();
    DataSet output = main.run(new String[]{
            "--file1", "testFiles/merge/cog.txt",
            "--file2", "testFiles/merge/gene_product.txt",
            "--output", "out.txt",
            "--column1", "0",
            "--column2", "0"});
    assertNotNull(output);
    Data element = output.getElements().get(11);
    assertTrue(element.getOther().size() > 9);
    assertFalse(element.getOther().get(9).isEmpty());
  }

  @Test
  public void testMergeMap() throws Exception {
    Main main = new Main();
    DataSet output = main.run(new String[]{
            "--file1", "testFiles/merge/cog.txt",
            "--file2", "testFiles/merge/map.txt",
            "--output", "out.txt",
            "--column1", "0",
            "--identifier-substring1", "0,17",
            "--column2", "1"});
    assertNotNull(output);
    Data element = output.getElements().get(11);
    assertTrue(element.getOther().size() > 9);
    assertFalse(element.getOther().get(9).isEmpty());
  }

  @Test
  public void testMergePhylodist() throws Exception {
    Main main = new Main();
    DataSet output = main.run(new String[]{
            "--file1", "testFiles/merge/cog.txt",
            "--file2", "testFiles/merge/phylodist.txt",
            "--output", "out.txt",
            "--column1", "0",
            "--column2", "0"});
    assertNotNull(output);
    Data element = output.getElements().get(11);
    assertTrue(element.getOther().size() > 9);
    assertFalse(element.getOther().get(9).isEmpty());
  }

  @Test
  public void testMergeTpm() throws Exception {
    Main main = new Main();
    DataSet output = main.run(new String[]{
            "--file1", "testFiles/merge/map.txt",
            "--file2", "testFiles/merge/TPM.txt",
            "--output", "out.txt",
            "--column1", "0",
            "--column2", "0"});
    assertNotNull(output);
    Data element = output.getElements().get(11);
    assertTrue(element.getOther().size() > 2);
    assertFalse(element.getOther().get(2).isEmpty());
  }

  @Test
  public void testMergeDuplicates() throws Exception {
    Main main = new Main();
    DataSet output = main.run(new String[]{
            "--file1", "testFiles/merge/dupl1.txt",
            "--file2", "testFiles/merge/dupl2.txt",
            "--output", "out.txt",
            "--column1", "0",
            "--column2", "0"});
    assertNotNull(output);
    assertEquals(output.getElements().size(), 5);
  }

  @Test
  public void testMergeDuplicatesSingleCell() throws Exception {
    Main main = new Main();
    DataSet output = main.run(new String[]{
            "--file1", "testFiles/merge/dupl1.txt",
            "--file2", "testFiles/merge/dupl2.txt",
            "--output", "out.txt",
            "--column1", "0",
            "--column2", "0",
            "-m", "0",
    });
    assertNotNull(output);
    assertEquals(output.getElements().size(), 3);
    Data element = output.getElements().get(1);
    assertEquals(2, element.getOther().size());
  }

  @Test
  public void testMergeDuplicatesSingleRow() throws Exception {
    Main main = new Main();
    DataSet output = main.run(new String[]{
            "--file1", "testFiles/merge/dupl1.txt",
            "--file2", "testFiles/merge/dupl2.txt",
            "--output", "out.txt",
            "--column1", "0",
            "--column2", "0",
            "-d", "0",
    });
    assertNotNull(output);
    assertEquals(output.getElements().size(), 3);
    Data element = output.getElements().get(1);
    assertEquals(3, element.getOther().size());
  }
}