package cs3500.pa04.view;

import static java.lang.System.out;

import cs3500.pa04.controller.InputController;
import cs3500.pa04.model.Board;
import cs3500.pa04.model.Coord;
import java.util.ArrayList;

/**
 * represents the terminal view for the player regarding getting inputs
 */
public class SalvoView {

  private final InputController input = new InputController();

  /**
   * @param s the string to be printed
   *
   * @return the string that was printed
   */
  public String printString(String s) {
    out.append(s);
    return s;
  }


  /**
   * prompts the user to give a board height and width
   */
  public void promptBoardSize() {
    printString("Congratulations on your promotion to Fleet Admiral!\n");
    printString("Prove your worth and destroy an OOD fleet.\n");
    printString("Please enter the HEIGHT of the sea you wish to conquer:\n");

    input.getBoardHeight();

    printString("Please enter the WIDTH of the sea you wish to conquer:\n");
    input.getBoardWidth();

  }

  /**
   * prompts the user to input a new height
   */
  public void sizeHeightAgain() {
    printString("Looks like that ocean isn't reachable.\n");
    printString("Make sure that your height is in the range of [6, 15]!\n");
  }

  /**
   * prompts the user to input a new width
   */
  public void sizeWidthAgain() {
    printString("Looks like that ocean isn't reachable.\n");
    printString("Make sure that your width is in the range of [6, 15]!\n");

  }

  /**
   * prompts the user to add fleet size
   */
  public void promptFleetSize() {
    printString("Alright matey, sounds good to me. Let's prepare your fleet now.\n");
    printString("Make sure to plan strategically. Get at least 1 of each ship type.\n");
    printString("Your fleet size may not exceed " + input.getMaxShips() + "\n");
    printString("Enter in the order of: [Carrier, BattleShip, Destroyer, Submarine].\n");
    printString("Separate each ship amount with a new line.\n");
    input.getFleetSize();
  }


  /**
   * @param maxShips the max amount of ships that a Player can have
   */
  public void fleetSizeAgain(int maxShips) {
    printString("Blimey! You're an admiral you landlubber. Make your fleet balanced.\n");
    printString("Don't forget you need at least one of each ship type.\n");
    printString("Remember your fleet size may not exceed " + maxShips + "\n");
  }

  /**
   * @param gameBoard the game board to print out
   * @param message   the message to send to the user
   */
  public void showBoard(String[][] gameBoard, String message) {
    printString(message);
    for (String[] strings : gameBoard) {
      for (int j = 0; j < gameBoard[0].length; j++) {
        printString(strings[j]);
      }
      printString("\n");
    }
    printString("\n");
  }

  /**
   * @param shots        the amount of shots to be taken
   * @param seenOppBoard the seen opponent's board
   * @param takenShots   the list of shots that have been taken
   */
  public void askForShots(int shots, Board seenOppBoard, ArrayList<Coord> takenShots) {
    printString("Send them to Davey Jones' locker!\n");
    printString("Remember, dead men tell no tales. Don't let that be you!\n");
    printString("Please enter " + shots + " shots\n");
    input.getShotCoords(shots, seenOppBoard, takenShots);
  }

  /**
   * @param shots the amount of shots the player can take
   */
  public void shotsAgain(int shots) {
    printString("You scallywag! What are you doing?\n");
    printString("You have to actually hit the enemy!\n");
    printString("Remember you can only enter " + shots + " shots.\n");
  }

}
