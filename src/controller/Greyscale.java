package controller;

import model.ColorTransformation;
import model.MultiLayerImageProcessingModel;

/**
 * The greyScale command function object used in the command pattern for the controller.
 */
public class Greyscale implements ImageProcessingCommands {

  @Override
  public void run(MultiLayerImageProcessingModel m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("The model given was null.");
    }
    m.colorTransformation(ColorTransformation.GREYSCALE);
  }
}
