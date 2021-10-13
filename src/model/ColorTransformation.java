package model;

/**
 * Represents the enumeration of color transformations that are available.
 */
public enum ColorTransformation {
  GREYSCALE(new double[][] {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722},
      {0.2126, 0.7152, 0.0722}}),

  SEPIA(new double[][] {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}});

  /**
   * Enumeration value constructor that associates a matrix with a color transformation.
   * @param matrix the color transformation matrix
   */
  ColorTransformation(double[][] matrix) {
    this.matrix = matrix;
  }

  private final double[][] matrix;

  /**
   * Gets the color transformation matrix of this enumeration.
   * @return the color transformation matrix
   */
  public double[][] getMatrix() {
    return matrix;
  }
}
