package image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Posn;

/**
 * This class represents a 24-bit image with three channels of 8 bits each.
 */
public class Image {
  private final List<List<Pixel>> pixels;
  private final int width;
  private final int height;

  /*
  Class Invariants:
  The width is not negative.
  The height is not negative.
  Each list in the list of list of pixels contains the same number of pixels equal to the height.
  The length of the list of list of pixels is equal to the width.
  (the two class invariants above ensure that this image is not malformed)
   */

  /**
   * Creates an image object with the given pixels, width, and height of the image.
   * @param pixels the pixels in the image
   * @param width the width of the image in pixels
   * @param height the height of the image in pixels
   * @throws IllegalArgumentException if the pixels given is null, does not match the width and
   *     height or the width and height are negative
   */
  public Image(List<List<Pixel>> pixels, int width, int height) {
    if (pixels == null) {
      throw new IllegalArgumentException("Pixels given was null.");
    }
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height cannot be negative or zero.");
    }

    if (pixels.size() != height) {
      throw new IllegalArgumentException("The pixels given does not have the correct width.");
    }

    for (int i = 0; i < pixels.size(); i++) {
      if (pixels.get(i).size() != width) {
        throw new IllegalArgumentException("The pixels given does not have the correct height.");
      }
    }

    this.pixels = pixels;
    this.width = width;
    this.height = height;
  }

  /**
   * Copy constructor for image objects.
   * @param im the image to make an image copy object
   */
  public Image(Image im) {
    if (im == null) {
      throw new IllegalArgumentException("Pixels given was null.");
    }
    List<List<Pixel>> pixels = new ArrayList<>();
    List<List<Pixel>> copyPixels = im.getPixels();
    for (int i = 0; i < im.height; i++) {
      pixels.add(new ArrayList<>());
      for (int j = 0; j < im.width; j++) {
        pixels.get(i).add(copyPixels.get(i).get(j));
      }
    }

    this.pixels = pixels;
    this.width = im.width;
    this.height = im.height;
  }

  /**
   * Constructor that creates in image in the given width and height of default pixels which is a
   * black pixel.
   * @param width the width to create the image
   * @param height the height to create the image
   */
  public Image(int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height cannot be negative or zero.");
    }

    List<List<Pixel>> pixels = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      pixels.add(new ArrayList<>());
      for (int j = 0; j < width; j++) {
        pixels.get(i).add(new Pixel(0, 0, 0));
      }
    }

    this.pixels = pixels;
    this.width = width;
    this.height = height;
  }

  /**
   * Returns a copy of this image's list of pixels.
   * @return the list of pixels of the image
   */
  public List<List<Pixel>> getPixels() {
    List<List<Pixel>> copyPixels = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      copyPixels.add(new ArrayList<>());
      for (int j = 0; j < width; j++) {
        Pixel p = pixels.get(i).get(j);
        copyPixels.get(i).add(new Pixel(p.getRed(), p.getGreen(), p.getBlue()));
      }
    }
    return copyPixels;
  }

  /**
   * Gets the value of the image's width.
   * @return the width of the image
   */
  public int getWidth() {
    return width;
  }

  /**
   * Gets the value of the images height.
   * @return the height of the image
   */
  public int getHeight() {
    return height;
  }

  /**
   * Applies the given color transformation matrix onto this image.
   * @param matrix the color transformation matrix
   * @return the image that is the result of the color transformation
   */
  public Image colorTransformation(double[][] matrix) {
    List<List<Pixel>> transformedPixels = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      transformedPixels.add(new ArrayList<>());
      for (int j = 0; j < width; j++) {
        Pixel pixel = pixels.get(i).get(j);
        transformedPixels.get(i).add(pixel.colorTransformation(matrix));
      }
    }
    return new Image(transformedPixels, width, height);
  }

  /**
   * Transforms this image with the given image filter matrix.
   * @param matrix the image filter matrix
   * @return the transformed image as a result of applying the filter matrix onto this image's
   *     pixels
   */
  public Image imageFilter(double[][] matrix) {
    List<List<Pixel>> transformedPixels = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      transformedPixels.add(new ArrayList<>());
      for (int j = 0; j < width; j++) {
        double transformedRed = 0;
        double transformedGreen = 0;
        double transformedBlue = 0;
        for (int row = 0; row < matrix.length; row++) {
          for (int col = 0; col < matrix[row].length; col++) {
            if (i + row - matrix.length / 2 < 0 || j + col - matrix.length / 2 < 0) {
              transformedRed += 0;
            }
            else if (i + row - matrix.length / 2 >= height
                || j + col - matrix.length / 2 >= width) {
              transformedRed += 0;
            }
            else {
              transformedRed += matrix[row][col] *
                  pixels.get(i + row - matrix.length / 2).
                      get(j + col - matrix.length / 2).getRed();
              transformedGreen += matrix[row][col] *
                  pixels.get(i + row - matrix.length / 2).
                      get(j + col - matrix.length / 2).getGreen();
              transformedBlue += matrix[row][col] *
                  pixels.get(i + row - matrix.length / 2).
                      get(j + col - matrix.length / 2).getBlue();
            }
          }
        }
        int roundedRed = (int)Math.round(transformedRed);
        int roundedGreen = (int)Math.round(transformedGreen);
        int roundedBlue = (int)Math.round(transformedBlue);
        transformedPixels.get(i).add(new Pixel(roundedRed,roundedGreen,roundedBlue));
      }
    }

    return new Image(transformedPixels,width,height);
  }

  /**
   * Sets the pixel of this image at the given x and y value to the given pixel.
   * @param position the position of the pixel
   * @param pixel the pixel
   * @throws IllegalArgumentException if the given coordinates is not within bounds of the image
   *     or the given pixel is null
   */
  public void setPixel(Posn position, Pixel pixel) throws IllegalArgumentException {
    if (pixel == null) {
      throw new IllegalArgumentException("The pixel cannot be null.");
    }
    if (position.getX() < 0 || position.getY() < 0) {
      throw new IllegalArgumentException("The given width or height is less than 0.");
    }
    if (position.getX() >= width || position.getY() >= height) {
      throw new IllegalArgumentException("The given width or height is greater than or equal to "
          + "the image's width or height.");
    }

    pixels.get(position.getY()).set(position.getX(), new Pixel(pixel.getRed(), pixel.getGreen(),
        pixel.getBlue()));
  }

  /**
   * Gets the pixel at the specified position.
   * @param position the position to get a pixel
   * @return the pixel at the position
   */
  public Pixel getPixel(Posn position) {
    if (position == null) {
      throw new IllegalArgumentException("The position given was null.");
    }

    int x = position.getX();
    int y = position.getY();

    try {
      return new Pixel(pixels.get(y).get(x));
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("The given position is not within bounds of the image.");
    }
  }

  /**
   * Creates a downscale version of this image to the given width and height.
   * @param width the width to downscale to
   * @param height the height to downscale to
   * @return the image downscaled to the width and height
   * @throws IllegalArgumentException if the width or height are 0 or negative or the width or
   *     height is greater than this image's width and height
   */
  public Image downscale(int width, int height) throws IllegalArgumentException {
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException("Cannot downscale to a width or height that is zero or "
          + "negative.");
    }

    if (width > this.width || height > this.height) {
      throw new IllegalArgumentException("Must downscale or leave the same width and height.");
    }

    Image im = new Image(width, height);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double x = ((double) j) / width * this.width;
        double y = ((double) i) / height * this.height;
        // (0,0) -> (0,0)
        // (0,1) -> (0,4)
        // (0,2) -> (0,8)
        // (0,3) -> (0,12)

        if (Math.abs(x - Math.floor(x)) < 0.001 || Math.abs(y - Math.floor(y)) < 0.001) {
          im.setPixel(new Posn(j, i), new Pixel(getPixel(new Posn((int) x, (int) y))));
        }

        else {
          Pixel a = this.getPixel(new Posn((int) Math.floor(x), (int) Math.floor(y)));
          Pixel b = this.getPixel(new Posn((int) Math.ceil(x), (int) Math.floor(y)));
          Pixel c = this.getPixel(new Posn((int) Math.floor(x), (int) Math.ceil(y)));
          Pixel d = this.getPixel(new Posn((int) Math.ceil(x), (int) Math.ceil(y)));

          double mRed = b.getRed() * (x - Math.floor(x)) + a.getRed() * (Math.ceil(x) - x);
          double mGreen = b.getGreen() * (x - Math.floor(x)) + a.getGreen() * (Math.ceil(x) - x);
          double mBlue = b.getBlue() * (x - Math.floor(x)) + a.getBlue() * (Math.ceil(x) - x);

          double nRed = d.getRed() * (x - Math.floor(x)) + c.getRed() * (Math.ceil(x) - x);
          double nGreen = d.getGreen() * (x - Math.floor(x)) + c.getGreen() * (Math.ceil(x) - x);
          double nBlue = d.getBlue() * (x - Math.floor(x)) + c.getBlue() * (Math.ceil(x) - x);

          int colorRed = (int) Math.round(nRed * (y - Math.floor(y)) + mRed * (Math.ceil(y) - y));
          int colorGreen = (int)
              Math.round(nGreen * (y - Math.floor(y)) + mGreen * (Math.ceil(y) - y));
          int colorBlue = (int)
              Math.round(nBlue * (y - Math.floor(y)) + mBlue * (Math.ceil(y) - y));

          im.setPixel(new Posn(j, i), new Pixel(colorRed, colorGreen, colorBlue));
        }
      }
    }
    return im;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (! (o instanceof Image)) {
      return false;
    }

    Image that = (Image) o;

    if (this.width != that.width || this.height != that.height) {
      return false;
    }

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        if (!(this.pixels.get(i).get(j).equals(that.getPixels().get(i).get(j)))) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(pixels, width, height);
  }
}
