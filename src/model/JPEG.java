package model;

import image.Image;
import image.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Represents the JPEG file type.
 */
public class JPEG extends AbstractFileType {

  @Override
  public void writeFile(String filename, Image im) throws IllegalStateException {
    if (filename == null || im == null) {
      throw new IllegalArgumentException("The file or image is null.");
    }

    int width = im.getWidth();
    int height = im.getHeight();
    List<List<Pixel>> pixels = im.getPixels();

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel p = pixels.get(i).get(j);
        Color c = new Color(p.getRed(),p.getGreen(),p.getBlue());
        image.setRGB(j,i,c.getRGB());
      }
    }
    try {
      ImageIO.write(image,"jpeg",new File(filename));
    } catch (IOException e) {
      new IllegalArgumentException("Error occurred while writing");
    }
  }
}
