import static org.junit.Assert.assertEquals;

import model.ColorTransformation;
import model.Filter;
import model.ImageProcessingModel;
import model.Posn;
import model.SimpleImageProcessingModel;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import image.Image;
import image.Pixel;

/**
 * Testing class for the image class.
 */
public class ImageTest {

  //tests that the constructor throws an exception if the given pixels is null
  @Test(expected = IllegalArgumentException.class)
  public void testImageConstructorPixelsNull() {
    new Image(null, 3, 3);
  }

  //tests that the constructor throws an exception if the width given is less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testImageConstructorInvalidWidth() {
    new Image(new ArrayList<>(), 0, 300);
  }

  //tests that the constructor throws an exception if the height given is less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testImagConstructoreInvalidHeight() {
    new Image(new ArrayList<>(), 300, 0);
  }

  //tests that the constructor throws an exception if the pixels given does not match the given
  //height
  @Test(expected = IllegalArgumentException.class)
  public void testImageConstructorInvalidPixelHeight() {
    new Image(new ArrayList<>(), 1, 1);
  }

  //tests that the constructor throws an exception if the pixels given does not match the given
  //width
  @Test(expected = IllegalArgumentException.class)
  public void testImageConstructorInvalidPixelWidth() {
    List<List<Pixel>> pixels = new ArrayList<>();
    pixels.add(new ArrayList<Pixel>());
    new Image(new ArrayList<>(), 1, 1);
  }

  //tests that the copy constructor throws an exception if the given image is null
  @Test(expected = IllegalArgumentException.class)
  public void testImageCopyConstructor() {
    new Image(null);
  }

  //tests that the getPixels method returns the pixels of an image
  @Test
  public void testGetPixels() {
    List<List<Pixel>> pixels = new ArrayList<>();
    pixels.add(new ArrayList<>());
    pixels.get(0).add(new Pixel(255, 255, 255));
    Image im = new Image(pixels, 1, 1);

    assertEquals(im.getPixels(), pixels);
  }

  //tests that the getWidth method properly returns the correct width
  @Test
  public void testGetWidth() {
    List<List<Pixel>> pixels = new ArrayList<>();
    pixels.add(new ArrayList<>());
    pixels.get(0).add(new Pixel(255, 255, 255));
    Image im = new Image(pixels, 1, 1);

    assertEquals(1, im.getWidth());
  }

  //tests that the getWidth method properly returns the correct width
  @Test
  public void testGetHeight() {
    List<List<Pixel>> pixels = new ArrayList<>();
    pixels.add(new ArrayList<>());
    pixels.get(0).add(new Pixel(255, 255, 255));
    Image im = new Image(pixels, 1, 1);

    assertEquals(1, im.getHeight());
  }

  //tests that the colorTransformation method returns the correct transformed image
  @Test
  public void testColorTransformation() {
    ImageProcessingModel model = new SimpleImageProcessingModel();
    model.makeCheckerBoard(3);
    Image im = model.exportImage();

    List<List<Pixel>> expectedPixels = new ArrayList<>();
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());

    expectedPixels.get(0).add(new Pixel(0, 0, 0));
    expectedPixels.get(0).add(new Pixel(255, 255, 239));
    expectedPixels.get(0).add(new Pixel(0, 0, 0));

    expectedPixels.get(1).add(new Pixel(255, 255, 239));
    expectedPixels.get(1).add(new Pixel(0, 0, 0));
    expectedPixels.get(1).add(new Pixel(255, 255, 239));

    expectedPixels.get(2).add(new Pixel(0, 0, 0));
    expectedPixels.get(2).add(new Pixel(255, 255, 239));
    expectedPixels.get(2).add(new Pixel(0, 0, 0));

    Image expectedImage = new Image(expectedPixels, 3, 3);
    assertEquals(expectedImage, im.colorTransformation(ColorTransformation.SEPIA.getMatrix()));
  }

  //tests that the imageFilter method returns the correct transformed image
  @Test
  public void testImageFilter() {
    ImageProcessingModel model = new SimpleImageProcessingModel();
    model.makeCheckerBoard(3);
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
    assertEquals(expectedImage, im.imageFilter(Filter.BLUR.getMatrix()));
  }

  //tests that the equals method checks for extensionally equality
  @Test
  public void testEquals() {
    Image im1 = new Image(new ArrayList<>(), 0, 0);
    Image im2 = new Image(new ArrayList<>(), 0, 0);

    assertEquals(im1, im2);
  }

  //tests that the hashCode method returns the same hashCode for image objects that are
  //extensionally equal
  @Test
  public void testHashCode() {
    Image im1 = new Image(new ArrayList<>(), 0, 0);
    Image im2 = new Image(new ArrayList<>(), 0, 0);
    int hashCode1 = im1.hashCode();
    int hashCode2 = im2.hashCode();
    assertEquals(hashCode1, hashCode2);
  }

  // test setPixel with null pixel
  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelNullPixel() {
    Image im = new Image(new ArrayList<>(),0,0);
    im.setPixel(new Posn(0,0),null);
  }

  // test setPixel with negative posn
  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelNegativePosn() {
    Image im = new Image(new ArrayList<>(),10,10);
    im.setPixel(new Posn(-1,-10),new Pixel(0,0,0));
  }

  // test setPixel with posn greater than width and height of original image
  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelGreaterThanImage() {
    Image im = new Image(new ArrayList<>(),10,10);
    im.setPixel(new Posn(10,100),new Pixel(0,0,0));
  }

  // test setPixel
  @Test
  public void testSetPixel() {
    List<List<Pixel>> pixels = new ArrayList<>();
    pixels.add(new ArrayList<>());
    pixels.get(0).add(new Pixel(0,0,0));
    Image im = new Image(pixels,1,1);
    im.setPixel(new Posn(0,0),new Pixel(10,10,10));
    assertEquals(im.getPixel(new Posn(0,0)),new Pixel(10,10,10));
  }

  // test downscale
  @Test
  public void testDownscale() {
    List<List<Pixel>> pixels = new ArrayList<>();
    pixels.add(new ArrayList<>());
    pixels.get(0).add(new Pixel(0,0,0));
    Image im = new Image(pixels,1,1);
    im.downscale(1,1);
    assertEquals(im.getPixel(new Posn(0,0)),new Pixel(0,0,0));

  }

  // test empty image constructor
  @Test
  public void testEmptyImageConstructor() {
    Image im = new Image(10,10);
    assertEquals(im.getPixels().get(4).get(5),new Pixel(0,0,0));
  }

}