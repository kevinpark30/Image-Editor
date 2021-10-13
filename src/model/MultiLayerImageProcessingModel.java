package model;

import image.Image;
import java.util.List;

/**
 * Represents the model for a multi-layer image processing application. This model supports
 * creating new layers, applying filters and color transformations to individual layers, save layers
 * as an image, save layers as a project, and set a layer visible or invisible.
 */
public interface MultiLayerImageProcessingModel extends ImageProcessingModel {

  /**
   * Creates a new layer in the MultiLayerImageProcessingModel with no image yet (still need to load
   * in an image). Also sets this as the current layer.
   * @param name the name of the layer to create
   * @throws IllegalArgumentException if the given name is null or the given name already exists
   *     in one of the layers
   */
  public void createLayer(String name);

  /**
   * Deletes the given layer.
   * @param name the name of the layer to delete
   * @throws IllegalArgumentException if the name given is not one of the names of the layers
   */
  public void deleteLayer(String name) throws IllegalArgumentException;

  /**
   * Sets the current layer to the one specified by the given name.
   * @param name the name of the layer to set to current
   * @throws IllegalArgumentException if the name is null or the layer does not exist with the given
   *     name
   */
  public void setCurrentLayer(String name) throws IllegalArgumentException;

  /**
   * Sets the layer with the given name to be visible.
   * @param name the name of the layer to turn to visible
   * @throws IllegalArgumentException if the name given is not one of the names of the layers
   */
  public void setVisible(String name) throws IllegalArgumentException;

  /**
   * Sets the layer with the given name to be invisible.
   * @param name the name of the layer to turn to invisible
   * @throws IllegalArgumentException if the name given is not one of the names of the layers
   */
  public void setInvisible(String name) throws IllegalArgumentException;

  /**
   * Exports all the images in the layers that have images loaded into them.
   */
  public List<Image> exportLayers();

  /**
   * Gets a copy of the layers in this model.
   * @return the layers in this model
   */
  public List<Layer> getLayers();

  /**
   * Resets the given model with the given model state.
   * @param newModel the given model
   */
  public void reset(SimpleMultiLayerImageProcessingModel newModel);
}
