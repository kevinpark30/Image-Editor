package model;

import image.Image;

/**
 * Represents the model for an image processing application and the operations that it needs to
 * support.
 */
public interface ImageProcessingModel {

  /**
   * Applies the given image filter onto the current image.
   * @param filter the type of filter to be applied
   * @throws IllegalArgumentException if the given filter is null
   * @throws IllegalStateException if there is no image to apply the filter on
   */
  public void imageFilter(Filter filter) throws IllegalArgumentException, IllegalStateException;

  /**
   * Applies the given color transformation onto the current image.
   * @param ct the type of color transformation to be applied.
   * @throws IllegalArgumentException if the given color transformation is null
   * @throws IllegalStateException if there is no image to apply the color transformation on
   */
  public void colorTransformation(ColorTransformation ct) throws IllegalArgumentException,
      IllegalStateException;

  /**
   * Generates and adds an image of a programmatically made checkerboard.
   * @param length the length of the image in pixels
   * @throws IllegalArgumentException if the size of the square is 0 or less or length is negative
   */
  public void makeCheckerBoard(int length) throws IllegalArgumentException;

  /**
   * Imports the given file image into the application.
   * @param im The image being imported to the model.
   * @throws IllegalArgumentException if the given image is null
   */
  public void importImage(Image im) throws IllegalArgumentException;

  /**
   * Exports the current image into a file.
   * @throws IllegalStateException if there is no image to export, or the file could not be written
   *     to
   */
  public Image exportImage() throws IllegalStateException;




}
