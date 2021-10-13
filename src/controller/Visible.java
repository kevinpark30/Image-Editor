package controller;

import model.MultiLayerImageProcessingModel;

/**
 * The visible command function object used in the command pattern for the controller.
 */
public class Visible implements ImageProcessingCommands {

  private final String name;

  /**
   * Creates a visible function object with the given name.
   * @param name the name
   */
  public Visible(String name) {
    this.name = name;
  }

  @Override
  public void run(MultiLayerImageProcessingModel m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("The model given was null.");
    }
    m.setVisible(name);
  }
}
