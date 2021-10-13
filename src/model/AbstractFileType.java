package model;

import image.Image;
import image.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Represents an abstract class for an image file type. This class contains methods that are
 * shared by image file types and demands that certain methods be implemented by its subclasses.
 */
public abstract class AbstractFileType implements ImageFileType {

  @Override
  public Image importFile(String filename) throws IllegalArgumentException {
    if (filename == null) {
      throw new IllegalArgumentException("The file is null");
    }

    BufferedImage image;
    try {
      image = ImageIO.read(new File(filename));
    } catch (IOException e) {
      throw new IllegalArgumentException("Error occurred during reading");
    }

    int width = image.getWidth();
    int height = image.getHeight();
    List<List<Pixel>> pixels = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      pixels.add(new ArrayList<>());
      for (int j = 0; j < width; j++) {
        int rgbValue = image.getRGB(j,i);
        Color c = new Color(rgbValue);
        pixels.get(i).add(new Pixel(c.getRed(),c.getGreen(),c.getBlue()));
      }
    }

    return new Image(pixels,width,height);

  }

  @Override
  public abstract void writeFile(String filename, Image im) throws IllegalStateException;

}
