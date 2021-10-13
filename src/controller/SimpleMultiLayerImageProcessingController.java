package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;
import model.MultiLayerImageProcessingModel;
import view.ImageProcessingView;
import view.SimpleMultiLayerImageProcessingView;

/**
 * Represents a controller for the multi-layer image processing application that tells the model
 * what to do and the view what to show.
 */
public class SimpleMultiLayerImageProcessingController
    implements MultiLayerImageProcessingController {

  private final MultiLayerImageProcessingModel model;
  private final ImageProcessingView view;
  private final Readable rd;
  private final Map<String, Function<Scanner, ImageProcessingCommands>> knownCommands;

  /**
   * Constructor that takes in a model, a readable and appendable object.
   * @param model the multi-layer image model
   * @param rd the readable object
   * @param ap the appendable object
   * @throws IllegalArgumentException if the given model, readable, or appendable is null
   */
  public SimpleMultiLayerImageProcessingController(MultiLayerImageProcessingModel model,
      Readable rd, Appendable ap) throws IllegalArgumentException {
    if (model == null || rd == null || ap == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    this.model = model;
    this.rd = rd;
    this.view = new SimpleMultiLayerImageProcessingView(ap);

    // createLayer "name"
    // deleteLayer "name"
    // current "name"
    // load "filepath"
    // loadProject "filepath"
    // blur, sharpen
    // save "filepath"
    // sepia, greyscale
    // invisible "name"
    // visible "name"
    // saveProject "filepath"
    // loadCheckerboard sideLength

    // quit
    this.knownCommands = new HashMap<>();

    knownCommands.put("createLayer",s -> new CreateLayer(s.next()));
    knownCommands.put("current",s -> new Current(s.next()));
    knownCommands.put("load",s -> new Load(s.next()));
    knownCommands.put("loadProject",s -> new LoadProject(s.next()));
    knownCommands.put("blur",s -> new Blur());
    knownCommands.put("sharpen",s -> new Sharpen());
    knownCommands.put("save",s -> new Save(s.next()));
    knownCommands.put("sepia",s -> new Sepia());
    knownCommands.put("greyscale",s -> new Greyscale());
    knownCommands.put("invisible",s -> new Invisible(s.next()));
    knownCommands.put("visible",s -> new Visible(s.next()));
    knownCommands.put("saveProject",s -> new SaveProject(s.next()));
    knownCommands.put("loadCheckerboard",s -> new LoadCheckerboard(s.next()));
    knownCommands.put("deleteLayer", s -> new DeleteLayer(s.next()));
  }

  @Override
  public void run() throws IllegalStateException {
    // have user choose between loading a file with scripts or entering commands interactively
    try {
      view.renderMessage("Menu:\nS: Script\nI: Interactive\nEnter your choice: ");
    } catch (IOException e) {
      throw new IllegalStateException("\nCould not write to readable.");
    }
    Scanner sc = new Scanner(rd);
    // look for choice until correct choice is entered
    boolean choiceFound = false;
    while (!choiceFound) {
      // check if there are any inputs left
      if (!sc.hasNext()) {
        throw new IllegalStateException("\nNo more inputs to read from.");
      }
      String choice = sc.next();
      switch (choice) {
        case "S":
          choiceFound = true;
          try {
            view.renderMessage("\nScript Mode\n");
          } catch (IOException e) {
            throw new IllegalStateException("\nCould not write to readable.");
          }
          goScript(sc);
          break;
        case "I":
          choiceFound = true;
          try {
            view.renderMessage("\nInteractive Mode\n");
          } catch (IOException e) {
            throw new IllegalStateException("\nCould not write to readable.");
          }
          readCommands(sc);
          break;
        default:
          try {
            view.renderMessage("\nRe-enter your choice, S for script and I for interactive: ");
          } catch (IOException e) {
            throw new IllegalStateException("\nCould not write to readable.");
          }
          break;
      }
    }

  }

  /**
   * Helper method that runs the script portion of the controller.
   * @param sc the scanner to read inputs from
   * @throws IllegalStateException if the appendable could not be written to or there are no more
   *     inputs to read from
   */
  private void goScript(Scanner sc) throws IllegalStateException {
    try {
      view.renderMessage("Enter the filepath for the script: ");
    } catch (IOException e) {
      throw new IllegalStateException("\nCould not write to appendable.");
    }


    boolean fileFound = false;
    // loops until correct filepath is entered for script
    while (!fileFound) {
      if (!sc.hasNextLine()) {
        throw new IllegalStateException("\nNo more inputs to read from.");
      }
      String filepath = sc.nextLine();
      File script = new File(filepath);
      Scanner readFile;
      try {
        readFile = new Scanner(script);
        fileFound = true;
        readCommands(readFile);
      } catch (FileNotFoundException e) {
        try {
          view.renderMessage("\nFile could not be found. Re-enter the filepath: ");
        } catch (IOException ioException) {
          throw new IllegalStateException("\nCould not write to appendable.");
        }
      }
    }

  }

  /**
   * Helper method that reads the commands that are fed into a script or read through interactively.
   * @param sc the scanner to read the script or commands given through interactively
   */
  private void readCommands(Scanner sc) {
    try {
      view.renderMessage("Enter command: ");
    } catch (IOException e) {
      throw new IllegalStateException("\nCould not write to readable.");
    }
    while (sc.hasNext()) {

      ImageProcessingCommands ipc;
      String command = sc.next();
      if (command.equals("quit")) {
        return;
      }
      Function<Scanner,ImageProcessingCommands> cmd =
          knownCommands.getOrDefault(command,null);
      if (cmd == null) {
        try {
          view.renderMessage("\nCommand does not exist" + "\nRe-enter command: ");
        } catch (IOException e) {
          throw new IllegalStateException("\nCould not write to readable.");
        }
      }
      else {
        try {
          ipc = cmd.apply(sc);
        } catch (NoSuchElementException e) {
          throw new IllegalStateException("\nNo more inputs to read from.");
        }
        try {
          ipc.run(model);
          try {
            view.renderMessage("Enter command: ");
          } catch (IOException e) {
            throw new IllegalStateException("\nCould not write to readable.");
          }
          // if the command called was to export the multi-layer project, quit the program
          if (command.equals("saveProject")) {
            return;
          }
        } catch (IllegalArgumentException e) {
          try {
            view.renderMessage("\nCommand cannot be run. "
                + e.getMessage() + "\nRe-enter command: ");
          } catch (IOException ioException) {
            throw new IllegalStateException("\nCould not write to readable.");
          }
        } catch (IllegalStateException e) {
          try {
            view.renderMessage("\nCommand cannot be run. "
                + e.getMessage()  + "\nRe-enter command: ");
          } catch (IOException ioException) {
            throw new IllegalStateException("\nCould not write to readable.");
          }
        }
      }
    }
    throw new IllegalStateException("\nNo more inputs to read from.");
  }
}
