package view;

import java.io.IOException;

/**
 * View for an image processing application that is able to send messeages.
 */
public interface ImageProcessingView {

  /**
   * Renders the given message.
   * @param message the message to be rendered
   * @throws IllegalArgumentException if the given message is null
   */
  void renderMessage(String message) throws IOException;




}
