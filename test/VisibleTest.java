import static org.junit.Assert.assertEquals;

import controller.Visible;
import model.MultiLayerImageProcessingModel;
import model.SimpleMultiLayerImageProcessingModel;
import org.junit.Test;

/**
 * Test class for the visible command.
 */
public class VisibleTest {

  // tests go
  @Test
  public void testGo() {
    MultiLayerImageProcessingModel model = new SimpleMultiLayerImageProcessingModel();
    model.createLayer("First");
    new Visible("First").run(model);
    assertEquals(model.getLayers().get(0).getVisibility(),true);
  }
}
