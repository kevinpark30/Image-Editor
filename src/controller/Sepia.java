package controller;

import model.ColorTransformation;
import model.MultiLayerImageProcessingModel;

/**
 * The sepia command function object used in the command pattern for the controller.
 */
public class Sepia implements ImageProcessingCommands {

  @Override
  public void run(MultiLayerImageProcessingModel m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("The model given was null.");
    }
    m.colorTransformation(ColorTransformation.SEPIA);
  }
}
