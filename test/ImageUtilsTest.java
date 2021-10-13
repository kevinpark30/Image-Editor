import static org.junit.Assert.assertEquals;

import image.Image;
import image.Pixel;
import java.util.ArrayList;
import java.util.List;
import model.ImageUtils;
import model.PPM;
import org.junit.Test;

/**
 * Test class for the ImageUtils class.
 */
public class ImageUtilsTest {

  // tests readFile
  @Test
  public void testReadFile() {
    Image im = ImageUtils.readFile("checkerboardBLUR.ppm",new PPM());

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

  // tests writeFile
  @Test
  public void testWriteFile() {
    Image im = ImageUtils.readFile("checkerboardBLUR.ppm",new PPM());

    PPM ppmtest = new PPM(new StringBuilder());
    ImageUtils.writeFile("checkboardBLUR", im, ppmtest);
    assertEquals("P3\n"
        + "3 3\n"
        + "255\n"
        + "64\n"
        + "64\n"
        + "64\n"
        + "96\n"
        + "96\n"
        + "96\n"
        + "64\n"
        + "64\n"
        + "64\n"
        + "96\n"
        + "96\n"
        + "96\n"
        + "128\n"
        + "128\n"
        + "128\n"
        + "96\n"
        + "96\n"
        + "96\n"
        + "64\n"
        + "64\n"
        + "64\n"
        + "96\n"
        + "96\n"
        + "96\n"
        + "64\n"
        + "64\n"
        + "64\n",ppmtest.getAp().toString());
  }

}
