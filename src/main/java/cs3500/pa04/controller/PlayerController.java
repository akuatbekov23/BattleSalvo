package cs3500.pa04.controller;

import cs3500.pa04.model.Ai;
import cs3500.pa04.model.Board;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.GameResult;
import cs3500.pa04.model.Ship;
import cs3500.pa04.model.ShipType;
import cs3500.pa04.model.User;
import cs3500.pa04.Player;
import java.util.List;
import java.util.Map;

/**
 * represents a controller for anything Player related
 */
public class PlayerController {

  private Board playerBoard;
  private Board botBoard;
  private Board seenPlayerBoard;
  private Board seenBotBoard;
  private Player user;
  private Player bot;
  private ShipController controller = new ShipController();

  /**
   * @param height         the height of the board
   * @param width          the width of the board
   * @param specifications a map of ship amounts to each ship type
   */
  public void startGame(int height, int width, Map<ShipType, Integer> specifications) {
    playerBoard = new Board(height, width);
    botBoard = new Board(height, width);
    seenPlayerBoard = new Board(height, width);
    seenBotBoard = new Board(height, width);

    user = new User(playerBoard, seenBotBoard, botBoard);
    bot = new Ai(botBoard, seenPlayerBoard, seenBotBoard);

    user.setup(height, width, specifications);
    bot.setup(height, width, specifications);
    gameLoop();
  }

  /**
   * starts the game loop
   */
  public void gameLoop() {
    boolean gameEnd = false;
    while (!gameEnd) {
      List<Coord> userShots = user.takeShots();
      List<Coord> aiShots = bot.takeShots();
      List<Coord> landedAiShots = user.reportDamage(aiShots);
      List<Coord> landedUserShots = bot.reportDamage(userShots);
      bot.successfulHits(landedAiShots);
      user.successfulHits(landedUserShots);

      List<Ship> playerShips = playerBoard.getShips();
      List<Ship> botShips = botBoard.getShips();

      if (controller.setShots(playerShips) == 0 && controller.setShots(botShips) == 0) {
        user.endGame(GameResult.TIE, "TIE!");
        gameEnd = true;
      } else if (controller.setShots(playerShips) == 0) {
        user.endGame(GameResult.LOSE, "You lost!");
        gameEnd = true;
      } else if (controller.setShots(botShips) == 0) {
        user.endGame(GameResult.WIN, "You win!");
        gameEnd = true;
      }

    }
  }
}