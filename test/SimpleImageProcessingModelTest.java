import static org.junit.Assert.assertEquals;

import image.Image;
import image.Pixel;
import model.ColorTransformation;
import model.Filter;
import model.ImageProcessingModel;
import model.ImageUtils;
import model.PPM;
import model.SimpleImageProcessingModel;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;


/**
 * Test class for the simple image processing model.
 */
public class SimpleImageProcessingModelTest {

  private final ImageProcessingModel model = new SimpleImageProcessingModel();

  // tests getCurrentImage()
  @Test
  public void getCurrentImage() {
    model.makeCheckerBoard(3);

    List<List<Pixel>> expectedPixels = new ArrayList<>();
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());

    expectedPixels.get(0).add(new Pixel(0,0,0));
    expectedPixels.get(0).add(new Pixel(255,255,255));
    expectedPixels.get(0).add(new Pixel(0,0,0));

    expectedPixels.get(1).add(new Pixel(255,255,255));
    expectedPixels.get(1).add(new Pixel(0,0,0));
    expectedPixels.get(1).add(new Pixel(255,255,255));

    expectedPixels.get(2).add(new Pixel(0,0,0));
    expectedPixels.get(2).add(new Pixel(255,255,255));
    expectedPixels.get(2).add(new Pixel(0,0,0));

    Image expectedImage = new Image(expectedPixels, 3, 3);

