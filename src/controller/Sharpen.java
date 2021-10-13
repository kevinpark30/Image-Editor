package controller;

import model.Filter;
import model.MultiLayerImageProcessingModel;

/**
 * The sharpen command function object used in the command pattern for the controller.
 */
public class Sharpen implements ImageProcessingCommands {

  @Override
  public void run(MultiLayerImageProcessingModel m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("The model given was null.");
    }
    m.imageFilter(Filter.SHARPEN);
  }
}
