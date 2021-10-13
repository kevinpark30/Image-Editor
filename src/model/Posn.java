package model;

import java.util.List;
import java.util.Objects;

/**
 * Represents a 2D position on the cartesian coordinate system.
 */
public class Posn {
  private final int x;
  private final int y;

  /**
   * Constructor that initializes the x and y position of a coordinate.
   * @param x the x coordinate
   * @param y the y coordinate
   */
  public Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Copy constructor for a position object.
   * @param position the position to copy
   */
  public Posn(Posn position) {
    this.x = position.x;
    this.y = position.y;
  }

  /**
   * Returns the x coordinate of this position.
   * @return the x coordinate
   */
  public int getX() {
    return x;
  }

  /**
   * Returns the y coordinate of this position.
   * @return the y coordinate
   */
  public int getY() {
    return y;
  }

  /**
   * Returns the index of the given list of positions that this position is closest to in that list.
   * @param positions the list of positions
   * @return the index of the given list of positions that this position is closest to
   * @throws IllegalArgumentException if the given list of positions is null or the size of the list
   *     is 0.
   */
  public int getIndexOfClosestPosition(List<Posn> positions) throws IllegalArgumentException {
    double minimumValue = Double.MAX_VALUE;
    int minimumIndex = -1;

    if (positions == null) {
      throw new IllegalArgumentException("The positions given were null.");
    }

    if (positions.size() == 0) {
      throw new IllegalArgumentException("The size of the positions list is 0.");
    }

    for (int i = 0; i < positions.size(); i++) {
      Posn currentPosn = positions.get(i);
      double distance = distanceTo(currentPosn);

      if (distance < minimumValue) {
        minimumIndex = i;
        minimumValue = distance;
      }
    }
    return minimumIndex;
  }

  /**
   * Calculates the distance between this position and the given position.
   * @param currentPosn the given position to calculate the distance to
   * @return the distance between this position and the given position
   */
  public double distanceTo(Posn currentPosn) {
    return Math.sqrt(Math.pow(this.x - currentPosn.x, 2) + Math.pow(this.y - currentPosn.y, 2));
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (! (o instanceof Posn)) {
      return false;
    }

    Posn that = (Posn) o;

    return this.x == that.x
        && this.y == that.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
