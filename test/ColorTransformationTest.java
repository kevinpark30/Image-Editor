import static org.junit.Assert.assertArrayEquals;

import model.ColorTransformation;
import org.junit.Test;

/**
 * Testing class for the color transformation enumeration.
 */
public class ColorTransformationTest {
  //tests that the getMatrix method retrieves the correct matrix for the color transformation
  @Test
  public void getMatrix() {
    double [][] matrix = ColorTransformation.GREYSCALE.getMatrix();
    double [][] expectedMatrix = {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};

    assertArrayEquals(expectedMatrix, matrix);
  }
}