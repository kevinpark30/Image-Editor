import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;
import view.FailedAppendable;
import view.SimpleMultiLayerImageProcessingView;

/**
 * Testing class for the SimpleMultiLayerImageProcessingView class.
 */
public class SimpleMultiLayerImageProcessingViewTest {

  // test renderMessage() with failed appendable
  @Test(expected = IOException.class)
  public void testRenderMessageFailedAppendable() throws IOException {
    SimpleMultiLayerImageProcessingView view =
        new SimpleMultiLayerImageProcessingView(new FailedAppendable());
    view.renderMessage("Hello");
  }

  // test renderMessage()
  @Test
  public void testRenderMessage() throws IOException {
    StringBuilder sb = new StringBuilder();
    SimpleMultiLayerImageProcessingView view =
        new SimpleMultiLayerImageProcessingView(sb);
    view.renderMessage("Hello");
    assertEquals(sb.toString(),"Hello");
  }
}
