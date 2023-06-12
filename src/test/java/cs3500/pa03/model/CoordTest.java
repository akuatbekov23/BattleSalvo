package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.Coord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * tests for the Coord class
 */
class CoordTest {

  Coord coordinate1;
  Coord coordinate2;

  /**
   * creates instances of coordinates
   */
  @BeforeEach
  public void setup() {
    coordinate1 = new Coord(0, 0);
    coordinate2 = new Coord(15, 3);
  }

  /**
   * a test for getting the X coordinate of a Coord
   */
  @Test
  public void testX() {
    assertEquals(coordinate1.getX(), 0);
    assertEquals(coordinate2.getX(), 15);
  }

  /**
   * a test for getting the Y coordinate of a Coord
   */
  @Test
  public void testY() {
    assertEquals(coordinate1.getY(), 0);
    assertEquals(coordinate2.getY(), 3);
  }
}