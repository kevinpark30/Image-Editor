package model;

import image.Image;

/**
 * Represents a layer of a multi-layer image.
 */
public interface ILayer {

  /**
   * Loads an image into this layer.
   * @param im the given image
   * @throws IllegalArgumentException if the given image is null
   */
  public void loadImage(Image im) throws IllegalArgumentException;

  /**
   * Sets the layer to be visible.
   */
  public void setVisible();

  /**
   * Sets the layer to be invisible.
   */
  public void setInvisible();

}
