package controller;

import model.ImageUtils;
import model.JPEG;
import model.MultiLayerImageProcessingModel;
import model.PNG;
import model.PPM;

/**
 * The save command function object used in the command pattern for the controller.
 */
public class Save implements ImageProcessingCommands {

  private final String filepath;

  /**
   * Creates a save function object with the given name.
   * @param filepath the filepath
   */
  public Save(String filepath) {
    if (filepath == null) {
      throw new IllegalArgumentException("Filepath must be given.");
    }
    this.filepath = filepath;
  }

  @Override
  public void run(MultiLayerImageProcessingModel m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("The model given was null.");
    }
    // this checks if the file has a name, because if the last four letters are the extension type,
    // there is no room for a file name
    if (filepath.length() < 5) {
      throw new IllegalArgumentException("File must have a name.");
    }

    if (filepath.substring(filepath.length() - 4,filepath.length()).equals(".ppm")) {
      ImageUtils.writeFile(filepath.substring(0,filepath.length() - 4),m.exportImage(),new PPM());
    }
    else if (filepath.substring(filepath.length() - 4,filepath.length()).equals(".jpg")) {
      ImageUtils.writeFile(filepath,m.exportImage(),new JPEG());
    }
    else if (filepath.substring(filepath.length() - 4,filepath.length()).equals(".png")) {
      ImageUtils.writeFile(filepath,m.exportImage(),new PNG());
    }
    else {
      throw new IllegalArgumentException("Invalid filetype.");
    }

  }

}
