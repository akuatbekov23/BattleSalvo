package cs3500.pa04.model;

/**
 * represents a point with an x,y coordinate
 */
public class Coord {
  private final int pointX;
  private final int pointY;

  /**
   * @param pointX the x coordinate
   * @param pointY the y coordinate
   */
  public Coord(int pointX, int pointY) {
    this.pointX = pointX;
    this.pointY = pointY;
  }

  /**
   * @return int representing the X coordinate
   */
  public int getX() {
    return this.pointX;
  }

  /**
   * @return int representing the Y coordinate
   */
  public int getY() {
    return this.pointY;
  }

  /**
   * @param o the coordinate to compare to
   * @return whether the coordinates are equal
   */
  @Override
  public boolean equals(Object o) {
    boolean value = false;
    if (o instanceof Coord coord) {
      value = this.getX() == coord.getX() && this.getY() == coord.getY();
    }
    return value;
  }
}
