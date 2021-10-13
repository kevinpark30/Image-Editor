import static org.junit.Assert.assertEquals;

import image.Image;
import image.Pixel;
import model.EnhancedMultiLayerImageProcessingModel;
import model.Posn;
import model.SimpleEnhancedMultiLayerImageProcessingModel;
import org.junit.Test;

/**
 * Testing class for the SimpleEnhancedMultiLayerImageProcessingModel class.
 */
public class SimpleEnhancedMultiLayerImageProcessingModelTest {

  EnhancedMultiLayerImageProcessingModel model = new SimpleEnhancedMultiLayerImageProcessingModel();

  // test downscaling with no image loaded
  @Test(expected = IllegalStateException.class)
  public void testDownscalingNoImage() {
    model.createLayer("First");
    model.downscaling(100,100);
  }

  // test downscaling with negative inputs
  @Test(expected = IllegalArgumentException.class)
  public void testDownscalingNegativeInputs() {
    model.createLayer("First");
    model.makeCheckerBoard(3);
    model.downscaling(-4,-5);
  }

  // test downscaling with inputs greater than original image
  @Test(expected = IllegalArgumentException.class)
  public void testDownscalingGreaterInputs() {
    model.createLayer("First");
    model.makeCheckerBoard(3);
    model.downscaling(100,100);
  }

  // tests that the downscale method correctly downscales the image
  @Test
  public void testDownscaling() {
    model.createLayer("First");
    model.makeCheckerBoard(3);
    model.downscaling(1,1);
    Image im = model.exportImage();
    Pixel p = im.getPixel(new Posn(0, 0));
    assertEquals(new Pixel(0, 0, 0), p);
    assertEquals(1, im.getWidth());
    assertEquals(1, im.getHeight());
  }

  // test mosaicing with no image loaded
  @Test(expected = IllegalStateException.class)
  public void testMosaicingNoImage() {
    model.mosaicing(1000);
  }

  // test mosaicing with negative inputs
  @Test(expected = IllegalArgumentException.class)
  public void testMosaicingNegativeInputs() {
    model.createLayer("First");
    model.mosaicing(-1);
  }

  // test mosaicing with more seeds than pixels in the image
  @Test(expected = IllegalArgumentException.class)
  public void testMosaicingMoreSeedsThanPixels() {
    model.createLayer("First");
    model.mosaicing(100000);
  }

  // test the mosaicing method
  @Test
  public void testMosaicing() {
    model.createLayer("First");
    model.makeCheckerBoard(3);
    model.mosaicing(9);
    Image im = model.exportImage();
    Pixel p = im.getPixel(new Posn(0, 0));
    assertEquals(new Pixel(0, 0, 0), p);
  }

}
