package cs3500.pa03.model;

/**
 * represents the types of ships a player can have
 */
public enum ShipType {
  /**
   * represents a CARRIER
   */
  CARRIER(6),
  /**
   * represents a BATTLESHIP
   */
  BATTLESHIP(5),
  /**
   * represents a DESTROYER
   */
  DESTROYER(4),
  /**
   * represents a SUBMARINE
   */
  SUBMARINE(3);

  /**
   * represents how many spaces of the board the ship type takes up
   */
  private final int size;

  /**
   * @param size constructs a specific ship of the given size
   */
  ShipType(int size) {
    this.size = size;
  }

  /**
   * @return int - size of the ship
   */
  public int getSize() {
    return this.size;
  }
}

