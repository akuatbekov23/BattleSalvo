package cs3500.pa03.controller;

import cs3500.pa03.model.Ai;
import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.model.User;
import java.util.List;
import java.util.Map;

/**
 * represents a controller for anything Player related
 */
public class PlayerController {

  private final User user = new User();
  private final Ai ai = new Ai();

  /**
   * @param height         the height of the board
   * @param width          the width of the board
   * @param specifications a map of ship amounts to each ship type
   */
  public void startGame(int height, int width, Map<ShipType, Integer> specifications) {
    user.setup(height, width, specifications);
    ai.setup(height, width, specifications);
    user.setOpponentBoard(ai.getMyBoard()); // set User's opponent board to AI board
    ai.setOpponentBoard(user.getMyBoard()); // set AI's opponent board to User board
    gameLoop();
  }

  /**
   * starts the game loop
   */
  public void gameLoop() {
    boolean gameEnd = false;
    while (!gameEnd) {
      Board seen = user.getSeenOpponentBoard();
      ai.setPerceived(seen);
      List<Coord> userShots = user.takeShots();
      List<Coord> aiShots = ai.takeShots();
      List<Coord> landedAiShots = user.reportDamage(aiShots);
      List<Coord> landedUserShots = ai.reportDamage(userShots);
      ai.successfulHits(landedAiShots);
      user.successfulHits(landedUserShots);

      if (user.getShotCount() == 0 && ai.getShotCount() == 0) {
        user.endGame(GameResult.TIE, "TIE!");
        gameEnd = true;
      } else if (user.getShotCount() == 0) {
        user.endGame(GameResult.LOSE, "You lost!");
        gameEnd = true;
      } else if (ai.getShotCount() == 0) {
        user.endGame(GameResult.WIN, "You won!");
        gameEnd = true;
      }
    }
  }
}
