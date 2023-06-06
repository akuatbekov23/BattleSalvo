package cs3500.pa03.controller;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import java.util.ArrayList;
import java.util.List;

/**
 * a controller that validates all user input
 */
public class ValidatorController {

  /**
   * @param size either the length or width of the board
   * @return boolean representing whether the given size is valid
   */
  public boolean validateSize(int size) {
    return size > 5 && size < 15;
  }

  /**
   * @param maxShips    the total allowed amount of ships
   * @param shipAmounts an array of the amounts of each ships
   * @return a boolean whether the fleet size is valid
   */
  public boolean validateFleetSize(int maxShips, int[] shipAmounts) {
    int carrier = shipAmounts[0];
    int bship = shipAmounts[1];
    int destroyer = shipAmounts[2];
    int sub = shipAmounts[3];
    int totalShips = carrier + bship + destroyer + sub;
    boolean carrierZero = carrier == 0;
    boolean bshipZero = bship == 0;
    boolean destroyerZero = destroyer == 0;
    boolean subZero = sub == 0;
    boolean anyZero = carrierZero || bshipZero || destroyerZero || subZero;
    return (totalShips > maxShips || anyZero);
  }

  /**
   * @param coord       the coordinate to validate that it's in bounds and hasn't been hit already
   * @param seenAiBoard the board used to validate the coord
   * @return a boolean on whether the coordinate is valid
   */
  public boolean invalidSpot(Coord coord, Board seenAiBoard) {
    int coordX = coord.getX();
    int coordY = coord.getY();
    String[][] gameBoard = seenAiBoard.getGameBoard();
    int height = gameBoard.length;
    int width = gameBoard[0].length;
    return coordX > height || coordX > width || coordY > height || coordY > width;
  }

  /**
   * @param coord the coordinate to validate
   * @param seenAiBoard the board used to validate the coordinate
   * @return whether the space has been hit
   */
  public boolean beenHit(Coord coord, Board seenAiBoard) {
    List<Coord> hitAlready = seenAiBoard.getHitSpots();
    boolean hit = false;
    for (Coord c : hitAlready) {
      if (coord.equals(c)) {
        hit = true;
        break;
      }
    }
    return hit;
  }

  /**
   * @param coord the coordinate to validate
   * @param pendingShots the list of pending shots
   * @return whether the coordinate has already been asked for
   */
  public boolean isDupe(Coord coord, ArrayList<Coord> pendingShots) {

    ArrayList<Boolean> alreadyContains = new ArrayList<>();

    for (Coord c : pendingShots) {
      int coordX = c.getX();
      int coordY = c.getY();
      if (coordX == coord.getX() && coordY == coord.getY()) {
        alreadyContains.add(true);
      } else {
        alreadyContains.add(false);
      }
    }
    return alreadyContains.contains(true);
  }
}

