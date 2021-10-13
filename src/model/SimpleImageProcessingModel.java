package model;

import image.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import image.Pixel;

/**
 * This class represents a simple model of an image processing application.
 */
public class SimpleImageProcessingModel implements ImageProcessingModel {
  private final Stack<Image> imageHistory;

  public SimpleImageProcessingModel() {
    this.imageHistory = new Stack<>();
  }

  @Override
  public void imageFilter(Filter filter) throws IllegalArgumentException, IllegalStateException {
    if (filter == null) {
      throw new IllegalArgumentException("Given filter was null.");
    }
    if (imageHistory.size() == 0) {
      throw new IllegalStateException("There is no image to apply the filter on.");
    }
    Image im = imageHistory.peek();
    double[][] matrix = filter.getMatrix();
    Image postImage = im.imageFilter(matrix);
    imageHistory.push(postImage);
  }

  @Override
  public void colorTransformation(ColorTransformation ct) throws IllegalArgumentException,
      IllegalStateException {
    if (ct == null) {
      throw new IllegalArgumentException("Color transformation was null was null.");
    }
    if (imageHistory.size() == 0) {
      throw new IllegalStateException("There is no image to apply the color transformation on.");
    }
    Image im = imageHistory.peek();
    double[][] matrix = ct.getMatrix();
    Image postImage = im.colorTransformation(matrix);
    imageHistory.push(postImage);
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
    imageHistory.push(im);
  }

  @Override
  public void importImage(Image im) throws IllegalArgumentException {
    imageHistory.push(im);
  }

  @Override
  public Image exportImage() throws IllegalStateException {
    if (imageHistory.size() == 0) {
      throw new IllegalStateException("There is no image to export.");
    }
    return imageHistory.peek();
  }
}
