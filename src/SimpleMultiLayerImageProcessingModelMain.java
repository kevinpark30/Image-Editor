import controller.MultiLayerImageProcessingController;
import controller.SimpleMultiLayerImageProcessingController;
import java.io.InputStreamReader;
import model.EnhancedMultiLayerImageProcessingModel;
import model.SimpleEnhancedMultiLayerImageProcessingModel;


/**
 * Class to run the simple multi layer image processing application.
 */
public class SimpleMultiLayerImageProcessingModelMain {

  /**
   * Main method that runs our multi-layer image processing application. This program does not make
   * any use of commandline arguments.
   * @param args the command line args
   */
  public static void main(String[] args) {
    EnhancedMultiLayerImageProcessingModel model = new
        SimpleEnhancedMultiLayerImageProcessingModel();
    MultiLayerImageProcessingController controller =
        new SimpleMultiLayerImageProcessingController(model, new InputStreamReader(System.in),
            System.out);
    controller.run();

  }
}
