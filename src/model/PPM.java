package model;

import image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import image.Pixel;

/**
 * Represents the PPM image file type.
 */
public class PPM implements ImageFileType {

  private final Appendable ap;

  /**
   * Default constructor for the PPM class. Assigns appendable to null if none is provided.
   */
  public PPM() {
    this.ap = null;
  }

  /**
   * Constructor used only for testing purposes.
   * @param ap Given appendable that will keep track of the string in writeFile()
   */
  public PPM(Appendable ap) {
    this.ap = ap;
  }


  @Override
  public Image importFile(String filename) throws IllegalArgumentException {
    if (filename == null) {
      throw new IllegalArgumentException("The file is null");
    }

    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("The file was not found.");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;

    int width;
    int height;
    int maxValue;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("PPM file must contain P3.");
    }

    try {
      width = sc.nextInt();
      validRange(width, 1, Integer.MAX_VALUE);
    } catch (InputMismatchException e) {
      throw new IllegalArgumentException("Malformed PPM, expected width.");
    }

    try {
      height = sc.nextInt();
      validRange(height, 1, Integer.MAX_VALUE);
    } catch (InputMismatchException e) {
      throw new IllegalArgumentException("Malformed PPM, expected height.");
    }

    try {
      maxValue = sc.nextInt();
      validRange(maxValue, 0, Integer.MAX_VALUE);
    } catch (InputMismatchException e) {
      throw new IllegalArgumentException("Malformed PPM, expected max-channel value.");
    }

    List<List<Pixel>> pixels = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      pixels.add(new ArrayList<>());
      for (int j = 0;j < width; j++) {
        int r;
        int g;
        int b;

        try {
          r = sc.nextInt();
          validRange(r, 0, maxValue);
        } catch (InputMismatchException e) {
          throw new IllegalArgumentException("Channel is not an integer.");
        }

        try {
          g = sc.nextInt();
          validRange(g, 0, maxValue);
        } catch (InputMismatchException e) {
          throw new IllegalArgumentException("Channel is not an integer.");
        }

        try {
          b = sc.nextInt();
          validRange(b, 0, maxValue);
        } catch (InputMismatchException e) {
          throw new IllegalArgumentException("Channel is not an integer.");
        }

        pixels.get(i).add(new Pixel(r, g, b));
      }
    }

    validImage(pixels, width, height);

    return new Image(pixels, width, height);
  }


  @Override
  public void writeFile(String filename, Image im)
      throws IllegalStateException {
    // check if appendable is null
    // for testing purposes append to given appendable in class
    if (ap != null) {
      try {
        ap.append("P3\n");
        ap.append(im.getWidth() + " " + im.getHeight() + "\n");
        ap.append("255\n");

        List<List<Pixel>> pixels = im.getPixels();
        // loop over pixel list in image
        for (int i = 0; i < im.getHeight(); i++) {
          for (int j = 0; j < im.getWidth(); j++) {
            // get current pixel
            Pixel currPixel = pixels.get(i).get(j);
            ap.append(currPixel.getRed() + "\n");
            ap.append(currPixel.getGreen() + "\n");
            ap.append(currPixel.getBlue() + "\n");
          }
        }
      } catch (IOException e) {
        throw new IllegalStateException("Invalid appendable");
      }

      return;
    }

    // create file and writer for the file
    PrintWriter pw;
    try {
      pw = new PrintWriter(filename + ".ppm");
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("File could not be created.");
    }

    // add P3, width, height, and maxValue
    pw.println("P3");
    pw.println(im.getWidth() + " " + im.getHeight());
    pw.println(255);

    List<List<Pixel>> pixels = im.getPixels();
    // loop over pixel list in image
    for (int i = 0; i < im.getHeight(); i++) {
      for (int j = 0; j < im.getWidth(); j++) {
        // get current pixel
        Pixel currPixel = pixels.get(i).get(j);
        pw.println(currPixel.getRed());
        pw.println(currPixel.getGreen());
        pw.println(currPixel.getBlue());
      }
    }


    pw.close();
  }

  /**
   * Getter for the appendable field in the class.
   * @return Returns the appendable field.
   */
  public Appendable getAp() {
    return ap;
  }

  /**
   * Checks that the given value is greater than or equal to the given minimum value and is less
   * than or equal to the maximum value.
   * @param value the value to check
   * @param min the minimum value allowed
   * @param max the maximum value allowed
   * @throws IllegalArgumentException if the value is not greater than or equal to the given
   *     minimum value or is not less than or equal to the maximum value
   */
  private static void validRange(int value, int min, int max) throws IllegalArgumentException {
    if (value < min) {
      throw new IllegalArgumentException("Invalid value.");
    }
    if (value > max) {
      throw new IllegalArgumentException("Invalid value.");
    }
  }

  /**
   * Checks that the size of the image is correct with the given width and height.
   * @param pixels the pixels of the image
   * @param width the width of the image in pixels
   * @param height the height of the image in pixels
   * @throws IllegalArgumentException if the size of the image is not correct
   */
  private void validImage(List<List<Pixel>> pixels, int width, int height) {
    if (pixels.size() != height) {
      throw new IllegalArgumentException("The height of the image is invalid.");
    }

    for (List<Pixel> lop : pixels) {
      if (lop.size() != width) {
        throw new IllegalArgumentException("The width of the image is invalid.");
      }
    }
  }
}
