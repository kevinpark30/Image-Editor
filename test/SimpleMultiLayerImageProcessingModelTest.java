import static org.junit.Assert.assertEquals;

import image.Image;
import image.Pixel;
import java.util.ArrayList;
import java.util.List;
import model.ColorTransformation;
import model.Filter;
import model.ImageUtils;
import model.Layer;
import model.MultiLayerImageProcessingModel;
import model.PPM;
import model.SimpleMultiLayerImageProcessingModel;
import org.junit.Test;

/**
 * Testing class for the SimpleMultiLayerImageProcessingModel class.
 */
public class SimpleMultiLayerImageProcessingModelTest {

  private final MultiLayerImageProcessingModel model = new SimpleMultiLayerImageProcessingModel();

  // tests makeCheckerBoard() with invalid length
  @Test(expected = IllegalArgumentException.class)
  public void testMakeCheckerBoardInvalidLength() {
    model.makeCheckerBoard(-1);
  }

  // tests makeCheckerBoard()
  @Test
  public void testMakeCheckerBoard() {
    model.createLayer("First");
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

  // tests imageFilter() with a null filter
  @Test(expected = IllegalArgumentException.class)
  public void imageFilterNullFilter() {
    model.imageFilter(null);
  }

  // tests imageFilter() with no selected currentLayerIndex
  @Test(expected = IllegalStateException.class)
  public void imageFilterNoCurrentLayerIndex() {
    model.imageFilter(Filter.BLUR);
  }

  // tests imageFilter() blur
  @Test
  public void imageFilter() {
    model.createLayer("First");
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

  // tests imageFilter() sharpen
  @Test
  public void testImageFilterSharpen() {
    model.createLayer("First");
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

  // tests colorTransformation() with null color transformation
  @Test(expected = IllegalArgumentException.class)
  public void colorTransformationNullFilter() {
    model.colorTransformation(null);
  }

  // tests colorTransformation() with no selected currentLayerIndex
  @Test(expected = IllegalStateException.class)
  public void colorTransformationNoCurrentLayerIndex() {
    model.colorTransformation(ColorTransformation.SEPIA);
  }

  // tests colorTransformation() sepia
  @Test
  public void testColorTransformationSepia() {
    model.createLayer("First");
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

  // tests colorTransformation() greyscale
  @Test
  public void testColorTransformationGreyScale() {
    model.createLayer("First");
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

  // test importImage() with null image
  @Test(expected = IllegalArgumentException.class)
  public void testImportImageNullImage() {
    model.importImage(null);
  }


  // test importImage() with no selected currentLayerIndex
  @Test(expected = IllegalStateException.class)
  public void testImportImageNoCurrentLayerIndex() {
    Image im = ImageUtils.readFile("checkerboardBLUR.ppm",new PPM());
    model.importImage(im);
  }

  // tests importImage() with an image that has different dimensions than other layers
  @Test(expected = IllegalArgumentException.class)
  public void testImportImageWrongDimensions() {
    Image im1 = ImageUtils.readFile("checkerboardBLUR.ppm",new PPM());
    Image im2 = ImageUtils.readFile("mushrooms.ppm",new PPM());
    model.createLayer("First");
    model.importImage(im1);
    model.createLayer("Second");
    model.importImage(im2);
  }

  // tests importImage
  @Test
  public void testImportImage() {
    Image im = ImageUtils.readFile("checkerboardBLUR.ppm",new PPM());
    model.createLayer("First");
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

  // tests exportImage() when there are no layers available
  @Test(expected = IllegalStateException.class)
  public void exportImageNoLayers() {
    model.exportImage();
  }

  // tests exportImage() when all layers are invisible
  @Test(expected = IllegalStateException.class)
  public void exportImageAllInvisibleLayers() {
    model.createLayer("First");
    model.setInvisible("First");
    model.createLayer("Second");
    model.setInvisible("Second");
    model.exportImage();
  }

  // tests exportImage() when top most layer is visible but no image is loaded
  @Test(expected = IllegalStateException.class)
  public void exportImageTopLayerNoImage() {
    model.createLayer("First");
    model.exportImage();
  }

  // tests exportImage() with valid layer with loaded image
  @Test
  public void exportImage() {
    model.createLayer("First");
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

  // test createLayer() with null name
  @Test(expected = IllegalArgumentException.class)
  public void testCreateLayerNullName() {
    model.createLayer(null);
  }

  // test createLayer() with duplicate name
  @Test(expected = IllegalArgumentException.class)
  public void testCreateLayerDuplicateName() {
    model.createLayer("First");
    model.createLayer("First");
  }

  // tests createLayer()
  @Test
  public void testCreateLayer() {
    model.createLayer("First");
    List<Layer> layers = model.getLayers();
    assertEquals(layers.get(0).getName(),"First");
  }

  // tests deleteLayer() with null name
  @Test(expected = IllegalArgumentException.class)
  public void testDeleteLayerNullName() {
    model.deleteLayer(null);
  }

  // tests deleteLayer() with invalid name
  @Test(expected = IllegalArgumentException.class)
  public void testDeleteLayerInvalidName() {
    model.createLayer("First");
    model.deleteLayer("Second");
  }

  // tests deleteLayer()
  @Test
  public void testDeleteLayer() {
    model.createLayer("First");
    model.makeCheckerBoard(3);
    model.createLayer("Second");
    model.deleteLayer("Second");
    assertEquals(model.getLayers().get(0).getName(),"First");

  }

  // tests setCurrentLayer() with null name
  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentLayerNullName() {
    model.setCurrentLayer(null);
  }

  // tests setCurrentLayer() with invalid name
  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentLayerInvalidName() {
    model.createLayer("Second");
    model.setCurrentLayer("First");
  }

  // tests setCurrentLayer()
  @Test
  public void testSetCurrentLayer() {
    model.createLayer("First");
    model.createLayer("Second");
    model.setCurrentLayer("First");
    model.makeCheckerBoard(3);
    model.setInvisible("Second");

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

  // tests setVisible() with null name
  @Test(expected = IllegalArgumentException.class)
  public void testSetVisibleNullName() {
    model.setVisible(null);
  }

  // tests setVisible() with invalid name
  @Test(expected = IllegalArgumentException.class)
  public void testSetVisibleInvalidName() {
    model.setVisible("First");
  }

  // tests setVisible()
  @Test
  public void testSetVisible() {
    model.createLayer("First");
    model.setInvisible("First");
    model.setVisible("First");
    assertEquals(model.getLayers().get(0).getVisibility(),true);
  }

  // tests setInvisible() with null name
  @Test(expected = IllegalArgumentException.class)
  public void testSetInvisibleNullName() {
    model.setInvisible(null);
  }

  // tests setInvisible() with invalid name
  @Test(expected = IllegalArgumentException.class)
  public void testSetInvisibleInvalidName() {
    model.setInvisible("First");
  }

  // tests setInvisible
  @Test
  public void testSetInvisible() {
    model.createLayer("First");
    model.setInvisible("First");
    assertEquals(model.getLayers().get(0).getVisibility(),false);
  }

  // tests exportLayers()
  @Test
  public void testExportLayers() {
    model.createLayer("First");
    model.createLayer("Second");
    model.makeCheckerBoard(3);
    Image im = model.exportLayers().get(0);

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

  // tests getLayers()
  @Test
  public void testGetLayers() {
    model.createLayer("First");
    model.makeCheckerBoard(3);
    Image im = model.getLayers().get(0).getImage();

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








}
