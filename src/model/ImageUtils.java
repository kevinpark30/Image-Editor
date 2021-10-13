package model;

import image.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * Image utility class for reading and writing files. This code will be useful later when
 * implementing the controller.
 */
public class ImageUtils {

  /**
   * Factory method for the ImageFileType class.
   * @param fileType the file type to output
   * @return Returns the corresponding ImageFileType based on the given string.
   * @throws IllegalArgumentException If the given string is a file type that cannot be handled.
   */
  public static ImageFileType imageFileTypeFactory(String fileType) throws
      IllegalArgumentException {
    switch (fileType) {
      case "png":
        return new PNG();
      case "JPEG":
        return new JPEG();
      default:
        throw new IllegalArgumentException("Can't handle file type.");
    }
  }

  /**
   * Reads the given filename as the given file type and returns an image.
   *
   * @param filename the filepath
   * @param ift      the file type
   * @return the image
   */
  public static Image readFile(String filename, ImageFileType ift) {
    return ift.importFile(filename);
  }

  /**
   * Writes to a file with the given filename, the image to write, and the given filetype.
   *
   * @param filename the file name
   * @param im       the image to write
   * @param ift      the file type for the image
   */
  public static void writeFile(String filename, Image im, ImageFileType ift) {
    ift.writeFile(filename, im);
  }

  /**
   * Determines the file type of an image file given the filepath.
   * @param filepath the filepath to examine the file
   * @return the type of image file as a string
   * @throws IllegalArgumentException if the given filepath was null or the file was not
   *     an image file type that could be determined
   */
  public static String determineFileType(String filepath) throws IllegalArgumentException {
    if (filepath == null) {
      throw new IllegalArgumentException("The given filepath was null.");
    }

    File f = new File(filepath);
    // This creates an object that is able to decode the file format for us
    ImageInputStream i;
    try {
      i = ImageIO.createImageInputStream(f);
    } catch (IOException e) {
      throw new IllegalArgumentException("Problem with creating ImageInputStream");
    }
    // creates an iterator of all possible ImageReaders that can decode the ImageInputStream
    Iterator<ImageReader> iterator = ImageIO.getImageReaders(i);

    // the only ImageReader that can decode the ImageInputStream will be the correct format type
    // for the image
    if (iterator.hasNext()) {
      ImageReader type = iterator.next();
      try {
        return type.getFormatName();
      } catch (IOException e) {
        throw new IllegalArgumentException("Could not get format name");
      }
    }

    // if there is no ImageReader in the iterator, then there is no available ImageInputStream to
    // decode our file, file is not one of the given filetypes that ImageIO uses
    throw new IllegalArgumentException("No file type found.");
  }

  /**
   * Determines if the file in the given filepath is a PPM file.
   * @param filepath the given filepath
   * @return whether the file in the filepath is a PPM or not
   */
  public static boolean isPPM(String filepath) {
    ImageFileType ppm = new PPM();
    try {
      ppm.importFile(filepath);
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }
}
