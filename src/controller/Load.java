package controller;

import model.ImageUtils;
import model.MultiLayerImageProcessingModel;
import model.PPM;

/**
 * The load command function object used in the command pattern for the controller.
 */
public class Load implements ImageProcessingCommands {

  private final String filepath;

  /**
   * Creates a load function object with the given filepath.
   * @param filepath the filepath
   */
  public Load(String filepath) {
    if (filepath == null) {
      throw new IllegalArgumentException("Filepath must be given.");
    }

    this.filepath = filepath;
  }

  @Override
  public void run(MultiLayerImageProcessingModel m) {
    if (m == null) {
      throw new IllegalArgumentException("The model given was null.");
    }
    if (ImageUtils.isPPM(filepath)) {
      m.importImage(ImageUtils.readFile(filepath,new PPM()));
    }
    else {
      String fileType = ImageUtils.determineFileType(filepath);
      m.importImage(ImageUtils.readFile(filepath,ImageUtils.imageFileTypeFactory(fileType)));
    }
  }
}
