package cs3500.pa03.model;

import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Ship;
import cs3500.pa04.model.ShipData;
import cs3500.pa04.model.ShipType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShipDataTest {

  @Test
  public void testRepresentData() {
    String[][] gameBoard = {
        {"C ", "= ", "= ", "= "},
        {"B ", "B ", "= ", "= "},
        {"= ", "D ", "D ", "D "},
        {"S ", "S ", "S ", "= "}
    };

    Ship ship1 = new Ship(ShipType.CARRIER, new ArrayList<>());

    ArrayList<Ship> allShips = new ArrayList<>();

    allShips.add(ship1);

    ShipData shipData = new ShipData();

    List<Ship> allShips1 = shipData.representData(gameBoard, "C ", allShips);
    ArrayList<Coord> shipCoords = new ArrayList<>();

    Ship expectedShip = ship1;

    expectedShip.addCoord(new Coord(0, 0));
    expectedShip.addCoord(new Coord(0, 1));
    expectedShip.addCoord(new Coord(0, 2));
    expectedShip.addCoord(new Coord(0, 3));

    Assertions.assertEquals(1, allShips1.size());
    Assertions.assertEquals(expectedShip, allShips1.get(0));
  }
}
