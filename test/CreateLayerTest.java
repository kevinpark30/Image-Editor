import static org.junit.Assert.assertEquals;


import controller.CreateLayer;
import java.util.List;
import model.Layer;
import model.MultiLayerImageProcessingModel;
import model.SimpleMultiLayerImageProcessingModel;
import org.junit.Test;

/**
 * Test class for the CreateLayer command.
 */
public class CreateLayerTest {

  // tests go
  @Test
  public void testGo() {
    MultiLayerImageProcessingModel m = new SimpleMultiLayerImageProcessingModel();
    new CreateLayer("first").run(m);
    List<Layer> layers = m.getLayers();
    assertEquals(layers.get(0).getName(),"first");

  }
}
