package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * a class that creates new instances of ships to represent their data
 */
public class ShipData {
  /**
   * resets the text color
   */
  public static final String ANSI_RESET = "\u001B[0m";
  /**
   * represents purple text
   */
  public static final String ANSI_PURPLE = "\u001B[35m";
  /**
   * represents the blue text
   */
  public static final String ANSI_BLUE = "\u001B[34m";

  /**
   * @param gameBoard the game board to view the coordinates
   * @param type      the type of Ship
   * @param allShips  a list of all the ships on the board
   * @return a list of all the ships including their coordinates
   */
  public List<Ship> representData(String[][] gameBoard, String type, List<Ship> allShips) {
    for (int i = 0; i < gameBoard.length; i++) {
      String[] currentRow = gameBoard[i];

      if (currentRow[0].equals(ANSI_PURPLE + type + ANSI_RESET)) {
        ArrayList<Coord> shipCoords = new ArrayList<>();
        for (int j = 0; j < currentRow.length; j++) {
          if (!currentRow[j].equals(ANSI_BLUE + "= " + ANSI_RESET)) {
            shipCoords.add(new Coord(i, j));
          }
        }
        switch (type) {
          case "C " -> allShips.add(new Ship(ShipType.CARRIER, shipCoords));
          case "B " -> allShips.add(new Ship(ShipType.BATTLESHIP, shipCoords));
          case "D " -> allShips.add(new Ship(ShipType.DESTROYER, shipCoords));
          case "S " -> allShips.add(new Ship(ShipType.SUBMARINE, shipCoords));
          default -> throw new IllegalArgumentException("Invalid ship type");
        }
      }
    }
    return allShips;
  }
}
