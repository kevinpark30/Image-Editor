package model;

import image.Image;
import image.Pixel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Implementation of the EnhancedMultiLayerImageProcessingModel interface. This class uses
 * the protected fields of the class that it extends to manipulate the internal state of the model
 * for this specific class.
 */
public class SimpleEnhancedMultiLayerImageProcessingModel extends
    SimpleMultiLayerImageProcessingModel implements EnhancedMultiLayerImageProcessingModel {

  @Override
  public void downscaling(int width, int height) throws IllegalArgumentException,
      IllegalStateException {
    if (this.width == -1 || this.height == -1) {
      throw new IllegalStateException("There is no image to downscale yet.");
    }

    if (width < 1 || height < 1) {
      throw new IllegalArgumentException("Cannot downscale to a width or height that is zero or "
          + "negative.");
    }

    if (width > this.width || height > this.height) {
      throw new IllegalArgumentException("Must downscale or leave the same width and height.");
    }

    this.width = width;
    this.height = height;

    for (Layer l : layers) {
      try {
        Image im = l.getImage();
        l.loadImage(im.downscale(width, height));
      } catch (IllegalStateException e) {
        // do nothing, the layer does not have an image so we cannot scale it down
      }
    }
  }

  @Override
  public void mosaicing(int seeds) throws IllegalArgumentException, IllegalStateException {
    if (currentLayerIndex == -1) {
      throw new IllegalStateException("There is no image to apply the mosaic on.");
    }

    if (seeds < 1) {
      throw new IllegalArgumentException("The number of seeds cannot be 0 or negative.");
    }

    // Pair class
    Set<Posn> seedPoints = new HashSet<>();
    Random r = new Random();

    // Using a set and randomly generate 1000 seeds which entails adding pairs of X and Y within
    // the bounds of an image until 1000 unique pairs are added
    if (seeds > width * height) {
      throw new IllegalArgumentException("The number of seeds cannot be greater than the number "
          + "of pixels in the image.");
    }

    while (seedPoints.size() < seeds) {
      int x = r.nextInt(width);
      int y = r.nextInt(height);
      seedPoints.add(new Posn(x, y));
    }

    // Convert the set to a list
    List<Posn> listOfSeedPoints = new ArrayList<>(seedPoints);

    // Use the list to create an array of clusters (these clusters will have only one pixel so far
    // which is the seed)
    Cluster[] clusters = new Cluster[listOfSeedPoints.size()];

    for (int i = 0; i < listOfSeedPoints.size(); i++) {
      clusters[i] = new Cluster();
      clusters[i].addPosn(listOfSeedPoints.get(i));
    }

    // Iterate through each pixel, and find the index of the closest seed, and then access that
    // cluster index and add that pixel
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Posn position = new Posn(j, i);
        int minimumDistanceIndex = position.getIndexOfClosestPosition(listOfSeedPoints);
        clusters[minimumDistanceIndex].addPosn(position);
      }
    }

    // Iterate through the array of clusters, for each pixel in the array of clusters, create
    // a new pixel at that pixel with the average color value for the cluster
    Image im = layers.get(currentLayerIndex).getImage();
    Image newImage = new Image(width, height);

    for (Cluster c : clusters) {
      int red;
      int green;
      int blue;

      red = c.averageRed(im);
      green = c.averageGreen(im);
      blue = c.averageBlue(im);

      List<Posn> positions = c.getPositions();
      for (Posn p : positions) {
        newImage.setPixel(p, new Pixel(red, green, blue));
      }
    }

    Layer layer = layers.get(currentLayerIndex);
    layer.loadImage(newImage);
  }

  @Override
  public String topMostVisibleLayerName() throws IllegalStateException {
    if (layers.size() == 0) {
      throw new IllegalStateException("There are no layers.");
    }

    for (int i = layers.size() - 1; i >= 0; i--) {
      if (layers.get(i).getVisibility()) {
        try {
          layers.get(i).getImage();
          return layers.get(i).getName();
        } catch (IllegalStateException e) {
          // do nothing and don't export this layer since no image is loaded
        }
      }
    }

    throw new IllegalStateException("All of the layers are invisible.");
  }


}
