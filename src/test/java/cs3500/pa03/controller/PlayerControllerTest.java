package cs3500.pa03.controller;


import cs3500.pa04.Player;
import cs3500.pa04.controller.PlayerController;
import cs3500.pa04.model.Ai;
import cs3500.pa04.model.Board;
import cs3500.pa04.model.GameResult;
import cs3500.pa04.model.ShipType;
import cs3500.pa04.model.User;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
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
  public void testStartGame() {
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    //playerController.startGame(6, 6, specifications);
    // check the output stream that it is correct


    // puts in a string of inputs to test the game loop


    // play out the game loop and check the output stream that it is correct


    // check that the game loop ends correctly

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    PrintStream originalPrintStream = System.out;
    System.setOut(printStream);

    // Provide input to test the game loop
    ByteArrayInputStream inputStream =
        new ByteArrayInputStream("1 0\n1 2\n1 3\n1 4\n".getBytes());
    InputStream originalInputStream = System.in;
    System.setIn(inputStream);

    // Start the game
    playerController.startGame(6, 6, specifications);

    // Restore the original input and output streams
    System.setOut(originalPrintStream);
    System.setIn(originalInputStream);

    // Verify the console output
    String consoleOutput = outputStream.toString();

  }

  @Test
  public void testStartGame2() {
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);

    playerController.startGame(6, 6, specifications);

    // Verify that playerBoard, botBoard, seenPlayerBoard, and seenBotBoard are initialized
    Assertions.assertNotNull(playerController.playerBoard);
    Assertions.assertNotNull(playerController.botBoard);
    Assertions.assertNotNull(playerController.seenPlayerBoard);
    Assertions.assertNotNull(playerController.seenBotBoard);

    // Verify that user and bot are initialized as User and Ai instances respectively
    Assertions.assertTrue(playerController.user instanceof User);
    Assertions.assertTrue(playerController.bot instanceof Ai);



    // Verify that the game loop is executed
    // Add assertions to verify the game loop behavior
    // ...

    // Verify that the game loop ends correctly
    // Add assertions to verify the game loop ends as expected
    // ...
  }



  @Test
  public void testGameLoop() {
    // Test case where the game ends in a tie
    Player user = new User();
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

  @Test
  public void testGameLoop2() {
    // Test case where the game ends in a tie
    playerController.playerBoard = new Board(10, 10);
    playerController.seenBotBoard = new Board(10, 10);
    playerController.botBoard = new Board(10, 10);
    playerController.seenPlayerBoard = new Board(10, 10);
    playerController.user = new User(playerController.playerBoard,
        playerController.seenBotBoard, playerController.botBoard) {
      @Override
      public void endGame(GameResult result, String message) {
        // Verify the game ends in a tie
        Assertions.assertEquals(GameResult.TIE, result);
        Assertions.assertEquals("TIE!", message);
      }
    };
    playerController.bot = new Ai(playerController.botBoard,
        playerController.seenPlayerBoard, playerController.seenBotBoard);

    // Set up boards and ships
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);

    playerController.user.setup(10, 10, specifications);
    playerController.bot.setup(10, 10, specifications);

    // Start the game loop
    playerController.gameLoop();

    // Test case where the user wins
    playerController.user = new User(playerController.playerBoard,
        playerController.seenBotBoard, playerController.botBoard) {
      @Override
      public void endGame(GameResult result, String message) {
        // Verify the user wins
        Assertions.assertEquals(GameResult.WIN, result);
        Assertions.assertEquals("You win!", message);
      }
    };
    playerController.bot = new Ai(playerController.botBoard,
        playerController.seenPlayerBoard, playerController.seenBotBoard);

    // Update specifications to make the user win
    specifications.put(ShipType.DESTROYER, 0);
    specifications.put(ShipType.SUBMARINE, 0);

    playerController.user.setup(10, 10, specifications);
    playerController.bot.setup(10, 10, specifications);

    // Start the game loop
    playerController.gameLoop();

    // Test case where the user loses
    playerController.user = new User(playerController.playerBoard,
        playerController.seenBotBoard, playerController.botBoard) {
      @Override
      public void endGame(GameResult result, String message) {
        // Verify the user loses
        Assertions.assertEquals(GameResult.LOSE, result);
        Assertions.assertEquals("You lost!", message);
      }
    };
    playerController.bot = new Ai(playerController.botBoard,
        playerController.seenPlayerBoard, playerController.seenBotBoard);

    // Update specifications to make the user lose
    specifications.put(ShipType.CARRIER, 0);
    specifications.put(ShipType.BATTLESHIP, 0);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);

    playerController.user.setup(10, 10, specifications);
    playerController.bot.setup(10, 10, specifications);

    // Start the game loop
    playerController.gameLoop();
  }
}
