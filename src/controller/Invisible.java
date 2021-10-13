package controller;

import model.MultiLayerImageProcessingModel;

/**
 * The invisible command function object used in the command pattern for the controller.
 */
public class Invisible implements ImageProcessingCommands {

  private final String name;

  /**
   * Creates an invisible function object with the given name.
   * @param name the name
   */
  public Invisible(String name) {
    this.name = name;
  }

  @Override
  public void run(MultiLayerImageProcessingModel m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("The model given was null.");
    }
    m.setInvisible(name);
  }
}
