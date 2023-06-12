package cs3500.pa04.controller;

import cs3500.pa04.model.Board;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Randomize;
import cs3500.pa04.model.ShipType;
import cs3500.pa04.view.SalvoView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * the controller that accepts and reads user inputs
 */
public class InputController {
  private final ValidatorController validator = new ValidatorController();
  private int height;
  private int width;
  private final Map<ShipType, Integer> specifications = new HashMap<>();

  /**
   * prompts the user to enter the board height
   */
  public void getBoardHeight() {

    Scanner sc;
    sc = new Scanner(System.in);

    SalvoView view = new SalvoView();

    String height = sc.nextLine();

    sc = new Scanner(height);

    if (sc.hasNextInt()) {
      if (!validator.validateSize(Integer.parseInt(height))) {
        view.sizeHeightAgain();
        getBoardHeight();
      } else {
        this.height = Integer.parseInt(height);
      }
    } else {
      view.sizeHeightAgain();
      getBoardHeight();
    }
  }

  /**
   * prompts the user to enter the board width
   */
  public void getBoardWidth() {

    Scanner sc;
    sc = new Scanner(System.in);

    SalvoView view = new SalvoView();

    String width = sc.nextLine();

    sc = new Scanner(width);

    if (sc.hasNextInt()) {
      if (!validator.validateSize(Integer.parseInt(width))) {
        view.sizeWidthAgain();
        getBoardWidth();
      } else {
        this.width = Integer.parseInt(width);
      }
    } else {
      view.sizeWidthAgain();
      getBoardWidth();
    }
  }

  /**
   * @return the max number of ships
   */
  public int getMaxShips() {
    return Math.min(this.height, this.width);
  }

  /**
   * reads the user's input for fleet size
   */
  public void getFleetSize() {

    int[] ships = new int[4];
    SalvoView view = new SalvoView();

    for (int i = 0; i < ships.length; i++) {
      Scanner sc;
      sc = new Scanner(System.in);

      String shipAmount = sc.nextLine();
      sc = new Scanner(shipAmount);

      if (sc.hasNextInt()) {
        ships[i] = sc.nextInt();
      } else {
        view.fleetSizeAgain(getMaxShips());
        getFleetSize();
        break;
      }
    }
    if (!validator.validateFleetSize(getMaxShips(), ships)) {
      specifications.put(ShipType.CARRIER, ships[0]);
      specifications.put(ShipType.BATTLESHIP, ships[1]);
      specifications.put(ShipType.DESTROYER, ships[2]);
      specifications.put(ShipType.SUBMARINE, ships[3]);
      PlayerController playerController = new PlayerController();
      playerController.startGame(height, width, specifications);
    } else {
      view.fleetSizeAgain(getMaxShips());
      getFleetSize();
    }
  }

  /**
   * @param shotCount         the amount of shots possible
   * @param seenOpponentBoard the opponent's board that can be seen by the player
   * @param shots             the list of shots to be taken
   * @return
   */
  public ArrayList<Coord> getShotCoords(int shotCount, Board seenOpponentBoard, ArrayList<Coord> shots) {
    shots.clear();

    for (int i = 0; i < shotCount; i++) {
      Scanner sc;
      sc = new Scanner(System.in);
      String coordinate = sc.nextLine();
      sc = new Scanner(coordinate);
      if (sc.hasNextInt()) {
        int coordX = sc.nextInt();
        if (sc.hasNextInt()) {
          int coordY = sc.nextInt();
          Coord coord = new Coord(coordX, coordY);
          if (validator.invalidSpot(coord, seenOpponentBoard) || validator.isDupe(coord, shots)) {
            resetShots(shotCount, seenOpponentBoard, shots);
            break;
          } else {
            if (validator.beenHit(coord, seenOpponentBoard)) {
              resetShots(shotCount, seenOpponentBoard, shots);
              break;
            } else {
              shots.add(coord);
            }
          }
        } else {
          resetShots(shotCount, seenOpponentBoard, shots);
          break;
        }
      } else {
        resetShots(shotCount, seenOpponentBoard, shots);
        break;
      }
    }
    return shots;
  }

  /**
   * @param shotCount         the amount of shots to take
   * @param seenOpponentBoard the opponent's board
   * @param shots             the list of shots to be taken
   */
  public void resetShots(int shotCount, Board seenOpponentBoard, ArrayList<Coord> shots) {
    SalvoView view = new SalvoView();
    view.shotsAgain(shotCount);
    getShotCoords(shotCount, seenOpponentBoard, shots);
  }

  /**
   * @param shotCount         the amount of shots to take
   * @param seenOpponentBoard the opponent's board that can be seen
   * @param shots             the list of shots to be taken
   */
  public void randomShots(int shotCount, Board seenOpponentBoard, ArrayList<Coord> shots) {
    Randomize randomize = new Randomize();
    String[][] seenBoard = seenOpponentBoard.getGameBoard();
    int height = seenBoard.length;
    int width = seenBoard[0].length;

    for (int i = 0; i < shotCount; i++) {
      int coordX = randomize.randomNumber(0, width);
      int coordY = randomize.randomNumber(0, height);
      Coord coord = new Coord(coordX, coordY);
      while (validator.beenHit(coord, seenOpponentBoard) || validator.isDupe(coord, shots)) {
        coordX = randomize.randomNumber(0, width);
        coordY = randomize.randomNumber(0, height);
        coord = new Coord(coordX, coordY);
      }
      shots.add(coord);
    }
  }

  /**
   * @return the board width
   */
  public int getHeight() {
    return height;
  }

  /**
   * @return the board height
   */
  public int getWidth() {
    return width;
  }

  /**
   * @param i the board height
   */
  public void setHeight(int i) {
    this.height = i;
  }

  /**
   * @param i the board width
   */
  public void setWidth(int i) {
    this.width = i;
  }

  /**
   * @return the specifications of the game
   */
  public Map<ShipType, Integer> getSpecifications() {
    return specifications;
  }
}
