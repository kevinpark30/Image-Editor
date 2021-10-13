import controller.MultiLayerImageProcessingController;
import controller.SimpleMultiLayerImageProcessingController;
import java.io.StringReader;
import model.MultiLayerImageProcessingModel;
import model.SimpleMultiLayerImageProcessingModel;
import org.junit.Test;

/**
 * Test class for SimpleMultiLayerImageProcessingController.
 */
public class SimpleMultiLayerImageProcessingControllerTest {

  MultiLayerImageProcessingModel model = new SimpleMultiLayerImageProcessingModel();

  // test constructor with null model
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    StringReader rd = new StringReader("q");
    StringBuilder sb = new StringBuilder();
    MultiLayerImageProcessingController controller =
        new SimpleMultiLayerImageProcessingController(null, rd, sb);
  }

  // test constructor with null appendable
  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    StringReader rd = new StringReader("q");
    StringBuilder sb = new StringBuilder();
    MultiLayerImageProcessingController controller =
        new SimpleMultiLayerImageProcessingController(model, rd, null);
  }

  // test constructor with null readable
  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    StringReader rd = new StringReader("q");
    StringBuilder sb = new StringBuilder();
    MultiLayerImageProcessingController controller =
        new SimpleMultiLayerImageProcessingController(model, null, sb);
  }

  // test when no more input to read from
  @Test(expected = IllegalStateException.class)
  public void testNoMoreInput() {
    StringReader rd = new StringReader("I");
    StringBuilder sb = new StringBuilder();
    MultiLayerImageProcessingController controller =
        new SimpleMultiLayerImageProcessingController(model, rd, sb);
    controller.run();
  }



}
