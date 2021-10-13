package controller;

import model.MultiLayerImageProcessingModel;

/**
 * The createLayer command function object used in the command pattern for the controller.
 */
public class CreateLayer implements ImageProcessingCommands {

  private final String name;

  /**
   * Creates a createLayer function object with the given name.
   * @param name the name
   */
  public CreateLayer(String name) {
    this.name = name;
  }

  @Override
  public void run(MultiLayerImageProcessingModel m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("The model given was null.");
    }
    m.createLayer(name);
  }
}
