package view;

import controller.Features;
import image.Image;
import java.io.IOException;

/**
 * Test class for ImageProcessingGUIView to see if correct methods are called.
 */
public class ImageProcessingGUIViewTEST implements ImageProcessingGUIView {

  private Appendable ap;

  /**
   * Constructor for the ImageProcessingGuiViewTEST class. Assigns an appendable that is used
   * to check whether the methods are being called correctly.
   * @param ap The appendable being assigned.
   */
  public ImageProcessingGUIViewTEST(Appendable ap) {
    this.ap = ap;
  }



  public Appendable getAp() {
    return this.ap;
  }

  @Override
  public void addFeatures(Features features) {
    try {
      ap.append("Add Features\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void notifyErrorMessage(String message) {
    try {
      ap.append("Notify Error Message: " + message + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void showTopMostImage(Image im, String name) {
    try {
      ap.append("Show Top Most Image: " + name + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void addToLayerSelectionList(String name) {
    try {
      ap.append("Add to Layer SelectionList: " + name + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void removeFromLayerSelectionList(String name) {
    try {
      ap.append("Remove from Layer SelectionList: " + name + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
