package cs3500.pa04.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * represents a Board
 */
public class Board {

  private final String[][] gameBoard;
  private final List<Coord> hitSpots;
  private ArrayList<Ship> myShips = new ArrayList<>();

  /**
   * resets the color
   */
  public static final String ANSI_RESET = "\u001B[0m";
  /**
   * represents blue text
   */
  public static final String ANSI_BLUE = "\u001B[34m";

  /**
   * represents red text
   */
  public static final String ANSI_RED = "\u001B[31m";

  /**
   * represents green text
   */
  public static final String ANSI_GREEN = "\u001B[32m";


  /**
   * constructs a new board with an empty board and an empty array list of hit spaces
   *
   * @param height the height of the board
   * @param width  the width of the board
   */
  public Board(int height, int width) {
    gameBoard = createBoard(width, height);
    hitSpots = new ArrayList<>();
  }

  /**
   * @param height the height of the board
   * @param width  the width of the board
   * @return an empty game board
   */
  public String[][] createBoard(int height, int width) {
    String[][] gameBoard = new String[width][height];
    for (String[] strings : gameBoard) {
      Arrays.fill(strings, (ANSI_BLUE + "= " + ANSI_RESET));
    }
    return gameBoard;
  }

  /**
   * @return the game board
   */
  public String[][] getGameBoard() {
    return gameBoard;
  }

  /**
   * @return the list of coordinates that have been hit
   */
  public List<Coord> getHitSpots() {
    return hitSpots;
  }

  /**
   * @param gameBoard      the game board to place ships onto
   * @param specifications the types of ships and the amount to place
   */
  public void placeShips(String[][] gameBoard, Map<ShipType, Integer> specifications) {
    Placement place = new Placement();
    place.placeAllShips(specifications.get(ShipType.CARRIER), gameBoard, ShipType.CARRIER);
    place.placeAllShips(specifications.get(ShipType.BATTLESHIP), gameBoard, ShipType.BATTLESHIP);
    place.placeAllShips(specifications.get(ShipType.DESTROYER), gameBoard, ShipType.DESTROYER);
    place.placeAllShips(specifications.get(ShipType.SUBMARINE), gameBoard, ShipType.SUBMARINE);
  }


  /**
   * @param missedShots list of coordinates that missed
   */
  public void updateBoardMissed(List<Coord> missedShots) {
    for (Coord c : missedShots) {
      int coordX = c.getX();
      int coordY = c.getY();
      gameBoard[coordY][coordX] = (ANSI_RED + "X " + ANSI_RESET);
    }
  }

  /**
   * @param hitShots the list of coordinates that hit something
   */
  public void updateBoardHit(List<Coord> hitShots) {
    for (Coord c : hitShots) {
      int coordX = c.getX();
      int coordY = c.getY();
      gameBoard[coordY][coordX] = (ANSI_GREEN + "O " + ANSI_RESET);
    }
  }

  /**
   * @return the board's ships
   */
  public List<Ship> getShips() {
    return myShips;
  }


  /**
   * @param coord the coordinate to add to the list of hit spots
   */
  public void addHitSpot(Coord coord) {
    hitSpots.add(coord);
  }
}
