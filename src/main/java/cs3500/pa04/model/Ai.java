package cs3500.pa04.model;

import cs3500.pa04.Player;
import cs3500.pa04.controller.InputController;
import cs3500.pa04.controller.ShipController;
import cs3500.pa04.view.SalvoView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * represents the AI
 */
public class Ai implements Player {
  private Board myBoard;
  private Board seenOpponentBoard;
  private Board perceivedBoard;
  private int shotCount;
  private ShipController controller = new ShipController();

  /**
   * constructor for the AI
   *
   * @param myBoard           the AI's board
   * @param seenOpponentBoard the opponent's board
   * @param perceivedBoard    the AI's perceived board
   */
  public Ai(Board myBoard, Board seenOpponentBoard, Board perceivedBoard) {
    this.myBoard = myBoard;
    this.seenOpponentBoard = seenOpponentBoard;
    this.perceivedBoard = perceivedBoard;
  }

  /**
   * @return the user's name
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
   * @return a list of the user's ships and their coordinates
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
    return myShips;

  }

  /**
   * @return a list of the shots the AI will take
   */
  @Override
  public List<Coord> takeShots() {
    InputController input = new InputController();
    ArrayList<Coord> shotsToTake = new ArrayList<>();
    input.randomShots(shotCount, seenOpponentBoard, shotsToTake);

    for (Coord c : shotsToTake) {
      seenOpponentBoard.getHitSpots().add(c);
    }

    return shotsToTake;
  }

  /**
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return list of coordinates that the opponent hit
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> hitPlaces = new ArrayList<>();
    List<Coord> allPositions = new ArrayList<>();
    for (Ship s : myBoard.getShips()) {
      List<Coord> shipPosition = s.getPosition();
      allPositions.addAll(shipPosition);
    }
    for (Coord posn : opponentShotsOnBoard) {
      myBoard.getHitSpots().add(posn);
    }

    for (Coord pos : opponentShotsOnBoard) {
      for (Coord coord : allPositions) {
        if (pos.equals(coord)) {
          hitPlaces.add(pos);
          break;
        }
      }
    }
    perceivedBoard.updateBoardMissed(opponentShotsOnBoard);
    perceivedBoard.updateBoardHit(hitPlaces);
    return hitPlaces;
  }


  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    SalvoView view = new SalvoView();
    view.showBoard(perceivedBoard.getGameBoard(), "Opponent Board:");

    view.printString("AI Successful hits: ");
    for (Coord c : shotsThatHitOpponentShips) {
      view.printString("[" + c.getX() + " " + c.getY() + "]");
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
  }

}

