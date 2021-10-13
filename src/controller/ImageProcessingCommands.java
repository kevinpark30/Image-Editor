package controller;

import model.MultiLayerImageProcessingModel;

/**
 * Represents a function object command for an image processing application.
 */
public interface ImageProcessingCommands {

  /**
   * Executes the given command with the given model.
   * @param m the multi layer image processing application to execute the command from
   * @throws IllegalArgumentException if the model given is null
   */
  void run(MultiLayerImageProcessingModel m) throws IllegalArgumentException;

}
