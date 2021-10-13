package controller;

import model.MultiLayerImageProcessingModel;

/**
 * The current command function object used in the command pattern for the controller.
 */
public class Current implements ImageProcessingCommands {

  private final String name;

  /**
   * Creates a current function object with the given name.
   * @param name the name
   */
  public Current(String name) {
    this.name = name;
  }


  @Override
  public void run(MultiLayerImageProcessingModel m) {
    if (m == null) {
      throw new IllegalArgumentException("The model given was null.");
    }
    m.setCurrentLayer(name);
  }

}
