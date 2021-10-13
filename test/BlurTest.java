import static org.junit.Assert.assertEquals;

import controller.Blur;
import image.Image;
import image.Pixel;
import java.util.ArrayList;
import java.util.List;
import model.MultiLayerImageProcessingModel;
import model.SimpleMultiLayerImageProcessingModel;
import org.junit.Test;

/**
 * Test class for the Blur command.
 */
public class BlurTest {

  // tests go
  @Test
  public void testGo() {
    MultiLayerImageProcessingModel m = new SimpleMultiLayerImageProcessingModel();
    m.createLayer("first");
    m.makeCheckerBoard(3);
    new Blur().run(m);

    List<List<Pixel>> expectedPixels = new ArrayList<>();
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());

    expectedPixels.get(0).add(new Pixel(64, 64, 64));
    expectedPixels.get(0).add(new Pixel(96, 96, 96));
    expectedPixels.get(0).add(new Pixel(64, 64, 64));

    expectedPixels.get(1).add(new Pixel(96, 96, 96));
    expectedPixels.get(1).add(new Pixel(128, 128, 128));
    expectedPixels.get(1).add(new Pixel(96, 96, 96));

    expectedPixels.get(2).add(new Pixel(64, 64, 64));
    expectedPixels.get(2).add(new Pixel(96, 96, 96));
    expectedPixels.get(2).add(new Pixel(64, 64, 64));

    Image expectedImage = new Image(expectedPixels, 3, 3);
    assertEquals(m.exportImage(), expectedImage);

  }

}
