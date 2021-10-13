package controller;

import model.Filter;
import model.MultiLayerImageProcessingModel;

/**
 * The blur command function object used in the command pattern for the controller.
 */
public class Blur implements ImageProcessingCommands {


  @Override
  public void run(MultiLayerImageProcessingModel m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("The model given was null.");
    }
    m.imageFilter(Filter.BLUR);
  }

}
