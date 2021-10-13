package controller;

import model.MultiLayerImageProcessingModel;

/**
 * The loadCheckerboard command function object used in the command pattern for the controller.
 */
public class LoadCheckerboard implements ImageProcessingCommands {

  private final String length;

  /**
   * Creates a loadCheckerBoard function object with the given length.
   * @param length the length
   */
  public LoadCheckerboard(String length) {
    this.length = length;
  }

  @Override
  public void run(MultiLayerImageProcessingModel m) {
    if (m == null) {
      throw new IllegalArgumentException("The model given was null.");
    }
    try {
      m.makeCheckerBoard(Integer.parseInt(length));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Must be an integer value.");
    }
  }

}
