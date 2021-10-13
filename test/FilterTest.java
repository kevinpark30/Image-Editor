import static org.junit.Assert.assertArrayEquals;

import model.Filter;
import org.junit.Test;

/**
 * Testing class for the filter enumeration.
 */
public class FilterTest {
  //tests that the getMatrix returns the correct matrix for the filter enumeration
  @Test
  public void testGetMatrix() {
    double [][] matrix = Filter.BLUR.getMatrix();
    double [][] expectedMatrix = {{0.0625,0.125,0.0625},{0.125,0.25,0.125},{0.0625,0.125,0.0625}};

    assertArrayEquals(expectedMatrix, matrix);
  }
}