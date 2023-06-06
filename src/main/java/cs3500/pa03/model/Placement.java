package cs3500.pa03.model;

/**
 * a class representing placing ships on the game board
 */
public class Placement {
  /**
   * resets the text color
   */
  public static final String ANSI_RESET = "\u001B[0m";
  /**
   * represents purple text
   */
  public static final String ANSI_PURPLE = "\u001B[35m";
  /**
   * represents blue text
   */
  public static final String ANSI_BLUE = "\u001B[34m";
  /**
   * represents a randomized number
   */
  private final Randomize randomize = new Randomize();


  /**
   * @param shipAmount the total amount of ships
   * @param gameBoard the game board to place ships
   * @param type the type of ship being placed
   */
  public void placeAllShips(int shipAmount, String[][] gameBoard, ShipType type) {

    for (int i = 0; i < shipAmount; i++) {
      int verticalPlacement = randomize.randomNumber(0, gameBoard.length);
      String[] currentRow = gameBoard[verticalPlacement];

      while (!currentRow[0].equals(ANSI_BLUE + "= " + ANSI_RESET)) {
        verticalPlacement = randomize.randomNumber(0, gameBoard.length);
        currentRow = gameBoard[verticalPlacement];
      }

      String representation = switch (type) {
        case CARRIER -> "C ";
        case BATTLESHIP -> "B ";
        case DESTROYER -> "D ";
        case SUBMARINE -> "S ";
      };

      for (int j = 0; j < type.getSize(); j++) {
        currentRow[j] = (ANSI_PURPLE + representation + ANSI_RESET);
      }
    }
  }
}