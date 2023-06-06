package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents tests for the Ship class
 */
class ShipTest {
  Ship sunkCarrier;
  Ship floatingCarrier;
  Ship sunkBattleShip;
  Ship floatingBattleShip;
  Ship sunkDestroyer;
  Ship floatingDestroyer;
  Ship sunkSubmarine;
  Ship floatingSubmarine;
  ArrayList<Coord> emptyList = new ArrayList<>();

  /**
   * creates instances of ships
   */
  @BeforeEach
  public void setup() {
    sunkCarrier = new Ship(ShipType.CARRIER, emptyList);
    sunkCarrier.makeSunk();
    floatingCarrier = new Ship(ShipType.CARRIER, emptyList);
    sunkBattleShip = new Ship(ShipType.BATTLESHIP, emptyList);
    sunkBattleShip.makeSunk();
    floatingBattleShip = new Ship(ShipType.BATTLESHIP, emptyList);
    sunkDestroyer = new Ship(ShipType.DESTROYER, emptyList);
    sunkDestroyer.makeSunk();
    floatingDestroyer = new Ship(ShipType.DESTROYER, emptyList);
    sunkSubmarine = new Ship(ShipType.SUBMARINE, emptyList);
    sunkSubmarine.makeSunk();
    floatingSubmarine = new Ship(ShipType.SUBMARINE, emptyList);
    List<Coord> coords = floatingSubmarine.getPosition();
    coords.add(new Coord(0, 0));
    coords.add(new Coord(1, 1));
  }

  /**
   * tests whether a ship is sunk or not
   */
  @Test
  public void testSunk() {
    assertTrue(sunkCarrier.isSunk());
    assertTrue(sunkDestroyer.isSunk());
    assertTrue(sunkBattleShip.isSunk());
    assertTrue(sunkSubmarine.isSunk());
    assertFalse(floatingCarrier.isSunk());
    assertFalse(floatingBattleShip.isSunk());
    assertFalse(floatingDestroyer.isSunk());
    assertFalse(floatingSubmarine.isSunk());
  }

  /**
   * tests whether the type of a Ship is returned
   */
  @Test
  public void testGetType() {
    assertEquals(sunkCarrier.getType(), ShipType.CARRIER);
    assertEquals(floatingCarrier.getType(), ShipType.CARRIER);
    assertEquals(sunkBattleShip.getType(), ShipType.BATTLESHIP);
    assertEquals(floatingBattleShip.getType(), ShipType.BATTLESHIP);
    assertEquals(sunkDestroyer.getType(), ShipType.DESTROYER);
    assertEquals(floatingDestroyer.getType(), ShipType.DESTROYER);
    assertEquals(sunkSubmarine.getType(), ShipType.SUBMARINE);
    assertEquals(floatingSubmarine.getType(), ShipType.SUBMARINE);
  }

  /**
   * tests for getting the position of a ship
   */
  @Test
  public void testGetPosition() {
    assertEquals(sunkCarrier.getPosition(), emptyList);
    List<Coord> comparison = new ArrayList<>(Arrays.asList(new Coord(0, 0),
        new Coord(1, 1)));
    List<Coord> result = floatingSubmarine.getPosition();
    Coord coord1 = result.get(0);
    Coord coord2 = comparison.get(0);
    Coord coord3 = result.get(1);
    Coord coord4 = result.get(1);
    assertEquals(coord1.getX(), coord2.getX());
    assertEquals(coord1.getY(), coord2.getY());
    assertEquals(coord3.getX(), coord4.getX());
    assertEquals(coord3.getY(), coord4.getY());
    assertEquals(result.size(), comparison.size());
  }

}