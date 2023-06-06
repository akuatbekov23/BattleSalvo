package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents tests for the ShipType class
 */
class ShipTypeTest {
  ShipType carrier;
  ShipType battleship;
  ShipType destroyer;
  ShipType submarine;

  /**
   * sets up examples of different ShipTypes
   */
  @BeforeEach
  public void setup() {
    carrier = ShipType.CARRIER;
    battleship = ShipType.BATTLESHIP;
    destroyer = ShipType.DESTROYER;
    submarine = ShipType.SUBMARINE;
  }

  /**
   * tests the getter for getting the size of a ship type
   */
  @Test
  public void testSize() {
    assertEquals(carrier.getSize(), 6);
    assertEquals(battleship.getSize(), 5);
    assertEquals(destroyer.getSize(), 4);
    assertEquals(submarine.getSize(), 3);
  }
}