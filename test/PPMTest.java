import static org.junit.Assert.assertEquals;

import image.Image;
import image.Pixel;
import model.ImageProcessingModel;
import model.PPM;
import model.SimpleImageProcessingModel;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Test class for the PPM image file type.
 */
public class PPMTest {

  private final ImageProcessingModel model1 = new SimpleImageProcessingModel();

  // tests that appendable is null if the default constructor is passed
  @Test
  public void testPPMDefaultConstructor() {
    PPM ppm = new PPM();
    assertEquals(ppm.getAp(),null);
  }

  // tests that getAP() gives the correct appendable
  @Test
  public void testPPMGetAP() {
    StringBuilder ap = new StringBuilder();
    PPM ppm = new PPM(ap);
    assertEquals(ppm.getAp(),ap);
  }

  // tests the writeFile method
  @Test
  public void testPPMWriteFile() {
    StringBuilder ap = new StringBuilder();
    PPM ppmTest = new PPM(ap);

    model1.makeCheckerBoard(3);
    Image im = model1.exportImage();
    ppmTest.writeFile("checkerboard",im);
    assertEquals(ppmTest.getAp().toString(),"P3\n"
        + "3 3\n"
        + "255\n"
        + "0\n"
        + "0\n"
        + "0\n"
        + "255\n"
        + "255\n"
        + "255\n"
        + "0\n"
        + "0\n"
        + "0\n"
        + "255\n"
        + "255\n"
        + "255\n"
        + "0\n"
        + "0\n"
        + "0\n"
        + "255\n"
        + "255\n"
        + "255\n"
        + "0\n"
        + "0\n"
        + "0\n"
        + "255\n"
        + "255\n"
        + "255\n"
        + "0\n"
        + "0\n"
        + "0\n");
  }

  // tests the importFile method
  @Test
  public void testImportFile() {
    PPM ppm = new PPM();

    Image im = ppm.importFile("checkerboardBLUR.ppm");

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

    assertEquals(im,expectedImage);

  }

  // tests importFile method with file not found
  @Test(expected = IllegalArgumentException.class)
  public void testImportFileFileNotFound() {
    PPM ppm = new PPM();
    ppm.importFile("aidfnoadnc.ppm");
  }

  // tests importFile method with invalid ppm file (no P3 in beginning)
  @Test(expected = IllegalArgumentException.class)
  public void testImportFileNoP3() {
    PPM ppm = new PPM();
    ppm.importFile("PPMTestImageNoP3");
  }

  // tests importFile method with invalid width
  @Test(expected = IllegalArgumentException.class)
  public void testImportFileInvalidWidth() {
    PPM ppm = new PPM();
    ppm.importFile("PPMTestImageNoWidth");
  }

  // tests importFile method with invalid height
  @Test(expected = IllegalArgumentException.class)
  public void testImportFileInvalidHeight() {
    PPM ppm = new PPM();
    ppm.importFile("PPMTestImageNoHeight");
  }

  // tests importFile method with invalid max channel value
  @Test(expected = IllegalArgumentException.class)
  public void testImportFileInvalidMaxChannelValue() {
    PPM ppm = new PPM();
    ppm.importFile("PPMTestImageNoMaxValue");
  }

  // tests importFile method with invalid channel values
  @Test(expected = IllegalArgumentException.class)
  public void testImportFileInvalidChannelValue() {
    PPM ppm = new PPM();
    ppm.importFile("PPMTestImageInvalidChannel");
  }



}