    assertEquals(model.exportImage(),expectedImage);

  }

  // tests makeCheckerBoard()
  @Test
  public void testMakeCheckerBoard() {
    model.makeCheckerBoard(3);
    Image im = model.exportImage();

    List<List<Pixel>> expectedPixels = new ArrayList<>();
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());

    expectedPixels.get(0).add(new Pixel(0,0,0));
    expectedPixels.get(0).add(new Pixel(255,255,255));
    expectedPixels.get(0).add(new Pixel(0,0,0));

    expectedPixels.get(1).add(new Pixel(255,255,255));
    expectedPixels.get(1).add(new Pixel(0,0,0));
    expectedPixels.get(1).add(new Pixel(255,255,255));

    expectedPixels.get(2).add(new Pixel(0,0,0));
    expectedPixels.get(2).add(new Pixel(255,255,255));
    expectedPixels.get(2).add(new Pixel(0,0,0));

    Image expectedImage = new Image(expectedPixels, 3, 3);
    assertEquals(im, expectedImage);
  }

  // tests makeCheckerBoard() with invalid length
  @Test(expected = IllegalArgumentException.class)
  public void testMakeCheckerBoardInvalidLength() {
    model.makeCheckerBoard(-1);
  }

  // tests the blur image filter
  @Test
  public void testImageFilterBlur() {
    model.makeCheckerBoard(3);
    model.imageFilter(Filter.BLUR);
    Image im = model.exportImage();

    List<List<Pixel>> expectedPixels = new ArrayList<>();
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());

    expectedPixels.get(0).add(new Pixel(64, 64, 64));
    expectedPixels.get(0).add(new Pixel(96, 96, 96));
    expectedPixels.get(0).add(new Pixel(64, 64, 64));

    expectedPixels.get(1).add(new Pixel(96, 96, 96));
    expectedPixels.get(1).add(new Pixel(128, 128, 128));
    expectedPixels.get(1).add(new Pixel(96, 96, 96));

    expectedPixels.get(2).add(new Pixel(64, 64, 64));
    expectedPixels.get(2).add(new Pixel(96, 96, 96));
    expectedPixels.get(2).add(new Pixel(64, 64, 64));

    Image expectedImage = new Image(expectedPixels, 3, 3);
    assertEquals(im, expectedImage);
  }

  // tests the sharpen image filter
  @Test
  public void testImageFilterSharpen() {
    model.makeCheckerBoard(3);
    model.imageFilter(Filter.SHARPEN);
    Image im = model.exportImage();

    List<List<Pixel>> expectedPixels = new ArrayList<>();
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());

    expectedPixels.get(0).add(new Pixel(64, 64, 64));
    expectedPixels.get(0).add(new Pixel(255, 255, 255));
    expectedPixels.get(0).add(new Pixel(64, 64, 64));

    expectedPixels.get(1).add(new Pixel(255, 255, 255));
    expectedPixels.get(1).add(new Pixel(255, 255, 255));
    expectedPixels.get(1).add(new Pixel(255, 255, 255));

    expectedPixels.get(2).add(new Pixel(64, 64, 64));
    expectedPixels.get(2).add(new Pixel(255, 255, 255));
    expectedPixels.get(2).add(new Pixel(64, 64, 64));

    Image expectedImage = new Image(expectedPixels, 3, 3);
    assertEquals(im, expectedImage);



  }

  // tests imageFilter with null filter
  @Test(expected = IllegalArgumentException.class)
  public void testImageFilterNullFilter() {
    model.makeCheckerBoard(3);
    model.imageFilter(null);
  }

  // tests the sepia color transformation
  @Test
  public void testColorTransformationSepia() {
    model.makeCheckerBoard(3);
    model.colorTransformation(ColorTransformation.SEPIA);
    Image im = model.exportImage();

    List<List<Pixel>> expectedPixels = new ArrayList<>();
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());

    expectedPixels.get(0).add(new Pixel(0,0,0));
    expectedPixels.get(0).add(new Pixel(255, 255, 239));
    expectedPixels.get(0).add(new Pixel(0,0,0));

    expectedPixels.get(1).add(new Pixel(255, 255, 239));
    expectedPixels.get(1).add(new Pixel(0,0,0));
    expectedPixels.get(1).add(new Pixel(255, 255, 239));

    expectedPixels.get(2).add(new Pixel(0,0,0));
    expectedPixels.get(2).add(new Pixel(255, 255, 239));
    expectedPixels.get(2).add(new Pixel(0,0,0));

    Image expectedImage = new Image(expectedPixels, 3, 3);
    assertEquals(im, expectedImage);

  }

  // tests the greyscale color transformation
  @Test
  public void testColorTransformationGreyScale() {
    model.makeCheckerBoard(3);
    model.colorTransformation(ColorTransformation.GREYSCALE);
    Image im = model.exportImage();

    List<List<Pixel>> expectedPixels = new ArrayList<>();
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());

    expectedPixels.get(0).add(new Pixel(0,0,0));
    expectedPixels.get(0).add(new Pixel(255,255,255));
    expectedPixels.get(0).add(new Pixel(0,0,0));

    expectedPixels.get(1).add(new Pixel(255,255,255));
    expectedPixels.get(1).add(new Pixel(0,0,0));
    expectedPixels.get(1).add(new Pixel(255,255,255));

    expectedPixels.get(2).add(new Pixel(0,0,0));
    expectedPixels.get(2).add(new Pixel(255,255,255));
    expectedPixels.get(2).add(new Pixel(0,0,0));

    Image expectedImage = new Image(expectedPixels, 3, 3);
    assertEquals(im, expectedImage);
  }

  // tests colorTransformation with null color transformation
  @Test(expected = IllegalArgumentException.class)
  public void testColorTransformationNullCT() {
    model.makeCheckerBoard(3);
    model.colorTransformation(null);
  }

  // tests importImage
  @Test
  public void testImportImage() {
    Image im = ImageUtils.readFile("checkerboardBLUR.ppm",new PPM());
    model.importImage(im);

    List<List<Pixel>> expectedPixels = new ArrayList<>();
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());

    expectedPixels.get(0).add(new Pixel(64, 64, 64));
    expectedPixels.get(0).add(new Pixel(96, 96, 96));
    expectedPixels.get(0).add(new Pixel(64, 64, 64));

    expectedPixels.get(1).add(new Pixel(96, 96, 96));
    expectedPixels.get(1).add(new Pixel(128, 128, 128));
    expectedPixels.get(1).add(new Pixel(96, 96, 96));

    expectedPixels.get(2).add(new Pixel(64, 64, 64));
    expectedPixels.get(2).add(new Pixel(96, 96, 96));
    expectedPixels.get(2).add(new Pixel(64, 64, 64));

    Image expectedImage = new Image(expectedPixels, 3, 3);

    assertEquals(model.exportImage(),expectedImage);


  }

  // tests exportImage
  @Test
  public void testExportImage() {
    model.makeCheckerBoard(3);
    Image im = model.exportImage();

    List<List<Pixel>> expectedPixels = new ArrayList<>();
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());

    expectedPixels.get(0).add(new Pixel(0,0,0));
    expectedPixels.get(0).add(new Pixel(255,255,255));
    expectedPixels.get(0).add(new Pixel(0,0,0));

    expectedPixels.get(1).add(new Pixel(255,255,255));
    expectedPixels.get(1).add(new Pixel(0,0,0));
    expectedPixels.get(1).add(new Pixel(255,255,255));

    expectedPixels.get(2).add(new Pixel(0,0,0));
    expectedPixels.get(2).add(new Pixel(255,255,255));
    expectedPixels.get(2).add(new Pixel(0,0,0));

    Image expectedImage = new Image(expectedPixels, 3, 3);

    assertEquals(im,expectedImage);
  }

  // tests exportImage when there are no images to export
  @Test(expected = IllegalStateException.class)
  public void testExportImageNoImagesToExport() {
    model.exportImage();

  }

  // tests than an exception is thrown when imageFilter is called when there is no image
  @Test(expected = IllegalStateException.class)
  public void testImageFilterNoImage() {
    model.imageFilter(Filter.BLUR);
  }

  //tests than an exception is thrown when colorTransformation is called when there is no image
  @Test(expected = IllegalStateException.class)
  public void testColorTransformationNoImage() {
    model.colorTransformation(ColorTransformation.GREYSCALE);
  }
}

