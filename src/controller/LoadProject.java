package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import model.MultiLayerImageProcessingModel;
import model.SimpleMultiLayerImageProcessingModel;

/**
 * The loadProject command function object used in the command pattern for the controller.
 */
public class LoadProject implements ImageProcessingCommands {

  private final String filepath;

  /**
   * Creates a LoadProject command object with the given filepath.
   * @param filepath the filepath
   * @throws IllegalArgumentException if the filepath is null
   */
  public LoadProject(String filepath) throws IllegalArgumentException {
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
    File f = new File(filepath + "/" + "LayerAttributes.txt");
    Scanner sc;
    try {
      sc = new Scanner(f);
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Text file cannot be found.");
    }


    List<String> filePaths = new ArrayList<>();
    List<String> fileNames = new ArrayList<>();
    List<Boolean> fileVisibility = new ArrayList<>();
    while (sc.hasNextLine()) {
      String line1;
      String line2;
      String line3;
      try {
        line1 = sc.nextLine();
        line2 = sc.nextLine();
        line3 = sc.nextLine();
      } catch (NoSuchElementException e) {
        throw new IllegalArgumentException("Text file is malformed.");
      }
      String filePath = line1;
      String name = line2;
      String visibility = line3;

      if (!visibility.equals("true") && !visibility.equals("false")) {
        System.out.println(visibility);
        throw new IllegalArgumentException("Visibility needs to be true or false.");
      }

      filePaths.add(filePath);
      fileNames.add(name);
      fileVisibility.add(Boolean.valueOf(visibility));
    }


    SimpleMultiLayerImageProcessingModel tempModel = new SimpleMultiLayerImageProcessingModel();

    for (int i = 0; i < filePaths.size(); i++) {
      tempModel.createLayer(fileNames.get(i));
      try {
        new Load(filePaths.get(i)).run(tempModel);
      }
      catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("File path was invalid");
      }
      if (fileVisibility.get(i)) {
        tempModel.setVisible(fileNames.get(i));
      }
      else {
        tempModel.setInvisible(fileNames.get(i));
      }
    }


    m.reset(tempModel);


  }

}
