package controller;

/**
 * Represents the features that are available in our multi-layer image processing graphical
 * user interface application. All of these methods implemented by the controller tell the view
 * and model to take the appropriate action.
 */
public interface Features {

  /**
   * Creates a layer with the given name.
   * @param name the name of the layer
   */
  void createLayer(String name);

  /**
   * Deletes a layer with the given name.
   * @param name the name of the layer
   */
  void deleteLayer(String name);

  /**
   * Sets the current layer to name of the given layer.
   * @param name the name of the layer to set as the current layer
   */
  void setCurrentLayer(String name);

  /**
   * Sets the layer with the given name, which is the current layer, to be invisible.
   * @param name the layer to set to invisible
   */
  void setCurrentLayerToInvisible(String name);

  /**
   * Sets the layer with the given name, which is the current layer, to be visible.
   * @param name the layer to set to visible
   */
  void setCurrentLayerToVisible(String name);

  /**
   * Applies the blur filtering operation onto the current layer.
   */
  void applyBlurToCurrentLayer();

  /**
   * Applies the sharpen filtering option onto the current layer.
   */
  void applySharpenToCurrentLayer();

  /**
   * Applies the sepia color transformation option onto the current layer.
   */
  void applySepiaToCurrentLayer();

  /**
   * Applies the greyscale color transformation option onto the current layer.
   */
  void applyGreyscaleToCurrentLayer();

  /**
   * Applies the downscale operation to all of the layers. This operation downscales the layers
   * to the given width and height.
   * @param width the width to downscale to
   * @param height the height to downscale to
   */
  void applyDownscaleToAllLayers(String width, String height);

  /**
   * Applies the mosaic filtering operation to the current layer with the given number of seeds.
   * @param seeds the number of seeds to apply mosaic on
   */
  void applyMosaicToCurrentLayer(String seeds);

  /**
   * Loads the image in from the given filepath.
   * @param filepath the filepath to load an image from
   */
  void loadImage(String filepath);

  /**
   * Loads the project in from the given filepath.
   * @param filepath the filepath to load a project from
   */
  void loadProject(String filepath);

  /**
   * Saves the image to the given filepath.
   * @param filepath the filepath to save an image to
   */
  void save(String filepath);

  /**
   * Saves the project to the given filepath.
   * @param filepath the filepath to save the project to
   */
  void saveProject(String filepath);

  /**
   * Loads a checkerboard image in with the given length to the current layer. This checkberboard
   * image uses the colors black and white in alternating fashion from row to row.
   * @param length the length of the checkerboard image in pixels
   */
  void loadCheckboardImage(String length);

  /**
   * Loads in a script that contains commands that manipulate this multi-layer image processing
   * application.
   * @param filepath the filepath to load a script from
   */
  void loadScript(String filepath);

}
