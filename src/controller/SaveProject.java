package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.ImageUtils;
import model.Layer;
import model.MultiLayerImageProcessingModel;
import model.PPM;

/**
 * The saveProject command function object used in the command pattern for the controller.
 */
public class SaveProject implements ImageProcessingCommands {

  private final String filepath;

  /**
   * Creates a saveProject function object with the given name.
   * @param filepath the filepath
   */
  public SaveProject(String filepath) {
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
    List<Layer> layers = m.getLayers();
    List<String> filePaths = new ArrayList<>();
    List<String> fileNames = new ArrayList<>();
    List<Boolean> fileVisibility = new ArrayList<>();
    File f = new File(filepath);
    f.mkdir();


    for (Layer l : layers) {
      try {
        l.getImage();
        String path = f.getPath() + "/" + l.getName();
        ImageUtils.writeFile(path,l.getImage(),new PPM());
        filePaths.add(path);
        fileNames.add(l.getName());
        fileVisibility.add(l.getVisibility());
      }
      catch (IllegalStateException e) {
        //do nothing
      }
    }

    PrintWriter pw;
    try {
      pw = new PrintWriter(f.getPath() + "/" + "LayerAttributes.txt");

    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File could not be found");
    }

    // store the layer filepath, name, and visibility in the text file
    for (int i = 0; i < filePaths.size(); i++) {
      pw.println(filePaths.get(i) + ".ppm");
      pw.println(fileNames.get(i));
      pw.println(fileVisibility.get(i));
    }

    pw.close();


  }
}
