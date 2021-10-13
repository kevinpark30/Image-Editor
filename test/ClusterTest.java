import static org.junit.Assert.assertEquals;

import image.Image;
import java.util.List;
import model.Cluster;
import model.EnhancedMultiLayerImageProcessingModel;
import model.Posn;
import model.SimpleEnhancedMultiLayerImageProcessingModel;
import org.junit.Test;

/**
 * Testing class for the Cluster class.
 */
public class ClusterTest {

  //tests that the addPosn method correctly adds a Posn
  @Test
  public void testAddPosn() {
    Cluster cluster = new Cluster();
    cluster.addPosn(new Posn(3, 4));

    assertEquals(1, cluster.getPositions().size());
  }

  //tests that averageRed method works correctly
  @Test
  public void testAverageRed() {
    EnhancedMultiLayerImageProcessingModel m = new SimpleEnhancedMultiLayerImageProcessingModel();
    m.createLayer("First");
    m.makeCheckerBoard(3);
    Image im = m.exportImage();
    Cluster cluster = new Cluster();
    cluster.addPosn(new Posn(1, 1));
    cluster.addPosn(new Posn(1, 2));
    assertEquals(128, cluster.averageRed(im));
  }

  //tests that averageGreen method works correctly
  @Test
  public void testAverageGreen() {
    EnhancedMultiLayerImageProcessingModel m = new SimpleEnhancedMultiLayerImageProcessingModel();
    m.createLayer("First");
    m.makeCheckerBoard(3);
    Image im = m.exportImage();
    Cluster cluster = new Cluster();
    cluster.addPosn(new Posn(1, 1));
    cluster.addPosn(new Posn(1, 2));
    assertEquals(128, cluster.averageGreen(im));
  }

  //tests that averageBlue method works correctly
  @Test
  public void testAverageBlue() {
    EnhancedMultiLayerImageProcessingModel m = new SimpleEnhancedMultiLayerImageProcessingModel();
    m.createLayer("First");
    m.makeCheckerBoard(3);
    Image im = m.exportImage();
    Cluster cluster = new Cluster();
    cluster.addPosn(new Posn(1, 1));
    cluster.addPosn(new Posn(1, 2));
    assertEquals(128, cluster.averageBlue(im));
  }

  //tests that the getPositions method works correctly
  @Test
  public void testGetPositions() {
    Cluster cluster = new Cluster();

    cluster.addPosn(new Posn(3, 4));
    cluster.addPosn(new Posn(4, 5));

    List<Posn> positions = cluster.getPositions();

    assertEquals(new Posn(3, 4), positions.get(0));
    assertEquals(new Posn(4, 5), positions.get(1));
  }
}