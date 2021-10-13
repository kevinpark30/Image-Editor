package model;

/**
 * Represents the enumeration of the type of filters that are available.
 */
public enum Filter {
  BLUR(new double[][] {{0.0625,0.125,0.0625},{0.125,0.25,0.125},{0.0625,0.125,0.0625}}),
  SHARPEN(new double[][] {{-0.125,-0.125,-0.125,-0.125,-0.125},{-0.125,0.25,0.25,0.25,-0.125},
      {-0.125,0.25,1.0,0.25,-0.125},{-0.125,0.25,0.25,0.25,-0.125},
      {-0.125,-0.125,-0.125,-0.125,-0.125}});

  /**
   * Enumeration value constructor that associates a matrix with a filter.
   * @param matrix the filter matrix
   */
  Filter(double[][] matrix) {
    this.matrix = matrix;
  }

  private final double[][] matrix;

  /**
   * Gets the filter matrix of this enumeration.
   * @return the filter matrix
   */
  public double[][] getMatrix() {
    return matrix;
  }
}
