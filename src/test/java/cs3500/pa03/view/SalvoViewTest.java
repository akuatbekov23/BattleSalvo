package cs3500.pa03.view;

import cs3500.pa04.controller.InputController;
import cs3500.pa04.view.SalvoView;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the SalvoView class.
 */
public class SalvoViewTest {

  private SalvoView salvoView;
  private ByteArrayOutputStream outputStream;
  private InputController inputController;

  /**
   * setup before each test
   */
  @BeforeEach
  public void setUp() {
    salvoView = new SalvoView();
    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    inputController = new InputController();
  }

  /**
   * tests the print string
   */
  @Test
  public void testPrintString() {
    String message = "Congratulations!";
    String printed = salvoView.printString(message);
    String expected = message;
    Assertions.assertEquals(expected, printed);
  }


  /**
   * tests that the height again prompt shows
   */
  @Test
  public void testSizeHeightAgain() {
    salvoView.sizeHeightAgain();
    String expected = "Looks like that ocean isn't reachable.\n"
        + "Make sure that your height is in the range of [6, 15]!\n";
    String printed = outputStream.toString();
    Assertions.assertEquals(expected, printed);
  }


  /**
   * test for showing the board
   */
  @Test
  public void testShowBoard() {
    String[][] gameBoard = {{"O", "O", "O"}, {"X", "X", "X"}};
    String message = "Board state:\n";
    salvoView.showBoard(gameBoard, message);
    String expected = "Board state:\n"
        + "OOO\n"
        + "XXX\n\n";
    String printed = outputStream.toString();
    Assertions.assertEquals(expected, printed);
  }


  /**
   * test for requesting shots again
   */
  @Test
  public void testShotsAgain() {
    int shots = 2;
    salvoView.shotsAgain(shots);
    String expected = "You scallywag! What are you doing?\n"
        + "You have to actually hit the enemy!\n"
        + "Remember you can only enter "
        + shots
        + " shots.\n";
    String printed = outputStream.toString();
    Assertions.assertEquals(expected, printed);
  }
}
