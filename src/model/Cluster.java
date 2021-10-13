package model;

import image.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cluster of pixels in a mosaic image. Each pixel in the mosaic image have the same
 * color and is an average of the colors of all the pixels in the cluster.
 */
public class Cluster {
  List<Posn> positions;

  /**
   * Default cluster that creates a cluster of positions that are first empty.
   */
  public Cluster() {
    this.positions = new ArrayList<>();
  }

  /**
   * Adds a postion to this cluster.
   * @param position the position to add this cluster to
   */
  public void addPosn(Posn position) {
    this.positions.add(position);
  }

  /**
   * Calculates the average red value of the positions in this cluster in the given image.
   * @param im the image to find the average red value for
   * @return the average red value
   */
  public int averageRed(Image im) {
    double sumOfRed = 0;

    for (Posn p : positions) {
      sumOfRed += im.getPixel(p).getRed();
    }

    return (int) Math.round(sumOfRed / positions.size());
  }

  /**
   * Calculates the average green value of the positions in this cluster in the given image.
   * @param im the image to find the average green value for
   * @return the average green value
   */
  public int averageGreen(Image im) {
    double sumOfGreen = 0;

    for (Posn p : positions) {
      sumOfGreen += im.getPixel(p).getGreen();
    }

    return (int) Math.round(sumOfGreen / positions.size());
  }

  /**
   * Calculates the average blue value of the positions in this cluster in the given image.
   * @param im the image to find the average blue value for
   * @return the average blue value
   */
  public int averageBlue(Image im) {
    double sumOfBlue = 0;

    for (Posn p : positions) {
      sumOfBlue += im.getPixel(p).getBlue();
    }

    return (int) Math.round(sumOfBlue / positions.size());
  }

  /**
   * Gets the positions in this cluster.
   * @return the postions in this cluster
   */
  public List<Posn> getPositions() {
    List<Posn> copyPosition = new ArrayList<>();

    for (Posn p : positions) {
      copyPosition.add(p);
    }

    return copyPosition;
  }
}
