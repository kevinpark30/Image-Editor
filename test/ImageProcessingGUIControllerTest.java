import static org.junit.Assert.assertEquals;

import controller.ImageProcessingGUIController;
import model.EnhancedMultiLayerImageProcessingModel;
import model.SimpleEnhancedMultiLayerImageProcessingModel;
import org.junit.Test;
import view.ImageProcessingGUIViewTEST;

/**
 * Testing class for the ImageProcessingGUIController class.
 */
public class ImageProcessingGUIControllerTest {

  private final EnhancedMultiLayerImageProcessingModel model =
      new SimpleEnhancedMultiLayerImageProcessingModel();
  private final ImageProcessingGUIController controller =
      new ImageProcessingGUIController(model);
  private final ImageProcessingGUIViewTEST view = new
      ImageProcessingGUIViewTEST(new StringBuilder());

  // test constructor with null model
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    ImageProcessingGUIController controller = new ImageProcessingGUIController(null);

  }

  // test createLayer with caught argument exception
  @Test
  public void testCreateLayerIllegalArgument() {
    controller.setView(view);
    controller.createLayer(null);
    assertEquals("Add Features\n"
        + "Notify Error Message: You must give a name.\n",view.getAp().toString());
  }

  // test createLayer with caught state exception
  @Test
  public void testCreateLayerIllegalState() {
    controller.setView(view);
    controller.createLayer("first");
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n",view.getAp().toString());
  }

  // test createLayer
  @Test
  public void testCreateLayer() {
    controller.setView(view);
    controller.createLayer("first");
    controller.loadImage("checkerboardBLUR.ppm");
    controller.createLayer("second");
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n"
        + "Show Top Most Image: first\n"
        + "Add to Layer SelectionList: second\n"
        + "Show Top Most Image: first\n",view.getAp().toString());
  }

  // test deleteLayer with caught argument exception
  @Test
  public void testDeleteLayerIllegalArgument() {
    controller.setView(view);
    controller.deleteLayer(null);
    assertEquals("Add Features\n"
        + "Notify Error Message: You must give a name.\n",view.getAp().toString());
  }

  // test deleteLayer with caught state exception
  @Test
  public void testDeleteLayerIllegalState() {
    controller.setView(view);
    controller.createLayer("first");
    controller.deleteLayer("first");
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n"
        + "Remove from Layer SelectionList: first\n"
        + "Show Top Most Image: \n",view.getAp().toString());
  }

  // test deleteLayer
  @Test
  public void testDeleteLayer() {
    controller.setView(view);
    controller.createLayer("first");
    controller.loadImage("checkerboardBLUR.ppm");
    controller.createLayer("second");
    controller.deleteLayer("second");
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n"
        + "Show Top Most Image: first\n"
        + "Add to Layer SelectionList: second\n"
        + "Show Top Most Image: first\n"
        + "Remove from Layer SelectionList: second\n"
        + "Show Top Most Image: first\n",view.getAp().toString());
  }

  // test setCurrentLayer with caught argument exception
  @Test
  public void testSetCurrentLayerIllegalArgument() {
    controller.setView(view);
    controller.setCurrentLayer(null);
    assertEquals("Add Features\n"
        + "Notify Error Message: You must give a name.\n",view.getAp().toString());
  }

  // test setCurrentLayer
  @Test
  public void testSetCurrentLayer() {
    controller.setView(view);
    controller.createLayer("first");
    controller.loadImage("checkerboardBLUR.ppm");
    controller.createLayer("second");
    controller.setCurrentLayer("first");
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n"
        + "Show Top Most Image: first\n"
        + "Add to Layer SelectionList: second\n"
        + "Show Top Most Image: first\n",view.getAp().toString());
  }


  // test setCurrentLayerToInvisible with caught argument exception
  @Test
  public void testSetCurrentLayerToInvisibleIllegalArgument() {
    controller.setView(view);
    controller.setCurrentLayerToInvisible(null);
    assertEquals("Add Features\n"
        + "Notify Error Message: You must give a name.\n",view.getAp().toString());
  }

  // test setCurrentLayerToInvisible with caught state exception
  @Test
  public void testSetCurrentLayerToInvisibleIllegalState() {
    controller.setView(view);
    controller.createLayer("first");
    controller.createLayer("second");
    controller.setCurrentLayerToInvisible("second");
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n"
        + "Add to Layer SelectionList: second\n"
        + "Show Top Most Image: \n"
        + "Show Top Most Image: \n",view.getAp().toString());
  }

  // test setCurrentLayerToInvisible
  @Test
  public void testSetCurrentLayerToInvisible() {
    controller.setView(view);
    controller.createLayer("first");
    controller.loadImage("checkerboardBLUR.ppm");
    controller.createLayer("second");
    controller.setCurrentLayerToInvisible("second");
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n"
        + "Show Top Most Image: first\n"
        + "Add to Layer SelectionList: second\n"
        + "Show Top Most Image: first\n"
        + "Show Top Most Image: first\n",view.getAp().toString());
  }


  // test setCurrentLayerToVisible with caught argument exception
  @Test
  public void testSetCurrentLayerToVisibleIllegalArgument() {
    controller.setView(view);
    controller.setCurrentLayerToVisible(null);
    assertEquals("Add Features\n"
        + "Notify Error Message: You must give a name.\n",view.getAp().toString());
  }

  // test setCurrentLayerToVisible with caught state exception
  @Test
  public void testSetCurrentLayerToVisibleIllegalState() {
    controller.setView(view);
    controller.createLayer("first");
    controller.createLayer("second");
    controller.setCurrentLayerToVisible("second");
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n"
        + "Add to Layer SelectionList: second\n"
        + "Show Top Most Image: \n"
        + "Show Top Most Image: \n",view.getAp().toString());
  }

  // test setCurrentLayerToVisible
  @Test
  public void testSetCurrentLayerToVisible() {
    controller.setView(view);
    controller.createLayer("first");
    controller.loadImage("checkerboardBLUR.ppm");
    controller.createLayer("second");
    controller.setCurrentLayerToVisible("second");
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n"
        + "Show Top Most Image: first\n"
        + "Add to Layer SelectionList: second\n"
        + "Show Top Most Image: first\n"
        + "Show Top Most Image: first\n",view.getAp().toString());
  }

  // test applyBlurToCurrentLayer with caught state exception
  @Test
  public void testApplyBlurToCurrentLayerIllegalArgument() {
    controller.setView(view);
    controller.applyBlurToCurrentLayer();
    assertEquals("Add Features\n"
        + "Notify Error Message: There is no image to apply the filter on.\n",
        view.getAp().toString());
  }


  // test applyBlurToCurrentLayer
  @Test
  public void testApplyBlurToCurrentLayer() {
    controller.setView(view);
    controller.createLayer("first");
    controller.loadImage("checkerboardBLUR.ppm");
    controller.applyBlurToCurrentLayer();
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n"
        + "Show Top Most Image: first\n"
        + "Show Top Most Image: first\n",view.getAp().toString());
  }

  // test applySharpenToCurrentLayer with caught state exception
  @Test
  public void testApplySharpenToCurrentLayerIllegalArgument() {
    controller.setView(view);
    controller.applySharpenToCurrentLayer();
    assertEquals("Add Features\n"
        + "Notify Error Message: There is no image to apply the filter on.\n",
        view.getAp().toString());
  }


  // test applySharpenToCurrentLayer
  @Test
  public void testApplySharpenToCurrentLayer() {
    controller.setView(view);
    controller.createLayer("first");
    controller.loadImage("checkerboardBLUR.ppm");
    controller.applySharpenToCurrentLayer();
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n"
        + "Show Top Most Image: first\n"
        + "Show Top Most Image: first\n",view.getAp().toString());
  }


  // test applySepiaToCurrentLayer with caught state exception
  @Test
  public void testApplySepiaToCurrentLayerIllegalArgument() {
    controller.setView(view);
    controller.applySepiaToCurrentLayer();
    assertEquals("Add Features\n"
        + "Notify Error Message: There is no image to apply the color transformation on.\n",
        view.getAp().toString());
  }

  // test applySepiaToCurrentLayer
  @Test
  public void testApplySepiaToCurrentLayer() {
    controller.setView(view);
    controller.createLayer("first");
    controller.loadImage("checkerboardBLUR.ppm");
    controller.applySepiaToCurrentLayer();
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n"
        + "Show Top Most Image: first\n"
        + "Show Top Most Image: first\n",view.getAp().toString());
  }


  // test applyGreyscaleToCurrentLayer with caught state exception
  @Test
  public void testApplyGreyscaleToCurrentLayerIllegalArgument() {
    controller.setView(view);
    controller.applyGreyscaleToCurrentLayer();
    assertEquals("Add Features\n"
        + "Notify Error Message: There is no image to apply the color transformation on.\n",
        view.getAp().toString());
  }

  // test applyGreyscaleToCurrentLayer
  @Test
  public void testApplyGreyscaleToCurrentLayer() {
    controller.setView(view);
    controller.createLayer("first");
    controller.loadImage("checkerboardBLUR.ppm");
    controller.applyGreyscaleToCurrentLayer();
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n"
        + "Show Top Most Image: first\n"
        + "Show Top Most Image: first\n",view.getAp().toString());
  }

  // test applyDownscaleToCurrentLayer with invalid string values (not integers)
  @Test
  public void testApplyDownscaleAllLayersInvalidValue() {
    controller.setView(view);
    controller.applyDownscaleToAllLayers("hello","hi");
    assertEquals("Add Features\n"
            + "Notify Error Message: Width or Height must be a number.\n",
        view.getAp().toString());
  }


  // test applyDownscaleToCurrentLayer with caught state exception
  @Test
  public void testApplyDownscaleAllLayersIllegalState() {
    controller.setView(view);
    controller.createLayer("first");
    controller.applyDownscaleToAllLayers("1","1");
    assertEquals("Add Features\n"
            + "Add to Layer SelectionList: first\n"
            + "Show Top Most Image: \n"
            + "Notify Error Message: There is no image to downscale yet.\n",
        view.getAp().toString());
  }

  // test applyDownscaleToCurrentLayer with caught argument exception
  @Test
  public void testApplyDownscaleAllLayersIllegalArgument() {
    controller.setView(view);
    controller.applyDownscaleToAllLayers("10","10");
    assertEquals("Add Features\n"
            + "Notify Error Message: There is no image to downscale yet.\n",
        view.getAp().toString());
  }

  // test applyDownscaleToCurrentLayer
  @Test
  public void testApplyDownscaleToAllLayers() {
    controller.setView(view);
    controller.createLayer("first");
    controller.loadImage("checkerboardBLUR.ppm");
    controller.applyDownscaleToAllLayers("1","1");
    assertEquals("Add Features\n"
            + "Add to Layer SelectionList: first\n"
            + "Show Top Most Image: \n"
            + "Show Top Most Image: first\n"
            + "Show Top Most Image: first\n",
        view.getAp().toString());
  }

  // test applyMosaicToCurrentLayer with invalid string values (not integers)
  @Test
  public void testApplyMosaicToCurrentLayerInvalidValue() {
    controller.setView(view);
    controller.applyMosaicToCurrentLayer("hello");
    assertEquals("Add Features\n"
            + "Notify Error Message: Seeds must be a number.\n",
        view.getAp().toString());
  }

  // test applyMosaicToCurrentLayer with caught state exception
  @Test
  public void testApplyMosaicToCurrentLayerIllegalState() {
    controller.setView(view);
    controller.createLayer("first");
    controller.applyMosaicToCurrentLayer("3");
    assertEquals("Add Features\n"
            + "Add to Layer SelectionList: first\n"
            + "Show Top Most Image: \n"
            + "Notify Error Message: The number of seeds cannot be greater than the number "
            + "of pixels in the image.\n",
        view.getAp().toString());
  }

  // test applyMosaicToCurrentLayer with caught argument exception
  @Test
  public void testApplyMosaicToCurrentLayerIllegalArgument() {
    controller.setView(view);
    controller.applyMosaicToCurrentLayer("3");
    assertEquals("Add Features\n"
            + "Notify Error Message: There is no image to apply the mosaic on.\n",
        view.getAp().toString());
  }

  // test applyMosaicToCurrentLayer
  @Test
  public void testApplyMosaicToCurrentLayer() {
    controller.setView(view);
    controller.createLayer("first");
    controller.loadImage("checkerboardBLUR.ppm");
    controller.applyMosaicToCurrentLayer("9");
    assertEquals("Add Features\n"
            + "Add to Layer SelectionList: first\n"
            + "Show Top Most Image: \n"
            + "Show Top Most Image: first\n"
            + "Show Top Most Image: first\n",
        view.getAp().toString());
  }


  // test loadImage with caught state exception
  @Test
  public void testLoadImageIllegalState() {
    controller.setView(view);
    controller.loadImage("checkerboardBLUR.ppm");
    assertEquals("Add Features\n"
        + "Notify Error Message: There is no layer to load this image.\n",view.getAp().toString());
  }

  // test loadImage
  @Test
  public void testLoadImage() {
    controller.setView(view);
    controller.createLayer("First");
    controller.loadImage("checkerboardBLUR.ppm");
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: First\n"
        + "Show Top Most Image: \n"
        + "Show Top Most Image: First\n",view.getAp().toString());
  }

  // test loadProject with caught state exception
  @Test
  public void testLoadProjectIllegalState() {
    controller.setView(view);
    controller.loadProject("checkerboardBLUR.ppm");
    assertEquals("Add Features\n"
        + "Notify Error Message: Text file cannot be found.\n",view.getAp().toString());
  }

  // test loadProject
  @Test
  public void testLoadProject() {
    controller.setView(view);
    controller.createLayer("First");
    controller.loadProject("res/saveProjectTest");
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: First\n"
        + "Show Top Most Image: \n"
        + "Remove from Layer SelectionList: First\n"
        + "Notify Error Message: Visibility needs to be true or false.\n",view.getAp().toString());
  }

  // test save with caught state exception
  @Test
  public void testSaveIllegalState() {
    controller.setView(view);
    controller.save("checkerboardBLUR.ppm");
    assertEquals("Add Features\n"
        + "Notify Error Message: There are no layers.\n",view.getAp().toString());
  }

  // test save
  @Test
  public void testSave() {
    controller.setView(view);
    controller.createLayer("first");
    controller.loadImage("checkerboardBLUR.ppm");
    controller.save("checkerboardBLUR.ppm");
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n"
        + "Show Top Most Image: first\n"
        + "Show Top Most Image: first\n",view.getAp().toString());
  }


  // test saveProject with caught state exception
  @Test
  public void testSaveProjectIllegalState() {
    controller.setView(view);
    controller.saveProject("checkerboardBLUR.ppm");
    assertEquals("Add Features\n"
        + "Notify Error Message: File could not be found\n",view.getAp().toString());
  }

  // test saveProject
  @Test
  public void testSaveProject() {
    controller.setView(view);
    controller.createLayer("first");
    controller.loadImage("checkerboardBLUR.ppm");
    controller.saveProject("checkerboardBLUR");
    assertEquals("Add Features\n"
        + "Add to Layer SelectionList: first\n"
        + "Show Top Most Image: \n"
        + "Show Top Most Image: first\n",view.getAp().toString());
  }

  // test loadCheckerboardImage with invalid value (not integer)
  @Test
  public void testLoadCheckerboardImageInvalidValue() {
    controller.setView(view);
    controller.loadCheckboardImage("hello");
    assertEquals("Add Features\n"
            + "Notify Error Message: Side Length must be a number.\n",
        view.getAp().toString());
  }

  // test loadCheckerboardImage with caught argument exception
  @Test
  public void testLoadCheckerboardImageIllegalArgument() {
    controller.setView(view);
    controller.loadCheckboardImage("3");
    assertEquals("Add Features\n"
            + "Notify Error Message: There is no layer to load this image.\n",
        view.getAp().toString());
  }

  // test loadCheckerboardImage
  @Test
  public void testLoadCheckerboardImageIllegalState() {
    controller.setView(view);
    controller.createLayer("first");
    controller.loadCheckboardImage("3");
    assertEquals("Add Features\n"
            + "Add to Layer SelectionList: first\n"
            + "Show Top Most Image: \n"
            + "Show Top Most Image: first\n",
        view.getAp().toString());
  }


  // test loadScript with null filepath
  @Test
  public void testLoadScriptNullFilepath() {
    controller.setView(view);
    controller.loadScript(null);
    assertEquals("Add Features\n"
        + "Notify Error Message: Script must be given.\n",view.getAp().toString());
  }


  // test loadScript
  @Test
  public void testLoadScript() {
    controller.setView(view);
    controller.loadScript("res/script1.txt");
    assertEquals("Add Features\n",view.getAp().toString());
  }
}
