package cs3500.pa03.view;

import cs3500.pa03.controller.InputController;
import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import java.util.ArrayList;

/**
 * represents the terminal view for the player regarding getting inputs
 */
public class SalvoView {

  private final InputController input = new InputController();

  /**
   * prompts the user to give a board height and width
   */
  public void promptBoardSize() {
    System.out.println("Congratulations on your promotion to Fleet Admiral!");
    System.out.println("Prove your worth and destroy an OOD fleet.");
    System.out.println("Please enter the HEIGHT of the sea you wish to conquer:");

    input.getBoardHeight();

    System.out.println("Please enter the WIDTH of the sea you wish to conquer:");
    input.getBoardWidth();

  }

  /**
   * prompts the user to input a new height
   */
  public void sizeHeightAgain() {
    System.out.println("Looks like that ocean isn't reachable.");
    System.out.println("Make sure that your height is in the range of [6, 15]!");
  }

  /**
   * prompts the user to input a new width
   */
  public void sizeWidthAgain() {
    System.out.println("Looks like that ocean isn't reachable.");
    System.out.println("Make sure that your width is in the range of [6, 15]!");

  }

  /**
   * prompts the user to add fleet size
   */
  public void promptFleetSize() {
    System.out.println("Alright matey, sounds good to me. Let's prepare your fleet now.");
    System.out.println("Make sure to plan strategically. Get at least 1 of each ship type.");
    System.out.println("Your fleet size may not exceed " + input.getMaxShips());
    System.out.println("Enter in the order of: [Carrier, BattleShip, Destroyer, Submarine].");
    System.out.println("Separate each ship amount with a new line.");
    input.getFleetSize();
  }


  /**
   * @param maxShips the max amount of ships that a Player can have
   */
  public void fleetSizeAgain(int maxShips) {
    System.out.println("Blimey! You're an admiral you landlubber. Make your fleet balanced.");
    System.out.println("Don't forget you need at least one of each ship type.");
    System.out.println("Remember your fleet size may not exceed " + maxShips);
  }

  /**
   * @param gameBoard the game board to print out
   * @param message the message to send to the user
   */
  public void showBoard(String[][] gameBoard, String message) {
    System.out.println(message);
    for (String[] strings : gameBoard) {
      for (int j = 0; j < gameBoard[0].length; j++) {
        System.out.print(strings[j]);
      }
      System.out.println();
    }
    System.out.println();
  }

  /**
   * @param shots the amount of shots to be taken
   * @param seenOppBoard the seen opponent's board
   * @param takenShots the list of shots that have been taken
   */
  public void askForShots(int shots, Board seenOppBoard, ArrayList<Coord> takenShots) {
    System.out.println("Send them to Davey Jones' locker!");
    System.out.println("Remember, dead men tell no tales. Don't let that be you!");
    System.out.println("Please enter " + shots + " shots");
    input.getShotCoords(shots, seenOppBoard, takenShots);
  }

  /**
   * @param shots the amount of shots the player can take
   */
  public void shotsAgain(int shots) {
    System.out.println("You scallywag! What are you doing?");
    System.out.println("You have to actually hit the enemy!");
    System.out.println("Remember you can only enter " + shots + " shots.");
  }

}
