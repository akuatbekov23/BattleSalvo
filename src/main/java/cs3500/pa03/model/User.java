package cs3500.pa03.model;

import cs3500.pa03.controller.ShipController;
import cs3500.pa04.Player;
import cs3500.pa03.view.SalvoView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * represents the User
 */
public class User implements Player {
  private Board myBoard;
  private Board seenOpponentBoard;
  private Board opponentBoard;
  private int shotCount;
  private ArrayList<Coord> shotsToTake;
  private ShipController controller = new ShipController();

  public User(Board myBoard, Board seenOpponentBoard, Board opponentBoard) {
    this.myBoard = myBoard;
    this.seenOpponentBoard = seenOpponentBoard;
    this.opponentBoard = opponentBoard;
  }

  /**
   * @return the name of the player
   */
  @Override
  public String name() {
    return null;
  }

  /**
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return a list of all the ships and their placements
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    myBoard.placeShips(myBoard.getGameBoard(), specifications); // place my ships
    List<Ship> myShips = myBoard.getShips();
    ShipData data = new ShipData();
    data.representData(myBoard.getGameBoard(), "C ", myShips);
    data.representData(myBoard.getGameBoard(), "B ", myShips);
    data.representData(myBoard.getGameBoard(), "D ", myShips);
    data.representData(myBoard.getGameBoard(), "S ", myShips); // get list of ships & coords
    shotCount = myShips.size(); // set shot # to current amount of non-sunk ships
    SalvoView view = new SalvoView();
    view.showBoard(seenOpponentBoard.getGameBoard(), "Opponent Board:");
    view.showBoard(myBoard.getGameBoard(), "Your Board:");
    return myShips;

  }

  /**
   * @return the List of valid shots the player takes
   */
  @Override
  public List<Coord> takeShots() {
    shotsToTake = new ArrayList<>();
    SalvoView view = new SalvoView();
    view.askForShots(shotCount, seenOpponentBoard, shotsToTake);

    for (Coord c : shotsToTake) {
      seenOpponentBoard.getHitSpots().add(c);
    }

    return shotsToTake;
  }

  /**
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a list of the shots that the opponent hit
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> hitPlaces = new ArrayList<>();
    List<Coord> allPositions = new ArrayList<>();
    for (Ship s : myBoard.getShips()) {
      List<Coord> shipPosition = s.getPosition();
      allPositions.addAll(shipPosition);
    }
    for (Coord pos : opponentShotsOnBoard) {
      myBoard.getHitSpots().add(pos);
    }

    for (Coord pos : opponentShotsOnBoard) {
      for (Coord coord : allPositions) {
        if (pos.equals(coord)) {
          hitPlaces.add(pos);
          break;
        }
      }
    }
    myBoard.updateBoardMissed(opponentShotsOnBoard);
    myBoard.updateBoardHit(hitPlaces);
    return hitPlaces;
  }

  /**
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    SalvoView view = new SalvoView();
    view.showBoard(myBoard.getGameBoard(), "Your Board:");

    System.out.println("Your Successful hits: ");
    for (Coord c : shotsThatHitOpponentShips) {
      System.out.println("[" + c.getX() + " " + c.getY() + "]");
    }
    controller.updateShip(myBoard.getShips(), myBoard);
    shotCount = controller.setShots(myBoard.getShips());

  }

  /**
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    System.out.println(reason);

  }
}
