package cs3500.pa03.model;

import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Ship;
import cs3500.pa04.model.ShipData;
import cs3500.pa04.model.ShipType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * represents tests for ship data
 */
public class ShipDataTest {

  /**
   * a test for representing the data on a board
   */
  @Test
  public void testRepresentData() {

    Ship ship1 = new Ship(ShipType.CARRIER, new ArrayList<>());

    ArrayList<Ship> allShips = new ArrayList<>();

    allShips.add(ship1);

    ShipData shipData = new ShipData();

    Ship expectedShip = ship1;

    expectedShip.addCoord(new Coord(0, 0));
    expectedShip.addCoord(new Coord(0, 1));
    expectedShip.addCoord(new Coord(0, 2));
    expectedShip.addCoord(new Coord(0, 3));

    String[][] gameBoard = {
        {"C ", "= ", "= ", "= "},
        {"B ", "B ", "= ", "= "},
        {"= ", "D ", "D ", "D "},
        {"S ", "S ", "S ", "= "}
    };

    List<Ship> allShips1 = shipData.representData(gameBoard, "C ", allShips);

    Assertions.assertEquals(1, allShips1.size());
    Assertions.assertEquals(expectedShip, allShips1.get(0));
  }
}
