import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import image.Image;
import image.Pixel;
import java.util.ArrayList;
import java.util.List;
import model.Layer;
import org.junit.Test;

/**
 * Testing class for the layer class.
 */
public class LayerTest {

  //tests that the constructor throws an exception if a null name is passed
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullName() {
    new Layer(null);
  }

  //tests that the loadImage method throws an exception if a null image is passed to it
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageNullImage() {
    Layer l = new Layer("First");
    l.loadImage(null);
  }

  //tests that the loadImage method properly loads an image in
  @Test
  public void testLoadImage() {
    Layer l = new Layer("First");
    List<List<Pixel>> pixels = new ArrayList<>();
    pixels.add(new ArrayList<>());
    pixels.get(0).add(new Pixel(0, 0, 0));
    Image im = new Image(pixels, 1 , 1);
    l.loadImage(im);
    assertEquals(im, l.getImage());
  }

  //tests that the setInvisible method works
  @Test
  public void testSetInvisible() {
    Layer l = new Layer("First");
    l.setInvisible();
    assertFalse(l.getVisibility());
  }

  //tests that the setVisible method works
  @Test
  public void testSetVisible() {
    Layer l = new Layer("First");
    l.setInvisible();
    l.setVisible();
    assertTrue(l.getVisibility());
  }

  //tests that the getImage method throws an exception when trying to get an image for a layer
  //that has not yet loaded an image in
  @Test(expected = IllegalStateException.class)
  public void testGetImageNoImageLoadedInYet() {
    Layer l = new Layer("First");
    l.getImage();
  }

  //tests that the getImage method returns the image that is loaded into the layer
  @Test
  public void testGetImage() {
    Layer l = new Layer("First");
    List<List<Pixel>> pixels = new ArrayList<>();
    pixels.add(new ArrayList<>());
    pixels.get(0).add(new Pixel(0, 0, 0));
    Image im = new Image(pixels, 1 , 1);
    l.loadImage(im);
    assertEquals(im, l.getImage());
  }

  //tests that the getVisibility returns the proper visibility (layers are at default visible)
  @Test
  public void testGetVisibility() {
    Layer l = new Layer("First");
    assertTrue(l.getVisibility());
  }

  //tests that the getName returns the correct name of the layer
  @Test
  public void testGetName() {
    Layer l = new Layer("First");
    assertEquals("First", l.getName());
  }
}