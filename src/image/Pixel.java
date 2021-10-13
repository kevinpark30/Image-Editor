package image;

import java.util.Objects;

/**
 * This class represents an RGB pixel for images.
 */
public final class Pixel {
  private final int red;
  private final int green;
  private final int blue;

  /*
  Class Invariants:
  The value of red is between 0 and 255 inclusive.
  The value of green is between 0 and 255 inclusive.
  The value of blue is between 0 and 255 inclusive.
   */

  /**
   * Creates a pixel with the given RGB values.
   * @param red the red value of the pixel
   * @param green the green value of the pixel
   * @param blue the blue value of the pixel
   */
  public Pixel(int red, int green, int blue) {
    if (red < 0) {
      this.red = 0;
    }
    else if (red > 255) {
      this.red = 255;
    }
    else {
      this.red = red;
    }
    if (blue < 0) {
      this.blue = 0;
    }
    else if (blue > 255) {
      this.blue = 255;
    }
    else {
      this.blue = blue;
    }
    if (green < 0) {
      this.green = 0;
    }
    else if (green > 255) {
      this.green = 255;
    }
    else {
      this.green = green;
    }
  }

  /**
   * Copy constructor for Pixel objects.
   * @param p the pixel to copy
   */
  public Pixel(Pixel p) {
    if (p == null) {
      throw new IllegalArgumentException("The given pixel was null.");
    }

    this.red = p.red;
    this.green = p.green;
    this.blue = p.blue;
  }

  /**
   * Returns the red value of this pixel.
   * @return the red value of this pixel
   */
  public int getRed() {
    return red;
  }

  /**
   * Returns the green value of this pixel.
   * @return the green value of this pixel
   */
  public int getGreen() {
    return green;
  }

  /**
   * Returns the blue value of this pixel.
   * @return the blue value of this pixel
   */
  public int getBlue() {
    return blue;
  }

  /**
   * Applies the given color transformation matrix onto this pixel.
   * @param matrix the color transformation matrix
   * @return the new pixel result
   */
  public Pixel colorTransformation(double[][] matrix) {
    double[] rgbValues = new double[3];
    for (int i = 0; i < matrix.length; i++) {
      double[] row = matrix[i];
      rgbValues[i] = row[0] * red + row[1] * green + row[2] * blue;
    }

    int newRed = (int) Math.round(rgbValues[0]);
    int newGreen = (int) Math.round(rgbValues[1]);
    int newBlue = (int) Math.round(rgbValues[2]);

    return new Pixel(newRed, newGreen, newBlue);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (! (o instanceof Pixel)) {
      return false;
    }

    Pixel that = (Pixel) o;

    return this.red == that.red
        && this.green == that.green
        && this.blue == that.blue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }
}
