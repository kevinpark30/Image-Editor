package model;

import image.Image;
import java.util.Optional;

/**
 * Represents a layer for a multi-image.
 */
public class Layer implements ILayer {
  private Optional<Image> image;
  private boolean visibility;
  private String name;

  /**
   * Constructor that creates a layer object with the given name with no image yet loaded into it.
   * @param name the name of the layer
   */
  public Layer(String name) {
    if (name == null) {
      throw new IllegalArgumentException("The name of the layer is null.");
    }

    image = Optional.empty();
    this.name = name;
    this.visibility = true;
  }

  /**
   * Copy constructor for layer object.
   * @param im the image to be copied
   * @param visibility the visibility type to copied
   * @param name the name of the layer to be copied
   */
  public Layer(Optional<Image> im, boolean visibility, String name) {
    this.image = im;
    this.visibility = visibility;
    this.name = name;
  }

  @Override
  public void loadImage(Image im) throws IllegalArgumentException {
    if (im == null) {
      throw new IllegalArgumentException("The given image was null.");
    }
    image = Optional.of(im);
  }

  @Override
  public void setVisible() {
    visibility = true;
  }

  @Override
  public void setInvisible() {
    visibility = false;
  }

  /**
   * Gets a copy of the image that is stored in this layer.
   * @return the image stored in this layer
   * @throws IllegalStateException if the layer does not have an image loaded into it yet
   */
  public Image getImage() {
    if (image.isEmpty()) {
      throw new IllegalStateException("There is no image in this layer.");
    }
    return new Image(image.get());
  }

  /**
   * Determines if this layer is visible or not.
   * @return the visibility of this layer
   */
  public boolean getVisibility() {
    return visibility;
  }

  /**
   * Gets the name of this layer.
   * @return the name of this layer
   */
  public String getName() {
    return name;
  }
}
