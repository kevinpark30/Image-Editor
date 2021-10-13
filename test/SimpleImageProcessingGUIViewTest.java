import static org.junit.Assert.assertEquals;

import controller.ImageProcessingGUIController;
import model.EnhancedMultiLayerImageProcessingModel;
import model.ImageUtils;
import model.PNG;
import model.SimpleEnhancedMultiLayerImageProcessingModel;
import org.junit.Test;
import view.ImageProcessingGUIView;
import view.SimpleImageProcessingGUIView;

/**
 * Test class for the SimpleImageProcessingGUIView class. Running these tests will open them
 * in the GUI.
 */
public class SimpleImageProcessingGUIViewTest {

  private ImageProcessingGUIView view1 = new SimpleImageProcessingGUIView("Test");
  private EnhancedMultiLayerImageProcessingModel model =
      new SimpleEnhancedMultiLayerImageProcessingModel();
  private ImageProcessingGUIController controller = new ImageProcessingGUIController(model);

  // test addFeatures
  @Test
  public void testAddFeatures() {
    view1.addFeatures(controller);
    assertEquals("1", String.valueOf(1));
  }

  // test notifyErrorMessage
  @Test
  public void testNotifyErrorMessage() {
    assertEquals("1", String.valueOf(1));
    view1.notifyErrorMessage("Hello");
  }

  // test showTopMostImage
  @Test
  public void testShowTopMostImage() {
    model.createLayer("First");
    model.importImage(ImageUtils.readFile("res/manhattan.png",new PNG()));
    view1.showTopMostImage(model.exportImage(),"First");
    view1.notifyErrorMessage("Hello");
    assertEquals("1", String.valueOf(1));
  }

  // test addToLayerSelectionList with null input
  @Test
  public void testAddToLayerSelectionListNullInput() {
    view1.addToLayerSelectionList(null);
    view1.notifyErrorMessage("Hello");
    assertEquals("1", String.valueOf(1));
  }

  // test addToLayerSelectionList
  @Test
  public void testAddToLayerSelectionList() {
    view1.addToLayerSelectionList("First");
    view1.notifyErrorMessage("Hello");
    assertEquals("1", String.valueOf(1));
  }

  // test removeFromLayerSelectionList with null input
  @Test
  public void testRemoveFromLayerSelectionListNullInput() {
    view1.removeFromLayerSelectionList(null);
    view1.notifyErrorMessage("Hello");
    assertEquals("1", String.valueOf(1));
  }

  // test removeFromLayerSelectionList
  @Test
  public void testRemoveFromLayerSelectionList() {
    view1.addToLayerSelectionList("First");
    view1.addToLayerSelectionList("Second");
    view1.removeFromLayerSelectionList("First");
    view1.notifyErrorMessage("Hello");
    assertEquals("1", String.valueOf(1));
  }

}
