package controller;

import model.MultiLayerImageProcessingModel;

/**
 * The deleteLayer command function object used in the command pattern for the controller.
 */
public class DeleteLayer implements ImageProcessingCommands {

  private final String name;

  /**
   * Creates a deleteLayer function object with the given name.
   * @param name the name
   */
  public DeleteLayer(String name) {
    this.name = name;
  }

  @Override
  public void run(MultiLayerImageProcessingModel m) {
    if (m == null) {
      throw new IllegalArgumentException("The model given was null.");
    }
    m.deleteLayer(name);
  }
}
