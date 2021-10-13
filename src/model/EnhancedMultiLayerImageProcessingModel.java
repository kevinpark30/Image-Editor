package model;

/**
 * A multi-layer image processing interface that also supports image downscaling and image
 * mosaicing.
 */
public interface EnhancedMultiLayerImageProcessingModel extends MultiLayerImageProcessingModel {

  /**
   * Downscales all the sizes of the layers to the given width and the given height.
   * @param width the width to downscale to
   * @param height the height to downscale to
   * @throws IllegalArgumentException if the width and the height is 0 or negative, if the
   *     width is greater than the width of the original layers, or if the height is greater than
   *     the height of the original layers
   * @throws IllegalStateException if there is no image to downscale yet
   */
  public void downscaling(int width, int height) throws IllegalArgumentException;

  /**
   * Produces an image mosaicing effect with the given number of seeds.
   * @param seeds the number of seeds to apply the image mosaicing on
   * @throws IllegalArgumentException if the number of seeds is 0 or negative, if the number of
   *     seeds is greater than the number of pixels in the image
   * @throws IllegalStateException if there is no image to apply the mosaic on
   */
  public void mosaicing(int seeds) throws IllegalArgumentException, IllegalStateException;

  /**
   * Gets the top-most visible layer in the list of layers.
   * @return The name of the top most visible layer.
   * @throws IllegalStateException if there is no top most visible layer
   */

  public String topMostVisibleLayerName() throws IllegalStateException;
}
