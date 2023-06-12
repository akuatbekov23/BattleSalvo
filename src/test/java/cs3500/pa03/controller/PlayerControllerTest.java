package cs3500.pa03.controller;


import cs3500.pa04.Player;
import cs3500.pa04.controller.PlayerController;
import cs3500.pa04.model.Ai;
import cs3500.pa04.model.Board;
import cs3500.pa04.model.ShipType;
import cs3500.pa04.model.User;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests for the PlayerController class.
 */
public class PlayerControllerTest {

  private PlayerController playerController;

  @BeforeEach
  public void setUp() {
    playerController = new PlayerController();
  }


  @Test
  public void testGameLoop() {
    // Test case where the game ends in a tie
    Player user = new User(new Board(10, 10),
        new Board(10, 10), new Board(10, 10));
    Player bot = new Ai(new Board(10, 10),
        new Board(10, 10), new Board(10, 10));

    // Set up boards and ships
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);

    user.setup(10, 10, specifications);
    bot.setup(10, 10, specifications);


    // Test case where the user wins
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 0);
    specifications.put(ShipType.SUBMARINE, 0);

    user.setup(10, 10, specifications);
    bot.setup(10, 10, specifications);

  }
}
