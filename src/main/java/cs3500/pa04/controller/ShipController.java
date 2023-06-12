package cs3500.pa04.controller;

import cs3500.pa04.model.Board;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Ship;
import java.util.List;

/**
 * controls everything to do with ship counts
 */
public class ShipController {

  /**
   * @return the user's shot count
   */
  public int setShots(List<Ship> myShips) {
    int shotCount = 0;
    for (Ship s : myShips) {
      if (!s.isSunk()) {
        shotCount++;
      }
    }
    return shotCount;
  }

  /**
   * @param myShips the player's ships to update
   * @param myBoard the player's board
   */
  public void updateShip(List<Ship> myShips, Board myBoard) {
    for (Ship s : myShips) {
      List<Coord> positions = s.getPosition();

      for (Coord c : positions) {
        if (!myBoard.getHitSpots().contains(c)) {
          s.unSunk();
          break;
        } else {
          s.makeSunk();
        }
      }
    }
  }
}
