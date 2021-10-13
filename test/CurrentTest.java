import static org.junit.Assert.assertEquals;

import controller.Current;
import image.Image;
import image.Pixel;
import java.util.ArrayList;
import java.util.List;
import model.MultiLayerImageProcessingModel;
import model.SimpleMultiLayerImageProcessingModel;
import org.junit.Test;

/**
 * Test class for the current command.
 */
public class CurrentTest {

  // tests go
  @Test
  public void testGo() {
    MultiLayerImageProcessingModel model = new SimpleMultiLayerImageProcessingModel();
    model.createLayer("First");
    model.createLayer("Second");
    new Current("First").run(model);
    model.makeCheckerBoard(3);
    model.setInvisible("Second");

    List<List<Pixel>> expectedPixels = new ArrayList<>();
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());
    expectedPixels.add(new ArrayList<>());

    expectedPixels.get(0).add(new Pixel(0,0,0));
    expectedPixels.get(0).add(new Pixel(255,255,255));
    expectedPixels.get(0).add(new Pixel(0,0,0));

    expectedPixels.get(1).add(new Pixel(255,255,255));
    expectedPixels.get(1).add(new Pixel(0,0,0));
    expectedPixels.get(1).add(new Pixel(255,255,255));

    expectedPixels.get(2).add(new Pixel(0,0,0));
    expectedPixels.get(2).add(new Pixel(255,255,255));
    expectedPixels.get(2).add(new Pixel(0,0,0));

    Image expectedImage = new Image(expectedPixels, 3, 3);

    assertEquals(model.exportImage(),expectedImage);

  }

}
