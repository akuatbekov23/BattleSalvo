package cs3500.pa03.controller;

import cs3500.pa04.controller.ShipController;
import cs3500.pa04.model.Board;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Ship;
import cs3500.pa04.model.ShipType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents tests for the ship controller
 */
public class ShipControllerTest {

  private ShipController shipController;
  private List<Ship> ships;

  @BeforeEach
  public void setUp() {
    shipController = new ShipController();
    ships = new ArrayList<>();
  }

  @Test
  public void testSetShotsAllSunk() {
    Ship ship1 = new Ship(ShipType.BATTLESHIP, new ArrayList<>());
    Ship ship2 = new Ship(ShipType.CARRIER, new ArrayList<>());
    Ship ship3 = new Ship(ShipType.DESTROYER, new ArrayList<>());

    ship1.makeSunk();
    ship2.makeSunk();
    ship3.makeSunk();

    ships.add(ship1);
    ships.add(ship2);
    ships.add(ship3);

    int shotCount = shipController.setShots(ships);

    Assertions.assertEquals(0, shotCount);
  }

  @Test
  public void testSetShotsMixedStatus() {
    Ship ship1 = new Ship(ShipType.BATTLESHIP, new ArrayList<>());
    Ship ship2 = new Ship(ShipType.CARRIER, new ArrayList<>());
    Ship ship3 = new Ship(ShipType.DESTROYER, new ArrayList<>());

    ship1.makeSunk();
    ship2.unSunk();
    ship3.makeSunk();

    ships.add(ship1);
    ships.add(ship2);
    ships.add(ship3);

    int shotCount = shipController.setShots(ships);

    Assertions.assertEquals(1, shotCount);
  }

  @Test
  public void testUpdateShipAllSunk() {

    Ship ship1 = new Ship(ShipType.SUBMARINE, new ArrayList<>());
    Ship ship2 = new Ship(ShipType.DESTROYER, new ArrayList<>());

    ship1.setPosition(List.of(new Coord(0, 0), new Coord(0, 1),
        new Coord(0, 2)));
    ship2.setPosition(List.of(new Coord(1, 1), new Coord(1, 2),
        new Coord(1, 3), new Coord(1, 4)));

    ships.add(ship1);
    ships.add(ship2);

    Board board = new Board(10, 10);
    // Simulate all ships being hit and sunk
    board.addHitSpot(new Coord(0, 0));
    board.addHitSpot(new Coord(0, 1));
    board.addHitSpot(new Coord(0, 2));
    board.addHitSpot(new Coord(1, 1));
    board.addHitSpot(new Coord(1, 2));
    board.addHitSpot(new Coord(1, 3));
    board.addHitSpot(new Coord(1, 4));
    board.addHitSpot(new Coord(2, 2));
    board.addHitSpot(new Coord(2, 3));

    shipController.updateShip(ships, board);

    // Assert that all ships are marked as sunk
    for (Ship ship : ships) {
      Assertions.assertTrue(ship.isSunk());
    }
  }

  @Test
  public void testUpdateShipNotAllSunk() {

    Ship ship1 = new Ship(ShipType.SUBMARINE, new ArrayList<>());
    Ship ship2 = new Ship(ShipType.DESTROYER, new ArrayList<>());


    ship1.setPosition(List.of(new Coord(0, 0), new Coord(0, 1),
        new Coord(0, 2)));
    ship2.setPosition(List.of(new Coord(1, 1), new Coord(1, 2),
        new Coord(1, 3), new Coord(1, 4)));


    ships.add(ship1);
    ships.add(ship2);

    Board board = new Board(10, 10);
    // Simulate some ships being hit and sunk
    board.addHitSpot(new Coord(0, 0));
    board.addHitSpot(new Coord(0, 1));
    board.addHitSpot(new Coord(0, 2));
    board.addHitSpot(new Coord(1, 1));
    board.addHitSpot(new Coord(1, 2));
    board.addHitSpot(new Coord(1, 3));

    shipController.updateShip(ships, board);

    // Assert that only the first ship is marked as not sunk


  }
}
