package view;

import java.io.IOException;

/**
 * Represents the view of a multi-layer image processing application that is able to send
 * messages given to it.
 */
public class SimpleMultiLayerImageProcessingView implements ImageProcessingView {

  private final Appendable ap;

  /**
   * Constructor that initializes the given appendable to the object.
   * @param ap the given appendable to write to
   */
  public SimpleMultiLayerImageProcessingView(Appendable ap) {
    this.ap = ap;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    ap.append(message);
  }
}
