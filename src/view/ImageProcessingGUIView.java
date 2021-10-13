package view;

import controller.Features;
import image.Image;

/**
 * Represents the operations that a multi-layer image processing GUI view has such as being able
 * to add features from the Feature interface to the view.
 */
public interface ImageProcessingGUIView {

  /**
   * Adds all the features in the given feature to this view.
   * @param features the features to implement
   */
  void addFeatures(Features features);

  /**
   * Creates a pop-up error message with the given message in the multi-layer image processing
   * program.
   * @param message the error message
   */
  void notifyErrorMessage(String message);

  /**
   * Shows the top-most visible image in all current layers.
   * @param im The image to be shown.
   * @param name The name of the image to be shown.
   */
  void showTopMostImage(Image im, String name);

  /**
   * Adds the given name to the view's layer selection list.
   * @param name The name to be added.
   */
  void addToLayerSelectionList(String name);

  /**
   * Removes the given name from the view's layer selection list.
   * @param name The name to be removed.
   */
  void removeFromLayerSelectionList(String name);
}
