import static org.junit.Assert.assertEquals;

import controller.Invisible;
import model.MultiLayerImageProcessingModel;
import model.SimpleMultiLayerImageProcessingModel;
import org.junit.Test;

/**
 * Tests for the invisible command.
 */
public class InvisibleTest {

  // tests go
  @Test
  public void testGo() {
    MultiLayerImageProcessingModel model = new SimpleMultiLayerImageProcessingModel();
    model.createLayer("First");
    new Invisible("First").run(model);
    assertEquals(model.getLayers().get(0).getVisibility(),false);
  }

}
