package model;

import image.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import image.Pixel;

/**
 * This class represents a simple model of an image processing application.
 */
public class SimpleMultiLayerImageProcessingModel implements MultiLayerImageProcessingModel {
  protected List<Layer> layers;
  protected int currentLayerIndex;
  protected int width;
  protected int height;

  /**
   * Creates a image processing model with an empty list of layers, a default layer index, width,
   * and height of -1.
   */
  public SimpleMultiLayerImageProcessingModel() {
    this.layers = new ArrayList<>();
    this.currentLayerIndex = -1;
    this.width = -1;
    this.height = -1;
  }

  /**
   * Copy constructor for the model.
   * @param model The model to be copied.
   */
  public SimpleMultiLayerImageProcessingModel(SimpleMultiLayerImageProcessingModel model) {
    this.layers = model.getLayers();
    this.currentLayerIndex = model.currentLayerIndex;
    this.width = model.width;
    this.height = model.height;
  }

  @Override
  public void imageFilter(Filter filter) throws IllegalArgumentException, IllegalStateException {
    if (filter == null) {
      throw new IllegalArgumentException("Given filter was null.");
    }
    if (currentLayerIndex == -1) {
      throw new IllegalStateException("There is no image to apply the filter on.");
    }
    Layer layer = layers.get(currentLayerIndex);
    double[][] matrix = filter.getMatrix();
    Image postImage = layer.getImage().imageFilter(matrix);
    layer.loadImage(postImage);
  }

  @Override
  public void colorTransformation(ColorTransformation ct) throws IllegalArgumentException,
      IllegalStateException {
    if (ct == null) {
      throw new IllegalArgumentException("Color transformation was null.");
    }
    if (currentLayerIndex == -1) {
      throw new IllegalStateException("There is no image to apply the color transformation on.");
    }
    Layer layer = layers.get(currentLayerIndex);
    double[][] matrix = ct.getMatrix();
    Image postImage = layer.getImage().colorTransformation(matrix);
    layer.loadImage(postImage);
  }

  @Override
  public void makeCheckerBoard(int length) throws IllegalArgumentException {
    if (length <= 0) {
      throw new IllegalArgumentException("The length of the checkerboard cannot be 0 or negative.");
    }
    List<List<Pixel>> pixels = new ArrayList<>();
    boolean whiteTile = true;
    for (int i = 0; i < length; i++) {
      pixels.add(new ArrayList<>());
      for (int j = 0; j < length; j++) {
        if (whiteTile) {
          pixels.get(i).add(new Pixel(0, 0, 0));
          whiteTile = false;
        }
        else {
          pixels.get(i).add(new Pixel(255, 255, 255));
          whiteTile = true;
        }
      }
    }
    Image im = new Image(pixels, length, length);
    importImage(im);
  }

  @Override
  public void importImage(Image im) throws IllegalArgumentException {
    if (im == null) {
      throw new IllegalArgumentException("You must give a name.");
    }

    if (currentLayerIndex == -1) {
      throw new IllegalStateException("There is no layer to load this image.");
    }

    if (width == -1 && height == -1) {
      this.width = im.getWidth();
      this.height = im.getHeight();
    }

    if (im.getWidth() != width || im.getHeight() != height) {
      throw new IllegalArgumentException("Cannot load in an image that is of different dimension "
          + "from the rest of the layers.");
    }

    layers.get(currentLayerIndex).loadImage(im);
  }

  //Tries to export the topmost visible layer. If the topmost visible layer does not have an
  //image loaded into it, all the layer are invisible, or there are no layers then we throw
  //exceptions.
  @Override
  public Image exportImage() throws IllegalStateException {
    if (layers.size() == 0) {
      throw new IllegalStateException("There are no layers.");
    }

    for (int i = layers.size() - 1; i >= 0; i--) {
      if (layers.get(i).getVisibility()) {
        try {
          Image im = layers.get(i).getImage();
          return im;
        } catch (IllegalStateException e) {
          // do nothing and don't export this layer since no image is loaded
        }
      }
    }
    throw new IllegalStateException("All the layers are invisible.");
  }

  @Override
  public void createLayer(String name) {
    if (name == null) {
      throw new IllegalArgumentException("You must give a name.");
    }
    for (Layer l : layers) {
      if (l.getName().equals(name)) {
        throw new IllegalArgumentException("Cannot add a new layer with a name that is"
            + " already used.");
      }
    }
    layers.add(new Layer(name));
    currentLayerIndex = layers.size() - 1;
  }

  //Deletes the current layer, if the layer to be deleted is the layer that the user is currently
  //on then the current layer is set to the topmost layer
  @Override
  public void deleteLayer(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("You must give a name.");
    }

    for (int i = 0; i < layers.size(); i++) {
      if (layers.get(i).getName().equals(name)) {
        layers.remove(layers.get(i));
        if (i < currentLayerIndex) {
          currentLayerIndex -= 1;
        }
        else if (i == currentLayerIndex) {
          currentLayerIndex = layers.size() - 1;
          // this resets the necessary width and height of the layers since there are no layers left
          if (layers.size() == 0) {
            this.width = -1;
            this.height = -1;
          }
        }
        return;
      }
    }
    throw new IllegalArgumentException("Could not find the layer with the given name.");
  }

  @Override
  public void setCurrentLayer(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("You must give a name.");
    }

    for (int i = 0; i < layers.size(); i++) {
      if (layers.get(i).getName().equals(name)) {
        currentLayerIndex = i;
        return;
      }
    }
    throw new IllegalArgumentException("Could not find the layer with the given name.");
  }

  @Override
  public void setVisible(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("You must give a name.");
    }

    for (int i = 0; i < layers.size(); i++) {
      if (layers.get(i).getName().equals(name)) {
        layers.get(i).setVisible();
        return;
      }
    }
    throw new IllegalArgumentException("Could not find the layer with the given name.");
  }

  @Override
  public void setInvisible(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("You must give a name.");
    }

    for (int i = 0; i < layers.size(); i++) {
      if (layers.get(i).getName().equals(name)) {
        layers.get(i).setInvisible();
        return;
      }
    }
    throw new IllegalArgumentException("Could not find the layer with the given name.");
  }

  @Override
  public List<Image> exportLayers() {
    List<Image> images = new ArrayList<>();
    for (Layer l : layers) {
      try {
        Image im = l.getImage();
        images.add(im);
      } catch (IllegalStateException e) {
        // do nothing
      }
    }
    return images;
  }

  @Override
  public List<Layer> getLayers() {
    List<Layer> layers = new ArrayList<>();
    for (Layer l : this.layers) {
      try {
        Image im = l.getImage();
        layers.add(new Layer(Optional.of(im), l.getVisibility(), l.getName()));
      } catch (IllegalStateException e) {
        layers.add(new Layer(Optional.empty(), l.getVisibility(), l.getName()));
      }
    }
    return layers;
  }

  @Override
  public void reset(SimpleMultiLayerImageProcessingModel newModel) {
    this.layers = newModel.layers;
    this.currentLayerIndex = newModel.currentLayerIndex;
    this.width = newModel.width;
    this.height = newModel.height;
  }
}
