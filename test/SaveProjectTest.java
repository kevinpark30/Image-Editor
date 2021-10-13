import static org.junit.Assert.assertEquals;

import controller.LoadProject;
import controller.SaveProject;
import java.util.List;
import model.Layer;
import model.MultiLayerImageProcessingModel;
import model.SimpleMultiLayerImageProcessingModel;
import org.junit.Test;

/**
 * Test class for the saveProject command.
 */
public class SaveProjectTest {

  // test go
  @Test
  public void testGo() {
    MultiLayerImageProcessingModel model = new SimpleMultiLayerImageProcessingModel();
    new LoadProject("res/saveProjectTest").run(model);
    new SaveProject("saveProjectTest").run(model);
    new LoadProject("res/saveProjectTest").run(model);
    List<Layer> layers = model.getLayers();
    assertEquals(layers.get(2).getName(),"Third");

  }

}
