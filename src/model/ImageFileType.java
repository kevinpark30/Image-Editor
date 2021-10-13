package model;

import image.Image;

/**
 * Represents image file types and the operations that these files should support.
 */
public interface ImageFileType {
  /**
   * Imports an image from the given file path.
   * @param filename the filepath of the image file
   * @return the image that is imported
   * @throws IllegalArgumentException if the file is malformed in size, has bad inputs, or cannot be
   *     found in the file path
   */
  public Image importFile(String filename) throws IllegalArgumentException;

  /**
   * Exports the given image with the appropriate file type.
   * @param filename the filepath of the image file
   * @param im the image to export
   * @throws IllegalStateException if there is no image to export
   */
  public void writeFile(String filename, Image im) throws IllegalStateException;
}
