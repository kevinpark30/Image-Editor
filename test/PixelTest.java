import static org.junit.Assert.assertEquals;

import image.Pixel;
import model.ColorTransformation;
import org.junit.Test;

/**
 * Testing class for pixel class.
 */
public class PixelTest {
  //tests that the constructor clamps to the minimum red value
  @Test
  public void testConstructorClampRedMinimum() {
    Pixel p = new Pixel(-3, 1, 1);

    assertEquals(0, p.getRed());
  }

  //tests that the constructor clamps to the minimum green value
  @Test
  public void testConstructorClampGreenMinimum() {
    Pixel p = new Pixel(1, -3, 1);

    assertEquals(0, p.getGreen());
  }

  //tests that the constructor clamps to the minimum blue value
  @Test
  public void testConstructorClampBlueMinimum() {
    Pixel p = new Pixel(1, 1, -3);

    assertEquals(0, p.getBlue());
  }

  //tests that the constructor clamps to the maximum red value
  @Test
  public void testConstructorClampRedMaximum() {
    Pixel p = new Pixel(260, 1, 1);

    assertEquals(255, p.getRed());
  }

  //tests that the constructor clamps to the maximum red value
  @Test
  public void testConstructorClampGreenMaximum() {
    Pixel p = new Pixel(1, 260, 1);

    assertEquals(255, p.getGreen());
  }

  //tests that the constructor clamps to the maximum red value
  @Test
  public void testConstructorClampBlueMaximum() {
    Pixel p = new Pixel(1, 1, 260);

    assertEquals(255, p.getBlue());
  }

  //tests that the getRed method returns the correct red value of the pixel
  @Test
  public void testGetRed() {
    Pixel p = new Pixel(1, 2, 3);

    assertEquals(1, p.getRed());
  }

  //tests that the getGreen method returns the correct green value of the pixel
  @Test
  public void testGetGreen() {
    Pixel p = new Pixel(1, 2, 3);

    assertEquals(2, p.getGreen());
  }

  //tests that the getBlue method returns the correct blue value of the pixel
  @Test
  public void testGetBlue() {
    Pixel p = new Pixel(1, 2, 3);

    assertEquals(3, p.getBlue());
  }

  //tests that the colorTransformation method correctly transforms a pixel correctly
  @Test
  public void testColorTransformation() {
    Pixel p = new Pixel(255, 255, 255);
    Pixel expectedPixel = new Pixel(255, 255, 239);

    assertEquals(expectedPixel, p.colorTransformation(ColorTransformation.SEPIA.getMatrix()));
  }

  //tests that the equals method checks for extensional equality
  @Test
  public void testEquals() {
    Pixel p1 = new Pixel(255, 255, 255);
    Pixel p2 = new Pixel(255, 255, 255);

    assertEquals(p1, p2);
  }

  //tests that the hashCode method returns the same hash code for pixel objects that are
  //extensionally equal
  @Test
  public void testHashCode() {
    Pixel p1 = new Pixel(255, 255, 255);
    Pixel p2 = new Pixel(255, 255, 255);
    int hashCode1 = p1.hashCode();
    int hashCode2 = p2.hashCode();

    assertEquals(hashCode1, hashCode2);
  }

  // test Pixel copy constructor
  @Test
  public void testPixelCopyConstructor() {
    Pixel p = new Pixel(12,12,25);
    assertEquals(p,new Pixel(p));
  }
}