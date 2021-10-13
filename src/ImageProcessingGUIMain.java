import controller.ImageProcessingGUIController;
import controller.MultiLayerImageProcessingController;
import controller.SimpleMultiLayerImageProcessingController;
import java.io.InputStreamReader;
import java.io.StringReader;
import model.EnhancedMultiLayerImageProcessingModel;
import model.SimpleEnhancedMultiLayerImageProcessingModel;
import view.ImageProcessingGUIView;
import view.SimpleImageProcessingGUIView;

/**
 * Class with a main method that runs the multi-layer image processing GUI application.
 */
public class ImageProcessingGUIMain {

  /**
   * Runs the image processing application where the user can run a script, run text interactive
   * mode, or GUI mode.
   * @param args flags to specify the type of mode and additional arguments such as script path
   */
  public static void main(String[] args) {
    if (args.length > 2) {
      System.out.println("Not a valid command.");
      return;
    }

    if (args[0].equals("-script")) {
      if (args.length == 1) {
        System.out.println("No file path for the script was given.");
        return;
      }

      EnhancedMultiLayerImageProcessingModel model =
          new SimpleEnhancedMultiLayerImageProcessingModel();
      MultiLayerImageProcessingController controller = new
          SimpleMultiLayerImageProcessingController(model, new StringReader("S \n" + args[1]),
          new StringBuilder());
      controller.run();
      return;
    }

    else if (args[0].equals("-text")) {
      if (args.length == 2) {
        System.out.println("Not a valid command");
        return;
      }
      EnhancedMultiLayerImageProcessingModel model =
          new SimpleEnhancedMultiLayerImageProcessingModel();
      MultiLayerImageProcessingController controller = new
          SimpleMultiLayerImageProcessingController(model, new InputStreamReader(System.in),
          System.out);
      controller.run();
      return;
    }

    else if (args[0].equals("-interactive")) {
      if (args.length == 2) {
        System.out.println("Not a valid command");
        return;
      }
      EnhancedMultiLayerImageProcessingModel model = new
          SimpleEnhancedMultiLayerImageProcessingModel();
      ImageProcessingGUIController controller = new ImageProcessingGUIController(model);
      // Will need an interface for the view
      ImageProcessingGUIView view = new SimpleImageProcessingGUIView("Image Processing");
      controller.setView(view);
      return;
    }

    else {
      System.out.println("Not a valid command.");
      return;
    }
  }
}
