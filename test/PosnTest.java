import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import model.Posn;
import org.junit.Test;

/**
 * Testing class for the Posn class.
 */
public class PosnTest {

  //tests the getX method
  @Test
  public void testGetX() {
    Posn position = new Posn(3, 4);

    assertEquals(3, position.getX());
  }

  //tests the getY method
  @Test
  public void testGetY() {
    Posn position = new Posn(3, 4);

    assertEquals(4, position.getY());
  }

  //tests the distanceTo method
  @Test
  public void testDistanceTo() {
    Posn position1 = new Posn(3, 4);
    Posn position2 = new Posn(0, 0);

    assertEquals(5, position1.distanceTo(position2), 0.001);
  }

  //tests the getIndexOfClosestPosition method
  @Test
  public void testGetIndexOfClosestPosition() {
    List<Posn> positions = new ArrayList<>();
    Posn position = new Posn(0, 0);
    Posn position1 = new Posn(3, 4);
    Posn position2 = new Posn(10, 10);
    positions.add(position1);
    positions.add(position2);
    assertEquals(0, position.getIndexOfClosestPosition(positions));
  }

  //tests that Posn objects are extensionally equal
  @Test
  public void testEquals() {
    Posn position1 = new Posn(3, 4);
    Posn position2 = new Posn(3, 4);

    assertEquals(position1, position2);
  }

  //tests that extensionally equal Posn objects have the same hashCode
  @Test
  public void testHashCode() {
    Posn position1 = new Posn(3, 4);
    Posn position2 = new Posn(3, 4);
    assertEquals(position1.hashCode(), position2.hashCode());
  }
}