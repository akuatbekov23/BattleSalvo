package cs3500.pa04.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ship
 */
public class Ship {

  private boolean sunk;
  private final ShipType type;
  private final List<Coord> position;

  /**
   * @param type the type of Ship
   * @param position the list of coordinates that the ship is positioned at
   */
  public Ship(ShipType type, ArrayList<Coord> position) {
    this.sunk = false;
    this.type = type;
    this.position = position;
  }

  /**
   * @return boolean, whether the ship is sunk
   */
  public boolean isSunk() {
    return this.sunk;
  }

  /**
   * @return ShipType, the type of ship
   */
  public ShipType getType() {
    return this.type;
  }

  /**
   * unsinks the ship
   */
  public void unSunk() {
    sunk = false;
  }

  /**
   * declares a ship as sunk
   */
  public void makeSunk() {
    this.sunk = true;
  }

  /**
   * @return the list of coordinates that the ship is positioned at
   */
  public List<Coord> getPosition() {
    return this.position;
  }
}
